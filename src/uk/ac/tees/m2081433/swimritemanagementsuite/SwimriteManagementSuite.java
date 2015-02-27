package uk.ac.tees.m2081433.swimritemanagementsuite;

import uk.ac.tees.m2081433.swimritemanagementsuite.controller.DatabaseManager;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.smsMainPanel;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import javax.swing.*;

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
        
        // Calls method to initialise the Swimrite Management Suite database connection references.
        createDbReferences();
        
        // Calls method to create the swimrite management frame and panels.
        createSMSWindow();
    }
    
    /**
     * Creates the all database references (connection source and data access objects) and sets up the database tables.
     */
    public static void createDbReferences() {
        // Creates the Swimrite Management Suites Database Manager inorder to start interactions with the db.
        smsDatabaseManager = new DatabaseManager();
        
        // Gets the JDBC Connection Source to the SMS database.
        smsConnectionSource = smsDatabaseManager.createDatabaseConnection();
        
        // If the connection source is null display error msg.
        if (smsConnectionSource == null) {
            JOptionPane.showMessageDialog(null, "<HTML> <b> <font color='red'> ERROR </font> </b> with connection source. </HTML> \n"
                    + "(Connection Source is null)"
                    , "Connection Source Error", JOptionPane.OK_OPTION);
        } else {
            // Creates the database tables if they do not already exist.
            smsDatabaseManager.setupDatabase(smsConnectionSource);

            // Initialises the database access object for each table inorder to add, edit and delete records.
            smsDatabaseManager.initializeDaos(smsConnectionSource);
        }
    }
    
    /**
     * Creates and displays the Swimrite Management System GUI.
     */
    public static void createSMSWindow() {
        // creates the frame to hold the home controller panel
        final JFrame smsFrame = new JFrame("Swimrite Management Suite");
        
        // creates the main panel to hold the tool bar and the interchangeable panels.
        final smsMainPanel smsMainPanel = new smsMainPanel();
        
        // Adds the main panel to the frame.
        smsFrame.add(smsMainPanel);
        
        // settings for the frame
        smsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        smsFrame.setSize(1400, 800);
        smsFrame.setVisible(true);
        smsFrame.setResizable(false);
    }
}
