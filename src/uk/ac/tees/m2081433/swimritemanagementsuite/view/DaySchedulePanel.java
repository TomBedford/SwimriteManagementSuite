package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.TeacherController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import java.util.List;
import javax.swing.JOptionPane;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.SwimmingClassesController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.TimeslotController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * This class dynamically displays the swimming class schedule for the specified day.
 */
public class DaySchedulePanel extends JPanel {
    
    /**
     * The Teacher controller for when running db queries on the Teacher table.
     */
    TeacherController teacherController;
    
    /**
     * The Timeslot controller for when running db queries on the Timeslot table.
     */
    TimeslotController timeslotController;
    
    /**
     * The Swimming Classes controller for when running db queries on the Swimming Classes table.
     */
    SwimmingClassesController swimmingClassesController;
    
    /**
     * Holds all Teachers for the specified day.
     */
    List<Teacher> teacherList;
    
    /**
     * Holds all Timeslots for the specified day.
     */
    List<Timeslot> timeslotList;
    
    /**
     * Holds all Swimming Classes for the specified day.
     */
    List<SwimmingClasses> swimmingClassesList;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * Dynamically loads the Swimming Class schedule for the day specified as the parameter.
     * @param day The specified day to load the swimming class schedule.
     */
    public DaySchedulePanel(Day day) {
        
        // Initialises all controllers used by this class.
        teacherController = new TeacherController();
        timeslotController = new TimeslotController();
        swimmingClassesController = new SwimmingClassesController();
        
        // Method to get all the timeslots for the specified day (needed info to work out panel size etc.)
        getTimeslotsForDay(day);
        
        // Calculates what the height of the day schedule panel should be.
        int panelHeight = calculateDaySchedulePanelHeight(day);
        
        // sets the smsMainPanel JPanel attributes.
        this.setPreferredSize(new Dimension(1360, panelHeight));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint.
        c = new GridBagConstraints();
        
        // Method to load the entire swimming class schedule for that day.
        loadSchedule(day);
        
        // Invisible label used to push all other components in the layout to the top.
        JLabel pullComponentsUp = new JLabel();
        
        c.gridx = 0;
        c.gridy = 1000;
        c.weighty = 1.0;
        c.fill = 1;
        
        this.add(pullComponentsUp, c);
    }
    
    /**
     * Gets all the timeslots associated with the specified day.
     * This is needed so the height of the panel can be calculated and whether an error message
     * is neeeded to be displayed if the default class schedules have not been initialised.
     * @param day The specified day to load the timeslots from.
     */
    public void getTimeslotsForDay(Day day) {
        // Gets all the timeslots for the specified day.
        timeslotList = timeslotController.getTimeslotsForDay(day);
    }
    
    /**
     * Calculates the height that the day Schedule Panel should be.
     * @param day The specified day schedule to calculate
     * @return panelHeight The height that the day schedule panel should be.
     */
    public int calculateDaySchedulePanelHeight(Day day) {
        // Gets the amount of timeslots from the size of the timeslot list.
        int amountOfTimeslots = timeslotList.size();
        
        // Calculates the height of the panel as each timeslot label is 150 height.
        int panelHeight = (amountOfTimeslots * 150) +  100;
        
        // If the panel height is less than the smallest required, set it to the smallest size.
        if (panelHeight < 575) {
            panelHeight = 575;
        }
        
        return panelHeight;
    }
    
    /**
     * This method calls each individual method to load different parts of the schedule.
     * @param selectedDay The specified day to load the swimming class schedule.
     */
    public void loadSchedule(Day selectedDay) {
        
        // adds the timeslot column title to the schedule.
        addTimeslotColumnTitle();
        
        // adds all teachers that work on the specified day to the schedule.
        addTeachersRow(selectedDay);
        
        // adds all the timeslots on the specified day to the schedule.
        addTimeslotColumn(selectedDay);
        
        // adds all swimming classes on the specified day to the schedule.
        addSwimmingClasses(selectedDay);
    }
    
