package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;

/**
 * This controller interacts (create, update and delete) with the lesson block table within the database.
 */
public class LessonBlockController {
    
    /**
     * Creates a lesson block record in the database associated with the student record provided as param.
     * @param studentRecord The student record to associate the lesson block with
     */
    public void createLessonBlock(StudentRecord studentRecord) {
        
        // Creates and initializes a new lesson block associated with the student record
        final LessonBlock lessonBlock = new LessonBlock(studentRecord);
        
        try {
            // Creates the lesson block in the database.
            DatabaseManager.lessonBlockDAO.create(lessonBlock);
        } catch (SQLException e) {
            System.out.println("createLessonBlock: Error creating the lesson block in the db.");
        }
    }
    
    /**
     * Updates a lesson block record in the database using the updates lesson block object provided as a param.
     * @param lessonBlock The lesson block with updated values to update in the db table.
     */
    public void updateLessonBlock(LessonBlock lessonBlock) {
        try {
            DatabaseManager.lessonBlockDAO.update(lessonBlock);
        } catch (SQLException e) {
            System.out.println("updateLessonBlock: Error updating lesson block record (controller).");
        }
    }
    
    /**
     * Deletes a lesson block record from the database that matches the lesson block object provided as a param.
     * @param lessonBlock The lesson block to delete from the database.
     */
    public void deleteLessonBlock(LessonBlock lessonBlock) {
        try {
            DatabaseManager.lessonBlockDAO.delete(lessonBlock);
            
            // If the lesson blocks lesson payment is not null, delete the associated lesson block lesson payment
            if (lessonBlock.getLessonPayment() != null) {
                DatabaseManager.lessonPaymentDAO.delete(lessonBlock.getLessonPayment());
            }
        } catch (SQLException e) {
            System.out.println("deleteLessonBlock: Error deleting the lessonBlock record (controller).");
        }
    }
    
    /**
     * Gets all lesson blocks associated with a specified student record provided as a param.
     * @param studentRecord The student record to gather all the lesson blocks from
     * @return The list of lesson blocks associated with the student record
     */
    public List<LessonBlock> getLessonBlocksByStudent(StudentRecord studentRecord) {
        
        // Initialises the list to hold the lesson blocks
        List<LessonBlock> studentLessonBlocks = null;
        
        try {
            // Queries the db for where the student record column in the lesson block matches the student record provided
            studentLessonBlocks = DatabaseManager.lessonBlockDAO.queryForEq(LessonBlock.STUDENTRECORD_COLUMN_NAME, studentRecord);
        } catch (SQLException e) {
            System.out.println("getLessonBlocksByStudent: Error getting lesson blocks by the student record.");
        }
        
        // Returns the completed list of lesson blocks.
        return studentLessonBlocks;
    }
    
    /**
     * Sorts a list of lesson blocks by the lesson block id (Descending order).
     * @param lessonBlockList - The unsorted list of lesson blocks.
     * @return lessonBlockList - The sorted list of lesson blocks..
     */
    public List<LessonBlock> sortLessonBlockssByLessonBlockId(List<LessonBlock> lessonBlockList) {
        // Calls the overided method CompareTo to sort by the lesson block id in descending order).
        Collections.sort(lessonBlockList);
        
        // Returns the sorted list.
        return lessonBlockList;
    }
}
