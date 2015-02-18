package swimritemanagementsuite;

import swimritemanagementsuite.model.*;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
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
    private final static String DATABASE_URL = "jdbc:mysql://localhost:8888/SwimriteManagementSuite";

    /**
     * This is the username for the MySql database.
     */
    private final String dbUsername = "root";

    /**
     * This is the password for the MySql database.
     */
    private final String dbPassword = "root";
    
    AddressDAC addressDAC;
    
    /**
     * This is the Data Access Class used to Add, Update and Delete records from the Attendance Record table.
     */
    AttendanceRecordDAC attendanceRecordDAC;
    
    /**
     * This is the Data Access Class used to Add, Update and Delete records from the Lesson Block table.
     */
    LessonBlockDAC lessonBlockDAC;
    
    /**
     * This is the Data Access Class used to Add, Update and Delete records from the Lesson Payment table.
     */
    LessonPaymentDAC lessonPaymentDAC;
    
    /**
     * This is the Data Access Class used to Add, Update and Delete records from the Student Record table.
     */
    StudentRecordDAC studentRecordDAC;
    
    /**
     * This is the Data Access Class used to Add, Update and Delete records from the Swimming Classes table.
     */
    SwimmingClassesDAC swimmingClassesDAC;
    
    /**
     * This is the Data Access Class used to Add, Update and Delete records from the Teacher table.
     */
    TeacherDAC TeacherDAC;
    
    /**
     * This is the Data Access Class used to Add, Update and Delete records from the Timeslot table.
     */
    TimeslotDAC TimeslotDAC;
    

    /**
     * Default constructor of the database manager class.
     */
    public void DatabaseManager() {

    }

    /**
     * Creates and returns the database JDBC connection source that links to the Swimrite Management Suite online MySQL
     * database.
     *
     * @return connectionSource This is the JDBC connection source that leads to the MySQL database.
     */
    public JdbcConnectionSource createDatabaseConnection() {
        
        JdbcConnectionSource connectionSource = null;

        try {
            // create our data source.
            connectionSource = new JdbcConnectionSource(DATABASE_URL, dbUsername, dbPassword);
            System.out.println("Connection Source set up correctly");
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("createDatabaseConnection: Error establishing connection source");
        }
        return connectionSource;
    }

    /**
     * Sets up the database if tables have not already been initialized.
     *
     * @param connectionSource The connection to the Swimrite Management Suite MySQL database
     */
    public void setupDatabase(JdbcConnectionSource connectionSource) {

        try {
            //Creates all tables within the database if they do not already exist.
            TableUtils.createTableIfNotExists(connectionSource, Address.class);
            TableUtils.createTableIfNotExists(connectionSource, AttendanceRecord.class);
            TableUtils.createTableIfNotExists(connectionSource, SwimmingClasses.class);
            TableUtils.createTableIfNotExists(connectionSource, LessonBlock.class);
            TableUtils.createTableIfNotExists(connectionSource, LessonPayment.class);
            TableUtils.createTableIfNotExists(connectionSource, StudentRecord.class);
            TableUtils.createTableIfNotExists(connectionSource, Teacher.class);
            TableUtils.createTableIfNotExists(connectionSource, Timeslot.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            //e.printStackTrace();
            System.out.println("setupDatabase: Error creating database tables");
        }
    }

    /**
     * Initializes all Database Access Objects inside the Data Access Classes for each table in the database.
     *
     * @param connectionSource The JBDC connection source to the Swimrite Management Suite MySQL database.
     */
    public void initializeDaos(JdbcConnectionSource connectionSource) {
        
        addressDAC = new AddressDAC(connectionSource);
        attendanceRecordDAC = new AttendanceRecordDAC(connectionSource);
        lessonBlockDAC = new LessonBlockDAC(connectionSource);
        lessonPaymentDAC = new LessonPaymentDAC(connectionSource);
        studentRecordDAC = new StudentRecordDAC(connectionSource);
        swimmingClassesDAC = new SwimmingClassesDAC(connectionSource);
        TeacherDAC = new TeacherDAC(connectionSource);
        TimeslotDAC = new TimeslotDAC(connectionSource);
        
    }
}
