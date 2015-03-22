package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonPayment;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.PaymentType;

/**
 * A set of unit tests to exercise the LessonPaymentController class.
 */
public class LessonPaymentControllerTest {
    
    /**
     * The database manager where the all data access object is located.
     */
    static DatabaseManager dbManager;
    
    /**
     * The connection source to the database.
     */
    static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The instance of the login account controller.
     */
    static LessonPaymentController instance;
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        dbManager = new DatabaseManager();
        
        smsConnectionSource = dbManager.createDatabaseConnection();
        
        dbManager.setupDatabase(smsConnectionSource);

        dbManager.initializeDaos(smsConnectionSource);
            
        instance = new LessonPaymentController();
    }
    
    /**
     * Test of create method, of class LessonPaymentController to check that a lesson payment can be successfully 
     * created within the lesson payment database table.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing: create - testing a lesson payment can be created in the database table");
        
        final LessonPayment expectedResult = new LessonPayment("£30.00", PaymentType.CARD, "12/12/2001", "test");
        
        instance.create(expectedResult);
        
        List<LessonPayment> result = null;
        
        try {
            result = dbManager.lessonPaymentDAO.queryForMatching(expectedResult);
            
            assertEquals(expectedResult.getPaymentTaker(), result.get(0).getPaymentTaker());
        } catch (SQLException ex) {
            Logger.getLogger(LessonPaymentControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class LessonPaymentController to check records in the lesson payment table can be
     * successfully updated.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing: update - testing a lesson payment can be updated in the database table");
        
        final LessonPayment lp = new LessonPayment("£30.00", PaymentType.CARD, "12/12/2001", "test");
        
        List<LessonPayment> result = null;
        
        try {
            dbManager.lessonPaymentDAO.create(lp);
            
            result = dbManager.lessonPaymentDAO.queryForMatching(lp);
            
            result.get(0).setPaymentTaker("Testing Update");
            
            instance.update(result.get(0));
            
            result = dbManager.lessonPaymentDAO.queryForMatching(result.get(0));
            
            assertEquals("Testing Update", result.get(0).getPaymentTaker());
            
        } catch (SQLException ex) {
            Logger.getLogger(LessonPaymentControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of delete method, of class LessonPaymentController to check lesson payment records can be successfully 
     * deleted from the lesson payment table.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing: delete - testing a lesson payment can be deleted in the database table");
        
        final LessonPayment lp = new LessonPayment("£30.00", PaymentType.CARD, "12/12/2001", "test");
        
        List<LessonPayment> result = null;
        
        try {
            dbManager.lessonPaymentDAO.create(lp);
        
            instance.delete(lp);
        
            result = dbManager.lessonPaymentDAO.queryForMatching(lp);
            
            assertEquals(0, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(LessonPaymentControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of formatpaymentDate method, of class LessonPaymentController to check that date strings are concatenated
     * in the correct date format DD/MM/YYYY.
     */
    @Test
    public void testFormatpaymentDate() {
        System.out.println("Testing: formatpaymentDate - that date strings are concatenated in the correct date format DD/MM/YYYY.");
        final String dateDay = "1";
        final String dateMonth = "2";
        final String dateYear = "2001";
        final LessonPaymentController instance = new LessonPaymentController();
        final String expResult = "01/02/2001";
        final String result = instance.formatpaymentDate(dateDay, dateMonth, dateYear);
        assertEquals(expResult, result);
    }

    /**
     * Test of unformatPaymentDate method, of class LessonPaymentController to check that date strings are correctly
     * separated on the slashes "/".
     */
    @Test
    public void testUnformatPaymentDate() {
        System.out.println("unformatPaymentDate");
        final String paymentDateString = "01/02/2001";
        final LessonPaymentController instance = new LessonPaymentController();
        final String[] expResult = {"01", "02", "2001"};
        final String[] result = instance.unformatPaymentDate(paymentDateString);
        assertArrayEquals(expResult, result);
    }
    
}
