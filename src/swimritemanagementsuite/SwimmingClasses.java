package swimritemanagementsuite;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Swimming Classes database table showing all classes running at 
 Swimrite Leisure, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "SwimmingClasses")
public class SwimmingClasses {
    
    /**
     * Primary Key: Auto generated Class ID number.
     */
    @DatabaseField(columnName = "classId", generatedId = true)
    private int classId;
    
    /**
     * The type of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = "classType", canBeNull = false)
    private String classType;
    
    
    /**
     * Foreign Key: The timeslot (day/time) of the class.
     */
    @DatabaseField(columnName = "timeslot", canBeNull = false, foreign = true)
    private Timeslot timeslot;
    
    /**
     * Foreign Key: The teacher of the class.
     */
    @DatabaseField(columnName = "teacher", canBeNull = false, foreign = true)
    private Teacher teacher;
    
    /**
     * The maximum capacity of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = "maxCapacity", canBeNull = false)
    private int maxCapacity;
    
    /**
     * The current capacity of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = "currentCapacity", canBeNull = false)
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
    public SwimmingClasses(String classType, Timeslot timeslot, Teacher teacher, int maxCapacity) {
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
    public String getClassType() {
        return classType;
    }

    /**
     * Mutator to set the new swimming class type.
     * @param classType The updated type of the swimming class.
     */
    public void setClassType(String classType) {
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
    
}