/** 
 * Project Name:realTimeLog 
 * File Name:LogStoreHbaseDaoImpl.java 
 * Package Name:com.asiainfo.log.dao.impl 
 * Date:2015年10月5日下午12:48:58 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.dao.impl;  

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.asiainfo.integration.o2p.log.common.bo.SolrBean;
import com.asiainfo.integration.o2p.log.common.hbase.fields.EmbeddedExpAndCtgLogKeys;
import com.asiainfo.integration.o2p.log.common.solr.HbaseConfig;
import com.asiainfo.integration.o2p.log.common.solr.SolrConfig;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.dao.LogStoreDAO;
import com.asiainfo.integration.o2p.log.solr.service.SolrOpServer;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.CtgLogs;
import com.ailk.eaap.op2.bo.EndpointInteraction;
import com.ailk.eaap.op2.bo.ExceptionLogs;
import com.ailk.eaap.op2.bo.LogMessageObject;
import com.ailk.eaap.op2.bo.OriLogClob;

/** 
 * ClassName:LogStoreHbaseDaoImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年10月5日 下午12:48:58 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class LogStoreHbaseDaoImpl implements LogStoreDAO{
    
    
    public  static final Logger LOG = LoggerFactory.getLogger(LogStoreHbaseDaoImpl.class);
    
    private HbaseTemplate hbaseTemplate;
    
    private SolrOpServer solrService;
    

    @Override
    public void insert(String statementName, Object parameterObject) {
        
    }

    @Override
    public void batchInsert(List<LogMessageObject> list) {
        
    }

    
    
    
    @Override
    public Map insertLog(LogMessageObject log) {
        
        Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
        List<ContractInteraction> cis = log.getContractInteractionList();
        final ContractInteraction ci = cis.get(0);
        ci.setCreateTime(currTimestamp);
        final List<EndpointInteraction> eis = log.getEndpointInteractionList();
        final List<OriLogClob> logClobs = log.getOriLogClobList();
        LogUtils.mergeLogEIAndOri(eis, logClobs, currTimestamp);
        final List<ExceptionLogs> exceptions = log.getExceptionLogsList();
        final List<CtgLogs> ctgs = log.getCtgLogsList();
        Class<?> clszz = (Class<?>) ci.getClass();
        final Field[] _CIfields = clszz.getDeclaredFields();
//      long createDt = System.currentTimeMillis();
        //LogUtils.getRowKeyByUUID();
        final String ciRowKey = ci.getContractInteractionId();
        
        try{
            hbaseTemplate.execute(HbaseConfig.TAB_CI, new TableCallback<Put>(){
    
                public Put doInTable(HTableInterface table) throws Throwable {
                    Put put = new Put(Bytes.toBytes(ciRowKey));
                    // persist CI object
                    buildObj(ci, _CIfields, put);
                    //merge the exceptionLog. wrap the obj to json
                    if(!CollectionUtils.isEmpty(exceptions)){
                        mergeException2CI(exceptions, put);
                    }
                    if(!CollectionUtils.isEmpty(ctgs)){
                        mergeCtg2CI(ctgs, put);
                    }
                    
                    if(!CollectionUtils.isEmpty(eis)){
                        JSONArray lcs = JSONArray.fromObject(eis);
                        put.add(HbaseConfig.FAMILY_EI.getBytes(), HbaseConfig.QUALIFIER_EIORILOG.getBytes(), lcs.toString().getBytes());
                    }               
                    table.put(put);
                    return null;
                }
            });
        }catch(Exception e){
            LOG.error("insert log to hbase err,cause by:", e);
        }
        return null;
    }
    
    
    private void buildObj(final Object ci,
            final Field[] fields, Put put)
                    throws IllegalAccessException {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Object fieldValue = field.get(ci);
            if(fieldValue!=null){
                if(field.getType().equals(String.class)&&!StringUtils.hasText(fieldValue.toString())){
                    try{
                        put.add(HbaseConfig.FAMILY_CI.getBytes(), LogUtils.getByteKey(field),
                                LogUtils.getByteValue(field, fieldValue));
                    }catch(RuntimeException e){
                        LOG.error("buildObj err,cause by:",e);
                    }
                }
            }
        }
    }
    
    private void mergeException2CI(
            final List<ExceptionLogs> exceptions, Put put)
                    throws JSONException {
        JSONArray els = new JSONArray();

        for(ExceptionLogs el:exceptions){
            JSONObject e = new JSONObject();
            e.put(EmbeddedExpAndCtgLogKeys.EXP_MSG, el.getExceptionMessage());
            e.put(EmbeddedExpAndCtgLogKeys.EXP_CONTENT, el.getContent());
            els.add(e);
        }
        try{
            if(els.size()>0){
                put.add(HbaseConfig.FAMILY_CI.getBytes(), HbaseConfig.QUALIFIER_EXCEPTION.getBytes(), els.toString().getBytes());
            }   
        }catch(RuntimeException e){
            LOG.error("the Object {},the message:{} put the cell failure! the cause:{}",new Object[]{"ExceptionLogs",els.toString(),ExceptionUtils.getStackTrace(e)});
        }

    }
    
    public void mergeCtg2CI(final List<CtgLogs> ctgs, Put put){
        JSONArray jctgs = new JSONArray();
        for(CtgLogs ctg:ctgs){
            JSONObject e = new JSONObject();
            e.put(EmbeddedExpAndCtgLogKeys.CTG_ERR_CODE,ctg.getErrCode());
            e.put(EmbeddedExpAndCtgLogKeys.CTG_FUN_NAME,ctg.getFunName());
            e.put(EmbeddedExpAndCtgLogKeys.CTG_ERR_MSG,ctg.getErrorMsg());
            e.put(EmbeddedExpAndCtgLogKeys.CTG_DESC,ctg.getDescriptor());
            e.put(EmbeddedExpAndCtgLogKeys.CTG_STATUS,ctg.getStatus());
            e.put(EmbeddedExpAndCtgLogKeys.CTG_TENANT_ID, ctg.getTenantId());
            jctgs.add(e);
        }
        try{
            if(jctgs.size()>0){
                put.add(HbaseConfig.FAMILY_CI.getBytes(), HbaseConfig.QUALIFIER_CTG.getBytes(), jctgs.toString().getBytes());
            }   
        }catch(Exception e){
            LOG.error("the Object {},the message:{} put the cell failure! the cause:{}",new Object[]{"CtgLogs",jctgs.toString(),ExceptionUtils.getStackTrace(e)});
        }
    }

    public HbaseTemplate getHbaseTemplate() {
        return hbaseTemplate;
    }

    public void setHbaseTemplate(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Override
    public void insertOriClobs(List<OriLogClob> oris) {
    }

    @Override
    public void putHbase(List<LogMessageObject> logs, HConnection connection) {
        Field[] ciFields = null;
        HTableInterface table = null;
        List<Put> puts = new ArrayList<Put>();
        Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
        List<ContractInteraction> allCis = new ArrayList<ContractInteraction>();
        try {
            for(LogMessageObject log:logs){
                List<ContractInteraction> cis = log.getContractInteractionList();
                final ContractInteraction ci = cis.get(0);
                //ci.setCreateTime(currTimestamp);
                allCis.add(ci);
                final List<EndpointInteraction> eis = log.getEndpointInteractionList();
                final List<OriLogClob> logClobs = log.getOriLogClobList();
                LogUtils.mergeLogEIAndOri(eis, logClobs, currTimestamp);
                final List<ExceptionLogs> exceptions = log.getExceptionLogsList();
                final List<CtgLogs> ctgs = log.getCtgLogsList();
                Class<?> clszz = (Class<?>) ci.getClass();
                if(ciFields == null){
                    ciFields = clszz.getDeclaredFields();
                }
                final String ciRowKey = ci.getContractInteractionId();
                Put put = new Put(Bytes.toBytes(ciRowKey));
                // persist CI object
                buildObj(ci, ciFields, put);
                //merge the exceptionLog. wrap the obj to json
                if(!CollectionUtils.isEmpty(exceptions)){
                    mergeException2CI(exceptions, put);
                }
                if(!CollectionUtils.isEmpty(ctgs)){
                    mergeCtg2CI(ctgs, put);
                }
                if(!CollectionUtils.isEmpty(eis)){
                    JSONArray jeis = JSONArray.fromObject(eis);
                    put.add(HbaseConfig.FAMILY_EI.getBytes(), HbaseConfig.QUALIFIER_EIORILOG.getBytes(), jeis.toString().getBytes());
                } 
                puts.add(put);
            }  
            table = connection.getTable(HbaseConfig.TAB_CI);
            table.setAutoFlush(false);
            table.put(puts);
            //post to solr
            List<SolrBean> solrs = buildSolrBeans(logs);
            //this.solrService.batchAddIndexToSolr(allCis);
            this.solrService.batchAddIndex(solrs);
        } catch (IOException e) {
            LOG.error("persiste to hbase err,cause by:",e);
        } catch (IllegalAccessException e) {
            LOG.error("persiste to hbase err,cause by:",e);
        }finally{
            if(table != null){
                realeTable(table);
            }
        }
    }
    
    

    @Override
    public void putHbase(LogMessageObject log, HConnection connection) {
        @SuppressWarnings("unchecked")
        List<ContractInteraction> cis = log.getContractInteractionList();
/*      @SuppressWarnings("unchecked")
        List<EndpointInteraction> eis = logMsgObj.getEndpointInteractionList();*/
        @SuppressWarnings("unchecked")
        final List<ExceptionLogs> exceptions = log.getExceptionLogsList();
        @SuppressWarnings("unchecked")
        final List<OriLogClob> logClobs = log.getOriLogClobList();
        Assert.notEmpty(cis);
        final ContractInteraction ci = cis.get(0);
        Class<?> clszz = (Class<?>) ci.getClass();
        final Field[] _CIfields = clszz.getDeclaredFields();
