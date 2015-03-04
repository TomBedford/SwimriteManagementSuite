package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;

/**
 *
 */
public class StudentRecordController {
    
    public boolean createStudentRecord(String studentName, String studentDOBDay, String studentDOBMonth, String studentDOBYear,
                                        String studentTelephoneNo, String addressLine1, String addressLine2, String addressCity,
                                            String addressCounty, String addressPostcode, String hasIllness, String parentName,
                                                SwimmingLevel abilityLevel) {
        
        StudentAddress studentAddress = new StudentAddress(addressLine1, addressLine2, addressCity, addressCounty, addressPostcode);
        
        try {
            DatabaseManager.studentAddressDAO.create(studentAddress);
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the student address for the student record.");
            return false;
        }
        
        // runs the method to format the date of birth correctly.
        String formattedDOB = formatDOB(studentDOBDay, studentDOBMonth, studentDOBYear);
        
        StudentRecord studentRecord = new StudentRecord(studentName, formattedDOB, studentTelephoneNo, studentAddress, 
                                                            hasIllness, parentName, abilityLevel);
        
        try {
            DatabaseManager.studentRecordDAO.create(studentRecord);
            return true;
            
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the student record.");
        }
        
        LessonBlock lessonBlock = new LessonBlock(studentRecord);
        
        try {
            DatabaseManager.lessonBlockDAO.create(lessonBlock);
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the lesson block for the student record.");
            return false;
        }
        
        return false;
    }
    
    public List<StudentRecord> getAllStudentRecords() {
        
        List<StudentRecord> studentRecordList = null;
        
        try {
            // query for all accounts that have "qwerty" as a password
            studentRecordList = DatabaseManager.studentRecordDAO.queryForAll();
            
        } catch (SQLException e) {
            System.out.println("getAllStudentRecords: Error getting all student records (controller).");
        }
        
        return studentRecordList;
    }
    
    public List<StudentRecord> getEnrolledStudentRecords() {
        
        // Gets all student records
        List<StudentRecord> allStudentRecordList = getAllStudentRecords();
        
        // New List to hold all student records where their swimming class field is not null
        List<StudentRecord> enrolledStudentRecordList = new ArrayList();
        
        // Loops through the all of the student records in the allStudentRecordList list
        for (int i = 0; i < allStudentRecordList.size(); i++) {
            
            // If the swimming class field is NOT equal to null (they are registered to a swimming class)
            if (allStudentRecordList.get(i).getSwimmingClass() != null) {
                // Add that student record to the enrolled student record list
                enrolledStudentRecordList.add(allStudentRecordList.get(i));
            }
        }
        return enrolledStudentRecordList;
    }
    
    public List<StudentRecord> getWaitingListStudentRecords() {
        
        // Gets all student records
        List<StudentRecord> allStudentRecordList = getAllStudentRecords();
        
        // New List to hold all student records where their swimming class field null
        List<StudentRecord> waitingListStudentRecordList = new ArrayList();
        
        // Loops through the all of the student records in the allStudentRecordList list
        for (int i = 0; i < allStudentRecordList.size(); i++) {
            
            // If the swimming class field is equal to null (they are not registered to a swimming class)
            if (allStudentRecordList.get(i).getSwimmingClass() == null) {
                // Add that student record to the waiting list student record list
                waitingListStudentRecordList.add(allStudentRecordList.get(i));
            }
        }
        return waitingListStudentRecordList;
    }
    
    public Boolean updateStudentRecord(StudentRecord studentRecord) {
        try {
            DatabaseManager.studentRecordDAO.update(studentRecord);
        } catch (SQLException e) {
            System.out.println("getAllStudentRecords: Error getting all student records (controller).");
            return false;
        }
        return true;
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
        
        final String[] unformattedDOB = dobString.split("/");
        
        return unformattedDOB;
    }
}
