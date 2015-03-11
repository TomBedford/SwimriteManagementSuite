package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;

/**
 * This controller interacts (create, update and delete, etc.) with the Student Record table within the database.
 */
public class StudentRecordController {
    
    /**
     * Reference to the Lesson Block controller for when deleting lesson blocks associated with student records.
     */
    final LessonBlockController lessonBlockController = new LessonBlockController();
    
    /**
     * Reference to the Student Address controller for when creating, updating and deleting student records.
     */
    final StudentAddressController studentAddressController = new StudentAddressController();
    
    
    
    /**
     * Creates a Student Record in the database using the provided parameters.
     * @param studentName The name of the student   
     * @param studentDOBDay The day of the date of birth of the student
     * @param studentDOBMonth The month of the date of birth of the student
     * @param studentDOBYear The year of the date of birth of the student
     * @param studentTelephoneNo The telephone no of the student
     * @param addressLine1 The first line of the students address
     * @param addressLine2 The second line of the students address
     * @param addressCity The city of the students address
     * @param addressCounty The county of the students address
     * @param addressPostcode The postcode of the students address
     * @param hasIllness The students mental or physical special requirements
     * @param parentName The students parents name
     * @param abilityLevel The ability level of the student
     * @return Boolean as to whether the student record was successfully created in the database.
     */
    public boolean createStudentRecord(String studentName, String studentDOBDay, String studentDOBMonth, String studentDOBYear,
                                        String studentTelephoneNo, String addressLine1, String addressLine2, String addressCity,
                                            String addressCounty, String addressPostcode, String hasIllness, String parentName,
                                                SwimmingLevel abilityLevel) {
        
        // Creates and initializes a new student address using the address params provided
        final StudentAddress studentAddress = new StudentAddress(addressLine1, addressLine2, addressCity, addressCounty, addressPostcode);
        
        // Creates the new student address in the database
        studentAddressController.createStudentAddress(studentAddress);
        
        // Calls the method to format the date of birth correctly for db storage.
        final String formattedDOB = formatDOB(studentDOBDay, studentDOBMonth, studentDOBYear);
        
        // Creates and initializes a new student record using the params provided, the student address created and the formatted dob
        final StudentRecord studentRecord = new StudentRecord(studentName, formattedDOB, studentTelephoneNo, studentAddress, 
                                                            hasIllness, parentName, abilityLevel);
        
        try {
            // Creates the student record in the student record database table
            DatabaseManager.studentRecordDAO.create(studentRecord);
            return true;
            
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the student record.");
        }
        
        // If this statement is reached then the student record wasn't added to the db correctly
        return false;
    }
    
    /**
     * Updates a Student Record in the database using the updated Student Record object provided as a parameter.
     * @param studentRecord The student record with updated values to update in the db table.
     */
    public void updateStudentRecord(StudentRecord studentRecord) {
        
        // Updates the student address from the student record
        studentAddressController.updateStudentAddress(studentRecord.getStudentAddress());
        
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
    public void deleteStudentRecord(StudentRecord studentRecord) {
        try {
            // Deletes the student record from the database.
            DatabaseManager.studentRecordDAO.delete(studentRecord);
            
            // Deletes the student records student address from the database.
            studentAddressController.deleteStudentAddress(studentRecord.getStudentAddress());
            
            // Creates and initializes a list to hold all lesson blocks associated with this student record.
            List<LessonBlock> studentLessonBlocks = null;
            
            // Gets all lesson blocks associated with this student record
            studentLessonBlocks = DatabaseManager.lessonBlockDAO.queryForEq(LessonBlock.STUDENTRECORD_COLUMN_NAME, studentRecord);
            
            // Loops through each lesson block in the list deleting it
            for (LessonBlock studentLessonBlock : studentLessonBlocks) {
                lessonBlockController.deleteLessonBlock(studentLessonBlock);
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
