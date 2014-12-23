package swimritemanagementsuite;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the attendance record database table mapping students to
 * their lesson blocks, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "AttendanceRecord")
public class AttendanceRecord {
    
    /**
     * Primary Key: Auto generated Attendance Record ID number.
     */
    @DatabaseField(columnName = "attendanceId", generatedId = true)
    private int attendanceId;
    
    /**
     * Foreign Key: The lesson block of the attendance record.
     */
    @DatabaseField (columnName = "lessonBlock", foreign = true)
    private LessonBlock lessonBlock;

    /**
     * Default constructor of the Attendance Record class.
     */
    public AttendanceRecord() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new attendance record in the attendance record database table.
     * @param lessonBlock The lesson block.
     */
    public AttendanceRecord(LessonBlock lessonBlock) {
        this.lessonBlock = lessonBlock;
    }

    /**
     * Accessor to retrieve the auto generated attendance record Id.
     * @return attendanceId The auto generated attendance record Id.
     */
    public int getAttendanceId() {
        return attendanceId;
    }

    /**
     * Accessor to retrieve the lesson block.
     * @return lessonBlock The lesson block.
     */
    public LessonBlock getLessonBlock() {
        return lessonBlock;
    }

    /**
     * Mutator to set the new lesson block.
     * @param lessonBlock The updated lesson block.
     */
    public void setLessonBlock(LessonBlock lessonBlock) {
        this.lessonBlock = lessonBlock;
    }

}