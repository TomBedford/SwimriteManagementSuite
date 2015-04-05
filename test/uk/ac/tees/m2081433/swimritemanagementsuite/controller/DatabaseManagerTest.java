/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonPayment;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * A set of unit tests to exercise the DatabaseManager class.
 */
public class DatabaseManagerTest {
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        final DatabaseManager dbManager = new DatabaseManager();
        
        final JdbcConnectionSource smsConnectionSource = dbManager.createDatabaseConnection();

        try {
            TableUtils.clearTable(smsConnectionSource, LessonBlock.class);
            TableUtils.clearTable(smsConnectionSource, LessonPayment.class);
            TableUtils.clearTable(smsConnectionSource, LoginAccount.class);
            TableUtils.clearTable(smsConnectionSource, StudentAddress.class);
            TableUtils.clearTable(smsConnectionSource, StudentRecord.class);
            TableUtils.clearTable(smsConnectionSource, SwimmingClasses.class);
            TableUtils.clearTable(smsConnectionSource, Teacher.class);
            TableUtils.clearTable(smsConnectionSource, Timeslot.class);
        } catch (SQLException ex) {
            Logger.getLogger(TimeslotControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createDatabaseConnection method, of class DatabaseManager to check that the connection source is
     * firstly not equal to null, then checks if the URL of the result is equal to what is expected.
     */
    @Test
    public void testCreateDatabaseConnection() {
        System.out.println("Testing: createDatabaseConnection - the connection source to the database");
        final DatabaseManager instance = new DatabaseManager();
        
        final JdbcConnectionSource result = instance.createDatabaseConnection();
        
        assertNotNull(result);
        assertSame("jdbc:mysql://localhost:8888/SwimriteManagementSuite", result.getUrl());
    }

    /**
     * Test of setupDatabase method, of class DatabaseManager to check that database tables are created correctly
     * and exist within the database.
     */
    @Test
    public void testSetupDatabase() {
        System.out.println("Testing: setupDatabase - database tables are created and exist within the database");
        
        final DatabaseManager instance = new DatabaseManager();
        
        final JdbcConnectionSource connectionSource = instance.createDatabaseConnection();
        
        instance.initializeDaos(connectionSource);
        
        instance.setupDatabase(connectionSource);
        
        try {
            assertTrue(instance.lessonBlockDAO.isTableExists());
            assertTrue(instance.lessonPaymentDAO.isTableExists());
            assertTrue(instance.loginAccountDAO.isTableExists());
            assertTrue(instance.studentAddressDAO.isTableExists());
            assertTrue(instance.studentRecordDAO.isTableExists());
            assertTrue(instance.swimmingClassesDAO.isTableExists());
            assertTrue(instance.teacherDAO.isTableExists());
            assertTrue(instance.timeslotDAO.isTableExists());
        } catch (SQLException ex) {
            System.out.println("testSetupDatabase: Database tables not created correctly");
            System.out.println("testSetupDatabase: Possible fail if Data Access Objects not created correctly");
            Logger.getLogger(DatabaseManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of initializeDaos method, of class DatabaseManager to check that the data access objects for each table
     * within the database is created and works correctly.
     */
    @Test
    public void testInitializeDaos() {
        System.out.println("Testing: initializeDaos - Data Access Objects are created/initialized correctly");

        final DatabaseManager instance = new DatabaseManager();
        
        final JdbcConnectionSource connectionSource = instance.createDatabaseConnection();
        
        instance.initializeDaos(connectionSource);
        
        assertEquals(LessonBlock.class, instance.lessonBlockDAO.getDataClass());
        assertEquals(LessonPayment.class, instance.lessonPaymentDAO.getDataClass());
        assertEquals(LoginAccount.class, instance.loginAccountDAO.getDataClass());
        assertEquals(StudentAddress.class, instance.studentAddressDAO.getDataClass());
        assertEquals(StudentRecord.class, instance.studentRecordDAO.getDataClass());
        assertEquals(SwimmingClasses.class, instance.swimmingClassesDAO.getDataClass());
        assertEquals(Teacher.class, instance.teacherDAO.getDataClass());
        assertEquals(Timeslot.class, instance.timeslotDAO.getDataClass());
    }

    /**
     * Test of checkLoginAccountsForAdmin method, of class DatabaseManager to check that the overall database
     * administrator has been created successfully within the database.
     */
    @Test
    public void testCheckLoginAccountsForAdmin() {
        System.out.println("Testing: checkLoginAccountsForAdmin - The overall admin is created in the login account table");
        final DatabaseManager instance = new DatabaseManager();
        
        final JdbcConnectionSource connectionSource = instance.createDatabaseConnection();
        
        // Drops all records within the login account table
        try {
            TableUtils.clearTable(connectionSource, LoginAccount.class);
            
            // Should create the admin account
            instance.checkLoginAccountsForAdmin();

            // Gets all login accounts
            final List<LoginAccount> result = instance.loginAccountDAO.queryForAll();
            
            final LoginAccount expectedResult = new LoginAccount("Tom_Bedford", 
                        "ce29cc33bd2a6e1acbc82b97d4618ddc83f44b9d7d0efe5ee2b4147cc237b7c1f90c129d9e786456d29f896bdc361549bdad08fca7b7308c9fa43c51eb857312", 
                        "2fcc62eb46eae117ea2cbb96", true, "What was your first dog?", 
                        "ce94d02e85dbfb6d22d799300a3e5ae92262cd2187681b52c21011d64c5753dbffb78b53e4deae51efbf0d66a6d3d647569d9a4f64735cf7e893213307e532c7");
            
            assertEquals(expectedResult.getUsername(), result.get(0).getUsername());
            assertEquals(expectedResult.getPassword(), result.get(0).getPassword());
            assertEquals(expectedResult.getSalt(), result.get(0).getSalt());
            assertEquals(expectedResult.getSecurityQuestion(), result.get(0).getSecurityQuestion());
            assertEquals(expectedResult.getSecurityQuestionAnswer(), result.get(0).getSecurityQuestionAnswer());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
