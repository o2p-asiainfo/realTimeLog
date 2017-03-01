package com.asiainfo.integration.o2p.log.solr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.integration.o2p.log.common.bo.SolrBean;
import com.asiainfo.integration.o2p.log.common.solr.SolrConfig;
import com.asiainfo.integration.o2p.log.solr.dao.SolrDao;
import com.asiainfo.integration.o2p.log.solr.service.SolrOpServer;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.EndpointInteraction;

public class SolrOpServerImpl implements SolrOpServer {
	
	SolrDao solrDao;

	public SolrDao getSolrDao() {
		return solrDao;
	}

	public void setSolrDao(SolrDao solrDao) {
		this.solrDao = solrDao;
	}

	private static final Logger LOG = LoggerFactory.getLogger(SolrOpServerImpl.class);  
	
	public void indexOnSolr(Object obj) {
		SolrBean solr = new SolrBean();

		if(obj instanceof ContractInteraction){
			
			ContractInteraction ci = (ContractInteraction) obj;
//		    solr.rowkey = ci.getRowkeyValue();
		    solr.setContractInteractionId(ci.getContractInteractionId());
			solr.setTable(SolrConfig.table_CI);
			solr.setSrcTranId(ci.getSrcTranId());
			solr.setSrcSysCode(ci.getSrcSysCode());
			solr.setDstSysCode(ci.getDstSysCode());
			solr.setSrcReqTime(ci.getSrcReqTime().getTime());//getSrcReqTime();
			solr.setContractVersion( ci.getContractVersion());
			solr.setResponseCode(ci.getResponseCode());
			//solr.status = ci.get
		}else if(obj instanceof EndpointInteraction){
			
			EndpointInteraction ei = (EndpointInteraction) obj;
			//solr.rowkey = ei.getRowkeyValue();
			solr.setTable(SolrConfig.table_EI);
			solr.setContractInteractionId( ei.getContractInteractionId());
        }
		try {
			solrDao.addIndex(solr);
		} catch (SolrServerException e) {
			LOG.error("addIndex on solr err,cause by:",e);
		}
		
	}
	
	
	@Override
	public void indexOnSolr(Object obj,String rowKey) {
		SolrBean solr = new SolrBean();
		 solr.setRowkey(rowKey);
		if(obj instanceof ContractInteraction){
			
			ContractInteraction ci = (ContractInteraction) obj;
			solr.setTable(SolrConfig.table_CI);
			//solr.rowkey = ci.getRowkeyValue();
			solr.setContractInteractionId( ci.getContractInteractionId());
			solr.setSrcTranId(ci.getSrcTranId());
			solr.setSrcSysCode(ci.getSrcSysCode());
			solr.setDstSysCode(ci.getDstSysCode());
			solr.setSrcReqTime( ci.getSrcReqTime().getTime());
			solr.setContractVersion( ci.getContractVersion());
			solr.setResponseCode( ci.getResponseCode());
			//solr.status = ci.getStatus();
		}else if(obj instanceof EndpointInteraction){
			
			EndpointInteraction ei = (EndpointInteraction) obj;
			solr.setTable(SolrConfig.table_CI);
			solr.setContractInteractionId(ei.getContractInteractionId());
        }
		try {
			solrDao.addIndex(solr);
		} catch (SolrServerException e) {
		    LOG.error("addIndex on solr err,cause by:",e);
		}
		
	}

	@Override
	public void deleteAllIndex() {
		
		try {
			solrDao.deleteAllIndex();
		} catch (SolrServerException e) {
		    LOG.error("delete Index on solr err,cause by:",e);
		}
		
	}


	@Override
	public void batchAddIndex(List<SolrBean> list) {
		
		try {
			solrDao.batchAddIndex(list);
		} catch (SolrServerException e) {
		    LOG.error("batchAddIndex on solr err,cause by:",e);
		}
		
	}

    @Override
    public void batchAddIndexToSolr(List<ContractInteraction> cis) {
        List<SolrBean> solrs = new ArrayList<SolrBean>();
        for(ContractInteraction ci :cis){
            SolrBean solr = new SolrBean();
            solr.setId(ci.getContractInteractionId()+System.currentTimeMillis());
            solr.setContractInteractionId(ci.getContractInteractionId());
            solr.setTable( SolrConfig.table_CI);
            solr.setSrcTranId( ci.getSrcTranId());
            solr.setSrcSysCode(ci.getSrcSysCode());
            solr.setDstSysCode( ci.getDstSysCode());
            solr.setSrcReqTime(ci.getSrcReqTime().getTime());
            solr.setCreateTime( ci.getCreateTime().getTime());
            solr.setTenantId( ci.getTenantId());
            solr.setServiceStyle( ci.getServiceStyle());
            solr.setContractVersion( ci.getContractVersion());
            solr.setResponseCode( ci.getResponseCode());
            solrs.add(solr);
        }
        try {
            this.solrDao.batchAddIndex(solrs);
            if(LOG.isDebugEnabled()){
                LOG.debug("post to solr success,size:"+solrs.size());
            }
        } catch (SolrServerException e) {
            LOG.error("post to solr fail,cause by:",e);
        }
    }

}
