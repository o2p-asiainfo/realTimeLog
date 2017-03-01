package storm.log.util.codepro.test;

import java.sql.Timestamp;

import org.junit.*;

import static org.junit.Assert.*;

import com.asiainfo.integration.o2p.log.common.bo.RegStatRecent;
import com.asiainfo.integration.o2p.log.common.bo.RegStatSec;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntSec;
import com.asiainfo.integration.o2p.log.utils.StatisticsHelper;
import com.ailk.eaap.op2.bo.ContractInteraction;

/**
 * The class <code>StatisticsHelperTest</code> contains tests for the class <code>{@link StatisticsHelper}</code>.
 *
 * @generatedBy CodePro at 15-12-18 下午3:53
 * @author daimq
 * @version $Revision: 1.0 $
 */
public class StatisticsHelperTest {
    /**
     * Run the RegStatRecent generateRegStatRecent(ContractInteraction,String,int,int) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testGenerateRegStatRecent_1()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion((String) null);
        contractInteraction.setBizFuncCode((String) null);
        contractInteraction.setCenterRecDstTime(new Timestamp(1L));
        contractInteraction.setCenterFwd2SrcTime(new Timestamp(1L));
        contractInteraction.setDstOrgCode((String) null);
        contractInteraction.setTenantId(1);
        contractInteraction.setBizIntfCode((String) null);
        contractInteraction.setCenterRecReqTime(new Timestamp(1L));
        contractInteraction.setCenterFwd2DstTime(new Timestamp(1L));
        String timeType = "1";
        int expNum = 1;
        int ctgNum = 1;

        RegStatRecent result = StatisticsHelper.generateRegStatRecent(contractInteraction, timeType, expNum, ctgNum);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:578)
        //       at java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:560)
        //       at com.asiainfo.integration.o2p.log.utils.LogUtils.getStatisticsDateTypeValue(LogUtils.java:105)
        //       at com.asiainfo.integration.o2p.log.utils.StatisticsHelper.generateRegStatRecent(StatisticsHelper.java:165)
        assertNotNull(result);
    }

    /**
     * Run the RegStatSec generateRegStatSec(ContractInteraction,String,int,int) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testGenerateRegStatSec_1()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion((String) null);
        contractInteraction.setBizFuncCode((String) null);
        contractInteraction.setCenterRecDstTime(new Timestamp(1L));
        contractInteraction.setCenterFwd2SrcTime(new Timestamp(1L));
        contractInteraction.setDstOrgCode((String) null);
        contractInteraction.setTenantId(1);
        contractInteraction.setBizIntfCode((String) null);
        contractInteraction.setCenterRecReqTime(new Timestamp(1L));
        contractInteraction.setCenterFwd2DstTime(new Timestamp(1L));
        String timeType = "0";
        int expNum = 1;
        int ctgNum = 1;

        RegStatSec result = StatisticsHelper.generateRegStatSec(contractInteraction, timeType, expNum, ctgNum);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:578)
        //       at java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:560)
        //       at com.asiainfo.integration.o2p.log.utils.LogUtils.getStatisticsDateTypeValue(LogUtils.java:105)
        //       at com.asiainfo.integration.o2p.log.utils.StatisticsHelper.generateRegStatSec(StatisticsHelper.java:123)
        assertNotNull(result);
    }

    /**
     * Run the UseStatCntRecent generateUseStatCntRecent(ContractInteraction,String,int,int) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testGenerateUseStatCntRecent_1()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setBizFuncCode((String) null);
        contractInteraction.setCenterRecDstTime(new Timestamp(1L));
        contractInteraction.setCenterFwd2SrcTime(new Timestamp(1L));
        contractInteraction.setTenantId(1);
        contractInteraction.setBizIntfCode((String) null);
        contractInteraction.setSrcSysCode((String) null);
        contractInteraction.setContractVersion((String) null);
        contractInteraction.setCenterRecReqTime(new Timestamp(1L));
        contractInteraction.setCenterFwd2DstTime(new Timestamp(1L));
        String dateType = "1";
        int expNum = 1;
        int ctgNum = 1;

        UseStatCntRecent result = StatisticsHelper.generateUseStatCntRecent(contractInteraction, dateType, expNum, ctgNum);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:578)
        //       at java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:560)
        //       at com.asiainfo.integration.o2p.log.utils.LogUtils.getStatisticsDateTypeValue(LogUtils.java:105)
        //       at com.asiainfo.integration.o2p.log.utils.StatisticsHelper.generateUseStatCntRecent(StatisticsHelper.java:101)
        assertNotNull(result);
    }

    /**
     * Run the UseStatCntSec generateUseStatCntSec(ContractInteraction,String,int,int) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testGenerateUseStatCntSec_1()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setBizFuncCode((String) null);
        contractInteraction.setCenterRecDstTime(new Timestamp(1L));
        contractInteraction.setCenterFwd2SrcTime(new Timestamp(1L));
        contractInteraction.setTenantId(1);
        contractInteraction.setBizIntfCode((String) null);
        contractInteraction.setSrcSysCode((String) null);
        contractInteraction.setContractVersion((String) null);
        contractInteraction.setCenterRecReqTime(new Timestamp(1L));
        contractInteraction.setCenterFwd2DstTime(new Timestamp(1L));
        String timeType = "0";
        int expNum = 1;
        int ctgNum = 1;

        UseStatCntSec result = StatisticsHelper.generateUseStatCntSec(contractInteraction, timeType, expNum, ctgNum);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:578)
        //       at java.text.SimpleDateFormat.<init>(SimpleDateFormat.java:560)
        //       at com.asiainfo.integration.o2p.log.utils.LogUtils.getStatisticsDateTypeValue(LogUtils.java:105)
        //       at com.asiainfo.integration.o2p.log.utils.StatisticsHelper.generateUseStatCntSec(StatisticsHelper.java:46)
        assertNotNull(result);
    }

    /**
     * Run the boolean isEmptyString(String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testIsEmptyString_1()
        throws Exception {
        String str = "";

        boolean result = StatisticsHelper.isEmptyString(str);

        // add additional test code here
        assertEquals(true, result);
    }

    /**
     * Run the boolean isEmptyString(String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testIsEmptyString_2()
        throws Exception {
        String str = null;

        boolean result = StatisticsHelper.isEmptyString(str);

        // add additional test code here
        assertEquals(true, result);
    }

    /**
     * Run the boolean isEmptyString(String) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testIsEmptyString_3()
        throws Exception {
        String str = "";

        boolean result = StatisticsHelper.isEmptyString(str);

        // add additional test code here
        assertEquals(true, result);
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_1()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_2()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_3()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_4()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_5()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_6()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_7()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_8()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_9()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_10()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_11()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_12()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_13()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_14()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_15()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Run the void validCINotNullColunm(ContractInteraction) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    @Test
    public void testValidCINotNullColunm_16()
        throws Exception {
        ContractInteraction contractInteraction = new ContractInteraction();
        contractInteraction.setRegSerVersion("");
        contractInteraction.setBizFuncCode("");
        contractInteraction.setDstOrgCode("");
        contractInteraction.setBizIntfCode("");
        contractInteraction.setSrcSysCode("");
        contractInteraction.setContractVersion("");

        StatisticsHelper.validCINotNullColunm(contractInteraction);

        // add additional test code here
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception
     *         if the initialization fails for some reason
     *
     * @generatedBy CodePro at 15-12-18 下午3:53
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
     * @generatedBy CodePro at 15-12-18 下午3:53
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
     * @generatedBy CodePro at 15-12-18 下午3:53
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(StatisticsHelperTest.class);
    }
}