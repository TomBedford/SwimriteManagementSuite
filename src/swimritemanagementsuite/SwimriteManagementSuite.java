package swimritemanagementsuite;

import swimritemanagementsuite.controller.DatabaseManager;
import swimritemanagementsuite.model.*;
import swimritemanagementsuite.view.smsMainPanel;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

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
    public static void main(String[] args) throws Exception {
        
        // Calls method to initialise the Swimrite Management Suite database connection references.
        createDbReferences();
        
        // TESTING INPUT OF DATA INTO DB
        // readAndWriteDB();
        
        // Calls method to create the swimrite management frame and panels.
        createSMSWindow();
        
        
        
    }
    
    public static void createSMSWindow() throws SQLException{
        // creates the frame to hold the home controller panel
        JFrame smsFrame = new JFrame("Swimrite Management Suite");
        
        // creates the main panel to hold the tool bar and the interchangeable panels.
        smsMainPanel smsMainPanel = new smsMainPanel();
        
        // Adds the main panel to the frame.
        smsFrame.add(smsMainPanel);
        
        // settings for the frame
        smsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        smsFrame.setSize(1400,800);
        smsFrame.setVisible(true);
        smsFrame.setResizable(false);
    }
    
    public static void createDbReferences() throws Exception{
        // Creates the Swimrite Management Suites Database Manager inorder to start interactions with the db.
        smsDatabaseManager = new DatabaseManager();
        
        // Gets the JDBC Connection Source to the SMS database.
        smsConnectionSource = smsDatabaseManager.createDatabaseConnection();
        
        /**
         * DO SOMETHING IF THE DB CONNECTION IS NULL!!!!!
         */
        
        // Creates the database tables if they do not already exist.
        smsDatabaseManager.setupDatabase(smsConnectionSource);
        
        // Initialises the database access object for each table inorder to add, edit and delete records.
        smsDatabaseManager.initializeDaos(smsConnectionSource);
    }
    
    public static void readAndWriteDB() throws Exception {

        /**
         * CREATE DEFAULT TEACHERS (3)
         */
        // Creates Teachers
        Teacher[] teachers = new Teacher[3];
        teachers[0] = new Teacher("Gemma");
        teachers[1] = new Teacher("Rachel");
        teachers[2] = new Teacher("Dani");
        
        for (int i = 0; i < 3; i++) {
            
            teachers[i].setWorkMonday(true);
            teachers[i].setWorkTuesday(true);
            teachers[i].setWorkWednesday(true);
            teachers[i].setWorkThursday(true);
            teachers[i].setWorkFriday(true);
            teachers[i].setWorkSaturday(true);
            teachers[i].setWorkSunday(true);
            
            smsDatabaseManager.teacherDAO.create(teachers[i]);
            
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        
        /**
         * CREATE ALL DEFAULT TIMESLOTS FOR MON, TUE, WED, THU, FRI, SAT, SUN
         */
        
        // CREATE MONDAY DEFAULT TIMESLOTS
        Timeslot[] timeslotArray = new Timeslot[14];
        
        int timeslotTime;
        
        // CREATE MONDAY DEFAULT TIMESLOTS
        timeslotTime = 1400;
        
        
        /**
         * ALSO CREATES TEST DATA FOR EARLY MONDAY CLASSES TO TEST QUERIES
         */
        for (int i = 0; i < 4; i++) {
            Timeslot timeslot = new Timeslot();
            timeslot.setDay(Day.MONDAY);
            timeslot.setTime(timeslotTime);
            timeslotArray[i] = timeslot;
            timeslotTime = timeslotTime + 15;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
            
            // Creates Swimming Classes for monday
            SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.MOMS_AND_DUCKS, timeslotArray[i], teachers[0], 5);
            SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.MOMS_AND_DUCKS, timeslotArray[i], teachers[1], 5);
            SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.MOMS_AND_DUCKS, timeslotArray[i], teachers[2], 5);

            smsDatabaseManager.swimmingClassesDAO.create(class1);
            smsDatabaseManager.swimmingClassesDAO.create(class2);
            smsDatabaseManager.swimmingClassesDAO.create(class3);
        }
        
        timeslotTime = 1500;
        
        for (int i = 4; i < 14; i++) {
            Timeslot timeslot1 = new Timeslot();
            timeslot1.setDay(Day.MONDAY);
            timeslot1.setTime(timeslotTime);
            timeslotArray[i] = timeslot1;
            timeslotTime = timeslotTime + 30;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
            
            Timeslot timeslot2 = new Timeslot();
            timeslot2.setDay(Day.MONDAY);
            timeslot2.setTime(timeslotTime);
            i++;
            timeslotArray[i] = timeslot2;
            timeslotTime = timeslotTime + 70;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        // CREATE TUESDAY DEFAULT TIMESLOTS
        timeslotTime = 1400;
        
        for (int i = 0; i < 4; i++) {
            Timeslot timeslot = new Timeslot();
            timeslot.setDay(Day.TUESDAY);
            timeslot.setTime(timeslotTime);
            timeslotArray[i] = timeslot;
            timeslotTime = timeslotTime + 15;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        timeslotTime = 1500;
        
        for (int i = 4; i < 14; i++) {
            Timeslot timeslot1 = new Timeslot();
            timeslot1.setDay(Day.TUESDAY);
            timeslot1.setTime(timeslotTime);
            timeslotArray[i] = timeslot1;
            timeslotTime = timeslotTime + 30;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
            
            Timeslot timeslot2 = new Timeslot();
            timeslot2.setDay(Day.TUESDAY);
            timeslot2.setTime(timeslotTime);
            i++;
            timeslotArray[i] = timeslot2;
            timeslotTime = timeslotTime + 70;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        // CREATE WEDNESDAY DEFAULT TIMESLOTS
        timeslotTime = 1400;
        
        for (int i = 0; i < 4; i++) {
            Timeslot timeslot = new Timeslot();
            timeslot.setDay(Day.WEDNESDAY);
            timeslot.setTime(timeslotTime);
            timeslotArray[i] = timeslot;
            timeslotTime = timeslotTime + 15;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        timeslotTime = 1500;
        
        for (int i = 4; i < 14; i++) {
            Timeslot timeslot1 = new Timeslot();
            timeslot1.setDay(Day.WEDNESDAY);
            timeslot1.setTime(timeslotTime);
            timeslotArray[i] = timeslot1;
            timeslotTime = timeslotTime + 30;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
            
            Timeslot timeslot2 = new Timeslot();
            timeslot2.setDay(Day.WEDNESDAY);
            timeslot2.setTime(timeslotTime);
            i++;
            timeslotArray[i] = timeslot2;
            timeslotTime = timeslotTime + 70;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        // CREATE THURSDAY DEFAULT TIMESLOTS
        timeslotTime = 1400;
        
        for (int i = 0; i < 4; i++) {
            Timeslot timeslot = new Timeslot();
            timeslot.setDay(Day.THURSDAY);
            timeslot.setTime(timeslotTime);
            timeslotArray[i] = timeslot;
            timeslotTime = timeslotTime + 15;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        timeslotTime = 1500;
        
        for (int i = 4; i < 14; i++) {
            Timeslot timeslot1 = new Timeslot();
            timeslot1.setDay(Day.THURSDAY);
            timeslot1.setTime(timeslotTime);
            timeslotArray[i] = timeslot1;
            timeslotTime = timeslotTime + 30;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
            
            Timeslot timeslot2 = new Timeslot();
            timeslot2.setDay(Day.THURSDAY);
            timeslot2.setTime(timeslotTime);
            i++;
            timeslotArray[i] = timeslot2;
            timeslotTime = timeslotTime + 70;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        // CREATE FRIDAY DEFAULT TIMESLOTS
        timeslotTime = 1400;
        
        for (int i = 0; i < 4; i++) {
            Timeslot timeslot = new Timeslot();
            timeslot.setDay(Day.FRIDAY);
            timeslot.setTime(timeslotTime);
            timeslotArray[i] = timeslot;
            timeslotTime = timeslotTime + 15;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        timeslotTime = 1500;
        
        for (int i = 4; i < 14; i++) {
            Timeslot timeslot1 = new Timeslot();
            timeslot1.setDay(Day.FRIDAY);
            timeslot1.setTime(timeslotTime);
            timeslotArray[i] = timeslot1;
            timeslotTime = timeslotTime + 30;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
            
            Timeslot timeslot2 = new Timeslot();
            timeslot2.setDay(Day.FRIDAY);
            timeslot2.setTime(timeslotTime);
            i++;
            timeslotArray[i] = timeslot2;
            timeslotTime = timeslotTime + 70;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        // CREATE SATURDAY DEFAULT TIMESLOTS
        timeslotTime = 1400;
        
        for (int i = 0; i < 4; i++) {
            Timeslot timeslot = new Timeslot();
            timeslot.setDay(Day.SATURDAY);
            timeslot.setTime(timeslotTime);
            timeslotArray[i] = timeslot;
            timeslotTime = timeslotTime + 15;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        timeslotTime = 1500;
        
        for (int i = 4; i < 14; i++) {
            Timeslot timeslot1 = new Timeslot();
            timeslot1.setDay(Day.SATURDAY);
            timeslot1.setTime(timeslotTime);
            timeslotArray[i] = timeslot1;
            timeslotTime = timeslotTime + 30;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
            
            Timeslot timeslot2 = new Timeslot();
            timeslot2.setDay(Day.SATURDAY);
            timeslot2.setTime(timeslotTime);
            i++;
            timeslotArray[i] = timeslot2;
            timeslotTime = timeslotTime + 70;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        // CREATE SUNDAY DEFAULT TIMESLOTS
        timeslotTime = 1400;
        
        for (int i = 0; i < 4; i++) {
            Timeslot timeslot = new Timeslot();
            timeslot.setDay(Day.SUNDAY);
            timeslot.setTime(timeslotTime);
            timeslotArray[i] = timeslot;
            timeslotTime = timeslotTime + 15;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        timeslotTime = 1500;
        
        for (int i = 4; i < 14; i++) {
            Timeslot timeslot1 = new Timeslot();
            timeslot1.setDay(Day.SUNDAY);
            timeslot1.setTime(timeslotTime);
            timeslotArray[i] = timeslot1;
            timeslotTime = timeslotTime + 30;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
            
            Timeslot timeslot2 = new Timeslot();
            timeslot2.setDay(Day.SUNDAY);
            timeslot2.setTime(timeslotTime);
            i++;
            timeslotArray[i] = timeslot2;
            timeslotTime = timeslotTime + 70;
            smsDatabaseManager.timeslotDAO.create(timeslotArray[i]);
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
//        // create attendance record
//        AttendanceRecord attendance = new AttendanceRecord();
//        smsDatabaseManager.attendanceRecordDAO.create(attendance);
//        
//        
//        // Create student records for mondays class
//        Date dob = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MONTH, 1);
//        cal.set(Calendar.DATE, 1);
//        cal.set(Calendar.YEAR, 1990);
//        dob = cal.getTime();
        
//        StudentRecord studentRecord1 = new StudentRecord("John Doe", dob, 0, "line1, line2, city, count, P05T C0D3", "No", "Jane Doe", SwimmingLevel.BEGINNERS, class1,attendance);
//        
//        smsDatabaseManager.studentRecordDAO.create(studentRecord1);
    }
}
