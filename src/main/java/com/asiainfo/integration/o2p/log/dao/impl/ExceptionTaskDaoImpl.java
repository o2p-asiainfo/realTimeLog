package com.asiainfo.integration.o2p.log.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ailk.eaap.op2.bo.ExceptionDealInfo;
import com.asiainfo.integration.o2p.log.dao.ExceptionTaskDao;

public class ExceptionTaskDaoImpl  implements ExceptionTaskDao{

	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void saveExceptionDealInfo(List<ExceptionDealInfo> edf) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("exceptionDealInfo.addExceptionDealInfo", edf);
		
	}

}
