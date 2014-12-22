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
     * Foreign Key: The lesson block id of the attendance record.
     */
    @DatabaseField (columnName = "blockId", foreign = true)
    private int blockId;

    /**
     * Default constructor of the Attendance Record class.
     */
    public AttendanceRecord() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new attendance record in the attendance record database table.
     * @param blockId The lesson block Id.
     */
    public AttendanceRecord(int blockId) {
        this.blockId = blockId;
    }

    /**
     * Accessor to retrieve the auto generated attendance record Id.
     * @return attendanceId The auto generated attendance record Id.
     */
    public int getAttendanceId() {
        return attendanceId;
    }

    /**
     * Accessor to retrieve the lesson block Id.
     * @return blockId The lesson block Id.
     */
    public int getBlockId() {
        return blockId;
    }

    /**
     * Mutator to set the new lesson block Id.
     * @param blockId The updated lesson block Id.
     */
    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

}