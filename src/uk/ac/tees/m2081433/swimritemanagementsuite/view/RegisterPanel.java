/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.TeacherController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LessonBlockController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.StudentRecordController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.SwimmingClassesController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.TimeslotController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.AttendanceType;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * This class dynamically displays the swimming class schedule to be registered for todays classes.
 */
public class RegisterPanel extends JPanel implements ActionListener {
    
    /**
     * The day to load the register swimming schedule for (todays day!).
     */
    private Day day;
    
    /**
     * The date of todays lesson to register.
     */
    private String todaysDate;
    
    /**
     * The Teacher controller for when running db queries on the Teacher table.
     */
    private final TeacherController teacherController = new TeacherController();
    
    /**
     * The Timeslot controller for when running db queries on the Timeslot table.
     */
    private final TimeslotController timeslotController = new TimeslotController();
    
    /**
     * The Swimming Classes controller for when running db queries on the Swimming Classes table.
     */
    private final SwimmingClassesController swimmingClassesController = new SwimmingClassesController();
    
    /**
     * The Student Record controller for when running db queries on the Student Record table.
     */
    private final StudentRecordController studentRecordController = new StudentRecordController();
    
    /**
     * The controller to interact with the lesson block table in the database.
     */
    private final LessonBlockController lessonBlockController = new LessonBlockController();
    
    /**
     * Holds all Teachers for the specified day.
     */
    private List<Teacher> workingTeacherList;
    
    /**
     * Holds all Teachers that dont work on the specified day.
     */
    private List<Teacher> notWorkingTeacherList;
    
    /**
     * Holds all Timeslots for the specified day.
     */
    private List<Timeslot> timeslotList;
    
    /**
     * Holds all Swimming Classes for the specified day.
     */
    private List<SwimmingClasses> swimmingClassesList;
    
    /**
     * The two dimensional array of panels that holds the swimming classes for each teachers column.
     */
    private JPanel[][] teacherClassesColumnPanels;
    
    /**
     * The array of array list to hold the different swimming classes by teacher.
     */
    private ArrayList<SwimmingClasses>[] teachersClassesLists;
    
    /**
     * The three dimensional array of student records that holds each student record for each swimming class
     * in each teacher column.
     * 1st dimension = The teacher column the student record is in
     * 2nd dimension = The swimming class row in that teacher column the student record is in
     * 3rd dimension = The student record for that swimming class
     */
    private StudentRecord[][][] studentRecordList;
    
    /**
     * The three dimensional array of panels that holds each student records name and attendance buttons for
     * every swimming class.
     */
    private JPanel[][][] swimmingClassStudentRecordPanels;
    
    /**
     * The three dimensional array of labels that holds each student records name on the student records panel.
     */
    private JLabel[][][] studentRecordNameLabels;
    
    /**
     * The three dimensional array for all the buttons to change attendance of the appropriate student record to present.
     */
    private JButton[][][] presentButton;
    
    /**
     * The three dimensional array for all the buttons to change attendance of the appropriate student record to absent.
     */
    private JButton[][][] absentButton;
    
    /**
     * The three dimensional array for all the buttons to change attendance of the appropriate student record to fun swim.
     */
    private JButton[][][] funSwimButton;
    
    /**
     * The three dimensional array for all the buttons to begin the editing of the attendance of the appropriate student record.
     */
    private JButton[][][] editAttendanceButton;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    private GridBagConstraints c;
    
    
    
    /**
     * The colour used for the headers background.
     */
    private final Color smsBlue = new Color(172, 172, 255);
    
    /**
     * The color of attendance marked students name labels.
     */
    private final Color smsGreenMarked = new Color(50, 150, 0);
    
    /**
     * The font for all headers in the table.
     */
    private final Font tableHeaderFont = new Font("Arial", Font.PLAIN, 18);
    
    /**
     * The font for all records/rows in the table.
     */
    private final Font tableRecordFont = new Font("Arial", Font.PLAIN, 15);
    
    /**
     * The font all edit attendance buttons.
     */
    private final Font editAttendanceFont = new Font("Arial", Font.PLAIN, 12);
    
    
    
    /**
     * The panels to hold each timeslot time on.
     */
    private JPanel[] timeslotColumn;
        
    /**
     * The panel to hold the teacher name column headers.
     */
    private JPanel[] teacherHeader;
    
