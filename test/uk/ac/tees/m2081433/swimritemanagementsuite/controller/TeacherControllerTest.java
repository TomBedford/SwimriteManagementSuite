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
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;

/**
 * A set of unit tests to exercise the TeacherController class.
 */
public class TeacherControllerTest {
    
    /**
     * The database manager where all data access object is located.
     */
    static DatabaseManager dbManager;
    
    /**
     * The connection source to the database.
     */
    static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The instance of the teacher controller.
     */
    static TeacherController instance;
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        dbManager = new DatabaseManager();
        
        smsConnectionSource = dbManager.createDatabaseConnection();
        
        dbManager.setupDatabase(smsConnectionSource);

        dbManager.initializeDaos(smsConnectionSource);
            
        instance = new TeacherController();
    }
    
    /**
     * Test of create method, of class TeacherController to check that a teacher can be successfully 
     * created within the teacher database table.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing: create - testing a teacher can be created in the database table");
        
        final Teacher expectedResult = new Teacher("Create Test Name");
        
        instance.create(expectedResult);
        
        List<Teacher> result = null;
        
        try {
            result = dbManager.teacherDAO.queryForMatching(expectedResult);
            
            assertEquals(expectedResult.getTeacherName(), result.get(0).getTeacherName());
        } catch (SQLException ex) {
            Logger.getLogger(TeacherControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class TeacherController to check records in the teacher table can be
     * successfully updated.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing: update - testing a teacher can be updated in the database table");
        
        final Teacher t = new Teacher("Update Test Name 1");

        List<Teacher> result = null;
        
        try {
            dbManager.teacherDAO.create(t);
            
            result = dbManager.teacherDAO.queryForMatching(t);
            
            result.get(0).setTeacherName("Update Test Name 2");
            
            instance.update(result.get(0));
            
            result = dbManager.teacherDAO.queryForMatching(result.get(0));
            
            assertEquals("Update Test Name 2", result.get(0).getTeacherName());
            
        } catch (SQLException ex) {
            //Logger.getLogger(TeacherControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of delete method, of class TeacherController to check student records can be successfully 
     * deleted from the student record table.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing: delete - to check teachers can be successfully deleted from the teacher table.");
        
        final Teacher t = new Teacher("Delete Test Name");

        List<Teacher> result = null;
        
        try {
            dbManager.teacherDAO.create(t);
        
            instance.delete(t);
        
            result = dbManager.teacherDAO.queryForMatching(t);
            
            assertEquals(0, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(TeacherControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAllTeachers method, of class TeacherController to check that all teachers can be successfully
     * queried from the database.
     */
    @Test
    public void testGetAllTeachers() {
        System.out.println("getAllTeachers");
        
        final Teacher t1 = new Teacher("All Teacher Test Name 1");
        
        final Teacher t2 = new Teacher("All Teacher Test Name 2");
        
        try {
            dbManager.teacherDAO.create(t1);
            dbManager.teacherDAO.create(t2);
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<Teacher> result = instance.getAllTeachers();
        
        assertTrue(result.size() > 1);
    }

    /**
     * Test of getTeachersForDay method, of class TeacherController to check that all teachers can be queried from
     * the database for a specific day.
     */
    @Test
    public void testGetTeachersForDay() {
        System.out.println("Testing: getTeachersForDay - to check that all teachers can be queried from the database for a specific day.");
        
        final Teacher t1 = new Teacher("All Teacher Test Name 3");
        t1.setWorkMonday(true);
        
        final Teacher t2 = new Teacher("All Teacher Test Name 4");
        t2.setWorkMonday(true);
        
        try {
            dbManager.teacherDAO.create(t1);
            dbManager.teacherDAO.create(t2);
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<Teacher> result = instance.getTeachersForDay(Day.MONDAY);
        
        for (Teacher teacher: result) {
            assertTrue(teacher.getWorkMonday());
        }
    }

    /**
     * Test of getTeachersNotForDay method, of class TeacherController to check that all teachers can be quiried from 
     * the database that dont work on the specified day.
     */
    @Test
    public void testGetTeachersNotForDay() {
        System.out.println("Testing: getTeachersNotForDay - test that all teachers can be quiried from the database that dont work on the specified day.");

        final Teacher t1 = new Teacher("All Teacher Test Name 5");
        
        final Teacher t2 = new Teacher("All Teacher Test Name 6");
        
        try {
            dbManager.teacherDAO.create(t1);
            dbManager.teacherDAO.create(t2);
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final List<Teacher> result = instance.getTeachersNotForDay(Day.MONDAY);
        
        for (Teacher teacher: result) {
            assertFalse(teacher.getWorkMonday());
        }
    }

    /**
     * Test of sortTeachersByTeacherId method, of class TeacherController to check that all teachers can be sorted by 
     * their id in ascending order.
     */
    @Test
    public void testSortTeachersByTeacherId() {
        System.out.println("Testing: sortTeachersByTeacherId - that all teachers can be sorted by their id in ascending order.");
        
        List<Teacher> result = null;
        
        try {
            TableUtils.clearTable(smsConnectionSource, Teacher.class);
            
            dbManager.teacherDAO.create(new Teacher("t1"));
            dbManager.teacherDAO.create(new Teacher("t2"));
            dbManager.teacherDAO.create(new Teacher("t3"));
            result = dbManager.teacherDAO.queryForAll();
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        result = instance.sortTeachersByTeacherId(result);
        
        assertTrue(result.get(0).getTeacherId() < result.get(1).getTeacherId());
        assertTrue(result.get(1).getTeacherId() < result.get(2).getTeacherId());
        assertTrue(result.get(0).getTeacherId() < result.get(2).getTeacherId());
        
    }
    
}
