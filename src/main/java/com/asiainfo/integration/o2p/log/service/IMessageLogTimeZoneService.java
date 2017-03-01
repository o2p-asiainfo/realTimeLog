package com.asiainfo.integration.o2p.log.service;

import com.ailk.eaap.op2.bo.LogMessageObject;

public interface IMessageLogTimeZoneService {
	
	/**
	 * 把日志对象中时间转换为具体时区时间
	 * @param logMessageObject
	 */
	void convertLogTimeZone(LogMessageObject logMessageObject, String timeZone);
}