    /**
     * The labels to hold the text for each teachers name on the column header panels.
     */
    private JLabel[] teacherHeaderText;
    

    
    /**
     * Dynamically loads the Swimming Class schedule for the day specified as the parameter.
     * @param smsBodyPanel The smsBodyPanel reference to swap panels on the body panel.
     */
    public RegisterPanel(SMSBodyPanel smsBodyPanel) {
        
        // Method that gets todays day and date.
        calcTodaysDayAndDate();
        
        // Method to get all the timeslots for the specified day (needed info to work out panel height etc.)
        getTimeslotsForDay();
        
        // Method to get all teachers for the specifiec day (needed info to work out the panel width etc.)
        getWorkingTeachersForDay();
        
        // Calculates what the height of the day schedule panel should be.
        final int panelHeight = calculateDaySchedulePanelHeight();
        
        // Calculates what the width of the day schedule panel should be.
        final int panelWidth = calculateDaySchedulePanelWidth();
        
        // sets the smsMainPanel JPanel attributes. 
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint.
        c = new GridBagConstraints();
        
        // Method to load the entire swimming class schedule for that day
        loadSchedule();
        
        // Invisible label used to push all other components in the layout to the top.
        final JLabel pullComponentsUp = new JLabel();
        
        // Adds the component to push all other componenets up on this layout, to this layout.
        c.gridx = 0;
        c.gridy = 1000;
        c.weighty = 1.0;
        c.fill = 1;
        
        this.add(pullComponentsUp, c);
    }
    
    /**
     * Calculates todays day and sets the day variable to it, also calculates todays date and formats it correctly
     * and sets it to the todaysDate variable.
     */
    private void calcTodaysDayAndDate() {
        // Gets an instance of a calendar to work out todays date
        final Calendar calendar = Calendar.getInstance();
        
        // Gets the day of the week as an int
        final int dayNo = calendar.get(Calendar.DAY_OF_WEEK);
       
        // Switch to determine which day of the week it is and set the day variable to the correct day enum
        switch (dayNo) {
            // The day of the week is sunday
            case 1: day = Day.SUNDAY;
                break;
            // The day of the week is monday
            case 2: day = Day.MONDAY;
                break;
            // The day of the week is tuesday
            case 3: day = Day.TUESDAY;
                break;
            // The day of the week is wednesday
            case 4: day = Day.WEDNESDAY;
                break;
            // The day of the week is thursday
            case 5: day = Day.THURSDAY;
                break;
            // The day of the week is friday
            case 6: day = Day.FRIDAY;
                break;
            // The day of the week is saturday
            case 7: day = Day.SATURDAY;
                break;
            default:
                break;
        }
        
        
        
        // The string to hold the day of the month
        String dayOfMonth;
        
        // If the day of the month is less than 10, add a 0 to the front of it for proper date format (eg, DD/MM/YYYY)
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            dayOfMonth = "0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            dayOfMonth = "" + calendar.get(Calendar.DAY_OF_MONTH);
        }
        
        // The string to hold the month
        String month;
        
        // If the month is less than 10 add a 0 to the front of it for proper date format (eg, DD/MM/YYYY)
        // Calendar month plus 1 as the calendar month starts at 0 for january...
        if ((calendar.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            month = "" + (calendar.get(Calendar.MONTH) + 1);
        }
        
        // Concatinates the date strings to form one correclty formatted date string (eg, DD/MM/YYYY)
        todaysDate = dayOfMonth + "/" + month + "/" + calendar.get(Calendar.YEAR);
    }
    
    /**
     * Gets all the timeslots associated with the specified day.
     * Info needed early to calculate panel height.
     */
    private void getTimeslotsForDay() {
        // Gets all the timeslots for the specified day.
        timeslotList = timeslotController.getTimeslotsForDay(day);
    }
    
    /**
     * Gets all the teachers that work on the specified day.
     * Info needed early to calculate panel width.
     */
    private void getWorkingTeachersForDay() {
        // Gets all the teachers for the specified day
        workingTeacherList = teacherController.getTeachersForDay(day);
    }
    
    /**
     * Calculates the height that the Day Schedule Panel should be.
     * @return panelHeight The height that the day schedule panel should be.
     */
    private int calculateDaySchedulePanelHeight() {
        // Gets the amount of timeslots from the size of the timeslot list.
        final int amountOfTimeslots = timeslotList.size();
        
        // Calculates the height of the panel as each timeslot label is 150 height (adds 100 padding).
        final int panelHeight = (amountOfTimeslots * 210) +  100;
        
        return panelHeight;
    }
    
    /**
     * Calculates the width that the Day Schedule Panel should be.
     * @return panelWidth The width that the day schedule panel should be.
     */
    private int calculateDaySchedulePanelWidth() {
        // Gets the amound of teachers from the size of the working teacher list.
        int amountOfTeachers = workingTeacherList.size();
        
        // Creates an int to hold the panel width
        int panelWidth;
        
        // If the amount of teachers is larger than 3 calculate the panel width
        if (amountOfTeachers > 3) {
            // Removes 3 of the teachers to calculate how much larger the panel needs to be
            amountOfTeachers = amountOfTeachers - 3;
            
            // Calculates the width of the panel as each teacher column is 300 width (adds 100 padding).
            panelWidth = 1360 + (amountOfTeachers * 300);
            
        } else {
            // Otherwise sets it to default panel size
            panelWidth = 1360;
        }
        
        return panelWidth;
    }
    
