package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
        
        int dobDay = Integer.parseInt(studentDOBDay);
        int dobMonth = Integer.parseInt(studentDOBMonth);
        int dobYear = Integer.parseInt(studentDOBYear);
        
        StudentAddress studentAddress = new StudentAddress(addressLine1, addressLine2, addressCity, addressCounty, addressPostcode);
        
        try {
            DatabaseManager.studentAddressDAO.create(studentAddress);
        } catch (SQLException e) {
            System.out.println("createStudentRecord: Error creating the student address for the student record.");
        }
        
        
        StudentRecord studentRecord = new StudentRecord(studentName, dobDay, dobMonth, dobYear, studentTelephoneNo, studentAddress, 
                                                            hasIllness, parentName, abilityLevel);
        
        try {
            DatabaseManager.studentRecordDAO.create(studentRecord);
            
            JOptionPane.showMessageDialog(null,
                                "Student Record has been successfully created and added to the database!",
                                "Student Record created successfully!",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("images/icons/add.png"));
            
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
        
        return studentRecordList;
    }
    
    public List<StudentRecord> getEnrolledStudentRecords() {
        
        List<StudentRecord> allStudentRecordList = getAllStudentRecords();
        
        List<StudentRecord> enrolledStudentRecordList = new ArrayList();
        
        for (int i = 0; i < allStudentRecordList.size(); i++) {
            
            if (allStudentRecordList.get(i).getSwimmingClass() != null) {
                enrolledStudentRecordList.add(allStudentRecordList.get(i));
            }
            
        }
        
        System.out.println("enrolled SR: " + enrolledStudentRecordList.size());
        
        return enrolledStudentRecordList;
    }
}
