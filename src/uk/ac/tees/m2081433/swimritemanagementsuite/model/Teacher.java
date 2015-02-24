package uk.ac.tees.m2081433.swimritemanagementsuite.model;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Teacher database table showing teachers working at 
 * Swimrite Leisure, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "Teacher")
public class Teacher implements Comparable<Teacher>{
    
    /**
     * The column name for the Teacher ID.
     */
    public static final String TEACHERID_COLUMN_NAME = "teacherId";
    
    /**
     * The column name for the Teachers Name.
     */
    public static final String TEACHERNAME_COLUMN_NAME = "teacherName";
    
    /**
     * The column name for Work Monday.
     */
    public static final String WORKMONDAY_COLUMN_NAME = "workMonday"; 
    
    /**
     * The column name for the Work Tuesday.
     */
    public static final String WORKTUESDAY_COLUMN_NAME = "workTuesday"; 
    
    /**
     * The column name for the Work Wednesday.
     */
    public static final String WORKWEDNESDAY_COLUMN_NAME = "workWednesday"; 
    
    /**
     * The column name for the Work Thursday.
     */
    public static final String WORKTHURSDAY_COLUMN_NAME = "workThursday"; 
    
    /**
     * The column name for the Work Friday.
     */
    public static final String WORKFRIDAY_COLUMN_NAME = "workFriday"; 
    
    /**
     * The column name for the Work Saturday.
     */
    public static final String WORKSATURDAY_COLUMN_NAME = "workSaturday"; 
    
    /**
     * The column name for the Work Sunday.
     */
    public static final String WORKSUNDAY_COLUMN_NAME = "workSunday"; 
    
    
    /**
     * Primary Key: Auto generated Teacher ID number.
     */
    @DatabaseField(columnName = TEACHERID_COLUMN_NAME, generatedId = true)
    private int teacherId;
    
    /**
     * The name of the Teacher (field cannot be null and must be unique).
     */
    @DatabaseField (columnName = TEACHERNAME_COLUMN_NAME, canBeNull = false, unique = true)
    private String teacherName;
    
    /**
     * A Boolean field that specifies whether that teacher works on Monday or not.
     */
    @DatabaseField (columnName = WORKMONDAY_COLUMN_NAME, canBeNull = false)
    private Boolean workMonday;
    
    /**
     * A Boolean field that specifies whether that teacher works on Tuesday or not.
     */
    @DatabaseField (columnName = WORKTUESDAY_COLUMN_NAME, canBeNull = false)
    private Boolean workTuesday;
    
    /**
     * A Boolean field that specifies whether that teacher works on Wednesday or not.
     */
    @DatabaseField (columnName = WORKWEDNESDAY_COLUMN_NAME, canBeNull = false)
    private Boolean workWednesday;
    
    /**
     * A Boolean field that specifies whether that teacher works on Thursday or not.
     */
    @DatabaseField (columnName = WORKTHURSDAY_COLUMN_NAME, canBeNull = false)
    private Boolean workThursday;
    
    /**
     * A Boolean field that specifies whether that teacher works on Friday or not.
     */
    @DatabaseField (columnName = WORKFRIDAY_COLUMN_NAME, canBeNull = false)
    private Boolean workFriday;
    
    /**
     * A Boolean field that specifies whether that teacher works on Saturday or not.
     */
    @DatabaseField (columnName = WORKSATURDAY_COLUMN_NAME, canBeNull = false)
    private Boolean workSaturday;
    
    /**
     * A Boolean field that specifies whether that teacher works on Sunday or not.
     */
    @DatabaseField (columnName = WORKSUNDAY_COLUMN_NAME, canBeNull = false)
    private Boolean workSunday;    
    
    /**
     * Default constructor of the Teacher class.
     */
    public Teacher() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new Teacher in the Teacher database table.
     * @param teacherName The individual Teachers name.
     */
    public Teacher(String teacherName) {
        this.teacherName = teacherName;
        this.workMonday = false;
        this.workTuesday = false;
        this.workWednesday = false;
        this.workThursday = false;
        this.workFriday = false;
        this.workSaturday = false;
        this.workSunday = false;
    }

    /**
     * Accessor to retrieve the auto generated teacher Id.
     * @return teacherId The auto generated teachers Id.
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * Accessor to retrieve the teachers name.
     * @return teacherName The teachers name.
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Mutator to change the teachers name.
     * @param teacherName The teachers updated name. 
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Boolean getWorkMonday() {
        return workMonday;
    }

    public void setWorkMonday(Boolean workMonday) {
        this.workMonday = workMonday;
    }

    public Boolean getWorkTuesday() {
        return workTuesday;
    }

    public void setWorkTuesday(Boolean workTuesday) {
        this.workTuesday = workTuesday;
    }

    public Boolean getWorkWednesday() {
        return workWednesday;
    }

    public void setWorkWednesday(Boolean workWednesday) {
        this.workWednesday = workWednesday;
    }

    public Boolean getWorkThursday() {
        return workThursday;
    }

    public void setWorkThursday(Boolean workThursday) {
        this.workThursday = workThursday;
    }

    public Boolean getWorkFriday() {
        return workFriday;
    }

    public void setWorkFriday(Boolean workFriday) {
        this.workFriday = workFriday;
    }

    public Boolean getWorkSaturday() {
        return workSaturday;
    }

    public void setWorkSaturday(Boolean workSaturday) {
        this.workSaturday = workSaturday;
    }

    public Boolean getWorkSunday() {
        return workSunday;
    }

    public void setWorkSunday(Boolean workSunday) {
        this.workSunday = workSunday;
    }

    @Override
    public int compareTo(Teacher o) {
        // If the teacher id of this teacher is larger than the teacher params teacher id passed in return 1.
        if (this.getTeacherId() > o.getTeacherId()) {
            return 1;
        }    
        // If the teacher id of this teacher is equal to the teacher params teacher id passed in return 0.
        else if (this.getTeacherId() == o.getTeacherId()) {
            return 0;
        }   
        // Otherwise the teacher params teacher id passed in is larger so return -1.
        else {
            return -1;
        } 
    }
}
