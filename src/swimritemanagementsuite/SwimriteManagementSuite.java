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
        
        // Commented out dtabase initialization code while under development.
        
//        smsDatabaseManager = new DatabaseManager();
//        smsConnectionSource = smsDatabaseManager.createDatabaseConnection();
//        smsDatabaseManager.setupDatabase(smsConnectionSource);
//        smsDatabaseManager.initializeDaos(smsConnectionSource);
        
    }
    
}
