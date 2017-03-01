/** 
 * Project Name:stormDemo 
 * File Name:IbatisDAOImpl.java 
 * Package Name:com.ai.mine.rmdb.impl 
 * Date:2015年8月25日上午9:44:29 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.dao.impl;  

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.HConnection;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.integration.o2p.log.common.util.Compressor;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.asiainfo.integration.o2p.log.dao.LogStoreDAO;
import com.ailk.eaap.o2p.common.cache.CacheKey;
import com.ailk.eaap.o2p.common.cache.ICacheFactory;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.CtgLogs;
import com.ailk.eaap.op2.bo.EndpointInteraction;
import com.ailk.eaap.op2.bo.ExceptionLogs;
import com.ailk.eaap.op2.bo.JdbcDataSource;
import com.ailk.eaap.op2.bo.LogMessageObject;
import com.ailk.eaap.op2.bo.OriLogClob;
import com.ailk.eaap.op2.common.InType;
import com.linkage.rainbow.dao.MultiDataSource;


/** 
 * ClassName:IbatisDAOImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年8月25日 上午9:44:29 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class LogStoreDaoImpl extends SqlSessionDaoSupport  implements LogStoreDAO{

    public  static final Logger LOG = LoggerFactory.getLogger(LogStoreDaoImpl.class); 
    @Resource(name="cacheFactory")
	private ICacheFactory<String, Object> cacheFactory;
    private MultiDataSource multiDataSource;
    
    @Override
    public void insert(String statementName, Object parameterObject) {
         this.getSqlSession().insert(statementName, parameterObject);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map insertLog(LogMessageObject logMessageObject){
        Map map = new HashMap();
        Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
        //生成主表主键及关联表外键，除ctg对象，ctg需要在另外赋值
        String contractInteractionId = "";
        //logMessageObject.setAllContractInteractionId(contractInteractionId);
        //生成表后缀
//        logMessageObject.setDataBaseTableSuffix(LogUtils.getTableSuffix(currTimestamp));  
        //写入CI对象
        List contractInteractionList = logMessageObject.getContractInteractionList();
        List<ExceptionLogs> exceptionLogs = logMessageObject.getExceptionLogsList();
        this.insertCI(contractInteractionList, currTimestamp,exceptionLogs);
        contractInteractionId = ((ContractInteraction)contractInteractionList.get(0)).getContractInteractionId();
        //写入端点表
        Map<Long,String> endpointInteractionIdMap = new HashMap<Long,String>();
        List endpointInteractionList = logMessageObject.getEndpointInteractionList();
        List oriLogClobList = logMessageObject.getOriLogClobList();
        this.batchInsertEI(endpointInteractionList, currTimestamp, endpointInteractionIdMap,oriLogClobList);
        //写入报文表  报文改为在下一个bolt写入，分开写入提升性能
        
        //去掉报文表记录 2015.11.3
        String ip = logMessageObject.getJavaFiledMap().get(InType.IP);
        this.batchInsertORI(oriLogClobList, currTimestamp, endpointInteractionIdMap, ip);
        //写入异常表
        
        List exceptionLogsList = logMessageObject.getExceptionLogsList();
        this.insertExp(exceptionLogsList, currTimestamp);
        //写入系统内部异常
        List ctgLogsList = logMessageObject.getCtgLogsList();
        this.insertCtg(ctgLogsList, currTimestamp, contractInteractionId);
        map.put("ci", contractInteractionList);
        map.put("ei", endpointInteractionList);
        map.put("oi", oriLogClobList);
        return map;
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
    public void batchInsert(List<LogMessageObject> list) {
        List<ContractInteraction> cis = new ArrayList<ContractInteraction>();
        List<EndpointInteraction> eis = new ArrayList<EndpointInteraction>();
       for(LogMessageObject logMessageObject : list){
           Map map = this.insertLog(logMessageObject);
           cis.addAll((List<ContractInteraction>)map.get("ci"));
           eis.addAll((List<EndpointInteraction>)map.get("ei"));
       }
       
       this.insert("mapper.contractInteraction.insertContractInteraction", cis);
       if(!CollectionUtils.isEmpty(eis)){
           this.insert("mapper.endpointInteraction.insertEndpointInteraction", eis);
       }
    }
    
    public void insertCI(List<ContractInteraction>  cis,Timestamp currTimestamp,List<ExceptionLogs> expLogs){
        combineCI(cis.get(0),currTimestamp,expLogs);
        //this.insert("mapper.contractInteraction.insertContractInteraction", cis);
    }
    public void batchInsertEI(List<EndpointInteraction> eis,Timestamp currTimestamp,Map<Long,String> endpointInteractionIdMap,List<OriLogClob> oriList){
        Map<String,String> clobMap = new HashMap<String,String>();
        for(int i = 0;i<oriList.size();i++){
            OriLogClob ori = oriList.get(i);
            if(ori != null){
                String key = ori.getEndPointId()+ori.getReqOrRes();
                //clobMap.put(key,Compressor.gzip(ori.getMsg()));
                clobMap.put(key,ori.getMsg());
            }
        }
        
        for(int i=0;i<eis.size();i++){
            EndpointInteraction endpointInteraction=(EndpointInteraction)eis.get(i);
            String endpointInteractionId = LogUtils.getUUID();
            endpointInteraction.setEndpointInteractionId(endpointInteractionId);
            //endpointInteraction.setCreateDate(LogUtils.ifAndGetUTCTimestamp(currTimestamp,Properties.getInstance().getIfFormatUTC()));
            endpointInteraction.setInMsg(clobMap.get(endpointInteraction.getEndpointId()+"0"));
            endpointInteraction.setOutMsg(clobMap.get(endpointInteraction.getEndpointId()+"1"));
            endpointInteractionIdMap.put(endpointInteraction.getEndpointId(), endpointInteractionId);
        }
        /*if(!CollectionUtils.isEmpty(eis)){
            this.insert("mapper.endpointInteraction.insertEndpointInteraction", eis);
        }*/
    }
    public void batchInsertORI(List<OriLogClob> oris,Timestamp currTimestamp,Map<Long,String> endpointInteractionIdMap,String ip){
        List<OriLogClob> oriLists = new ArrayList<OriLogClob>();
        for(int i=0;i<oris.size();i++){
            OriLogClob oriLogClob=(OriLogClob)oris.get(i);
            oriLogClob.setSrcIp(ip);
            oriLogClob.setOlcId(LogUtils.getUUID());
            oriLogClob = combineOri(oriLogClob,currTimestamp, endpointInteractionIdMap );
            if(oriLogClob.getEndpointInteractionId()!=null && !"".equals(oriLogClob.getEndpointInteractionId())){
                oriLists.add(oriLogClob);
            }
        }
        if(!CollectionUtils.isEmpty(oris)){
            this.insert("mapper.oriLogClob.insertOriLogClob", oriLists);
        }
    }
    public void insertExp(List<ExceptionLogs> exps,Timestamp currTimestamp){
        String exceptionMsg = "";
        for(int i=0;i<exps.size();i++){
            ExceptionLogs exceptionLogs=(ExceptionLogs)exps.get(i);
            //exceptionLogs.setCreateTime(LogUtils.ifAndGetUTCTimestamp(currTimestamp,Properties.getInstance().getIfFormatUTC()));
            exceptionMsg = exceptionLogs.getExceptionMessage();
            if(exceptionMsg != null && exceptionMsg.length()>1000){
                exceptionLogs.setExceptionMessage(exceptionMsg.substring(0, 1000));
            }
            this.insert("mapper.exceptionLogs.insertExceptionLogs", exceptionLogs);
        }
    }
    public void insertCtg(List<CtgLogs> ctgs,Timestamp currTimestamp,String contractInteractionId){
        for(int i=0;i<ctgs.size();i++){
            CtgLogs ctgLogs=(CtgLogs)ctgs.get(i);
            ctgLogs.setContractInteractionId(contractInteractionId);
            //ctgLogs.setCreateDate(LogUtils.ifAndGetUTCTimestamp(currTimestamp,Properties.getInstance().getIfFormatUTC()));
            this.insert("mapper.ctgLogs.insertCtgLogs", ctgLogs);
        }
    }
    
    
    public ContractInteraction combineCI(ContractInteraction contractInteraction,Timestamp currTimestamp,List<ExceptionLogs> expLogs){
        //contractInteraction.setCreateTime(LogUtils.ifAndGetUTCTimestamp(currTimestamp,Properties.getInstance().getIfFormatUTC()));
        if(!StringUtils.isEmpty(contractInteraction.getResponseDes())){
            String expMsg = contractInteraction.getResponseDes();
            if(expMsg != null && expMsg.length()>1000){
              contractInteraction.setResponseDes(expMsg.substring(0, 1000));
          }
        }
        return contractInteraction;
    }
    public OriLogClob combineOri(OriLogClob oriLogClob,Timestamp currTimestamp,Map<Long,String> endpointInteractionIdMap ){
        String xmlClob = oriLogClob.getMsg();
        if(xmlClob != null && !"".equals(xmlClob) && xmlClob.indexOf("<?")!=-1){
            int startIndex = xmlClob.indexOf("<?");
            String xml0 = xmlClob.substring(0, startIndex);
            String xml = xmlClob.substring(startIndex);
            
            String[] arr = xml.split("\\s{2,}");
            StringBuilder sb = new StringBuilder();
            for(String s :arr){
                sb.append("\r\n"+s);
            }
            xmlClob = xml0+sb.toString();
            oriLogClob.setMsg(xmlClob);   
        }
        //压缩报文
        if(!StringUtils.isEmpty(oriLogClob.getMsg())){
            oriLogClob.setMsg(Compressor.gzip(oriLogClob.getMsg()));
        }
        oriLogClob.setCreateTime(LogUtils.ifAndGetUTCTimestamp(currTimestamp,Properties.getInstance().getIfFormatUTC()));
        if(endpointInteractionIdMap.get(oriLogClob.getEndPointId())!=null
                &&!endpointInteractionIdMap.get(oriLogClob.getEndPointId()).equals("")){
            String endpointInteractionId = (String)endpointInteractionIdMap.get(oriLogClob.getEndPointId());
            oriLogClob.setEndpointInteractionId(endpointInteractionId);
        }
        return oriLogClob;
    }

    @Override
    public void insertOriClobs(List<OriLogClob> oris) {
        this.insert("mapper.oriLogClob.insertOriLogClob", oris);
    }

    @Override
    public void putHbase(LogMessageObject log, HConnection connection) {
    }
    @Override
    public void putHbase(List<LogMessageObject> logs, HConnection connection) {
    }

}
