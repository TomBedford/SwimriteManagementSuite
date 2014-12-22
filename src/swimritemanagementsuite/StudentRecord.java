package swimritemanagementsuite;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 * This class represents the Student Record database table holding student private 
 * information etc, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "StudentRecord")
public class StudentRecord {
    
    /**
     * Primary Key: Auto generated student record ID number.
     */
    @DatabaseField(columnName = "studentId", generatedId = true)
    private int studentId;
    
    /**
     * The name of the student (field cannot be null).
     */
    @DatabaseField (columnName = "studentName", canBeNull = false)
    private String studentName;
    
    /**
     * The date of birth of the student (field cannot be null).
     */
    @DatabaseField (columnName = "studentDOB", canBeNull = false)
    private Date studentDOB;
    
    /**
     * The telephone number of the student (field cannot be null).
     */
    @DatabaseField (columnName = "telephoneNo", canBeNull = false)
    private int telephoneNo;
    
    /**
     * The address of the student (field cannot be null).
     */
    @DatabaseField (columnName = "studentAddress", canBeNull = false)
    private String studentAddress;
    
    /**
     * String containing information as to whether the student has any 
     * Illnesses/disabilities.
     */
    @DatabaseField (columnName = "hasIllness")
    private String hasIllness;
    
    /**
     * The students parents name (field cannot be null).
     */
    @DatabaseField (columnName = "parentName", canBeNull = false)
    private String parentName;
    
    /**
     * The ability level of the student.
     */
    @DatabaseField (columnName = "abilityLevel")
    private String abilityLevel;
    
    /**
     * The class that the student is currently in.
     */
    @DatabaseField(columnName = "classId", foreign = true)
    private int classId;
    
    /**
     * The attendance record of the student (field cannot be null).
     */
    @DatabaseField(columnName = "attendanceId", foreign = true)
    private int attendanceId;

    /**
     * Default constructor of the Student Record class.
     */
    public StudentRecord() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new Student Record in the Student Record database table.
     * @param studentName The name of the student.
     * @param studentDOB The date of birth of the student.
     * @param telephoneNo The telephone number of the student.
     * @param studentAddress The address of the student.
     * @param hasIllness Information as to whether the student has any illnesses/disabilities
     * @param parentName The students parents name.
     * @param abilityLevel The ability level of the student.
     * @param classId The class the student is in.
     * @param attendanceId The attendance ID of the student.
     */
    public StudentRecord(String studentName, Date studentDOB, int telephoneNo, String studentAddress, 
                            String hasIllness, String parentName, String abilityLevel, int classId, int attendanceId) {
        this.studentName = studentName;
        this.studentDOB = studentDOB;
        this.telephoneNo = telephoneNo;
        this.studentAddress = studentAddress;
        this.hasIllness = hasIllness;
        this.parentName = parentName;
        this.abilityLevel = abilityLevel;
        this.classId = classId;
        this.attendanceId = attendanceId;
    }

    /**
     * Accessor to retrieve the auto generated Student Id.
     * @return studentId The auto generated Student Id.
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Accessor to retrieve the students name.
     * @return studentName The name of the student.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Mutator to set the new name of the student.
     * @param studentName The updated name of the student.
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Accessor to retrieve the students date of birth.
     * @return studentDOB the date of birth of the student.
     */
    public Date getStudentDOB() {
        return studentDOB;
    }

    /**
     * Mutator to set the new date of birth of the student.
     * @param studentDOB The updated date of birth of the student.
     */
    public void setStudentDOB(Date studentDOB) {
        this.studentDOB = studentDOB;
    }

    /**
     * Accessor to retrieve the telephone number of the student.
     * @return telephoneNo The telephone number of the student.
     */
    public int getTelephoneNo() {
        return telephoneNo;
    }

    /**
     * Mutator to set the new telephone number of the student.
     * @param telephoneNo The updated telephone number of the student.
     */
    public void setTelephoneNo(int telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    /**
     * Accessor to retrieve the address of the student.
     * @return studentAddress The address of the student.
     */
    public String getStudentAddress() {
        return studentAddress;
    }

    /**
     * Mutator to set the new address of the student.
     * @param studentAddress The updated address of the student.
     */
    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    /**
     * Accessor to retrieve the illness/disability status of the student.
     * @return hasIllness The string containing whether the student has any illnesses or disabilities.
     */
    public String getHasIllness() {
        return hasIllness;
    }

    /**
     * Mutator to set the new illness/disability status of the student.
     * @param hasIllness The updated illness/disability status of the student.
     */
    public void setHasIllness(String hasIllness) {
        this.hasIllness = hasIllness;
    }

    /**
     * Accessor to retrieve the students parents name.
     * @return parentName The students parents name.
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * Mutator to set the new parents name of the student.
     * @param parentName The updated parents name of the student.
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * Accessor to retrieve the ability level of the student.
     * @return abilityLevel The students ability level.
     */
    public String getAbilityLevel() {
        return abilityLevel;
    }

    /**
     * Mutator to set the new ability level of the student.
     * @param abilityLevel The updated ability level of the student.
     */
    public void setAbilityLevel(String abilityLevel) {
        this.abilityLevel = abilityLevel;
    }

    /**
     * Accessor to retrieve the class Id of the student. 
     * @return classId The class the student is in.
     */
    public int getClassId() {
        return classId;
    }

    /**
     * Mutator to set the new class Id of the student.
     * @param classId The updated class Id of the student.
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * Accessor to retrieve the students attendance Id.
     * @return attendanceID The link to the attendance record of the student.
     */
    public int getAttendanceId() {
        return attendanceId;
    }

    /**
     * Mutator to set the new attendance Id of the student.
     * @param attendanceId The updated attendance Id of the student.
     */
    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

}