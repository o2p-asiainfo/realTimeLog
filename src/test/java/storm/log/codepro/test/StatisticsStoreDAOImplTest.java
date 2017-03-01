package storm.log.codepro.test;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.*;
import org.mybatis.spring.SqlSessionTemplate;

import static org.junit.Assert.*;

import com.asiainfo.integration.o2p.log.common.bo.RegStatRecent;
import com.asiainfo.integration.o2p.log.common.bo.RegStatSec;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntSec;
import com.asiainfo.integration.o2p.log.dao.impl.StatisticsStoreDAOImpl;

/**
 * The class <code>StatisticsStoreDAOImplTest</code> contains tests for the class <code>{@link StatisticsStoreDAOImpl}</code>.
 *
 * @generatedBy CodePro at 15-12-17 下午5:47
 * @author daimq
 * @version $Revision: 1.0 $
 */
public class StatisticsStoreDAOImplTest {
    
    SqlSessionTemplate session = EasyMock.createMock(SqlSessionTemplate.class);
    /**
     * Run the void batchInsert(List<Object>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testBatchInsert_1()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<Object> list = new ArrayList();

        fixture.batchInsert(list);

        // add additional test code here
    }

    /**
     * Run the void batchUpdate(List<Object>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testBatchUpdate_1()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<Object> list = new ArrayList();

        fixture.batchUpdate(list);

        // add additional test code here
    }

    /**
     * Run the void insert(String,Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testInsert_1()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        String statementName = "";
        Object parameterObject = new Object();
        fixture.setSqlSessionTemplate(session);
        fixture.insert(statementName, parameterObject);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.StatisticsStoreDAOImpl.insert(StatisticsStoreDAOImpl.java:37)
    }

    /**
     * Run the void saveOrUpdateRegOther(List<RegStatRecent>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testSaveOrUpdateRegOther_1()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<RegStatRecent> list = new ArrayList();

        fixture.saveOrUpdateRegOther(list);

        // add additional test code here
    }

    /**
     * Run the void saveOrUpdateRegOther(List<RegStatRecent>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testSaveOrUpdateRegOther_2()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<RegStatRecent> list = new ArrayList();

        fixture.saveOrUpdateRegOther(list);

        // add additional test code here
    }

    /**
     * Run the void saveOrUpdateRegSec(List<RegStatSec>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testSaveOrUpdateRegSec_1()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<RegStatSec> list = new ArrayList();

        fixture.saveOrUpdateRegSec(list);

        // add additional test code here
    }

    /**
     * Run the void saveOrUpdateRegSec(List<RegStatSec>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testSaveOrUpdateRegSec_2()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<RegStatSec> list = new ArrayList();

        fixture.saveOrUpdateRegSec(list);

        // add additional test code here
    }

    /**
     * Run the void saveOrUpdateUseOther(List<UseStatCntRecent>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testSaveOrUpdateUseOther_1()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<UseStatCntRecent> list = new ArrayList();

        fixture.saveOrUpdateUseOther(list);

        // add additional test code here
    }

    /**
     * Run the void saveOrUpdateUseOther(List<UseStatCntRecent>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testSaveOrUpdateUseOther_2()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<UseStatCntRecent> list = new ArrayList();

        fixture.saveOrUpdateUseOther(list);

        // add additional test code here
    }

    /**
     * Run the void saveOrUpdateUseSec(List<UseStatCntSec>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testSaveOrUpdateUseSec_1()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<UseStatCntSec> list = new ArrayList();

        fixture.saveOrUpdateUseSec(list);

        // add additional test code here
    }

    /**
     * Run the void saveOrUpdateUseSec(List<UseStatCntSec>) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testSaveOrUpdateUseSec_2()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        List<UseStatCntSec> list = new ArrayList();

        fixture.saveOrUpdateUseSec(list);

        // add additional test code here
    }

    /**
     * Run the int update(String,Object) method test.
     *
     * @throws Exception
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    @Test
    public void testUpdate_1()
        throws Exception {
        StatisticsStoreDAOImpl fixture = new StatisticsStoreDAOImpl();
        String statementName = "";
        Object parameterObject = new Object();
        fixture.setSqlSessionTemplate(session);
        int result = fixture.update(statementName, parameterObject);

        // add additional test code here
        // An unexpected exception was thrown in user code while executing this test:
        //    java.lang.NullPointerException
        //       at com.asiainfo.integration.o2p.log.dao.impl.StatisticsStoreDAOImpl.update(StatisticsStoreDAOImpl.java:42)
        assertEquals(0, result);
    }

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception
     *         if the initialization fails for some reason
     *
     * @generatedBy CodePro at 15-12-17 下午5:47
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
     * @generatedBy CodePro at 15-12-17 下午5:47
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
     * @generatedBy CodePro at 15-12-17 下午5:47
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(StatisticsStoreDAOImplTest.class);
    }
}