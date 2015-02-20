package swimritemanagementsuite.model;
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
public class Teacher {
    
    /**
     * Primary Key: Auto generated Teacher ID number.
     */
    @DatabaseField(columnName = "teacherId", generatedId = true)
    private int teacherId;
    
    /**
     * The name of the Teacher (field cannot be null and must be unique).
     */
    @DatabaseField (columnName = "teacherName", canBeNull = false, unique = true)
    private String teacherName;
    
    @DatabaseField (columnName = "workMonday", canBeNull = false)
    private Boolean workMonday;
    
    @DatabaseField (columnName = "workTuesday", canBeNull = false)
    private Boolean workTuesday;
    
    @DatabaseField (columnName = "workWednesday", canBeNull = false)
    private Boolean workWednesday;
    
    @DatabaseField (columnName = "workThursday", canBeNull = false)
    private Boolean workThursday;
    
    @DatabaseField (columnName = "workFriday", canBeNull = false)
    private Boolean workFriday;
    
    @DatabaseField (columnName = "workSaturday", canBeNull = false)
    private Boolean workSaturday;
    
    @DatabaseField (columnName = "workSunday", canBeNull = false)
    private Boolean workSunday;
    
    public static final String WORKMONDAY_FIELD_NAME = "workMonday"; 
    
    public static final String WORKTUESDAY_FIELD_NAME = "workTuesday"; 
    
    public static final String WORKWEDNESDAY_FIELD_NAME = "workWednesday"; 
    
    public static final String WORKTHURSDAY_FIELD_NAME = "workThursday"; 
    
    public static final String WORKFRIDAY_FIELD_NAME = "workFriday"; 
    
    public static final String WORKSATURDAY_FIELD_NAME = "workSaturday"; 
    
    public static final String WORKSUNDAY_FIELD_NAME = "workSunday"; 
    
    

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
}
