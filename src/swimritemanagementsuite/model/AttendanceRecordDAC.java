package swimritemanagementsuite.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author Bedford
 */
public class AttendanceRecordDAC {
    
    /**
     * The data access object used to access the Attendance Record table in the mySQL database.
     */
    private Dao<AttendanceRecord, Integer> attendanceRecordDAO;
    
    /**
     * Attendance Record object used for creating, updating and deleting attendance records from the database.
     */
    AttendanceRecord attendanceRecord;
    
    public AttendanceRecordDAC() {
        
    }
    
    public AttendanceRecordDAC(ConnectionSource connectionSource) {
        try {
            attendanceRecordDAO = DaoManager.createDao(connectionSource, AttendanceRecord.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the Attendance Record database access objects");
        }
    }

    // CREATE, UPDATE AND DELETE FOR THE ATTENDANCE RECORD DATABASE TABLE.
    /**
     * Creates an Attendance Record and persists it to the database.
     *
     * @throws Exception SQL Exception.
     */
    public void createAttendanceRecord() throws Exception {
        // Create an instance of an Attendance Record
        attendanceRecord = new AttendanceRecord();

        // Persist the Attendance Record object to the database.
        attendanceRecordDAO.create(attendanceRecord);
    }

    /**
     *
     * TODO: UPDATE THE TABLE TO HAVE MORE LESSON BLOCK ROWS RATHER THAN REPLACEMENT.
     *
     * Updates a specific Attendance Record within the attendance record database table.
     *
     * @param attendanceId The Attendance Record to update.
     * @param lessonBlock The new lesson block to be added to the Attendance Record.
     * @throws Exception SQL Exception.
     */
    public void updateAttendanceRecord(int attendanceId, LessonBlock lessonBlock) throws Exception {
        // Retrieves the desired attendance record.
        attendanceRecord = attendanceRecordDAO.queryForId(attendanceId);

        // If the attendance record object isnt null then the new lesson block is set and the database is updated.
        if (attendanceRecord != null) {
            attendanceRecord.setLessonBlock(lessonBlock);
            attendanceRecordDAO.update(attendanceRecord);
        }

    }

    /**
     * Deletes a specified Attendance Record from the attendance record database table.
     *
     * @param attendanceId the attendance record to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteAttendanceRecord(int attendanceId) throws Exception {
        // Retrieves the desired attendance record.
        attendanceRecord = attendanceRecordDAO.queryForId(attendanceId);

        // Deletes the desired attendance record.
        attendanceRecordDAO.delete(attendanceRecord);
    }
}
