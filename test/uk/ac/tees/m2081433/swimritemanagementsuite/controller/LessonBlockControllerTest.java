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
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;

/**
 * A set of unit tests to exercise the LessonBlockController class.
 */
public class LessonBlockControllerTest {
    
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
    static LessonBlockController instance;
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        dbManager = new DatabaseManager();
        
        smsConnectionSource = dbManager.createDatabaseConnection();
        
        dbManager.setupDatabase(smsConnectionSource);

        dbManager.initializeDaos(smsConnectionSource);
            
        instance = new LessonBlockController();
    }

    /**
     * Test of create method, of class LessonBlockController to check that a lesson block can be successfully 
     * created within the lesson block database table.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing: create - testing a lesson block can be created in the database table");
        
        final LessonBlock expectedResult = new LessonBlock();
        
        final LessonBlockController instance = new LessonBlockController();
        
        instance.create(expectedResult);
        
        List<LessonBlock> result = null;
        
        try {
            result = dbManager.lessonBlockDAO.queryForMatching(expectedResult);
            
            assertEquals(expectedResult.getLesson1Date(), result.get(0).getLesson1Date());
        } catch (SQLException ex) {
            Logger.getLogger(LessonBlockControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class LessonBlockController to check records in the lesson block table can be
     * successfully updated.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing: update - testing a lesson block can be updated in the database table");
        
        final LessonBlock lb = new LessonBlock();
        
        List<LessonBlock> result = null;
        
        try {
            dbManager.lessonBlockDAO.create(lb);
            
            result = dbManager.lessonBlockDAO.queryForMatching(lb);
            
            result.get(0).setLesson10Date("Testing Date Update");
            
            instance.update(result.get(0));
            
            result = dbManager.lessonBlockDAO.queryForMatching(lb);
            
            assertEquals("Testing Date Update", result.get(0).getLesson10Date());
            
        } catch (SQLException ex) {
            Logger.getLogger(LessonBlockControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of delete method, of class LessonBlockController to check lesson block records can be successfully 
     * deleted from the lesson block table.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing: delete - testing a lesson block can be deleted in the database table");
        
        final LessonBlock lb = new LessonBlock();
        
        List<LessonBlock> result = null;
        
        try {
            dbManager.lessonBlockDAO.create(lb);
        
            instance.delete(lb);
        
            result = dbManager.lessonBlockDAO.queryForMatching(lb);
            
            assertEquals(0, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(LessonBlockControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getLessonBlocksByStudent method, of class LessonBlockController to check that all lesson blocks can be
     * gathered for a desired student record.
     */
    @Test
    public void testGetLessonBlocksByStudent() {
        System.out.println("Testing: getLessonBlocksByStudent - tests all lesson blocks can be gathered for a desired student record");
        
        List<LessonBlock> result = null;
        
        try {
            final StudentRecord studentRecord = new StudentRecord("Name", "01/01/2001", "12345678901", new StudentAddress(), 
                            "n/a", "parent", SwimmingLevel.BEGINNERS);
            dbManager.studentRecordDAO.create(studentRecord);

            final LessonBlock lb1 = new LessonBlock(studentRecord);
            dbManager.lessonBlockDAO.create(lb1);
            final LessonBlock lb2 = new LessonBlock(studentRecord);
            dbManager.lessonBlockDAO.create(lb2);

            result = instance.getLessonBlocksByStudent(studentRecord);
            
            assertEquals(2, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(LessonBlockControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of sortLessonBlockssByLessonBlockId method, of class LessonBlockController to check that lesson blocks
     * are successfully sorted by their id (descending order).
     */
    @Test
    public void testSortLessonBlockssByLessonBlockId() {
        System.out.println("Testing: sortLessonBlockssByLessonBlockId - tests that lesson blocks are successfully sorted by their id (descending order)");
        
        List<LessonBlock> result = null;
        
        try {
            
            TableUtils.clearTable(smsConnectionSource, LessonBlock.class);
            dbManager.lessonBlockDAO.create(new LessonBlock());
            dbManager.lessonBlockDAO.create(new LessonBlock());
            dbManager.lessonBlockDAO.create(new LessonBlock());
            result = dbManager.lessonBlockDAO.queryForAll();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LessonBlockControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        result = instance.sortLessonBlockssByLessonBlockId(result);
        
        assertTrue(result.get(0).getBlocktId() > result.get(1).getBlocktId());
        assertTrue(result.get(1).getBlocktId() > result.get(2).getBlocktId());
        assertTrue(result.get(0).getBlocktId() > result.get(2).getBlocktId());
    }
}
