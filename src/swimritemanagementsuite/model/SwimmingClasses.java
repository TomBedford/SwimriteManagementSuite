package swimritemanagementsuite.model;
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
     * The column name for the Class ID.
     */
    public static final String CLASSID_COLUMN_NAME = "classId";
    
    /**
     * The column name for the Class Type.
     */
    public static final String CLASSTYPE_COLUMN_NAME = "classType";
    
    /**
     * The column name for the timeslot ID.
     */
    public static final String TIMESLOTID_COLUMN_NAME = "timeslotId";
    
    /**
     * The column name for the Class TeacherId.
     */
    public static final String TEACHERID_COLUMN_NAME = "teacherId";
    
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
    @DatabaseField(columnName = TIMESLOTID_COLUMN_NAME, canBeNull = false, foreign = true)
    private Timeslot timeslotId;
    
    /**
     * Foreign Key: The teacher of the class.
     */
    @DatabaseField(columnName = TEACHERID_COLUMN_NAME, canBeNull = false, foreign = true)
    private Teacher teacherId;
    
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
     * @param timeslotId The timeslot (day/time) of the class each week.
     * @param teacherId The teacher of the class.
     * @param maxCapacity The maximum capacity of the swimming class.
     */
    public SwimmingClasses(SwimmingLevel classType, Timeslot timeslotId, Teacher teacherId, int maxCapacity) {
        this.classType = classType;
        this.timeslotId = timeslotId;
        this.teacherId = teacherId;
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
    public Timeslot getTimeslotId() {
        return timeslotId;
    }

    /**
     * Mutator to set the new timeslot of the class.
     * @param timeslotId The updated timeslot of the class.
     */
    public void setTimeslotId(Timeslot timeslotId) {
        this.timeslotId = timeslotId;
    }

    /**
     * Accessor to retrieve the current teacher of the class.
     * @return teacher The teacher of the class.
     */
    public Teacher getTeacherId() {
        return teacherId;
    }

    /**
     * Mutator to set the new Teacher of the class.
     * @param teacherId The updated teacher of the class.
     */
    public void setTeacherId(Teacher teacherId) {
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