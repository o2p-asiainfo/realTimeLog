package com.asiainfo.integration.o2p.log.solr.service;

import java.util.List;

import com.asiainfo.integration.o2p.log.common.bo.SolrBean;
import com.ailk.eaap.op2.bo.ContractInteraction;


public interface SolrOpServer {
	
	public void indexOnSolr(Object obj);
	
	public void indexOnSolr(Object obj,String rowKey);
	
	public void deleteAllIndex();
	

	public void batchAddIndex(List<SolrBean> list);
	
	public void batchAddIndexToSolr(List<ContractInteraction> cis);

}
