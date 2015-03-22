package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * A set of unit tests to exercise the StudentRecordController class.
 */
public class StudentRecordControllerTest {
    
    /**
     * The database manager where the all data access object is located.
     */
    static DatabaseManager dbManager;
    
    /**
     * The connection source to the database.
     */
    static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The instance of the student record controller.
     */
    static StudentRecordController instance;
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        dbManager = new DatabaseManager();
        
        smsConnectionSource = dbManager.createDatabaseConnection();
        
        dbManager.setupDatabase(smsConnectionSource);

        dbManager.initializeDaos(smsConnectionSource);
            
        instance = new StudentRecordController();
    }
    
    /**
     * Test of create method, of class StudentRecordController to check that a student record can be successfully 
     * created within the student record database table.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing: create - testing a student record can be created in the database table");
        
        final StudentRecord expectedResult = new StudentRecord("Test Name", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        instance.create(expectedResult);
        
        List<StudentRecord> result = null;
        
        try {
            result = dbManager.studentRecordDAO.queryForMatching(expectedResult);
            
            assertEquals(expectedResult.getStudentName(), result.get(0).getStudentName());
        } catch (SQLException ex) {
            Logger.getLogger(StudentRecordControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class StudentRecordController to check records in the student record table can be
     * successfully updated.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing: update - testing a student record can be updated in the database table");
        
        final StudentRecord sr = new StudentRecord("Test Update Name", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        List<StudentRecord> result = null;
        
        try {
            dbManager.studentRecordDAO.create(sr);
            
            result = dbManager.studentRecordDAO.queryForMatching(sr);
            
            result.get(0).setStudentName("Testing Update");
            
            instance.update(result.get(0));
            
            result = dbManager.studentRecordDAO.queryForMatching(result.get(0));
            
            assertEquals("Testing Update", result.get(0).getStudentName());
            
        } catch (SQLException ex) {
            //Logger.getLogger(StudentRecordControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of delete method, of class StudentRecordController to check student records can be successfully 
     * deleted from the student record table.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing: delete - testing a student record can be deleted in the database table");
        
        final StudentRecord sr = new StudentRecord("Test Delete", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        List<StudentRecord> result = null;
        
        try {
            dbManager.studentRecordDAO.create(sr);
        
            instance.delete(sr);
        
            result = dbManager.studentRecordDAO.queryForMatching(sr);
            
            assertEquals(0, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(StudentRecordControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAllStudentRecords method, of class StudentRecordController to check that all student records can be
     * successfully queried from the database.
     */
    @Test
    public void testGetAllStudentRecords() {
        System.out.println("Testing: getAllStudentRecords - tests that all student records can be successfully queried from the database.");
        
        final StudentRecord sr1 = new StudentRecord("Test Name1", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        final StudentRecord sr2 = new StudentRecord("Test Name2", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        List<StudentRecord> result = null;
        
        try {
            dbManager.studentRecordDAO.create(sr1);
            dbManager.studentRecordDAO.create(sr2);
            
            result = instance.getAllStudentRecords();
            
            assertTrue(result.size() > 1);
        } catch (SQLException ex) {
            Logger.getLogger(StudentRecordControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getEnrolledStudentRecords method, of class StudentRecordController to check that all students that
     * are enrolled in a swimming class can be successfully queried from the database.
     */
    @Test
    public void testGetEnrolledStudentRecords() {
        System.out.println("Testing: getEnrolledStudentRecords - that all students that are enrolled in a swimming class can be successfully queried from the database.");
        
        final StudentRecord sr1 = new StudentRecord("Test Name1", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        final StudentRecord sr2 = new StudentRecord("Test Name2", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        final SwimmingClasses sc = new SwimmingClasses(SwimmingLevel.BEGINNERS, new Timeslot(), new Teacher(), 5);
        
        try {
            dbManager.swimmingClassesDAO.create(sc);
            
            sr1.setSwimmingClass(sc);
            sr2.setSwimmingClass(sc);
            
            dbManager.studentRecordDAO.create(sr1);
            dbManager.studentRecordDAO.create(sr2);
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentRecordControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<StudentRecord> result = instance.getEnrolledStudentRecords();
        
        for (StudentRecord studentRecord: result) {
            assertNotNull(studentRecord.getSwimmingClass());
        }
    }

    /**
     * Test of getWaitingListStudentRecords method, of class StudentRecordController to check that all students currently
     * not enrolled in a swimming class can be successfully queried from the database.
     */
    @Test
    public void testGetWaitingListStudentRecords() {
        System.out.println("Testing: getWaitingListStudentRecords - that all students currently not enrolled in a swimming class can be successfully queried from the database.");
        
        final StudentRecord sr1 = new StudentRecord("Test Name 1", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        final StudentRecord sr2 = new StudentRecord("Test Name 2", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        try {
            
            dbManager.studentRecordDAO.create(sr1);
            dbManager.studentRecordDAO.create(sr2);
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentRecordControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<StudentRecord> result = instance.getWaitingListStudentRecords();
        
        for (StudentRecord studentRecord: result) {
            assertNull(studentRecord.getSwimmingClass());
        }
    }

    /**
     * Test of getSwimmingClassStudentRecords method, of class StudentRecordController to check that all student records
     * can be queried from the database for a particular swimming class.
     */
    @Test
    public void testGetSwimmingClassStudentRecords() {
        System.out.println("Testing: getSwimmingClassStudentRecords - that all student records can be queried from the database for a particular swimming class.");
        
        final StudentRecord sr1 = new StudentRecord("Test Name 1", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        final StudentRecord sr2 = new StudentRecord("Test Name 2", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
        
        final SwimmingClasses sc = new SwimmingClasses(SwimmingLevel.BEGINNERS, new Timeslot(), new Teacher(), 1234567890);
        
        try {
            dbManager.swimmingClassesDAO.create(sc);
            
            sr1.setSwimmingClass(sc);
            sr2.setSwimmingClass(sc);
            
            dbManager.studentRecordDAO.create(sr1);
            dbManager.studentRecordDAO.create(sr2);
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentRecordControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<StudentRecord> result = instance.getSwimmingClassStudentRecords(sc);
        
        for (StudentRecord studentRecord: result) {
            assertEquals(sc.getMaxCapacity(), studentRecord.getSwimmingClass().getMaxCapacity());
        }
    }

    /**
     * Test of formatDOB method, of class StudentRecordController to check that date strings are concatenated
     * in the correct date format DD/MM/YYYY.
     */
    @Test
    public void testFormatDOB() {
        System.out.println("Testing: formatDOB - that date strings are concatenated in the correct date format DD/MM/YYYY.");
        final String dateDay = "1";
        final String dateMonth = "2";
        final String dateYear = "2001";
        final String expResult = "01/02/2001";
        final String result = instance.formatDOB(dateDay, dateMonth, dateYear);
        assertEquals(expResult, result);
    }

    /**
     * Test of unformatDOB method, of class StudentRecordController to check that date strings are correctly
     * separated on the slashes "/".
     */
    @Test
    public void testUnformatDOB() {
        System.out.println("unformatPaymentDate");
        final String paymentDateString = "01/02/2001";
        final String[] expResult = {"01", "02", "2001"};
        final String[] result = instance.unformatDOB(paymentDateString);
        assertArrayEquals(expResult, result);
    }
    
}
