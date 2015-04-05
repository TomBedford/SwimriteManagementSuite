/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.DatabaseTableController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;

/**
 * This controller interacts (create, update and delete, etc.) with the Student Record table within the database.
 */
public class StudentRecordController implements DatabaseTableController<StudentRecord> {
    
    /**
     * Reference to the Lesson Block controller for when deleting lesson blocks associated with student records.
     */
    private final LessonBlockController lessonBlockController = new LessonBlockController();
    
    /**
     * Reference to the Student Address controller for when creating, updating and deleting student records.
     */
    private final StudentAddressController studentAddressController = new StudentAddressController();
    
    
    
    /**
     * Creates a Student Record in the database using the provided parameters.
     * @param studentRecord The student record to add to the Student Record database table.
     */
    @Override
    public void create(StudentRecord studentRecord) {
        try {
            // Creates the student record in the student record database table
            DatabaseManager.studentRecordDAO.create(studentRecord);
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the student record.");
        }
    }
    
    /**
     * Updates a Student Record in the database using the updated Student Record object provided as a parameter.
     * @param studentRecord The student record with updated values to update in the db table.
     */
    @Override
    public void update(StudentRecord studentRecord) {
        
        // Updates the student address from the student record
        studentAddressController.update(studentRecord.getStudentAddress());
        
        // Updates the student record
        try {
            DatabaseManager.studentRecordDAO.update(studentRecord);
        } catch (SQLException e) {
            System.out.println("updateStudentRecord: Error updating student record (controller).");
        }
    }
    
    /**
     * Deletes a Student Record from the database that matches the student record object provided as a parameter,
     * deletes the Student Address associated with the Student Record and deletes all Lesson Blocks associated 
     * with that Student Record.
     * @param studentRecord The student record to delete from the database.
     */
    @Override
    public void delete(StudentRecord studentRecord) {
        try {
            // Deletes the student record from the database.
            DatabaseManager.studentRecordDAO.delete(studentRecord);
            
            // Deletes the student records student address from the database.
            studentAddressController.delete(studentRecord.getStudentAddress());
            
            // Creates and initializes a list to hold all lesson blocks associated with this student record.
            List<LessonBlock> studentLessonBlocks = null;
            
            // Gets all lesson blocks associated with this student record
            studentLessonBlocks = DatabaseManager.lessonBlockDAO.queryForEq(LessonBlock.STUDENTRECORD_COLUMN_NAME, studentRecord);
            
            // Loops through each lesson block in the list deleting it
            for (LessonBlock studentLessonBlock : studentLessonBlocks) {
                lessonBlockController.delete(studentLessonBlock);
            }
            
            // If the student is currently enrolled in a swimming class de-increment the current capacity of the class
            if (studentRecord.getSwimmingClass() != null) {
                
                // De-increments the current class capacity by 1
                studentRecord.getSwimmingClass().setCurrentCapacity(studentRecord.getSwimmingClass().getCurrentCapacity() - 1);
                
                // Updates the swimming class in the database
                DatabaseManager.swimmingClassesDAO.update(studentRecord.getSwimmingClass());
            }
        } catch (SQLException e) {
            System.out.println("deleteStudentRecord: Error deleting the student record (controller).");
        }
    }
    
    
    
    /**
     * Gets all student records from the database.
     * @return List of all student records in the student record database table
     */
    public List<StudentRecord> getAllStudentRecords() {
        
        // Creates and initializes an empty list to hold all student records
        List<StudentRecord> studentRecordList = null;
        
        try {
            // Query for all student records in the student record database table.
            studentRecordList = DatabaseManager.studentRecordDAO.queryForAll();
        } catch (SQLException e) {
            System.out.println("getAllStudentRecords: Error getting all student records (controller).");
        }
        
        // Returns the list of student records
        return studentRecordList;
    }
    
