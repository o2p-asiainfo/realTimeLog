package storm.log.codepro.test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.hadoop.hbase.client.HConnection;
import org.easymock.EasyMock;
import org.junit.*;
import org.mybatis.spring.SqlSessionTemplate;

import static org.junit.Assert.*;

import com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.CtgLogs;
import com.ailk.eaap.op2.bo.EndpointInteraction;
import com.ailk.eaap.op2.bo.ExceptionLogs;
import com.ailk.eaap.op2.bo.LogMessageObject;
import com.ailk.eaap.op2.bo.OriLogClob;

/**
 * The class <code>LogStoreDaoImplTest</code> contains tests for the class <code>{@link LogStoreDaoImpl}</code>.
 *
 * @generatedBy CodePro at 15-12-17 下午5:49
 * @author daimq
 * @version $Revision: 1.0 $
 */
public class LogStoreDaoImplTest {
    /**
     * Run the LogStoreDaoImpl() constructor test.
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     * 
     */
    
    SqlSessionTemplate session = EasyMock.createMock(SqlSessionTemplate.class);
    @Test
    public void testLogStoreDaoImpl_1()
        throws Exception {
        LogStoreDaoImpl result = new LogStoreDaoImpl();
        assertNotNull(result);
        // add additional test code here
    }

    /**
     * Run the void batchInsert(List<LogMessageObject>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsert_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        fixture.setSqlSessionTemplate(session);
        List<LogMessageObject> list = new Vector();

        fixture.batchInsert(list);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insert(LogStoreDaoImpl.java:66)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.batchInsert(LogStoreDaoImpl.java:121)
    }

    /**
     * Run the void batchInsert(List<LogMessageObject>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsert_2()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        fixture.setSqlSessionTemplate(session);
        List<LogMessageObject> list = new Vector();

        fixture.batchInsert(list);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insert(LogStoreDaoImpl.java:66)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.batchInsert(LogStoreDaoImpl.java:121)
    }

    /**
     * Run the void batchInsert(List<LogMessageObject>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsert_3()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        fixture.setSqlSessionTemplate(session);
        List<LogMessageObject> list = new Vector();

        fixture.batchInsert(list);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insert(LogStoreDaoImpl.java:66)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.batchInsert(LogStoreDaoImpl.java:121)
    }

    /**
     * Run the void batchInsert(List<LogMessageObject>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsert_4()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        fixture.setSqlSessionTemplate(session);
        List<LogMessageObject> list = new Vector();

        fixture.batchInsert(list);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insert(LogStoreDaoImpl.java:66)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.batchInsert(LogStoreDaoImpl.java:121)
    }

    /**
     * Run the void batchInsertEI(List<EndpointInteraction>,Timestamp,Map<Long,String>,List<OriLogClob>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertEI_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<EndpointInteraction> eis = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        List<OriLogClob> oriList = new Vector();
        fixture.setSqlSessionTemplate(session);
        fixture.batchInsertEI(eis, currTimestamp, endpointInteractionIdMap, oriList);

        // add additional test code here
    }

    /**
     * Run the void batchInsertEI(List<EndpointInteraction>,Timestamp,Map<Long,String>,List<OriLogClob>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertEI_2()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<EndpointInteraction> eis = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        List<OriLogClob> oriList = new Vector();
        fixture.setSqlSessionTemplate(session);
        fixture.batchInsertEI(eis, currTimestamp, endpointInteractionIdMap, oriList);

        // add additional test code here
    }

    /**
     * Run the void batchInsertEI(List<EndpointInteraction>,Timestamp,Map<Long,String>,List<OriLogClob>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertEI_3()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<EndpointInteraction> eis = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        List<OriLogClob> oriList = new Vector();
        fixture.setSqlSessionTemplate(session);
        fixture.batchInsertEI(eis, currTimestamp, endpointInteractionIdMap, oriList);

        // add additional test code here
    }

    /**
     * Run the void batchInsertEI(List<EndpointInteraction>,Timestamp,Map<Long,String>,List<OriLogClob>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertEI_4()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<EndpointInteraction> eis = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        List<OriLogClob> oriList = new Vector();
        fixture.setSqlSessionTemplate(session);
        fixture.batchInsertEI(eis, currTimestamp, endpointInteractionIdMap, oriList);

        // add additional test code here
    }

    /**
     * Run the void batchInsertEI(List<EndpointInteraction>,Timestamp,Map<Long,String>,List<OriLogClob>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertEI_5()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<EndpointInteraction> eis = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        List<OriLogClob> oriList = new Vector();
        fixture.setSqlSessionTemplate(session);
        fixture.batchInsertEI(eis, currTimestamp, endpointInteractionIdMap, oriList);

        // add additional test code here
    }

    /**
     * Run the void batchInsertEI(List<EndpointInteraction>,Timestamp,Map<Long,String>,List<OriLogClob>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertEI_6()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<EndpointInteraction> eis = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        List<OriLogClob> oriList = new Vector();
        fixture.batchInsertEI(eis, currTimestamp, endpointInteractionIdMap, oriList);

        // add additional test code here
    }

    /**
     * Run the void batchInsertORI(List<OriLogClob>,Timestamp,Map<Long,String>,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertORI_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<OriLogClob> oris = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        String ip = "";

        fixture.batchInsertORI(oris, currTimestamp, endpointInteractionIdMap, ip);

        // add additional test code here
    }

    /**
     * Run the void batchInsertORI(List<OriLogClob>,Timestamp,Map<Long,String>,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertORI_2()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<OriLogClob> oris = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        String ip = "";

        fixture.batchInsertORI(oris, currTimestamp, endpointInteractionIdMap, ip);

        // add additional test code here
    }

    /**
     * Run the void batchInsertORI(List<OriLogClob>,Timestamp,Map<Long,String>,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertORI_3()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<OriLogClob> oris = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        String ip = "";

        fixture.batchInsertORI(oris, currTimestamp, endpointInteractionIdMap, ip);

        // add additional test code here
    }

    /**
     * Run the void batchInsertORI(List<OriLogClob>,Timestamp,Map<Long,String>,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testBatchInsertORI_4()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<OriLogClob> oris = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();
        String ip = "";

        fixture.batchInsertORI(oris, currTimestamp, endpointInteractionIdMap, ip);

        // add additional test code here
    }

    /**
     * Run the ContractInteraction combineCI(ContractInteraction,Timestamp,List<ExceptionLogs>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testCombineCI_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setResponseDes("");
        Timestamp currTimestamp = new Timestamp(1L);
        List<ExceptionLogs> expLogs = new Vector();

        ContractInteraction result = fixture.combineCI(contractInteraction, currTimestamp, expLogs);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getResponseCode());
        assertEquals(null, result.getTimeZone());
        assertEquals(null, result.getServiceLevel());
        assertEquals(null, result.getQueueName());
        assertEquals(null, result.getDstSysCode());
        assertEquals(null, result.getSrcReqTime());
        assertEquals(null, result.getSrcTranId());
        assertEquals(null, result.getSrcSysCode());
        assertEquals(1, result.getTenantId());
        assertEquals(null, result.getCreateTime());
        assertEquals(null, result.getSrcSysSign());
        assertEquals(null, result.getDstTranId());
        assertEquals(null, result.getRegSerCode());
        assertEquals(null, result.getTestFlag());
        assertEquals(null, result.getDstRecTime());
        assertEquals(null, result.getSrcIp());
        assertEquals(null, result.getDstOrgCode());
        assertEquals(null, result.getTabSuffix());
        assertEquals(null, result.getSrcOrgCode());
        assertEquals(null, result.getCenterFwd2DstTime());
        assertEquals(0, result.getOutputSuccessFileNum());
        assertEquals(0, result.getInputFileNum());
        assertEquals(null, result.getSrcResponseTime());
        assertEquals(null, result.getBizIntfCode());
        assertEquals(null, result.getSrcConfirmTime());
        assertEquals(null, result.getDstReplyTime());
        assertEquals(0, result.getOutputErrFileNum());
        assertEquals(null, result.getResponseType());
        assertEquals(null, result.getRegSerVersion());
        assertEquals(null, result.getCenterRecDstTime());
        assertEquals(null, result.getCenterRecReqTime());
        assertEquals(null, result.getBizFuncCode());
        assertEquals(null, result.getCenterFwd2SrcTime());
        assertEquals(null, result.getContractVersion());
        assertEquals(null, result.getContractInteractionId());
        assertEquals(null, result.getServiceStyle());
        assertEquals("", result.getResponseDes());
    }

    /**
     * Run the ContractInteraction combineCI(ContractInteraction,Timestamp,List<ExceptionLogs>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testCombineCI_2()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setResponseDes("");
        Timestamp currTimestamp = new Timestamp(1L);
        List<ExceptionLogs> expLogs = new Vector();

        ContractInteraction result = fixture.combineCI(contractInteraction, currTimestamp, expLogs);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getResponseCode());
        assertEquals(null, result.getTimeZone());
        assertEquals(null, result.getServiceLevel());
        assertEquals(null, result.getQueueName());
        assertEquals(null, result.getDstSysCode());
        assertEquals(null, result.getSrcReqTime());
        assertEquals(null, result.getSrcTranId());
        assertEquals(null, result.getSrcSysCode());
        assertEquals(1, result.getTenantId());
        assertEquals(null, result.getCreateTime());
        assertEquals(null, result.getSrcSysSign());
        assertEquals(null, result.getDstTranId());
        assertEquals(null, result.getRegSerCode());
        assertEquals(null, result.getTestFlag());
        assertEquals(null, result.getDstRecTime());
        assertEquals(null, result.getSrcIp());
        assertEquals(null, result.getDstOrgCode());
        assertEquals(null, result.getTabSuffix());
        assertEquals(null, result.getSrcOrgCode());
        assertEquals(null, result.getCenterFwd2DstTime());
        assertEquals(0, result.getOutputSuccessFileNum());
        assertEquals(0, result.getInputFileNum());
        assertEquals(null, result.getSrcResponseTime());
        assertEquals(null, result.getBizIntfCode());
        assertEquals(null, result.getSrcConfirmTime());
        assertEquals(null, result.getDstReplyTime());
        assertEquals(0, result.getOutputErrFileNum());
        assertEquals(null, result.getResponseType());
        assertEquals(null, result.getRegSerVersion());
        assertEquals(null, result.getCenterRecDstTime());
        assertEquals(null, result.getCenterRecReqTime());
        assertEquals(null, result.getBizFuncCode());
        assertEquals(null, result.getCenterFwd2SrcTime());
        assertEquals(null, result.getContractVersion());
        assertEquals(null, result.getContractInteractionId());
        assertEquals(null, result.getServiceStyle());
        assertEquals("", result.getResponseDes());
    }

    /**
     * Run the ContractInteraction combineCI(ContractInteraction,Timestamp,List<ExceptionLogs>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testCombineCI_3()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setResponseDes("");
        Timestamp currTimestamp = new Timestamp(1L);
        List<ExceptionLogs> expLogs = new Vector();

        ContractInteraction result = fixture.combineCI(contractInteraction, currTimestamp, expLogs);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getResponseCode());
        assertEquals(null, result.getTimeZone());
        assertEquals(null, result.getServiceLevel());
        assertEquals(null, result.getQueueName());
        assertEquals(null, result.getDstSysCode());
        assertEquals(null, result.getSrcReqTime());
        assertEquals(null, result.getSrcTranId());
        assertEquals(null, result.getSrcSysCode());
        assertEquals(1, result.getTenantId());
        assertEquals(null, result.getCreateTime());
        assertEquals(null, result.getSrcSysSign());
        assertEquals(null, result.getDstTranId());
        assertEquals(null, result.getRegSerCode());
        assertEquals(null, result.getTestFlag());
        assertEquals(null, result.getDstRecTime());
        assertEquals(null, result.getSrcIp());
        assertEquals(null, result.getDstOrgCode());
        assertEquals(null, result.getTabSuffix());
        assertEquals(null, result.getSrcOrgCode());
        assertEquals(null, result.getCenterFwd2DstTime());
        assertEquals(0, result.getOutputSuccessFileNum());
        assertEquals(0, result.getInputFileNum());
        assertEquals(null, result.getSrcResponseTime());
        assertEquals(null, result.getBizIntfCode());
        assertEquals(null, result.getSrcConfirmTime());
        assertEquals(null, result.getDstReplyTime());
        assertEquals(0, result.getOutputErrFileNum());
        assertEquals(null, result.getResponseType());
        assertEquals(null, result.getRegSerVersion());
        assertEquals(null, result.getCenterRecDstTime());
        assertEquals(null, result.getCenterRecReqTime());
        assertEquals(null, result.getBizFuncCode());
        assertEquals(null, result.getCenterFwd2SrcTime());
        assertEquals(null, result.getContractVersion());
        assertEquals(null, result.getContractInteractionId());
        assertEquals(null, result.getServiceStyle());
        assertEquals("", result.getResponseDes());
    }

    /**
     * Run the OriLogClob combineOri(OriLogClob,Timestamp,Map<Long,String>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testCombineOri_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        OriLogClob oriLogClob = new OriLogClob();
        oriLogClob.setEndPointId(new Long(1L));
        oriLogClob.setMsg("");
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();

        OriLogClob result = fixture.combineOri(oriLogClob, currTimestamp, endpointInteractionIdMap);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getDstSysCode());
        assertEquals(null, result.getSrcTranId());
        assertEquals(null, result.getSrcSysCode());
        assertEquals(null, result.getReqOrRes());
        assertEquals("", result.getMsg());
        assertEquals(new Long(1L), result.getEndPointId());
        assertEquals(null, result.getSrcSysSign());
        assertEquals(null, result.getDstTranId());
        assertEquals(null, result.getSrcIp());
        assertEquals(null, result.getDstOrgCode());
        assertEquals(null, result.getFailMsg());
        assertEquals(null, result.getTabSuffix());
        assertEquals(null, result.getOlcId());
        assertEquals(null, result.getSrcOrgCode());
        assertEquals(null, result.getContractInteractionId());
        assertEquals(null, result.getEndpointInteractionId());
    }

    /**
     * Run the OriLogClob combineOri(OriLogClob,Timestamp,Map<Long,String>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testCombineOri_2()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        OriLogClob oriLogClob = new OriLogClob();
        oriLogClob.setEndPointId(new Long(1L));
        oriLogClob.setMsg("");
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();

        OriLogClob result = fixture.combineOri(oriLogClob, currTimestamp, endpointInteractionIdMap);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getDstSysCode());
        assertEquals(null, result.getSrcTranId());
        assertEquals(null, result.getSrcSysCode());
        assertEquals(null, result.getReqOrRes());
        assertEquals("", result.getMsg());
        assertEquals(new Long(1L), result.getEndPointId());
        assertEquals(null, result.getSrcSysSign());
        assertEquals(null, result.getDstTranId());
        assertEquals(null, result.getSrcIp());
        assertEquals(null, result.getDstOrgCode());
        assertEquals(null, result.getFailMsg());
        assertEquals(null, result.getTabSuffix());
        assertEquals(null, result.getOlcId());
        assertEquals(null, result.getSrcOrgCode());
        assertEquals(null, result.getContractInteractionId());
        assertEquals(null, result.getEndpointInteractionId());
    }

    /**
     * Run the OriLogClob combineOri(OriLogClob,Timestamp,Map<Long,String>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testCombineOri_3()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        OriLogClob oriLogClob = new OriLogClob();
        oriLogClob.setEndPointId(new Long(1L));
        oriLogClob.setMsg("");
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();

        OriLogClob result = fixture.combineOri(oriLogClob, currTimestamp, endpointInteractionIdMap);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getDstSysCode());
        assertEquals(null, result.getSrcTranId());
        assertEquals(null, result.getSrcSysCode());
        assertEquals(null, result.getReqOrRes());
        assertEquals("", result.getMsg());
        assertEquals(new Long(1L), result.getEndPointId());
        assertEquals(null, result.getSrcSysSign());
        assertEquals(null, result.getDstTranId());
        assertEquals(null, result.getSrcIp());
        assertEquals(null, result.getDstOrgCode());
        assertEquals(null, result.getFailMsg());
        assertEquals(null, result.getTabSuffix());
        assertEquals(null, result.getOlcId());
        assertEquals(null, result.getSrcOrgCode());
        assertEquals(null, result.getContractInteractionId());
        assertEquals(null, result.getEndpointInteractionId());
    }

    /**
     * Run the OriLogClob combineOri(OriLogClob,Timestamp,Map<Long,String>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testCombineOri_4()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        OriLogClob oriLogClob = new OriLogClob();
        oriLogClob.setEndPointId(new Long(1L));
        oriLogClob.setMsg("");
        Timestamp currTimestamp = new Timestamp(1L);
        Map<Long, String> endpointInteractionIdMap = new HashMap();

        OriLogClob result = fixture.combineOri(oriLogClob, currTimestamp, endpointInteractionIdMap);

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getDstSysCode());
        assertEquals(null, result.getSrcTranId());
        assertEquals(null, result.getSrcSysCode());
        assertEquals(null, result.getReqOrRes());
        assertEquals("", result.getMsg());
        assertEquals(new Long(1L), result.getEndPointId());
        assertEquals(null, result.getSrcSysSign());
        assertEquals(null, result.getDstTranId());
        assertEquals(null, result.getSrcIp());
        assertEquals(null, result.getDstOrgCode());
        assertEquals(null, result.getFailMsg());
        assertEquals(null, result.getTabSuffix());
        assertEquals(null, result.getOlcId());
        assertEquals(null, result.getSrcOrgCode());
        assertEquals(null, result.getContractInteractionId());
        assertEquals(null, result.getEndpointInteractionId());
    }

    /**
     * Run the void insert(String,Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsert_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        String statementName = "";
        Object parameterObject = new Object();
        fixture.setSqlSessionTemplate(session);
        fixture.insert(statementName, parameterObject);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insert(LogStoreDaoImpl.java:66)
    }

    /**
     * Run the void insertCI(List<ContractInteraction>,Timestamp,List<ExceptionLogs>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertCI_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<ContractInteraction> cis = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        List<ExceptionLogs> expLogs = new Vector();
        cis.add(new ContractInteraction());
        fixture.insertCI(cis, currTimestamp, expLogs);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
        //       at java.util.Vector.get(Vector.java:744)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insertCI(LogStoreDaoImpl.java:128)
    }

    /**
     * Run the void insertCtg(List<CtgLogs>,Timestamp,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertCtg_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<CtgLogs> ctgs = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        String contractInteractionId = "";

        fixture.insertCtg(ctgs, currTimestamp, contractInteractionId);

        // add additional test code here
    }

    /**
     * Run the void insertCtg(List<CtgLogs>,Timestamp,String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertCtg_2()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<CtgLogs> ctgs = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);
        String contractInteractionId = "";

        fixture.insertCtg(ctgs, currTimestamp, contractInteractionId);

        // add additional test code here
    }

    /**
     * Run the void insertExp(List<ExceptionLogs>,Timestamp) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertExp_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<ExceptionLogs> exps = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);

        fixture.insertExp(exps, currTimestamp);

        // add additional test code here
    }

    /**
     * Run the void insertExp(List<ExceptionLogs>,Timestamp) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertExp_2()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<ExceptionLogs> exps = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);

        fixture.insertExp(exps, currTimestamp);

        // add additional test code here
    }

    /**
     * Run the void insertExp(List<ExceptionLogs>,Timestamp) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertExp_3()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<ExceptionLogs> exps = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);

        fixture.insertExp(exps, currTimestamp);

        // add additional test code here
    }

    /**
     * Run the void insertExp(List<ExceptionLogs>,Timestamp) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertExp_4()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<ExceptionLogs> exps = new Vector();
        Timestamp currTimestamp = new Timestamp(1L);

        fixture.insertExp(exps, currTimestamp);

        // add additional test code here
    }

    /**
     * Run the Map insertLog(LogMessageObject) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertLog_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        LogMessageObject logMessageObject = new LogMessageObject();
        logMessageObject.setExceptionLogsList(new Vector());
        logMessageObject.setContractInteractionList(new Vector());
        logMessageObject.setCtgLogsList(new Vector());
        logMessageObject.setEndpointInteractionList(new Vector());
        logMessageObject.setOriLogClobList(new Vector());
        logMessageObject.getContractInteractionList().add(new ContractInteraction());
        Map result = fixture.insertLog(logMessageObject);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
        //       at java.util.Vector.get(Vector.java:744)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insertCI(LogStoreDaoImpl.java:128)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insertLog(LogStoreDaoImpl.java:82)
        assertNotNull(result);
    }

    /**
     * Run the void insertOriClobs(List<OriLogClob>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testInsertOriClobs_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<OriLogClob> oris = new Vector();

        fixture.setSqlSessionTemplate(session);
        fixture.insertOriClobs(oris);
        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insert(LogStoreDaoImpl.java:66)
        //       at com.asiainfo.integration.o2p.log.dao.impl.LogStoreDaoImpl.insertOriClobs(LogStoreDaoImpl.java:232)
    }

    /**
     * Run the void putHbase(LogMessageObject,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testPutHbase_1()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        LogMessageObject log = new LogMessageObject();
        HConnection connection = null;

        fixture.putHbase(log, connection);

        // add additional test code here
    }

    /**
     * Run the void putHbase(List<LogMessageObject>,HConnection) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    @Test
    public void testPutHbase_2()
        throws Exception {
        LogStoreDaoImpl fixture = new LogStoreDaoImpl();
        List<LogMessageObject> logs = new Vector();
        HConnection connection = null;

        fixture.putHbase(logs, connection);

        // add additional test code here
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception
     *         if the initialization fails for some reason
     *
     * @generatedBy CodePro at 15-12-17 下午5:49
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
     * @generatedBy CodePro at 15-12-17 下午5:49
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
     * @generatedBy CodePro at 15-12-17 下午5:49
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(LogStoreDaoImplTest.class);
    }
}