    /**
     * This method calls each individual method to load different parts of the schedule.
     */
    private void loadSchedule() {
        
        // adds the timeslot column title to the schedule.
        addTimeslotColumnHeader();
        
        // adds all teachers that work on the specified day to the schedule.
        addTeachersColumnHeaders();
        
        // adds all the timeslots on the specified day to the schedule and tells it that edit mode is false.
        addTimeslotColumn();
        
        // adds all swimming classes on the specified day to the schedule.
        addSwimmingClassesAndStudents();
    }
    
    /**
     * Adds the timeslot column title of the class schedule table, or displays an error message
     * depending on whether there is data to load.
     */
    private void addTimeslotColumnHeader() {
        
        // If the timeslot list is empty and the working teacher list is empty add begin label
        if (timeslotList.isEmpty() || workingTeacherList.isEmpty()) {
            final JLabel beginLabel = new JLabel("<HTML><b>No Classes To Register!</b> Please insert classes and student to begin registering</HTML>", SwingConstants.CENTER);
            beginLabel.setPreferredSize(new Dimension(525, 50));
            beginLabel.setOpaque(true);
            beginLabel.setVisible(true);
            beginLabel.setBackground(Color.white);
            beginLabel.setFont(tableHeaderFont);

            // The coordinates for where to add this component to the layout.
            c.gridx = 0;
            c.gridy = 0;

            this.add(beginLabel, c);
            
        // If the timeslot list is not empty add the timeslot time column header
        } else if (!timeslotList.isEmpty()) {
            
            // Creates the first label to hold the column heading for Timeslots and sets its attributes.
            final JLabel row1column1 = new JLabel("Time", SwingConstants.CENTER);
            row1column1.setPreferredSize(new Dimension(100, 50));
            row1column1.setOpaque(true);
            row1column1.setBorder(BorderFactory.createRaisedBevelBorder());
            row1column1.setVisible(true);
            row1column1.setBackground(smsBlue);
            row1column1.setFont(tableHeaderFont);

            // The coordinates for where to add this component to the layout.
            c.gridx = 0;
            c.gridy = 0;

            this.add(row1column1, c);
        }
    
    }
    
    /**
     * Adds all teachers to the swimming class schedule for the specified day.
     */
    private void addTeachersColumnHeaders() {
        
        // Sorts all of the teachers by the teacher Id of the teacher (Ascending order)
        workingTeacherList = teacherController.sortTeachersByTeacherId(workingTeacherList);
        
        // Panel array to hold the text label holding the name of the teachers for the specified day
        teacherHeader = new JPanel[workingTeacherList.size()];
        
        // Label array to hold the name of teachers for the specified day
        teacherHeaderText = new JLabel[workingTeacherList.size()];
        
        // Loops through the teacher list adding them to the schedule table
        for (int i = 0; i < workingTeacherList.size(); i++) {
            
            // Creates a header panel in the array to hold that teachers column label
            teacherHeader[i] = new JPanel();
            teacherHeader[i].setPreferredSize(new Dimension(300, 50));
            teacherHeader[i].setOpaque(true);
            teacherHeader[i].setBorder(BorderFactory.createRaisedBevelBorder());
            teacherHeader[i].setVisible(true);
            teacherHeader[i].setBackground(smsBlue);
            
            // Creates a header label to hold the text for that teacher column
            teacherHeaderText[i] = new JLabel();
            teacherHeaderText[i].setPreferredSize(new Dimension(290, 40));
            teacherHeaderText[i].setOpaque(true);
            teacherHeaderText[i].setVisible(true);
            teacherHeaderText[i].setBackground(smsBlue);
            teacherHeaderText[i].setText(workingTeacherList.get(i).getTeacherName());
            teacherHeaderText[i].setHorizontalAlignment(SwingConstants.CENTER);
            teacherHeaderText[i].setFont(tableHeaderFont);
            
            // Adds the header text label to the teacher header panel
            teacherHeader[i].add(teacherHeaderText[i]);

            // Coordinates for where to add the teacher header panel to this layout
            c.gridx = i + 1;
            c.gridy = 0;

            this.add(teacherHeader[i], c);
        }
    }
    