    /**
     * Gets all student records that are currently enrolled in swimming class.
     * @return List of all student records currently enrolled in a swimming class 
     */
    public List<StudentRecord> getEnrolledStudentRecords() {
        
        // Gets all student records
        final List<StudentRecord> allStudentRecordList = getAllStudentRecords();
        
        // New List to hold all student records where their swimming class field is not null
        final List<StudentRecord> enrolledStudentRecordList = new ArrayList();
        
        // Loops through the all of the student records in the allStudentRecordList list
        for (StudentRecord studentRecord : allStudentRecordList) {
            // If the swimming class field is NOT equal to null (they are registered to a swimming class)
            if (studentRecord.getSwimmingClass() != null) {
                // Add that student record to the enrolled student record list
                enrolledStudentRecordList.add(studentRecord);
            }
        }
        // Return the list of enrolled student records
        return enrolledStudentRecordList;
    }
    
    /**
     * Gets all student records not currently enrolled in a swimming class.
     * @return List of all student records not currently enrolled in a swimming class
     */
    public List<StudentRecord> getWaitingListStudentRecords() {
        
        // Gets all student records
        final List<StudentRecord> allStudentRecordList = getAllStudentRecords();
        
        // New List to hold all student records where their swimming class field null
        final List<StudentRecord> waitingListStudentRecordList = new ArrayList();
        
        // Loops through the all of the student records in the allStudentRecordList list
        for (StudentRecord studentRecord : allStudentRecordList) {
            // If the swimming class field is equal to null (they are not registered to a swimming class)
            if (studentRecord.getSwimmingClass() == null) {
                // Add that student record to the waiting list student record list
                waitingListStudentRecordList.add(studentRecord);
            }
        }
        // Return the list of unenrolled student records
        return waitingListStudentRecordList;
    }
    
    /**
     * Gets all student records that are in the specified swimming class.
     * @param swimmingClass The swimming class to get all the students for
     * @return List of all student records in the swimming class
     */
    public List<StudentRecord> getSwimmingClassStudentRecords(SwimmingClasses swimmingClass) {
        
        // creates a list to hold all the student records associated with the swimming class
        List<StudentRecord> swimmingClassStudentRecordList = null;
        
        try {
            // Gets all student records associated with the swimming class provided as a param
            swimmingClassStudentRecordList = DatabaseManager.studentRecordDAO.queryForEq(StudentRecord.STUDENTSWIMMINGCLASS_COLUMN_NAME, swimmingClass);
        } catch (SQLException e) {
            System.out.println("getSwimmingClassStudentRecords: Error getting student records for swimming class (controller).");
        }
        
        // Return the list of students in the swimming class
        return swimmingClassStudentRecordList;
    }
    
 
    
    /**
     * Formats/Concatenates the date of birth params provided into a string suitable for db storage.
     * @param dobDay The student day of their date of birth
     * @param dobMonth The student month of their date of birth
     * @param dobYear The student year of their date of birth
     * @return formattedDOB The formatted date of birth string of the student.
     */
    public String formatDOB(String dobDay, String dobMonth, String dobYear) {
        // If the day of the date of birth is only 1 character long add 0 to the front of it.
        if (dobDay.length() == 1) {
            dobDay = "0" + dobDay;
        }
        
        // If the month of the date of birth is only 1 character long add 0 to the front of it.
        if (dobMonth.length() == 1) {
            dobMonth = "0" + dobMonth;
        }
        
        // Concatenates the strings with slashes.
        final String formattedDOB = dobDay + "/" + dobMonth + "/" + dobYear;
        
        return formattedDOB;
    }
    
    /** 
     * Un-format the date of birth string by separating the Day, Month and Year as separate strings.
     * @param dobString The string containing the day, month and year of the students date of birth
     * @return unformattedDOB array the date of birth separated into 3 strings (day, month and year)
     */
    public String[] unformatDOB(String dobString) {
        // Splits the string in '/' (forward slashes)
        final String[] unformattedDOB = dobString.split("/");
        
        // Returns the unformatted dob string array
        return unformattedDOB;
    }
}
