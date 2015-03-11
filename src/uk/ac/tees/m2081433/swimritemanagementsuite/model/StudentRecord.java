package uk.ac.tees.m2081433.swimritemanagementsuite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Collection;

/**
 * This class represents the Student Record database table holding student private 
 * information etc, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 */
@DatabaseTable(tableName = "StudentRecord")
public class StudentRecord {
    
    /**
     * The column name for the Student ID.
     */
    public static final String STUDENTID_COLUMN_NAME = "studentId";
    
    /**
     * The column name for the Student Name.
     */
    public static final String STUDENTNAME_COLUMN_NAME = "studentName";
    
    /**
     * The column name for the Students Date of Birth.
     */
    public static final String STUDENTDOB_COLUMN_NAME = "studentDOB";
    
    /**
     * The column name for the Students telephone number.
     */
    public static final String STUDENTTELEPHONENO_COLUMN_NAME = "telephoneNo";
    
    /**
     * The column name for the Students address.
     */
    public static final String STUDENTADDRESS_COLUMN_NAME = "studentAddress";
    
    /**
     * The column name for the Students has illness.
     */
    public static final String HASILLNESS_COLUMN_NAME = "hasIllness";
    
    /**
     * The column name for the Students parents name.
     */
    public static final String PARENTNAME_COLUMN_NAME = "parentName";
    
    /**
     * The column name for the Students swimming level.
     */
    public static final String SWIMMINGLEVEL_COLUMN_NAME = "abilityLevel";
    
    /**
     * The column name for the Students swimming class.
     */
    public static final String STUDENTSWIMMINGCLASS_COLUMN_NAME = "swimmingClass";
    
    /**
     * The column name for the Lesson Block Id.
     */
    public static final String LESSONBLOCK_COLUMN_NAME = "lessonBlock"; 
    
    
    
    /**
     * Primary Key: Auto generated student record ID number.
     */
    @DatabaseField(columnName = STUDENTID_COLUMN_NAME, generatedId = true)
    private int studentId;
    
    /**
     * The name of the student (field cannot be null).
     */
    @DatabaseField (columnName = STUDENTNAME_COLUMN_NAME, canBeNull = false)
    private String studentName;
    
    /**
     * The date of birth of the student (field cannot be null).
     */
    @DatabaseField (columnName = STUDENTDOB_COLUMN_NAME, canBeNull = false)
    private String studentDOB;
    
    /**
     * The telephone number of the student (field cannot be null).
     */
    @DatabaseField (columnName = STUDENTTELEPHONENO_COLUMN_NAME, canBeNull = false)
    private String telephoneNo;
    
    /**
     * The address of the student (field cannot be null).
     */
    @DatabaseField (columnName = STUDENTADDRESS_COLUMN_NAME, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    StudentAddress studentAddress;
    
    /**
     * String containing information as to whether the student has any 
     * Illnesses/disabilities.
     */
    @DatabaseField (columnName = HASILLNESS_COLUMN_NAME, canBeNull = false)
    private String hasIllness;
    
    /**
     * The students parents name (field cannot be null).
     */
    @DatabaseField (columnName = PARENTNAME_COLUMN_NAME, canBeNull = false)
    private String parentName;
    
    /**
     * The ability level of the student.
     */
    @DatabaseField (columnName = SWIMMINGLEVEL_COLUMN_NAME, canBeNull = false)
    private SwimmingLevel swimmingLevel;
    
    /**
     * Foreign Key: The swimming class that the student is currently in.
     */
    @DatabaseField(columnName = STUDENTSWIMMINGCLASS_COLUMN_NAME, foreign = true, foreignAutoRefresh = true)
    private SwimmingClasses swimmingClass;
    
    /**
     * Foreign collection field: Shows that a student record can have 0 - many lesson blocks. 
     */
    @ForeignCollectionField
    private Collection<LessonBlock> lessonBlock;
    
    

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
     * @param studentAddress The home address of the student.
     * @param hasIllness Information as to whether the student has any illnesses/disabilities
     * @param parentName The students parents name.
     * @param swimmingLevel The swimming/ability level of the student.
     */
    public StudentRecord(String studentName, String studentDOB, String telephoneNo, StudentAddress studentAddress, 
                            String hasIllness, String parentName, SwimmingLevel swimmingLevel) {
        this.studentName = studentName;
        this.studentDOB = studentDOB;
        this.telephoneNo = telephoneNo;
        this.studentAddress = studentAddress;
        this.hasIllness = hasIllness;
        this.parentName = parentName;
        this.swimmingLevel = swimmingLevel;
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
     * Accessor to retrieve the date of birth of the student.
     * @return studentDOB The date of birth of the student
     */
    public String getStudentDOB() {
        return studentDOB;
    }

    /**
     * Mutator to set the new date of birth of the student.
     * @param studentDOB The updated date of birth of the student.
     */
    public void setStudentDOB(String studentDOB) {
        this.studentDOB = studentDOB;
    }

    /**
     * Accessor to retrieve the telephone number of the student.
     * @return telephoneNo The telephone number of the student.
     */
    public String getTelephoneNo() {
        return telephoneNo;
    }

    /**
     * Mutator to set the new telephone number of the student.
     * @param telephoneNo The updated telephone number of the student.
     */
    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    /**
     * Accessor to retrieve the address of the student.
     * @return studentAddress The address of the student.
     */
    public StudentAddress getStudentAddress() {
        return studentAddress;
    }

    /**
     * Mutator to set the new address of the student.
     * @param studentAddress The updated address of the student.
     */
    public void setStudentAddress(StudentAddress studentAddress) {
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
     * Accessor to retrieve the swimming/ability level of the student.
     * @return abilityLevel The students ability level.
     */
    public SwimmingLevel getSwimmingLevel() {
        return swimmingLevel;
    }

    /**
     * Mutator to set the new swimming/ability level of the student.
     * @param swimmingLevel The updated swimming level of the student.
     */
    public void setSwimmingLevel(SwimmingLevel swimmingLevel) {
        this.swimmingLevel = swimmingLevel;
    }
    
    /**
     * Accessor to retrieve the class of the student. 
     * @return swimmingClass The swimming class the student is in.
     */
    public SwimmingClasses getSwimmingClass() {
        return swimmingClass;
    }

    /**
     * Mutator to set the new class of the student.
     * @param swimmingClass The updated class of the student.
     */
    public void setSwimmingClass(SwimmingClasses swimmingClass) {
        this.swimmingClass = swimmingClass;
    }

    /**
     * Accessor to retrieve the lesson blocks of the student. 
     * @return lessonBlock The lesson block of the student.
     */
    public Collection<LessonBlock> getLessonBlock() {
        return lessonBlock;
    }

    /**
     * Mutator to set the new lesson blocks of the student.
     * @param lessonBlock The updated lesson block of the student.
     */
    public void setLessonBlock(Collection<LessonBlock> lessonBlock) {
        this.lessonBlock = lessonBlock;
    }
}