    /**
     * Adds all timeslots to the swimming class schedule for the specified day.
     */
    private void addTimeslotColumn() {
        // Sorts all of the timeslots by the time of the timeslot (Ascending order).
        timeslotList = timeslotController.sortTimeslotsByTime(timeslotList);
        
        // Panel array to hold the timeslots for the specified day.
        timeslotColumn = new JPanel[timeslotList.size()];
        
        // Label array to hold the text for each timeslot
        final JLabel[] timeslotColumnText = new JLabel[timeslotList.size()];
        
        // Loops through the timeslots list and adds each timeslot to the schedule table.
        for (int i = 0; i < timeslotList.size(); i++) {
            
            // Creates a panel to hold the timeslot text label
            timeslotColumn[i] = new JPanel();
            timeslotColumn[i].setPreferredSize(new Dimension(100, 210));
            timeslotColumn[i].setOpaque(true);
            timeslotColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
            timeslotColumn[i].setVisible(true);
            timeslotColumn[i].setBackground(smsBlue);
            timeslotColumn[i].setLayout(new BorderLayout());
            
            // Converts the timeslot time to a string.
            final String timeLabelText = Integer.toString(timeslotList.get(i).getTime());
            
            // Formats the time string with a colon so that it looks like a time (eg, 1300 -> 13:00)
            final String formattedLabelText = new StringBuilder(timeLabelText).insert(timeLabelText.length() - 2, ":").toString();
            
            // Creates a label to hold the timeslots time (text)
            timeslotColumnText[i] = new JLabel();
            timeslotColumnText[i].setPreferredSize(new Dimension(100, 50));
            timeslotColumnText[i].setOpaque(true);
            timeslotColumnText[i].setVisible(true);
            timeslotColumnText[i].setBackground(smsBlue);
            timeslotColumnText[i].setFont(tableHeaderFont);
            
            // Sets the text of the label and where it is on the label
            timeslotColumnText[i].setText(formattedLabelText);
            timeslotColumnText[i].setHorizontalAlignment(SwingConstants.CENTER);
            
            // Adds the timeslot label text to the timeslot panel
            timeslotColumn[i].add(timeslotColumnText[i], BorderLayout.CENTER);
            
            // Logic to add the timeslot panel component to the correct cooridinates on this layout.
            c.gridx = 0;
            c.gridy = i + 1;
            
            this.add(timeslotColumn[i], c);
        }
    }
    
