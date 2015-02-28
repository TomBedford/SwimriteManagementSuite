package uk.ac.tees.m2081433.swimritemanagementsuite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Swimming Classes database table showing all classes running at
 * Swimrite Leisure, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 */
@DatabaseTable(tableName = "SwimmingClasses")
public class SwimmingClasses implements Comparable<SwimmingClasses> {
    
    /**
     * The column name for the Class ID.
     */
    public static final String CLASSID_COLUMN_NAME = "classId";
    
    /**
     * The column name for the Class Type.
     */
    public static final String CLASSTYPE_COLUMN_NAME = "classType";
    
    /**
     * The column name for the timeslot.
     */
    public static final String TIMESLOT_COLUMN_NAME = "timeslot";
    
    /**
     * The column name for the Class TeacherId.
     */
    public static final String TEACHER_COLUMN_NAME = "teacher";
    
    /**
     * The column name for the maximum capacity.
     */
    public static final String MAXCAPACITY_COLUMN_NAME = "maxCapacity";
    
    /**
     * The column name for the current capacity.
     */
    public static final String CURRENTCAPACITY_COLUMN_NAME = "currentCapacity";
    
    
    
    /**
     * Primary Key: Auto generated Class ID number.
     */
    @DatabaseField(columnName = CLASSID_COLUMN_NAME, generatedId = true)
    private int classId;
    
    /**
     * The type of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = CLASSTYPE_COLUMN_NAME, canBeNull = false)
    private SwimmingLevel classType;
    
    /**
     * Foreign Key: The timeslot (day/time) of the class.
     */
    @DatabaseField(columnName = TIMESLOT_COLUMN_NAME, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Timeslot timeslot;
    
    /**
     * Foreign Key: The teacher of the class.
     */
    @DatabaseField(columnName = TEACHER_COLUMN_NAME, canBeNull = false, foreign = true)
    private Teacher teacher;
    
    /**
     * The maximum capacity of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = MAXCAPACITY_COLUMN_NAME, canBeNull = false)
    private int maxCapacity;
    
    /**
     * The current capacity of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = CURRENTCAPACITY_COLUMN_NAME, canBeNull = false)
    private int currentCapacity;
    
    
    
    /**
     * Default constructor of the Classes class.
     */
    public SwimmingClasses() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new Class in the Classes database table.
     * @param classType The type of swimming class.
     * @param timeslot The timeslot (day/time) of the class each week.
     * @param teacher The teacher of the class.
     * @param maxCapacity The maximum capacity of the swimming class.
     */
    public SwimmingClasses(SwimmingLevel classType, Timeslot timeslot, Teacher teacher, int maxCapacity) {
        this.classType = classType;
        this.timeslot = timeslot;
        this.teacher = teacher;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
    }
    
    

    /**
     * Accessor to retrieve the auto generated class Id.
     * @return classId The auto generated class Id.
     */
    public int getClassId() {
        return classId;
    }
    
    /**
     * Accessor to retrieve the current type of swimming class.
     * @return classType The type of class.
     */
    public SwimmingLevel getClassType() {
        return classType;
    }

    /**
     * Mutator to set the new swimming class type.
     * @param classType The updated type of the swimming class.
     */
    public void setClassType(SwimmingLevel classType) {
        this.classType = classType;
    }

    /**
     * Accessor to retrieve the current timeslot of the class.
     * @return timeslot The timeslot of the class.
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Mutator to set the new timeslot of the class.
     * @param timeslot The updated timeslot of the class.
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Accessor to retrieve the current teacher of the class.
     * @return teacher The teacher of the class.
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Mutator to set the new Teacher of the class.
     * @param teacher The updated teacher of the class.
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Accessor to retrieve the current maximum capacity of the class.
     * @return maxCapacity The maximum capacity of the class.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Mutator to set the new maximum capacity of the class.
     * @param maxCapacity The updated maximum capacity of the class. 
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Accessor to retrieve the current current capacity of the class.
     * @return currentCapacity The current capacity of the class.
     */
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * Mutator to set the new current capacity of the class.
     * @param currentCapacity The updated current capacity of the class.
     */
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    
    

    /**
     * Overided method to compare and sort Swimming Classes by their Teachers ID (in ascending order).
     * @param o The swimming class to compare against.
     * @return Int - Result changes depending on the outcome of the comparison.
     */
    @Override
    public int compareTo(SwimmingClasses o) {
        // Compare the teacher id of this swimming class with the one passed in.
        if (this.getTeacher().getTeacherId() > o.getTeacher().getTeacherId()) {
            // If this swimming classes teachers id is larger return 1.
            return 1;
         
        // If the teacher id of this swimming class is equal to the one passed in return 0.
        } else if (this.getTeacher().getTeacherId() == o.getTeacher().getTeacherId()) {
            return 0;
            
        // Otherwise the teacher id passed in is larger so return -1.
        } else {
            return -1;
        } 
    }
    
}