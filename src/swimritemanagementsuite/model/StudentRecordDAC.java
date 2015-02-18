package swimritemanagementsuite.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Bedford
 */
public class StudentRecordDAC {
    
    /**
     * The Data Access Object used to access the Student Record table in the mySQL database.
     */
    private Dao<StudentRecord, Integer> studentRecordDAO;
    
    /**
     * Student Record object used for creating, updating and deleting student records from the database.
     */
    StudentRecord studentRecord;
    
    public StudentRecordDAC() {
        
    }
    
    public StudentRecordDAC(ConnectionSource connectionSource) {
        try {
            studentRecordDAO = DaoManager.createDao(connectionSource, StudentRecord.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the Student Record database access objects");
        }
    }
    
    // CREATE, UPDATE AND DELETE FOR THE STUDENT RECORD DATABASE TABLE.
    /**
     * Creates a Student Record and persists it to the database.
     *
     * @param studentName The name of the student.
     * @param studentDOB The date of birth of the student.
     * @param telephoneNo The telephone number of the student.
     * @param studentAddress The home address of the student.
     * @param hasIllness Information as to whether the student has any illnesses/disabilities
     * @param parentName The students parents name.
     * @param abilityLevel The ability level of the student.
     * @param swimmingClass The class the student is in.
     * @param attendance The attendance record of the student.
     * @throws Exception SQL Exception.
     */
    public void createStudentRecord(String studentName, Date studentDOB, int telephoneNo, String studentAddress,
            String hasIllness, String parentName, SwimmingLevel abilityLevel, SwimmingClasses swimmingClass,
            AttendanceRecord attendance) throws Exception {
        // Create an instance of a Student Record.
        studentRecord = new StudentRecord(studentName, studentDOB, telephoneNo, studentAddress, hasIllness, parentName, abilityLevel, swimmingClass, attendance);

        // Presist the Student Record object to the database.
        studentRecordDAO.create(studentRecord);
    }

    /**
     * Updates a Student Record in the student record database table.
     *
     * @param studentId The unique student ID of the student record to be updated.
     * @param studentName The updated name of the student.
     * @param studentDOB The updated date of birth of the student.
     * @param telephoneNo The updated telephone number of the student.
     * @param studentAddress The updated home address of the student.
     * @param hasIllness The updated information as to whether the student has any illnesses/disabilities.
     * @param parentName The updated name of the students parent.
     * @param abilityLevel The updated ability level of the student.
     * @param swimmingClass The updated swimming class of the student.
     * @param attendance The updated attendance record of the student.
     * @throws Exception SQL Exception.
     */
    public void updateStudentRecord(int studentId, String studentName, Date studentDOB, int telephoneNo, String studentAddress,
            String hasIllness, String parentName, SwimmingLevel abilityLevel, SwimmingClasses swimmingClass,
            AttendanceRecord attendance) throws Exception {
        // Retrieves the desired Student Record from the database.
        studentRecord = studentRecordDAO.queryForId(studentId);

        // If the student record object isnt null then the new updated student record values are set and updated.
        if (studentRecord != null) {
            studentRecord.setStudentName(studentName);
            studentRecord.setStudentDOB(studentDOB);
            studentRecord.setTelephoneNo(telephoneNo);
            studentRecord.setStudentAddress(studentAddress);
            studentRecord.setHasIllness(hasIllness);
            studentRecord.setParentName(parentName);
            studentRecord.setAbilityLevel(abilityLevel);
            studentRecord.setSwimmingClass(swimmingClass);
            studentRecord.setAttendance(attendance);
            studentRecordDAO.update(studentRecord);
        }
    }
    
    /**
     * Deletes a specified Student Record from the Student Record database table.
     * @param studentId The unique Id of the specified Student Record to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteStudentRecord(int studentId) throws Exception{
        // Retrieves the desired lesson payment.
        studentRecord = studentRecordDAO.queryForId(studentId);

        // Deletes the desired Lesson Payment from the Lesson Payment table in the database.
        studentRecordDAO.delete(studentRecord);
    }
}