    /**
     * Adds all swimming classes to the swimming class schedule and adds all student records
     * that are in each swimming class to the appropriate swimming class panel for todays day.
     */
    private void addSwimmingClassesAndStudents() {
        
        // Gets all swimming classes for the specified day.
        swimmingClassesList = swimmingClassesController.getClassesByDay(day);
        
        // Sorts the classes by the timeslot time.
        swimmingClassesList = swimmingClassesController.sortClassesByTimeslotTime(swimmingClassesList);
        
        // Sorts all of the swimming classes by the teacher Id (Ascending order).
        swimmingClassesList = swimmingClassesController.sortClassesByTeacherId(swimmingClassesList);
        
        // an array of array list, as each array list will hold one column of teachers classes for that day.
        teachersClassesLists = new ArrayList[workingTeacherList.size()];
        
        // Loops through the amount of teachers working on that day and initializes the array.
        for (int i = 0; i < workingTeacherList.size(); i++) {
            teachersClassesLists[i] = new ArrayList();
        }
        
        // Loops through all of the indexs in the teachers classes list.
        for (int i = 0; i < teachersClassesLists.length; i++) {
            // Loops through the entire list of swimming classes putting them in the correct list for each teacher.
            for (SwimmingClasses sc : swimmingClassesList) {
                if (sc.getTeacher().getTeacherId() == workingTeacherList.get(i).getTeacherId()) {
                    teachersClassesLists[i].add(sc);
                }
            }
        }
        
        // Try Catch as when initializing the two dimensional array the second dimenion can be null.
        try {
            // Creates a two dimensional array to hold each teachers column of panels
            teacherClassesColumnPanels = new JPanel[teachersClassesLists.length][teachersClassesLists[0].size()];
            
            // Creates a three dimensional array to hold the students for each swimming class.
            studentRecordList = new StudentRecord[teachersClassesLists.length][teachersClassesLists[0].size()][5];
            
            // Creates a three dimensional array to hold the students name and attendance buttons for each swimming class
            swimmingClassStudentRecordPanels = new JPanel[teachersClassesLists.length][teachersClassesLists[0].size()][5];

            // Creates a three dimensional array of labels to hold the student records names on the sr panels
            studentRecordNameLabels = new JLabel[teachersClassesLists.length][teachersClassesLists[0].size()][5];
            
            // Creates a three dimensional array of buttons to change the appropriate student records attendance to present
            presentButton = new JButton[teachersClassesLists.length][teachersClassesLists[0].size()][5];
            
            // Creates a three dimensional array of buttons to change the appropriate student records attendance to absent
            absentButton = new JButton[teachersClassesLists.length][teachersClassesLists[0].size()][5];
            
            // Creates a three dimensional array of buttons to change the appropriate student records attendance to fun swim
            funSwimButton = new JButton[teachersClassesLists.length][teachersClassesLists[0].size()][5];
            
            // Creates a three dimensional array of buttons to edit the appropriate students records attendance.
            editAttendanceButton = new JButton[teachersClassesLists.length][teachersClassesLists[0].size()][5];
            
            // Loops through the first dimension of the array (each teacher column)
            for (int i = 0; i < teacherClassesColumnPanels.length; i++) {

                // Loops through the second dimenion of the array creating labels in each to hold swimming class info
                for (int x = 0; x < teachersClassesLists[i].size(); x++) {

                    // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
                    if (teachersClassesLists[i].get(x).getTimeslot().getTime() == timeslotList.get(x).getTime()) {
                        // Create and assign label attributes
                        teacherClassesColumnPanels[i][x] = new JPanel();
                        teacherClassesColumnPanels[i][x].setPreferredSize(new Dimension(300, 210));
                        teacherClassesColumnPanels[i][x].setOpaque(true);
                        teacherClassesColumnPanels[i][x].setBorder(BorderFactory.createRaisedBevelBorder());
                        teacherClassesColumnPanels[i][x].setVisible(true);

                        // Gets all student records that are in this swimming class
                        final List<StudentRecord> swimmingClassStudentRecordList = studentRecordController.getSwimmingClassStudentRecords(teachersClassesLists[i].get(x));

                        // Loops through all student records in this iterations swimming class
                        for (int y = 0; y < swimmingClassStudentRecordList.size(); y++) {
                            // Adds all student records for this swimming class to the correct index in the 3 dimensional array
                            studentRecordList[i][x][y] = swimmingClassStudentRecordList.get(y);
                            
                            // Creates the panel to hold the student records name and attendance buttons
                            swimmingClassStudentRecordPanels[i][x][y] = new JPanel();
                            swimmingClassStudentRecordPanels[i][x][y].setPreferredSize(new Dimension(290, 35));
                            swimmingClassStudentRecordPanels[i][x][y].setOpaque(true);
                            swimmingClassStudentRecordPanels[i][x][y].setVisible(true);
                            
                            
                            
                            // Creates the labels to hold the student record names
                            studentRecordNameLabels[i][x][y] = new JLabel();
                            studentRecordNameLabels[i][x][y].setPreferredSize(new Dimension(150, 30));
                            studentRecordNameLabels[i][x][y].setOpaque(true);
                            studentRecordNameLabels[i][x][y].setVisible(true);
                            studentRecordNameLabels[i][x][y].setText(studentRecordList[i][x][y].getStudentName());
                            
                            // Adds the student name labels to the student record panels
                            swimmingClassStudentRecordPanels[i][x][y].add(studentRecordNameLabels[i][x][y]);
                            
                            // Gets all lesson blocks associated with the student record
                            List<LessonBlock> lessonBlockList = lessonBlockController.getLessonBlocksByStudent(studentRecordList[i][x][y]);
                            
                            // If they don't have a lesson block, one is created and added to their student record in the db.
                            if (lessonBlockList.isEmpty()) {
                                // Creates a new lesson block using the student record
                                final LessonBlock lessonBlock = new LessonBlock(studentRecordList[i][x][y]);
                                // Creates the lesson block in the database
                                lessonBlockController.create(lessonBlock);
                                // Gets the newly created lesson block from the db
                                lessonBlockList = lessonBlockController.getLessonBlocksByStudent(studentRecordList[i][x][y]);
                                
                            // If not empty sort them. (No sorting is needed if their lesson block list is empty)
                            } else {
                                // Sorts the lesson blocks by their id (descending order/most recently created lesson blocks first).
                                lessonBlockList = lessonBlockController.sortLessonBlockssByLessonBlockId(lessonBlockList); 
                            }
                            
                            // String to store the most recent lesson date.
                            String mostRecentLessonDate = "";
                            
                            // Sets the date if the lesson 1 date is not null
                            if (lessonBlockList.get(0).getLesson1Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson1Date();
                            }
                            
                            // Sets the date if the lesson 2 date is not null
                            if (lessonBlockList.get(0).getLesson2Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson2Date();
                            }
                            
                            // Sets the date if the lesson 3 date is not null
                            if (lessonBlockList.get(0).getLesson3Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson3Date();
                            }
                            
                            // Sets the date if the lesson 4 date is not null
                            if (lessonBlockList.get(0).getLesson4Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson4Date();
                            }
                            
                            // Sets the date if the lesson 5 date is not null
                            if (lessonBlockList.get(0).getLesson5Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson5Date();
                            }
                            
                            // Sets the date if the lesson 6 date is not null
                            if (lessonBlockList.get(0).getLesson6Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson6Date();
                            }
                            
                            // Sets the date if the lesson 7 date is not null
                            if (lessonBlockList.get(0).getLesson7Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson7Date();
                            }
                            
                            // Sets the date if the lesson 8 date is not null
                            if (lessonBlockList.get(0).getLesson8Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson8Date();
                            }
                            
                            // Sets the date if the lesson 9 date is not null
                            if (lessonBlockList.get(0).getLesson9Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson9Date();
                            }
                            
                            // Sets the date if the lesson 10 date is not null
                            if (lessonBlockList.get(0).getLesson10Date() != null) {
                                mostRecentLessonDate = lessonBlockList.get(0).getLesson10Date();
                            }
                            
                            // Checks if the most recent date in their lesson history is equal to this day
                            if (mostRecentLessonDate.equals(todaysDate)) {
                                // Creates the edit attendance button for this student records panel
                                editAttendanceButton[i][x][y] = new JButton("Edit");
                                editAttendanceButton[i][x][y].setPreferredSize(new Dimension(100, 30));
                                editAttendanceButton[i][x][y].addActionListener(this);
                                editAttendanceButton[i][x][y].setToolTipText("<html> Click this button to <b> edit the attendance of </b> this student record for todays lesson. </html>");
                                editAttendanceButton[i][x][y].setFont(editAttendanceFont);
                                editAttendanceButton[i][x][y].setIcon(new ImageIcon("images/icons/16x16/bullet_edit.png"));
                                
                                // Adds the edit attendance button to the student records panel
                                swimmingClassStudentRecordPanels[i][x][y].add(editAttendanceButton[i][x][y]);
                                
                                // Sets the foreground to green to signal the attendance has been marked
                                studentRecordNameLabels[i][x][y].setForeground(smsGreenMarked);
                                
                            // Display attendance options
                            } else {
                            
                                // Creates the present button for this student records panel
                                presentButton[i][x][y] = new JButton();
                                presentButton[i][x][y].setPreferredSize(new Dimension(30, 30));
                                presentButton[i][x][y].addActionListener(this);
                                presentButton[i][x][y].setToolTipText("<html> Click this button to mark that the student is <b> present </b> for this lesson. </html>");
                                presentButton[i][x][y].setIcon(new ImageIcon("images/icons/16x16/accept.png"));

                                // Adds the present button to the student records panel
                                swimmingClassStudentRecordPanels[i][x][y].add(presentButton[i][x][y]);



                                // Creates the absent button for this student records panel
                                absentButton[i][x][y] = new JButton();
                                absentButton[i][x][y].setPreferredSize(new Dimension(30, 30));
                                absentButton[i][x][y].addActionListener(this);
                                absentButton[i][x][y].setToolTipText("<html> Click this button to mark that the student is <b> absent </b> for this lesson. </html>");
                                absentButton[i][x][y].setIcon(new ImageIcon("images/icons/16x16/cancel.png"));

                                // Adds the absent button to the student records panel
                                swimmingClassStudentRecordPanels[i][x][y].add(absentButton[i][x][y]);



                                // Creates the fun swim button for this student records panel
                                funSwimButton[i][x][y] = new JButton();
                                funSwimButton[i][x][y].setPreferredSize(new Dimension(30, 30));
                                funSwimButton[i][x][y].addActionListener(this);
                                funSwimButton[i][x][y].setToolTipText("<html> Click this button to mark that the student is granted a <b> fun swim </b> for this lesson. </html>");
                                funSwimButton[i][x][y].setIcon(new ImageIcon("images/icons/16x16/fun_swim.png"));

                                // Adds the fun swim button to the student records panel
                                swimmingClassStudentRecordPanels[i][x][y].add(funSwimButton[i][x][y]);
                                
                                // Sets the foreground to red to signal the attendance has not been marked
                                studentRecordNameLabels[i][x][y].setForeground(Color.RED);
                            }
                            
                            // Adds the student panel to the swimming class panel
                            teacherClassesColumnPanels[i][x].add(swimmingClassStudentRecordPanels[i][x][y]);
                        }
                        
                        // The Gridbag Layout constraints logic to add the label to the correct x & y grid space.
                        c.gridx = i + 1;
                        c.gridy = x + 1;

                        // Adds the label to the correct space in the gridbag layout.
                        this.add(teacherClassesColumnPanels[i][x], c);
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Second Dimension of the teacherClassesColumnLabels was null");
        }
    }
    
    /**
     * Sets the attendance for a student record to the attendance type passed in as a param and the attendance date 
     * to todays date if todays date is not already present within their lesson block.
     * @param i The first dimension of the arrays
     * @param x The second dimension of the arrays
     * @param y The third dimension of the arrays
     * @param attendanceType 
     */
    private void setStudentAttendance(int i, int x, int y, AttendanceType attendanceType) {

        // Gets all lesson blocks associated with the student record
        List<LessonBlock> lessonBlockList = lessonBlockController.getLessonBlocksByStudent(studentRecordList[i][x][y]);

        // Sorts the lesson blocks by their id (descending order/most recently created lesson blocks first).
        lessonBlockList = lessonBlockController.sortLessonBlockssByLessonBlockId(lessonBlockList);

        // flag if a matching date has been found so it doesnt check and change other columns attendances
        boolean foundMatchingDate = false;

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson1Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 1 date is equal to todays date
            if (lessonBlockList.get(0).getLesson1Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson1Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson2Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 2 date is equal to todays date
            if (lessonBlockList.get(0).getLesson2Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson2Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson3Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 3 date is equal to todays date
            if (lessonBlockList.get(0).getLesson3Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to to the attendance type passed in
                lessonBlockList.get(0).setLesson3Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson4Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 4 date is equal to todays date
            if (lessonBlockList.get(0).getLesson4Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson4Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson5Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 5 date is equal to todays date
            if (lessonBlockList.get(0).getLesson5Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson5Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson6Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 6 date is equal to todays date
            if (lessonBlockList.get(0).getLesson6Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson6Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson7Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 7 date is equal to todays date
            if (lessonBlockList.get(0).getLesson7Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson7Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson8Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 8 date is equal to todays date
            if (lessonBlockList.get(0).getLesson8Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson8Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson9Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 9 date is equal to todays date
            if (lessonBlockList.get(0).getLesson9Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson9Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // Checks if the lesson date is not equal to null and the found matching date is equal to false
        if (lessonBlockList.get(0).getLesson10Date() != null && foundMatchingDate == false) {
            // Sets the date if the lesson 10 date is equal to todays date
            if (lessonBlockList.get(0).getLesson10Date().equals(todaysDate)) {
                // Sets the appropriate attendance column to the attendance type passed in
                lessonBlockList.get(0).setLesson10Attendance(attendanceType);
                // Sets the flag to true as a matching date has been found
                foundMatchingDate = true;
            }
        }

        // If a matching date hasnt been found then create the date and mark attendance
        if (foundMatchingDate == false) {
            /**
             * Loop through their most recent lesson block finding the first lesson date that is null and set the date
             * to todays date and update the appropriate attendance column with the attendance type passed in.
             */
            if (lessonBlockList.get(0).getLesson1Date() == null) {
                lessonBlockList.get(0).setLesson1Date(todaysDate);
                lessonBlockList.get(0).setLesson1Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson2Date() == null) {
                lessonBlockList.get(0).setLesson2Date(todaysDate);
                lessonBlockList.get(0).setLesson2Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson3Date() == null) {
                lessonBlockList.get(0).setLesson3Date(todaysDate);
                lessonBlockList.get(0).setLesson3Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson4Date() == null) {
                lessonBlockList.get(0).setLesson4Date(todaysDate);
                lessonBlockList.get(0).setLesson4Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson5Date() == null) {
                lessonBlockList.get(0).setLesson5Date(todaysDate);
                lessonBlockList.get(0).setLesson5Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson6Date() == null) {
                lessonBlockList.get(0).setLesson6Date(todaysDate);
                lessonBlockList.get(0).setLesson6Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson7Date() == null) {
                lessonBlockList.get(0).setLesson7Date(todaysDate);
                lessonBlockList.get(0).setLesson7Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson8Date() == null) {
                lessonBlockList.get(0).setLesson8Date(todaysDate);
                lessonBlockList.get(0).setLesson8Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson9Date() == null) {
                lessonBlockList.get(0).setLesson9Date(todaysDate);
                lessonBlockList.get(0).setLesson9Attendance(attendanceType);
            } else if (lessonBlockList.get(0).getLesson10Date() == null) {
                lessonBlockList.get(0).setLesson10Date(todaysDate);
                lessonBlockList.get(0).setLesson10Attendance(attendanceType);
            }
        }
        // Updates the lesson block in the database
        lessonBlockController.update(lessonBlockList.get(0));

        // Removes all of the attendance buttons from their student panel
        swimmingClassStudentRecordPanels[i][x][y].remove(presentButton[i][x][y]);
        swimmingClassStudentRecordPanels[i][x][y].remove(absentButton[i][x][y]);
        swimmingClassStudentRecordPanels[i][x][y].remove(funSwimButton[i][x][y]);

        // Creates and adds the edit attendance button to their student panel
        editAttendanceButton[i][x][y] = new JButton("Edit");
        editAttendanceButton[i][x][y].setPreferredSize(new Dimension(100, 30));
        editAttendanceButton[i][x][y].addActionListener(this);
        editAttendanceButton[i][x][y].setToolTipText("<html> Click this button to <b> edit the attendance of </b> this student record for todays lesson. </html>");
        editAttendanceButton[i][x][y].setFont(editAttendanceFont);
        editAttendanceButton[i][x][y].setIcon(new ImageIcon("images/icons/16x16/bullet_edit.png"));

        // Adds the edit attendance button to the appropriate student record panel
        swimmingClassStudentRecordPanels[i][x][y].add(editAttendanceButton[i][x][y]);

        // Sets the foreground to green to signal the attendance has been marked
        studentRecordNameLabels[i][x][y].setForeground(smsGreenMarked);

        // Updates the UI with the new edit attendance button present
        swimmingClassStudentRecordPanels[i][x][y].updateUI();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Loops through all of the present buttons checking if any of them were pressed.
         */
        // Loops through the first dimenion in the array of buttons
        for (int i = 0; i < presentButton.length; i++) {
            // Loops through the second dimension of the array of buttons
            for (int x = 0; x < presentButton[i].length; x++) {
                // Loops through the third dimension of the array of buttons
                for (int y = 0; y < presentButton[i][x].length; y++) {
                    // Checks if that index in the array of buttons was the source of the button press
                    if (e.getSource() == presentButton[i][x][y]) {
                        // Sets the attendance for the student to present for todays date/lesson
                        setStudentAttendance(i, x, y, AttendanceType.PRESENT);
                    }
                }
            }
        }
        
        /**
         * Loops through all of the absent buttons checking if any of them were pressed.
         */
        // Loops through the first dimenion in the array of buttons
        for (int i = 0; i < absentButton.length; i++) {
            // Loops through the second dimension of the array of buttons
            for (int x = 0; x < absentButton[i].length; x++) {
                // Loops through the third dimension of the array of buttons
                for (int y = 0; y < absentButton[i][x].length; y++) {
                    // Checks if that index in the array of buttons was the source of button press
                    if (e.getSource() == absentButton[i][x][y]) {
                        // Sets the attendance for the student to present for todays date/lesson
                        setStudentAttendance(i, x, y, AttendanceType.ABSENT);
                    }
                }
            }
        }
        
        
        /**
         * Loops through all of the fun swim buttons checking if any of them were pressed.
         */
        // Loops through the first dimenion in the array of buttons
        for (int i = 0; i < funSwimButton.length; i++) {
            // Loops through the second dimension of the array of buttons
            for (int x = 0; x < funSwimButton[i].length; x++) {
                // Loops through the third dimension of the array of buttons
                for (int y = 0; y < funSwimButton[i][x].length; y++) {
                    // Checks if that index in the array of buttons was the source of button press
                    if (e.getSource() == funSwimButton[i][x][y]) {
                        // Sets the attendance for the student to present for todays date/lesson
                        setStudentAttendance(i, x, y, AttendanceType.FUN_SWIM);
                    }
                }
            }
        }
        
        
        /**
         * Loops through all of the edit attendance buttons checking if any of them were pressed.
         */
        // Loops through the first dimenion in the array of buttons
        for (int i = 0; i < editAttendanceButton.length; i++) {
            // Loops through the second dimension of the array of buttons
            for (int x = 0; x < editAttendanceButton[i].length; x++) {
                // Loops through the third dimension of the array of buttons
                for (int y = 0; y < editAttendanceButton[i][x].length; y++) {
                    // Checks if that index in the array of buttons was the source of button press
                    if (e.getSource() == editAttendanceButton[i][x][y]) {
                        // Removes the edit attendance buttons from their student panel
                        swimmingClassStudentRecordPanels[i][x][y].remove(editAttendanceButton[i][x][y]);
                        
                        /**
                         * Creates and adds all attendance buttons to the appropriate student panel.
                         */
                        // Creates the present button for this student records panel
                        presentButton[i][x][y] = new JButton();
                        presentButton[i][x][y].setPreferredSize(new Dimension(30, 30));
                        presentButton[i][x][y].addActionListener(this);
                        presentButton[i][x][y].setToolTipText("<html> Click this button to mark that the student is <b> present </b> for this lesson. </html>");
                        presentButton[i][x][y].setIcon(new ImageIcon("images/icons/16x16/accept.png"));

                        // Adds the present button to the student records panel
                        swimmingClassStudentRecordPanels[i][x][y].add(presentButton[i][x][y]);

                        // Creates the absent button for this student records panel
                        absentButton[i][x][y] = new JButton();
                        absentButton[i][x][y].setPreferredSize(new Dimension(30, 30));
                        absentButton[i][x][y].addActionListener(this);
                        absentButton[i][x][y].setToolTipText("<html> Click this button to mark that the student is <b> absent </b> for this lesson. </html>");
                        absentButton[i][x][y].setIcon(new ImageIcon("images/icons/16x16/cancel.png"));

                        // Adds the absent button to the student records panel
                        swimmingClassStudentRecordPanels[i][x][y].add(absentButton[i][x][y]);

                        // Creates the fun swim button for this student records panel
                        funSwimButton[i][x][y] = new JButton();
                        funSwimButton[i][x][y].setPreferredSize(new Dimension(30, 30));
                        funSwimButton[i][x][y].addActionListener(this);
                        funSwimButton[i][x][y].setToolTipText("<html> Click this button to mark that the student is granted a <b> fun swim </b> for this lesson. </html>");
                        funSwimButton[i][x][y].setIcon(new ImageIcon("images/icons/16x16/fun_swim.png"));

                        // Adds the fun swim button to the student records panel
                        swimmingClassStudentRecordPanels[i][x][y].add(funSwimButton[i][x][y]);
                        
                        // Updates the UI with the new attendance buttons present
                        swimmingClassStudentRecordPanels[i][x][y].updateUI();
                    }
                }
            }
        }
    }
}
