package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;

/**
 *
 */
public class LessonBlockController {
    
    public void createLessonBlock(StudentRecord studentRecord) {
        
        LessonBlock lessonBlock = new LessonBlock(studentRecord);
        
        try {
            DatabaseManager.lessonBlockDAO.create(lessonBlock);
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the student address for the student record.");
        }
    }
    
    
    
    public List<LessonBlock> getLessonBlocksByStudent(StudentRecord studentRecord) {
        
        List<LessonBlock> studentLessonBlocks = null;
        
        try {
            studentLessonBlocks = DatabaseManager.lessonBlockDAO.queryForEq(LessonBlock.STUDENTRECORD_COLUMN_NAME, studentRecord);
        } catch (SQLException e) {
            System.out.println("getLessonBlocksByStudent: Error getting lesson blocks by the student record.");
        }
        
        return studentLessonBlocks;
    }
    
    public void deleteLessonBlock(LessonBlock lessonBlock) {
        try {
            DatabaseManager.lessonBlockDAO.delete(lessonBlock);
        } catch (SQLException e) {
            System.out.println("deleteLessonBlock: Error deleting the lessonBlock record (controller).");
        }
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
    
    /**
     * Formats/Concatenates the date of payment params provided into a string suitable for db storage.
     * @param payDay The day of the payment
     * @param payMonth The month of the payment
     * @param payYear The year of the payment
     * @return formattedDate The formatted date of birth string of the student.
     */
    public String formatPaymentDate(String payDay, String payMonth, String payYear) {
        // If the day of the payment is only 1 character long add 0 to the front of it.
        if (payDay.length() == 1) {
            payDay = "0" + payDay;
        }
        
        // If the month of the payment is only 1 character long add 0 to the front of it.
        if (payMonth.length() == 1) {
            payMonth = "0" + payMonth;
        }
        
        // Concatenates the strings with slashes.
        final String formattedDate = payDay + "/" + payMonth + "/" + payYear;
        
        return formattedDate;
    }
    
    /** 
     * Un-format the date of payment string by separating the Day, Month and Year as separate strings.
     * @param paymentDateString The string containing the day, month and year of the lessonblocks payment date
     * @return unformattedDate array the date of payment separated into 3 strings (day, month and year)
     */
    public String[] unformatPaymentDate(String paymentDateString) {
        
        final String[] unformattedDate = paymentDateString.split("/");
        
        return unformattedDate;
    }
}
