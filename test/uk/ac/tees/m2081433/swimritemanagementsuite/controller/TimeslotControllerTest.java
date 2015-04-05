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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonPayment;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * A set of unit tests to exercise the TimeslotController class.
 */
public class TimeslotControllerTest {
    
    /**
     * The database manager where all data access object is located.
     */
    static DatabaseManager dbManager;
    
    /**
     * The connection source to the database.
     */
    static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The instance of the timeslot controller.
     */
    static TimeslotController instance;
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        dbManager = new DatabaseManager();
        
        smsConnectionSource = dbManager.createDatabaseConnection();
        
        dbManager.setupDatabase(smsConnectionSource);

        dbManager.initializeDaos(smsConnectionSource);
            
        instance = new TimeslotController();
    }
    
    /**
     * Occurs after the classes testing begins.
     */
    @AfterClass
    public static void tearDownClass() {
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
     * Test of create method, of class TimeslotController to check that a timeslot can be successfully 
     * created within the timeslot database table.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing: create - testing a timeslot can be created in the database table");
        
        final Timeslot expectedResult = new Timeslot(Day.MONDAY, 2200);
        
        instance.create(expectedResult);
        
        List<Timeslot> result = null;
        
        try {
            result = dbManager.timeslotDAO.queryForMatching(expectedResult);
            
            assertEquals(expectedResult.getTime(), result.get(0).getTime());
        } catch (SQLException ex) {
            Logger.getLogger(TimeslotControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class TimeslotController to check records in the timeslot table can be
     * successfully updated.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing: update - testing a timeslot can be updated in the database table");
        
        final Timeslot t = new Timeslot(Day.MONDAY, 2201);

        List<Timeslot> result = null;
        
        try {
            dbManager.timeslotDAO.create(t);
            
            result = dbManager.timeslotDAO.queryForMatching(t);
            
            result.get(0).setTime(2202);
            
            instance.update(result.get(0));
            
            result = dbManager.timeslotDAO.queryForMatching(result.get(0));
            
            assertEquals(2202, result.get(0).getTime());
            
        } catch (SQLException ex) {
            //Logger.getLogger(TimeslotControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of delete method, of class TimeslotController to check a timeslot can be successfully 
     * deleted from the timeslot table.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing: delete - to check a timeslot can be successfully deleted from the timeslot table.");
        
        final Timeslot t = new Timeslot(Day.MONDAY, 2203);
        
        List<Timeslot> result = null;
        
        try {
            dbManager.timeslotDAO.create(t);
        
            instance.delete(t);
        
            result = dbManager.timeslotDAO.queryForMatching(t);
            
            assertEquals(0, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(TimeslotControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTimeslotsForDay method, of class TimeslotController to check that you can query all timeslots for 
     * a specified day.
     */
    @Test
    public void testGetTimeslotsForDay() {
        System.out.println("Testing: getTimeslotsForDay - that you can query all timeslots for a specified day.");
        
        final Day day = Day.MONDAY;
        
        final List<Timeslot> expResult = null;
        
        final Timeslot t1 = new Timeslot(Day.MONDAY, 2204);
        
        final Timeslot t2 = new Timeslot(Day.MONDAY, 2205);

        try {
            dbManager.timeslotDAO.create(t1);
            dbManager.timeslotDAO.create(t2);
        
        } catch (SQLException ex) {
            Logger.getLogger(TimeslotControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<Timeslot> result = instance.getTimeslotsForDay(day);
        
        for (Timeslot timeslot: result) {
            assertEquals(day, timeslot.getDay());
        }
    }

    /**
     * Test of sortTimeslotsByTime method, of class TimeslotController to check that all timeslot can be sorted by 
     * their time in ascending order.
     */
    @Test
    public void testSortTimeslotsByTime() {
        System.out.println("Testing: sortTimeslotsByTime - test that all timeslot can be sorted by their time in ascending order.");
        
        List<Timeslot> result = null;
        
        try {
            TableUtils.clearTable(smsConnectionSource, Timeslot.class);
            
            dbManager.timeslotDAO.create(new Timeslot(Day.SATURDAY, 1));
            dbManager.timeslotDAO.create(new Timeslot(Day.SATURDAY, 2));
            dbManager.timeslotDAO.create(new Timeslot(Day.SATURDAY, 3));
            
            result = dbManager.timeslotDAO.queryForAll();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TimeslotControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        result = instance.sortTimeslotsByTime(result);
        
        assertTrue(result.get(0).getTime() < result.get(1).getTime());
        assertTrue(result.get(1).getTime() < result.get(2).getTime());
        assertTrue(result.get(0).getTime() < result.get(2).getTime());
    }
    
}
