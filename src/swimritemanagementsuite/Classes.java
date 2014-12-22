package swimritemanagementsuite;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Classes database table showing all classes running at 
 * Swimrite Leisure, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "Classes")
public class Classes {
    
    /**
     * Primary Key: Auto generated Class ID number.
     */
    @DatabaseField(columnName = "Class Id", generatedId = true)
    private int classId;
    
    /**
     * The type of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = "Class Type", canBeNull = false)
    private String classType;
    
    
    /**
     * Foreign Key: The timeslot (day/time) of the class.
     */
    @DatabaseField(columnName = "Timeslot Id", foreign = true)
    private int timeslotId;
    
    /**
     * Foreign Key: The teacher of the class.
     */
    @DatabaseField(columnName = "Teacher Id", foreign = true)
    private int teacherId;
    
    /**
     * The maximum capacity of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = "Maximum Capacity", canBeNull = false)
    private int maxCapacity;
    
    /**
     * The current capacity of swimming class (field cannot be null).
     */
    @DatabaseField (columnName = "Current Capacity", canBeNull = false)
    private int currentCapacity;

    /**
     * Default constructor of the Classes class.
     */
    public Classes() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new Class in the Classes database.
     * @param classType The type of swimming class.
     * @param timeslotId The timeslot (day/time) of the class each week.
     * @param teacherId The teacher of the class.
     * @param maxCapacity The maxmimum capacity of the swimming class.
     * @param currentCapacity The current capacity of the swimming class.
     */
    public Classes(String classType, int timeslotId, int teacherId, int maxCapacity, int currentCapacity) {
        this.classType = classType;
        this.timeslotId = timeslotId;
        this.teacherId = teacherId;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
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
     * @return timeslotId The timeslot of the class.
     */
    public int getTimeslotId() {
        return timeslotId;
    }

    /**
     * Mutator to set the new timeslot of the class.
     * @param timeslotId The updated timeslot of the class.
     */
    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    /**
     * Accessor to retrieve the current teacher Id of the class.
     * @return teacherId The teacher of the class.
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * Mutator to set the new Teacher Id of the class.
     * @param teacherId The updated teacherId of the class.
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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