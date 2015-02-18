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

}
