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

/**
 * A set of unit tests to exercise the StudentAddressController class.
 */
public class StudentAddressControllerTest {
    
    /**
     * The database manager where the all data access object is located.
     */
    static DatabaseManager dbManager;
    
    /**
     * The connection source to the database.
     */
    static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The instance of the student address controller.
     */
    static StudentAddressController instance;
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        dbManager = new DatabaseManager();
        
        smsConnectionSource = dbManager.createDatabaseConnection();
        
        dbManager.setupDatabase(smsConnectionSource);

        dbManager.initializeDaos(smsConnectionSource);
            
        instance = new StudentAddressController();
    }
    
    /**
     * Test of create method, of class StudentAddressController to check that a student address can be successfully 
     * created within the student address database table.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing: create - testing a student address can be created in the database table");
        
        final StudentAddress expectedResult = new StudentAddress("1 Line", "Second Line", "City", "County", "P05T C0D3");
        
        instance.create(expectedResult);
        
        List<StudentAddress> result = null;
        
        try {
            result = dbManager.studentAddressDAO.queryForMatching(expectedResult);
            
            assertEquals(expectedResult.getPostcode(), result.get(0).getPostcode());
        } catch (SQLException ex) {
            Logger.getLogger(StudentAddressControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class StudentAddressController to check records in the student address table can be
     * successfully updated.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing: update - testing a student address can be updated in the database table");
        
        final StudentAddress sa = new StudentAddress("1 Line", "Second Line", "City", "County", "P05T C0D3");
        
        List<StudentAddress> result = null;
        
        try {
            dbManager.studentAddressDAO.create(sa);
            
            result = dbManager.studentAddressDAO.queryForMatching(sa);
            
            result.get(0).setAddressLine1("Testing Update");
            
            instance.update(result.get(0));
            
            result = dbManager.studentAddressDAO.queryForMatching(result.get(0));
            
            assertEquals("Testing Update", result.get(0).getAddressLine1());
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentAddressControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of delete method, of class StudentAddressController to check student address records can be successfully 
     * deleted from the student address table.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing: delete - testing a student address can be deleted in the database table");
        
        final StudentAddress sa = new StudentAddress("1 Line", "Second Line", "City", "County", "P05T C0D3");
        
        List<StudentAddress> result = null;
        
        try {
            dbManager.studentAddressDAO.create(sa);
        
            instance.delete(sa);
        
            result = dbManager.studentAddressDAO.queryForMatching(sa);
            
            assertEquals(0, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(StudentAddressControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
