/** 
 * Project Name:realTimeLog 
 * File Name:StatisticsHelper.java 
 * Package Name:com.asianinfo.log.utils 
 * Date:2015年9月18日上午10:21:09 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.utils;  

import java.sql.Timestamp;

import com.ailk.eaap.o2p.common.cache.KeyParseUtil;
import com.asiainfo.integration.o2p.log.common.bo.RegStatRecent;
import com.asiainfo.integration.o2p.log.common.bo.RegStatSec;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntSec;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.ailk.eaap.op2.bo.ContractInteraction;

/** 
 * ClassName:StatisticsHelper <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月18日 上午10:21:09 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public final class StatisticsHelper {

    public static final String SECOND_TIME_TYPE="0";
    public static final String MINUTE_TIME_TYPE="1";
    public static final String HOUR_TIME_TYPE="2";
    public static final String DAY_TIME_TYPE="3";
    
    public static final String USE_SEC_PRIFIX="US";
    public static final String USE_OTHER_PRIFIX="UO";
    public static final String REG_SEC_PRIFIX="RS";
    public static final String REG_OTHER_PRIFIX="RO";
    
    public static UseStatCntSec generateUseStatCntSec(ContractInteraction contractInteraction, String timeType,int expNum,int ctgNum) {
        Timestamp centerRecReqTime = contractInteraction.getCenterRecReqTime();
        String measureTime = 
                LogUtils.getStatisticsDateTypeValue(centerRecReqTime, timeType);
        String bizCode = contractInteraction.getBizFuncCode();
        String intfCode = contractInteraction.getBizIntfCode();
        String contractVersion = contractInteraction.getContractVersion();
        String sysCode = contractInteraction.getSrcSysCode();
        Timestamp centerFwd2DstTime = contractInteraction.getCenterFwd2DstTime();
        Timestamp centerRecDstTime = contractInteraction.getCenterRecDstTime();
        Timestamp centerFwd2SrcTime = contractInteraction.getCenterFwd2SrcTime();
        long usingDst = centerRecDstTime.getTime() - centerFwd2DstTime.getTime();
        long usingInner = centerFwd2DstTime.getTime() - centerRecReqTime.getTime()
                + centerFwd2SrcTime.getTime() - centerRecDstTime.getTime();
        int qos = (int) (usingDst + usingInner);
        UseStatCntSec useStatCntSec = new UseStatCntSec();
        useStatCntSec.setDateTranId(measureTime);
        useStatCntSec.setBizCode(bizCode);
        useStatCntSec.setBizIntfCode(intfCode);
        useStatCntSec.setContractVersion(contractVersion);
        useStatCntSec.setSysCode(sysCode);
        useStatCntSec.setTotalTrans(1);
        useStatCntSec.setTotalBizErr(expNum);
        useStatCntSec.setTotalSysErr(ctgNum);
        useStatCntSec.setTotalNoGrant(0);
        useStatCntSec.setQos(qos);
        useStatCntSec.setDateType(timeType);
        useStatCntSec.setCountTime(LogUtils.ifAndGetUTCTimestamp(new Timestamp(System.currentTimeMillis()), Properties.getInstance().getIfFormatUTC()));
        useStatCntSec.setTableId(LogUtils.getTableSuffix(new Timestamp(System.currentTimeMillis())));
        useStatCntSec.setDataSourceKey(getStatisticsObjKey4Use(contractInteraction,USE_SEC_PRIFIX,useStatCntSec.getDateTranId()));
        useStatCntSec.setTenantId(contractInteraction.getTenantId());
        return useStatCntSec;
    }
    public static UseStatCntRecent generateUseStatCntRecent(ContractInteraction contractInteraction,String dateType,int expNum,int ctgNum) {
        String bizCode = contractInteraction.getBizFuncCode();
        String bizIntfCode = contractInteraction.getBizIntfCode();
        String contractVersion = contractInteraction.getContractVersion();
        String sysCode = contractInteraction.getSrcSysCode();
        Timestamp centerFwd2DstTime = contractInteraction.getCenterFwd2DstTime();
        Timestamp centerRecDstTime = contractInteraction.getCenterRecDstTime();
        Timestamp centerRecReqTime = contractInteraction.getCenterRecReqTime();
        Timestamp centerFwd2SrcTime = contractInteraction.getCenterFwd2SrcTime();
        long usingDst = centerRecDstTime.getTime() - centerFwd2DstTime.getTime();
        long usingInner = centerFwd2DstTime.getTime() - centerRecReqTime.getTime()
                + centerFwd2SrcTime.getTime() - centerRecDstTime.getTime();
        int qos = (int) (usingDst + usingInner);
        UseStatCntRecent useStatCntRecent = new UseStatCntRecent();
        useStatCntRecent.setBizCode(bizCode);
        useStatCntRecent.setBizIntfCode(bizIntfCode);
        useStatCntRecent.setContractVersion(contractVersion);
        useStatCntRecent.setSysCode(sysCode);
        useStatCntRecent.setTotalTrans(1);
        useStatCntRecent.setTotalBizErr(expNum);
        useStatCntRecent.setTotalSysErr(ctgNum);
        useStatCntRecent.setTotalNoGrant(0);
        useStatCntRecent.setQos(qos);
        useStatCntRecent.setDateType(dateType);
        String measureTime = 
                LogUtils.getStatisticsDateTypeValue(centerRecReqTime, dateType);
        useStatCntRecent.setDateTranId(measureTime);
        useStatCntRecent.setCountTime(LogUtils.ifAndGetUTCTimestamp(new Timestamp(System.currentTimeMillis()), Properties.getInstance().getIfFormatUTC()));
        useStatCntRecent.setDataSourceKey(getStatisticsObjKey4Use(contractInteraction,USE_OTHER_PRIFIX,useStatCntRecent.getDateTranId()));
        useStatCntRecent.setTenantId(contractInteraction.getTenantId());
        return useStatCntRecent;
    }
    
    public static RegStatSec generateRegStatSec(ContractInteraction contractInteraction, String timeType,int expNum,int ctgNum) {
        String bizCode = contractInteraction.getBizFuncCode();
        String intfCode = contractInteraction.getBizIntfCode();
        String regSerVersion = contractInteraction.getRegSerVersion();
        String dstCode = contractInteraction.getDstSysCode();
        Timestamp centerFwd2DstTime = contractInteraction.getCenterFwd2DstTime();
        Timestamp centerRecDstTime = contractInteraction.getCenterRecDstTime();
        Timestamp centerRecReqTime = contractInteraction.getCenterRecReqTime();
        Timestamp centerFwd2SrcTime = contractInteraction.getCenterFwd2SrcTime();
        long usingDst = centerRecDstTime.getTime() - centerFwd2DstTime.getTime();
        long usingInner = centerFwd2DstTime.getTime() - centerRecReqTime.getTime()
                + centerFwd2SrcTime.getTime() - centerRecDstTime.getTime();
        RegStatSec regStatSec = new RegStatSec();
        String measureTime = 
                LogUtils.getStatisticsDateTypeValue(centerRecReqTime, timeType);
        regStatSec.setDateTranId(measureTime);
        regStatSec.setBizCode(bizCode);
        regStatSec.setIntfCode(intfCode);
        regStatSec.setRegSerVersion(regSerVersion);
        regStatSec.setDstCode(dstCode);
        regStatSec.setTotalTrans(1);
        regStatSec.setTotalBizErr(expNum);
        regStatSec.setTotalSysErr(ctgNum);
        regStatSec.setQos(usingDst);
        regStatSec.setAvgUsing((int) usingInner);
        regStatSec.setDateType(timeType);
        regStatSec.setDataSourceKey(getStatisticsObjKey4Reg(contractInteraction,REG_SEC_PRIFIX,regStatSec.getDateTranId()));
        regStatSec.setCountTime(LogUtils.ifAndGetUTCTimestamp(new Timestamp(System.currentTimeMillis()), Properties.getInstance().getIfFormatUTC()));
        regStatSec.setTableId(LogUtils.getTableSuffix(new Timestamp(System.currentTimeMillis())));
        regStatSec.setTenantId(contractInteraction.getTenantId());
        return regStatSec;
    }
    
    public static RegStatRecent generateRegStatRecent(ContractInteraction contractInteraction,String timeType,int expNum,int ctgNum) {
        String bizCode = contractInteraction.getBizFuncCode();
        String intfCode = contractInteraction.getBizIntfCode();
        String regSerVersion = contractInteraction.getRegSerVersion();
        String dstCode = contractInteraction.getDstSysCode();
        Timestamp centerFwd2DstTime = contractInteraction.getCenterFwd2DstTime();
        Timestamp centerRecDstTime = contractInteraction.getCenterRecDstTime();
        Timestamp centerRecReqTime = contractInteraction.getCenterRecReqTime();
        Timestamp centerFwd2SrcTime = contractInteraction.getCenterFwd2SrcTime();
        long usingDst = centerRecDstTime.getTime() - centerFwd2DstTime.getTime();
        long usingInner = centerFwd2DstTime.getTime() - centerRecReqTime.getTime()
                + centerFwd2SrcTime.getTime() - centerRecDstTime.getTime();
        RegStatRecent regStatRecent = new RegStatRecent();
        regStatRecent.setBizCode(bizCode);
        regStatRecent.setIntfCode(intfCode);
        regStatRecent.setRegSerVersion(regSerVersion);
        regStatRecent.setDstCode(dstCode);
        regStatRecent.setTotalTrans(1);
        regStatRecent.setTotalBizErr(expNum);
        regStatRecent.setTotalSysErr(ctgNum);
        regStatRecent.setAvgUsingDst(usingDst);
        regStatRecent.setAvgUsing((int) usingInner);
        String measureTime = 
                LogUtils.getStatisticsDateTypeValue(centerRecReqTime, timeType);
        regStatRecent.setDateType(timeType);
        regStatRecent.setDateTranId(measureTime);
        regStatRecent.setCountTime(LogUtils.ifAndGetUTCTimestamp(new Timestamp(System.currentTimeMillis()), Properties.getInstance().getIfFormatUTC()));
        regStatRecent.setDataSourceKey(getStatisticsObjKey4Reg(contractInteraction,REG_OTHER_PRIFIX,regStatRecent.getDateTranId()));
        regStatRecent.setTenantId(contractInteraction.getTenantId());
        return regStatRecent;
    }

    public static String getStatisticsObjKey4Use(ContractInteraction contractInteraction,String classType,String dateTranId){
        StringBuilder dateTrandIdSb = new StringBuilder();
        dateTrandIdSb.append(classType);
        dateTrandIdSb.append("_");
        dateTrandIdSb.append(contractInteraction.getTenantId());
        dateTrandIdSb.append(contractInteraction.getSrcSysCode()==null?"unknown":contractInteraction.getSrcSysCode());
        dateTrandIdSb.append(contractInteraction.getBizFuncCode()==null?"unknown":contractInteraction.getBizFuncCode());
        dateTrandIdSb.append(contractInteraction.getBizIntfCode()==null?"unknown":contractInteraction.getBizIntfCode());
        dateTrandIdSb.append(contractInteraction.getContractVersion()==null?"unknown":contractInteraction.getContractVersion());
        dateTrandIdSb.append("_");
        dateTrandIdSb.append(dateTranId);
        return (String) KeyParseUtil.parse(dateTrandIdSb.toString());
       
    }
    public static String getStatisticsObjKey4Reg(ContractInteraction contractInteraction,String classType,String dateTranId){
        StringBuilder dateTrandIdSb = new StringBuilder();
        dateTrandIdSb.append(classType); 
        dateTrandIdSb.append("_");
        dateTrandIdSb.append(contractInteraction.getTenantId());
        dateTrandIdSb.append(contractInteraction.getDstOrgCode() == null?"unknown":contractInteraction.getDstOrgCode());
        dateTrandIdSb.append(contractInteraction.getBizIntfCode() == null?"unknown":contractInteraction.getBizIntfCode());
        dateTrandIdSb.append(contractInteraction.getBizFuncCode() == null?"unknown":contractInteraction.getBizFuncCode());
        dateTrandIdSb.append(contractInteraction.getRegSerVersion() == null?"unknown":contractInteraction.getRegSerVersion());
        dateTrandIdSb.append("_");
        dateTrandIdSb.append(dateTranId);
        return (String) KeyParseUtil.parse(dateTrandIdSb.toString());
    }
    
    public static void validCINotNullColunm(ContractInteraction contractInteraction){
        contractInteraction.setSrcSysCode(isEmptyString(contractInteraction.getSrcSysCode())?"unknown":contractInteraction.getSrcSysCode());
        contractInteraction.setBizFuncCode(isEmptyString(contractInteraction.getBizFuncCode())?"unknown":contractInteraction.getBizFuncCode());
        contractInteraction.setBizIntfCode(isEmptyString(contractInteraction.getBizIntfCode())?"unknown":contractInteraction.getBizIntfCode());
        contractInteraction.setContractVersion(isEmptyString(contractInteraction.getContractVersion())?"unknown":contractInteraction.getContractVersion());
        contractInteraction.setDstOrgCode(isEmptyString(contractInteraction.getDstOrgCode())?"unknown":contractInteraction.getDstOrgCode());
        contractInteraction.setRegSerVersion(isEmptyString(contractInteraction.getRegSerVersion())?"unknown":contractInteraction.getRegSerVersion());
    }
    
    public static boolean isEmptyString(String str){
        boolean result = false;
        if("".equals(str) || str == null){
            result = true;
        }
        return result;
    }
    
   /* public static void main(String[] args){
        
        
        System.out.println(KeyParseUtil.parse("UO_unknownunknownunknownunknownunknownunknown"
                + "_unknownunknownunknownunknownunknownunknown_unknownunknownunknownunknownunknownunknown"
                +"unknownunknownunknownunknownunknownunknown"
                + "_unknownunknownunknownunknownunknownunknown_unknownunknownunknownunknownunknownunknown"+"2015020202"));
        
    }*/
    
    private StatisticsHelper(){
    }
    
    
    
}
