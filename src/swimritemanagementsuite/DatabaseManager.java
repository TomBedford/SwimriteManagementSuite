package swimritemanagementsuite;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 * This class manages the access to the database and database creation and maintenance. 
 * 
 * @author Thomas Bedford (m2081433)
 */
public class DatabaseManager {
    
    // DATABASE ACCESS OBJECTS FOR EACH DATABASE TABLE.
    
    /**
     * The URL that links to the Online MySQL Swimrite Management Suite database.
     */
    private final static String DATABASE_URL = "jdbc:mysql://my-database-host/my-database-name";
    
    /**
     * The data access object used to access the Attendance Record table in the mySQL database.
     */
    private Dao<AttendanceRecord, Integer> attendanceRecordDao;
    
    /**
     * The data access object used to access the Swimming Classes table in the mySQL database.
     */
    private Dao<SwimmingClasses, Integer> swimmingClassDao;
    
    /**
     * The data access object used to access the Lesson Block table in the mySQL database.
     */
    private Dao<LessonBlock, Integer> lessonBlockDao;
    
    /**
     * The data access object used to access the Lesson Payment table in the mySQL database.
     */
    private Dao<LessonPayment, Integer> lessonPaymentDao;
    
    /**
     * The data access object used to access the Student Record table in the mySQL database.
     */
    private Dao<StudentRecord, Integer> studentRecordDao;
    
    /**
     * The data access object used to access the Teacher table in the mySQL database.
     */
    private Dao<Teacher, Integer> teacherDao;
    
    /**
     * The data access object used to access the Timeslot table in the mySQL database.
     */
    private Dao<Timeslot, Integer> timeslotDao;
    
    
    
    // DATABASE TABLE OBJECTS
    
    /**
     * Attendance Record object used for creating, updating and deleting attendance records from the database.
     */
    AttendanceRecord attendanceRecord;
    
    /**
     * Swimming Classes object used for creating, updating and deleting swimming classes from the database.
     */
    SwimmingClasses swimmingClass;
    
    /**
     * Lesson Block object used for creating, updating and deleting lesson blocks from the database.
     */
    LessonBlock lessonBlock;
    
    /**
     * Lesson Payment object used for creating, updating and deleting lesson payments from the database.
     */
    LessonPayment lessonPayment;
    
    /**
     * Student Record object used for creating, updating and deleting student records from the database.
     */
    StudentRecord studentRecord;
    
    /**
     * Teacher object used for creating, updating and deleting teachers from the database.
     */
    Teacher teacher;
    
    /**
     * Timeslot object used for creating, updating and deleting timeslots from the database.
     */
    Timeslot timeslot;
    
    
    
    /**
     * Default constructor of the database manager class.
     */
    public DatabaseManager() {
        
    }
    
    /**
     * Creates and returns the database JDBC connection source that links to the
     * Swimrite Management Suite online MySQL database.
     * @return connectionSource This is the JDBC connection source that leads to the MySQL database.
     */
    public JdbcConnectionSource createDatabaseConnection() {
        JdbcConnectionSource connectionSource = null;
            try {
                // create our data source
                connectionSource = new JdbcConnectionSource(DATABASE_URL);
            } catch (SQLException e) {
                // print stack trace to help diagnose error + appropriate message to console.
                e.printStackTrace();
                System.out.println("createDatabaseConnection: Error establishing connection source");
            }
            
            return connectionSource;
    }
    