//      long createDt = System.currentTimeMillis();
        //LogUtils.getRowKeyByUUID();
        final String ciRowKey = ci.getContractInteractionId();
        
        //
        HTableInterface table = null;
        try {
            Put put = new Put(Bytes.toBytes(ciRowKey));
            // persist CI object
            buildObj(ci, _CIfields, put);
            //merge the exceptionLog. wrap the obj to json
            if(!CollectionUtils.isEmpty(exceptions)){
                mergeException2CI(exceptions, put);
            }
            if(!CollectionUtils.isEmpty(logClobs)){
                JSONArray lcs = JSONArray.fromObject(logClobs);
                put.add(HbaseConfig.FAMILY_EI.getBytes(), HbaseConfig.QUALIFIER_EIORILOG.getBytes(), lcs.toString().getBytes());
            } 
            table = connection.getTable(HbaseConfig.TAB_CI);
            table.setAutoFlush(false);
            table.put(put);
        } catch (IOException e) {
            LOG.error("persiste to hbase err,cause by:",e);
        } catch (IllegalAccessException e) {
            LOG.error("persiste to hbase err,cause by:",e);
        }finally{
            if(table != null){
                realeTable(table);
            }
        }
        
    }
    
    
    
    private void realeTable(HTableInterface tab){
        try {
            tab.close();
        } catch (IOException e) {
            LOG.error("close hbase table err,cause by:",e);
        }
    }
    
    private List<SolrBean> buildSolrBeans(List<LogMessageObject> logs){
        List<SolrBean> solrs = new ArrayList<SolrBean>();
        for(LogMessageObject log : logs){
            SolrBean solr = new SolrBean();
            List<ContractInteraction> cis = log.getContractInteractionList();
            final ContractInteraction ci = cis.get(0);
            solr.setId(ci.getContractInteractionId()+System.currentTimeMillis());
            solr.setContractInteractionId(ci.getContractInteractionId());
            solr.setTable(SolrConfig.table_CI);
            solr.setSrcTranId( ci.getSrcTranId());
            solr.setSrcSysCode(ci.getSrcSysCode());
            solr.setDstSysCode( ci.getDstSysCode());
            solr.setSrcReqTime(ci.getSrcReqTime().getTime());
            solr.setCreateTime( ci.getCreateTime().getTime());
            solr.setTenantId( ci.getTenantId());
            solr.setServiceStyle( ci.getServiceStyle());
            solr.setContractVersion( ci.getContractVersion());
            solr.setResponseCode( ci.getResponseCode());
            
            
            final List<CtgLogs> ctgs = log.getCtgLogsList();
            if(!CollectionUtils.isEmpty(ctgs)){
                CtgLogs ctg = ctgs.get(0);
                solr.setFunName(ctg.getFunName());
                solr.setErrCode(ctg.getErrCode());
            }
            solrs.add(solr);
        }
        return solrs;
    }

    public SolrOpServer getSolrService() {
        return solrService;
    }

    public void setSolrService(SolrOpServer solrService) {
        this.solrService = solrService;
    }
    
    
}