    /**
     * Adds the timeslot column title of the class schedule table, or displays an error message
     * depending on whether there is data to load.
     */
    public void addTimeslotColumnTitle() {
        
        // if the timeslot list is empty display db initialisation error msg.
        if (timeslotList.size() == 0) {
            
            JOptionPane.showMessageDialog(null, "Please initialise the database to start! \n"
                    + "The database initialisation settings are located within the \n"
                    + "'Advanced Settings' Menu tab.", "Default Database not Initialised", JOptionPane.ERROR_MESSAGE);

        } else {
            // Creates the first label to hold the column heading for Timeslots and sets its attributes.
            JLabel row1column1 = new JLabel("Time", SwingConstants.CENTER);
            row1column1.setPreferredSize(new Dimension(150, 50));
            row1column1.setOpaque(true);
            row1column1.setBorder(BorderFactory.createRaisedBevelBorder());
            row1column1.setVisible(true);

            // The coordinates for where to add this component to the layout.
            c.gridx = 0;
            c.gridy = 0;

            this.add(row1column1, c);
        }
    
    }
    
    /**
     * Adds all teachers to the swimming class schedule for the specified day.
     * @param selectedDay The selected day to load the teacher data from.
     */
    public void addTeachersRow(Day selectedDay) {
        
        // Gets all the teachers for the specified day.
        teacherList = teacherController.getTeachersForDay(selectedDay);
        
        // Sorts all of the teachers by the teacher Id of the teacher (Ascending order).
        teacherList = teacherController.sortTeachersByTeacherId(teacherList);
        
        // label array to hold the name of the teachers for the specified day.
        JLabel[] teacherRow = new JLabel[teacherList.size()];
        
        // Loops through the teacher list adding them to the schedule table.
        for (int i = 0; i < teacherList.size(); i++) {
            
            teacherRow[i] = new JLabel();
            teacherRow[i].setPreferredSize(new Dimension(300, 50));
            teacherRow[i].setOpaque(true);
            teacherRow[i].setBorder(BorderFactory.createRaisedBevelBorder());
            teacherRow[i].setVisible(true);
            teacherRow[i].setText(teacherList.get(i).getTeacherName());
            teacherRow[i].setHorizontalAlignment(SwingConstants.CENTER);

            c.gridx = i + 1;
            c.gridy = 0;

            this.add(teacherRow[i], c);
        }
    }
    
    /**
     * Adds all timeslots to the swimming class schedule for the specified day.
     * @param selectedDay The specified day to load the schedule for.
     */
    public void addTimeslotColumn(Day selectedDay) {
        // Sorts all of the timeslots by the time of the timeslot (Ascending order).
        timeslotList = timeslotController.sortTimeslotsByTime(timeslotList);
        
        // label array to hold the timeslots for the specified day.
        JLabel[] timeslotColumn = new JLabel[timeslotList.size()];
        
        // Loops through the timeslots list and adds each timeslot to the schedule table.
        for (int i = 0; i < timeslotList.size(); i++) {
            
            timeslotColumn[i] = new JLabel();
            timeslotColumn[i].setPreferredSize(new Dimension(150, 150));
            timeslotColumn[i].setOpaque(true);
            timeslotColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
            timeslotColumn[i].setVisible(true);
            
            // Converts the timeslot time to a string.
            String timeLabelText = Integer.toString(timeslotList.get(i).getTime());
                    
            // Formats the time string with a colon so that it looks like a time (eg, 1300 -> 13:00)
            String formattedLabelText = new StringBuilder(timeLabelText).insert(timeLabelText.length()-2, ":").toString();
            
            // Sets the text of the label and where it is on the label.
            timeslotColumn[i].setText(formattedLabelText);
            timeslotColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
            
            // Logic to add the component to the correct cooridinates on the layout.
            c.gridx = 0;
            c.gridy = i + 1;
            
            this.add(timeslotColumn[i], c);
        }
    }
    
