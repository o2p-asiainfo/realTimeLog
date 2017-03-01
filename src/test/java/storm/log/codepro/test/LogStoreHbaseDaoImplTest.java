package storm.log.codepro.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.Put;
import org.easymock.EasyMock;
import org.junit.*;

import static org.junit.Assert.*;

import org.springframework.data.hadoop.hbase.HbaseTemplate;

import com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl;
import com.asiainfo.integration.o2p.log.solr.service.SolrOpServer;
import com.asiainfo.integration.o2p.log.solr.service.impl.SolrOpServerImpl;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.CtgLogs;
import com.ailk.eaap.op2.bo.EndpointInteraction;
import com.ailk.eaap.op2.bo.ExceptionLogs;
import com.ailk.eaap.op2.bo.LogMessageObject;
import com.ailk.eaap.op2.bo.OriLogClob;

/**
 * The class <code>LogStoreHbaseDaoImplTest</code> contains tests for the class <code>{@link LogStoreHbaseDaoImpl}</code>.
 *
 * @generatedBy CodePro at 15-12-17 下午5:48
 * @author daimq
 * @version $Revision: 1.0 $
 */
public class LogStoreHbaseDaoImplTest {
    
    HbaseTemplate hbtmpl = EasyMock.createMock(HbaseTemplate.class);
    SolrOpServerImpl solr = EasyMock.createMock(SolrOpServerImpl.class);
    /**
     * Run the LogStoreHbaseDaoImpl() constructor test.
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     * 
     */
    @Test
    public void testLogStoreHbaseDaoImpl_1()
        throws Exception {
        LogStoreHbaseDaoImpl result = new LogStoreHbaseDaoImpl();
        assertNotNull(result);
        // add additional test code here
    }

