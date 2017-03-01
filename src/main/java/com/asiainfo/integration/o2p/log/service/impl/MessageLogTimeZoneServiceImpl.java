package com.asiainfo.integration.o2p.log.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.asiainfo.integration.o2p.log.service.IMessageLogTimeZoneService;
import com.asiainfo.integration.o2p.log.utils.TimeZoneUtil;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.CtgLogs;
import com.ailk.eaap.op2.bo.EndpointInteraction;
import com.ailk.eaap.op2.bo.ExceptionLogs;
import com.ailk.eaap.op2.bo.LogMessageObject;
import com.ailk.eaap.op2.bo.OriLogClob;

public class MessageLogTimeZoneServiceImpl implements
		IMessageLogTimeZoneService {

	@Override
	public void convertLogTimeZone(LogMessageObject logMessageObject,
			String timeZone) {
		converContractInteractionObjectList(logMessageObject.getContractInteractionList(), timeZone);
		converEndpointInteractionObjectList(logMessageObject.getEndpointInteractionList(), timeZone);
		converExceptionLogsObjectList(logMessageObject.getExceptionLogsList(), timeZone);
		converCtgLogsObjectList(logMessageObject.getCtgLogsList(), timeZone);
	}

	// 遍历contractInteractionList将每个对象的时间转为UTC时间
	private void converContractInteractionObjectList(List<ContractInteraction> contractInteractionList, String timeZone) {
		if(contractInteractionList == null || contractInteractionList.isEmpty()) {
			return;
		}
		for (int i = 0; i < contractInteractionList.size(); i++) {
			ContractInteraction contractInteraction = (ContractInteraction) contractInteractionList
					.get(i);
			Date centerFwd2DstTime, centerFwd2SrcTime, centerRecDstTime, centerRecReqTime;
			if (contractInteraction.getCenterFwd2DstTime() != null) {
				centerFwd2DstTime = TimeZoneUtil.timeZoneConver(new Date(
						contractInteraction.getCenterFwd2DstTime().getTime()),timeZone);
			} else {
				centerFwd2DstTime = TimeZoneUtil.timeZoneConver(timeZone);
			}
			contractInteraction.setCenterFwd2DstTime(new Timestamp(
					centerFwd2DstTime.getTime()));

			if (contractInteraction.getCenterFwd2SrcTime() != null) {
				centerFwd2SrcTime = TimeZoneUtil.timeZoneConver(new Date(
						contractInteraction.getCenterFwd2SrcTime().getTime()),timeZone);
			} else {
				centerFwd2SrcTime = TimeZoneUtil.timeZoneConver(timeZone);
			}
			contractInteraction.setCenterFwd2SrcTime(new Timestamp(
					centerFwd2SrcTime.getTime()));

			if (contractInteraction.getCenterRecDstTime() != null) {
				centerRecDstTime = TimeZoneUtil.timeZoneConver(new Date(
						contractInteraction.getCenterRecDstTime().getTime()),timeZone);
			} else {
				centerRecDstTime = TimeZoneUtil.timeZoneConver(timeZone);
			}
			contractInteraction.setCenterRecDstTime(new Timestamp(
					centerRecDstTime.getTime()));

			if (contractInteraction.getCenterRecReqTime() != null) {
				centerRecReqTime = TimeZoneUtil.timeZoneConver(new Date(
						contractInteraction.getCenterRecReqTime().getTime()),timeZone);
			} else {
				centerRecReqTime = TimeZoneUtil.timeZoneConver(timeZone);
			}
			contractInteraction.setCenterRecReqTime(new Timestamp(
					centerRecReqTime.getTime()));
			if (contractInteraction.getSrcReqTime() != null) {
				contractInteraction.setSrcReqTime(TimeZoneUtil.timeZoneConver(contractInteraction.getSrcReqTime(),timeZone));
			} else {
				contractInteraction
						.setSrcReqTime(TimeZoneUtil.timeZoneConver(timeZone));
			}

			if (contractInteraction.getDstRecTime() != null) {
				contractInteraction.setDstRecTime(TimeZoneUtil.timeZoneConver(contractInteraction.getDstRecTime(),timeZone));
			} else {
				contractInteraction
						.setDstRecTime(TimeZoneUtil.timeZoneConver(timeZone));
			}

			if (contractInteraction.getDstReplyTime() != null) {
				contractInteraction
						.setDstReplyTime(TimeZoneUtil.timeZoneConver(contractInteraction
										.getDstReplyTime(), timeZone));
			} else {
				contractInteraction.setDstReplyTime(TimeZoneUtil.timeZoneConver(timeZone));
			}

			if (contractInteraction.getSrcConfirmTime() != null) {
				contractInteraction.setSrcConfirmTime(TimeZoneUtil.timeZoneConver(contractInteraction
								.getSrcConfirmTime(),timeZone));
			} else {
				contractInteraction.setSrcConfirmTime(TimeZoneUtil.timeZoneConver(timeZone));
			}

			if (contractInteraction.getSrcResponseTime() != null) {
				contractInteraction.setSrcResponseTime(TimeZoneUtil.timeZoneConver(contractInteraction
								.getSrcResponseTime(),timeZone));
			} else {
				contractInteraction.setSrcResponseTime(TimeZoneUtil.timeZoneConver(timeZone));
			}

			if (contractInteraction.getCreateTime() != null) {
				contractInteraction.setCreateTime(TimeZoneUtil.timeZoneConver(contractInteraction.getCreateTime(),timeZone));
			} else {
				contractInteraction
						.setCreateTime(TimeZoneUtil.timeZoneConver(timeZone));
			}
		}
	}

	// 遍历endpointInteractionList将每个对象的时间转为UTC时间
	public void converEndpointInteractionObjectList(List<EndpointInteraction> endpointInteractionList, String timeZone) {
		if(endpointInteractionList == null || endpointInteractionList.isEmpty()) {
			return;
		}
		for (int i = 0; i < endpointInteractionList.size(); i++) {
			EndpointInteraction endpointInteraction = (EndpointInteraction) endpointInteractionList
					.get(i);
			Date centerFwd2DstTime, dstReplyTime;
			if (endpointInteraction.getCenterFwd2DstTime() != null) {
				centerFwd2DstTime = TimeZoneUtil.timeZoneConver(new Date(
						endpointInteraction.getCenterFwd2DstTime().getTime()),timeZone);
			} else {
				centerFwd2DstTime = TimeZoneUtil.timeZoneConver(timeZone);
			}
			endpointInteraction.setCenterFwd2DstTime(new Timestamp(
					centerFwd2DstTime.getTime()));

			if (endpointInteraction.getDstReplyTime() != null) {
				dstReplyTime = TimeZoneUtil.timeZoneConver(new Date(
						endpointInteraction.getDstReplyTime().getTime()),timeZone);
			} else {
				dstReplyTime = TimeZoneUtil.timeZoneConver(timeZone);
			}
			endpointInteraction.setDstReplyTime(new Timestamp(dstReplyTime
					.getTime()));

			if (endpointInteraction.getCreateDate() != null) {
				endpointInteraction.setCreateDate(TimeZoneUtil.timeZoneConver(endpointInteraction.getCreateDate(),timeZone));
			} else {
				endpointInteraction
						.setCreateDate(TimeZoneUtil.timeZoneConver(timeZone));
			}
		}
	}

	// 遍历oriLogClobList将每个对象的时间转为UTC时间
	public void converOriLogClobObjectList(List<OriLogClob> oriLogClobList, String timeZone) {
		for (int i = 0; i < oriLogClobList.size(); i++) {
			OriLogClob oriLogClob = (OriLogClob) oriLogClobList.get(i);
			Date createTime;
			if (oriLogClob.getCreateTime() != null) {
				createTime = TimeZoneUtil.timeZoneConver(new Date(oriLogClob
						.getCreateTime().getTime()),timeZone);
			} else {
				createTime = TimeZoneUtil.timeZoneConver(timeZone);
			}
			oriLogClob.setCreateTime(new Timestamp(createTime.getTime()));
		}
	}

	// 遍历exceptionLogsList将每个对象的时间转为UTC时间
	public void converExceptionLogsObjectList(List<ExceptionLogs> exceptionLogsList, String timeZone) {
		for (int i = 0; i < exceptionLogsList.size(); i++) {
			ExceptionLogs exceptionLog = (ExceptionLogs) exceptionLogsList
					.get(i);
			if (exceptionLog.getCreateTime() != null) {
				exceptionLog.setCreateTime(TimeZoneUtil.timeZoneConver(exceptionLog.getCreateTime(),timeZone));
			} else {
				exceptionLog.setCreateTime(TimeZoneUtil.timeZoneConver(timeZone));
			}
		}
	}

	// 遍历ctgLogsList将每个对象的时间转为UTC时间
	public void converCtgLogsObjectList(List<CtgLogs> ctgLogsList, String timeZone) {
		for (int i = 0; i < ctgLogsList.size(); i++) {
			CtgLogs ctgLogs = (CtgLogs) ctgLogsList.get(i);
			Date createTime;
			if (ctgLogs.getCreateDate() != null) {
				createTime = TimeZoneUtil.timeZoneConver(new Date(ctgLogs
						.getCreateDate().getTime()),timeZone);
			} else {
				createTime = TimeZoneUtil.timeZoneConver(timeZone);
			}
			ctgLogs.setCreateDate(new Timestamp(createTime.getTime()));
		}
	}

}
