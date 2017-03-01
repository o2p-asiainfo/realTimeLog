package com.asiainfo.integration.o2p.log.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.ExceptionDealInfo;

public interface ExceptionTaskDao {

	public void saveExceptionDealInfo(List<ExceptionDealInfo> edf);
	
}
