package uk.ac.tees.m2081433.swimritemanagementsuite;

import uk.ac.tees.m2081433.swimritemanagementsuite.controller.DatabaseManager;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.SMSMainPanel;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.*;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;

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
    private static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The Swimrite Management Suits database manager referenced/used for any and all database requests.
     */
    private static DatabaseManager smsDatabaseManager;
    
    /**
     * Database tables and GUI are initialized/created.
     * @param args 
     */
    public static void main(String[] args) {
        
        // Calls method to initialise the Swimrite Management Suite database connection references.
        createDbReferences();
        
        //addStudentRecords();
        
        // Calls method to create the swimrite management frame and panels.
        createSMSWindow();
    }
    
    /**
     * Creates the all database references (connection source and data access objects) and sets up the database tables.
     */
    private static void createDbReferences() {
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
            
            // Creates the default SMS Admin account if no other admins exist in the database
            smsDatabaseManager.checkLoginAccountsForAdmin();
        }
    }
    
    /**
     * Creates and displays the Swimrite Management System GUI.
     */
    private static void createSMSWindow() {
        // creates the frame to hold the home controller panel
        final JFrame smsFrame = new JFrame("Swimrite Management Suite");
        
        // creates the main panel to hold the tool bar and the interchangeable panels.
        final SMSMainPanel smsMainPanel = new SMSMainPanel();
        
        // Adds the main panel to the frame.
        smsFrame.add(smsMainPanel);
        
        // Creates a toolkit to gather screen size from the computer
        final Toolkit tk = Toolkit.getDefaultToolkit();
        // Gets the width of the computers screen size
        int xSize = ((int) tk.getScreenSize().getWidth());
        // Gets the height of the computers screen size
        int ySize = ((int) tk.getScreenSize().getHeight());
        
        // sets the minimum width the frame can be
        if (xSize < 1440) {
            xSize = 1440;
        }
        
        // sets the minimum height the frame can be
        if (ySize < 900) {
            ySize = 900;
        }
        
        // settings for the frame (setting the size of the frame to the minimum size required or stretching to the screen
        smsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        smsFrame.setSize(xSize, ySize);
        smsFrame.setVisible(true);
        smsFrame.setResizable(false);
    }
    
    /**
     * Adds 20 student records for test data.
     * method call commented out in constructor.
     */
    private static void addStudentRecords() {
        
        for (int i = 0; i < 10; i++) {
            
            try {
                final StudentAddress sa = new StudentAddress(i + " Line", "Second Line", "City", "County", "P05 C0D3");
                DatabaseManager.studentAddressDAO.create(sa);
                
                final StudentRecord sr = new StudentRecord("John Doe " + i, "01/12/200" + i, "0123456789" + i, sa,
                        "n/a", "Jane Doe", SwimmingLevel.BEGINNERS);
                DatabaseManager.studentRecordDAO.create(sr);
            } catch (SQLException e) {
                System.out.println("adding Test SR's: Error creating the test student addresses & records.");
            }
        }
        
        for (int i = 0; i < 10; i++) {
            
            try {
                final StudentAddress sa = new StudentAddress(i + " Line", "Second Line", "City", "County", "P05 C0D3");
                DatabaseManager.studentAddressDAO.create(sa);

                final StudentRecord sr = new StudentRecord("Andrew Smith " + i, "30/01/199" + i, "0123456789" + i, sa,
                        "n/a", "John Smith", SwimmingLevel.HONORS);
                DatabaseManager.studentRecordDAO.create(sr);
            } catch (SQLException e) {
                System.out.println("adding Test SR's: Error creating the test student addresses & records.");
            }
        }
    }
}
