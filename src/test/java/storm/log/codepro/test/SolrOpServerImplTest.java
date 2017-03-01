package storm.log.codepro.test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import org.easymock.EasyMock;
import org.junit.*;

import static org.junit.Assert.*;

import com.asiainfo.integration.o2p.log.common.bo.SolrBean;
import com.asiainfo.integration.o2p.log.common.solr.SolrServer;
import com.asiainfo.integration.o2p.log.solr.dao.SolrDao;
import com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.EndpointInteraction;

/**
 * The class <code>SolrOpServerImplTest</code> contains tests for the class <code>{@link SolrOpServerImpl}</code>.
 *
 * @generatedBy CodePro at 15-12-18 下午2:53
 * @author daimq
 * @version $Revision: 1.0 $
 */
public class SolrOpServerImplTest {
    /**
     * Run the SolrOpServerImpl() constructor test.
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    
    SolrServer solrServer = EasyMock.createMock(SolrServer.class);
    SolrDao solrDao = EasyMock.createMock(SolrDao.class);
    @Test
    public void testSolrOpServerImpl_1()
        throws Exception {
        SolrOpServerImpl result = new SolrOpServerImpl();
        assertNotNull(result);
        // add additional test code here
    }

    /**
     * Run the void batchAddIndex(List<SolrBean>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testBatchAddIndex_1()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
       
        List<SolrBean> list = new Vector();
        fixture.getSolrDao().setSolrServer(solrServer);
        fixture.batchAddIndex(list);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.batchAddIndex(SolrDao.java:38)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.batchAddIndex(SolrOpServerImpl.java:110)
    }

    /**
     * Run the void batchAddIndex(List<SolrBean>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testBatchAddIndex_2()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        List<SolrBean> list = new Vector();

        fixture.batchAddIndex(list);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.batchAddIndex(SolrDao.java:38)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.batchAddIndex(SolrOpServerImpl.java:110)
    }

    /**
     * Run the void batchAddIndexToSolr(List<ContractInteraction>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testBatchAddIndexToSolr_1()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        List<ContractInteraction> cis = new Vector();

        fixture.batchAddIndexToSolr(cis);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.batchAddIndex(SolrDao.java:38)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.batchAddIndexToSolr(SolrOpServerImpl.java:137)
    }

    /**
     * Run the void batchAddIndexToSolr(List<ContractInteraction>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testBatchAddIndexToSolr_2()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        List<ContractInteraction> cis = new Vector();

        fixture.batchAddIndexToSolr(cis);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.batchAddIndex(SolrDao.java:38)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.batchAddIndexToSolr(SolrOpServerImpl.java:137)
    }

    /**
     * Run the void batchAddIndexToSolr(List<ContractInteraction>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testBatchAddIndexToSolr_3()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        List<ContractInteraction> cis = new Vector();

        fixture.batchAddIndexToSolr(cis);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.batchAddIndex(SolrDao.java:38)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.batchAddIndexToSolr(SolrOpServerImpl.java:137)
    }

    /**
     * Run the void batchAddIndexToSolr(List<ContractInteraction>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testBatchAddIndexToSolr_4()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        List<ContractInteraction> cis = new Vector();

        fixture.batchAddIndexToSolr(cis);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.batchAddIndex(SolrDao.java:38)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.batchAddIndexToSolr(SolrOpServerImpl.java:137)
    }

    /**
     * Run the void batchAddIndexToSolr(List<ContractInteraction>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testBatchAddIndexToSolr_5()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        List<ContractInteraction> cis = new Vector();

        fixture.batchAddIndexToSolr(cis);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.batchAddIndex(SolrDao.java:38)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.batchAddIndexToSolr(SolrOpServerImpl.java:137)
    }

    /**
     * Run the void batchAddIndexToSolr(List<ContractInteraction>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testBatchAddIndexToSolr_6()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        List<ContractInteraction> cis = new Vector();

        fixture.batchAddIndexToSolr(cis);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.batchAddIndex(SolrDao.java:38)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.batchAddIndexToSolr(SolrOpServerImpl.java:137)
    }

    /**
     * Run the void deleteAllIndex() method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testDeleteAllIndex_1()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);

        fixture.deleteAllIndex();

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.deleteAllIndex(SolrDao.java:47)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.deleteAllIndex(SolrOpServerImpl.java:98)
    }

    /**
     * Run the void deleteAllIndex() method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testDeleteAllIndex_2()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);

        fixture.deleteAllIndex();

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.deleteAllIndex(SolrDao.java:47)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.deleteAllIndex(SolrOpServerImpl.java:98)
    }

    /**
     * Run the SolrDao getSolrDao() method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testGetSolrDao_1()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);

        SolrDao result = fixture.getSolrDao();

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getSolrServer());
    }

    /**
     * Run the void indexOnSolr(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_1()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        EndpointInteraction obj = new EndpointInteraction();
        obj.setContractInteractionId("");

        fixture.indexOnSolr(obj);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:55)
    }

    /**
     * Run the void indexOnSolr(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_2()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        Object obj = new Object();

        fixture.indexOnSolr(obj);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:55)
    }

    /**
     * Run the void indexOnSolr(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_3()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        ContractInteraction obj = new ContractInteraction();
        obj.setResponseCode("");
        obj.setDstSysCode("");
        obj.setContractInteractionId("");
        obj.setSrcReqTime(new Timestamp(1L));
        obj.setSrcSysCode("");
        obj.setSrcTranId("");
        obj.setContractVersion("");

        fixture.indexOnSolr(obj);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:55)
    }

    /**
     * Run the void indexOnSolr(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_4()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        EndpointInteraction obj = new EndpointInteraction();
        obj.setContractInteractionId("");

        fixture.indexOnSolr(obj);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:55)
    }

    /**
     * Run the void indexOnSolr(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_5()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        Object obj = new Object();

        fixture.indexOnSolr(obj);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:55)
    }

    /**
     * Run the void indexOnSolr(Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_6()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        ContractInteraction obj = new ContractInteraction();
        obj.setResponseCode("");
        obj.setDstSysCode("");
        obj.setContractInteractionId("");
        obj.setSrcReqTime(new Timestamp(1L));
        obj.setSrcSysCode("");
        obj.setSrcTranId("");
        obj.setContractVersion("");

        fixture.indexOnSolr(obj);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:55)
    }

    /**
     * Run the void indexOnSolr(Object,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_7()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        EndpointInteraction obj = new EndpointInteraction();
        obj.setContractInteractionId("");
        String rowKey = "";

        fixture.indexOnSolr(obj, rowKey);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:87)
    }

    /**
     * Run the void indexOnSolr(Object,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_8()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        Object obj = new Object();
        String rowKey = "";

        fixture.indexOnSolr(obj, rowKey);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:87)
    }

    /**
     * Run the void indexOnSolr(Object,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_9()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        ContractInteraction obj = new ContractInteraction();
        obj.setResponseCode("");
        obj.setDstSysCode("");
        obj.setContractInteractionId("");
        obj.setSrcReqTime(new Timestamp(1L));
        obj.setSrcSysCode("");
        obj.setSrcTranId("");
        obj.setContractVersion("");
        String rowKey = "";

        fixture.indexOnSolr(obj, rowKey);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:87)
    }

    /**
     * Run the void indexOnSolr(Object,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_10()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        EndpointInteraction obj = new EndpointInteraction();
        obj.setContractInteractionId("");
        String rowKey = "";

        fixture.indexOnSolr(obj, rowKey);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:87)
    }

    /**
     * Run the void indexOnSolr(Object,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_11()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        Object obj = new Object();
        String rowKey = "";

        fixture.indexOnSolr(obj, rowKey);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:87)
    }

    /**
     * Run the void indexOnSolr(Object,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testIndexOnSolr_12()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        ContractInteraction obj = new ContractInteraction();
        obj.setResponseCode("");
        obj.setDstSysCode("");
        obj.setContractInteractionId("");
        obj.setSrcReqTime(new Timestamp(1L));
        obj.setSrcSysCode("");
        obj.setSrcTranId("");
        obj.setContractVersion("");
        String rowKey = "";

        fixture.indexOnSolr(obj, rowKey);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.solr.dao.SolrDao.addIndex(SolrDao.java:29)
        //       at com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl.indexOnSolr(SolrOpServerImpl.java:87)
    }

    /**
     * Run the void setSolrDao(SolrDao) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Test
    public void testSetSolrDao_1()
        throws Exception {
        SolrOpServerImpl fixture = new SolrOpServerImpl();
        fixture.setSolrDao(solrDao);
        SolrDao solrDao = new SolrDao();

        fixture.setSolrDao(solrDao);

        // add additional test code here
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception
     *         if the initialization fails for some reason
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @Before
    public void setUp()
        throws Exception {
        // add additional set up code here
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception
     *         if the clean-up fails for some reason
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    @After
    public void tearDown()
        throws Exception {
        // Add additional tear down code here
    }

    /**
     * Launch the test.
     *
     * @param args the command line arguments
     *
     * @generatedBy CodePro at 15-12-18 下午2:53
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(SolrOpServerImplTest.class);
    }
}