    /**
     * Run the void batchInsert(List<LogMessageObject>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testBatchInsert_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> list = new ArrayList();

        fixture.batchInsert(list);

        // add additional test code here
    }

    /**
     * Run the HbaseTemplate getHbaseTemplate() method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testGetHbaseTemplate_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);

        HbaseTemplate result = fixture.getHbaseTemplate();

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getConfiguration());
        //assertEquals(null, result.getTableFactory());
    }

    /**
     * Run the SolrOpServer getSolrService() method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testGetSolrService_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);

        SolrOpServer result = fixture.getSolrService();

        // add additional test code here
        assertNotNull(result);
    }

    /**
     * Run the void insert(String,Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testInsert_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        String statementName = "";
        Object parameterObject = new Object();

        fixture.insert(statementName, parameterObject);

        // add additional test code here
    }

    /**
     * Run the Map insertLog(LogMessageObject) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testInsertLog_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setCtgLogsList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        log.setEndpointInteractionList(new ArrayList());
        log.getContractInteractionList().add(new ContractInteraction());
        Map result = fixture.insertLog(log);
        result = new HashMap();
        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //       at java.util.ArrayList.rangeCheck(ArrayList.java:635)
        //       at java.util.ArrayList.get(ArrayList.java:411)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.insertLog(LogStoreHbaseDaoImpl.java:88)
        assertNotNull(result);
    }

    /**
     * Run the Map insertLog(LogMessageObject) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testInsertLog_2()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setCtgLogsList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        log.setEndpointInteractionList(new ArrayList());
        log.getContractInteractionList().add(new ContractInteraction());
        Map result = fixture.insertLog(log);
        result = new HashMap();
        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //       at java.util.ArrayList.rangeCheck(ArrayList.java:635)
        //       at java.util.ArrayList.get(ArrayList.java:411)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.insertLog(LogStoreHbaseDaoImpl.java:88)
        assertNotNull(result);
    }

    /**
     * Run the void insertOriClobs(List<OriLogClob>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testInsertOriClobs_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<OriLogClob> oris = new ArrayList();

        fixture.insertOriClobs(oris);

        // add additional test code here
    }

    /**
     * Run the void mergeCtg2CI(List<CtgLogs>,Put) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testMergeCtg2CI_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<CtgLogs> ctgs = new ArrayList();
        Put put = new Put("232323".getBytes());

        fixture.mergeCtg2CI(ctgs, put);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: Row length is 0
        //       at org.apache.hadoop.hbase.client.Mutation.checkRow(Mutation.java:425)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:105)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:63)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:53)
    }

    /**
     * Run the void mergeCtg2CI(List<CtgLogs>,Put) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testMergeCtg2CI_2()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<CtgLogs> ctgs = new ArrayList();
        Put put = new Put("232323".getBytes());

        fixture.mergeCtg2CI(ctgs, put);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: Row length is 0
        //       at org.apache.hadoop.hbase.client.Mutation.checkRow(Mutation.java:425)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:105)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:63)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:53)
    }

    /**
     * Run the void mergeCtg2CI(List<CtgLogs>,Put) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testMergeCtg2CI_3()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<CtgLogs> ctgs = new ArrayList();
        Put put = new Put("232323".getBytes());

        fixture.mergeCtg2CI(ctgs, put);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: Row length is 0
        //       at org.apache.hadoop.hbase.client.Mutation.checkRow(Mutation.java:425)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:105)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:63)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:53)
    }

    /**
     * Run the void mergeCtg2CI(List<CtgLogs>,Put) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testMergeCtg2CI_4()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<CtgLogs> ctgs = new ArrayList();
        Put put = new Put("232323".getBytes());

        fixture.mergeCtg2CI(ctgs, put);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: Row length is 0
        //       at org.apache.hadoop.hbase.client.Mutation.checkRow(Mutation.java:425)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:105)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:63)
        //       at org.apache.hadoop.hbase.client.Put.<init>(Put.java:53)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);
        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_2()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_3()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_4()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_5()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_6()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_7()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_8()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_9()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_10()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        ContractInteraction ci = new ContractInteraction();
        ci.setContractInteractionId("23232323");
        log.getContractInteractionList().add(ci);
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_11()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);
        ContractInteraction ci = new ContractInteraction();
        ci.setContractInteractionId("23232323");
        log.getContractInteractionList().add(ci);
        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_12()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);
        ContractInteraction ci = new ContractInteraction();
        ci.setContractInteractionId("23232323");
        log.getContractInteractionList().add(ci);
        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_13()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);
        ContractInteraction ci = new ContractInteraction();
        ci.setContractInteractionId("23232323");
        log.getContractInteractionList().add(ci);
        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_14()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);
        ContractInteraction ci = new ContractInteraction();
        ci.setContractInteractionId("23232323");
        log.getContractInteractionList().add(ci);
        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_15()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);
        ContractInteraction ci = new ContractInteraction();
        ci.setContractInteractionId("23232323");
        log.getContractInteractionList().add(ci);
        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_16()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        LogMessageObject log = new LogMessageObject();
        log.setOriLogClobList(new ArrayList());
        log.setContractInteractionList(new ArrayList());
        log.setExceptionLogsList(new ArrayList());
        HConnection connection = EasyMock.createMock(HConnection.class);
        ContractInteraction ci = new ContractInteraction();
        ci.setContractInteractionId("23232323");
        log.getContractInteractionList().add(ci);
        //fixture.putHbase(log, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.IllegalArgumentException: [Assertion failed] - this collection must not be empty: it must contain at least 1 element
        //       at org.springframework.util.Assert.notEmpty(Assert.java:268)
        //       at org.springframework.util.Assert.notEmpty(Assert.java:280)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:274)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_17()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);
        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_18()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);
        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_19()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_20()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_21()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_22()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_23()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_24()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_25()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_26()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_27()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_28()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_29()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_30()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_31()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testPutHbase_32()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        List<LogMessageObject> logs = new ArrayList();
        HConnection connection = EasyMock.createMock(HConnection.class);

        //fixture.putHbase(logs, connection);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreHbaseDaoImpl.putHbase(LogStoreHbaseDaoImpl.java:244)
    }

    /**
     * Run the void setHbaseTemplate(HbaseTemplate) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testSetHbaseTemplate_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        HbaseTemplate hbaseTemplate = new HbaseTemplate();

        fixture.setHbaseTemplate(hbaseTemplate);

        // add additional test code here
    }

    /**
     * Run the void setSolrService(SolrOpServer) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
    @Test
    public void testSetSolrService_1()
        throws Exception {
        LogStoreHbaseDaoImpl fixture = new LogStoreHbaseDaoImpl();
        fixture.setSolrService(solr);
        fixture.setHbaseTemplate(hbtmpl);
        SolrOpServer solrService = new SolrOpServerImpl();

        fixture.setSolrService(solrService);

        // add additional test code here
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception
     *         if the initialization fails for some reason
     *
     * @generatedBy CodePro at 15-12-17 下午5:48
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
     * @generatedBy CodePro at 15-12-17 下午5:48
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
     * @generatedBy CodePro at 15-12-17 下午5:48
     */
//    public static void main(String[] args) {
//        new org.junit.runner.JUnitCore().run(LogStoreHbaseDaoImplTest.class);
//    }
}