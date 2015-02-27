package uk.ac.tees.m2081433.swimritemanagementsuite.model;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Collection;

/**
 * This class represents the Timeslot database table showing different timeslots
 * available for classes, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "Timeslot")
public class Timeslot implements Comparable<Timeslot>{
    
    /**
     * The column name for the Timeslot ID.
     */
    public static final String TIMESLOTID_COLUMN_NAME = "timeslotId";
    
    /**
     * The column name for the Timeslot day.
     */
    public static final String DAY_COLUMN_NAME = "day";
    
    /**
     * The column name for the Class ID.
     */
    public static final String TIME_COLUMN_NAME = "time";
    
    /**
     * Primary Key: Auto generated Timeslot ID number.
     */
    @DatabaseField(columnName = TIMESLOTID_COLUMN_NAME, generatedId = true)
    private int timeslotId;
    
    /**
     * The day of the timeslot (field cannot be null).
     */
    @DatabaseField (columnName = DAY_COLUMN_NAME, canBeNull = false)
    private Day day;
    
    /**
     * The time of the timeslot (field cannot be null).
     */
    @DatabaseField (columnName = TIME_COLUMN_NAME, canBeNull = false)
    private int time;
    
    /**
     * Shows that a timeslot can be associated with multiple swimming classes in a 0 to many relationship.
     */
    @ForeignCollectionField
    private Collection<SwimmingClasses> swimmingClasses;

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
    
    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
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
     * @param time The updated time of the timeslot.
     */
    public void setTime(int time) {
        this.time = time;
    }
    
    /**
     * Accessor to retrieve the swimming classes associated with the timeslot.
     * @return swimmingClass The swimming classes associated with this timeslot.
     */
    public Collection<SwimmingClasses> getSwimmingClasses() {
        return swimmingClasses;
    }

    /**
     * Mutator to set the new swimming classes associated with this timeslot.
     * @param swimmingClasses The updated swimming classes for this timeslot.
     */
    public void setSwimmingClasses(Collection<SwimmingClasses> swimmingClasses) {
        this.swimmingClasses = swimmingClasses;
    }

    @Override
    public int compareTo(Timeslot o) {
        // If the time of this timeslot is larger than the timeslot params time passed in return 1.
        if (this.getTime() > o.getTime()) {
            return 1;
        }    
        // If the time of this timeslot is equal to the timeslot params time passed in return 0.
        else if (this.getTime() == o.getTime()) {
            return 0;
        }   
        // Otherwise the timeslot params time passed in is larger so return -1.
        else {
            return -1;
        } 
    }

}