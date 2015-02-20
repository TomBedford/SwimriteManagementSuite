package swimritemanagementsuite.model;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Timeslot database table showing different timeslots
 * available for classes, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "Timeslot")
public class Timeslot {
    
    /**
     * Primary Key: Auto generated Timeslot ID number.
     */
    @DatabaseField(columnName = "timeslotId", generatedId = true)
    private int timeslotId;
    
    /**
     * The day of the timeslot (field cannot be null).
     */
    @DatabaseField (columnName = "day", canBeNull = false)
    private Day day;
    
    /**
     * The time of the timeslot (field cannot be null).
     */
    @DatabaseField (columnName = "time", canBeNull = false)
    private int time;
    
    public static final String DAY_FIELD_NAME = "day"; 

    /**
     * Default constructor of the timeslot class.
     */
    public Timeslot() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new Timeslot in the Timeslot database table.
     * @param day The day of the timeslot.
     * @param time The time of the timeslot.
     */
    public Timeslot(Day day, int time) {
        this.day = day;
        this.time = time;
    }

    /**
     * Accessor to retrieve the auto generated timeslot Id.
     * @return timeslotId The auto generated timeslot Id.
     */
    public int getTimeslotId() {
        return timeslotId;
    }

    /**
     * Accessor to retrieve the day of the timeslot.
     * @return day The day the timeslot is on.
     */
    public Day getDay() {
        return day;
    }

    /**
     * Mutator to set the new day of the timeslot.
     * @param day The updated day of the timeslot.
     */
    public void setDay(Day day) {
        this.day = day;
    }

    /**
     * Accessor to retrieve the time of the timeslot.
     * @return time The time of the timeslot
     */
    public int getTime() {
        return time;
    }

    /**
     * Mutator to set the new time of the timeslot.
     * @param time The updared time of the timeslot.
     */
    public void setTime(int time) {
        this.time = time;
    }

}