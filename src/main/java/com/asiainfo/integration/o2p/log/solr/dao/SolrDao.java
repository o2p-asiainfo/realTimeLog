package com.asiainfo.integration.o2p.log.solr.dao;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.integration.o2p.log.common.bo.SolrBean;
import com.asiainfo.integration.o2p.log.common.solr.SolrServer;

public class SolrDao {


	public final static Logger LOG = LoggerFactory.getLogger(SolrDao.class);
	public SolrServer getSolrServer() {
		return solrServer;
	}

	public void setSolrServer(SolrServer solrServer) {
		this.solrServer = solrServer;
	}

	SolrServer solrServer;

	public  void addIndex(SolrBean solr) throws SolrServerException {
		try {
			solrServer.getCommonsHttpSolrServer().addBean(solr);
			solrServer.getCommonsHttpSolrServer().commit();
		} catch (IOException e) {
		    LOG.error("add index to solr err,cause by:",e);
		}
	}
	
	public  void batchAddIndex(List<SolrBean> list) throws SolrServerException {
		try {
			solrServer.getCommonsHttpSolrServer().addBeans(list);
			solrServer.getCommonsHttpSolrServer().commit();
		} catch (IOException e) {
		    LOG.error("add index to solr err,cause by:",e);
		}
	}

	public  void deleteAllIndex() throws SolrServerException {
		try {
			solrServer.getCommonsHttpSolrServer().deleteByQuery("*:*");
			solrServer.getCommonsHttpSolrServer().commit();
		} catch (IOException e) {
		    LOG.error("add index to solr err,cause by:",e);
		}

	}

	
	

}
