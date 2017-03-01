/** 
 * Project Name:realTimeLog 
 * File Name:LogStoreHbaseDaoImpl.java 
 * Package Name:com.asianinfo.log.dao.impl 
 * Date:2015年10月5日下午12:48:58 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.dao.impl;  

import java.lang.reflect.Field;
import java.util.List;





import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;

import com.asiainfo.integration.o2p.log.common.bo.RegStatRecent;
import com.asiainfo.integration.o2p.log.common.bo.RegStatSec;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntSec;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.dao.StatisticsStoreDAO;

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
public class StatisticsStoreHbaseDaoImpl implements StatisticsStoreDAO{
    
    private static final Logger LOG = LoggerFactory.getLogger(StatisticsStoreHbaseDaoImpl.class);
    
    public final String TAB_REG_STAT = "REG_STAT";
    public final String TAB_USE_STAT = "USE_STAT";
    public final String FAMILY_USE = "useStatInfo";
    public final String FAMILY_REG = "regStatInfo";
    
    private HbaseTemplate hbaseTemplate;

    

    public HbaseTemplate getHbaseTemplate() {
        return hbaseTemplate;
    }

    public void setHbaseTemplate(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Override
    public  void insert(String statementName, Object parameterObject) {
    }

    @Override
    public int update(String statementName, Object parameterObject) {
        return 0;
    }

    @Override
    public void batchInsert(List<Object> list) {
    }

    @Override
    public void batchUpdate(List<Object> list) {
    }

    @Override
    public void saveOrUpdateUseSec(final List<UseStatCntSec> list) {
        hbaseTemplate.execute(TAB_USE_STAT, new TableCallback<Put>(){
            public Put doInTable(HTableInterface table) throws Throwable {
                for(UseStatCntSec useSec:list){
                    Class<?> clszz = (Class<?>) useSec.getClass();
                    final Field[] fileds = clszz.getDeclaredFields();
                    Put put = new Put(Bytes.toBytes(useSec.getDataSourceKey()));
                    Get get = new Get(Bytes.toBytes(useSec.getDataSourceKey()));
                    Result rs = table.get(get);
                    if(rs.isEmpty()){
                        buildStatisticsPut(FAMILY_USE,useSec, fileds, put);
                        table.put(put);
                    }else{
                      byte[] totalTransByte = rs.getValue(Bytes.toBytes(FAMILY_USE), Bytes.toBytes("totalTrans"));
                      byte[] totalBizErrByte = rs.getValue(Bytes.toBytes(FAMILY_USE), Bytes.toBytes("totalBizErr"));
                      byte[] totalSysErrByte = rs.getValue(Bytes.toBytes(FAMILY_USE), Bytes.toBytes("totalSysErr"));
                      byte[] qosByte = rs.getValue(Bytes.toBytes(FAMILY_USE), Bytes.toBytes("qos"));
                      Integer totalTrans = Bytes.toInt(totalTransByte);
                      Integer totalBizErr = Bytes.toInt(totalBizErrByte);
                      Integer totalSysErr = Bytes.toInt(totalSysErrByte);
                      Integer  qos = Bytes.toInt(qosByte);
                      useSec.setTotalTrans(useSec.getTotalTrans()+totalTrans);
                      useSec.setTotalBizErr(useSec.getTotalBizErr()+totalBizErr);
                      useSec.setTotalSysErr(useSec.getTotalSysErr()+totalSysErr);
                      useSec.setQos((useSec.getQos()+qos*totalTrans)/useSec.getTotalTrans());
                      buildStatisticsPut(FAMILY_USE,useSec, fileds, put);
                      table.put(put);
//                      table.checkAndPut(
//                              Bytes.toBytes(useSec.getDataSourceKey()),
//                              Bytes.toBytes(FAMILY_USE), Bytes.toBytes(fileds[0].getName()),
//                              Bytes.toBytes(useSec.getTotalTrans()), 
//                              put);
                    }
                }
                return null;
            }
        });
    }

    @Override
    public void saveOrUpdateUseOther(final List<UseStatCntRecent> list) {
        hbaseTemplate.execute(TAB_USE_STAT, new TableCallback<Put>(){
            public Put doInTable(HTableInterface table) throws Throwable {
                for(UseStatCntRecent useOther:list){
                    Class<?> clszz = (Class<?>) useOther.getClass();
                    final Field[] fileds = clszz.getDeclaredFields();
                    Put put = new Put(Bytes.toBytes(useOther.getDataSourceKey()));
                    Get get = new Get(Bytes.toBytes(useOther.getDataSourceKey()));
                    Result rs = table.get(get);
                    if(rs.isEmpty()){
                        buildStatisticsPut(FAMILY_USE,useOther, fileds, put);
                        table.put(put);
                    }else{
                      byte[] totalTransByte = rs.getValue(Bytes.toBytes(FAMILY_USE), Bytes.toBytes("totalTrans"));
                      byte[] totalBizErrByte = rs.getValue(Bytes.toBytes(FAMILY_USE), Bytes.toBytes("totalBizErr"));
                      byte[] totalSysErrByte = rs.getValue(Bytes.toBytes(FAMILY_USE), Bytes.toBytes("totalSysErr"));
                      byte[] qosByte = rs.getValue(Bytes.toBytes(FAMILY_USE), Bytes.toBytes("qos"));
                      Integer totalTrans = Bytes.toInt(totalTransByte);
                      Integer totalBizErr = Bytes.toInt(totalBizErrByte);
                      Integer totalSysErr = Bytes.toInt(totalSysErrByte);
                      Integer  qos = Bytes.toInt(qosByte);
                      useOther.setTotalTrans(useOther.getTotalTrans()+totalTrans);
                      useOther.setTotalBizErr(useOther.getTotalBizErr()+totalBizErr);
                      useOther.setTotalSysErr(useOther.getTotalSysErr()+totalSysErr);
                      useOther.setQos((useOther.getQos()+qos*totalTrans)/useOther.getTotalTrans());
                      buildStatisticsPut(FAMILY_USE,useOther, fileds, put);
                      table.put(put);
                    }
                }
                return null;
            }
        });
    }

    @Override
    public void saveOrUpdateRegSec(final List<RegStatSec> list) {
           hbaseTemplate.execute(TAB_REG_STAT, new TableCallback<Put>(){
            public Put doInTable(HTableInterface table) throws Throwable {
                for(RegStatSec regSec:list){
                    Class<?> clszz = (Class<?>) regSec.getClass();
                    final Field[] fileds = clszz.getDeclaredFields();
                    Put put = new Put(Bytes.toBytes(regSec.getDataSourceKey()));
                    Get get = new Get(Bytes.toBytes(regSec.getDataSourceKey()));
                    Result rs = table.get(get);
                    if(rs.isEmpty()){
                        buildStatisticsPut(FAMILY_REG,regSec, fileds, put);
                        table.put(put);
                    }else{
                      byte[] totalTransByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("totalTrans"));
                      byte[] totalBizErrByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("totalBizErr"));
                      byte[] totalSysErrByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("totalSysErr"));
                      byte[] qosByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("qos"));
                      byte[] avgUsingByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("avgUsing"));
                      Integer totalTrans = Bytes.toInt(totalTransByte);
                      Integer totalBizErr = Bytes.toInt(totalBizErrByte);
                      Integer totalSysErr = Bytes.toInt(totalSysErrByte);
                      Integer avgUsing = Bytes.toInt(avgUsingByte);
                      Long  qos = Bytes.toLong(qosByte);
                      regSec.setTotalTrans(regSec.getTotalTrans()+totalTrans);
                      regSec.setTotalBizErr(regSec.getTotalBizErr()+totalBizErr);
                      regSec.setTotalSysErr(regSec.getTotalSysErr()+totalSysErr);
                      regSec.setAvgUsing((regSec.getAvgUsing()+totalTrans*avgUsing)/regSec.getTotalTrans());
                      regSec.setQos((regSec.getQos()+qos*totalTrans)/regSec.getTotalTrans());
                      buildStatisticsPut(FAMILY_REG,regSec, fileds, put);
                      table.put(put);
                    }
                }
                return null;
            }
        });
    }

    @Override
    public void saveOrUpdateRegOther(final List<RegStatRecent> list) {
        hbaseTemplate.execute(TAB_REG_STAT, new TableCallback<Put>(){
            public Put doInTable(HTableInterface table) throws Throwable {
                for(RegStatRecent regOther:list){
                    Class<?> clszz = (Class<?>) regOther.getClass();
                    final Field[] fileds = clszz.getDeclaredFields();
                    Put put = new Put(Bytes.toBytes(regOther.getDataSourceKey()));
                    Get get = new Get(Bytes.toBytes(regOther.getDataSourceKey()));
                    Result rs = table.get(get);
                    if(rs.isEmpty()){
                        buildStatisticsPut(FAMILY_REG,regOther, fileds, put);
                        table.put(put);
                    }else{
                      byte[] totalTransByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("totalTrans"));
                      byte[] totalBizErrByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("totalBizErr"));
                      byte[] totalSysErrByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("totalSysErr"));
                      byte[] avgUsingDstByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("avgUsingDst"));
                      byte[] avgUsingByte = rs.getValue(Bytes.toBytes(FAMILY_REG), Bytes.toBytes("avgUsing"));
                      Integer totalTrans = Bytes.toInt(totalTransByte);
                      Integer totalBizErr = Bytes.toInt(totalBizErrByte);
                      Integer totalSysErr = Bytes.toInt(totalSysErrByte);
                      Integer avgUsing = Bytes.toInt(avgUsingByte);
                      Long  avgUsingDst = Bytes.toLong(avgUsingDstByte);
                      regOther.setTotalTrans(regOther.getTotalTrans()+totalTrans);
                      regOther.setTotalBizErr(regOther.getTotalBizErr()+totalBizErr);
                      regOther.setTotalSysErr(regOther.getTotalSysErr()+totalSysErr);
                      regOther.setAvgUsing((regOther.getAvgUsing()+totalTrans*avgUsing)/regOther.getTotalTrans());
                      regOther.setAvgUsingDst((regOther.getAvgUsingDst()+avgUsingDst*totalTrans)/regOther.getTotalTrans());
                      buildStatisticsPut(FAMILY_REG,regOther, fileds, put);
                      table.put(put);
                    }
                }
                return null;
            }
        });
    }
    
    public <T> Put  buildStatisticsPut(String family,T t,Field[] fields,Put put){
        
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Object fieldValue;
            try {
                fieldValue = field.get(t);
                if(fieldValue!=null){
                    put.add(family.getBytes(), LogUtils.getByteKey(field),
                            LogUtils.getByteValue(field, fieldValue));
                }
            } catch (IllegalArgumentException e1) {
                LOG.error("build statistics put err,cause by:",e1);
            } catch (IllegalAccessException e1) {
                LOG.error("build statistics put err,cause by:",e1);
            }
        }
        return put;
    }
    
}
