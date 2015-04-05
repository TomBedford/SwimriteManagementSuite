/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonPayment;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;

/**
 * This class manages the access to the database and database creation and maintenance.
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
    
    /**
     * This is the connection source to the Swimrite Management System database.
     */
    public static JdbcConnectionSource connectionSource;
    
    /**
     * The Data Access Object used to access the Lesson Block table in the mySQL database.
     */
    public static Dao<LessonBlock, Integer> lessonBlockDAO;
    
    /**
     * The Data Access Object used to access the Lesson Payment table in the mySQL database.
     */
    public static Dao<LessonPayment, Integer> lessonPaymentDAO;
    
    /**
     * The Data Access Object used to access the Login Account table in the mySQL database.
     */
    public static Dao<LoginAccount, Integer> loginAccountDAO;
    
    /**
     * The Data Access Object used to access the Student Address table in the mySQL database.
     */
    public static Dao<StudentAddress, Integer> studentAddressDAO;
    
    /**
     * The Data Access Object used to access the Student Record table in the mySQL database.
     */
    public static Dao<StudentRecord, Integer> studentRecordDAO;

    /**
     * The Data Access Object used to access the Swimming Classes table in the mySQL database.
     */
    public static Dao<SwimmingClasses, Integer> swimmingClassesDAO;
    
    /**
     * The Data Access Object used to access the Teacher table in the mySQL database.
     */
    public static Dao<Teacher, Integer> teacherDAO;
    
    /**
     * The Data Access Object used to access the Timeslot table in the mySQL database.
     */
    public static Dao<Timeslot, Integer> timeslotDAO;

    /**
     * Creates and returns the database JDBC connection source that links to the Swimrite Management Suite online MySQL
     * database.
     *
     * @return connectionSource This is the JDBC connection source that leads to the MySQL database.
     */
    public JdbcConnectionSource createDatabaseConnection() {
        
        connectionSource = null;

        try {
            // Create our data source.
            connectionSource = new JdbcConnectionSource(DATABASE_URL, dbUsername, dbPassword);
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
     * @return boolean as to whether the setup of the database tables were successful.
     */
    public boolean setupDatabase(JdbcConnectionSource connectionSource) {

        try {
            // Creates all tables within the database if they do not already exist.
            TableUtils.createTableIfNotExists(connectionSource, LessonBlock.class);
            TableUtils.createTableIfNotExists(connectionSource, LessonPayment.class);
            TableUtils.createTableIfNotExists(connectionSource, LoginAccount.class);
            TableUtils.createTableIfNotExists(connectionSource, StudentAddress.class);
            TableUtils.createTableIfNotExists(connectionSource, StudentRecord.class);
            TableUtils.createTableIfNotExists(connectionSource, SwimmingClasses.class);
            TableUtils.createTableIfNotExists(connectionSource, Teacher.class);
            TableUtils.createTableIfNotExists(connectionSource, Timeslot.class);
        } catch (SQLException e) {
            // Print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("setupDatabase: Error creating database tables");
            return false;
        } 
        return true;
    }

    /**
     * Initializes all Database Access Objects inside the Data Access Classes for each table in the database.
     *
     * @param connectionSource The JBDC connection source to the Swimrite Management Suite MySQL database.
     */
    public void initializeDaos(JdbcConnectionSource connectionSource) {
        
        try {
            // Initialises all database acces objects for each table within the db.
            lessonBlockDAO = DaoManager.createDao(connectionSource, LessonBlock.class);
            lessonPaymentDAO = DaoManager.createDao(connectionSource, LessonPayment.class);
            loginAccountDAO = DaoManager.createDao(connectionSource, LoginAccount.class);
            studentAddressDAO = DaoManager.createDao(connectionSource, StudentAddress.class);
            studentRecordDAO = DaoManager.createDao(connectionSource, StudentRecord.class);
            swimmingClassesDAO = DaoManager.createDao(connectionSource, SwimmingClasses.class);
            teacherDAO = DaoManager.createDao(connectionSource, Teacher.class);
            timeslotDAO = DaoManager.createDao(connectionSource, Timeslot.class);
        } catch (SQLException e) {
            // Print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the database access objects");
        } 
    }
    
    /**
     * Checks the login account database for an admin account, if one doesn't exist then the default admin account is
     * created.
     */
    public void checkLoginAccountsForAdmin() {
        try {
            // Gets a login account if the username equals the one specified
            final List<LoginAccount> adminLoginAccounts = DatabaseManager.loginAccountDAO.queryForEq(LoginAccount.ADMIN_COLUMN_NAME, true);

            // If the list is empty then create the default admin account
            if (adminLoginAccounts.isEmpty()) {
                final LoginAccount defaultAdmin = new LoginAccount("Tom_Bedford", 
                        "ce29cc33bd2a6e1acbc82b97d4618ddc83f44b9d7d0efe5ee2b4147cc237b7c1f90c129d9e786456d29f896bdc361549bdad08fca7b7308c9fa43c51eb857312", 
                        "2fcc62eb46eae117ea2cbb96", true, "What was your first dog?", 
                        "ce94d02e85dbfb6d22d799300a3e5ae92262cd2187681b52c21011d64c5753dbffb78b53e4deae51efbf0d66a6d3d647569d9a4f64735cf7e893213307e532c7");
                DatabaseManager.loginAccountDAO.create(defaultAdmin);
            } 
        } catch (SQLException e) {
            System.out.println("checkLoginAccountsForAdmin: Error checking login accounts for an admin account.");
        }
    }
}
