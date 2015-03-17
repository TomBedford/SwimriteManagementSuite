package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.LoginDialog;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.SMSBodyPanel;


/**
 * This class asks for confirmation (including password confirmation) as to whether the user
 * wants to drop all records in the:
 * - Timeslot
 * - Teacher
 * - Swimming Classes 
 * database tables and insert the default data (restore tables to factory settings).
 * @author Bedford
 */
public class InitialiseDefaultDatabase {
    
    /**
     * The SMS Body Panel reference for when swapping panels on the body panel.
     */
    private SMSBodyPanel smsBodyPanel;
    
    
    
    /**
     * Confirmation proceedure that leads to formatting of db and insertion of default classes.
     * @param smsBP The reference to the SMS Body panel to close any components that exist on it after the format.
     */
    protected InitialiseDefaultDatabase(SMSBodyPanel smsBP) {
        
        // Initializes this panels body panel reference
        smsBodyPanel = smsBP;
        
        // Shows an Option Pane asking if wanting to format db.
        int answer = JOptionPane.showConfirmDialog(null, 
                "<HTML> Do you want to <b> <font color='red'> delete </font> </b> <u>all</u> </HTML> \n"
                        + "- Timeslot Records\n"
                        + "- Teacher Records\n"
                        + "- Swimming Class Records\n"
                        + "<HTML>and reset them to the <u>default</u> values? </HTML>"
                        , "Format Database?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        // Switch to determine users choice.
        switch (answer) {
            // The user wants to format the db.
            case 0: // Calls static method to load admin authentication dialog
                    final boolean adminAuthentication = LoginDialog.adminLoginDialog("Administrator permission required to format the database tables. Please Log In...");

                    // If admin authentication accepted, show final confirmation dialog.
                    if (adminAuthentication == true) {

                        // Show final format db confirmation dialog.
                        answer = JOptionPane.showConfirmDialog(null,
                                "<HTML> Are you sure you want to completely <b> <font color='red'> delete </font> </b> <u>all</u> </HTML> \n"
                                + "- Timeslot Records\n"
                                + "- Teacher Records\n"
                                + "- Swimming Class Records\n"
                                + "<HTML>and reset them to the <u>default</u> values? </HTML>", "FORMAT FINAL CONFIRMATION", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                        // If the answer to the dialog is yes, run the method to format the db to factory settings.
                        if (answer == 0) {
                            resetToDefaultDB();
                        }
                    }

                    break;
            // The user does not want to format the db so don't do anything.
            case 1: break;
            default:break;
        }
    }
    
    /** 
     * Method to drop all records within the following tables:
     * - Timeslot
     * - Teacher
     * - Swimming Classes
     * and then fills the above tables with the default values.
     */
    private void resetToDefaultDB() {
        
        try {
            /**
             * DROPS ALL RECORDS FROM THE FOLLOWING TABLES:
             * - Timeslot
             * - Teacher
             * - Swimming Classes.
             */
            // Removes all records within the timeslot table.
            TableUtils.clearTable(DatabaseManager.connectionSource, Timeslot.class);

            // Removes all records within the teacher table.
            TableUtils.clearTable(DatabaseManager.connectionSource, Teacher.class);

            // Removes all records within the swimming classes table.
            TableUtils.clearTable(DatabaseManager.connectionSource, SwimmingClasses.class);



            /**
             * CREATE DEFAULT TEACHERS (3)
             */
            // Creates Teachers
            final Teacher[] teachers = new Teacher[3];
            teachers[0] = new Teacher("Gemma");
            teachers[1] = new Teacher("Rachel");
            teachers[2] = new Teacher("Dani");

            // Sets each of the teachers to work on all days of the week.
            for (int i = 0; i < 3; i++) {

                teachers[i].setWorkMonday(true);
                teachers[i].setWorkTuesday(true);
                teachers[i].setWorkWednesday(true);
                teachers[i].setWorkThursday(true);
                teachers[i].setWorkFriday(true);
                teachers[i].setWorkSaturday(true);
                teachers[i].setWorkSunday(true);

                DatabaseManager.teacherDAO.create(teachers[i]);

            }

            /**
             * CREATE ALL DEFAULT TIMESLOTS FOR MON, TUE, WED, THU, FRI, SAT, SUN
             */

            // Array to hold each of the timeslots as they're created and added to the db.
            final Timeslot[] timeslotArray = new Timeslot[18];

            int timeslotTime;

            // CREATE MONDAY DEFAULT TIMESLOTS & SWIMMING CLASSES
            timeslotTime = 1400;
            // Creates Timeslots & Swimming Classes for Monday 14:00 - 14:45 (each quarter of an hour)
            for (int i = 0; i < 4; i++) {
                final Timeslot timeslot = new Timeslot();
                timeslot.setDay(Day.MONDAY);
                timeslot.setTime(timeslotTime);
                timeslotArray[i] = timeslot;
                timeslotTime = timeslotTime + 15;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                final SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.MOMS_AND_DUCKS, timeslotArray[i], teachers[0], 5);
                final SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.MOMS_AND_DUCKS, timeslotArray[i], teachers[1], 5);
                final SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.MOMS_AND_DUCKS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }

            // Creates Timeslots & Swimming Classes for Monday 15:00 - 19:00
            timeslotTime = 1500;
            for (int i = 4; i < 14; i++) {
                // Creates timeslot for Monday 15:00 - 19:00 (on the hour)
                final Timeslot timeslot1 = new Timeslot();
                timeslot1.setDay(Day.MONDAY);
                timeslot1.setTime(timeslotTime);
                timeslotArray[i] = timeslot1;
                timeslotTime = timeslotTime + 30;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Monday 15:00 - 19:00 (on the hour)
                SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);

                // Creates timeslot for Monday 15:30 - 19:30 (on the half of the hour)
                final Timeslot timeslot2 = new Timeslot();
                timeslot2.setDay(Day.MONDAY);
                timeslot2.setTime(timeslotTime);
                i++;
                timeslotArray[i] = timeslot2;
                timeslotTime = timeslotTime + 70;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates more Swimming Classes for Monday 15:30 - 19:30 (on the half of the hour)
                class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }



            // CREATE TUESDAY DEFAULT TIMESLOTS & SWIMMING CLASSES
            timeslotTime = 1400;
            // Creates Timeslots & Swimming Classes for Tuesday 14:00 - 14:45 (each quarter of an hour)
            for (int i = 0; i < 4; i++) {
                final Timeslot timeslot = new Timeslot();
                timeslot.setDay(Day.TUESDAY);
                timeslot.setTime(timeslotTime);
                timeslotArray[i] = timeslot;
                timeslotTime = timeslotTime + 15;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                final SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.GRADE_1, timeslotArray[i], teachers[0], 5);
                final SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.GRADE_1, timeslotArray[i], teachers[1], 5);
                final SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_1, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }

            // Creates Timeslots & Swimming Classes for Tuesday 15:00 - 19:00
            timeslotTime = 1500;
            for (int i = 4; i < 14; i++) {
                // Creates timeslot for Tuesday 15:00 - 19:00 (on the hour)
                final Timeslot timeslot1 = new Timeslot();
                timeslot1.setDay(Day.TUESDAY);
                timeslot1.setTime(timeslotTime);
                timeslotArray[i] = timeslot1;
                timeslotTime = timeslotTime + 30;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Tuesday 15:00 - 19:00 (on the hour)
                SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);

                // Creates timeslot for Tuesday 15:30 - 19:30 (on the half of the hour)
                final Timeslot timeslot2 = new Timeslot();
                timeslot2.setDay(Day.TUESDAY);
                timeslot2.setTime(timeslotTime);
                i++;
                timeslotArray[i] = timeslot2;
                timeslotTime = timeslotTime + 70;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates more Swimming Classes for Tuesday 15:30 - 19:30 (on the half of the hour)
                class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }



            // CREATE WEDNESDAY DEFAULT TIMESLOTS & SWIMMING CLASSES
            timeslotTime = 1400;
            // Creates Timeslots & Swimming Classes for Wednesday 14:00 - 14:45 (each quarter of an hour)
            for (int i = 0; i < 4; i++) {
                final Timeslot timeslot = new Timeslot();
                timeslot.setDay(Day.WEDNESDAY);
                timeslot.setTime(timeslotTime);
                timeslotArray[i] = timeslot;
                timeslotTime = timeslotTime + 15;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Wednesday 14:00 - 14:45
                final SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.GRADE_2, timeslotArray[i], teachers[0], 5);
                final SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.GRADE_2, timeslotArray[i], teachers[1], 5);
                final SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_2, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }

            // Creates Timeslots & Swimming Classes for Wednesday 15:00 - 19:00
            timeslotTime = 1500;
            for (int i = 4; i < 14; i++) {
                // Creates timeslot for Wednesday 15:00 - 19:00 (on the hour)
                final Timeslot timeslot1 = new Timeslot();
                timeslot1.setDay(Day.WEDNESDAY);
                timeslot1.setTime(timeslotTime);
                timeslotArray[i] = timeslot1;
                timeslotTime = timeslotTime + 30;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Wednesday 15:00 - 19:00 (on the hour)
                SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);

                // Creates timeslot for Wednesday 15:30 - 19:30 (on the half of the hour)
                final Timeslot timeslot2 = new Timeslot();
                timeslot2.setDay(Day.WEDNESDAY);
                timeslot2.setTime(timeslotTime);
                i++;
                timeslotArray[i] = timeslot2;
                timeslotTime = timeslotTime + 70;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates more Swimming Classes for Wednesday 15:30 - 19:30 (on the half of the hour)
                class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }



            // CREATE THURSDAY DEFAULT TIMESLOTS & SWIMMING CLASSES
            timeslotTime = 1400;
            // Creates Timeslots & Swimming Classes for Thursday 14:00 - 14:45 (each quarter of an hour)
            for (int i = 0; i < 4; i++) {
                final Timeslot timeslot = new Timeslot();
                timeslot.setDay(Day.THURSDAY);
                timeslot.setTime(timeslotTime);
                timeslotArray[i] = timeslot;
                timeslotTime = timeslotTime + 15;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Thursday 14:00 - 14:45
                final SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.GRADE_3, timeslotArray[i], teachers[0], 5);
                final SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.GRADE_3, timeslotArray[i], teachers[1], 5);
                final SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_3, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }

            // Creates Timeslots & Swimming Classes for Thursday 15:00 - 19:00
            timeslotTime = 1500;
            for (int i = 4; i < 14; i++) {
                // Creates timeslot for Thursday 15:00 - 19:00 (on the hour)
                final Timeslot timeslot1 = new Timeslot();
                timeslot1.setDay(Day.THURSDAY);
                timeslot1.setTime(timeslotTime);
                timeslotArray[i] = timeslot1;
                timeslotTime = timeslotTime + 30;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Thursday 15:00 - 19:00 (on the hour)
                SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);

                // Creates timeslot for Thursday 15:30 - 19:30 (on the half of the hour)
                final Timeslot timeslot2 = new Timeslot();
                timeslot2.setDay(Day.THURSDAY);
                timeslot2.setTime(timeslotTime);
                i++;
                timeslotArray[i] = timeslot2;
                timeslotTime = timeslotTime + 70;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates more Swimming Classes for Thursday 15:30 - 19:30 (on the half of the hour)
                class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }



            // CREATE FRIDAY DEFAULT TIMESLOTS & SWIMMING CLASSES
            timeslotTime = 1400;
            // Creates Timeslots & Swimming Classes for Friday 14:00 - 14:45 (each quarter of an hour)
            for (int i = 0; i < 4; i++) {
                // Creates timeslot for Friday 15:00 - 19:00 (on the hour)
                final Timeslot timeslot = new Timeslot();
                timeslot.setDay(Day.FRIDAY);
                timeslot.setTime(timeslotTime);
                timeslotArray[i] = timeslot;
                timeslotTime = timeslotTime + 15;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Friday 14:00 - 14:45
                final SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.GRADE_4, timeslotArray[i], teachers[0], 5);
                final SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.GRADE_4, timeslotArray[i], teachers[1], 5);
                final SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_4, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }

            // Creates Timeslots & Swimming Classes for Friday 15:00 - 19:00
            timeslotTime = 1500;
            for (int i = 4; i < 14; i++) {
                // Creates timeslot for Friday 15:00 - 19:00 (on the hour)
                final Timeslot timeslot1 = new Timeslot();
                timeslot1.setDay(Day.FRIDAY);
                timeslot1.setTime(timeslotTime);
                timeslotArray[i] = timeslot1;
                timeslotTime = timeslotTime + 30;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Friday 15:00 - 19:00 (on the hour)
                SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);

                // Creates timeslot for Friday 15:30 - 19:30 (on the half of the hour)
                final Timeslot timeslot2 = new Timeslot();
                timeslot2.setDay(Day.FRIDAY);
                timeslot2.setTime(timeslotTime);
                i++;
                timeslotArray[i] = timeslot2;
                timeslotTime = timeslotTime + 70;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates more Swimming Classes for Friday 15:30 - 19:30 (on the half of the hour)
                class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }



            // CREATE SATURDAY DEFAULT TIMESLOTS & SWIMMING CLASSES
            timeslotTime = 900;
            // Creates Timeslots & Swimming Classes for Saturday 9:00-5:30 (every half an hour)
            for (int i = 0; i < 17; i++) {
                // Creates timeslot for Saturday 09:00 - 17:00 (on the hour)
                final Timeslot timeslot1 = new Timeslot();
                timeslot1.setDay(Day.SATURDAY);
                timeslot1.setTime(timeslotTime);
                timeslotArray[i] = timeslot1;
                timeslotTime = timeslotTime + 30;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Saturday 09:00 - 17:00 (on the hour)
                SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);

                // Creates timeslot for Saturday 09:30 - 17:30 (on the half of the hour)
                final Timeslot timeslot2 = new Timeslot();
                timeslot2.setDay(Day.SATURDAY);
                timeslot2.setTime(timeslotTime);
                i++;
                timeslotArray[i] = timeslot2;
                timeslotTime = timeslotTime + 70;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates more Swimming Classes for Saturday 09:30 - 17:30 (on the half of the hour)
                class1 = new SwimmingClasses(SwimmingLevel.DUCKS, timeslotArray[i], teachers[0], 5);
                class2 = new SwimmingClasses(SwimmingLevel.BEGINNERS, timeslotArray[i], teachers[1], 5);
                class3 = new SwimmingClasses(SwimmingLevel.GRADE_1_MINUS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }



            // CREATE SUNDAY DEFAULT TIMESLOTS & SWIMMING CLASSES
            timeslotTime = 900;
            // Creates Timeslots & Swimming Classes for Sunday 9:00-5:30 (every half an hour)
            for (int i = 0; i < 11; i++) {
                // Creates timeslot for Sunday 09:00 - 17:00 (on the hour)
                final Timeslot timeslot1 = new Timeslot();
                timeslot1.setDay(Day.SUNDAY);
                timeslot1.setTime(timeslotTime);
                timeslotArray[i] = timeslot1;
                timeslotTime = timeslotTime + 30;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates Swimming Classes for Sunday 09:00 - 17:00 (on the hour)
                SwimmingClasses class1 = new SwimmingClasses(SwimmingLevel.GRADE_8, timeslotArray[i], teachers[0], 5);
                SwimmingClasses class2 = new SwimmingClasses(SwimmingLevel.GRADE_9, timeslotArray[i], teachers[1], 5);
                SwimmingClasses class3 = new SwimmingClasses(SwimmingLevel.HONORS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);

                // Creates timeslot for Sunday 09:30 - 17:30 (on the half of the hour)
                final Timeslot timeslot2 = new Timeslot();
                timeslot2.setDay(Day.SUNDAY);
                timeslot2.setTime(timeslotTime);
                i++;
                timeslotArray[i] = timeslot2;
                timeslotTime = timeslotTime + 70;
                DatabaseManager.timeslotDAO.create(timeslotArray[i]);

                // Creates more Swimming Classes for Sunday 09:30 - 17:30 (on the half of the hour)
                class1 = new SwimmingClasses(SwimmingLevel.GRADE_8, timeslotArray[i], teachers[0], 5);
                class2 = new SwimmingClasses(SwimmingLevel.GRADE_9, timeslotArray[i], teachers[1], 5);
                class3 = new SwimmingClasses(SwimmingLevel.HONORS, timeslotArray[i], teachers[2], 5);

                DatabaseManager.swimmingClassesDAO.create(class1);
                DatabaseManager.swimmingClassesDAO.create(class2);
                DatabaseManager.swimmingClassesDAO.create(class3);
            }
            
            // Loads the welcome panel onto the body panel
            smsBodyPanel.removeCurrentlyDisplayedPanel();
            
        } catch (SQLException e) {
            // Print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("InitialiseDefaultDatabase: Error initialising the default database tables");
        }
        
    }
}