    /**
     * Adds all swimming classes to the swimming class schedule for the specified day.
     * @param selectedDay The specified day to load the schedule for.
     */
    public void addSwimmingClasses(Day selectedDay) {
        
        // Gets all swimming classes for the specified day.
        swimmingClassesList = swimmingClassesController.getClassesByDay(selectedDay);
        
        // Sorts all of the swimming classes by the teacher Id (Ascending order).
        swimmingClassesList = swimmingClassesController.sortClassesByTeacherId(swimmingClassesList);
        
        // 3 Lists to hold each of the teachers own swimming classes.
        List<SwimmingClasses> teacher1ClassesList = new ArrayList();
        List<SwimmingClasses> teacher2ClassesList = new ArrayList();
        List<SwimmingClasses> teacher3ClassesList = new ArrayList();
        
        // Loops through the entire list of swimming classes putting them in the correct list for each teacher.
        for (SwimmingClasses sc : swimmingClassesList) {
            if (sc.getTeacher().getTeacherId() == teacherList.get(0).getTeacherId()) {
                teacher1ClassesList.add(sc);
            } else if (sc.getTeacher().getTeacherId() == teacherList.get(1).getTeacherId()) {
                teacher2ClassesList.add(sc);
            } else if (sc.getTeacher().getTeacherId() == teacherList.get(2).getTeacherId()) {
                teacher3ClassesList.add(sc);
            } else {
                System.out.println("ERROR INPUTTING CLASSES INTO SEPERATE TEACHER LISTS");
            }
        }
        
        // Creates an array of labels to hold the details for the swimming classes for the first teacher
        JLabel[] teacher1ClassesColumn = new JLabel[teacher1ClassesList.size()];
        
        // Iterate through the swimming classes list for teacher 1 to add all classes to the schedule.
        for (int i = 0; i < teacher1ClassesList.size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacher1ClassesList.get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher1ClassesColumn[i] = new JLabel();
                teacher1ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher1ClassesColumn[i].setOpaque(true);
                teacher1ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher1ClassesColumn[i].setLayout(new BorderLayout());
                teacher1ClassesColumn[i].setVisible(true);
                teacher1ClassesColumn[i].setText("Class Type: " + teacher1ClassesList.get(i).getClassType());
                teacher1ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                teacher1ClassesColumn[i].setVerticalAlignment(JLabel.BOTTOM);
                
                // The Gridbag Layout constraints logic to add the label to the correct x & y grid space.
                c.gridx = 1;
                c.gridy = i + 1;
                
                // Adds the label to the correct space in the gridbag layout.
                this.add(teacher1ClassesColumn[i], c);
                
            } else {
                System.out.println("ERROR MATCHING TEACHER CLASS 1 LIST TIME TO TIMESLOT TIME.");
            }
        }
        
        
        
        // Creates an array of labels to hold the details for the swimming classes for the second teacher
        JLabel[] teacher2ClassesColumn = new JLabel[teacher2ClassesList.size()];
        
        // Iterate through the swimming classes list for teacher 2 to add all classes to the schedule.
        for (int i = 0; i < teacher2ClassesList.size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacher2ClassesList.get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher2ClassesColumn[i] = new JLabel();
                teacher2ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher2ClassesColumn[i].setOpaque(true);
                teacher2ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher2ClassesColumn[i].setLayout(new BorderLayout());
                teacher2ClassesColumn[i].setVisible(true);
                teacher2ClassesColumn[i].setText("Class Type: " + teacher2ClassesList.get(i).getClassType());
                teacher2ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                teacher2ClassesColumn[i].setVerticalAlignment(JLabel.BOTTOM);
                
                // The Gridbag Layout constraints logic to add the label to the correct x & y grid space.
                c.gridx = 2;
                c.gridy = i + 1;
                
                // Adds the label to the correct space in the gridbag layout.
                this.add(teacher2ClassesColumn[i], c);
                
            } else {
                System.out.println("ERROR MATCHING TEACHER CLASS 2 LIST TIME TO TIMESLOT TIME.");
            }
        }
        
        
        
        // Creates an array of labels to hold the details for the swimming classes for the third teacher
        JLabel[] teacher3ClassesColumn = new JLabel[teacher3ClassesList.size()];
        
        // Iterate through the swimming classes list for teacher 3 to add all classes to the schedule.
        for (int i = 0; i < teacher3ClassesList.size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacher3ClassesList.get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher3ClassesColumn[i] = new JLabel();
                teacher3ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher3ClassesColumn[i].setOpaque(true);
                teacher3ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher3ClassesColumn[i].setLayout(new BorderLayout());
                teacher3ClassesColumn[i].setVisible(true);
                teacher3ClassesColumn[i].setText("Class Type: " + teacher3ClassesList.get(i).getClassType());
                teacher3ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                teacher3ClassesColumn[i].setVerticalAlignment(JLabel.BOTTOM);
                
                // The Gridbag Layout constraints logic to add the label to the correct x & y grid space.
                c.gridx = 3;
                c.gridy = i + 1;
                
                // Adds the label to the correct space in the gridbag layout.
                this.add(teacher3ClassesColumn[i], c);
                
            } else {
                System.out.println("ERROR MATCHING CLASS 3 LIST TIME TO TIMESLOT TIME.");
            }
        }
    }
}
