package swimritemanagementsuite;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

/**
 * This is the main class of the Swimrite Management Suite where the database and its tables 
 * are initialized if not already created, and then the Swimrite Management Suite GUI is
 * set up.
 * 
 * @author Thomas Bedford (m2081433)
 */
public class SwimriteManagementSuite {
    
    /**
     * The connection source for the Swimrite Management Suite MySQL database. 
     */
    static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The Swimrite Management Suits database manager referenced/used for any and all database requests.
     */
    static DatabaseManager smsDatabaseManager;
    
    /**
     * Database tables and GUI are initialized/created.
     * @param args 
     */
    public static void main(String[] args) {
        
        // Creates the Swimrite Management System Database Manager inorder to start interactios with the db.
        smsDatabaseManager = new DatabaseManager();
        
        // Gets the JDBC Connection Source 
        smsConnectionSource = smsDatabaseManager.createDatabaseConnection();
        
        /**
         * DO SOMETHING IF THE DB CONNECTION IS NULL!!!!!
         */
        
        // Sets up/creates the database tables if they do not already exist.
        smsDatabaseManager.setupDatabase(smsConnectionSource);
        
        // Initialises the database access object for each table inorder to add, edit and delete records.
        smsDatabaseManager.initializeDaos(smsConnectionSource);
        
    }
    
}