    /**
     * Sets up the database if tables have not already been initialized.
     * @param connectionSource The connection to the Swimrite Management Suite MySQL database 
     */
    public void setupDatabase(JdbcConnectionSource connectionSource) {
        
        try {
            //Creates all tables within the database if they do not already exist.
            TableUtils.createTableIfNotExists(connectionSource, AttendanceRecord.class);
            TableUtils.createTableIfNotExists(connectionSource, SwimmingClasses.class);
            TableUtils.createTableIfNotExists(connectionSource, LessonBlock.class);
            TableUtils.createTableIfNotExists(connectionSource, LessonPayment.class);
            TableUtils.createTableIfNotExists(connectionSource, StudentRecord.class);
            TableUtils.createTableIfNotExists(connectionSource, Teacher.class);
            TableUtils.createTableIfNotExists(connectionSource, Timeslot.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("setupDatabase: Error creating database tables");
        }
        
    }
    
    /**
     * Initializes all Database Access Objects needed for each table in the database.
     * @param connectionSource The JBDC connection source to the Swimrite Management Suite MySQL database.
     */
    public void initializeDaos(JdbcConnectionSource connectionSource) {
        try {
            attendanceRecordDao = DaoManager.createDao(connectionSource, AttendanceRecord.class);
            swimmingClassDao = DaoManager.createDao(connectionSource, SwimmingClasses.class);
            lessonBlockDao = DaoManager.createDao(connectionSource, LessonBlock.class);
            lessonPaymentDao = DaoManager.createDao(connectionSource, LessonPayment.class);
            studentRecordDao = DaoManager.createDao(connectionSource, StudentRecord.class);
            teacherDao = DaoManager.createDao(connectionSource, Teacher.class);
            timeslotDao = DaoManager.createDao(connectionSource, Timeslot.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the database access objects");
        }
    }
    
    
    
    // CREATE, UPDATE AND DELETE FOR ATTENDANCE RECORDS.
    
    /**
     * Creates an Attendance Record and persists it to the database.
     * @throws Exception SQL Exception.
     */
    public void createAttendanceRecord() throws Exception {
        // Create an instance of an Attendance Record
        attendanceRecord = new AttendanceRecord();
        
        // Persist the Attendance Record object to the database.
        attendanceRecordDao.create(attendanceRecord);
    }
    
    /**
     * 
     * TODO: UPDATE THE TABLE TO HAVE MORE LESSON BLOCK ROWS RATHER THAN REPLACEMENT.
     * 
     * Updates an Attendance Record within the database.
     * @param attendanceId The Attendance Record to update.
     * @param lessonBlock The new lesson block to be added to the Attendance Record.
     * @throws Exception SQL Exception.
     */
    public void updateAttendanceRecord(int attendanceId, LessonBlock lessonBlock) throws Exception {
        // Retrieves the desired attendance record.
        attendanceRecord = attendanceRecordDao.queryForId(attendanceId);
        
        // If the attendance record isnt null then the new lesson block is added and the database is updated.
        if (attendanceRecord != null) {
            attendanceRecord.setLessonBlock(lessonBlock);
            attendanceRecordDao.update(attendanceRecord);
        }
        
    }
    
    /**
     * Deletes a specified Attendance Record from the database.
     * @param attendanceId the attendance record to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteAttendanceRecord(int attendanceId) throws Exception {
        // Retrieves the desired attendance record.
        attendanceRecord = attendanceRecordDao.queryForId(attendanceId);
        
        // Deletes the desired attendance record.
        attendanceRecordDao.delete(attendanceRecord);
    }
    
    
    
    // CREATE, UPDATE AND DELETE FOR SWIMMING CLASSES.
    
    /**
     * Creates a Swimming Class and persists it to the database.
     * @param classType The type of Swimming class it is.
     * @param timeslot The timeslot of the class (day/time)
     * @param teacher The teacher of the class.
     * @param maxCapacity The maximum student capacity of the class.
     * @throws Exception SQL Exception.
     */
    public void createSwimmingClass(String classType, Timeslot timeslot, Teacher teacher, int maxCapacity) throws Exception {
        // Create an instance of a Swimming Class
        swimmingClass = new SwimmingClasses(classType, timeslot, teacher, maxCapacity);
        
        // Persist the Swimming Class object to the database.
        swimmingClassDao.create(swimmingClass);
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
