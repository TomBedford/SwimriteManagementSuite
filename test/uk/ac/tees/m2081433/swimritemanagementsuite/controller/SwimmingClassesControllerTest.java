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
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * A set of unit tests to exercise the SwimmingClassesController class.
 */
public class SwimmingClassesControllerTest {
    
    /**
     * The database manager where the all data access object is located.
     */
    static DatabaseManager dbManager;
    
    /**
     * The connection source to the database.
     */
    static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The instance of the swimming classes controller.
     */
    static SwimmingClassesController instance;
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        dbManager = new DatabaseManager();
        
        smsConnectionSource = dbManager.createDatabaseConnection();
        
        dbManager.setupDatabase(smsConnectionSource);

        dbManager.initializeDaos(smsConnectionSource);
            
        instance = new SwimmingClassesController();
    }
    
    /**
     * Test of create method, of class SwimmingClassesController to check that a swimming class can be successfully 
     * created within the swimming classes database table.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing: create - testing a swimming class can be created in the database table");
        
        final SwimmingClasses expectedResult = new SwimmingClasses(SwimmingLevel.BEGINNERS, new Timeslot(), new Teacher(), 201);
        
        instance.create(expectedResult);
        
        List<SwimmingClasses> result = null;
        
        try {
            result = dbManager.swimmingClassesDAO.queryForMatching(expectedResult);
            
            assertEquals(expectedResult.getMaxCapacity(), result.get(0).getMaxCapacity());
        } catch (SQLException ex) {
            Logger.getLogger(SwimmingClassesControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class SwimmingClassesController to check records in the swimming classes table can be
     * successfully updated.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing: update - testing a lesson block can be updated in the database table");
        
        final SwimmingClasses expectedResult = new SwimmingClasses(SwimmingLevel.BEGINNERS, new Timeslot(), new Teacher(), 202);
        
        List<SwimmingClasses> result = null;
        
        try {
            dbManager.swimmingClassesDAO.create(expectedResult);
            
            result = dbManager.swimmingClassesDAO.queryForMatching(expectedResult);
            
            result.get(0).setMaxCapacity(203);
            
            instance.update(result.get(0));
            
            result = dbManager.swimmingClassesDAO.queryForMatching(result.get(0));
            
            assertEquals(expectedResult.getMaxCapacity(), result.get(0).getMaxCapacity());
            
        } catch (SQLException ex) {
            //Logger.getLogger(SwimmingClassesControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of delete method, of class SwimmingClassesController to check swimming classes can be successfully 
     * deleted from the swimming classes table.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing: delete - testing a student record can be deleted in the database table");
        
        final SwimmingClasses expectedResult = new SwimmingClasses(SwimmingLevel.BEGINNERS, new Timeslot(), new Teacher(), 204);
        
        List<SwimmingClasses> result = null;
        
        try {
            dbManager.swimmingClassesDAO.create(expectedResult);
        
            instance.delete(expectedResult);
        
            result = dbManager.swimmingClassesDAO.queryForMatching(expectedResult);
            
            assertEquals(0, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(SwimmingClassesControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getClassesByDay method, of class SwimmingClassesController to check all swimming classes on a particular
     * day can be queried from the database.
     */
    @Test
    public void testGetClassesByDay() {
        System.out.println("Testing: getClassesByDay - testing all swimming classes on a particular day can be queried from the database.");
        
        final Day day = Day.MONDAY;
        
        final Timeslot t = new Timeslot(day, 1300);    
        
        final SwimmingClasses sc1 = new SwimmingClasses(SwimmingLevel.BEGINNERS, t, new Teacher(), 5);

        final SwimmingClasses sc2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, t, new Teacher(), 5);
        
        try {
            dbManager.swimmingClassesDAO.create(sc1);
            dbManager.swimmingClassesDAO.create(sc2);
        
        } catch (SQLException ex) {
            Logger.getLogger(SwimmingClassesControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<SwimmingClasses> result = instance.getClassesByDay(day);
        
        for (SwimmingClasses swimmingClass: result) {
            assertEquals(day, swimmingClass.getTimeslot().getDay());
        }
    }

    /**
     * Test of getClassesByTimeslot method, of class SwimmingClassesController to check all swimming classes for a 
     * particular timeslot can be queried from the database.
     */
    @Test
    public void testGetClassesByTimeslot() {
        System.out.println("Testing: getClassesByTimeslot - test all swimming classes for a particular timeslot can be queried from the database.");
        
        final Day day = Day.MONDAY;
        
        final Timeslot t = new Timeslot(day, 919191);    
        
        final SwimmingClasses sc1 = new SwimmingClasses(SwimmingLevel.BEGINNERS, t, new Teacher(), 5);

        final SwimmingClasses sc2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, t, new Teacher(), 5);
        
        try {
            dbManager.timeslotDAO.create(t);
            
            dbManager.swimmingClassesDAO.create(sc1);
            dbManager.swimmingClassesDAO.create(sc2);
        
        } catch (SQLException ex) {
            Logger.getLogger(SwimmingClassesControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<SwimmingClasses> result = instance.getClassesByTimeslot(t);
        
        for (SwimmingClasses swimmingClass: result) {
            assertEquals(t.getTime(), swimmingClass.getTimeslot().getTime());
        }
    }

    /**
     * Test of sortClassesByTeacherId method, of class SwimmingClassesController to check that swimming classes are
     * sorted by the teacher id in ascending order.
     */
    @Test
    public void testSortClassesByTeacherId() {
        System.out.println("Testing: sortClassesByTeacherId - test that swimming classes are sorted by the teacher id in ascending order.");
        
        final Teacher t1 = new Teacher("teacher 1");
        t1.setWorkMonday(true);
        
        final Teacher t2 = new Teacher("teacher 2");
        t2.setWorkMonday(true);
        
        final Timeslot t = new Timeslot(Day.MONDAY, 989898);  
        
        final SwimmingClasses sc1 = new SwimmingClasses(SwimmingLevel.BEGINNERS, t, t1, 5);

        final SwimmingClasses sc2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, t, t2, 5);
        
        try {
            dbManager.teacherDAO.create(t1);
            dbManager.teacherDAO.create(t2);
            
            dbManager.timeslotDAO.create(t);
            
            dbManager.swimmingClassesDAO.create(sc1);
            dbManager.swimmingClassesDAO.create(sc2);
        
        } catch (SQLException ex) {
            Logger.getLogger(SwimmingClassesControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<SwimmingClasses> result = instance.getClassesByTimeslot(t);
        
        result = instance.sortClassesByTeacherId(result);
        
        assertEquals(t1.getTeacherName(), result.get(0).getTeacher().getTeacherName());
        
        assertEquals(t2.getTeacherName(), result.get(1).getTeacher().getTeacherName());
    }

    /**
     * Test of sortClassesByTimeslotTime method, of class SwimmingClassesController to check that swimming classes are
     * sorted by the timeslot time in ascending order.
     */
    @Test
    public void testSortClassesByTimeslotTime() {
        System.out.println("Testing: sortClassesByTimeslotTime - test that swimming classes are sorted by the timeslot time in ascending order.");
        
        final Timeslot t1 = new Timeslot(Day.SUNDAY, 11111);  
        
        final Timeslot t2 = new Timeslot(Day.SUNDAY, 22222);  
        
        final SwimmingClasses sc1 = new SwimmingClasses(SwimmingLevel.BEGINNERS, t1, new Teacher(), 5);

        final SwimmingClasses sc2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, t2, new Teacher(), 5);
        
        try {
            TableUtils.clearTable(smsConnectionSource, Timeslot.class);
            TableUtils.clearTable(smsConnectionSource, SwimmingClasses.class);
            
            dbManager.timeslotDAO.create(t1);
            dbManager.timeslotDAO.create(t2);
            
            dbManager.swimmingClassesDAO.create(sc1);
            dbManager.swimmingClassesDAO.create(sc2);
        
        } catch (SQLException ex) {
            Logger.getLogger(SwimmingClassesControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<SwimmingClasses> result = instance.getClassesByDay(Day.SUNDAY);
        
        result = instance.sortClassesByTimeslotTime(result);
        
        assertEquals(t1.getTime(), result.get(0).getTimeslot().getTime());
        
        assertEquals(t2.getTime(), result.get(1).getTimeslot().getTime());
    }
    
}
