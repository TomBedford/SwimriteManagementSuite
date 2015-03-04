package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
        
        
        LessonBlock lessonBlock = new LessonBlock();
        
        try {
            DatabaseManager.lessonBlockDAO.create(lessonBlock);
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the lesson block for the student record.");
            return false;
        }
        
        
        StudentRecord studentRecord = new StudentRecord(studentName, studentDOBDay + "/" + studentDOBMonth + "/" + studentDOBYear, studentTelephoneNo, studentAddress, 
                                                            hasIllness, parentName, abilityLevel, lessonBlock);
        
        try {
            DatabaseManager.studentRecordDAO.create(studentRecord);
            return true;
            
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the student record.");
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
        
        for (int i = 0; i < studentRecordList.size(); i++) {
            
            if (studentRecordList.get(i).getSwimmingClass() != null) {
                
                try {
                
                    DatabaseManager.swimmingClassesDAO.refresh(studentRecordList.get(i).getSwimmingClass());
                    
                    DatabaseManager.timeslotDAO.refresh(studentRecordList.get(i).getSwimmingClass().getTimeslot());
                
                } catch (SQLException e) {
                    System.out.println("getAllStudentRecords: Error getting all student records (controller).");
                }
                
            }
            
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
}
