/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Collection;

/**
 * This class represents the Teacher database table showing teachers working at 
 * Swimrite Leisure, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 */
@DatabaseTable(tableName = "Teacher")
public class Teacher implements Comparable<Teacher> {
    
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
     * Shows that a Teacher can be associated with multiple swimming classes in a 0 to many relationship.
     */
    @ForeignCollectionField
    private Collection<SwimmingClasses> swimmingClasses;
    
    
    
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

    /**
     * Accessor to retrieve the boolean as to whether the teacher works on Monday.
     * @return workMonday boolean as to whether the teacher works on Monday
     */
    public Boolean getWorkMonday() {
        return workMonday;
    }

    /**
     * Mutator to set whether a teacher works on Monday or not.
     * @param workMonday The new boolean as to whether they work on Monday or not.
     */
    public void setWorkMonday(Boolean workMonday) {
        this.workMonday = workMonday;
    }

    /**
     * Accessor to retrieve the boolean as to whether the teacher works on Tuesday.
     * @return workTuesday boolean as to whether the teacher works on Tuesday
     */
    public Boolean getWorkTuesday() {
        return workTuesday;
    }

    /**
     * Mutator to set whether a teacher works on Tuesday or not.
     * @param workTuesday The new boolean as to whether they work on Tuesday or not.
     */
    public void setWorkTuesday(Boolean workTuesday) {
        this.workTuesday = workTuesday;
    }

    /**
     * Accessor to retrieve the boolean as to whether the teacher works on Wednesday.
     * @return workWednesday boolean as to whether the teacher works on Wednesday
     */
    public Boolean getWorkWednesday() {
        return workWednesday;
    }

    /**
     * Mutator to set whether a teacher works on Wednesday or not.
     * @param workWednesday The new boolean as to whether they work on Wednesday or not.
     */
    public void setWorkWednesday(Boolean workWednesday) {
        this.workWednesday = workWednesday;
    }

    /**
     * Accessor to retrieve the boolean as to whether the teacher works on Thursday.
     * @return workThursday boolean as to whether the teacher works on Thursday.
     */
    public Boolean getWorkThursday() {
        return workThursday;
    }

    /**
     * Mutator to set whether a teacher works on Thursday or not.
     * @param workThursday The new boolean as to whether they work on Thursday or not.
     */
    public void setWorkThursday(Boolean workThursday) {
        this.workThursday = workThursday;
    }

    /**
     * Accessor to retrieve the boolean as to whether the teacher works on Friday.
     * @return workFriday boolean as to whether the teacher works on Friday
     */
    public Boolean getWorkFriday() {
        return workFriday;
    }

    /**
     * Mutator to set whether a teacher works on Friday or not.
     * @param workFriday The new boolean as to whether they work on Friday or not.
     */
    public void setWorkFriday(Boolean workFriday) {
        this.workFriday = workFriday;
    }

    /**
     * Accessor to retrieve the boolean as to whether the teacher works on Saturday.
     * @return workSaturday boolean as to whether the teacher works on Saturday
     */
    public Boolean getWorkSaturday() {
        return workSaturday;
    }

    /**
     * Mutator to set whether a teacher works on Saturday or not.
     * @param workSaturday The new boolean as to whether they work on Saturday or not.
     */
    public void setWorkSaturday(Boolean workSaturday) {
        this.workSaturday = workSaturday;
    }

    /**
     * Accessor to retrieve the boolean as to whether the teacher works on Sunday.
     * @return workSunday boolean as to whether the teacher works on Sunday
     */
    public Boolean getWorkSunday() {
        return workSunday;
    }

    /**
     * Mutator to set whether a teacher works on Sunday or not.
     * @param workSunday The new boolean as to whether they work on Sunday or not.
     */
    public void setWorkSunday(Boolean workSunday) {
        this.workSunday = workSunday;
    }
    
    /**
     * Accessor to retrieve the swimming classes associated with the teacher.
     * @return swimmingClasses The swimming classes the teacher is associated with
     */
    public Collection<SwimmingClasses> getSwimmingClasses() {
        return swimmingClasses;
    }

    /**
     * Mutator to set the new swimming classes associated with this teacher.
     * @param swimmingClasses The updated swimming classes for this teacher.
     */
    public void setSwimmingClasses(Collection<SwimmingClasses> swimmingClasses) {
        this.swimmingClasses = swimmingClasses;
    }
    
    
    
    /**
     * Overided method to compare and sort Teachers by their Teachers ID (in ascending order).
     * @param o The Teacher to compare against.
     * @return Int - Result changes depending on the outcome of the comparison.
     */
    @Override
    public int compareTo(Teacher o) {
        // If the teacher id of this teacher is larger than the teacher params teacher id passed in return 1.
        if (this.getTeacherId() > o.getTeacherId()) {
            return 1;
            
        // If the teacher id of this teacher is equal to the teacher params teacher id passed in return 0.
        } else if (this.getTeacherId() == o.getTeacherId()) {
            return 0;
           
        // Otherwise the teacher params teacher id passed in is larger so return -1.
        } else {
            return -1;
        } 
    }
}
