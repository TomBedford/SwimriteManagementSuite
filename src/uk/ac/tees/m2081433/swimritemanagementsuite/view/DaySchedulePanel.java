package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.StudentRecordController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.SwimmingClassesController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.TimeslotController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * This class dynamically displays the swimming class schedule for the specified day.
 */
public class DaySchedulePanel extends JPanel implements ActionListener, MouseListener {
    
    /**
     * The day to load the swimming schedule for.
     */
    Day day;
    
    /**
     * Reference to the body panel to re-load this panel and to direct to view individual swimming class.
     */
    SMSBodyPanel smsBodyPanelRef;
    
    /**
     * The Teacher controller for when running db queries on the Teacher table.
     */
    TeacherController teacherController = new TeacherController();
    
    /**
     * The Timeslot controller for when running db queries on the Timeslot table.
     */
    TimeslotController timeslotController = new TimeslotController();
    
    /**
     * The Swimming Classes controller for when running db queries on the Swimming Classes table.
     */
    SwimmingClassesController swimmingClassesController = new SwimmingClassesController();
    
    /**
     * The Student Record controller for when running db queries on the Student Record table.
     */
    StudentRecordController studentRecordController = new StudentRecordController();
    
    /**
     * Holds all Teachers for the specified day.
     */
    List<Teacher> workingTeacherList;
    
    /**
     * Holds all Teachers that dont work on the specified day.
     */
    List<Teacher> notWorkingTeacherList;
    
    /**
     * Holds all Timeslots for the specified day.
     */
    List<Timeslot> timeslotList;
    
    /**
     * Holds all Swimming Classes for the specified day.
     */
    List<SwimmingClasses> swimmingClassesList;
    
    /**
     * The array of labels that holds the swimming classes for the first teacher column.
     */
    JLabel[] teacher1ClassesColumn;
    
    /**
     * The array of labels that holds the swimming classes for the second teacher column.
     */
    JLabel[] teacher2ClassesColumn;
    
    /**
     * The array of labels that holds the swimming classes for the third teacher column.
     */
    JLabel[] teacher3ClassesColumn;
    
    /**
     * The array of array list to hold the different swimming classes by teacher.
     */
    ArrayList<SwimmingClasses>[] teacherClassesList;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    
    
    /**
     * The colour used for the headers background.
     */
    private Color smsBlue = new Color(172, 172, 255);
    
    /**
     * The font for all headers in the table.
     */
    Font tableHeaderFont = new Font("Arial", Font.PLAIN, 18);
    
    /**
     * The font for all records/rows in the table.
     */
    Font tableRecordFont = new Font("Arial", Font.PLAIN, 15);
    
    
    
    /**
     * The panel to hold all editing buttons on for the day schedule.
     */
    JPanel buttonPanel;
        
    /**
     * The button to start editing the day schedule panel.
     */
    JButton editButton;
    
    /**
     * The button to update the day schedule panel with any changes done during editing.
     */
    JButton updateButton;
    
    /**
     * The button to exit editing the day schedule panel.
     */
    JButton cancelEditButton;
    
    /**
     * The button to add a Timeslot to the day schedule panel.
     */
    JButton addTimeslotButton;
    
    /**
     * The panels to hold each timeslot time and deletion button on.
     */
    JPanel[] timeslotColumn;
        
    /**
     * The delete buttons for the timeslot panels.
     */
    JButton[] deleteTimeslotButton;
    
    /**
     * The panel to hold the teacher name column headers.
     */
    JPanel[] teacherHeader;
    
    /**
     * The labels to hold the text for each teachers name on the column header panels.
     */
    JLabel[] teacherHeaderText;
    
    /**
     * The array of combo boxes for when editing the teachers of swimming classes.
     */
    JComboBox[] teacherNameList;
    
    
    
    /**
     * Dynamically loads the Swimming Class schedule for the day specified as the parameter.
     * @param dayToLoad The specified day to load the swimming class schedule.
     * @param smsBodyPanel The smsBodyPanel reference to swap panels on the body panel.
     * @param editMode whether this panel should be loaded into edit mode.
     */
    public DaySchedulePanel(Day dayToLoad, SMSBodyPanel smsBodyPanel, Boolean editMode) {
        
        // Sets the day to load the swimming class schedule for
        day = dayToLoad;
        
        // Sets the smsBodyPanel reference to swap out panels
        smsBodyPanelRef = smsBodyPanel;
        
        // Method to get all the timeslots for the specified day (needed info to work out panel size etc.)
        getTimeslotsForDay();
        
        // Calculates what the height of the day schedule panel should be.
        final int panelHeight = calculateDaySchedulePanelHeight();
        
        // sets the smsMainPanel JPanel attributes.
        this.setPreferredSize(new Dimension(1360, panelHeight));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint.
        c = new GridBagConstraints();
        
        // Method to load the entire swimming class schedule for that day
        loadSchedule();
        
        // Method to add the editing button panel containing buttons to edit this day schedule panel
        addEditingButtonPanel();
        
        // Checks if the panel should be loaded into edit mode
        if (editMode) {
            enterEditingMode();
        }
        
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
     * Gets all the timeslots associated with the specified day.
     * This is needed so the height of the panel can be calculated and whether an error message
     * is needed to be displayed if the default class schedules have not been initialized.
     */
    public void getTimeslotsForDay() {
        // Gets all the timeslots for the specified day.
        timeslotList = timeslotController.getTimeslotsForDay(day);
    }
    
    /**
     * Calculates the height that the day Schedule Panel should be.
     * @return panelHeight The height that the day schedule panel should be.
     */
    public int calculateDaySchedulePanelHeight() {
        // Gets the amount of timeslots from the size of the timeslot list.
        final int amountOfTimeslots = timeslotList.size();
        
        // Calculates the height of the panel as each timeslot label is 150 height (adds 100 padding).
        final int panelHeight = (amountOfTimeslots * 150) +  100;
        
        return panelHeight;
    }
    
    /**
     * This method calls each individual method to load different parts of the schedule.
     */
    public void loadSchedule() {
        
        // adds the timeslot column title to the schedule.
        addTimeslotColumnHeader();
        
        // adds all teachers that work on the specified day to the schedule.
        addTeachersColumnHeaders();
        
        // adds all the timeslots on the specified day to the schedule and tells it that edit mode is false.
        addTimeslotColumn();
        
        // adds all swimming classes on the specified day to the schedule.
        addSwimmingClasses();
    }
    
    /**
     * Adds the timeslot column title of the class schedule table, or displays an error message
     * depending on whether there is data to load.
     */
    public void addTimeslotColumnHeader() {
        
        // if the timeslot list is empty display db initialisation error msg.
        if (timeslotList.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Please initialise the database to start! \n"
                    + "The database initialisation settings are located within the \n"
                    + "'Advanced Settings' Menu tab.", "Default Database not Initialised", JOptionPane.ERROR_MESSAGE);

        } else {
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
    public void addTeachersColumnHeaders() {
        // Gets all the teachers for the specified day
        workingTeacherList = teacherController.getTeachersForDay(day);
        
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
    public void addTimeslotColumn() {
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
            timeslotColumn[i].setPreferredSize(new Dimension(100, 150));
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
     * Adds all swimming classes to the swimming class schedule for the specified day.
     */
    public void addSwimmingClasses() {
        
        // Gets all swimming classes for the specified day.
        swimmingClassesList = swimmingClassesController.getClassesByDay(day);
        
        // Sorts the classes by the timeslot time.
        swimmingClassesList = swimmingClassesController.sortClassesByTimeslotTime(swimmingClassesList);
        
        // Sorts all of the swimming classes by the teacher Id (Ascending order).
        swimmingClassesList = swimmingClassesController.sortClassesByTeacherId(swimmingClassesList);
        
        // an array of array list, as each array list will hold one teachers classes for that day.
        teacherClassesList = new ArrayList[workingTeacherList.size()];
        
        // Loops through the amount of teachers working on that day and initializes the array.
        for (int i = 0; i < workingTeacherList.size(); i++) {
            teacherClassesList[i] = new ArrayList();
        }
        
        // Loops through the entire list of swimming classes putting them in the correct list for each teacher.
        for (SwimmingClasses sc : swimmingClassesList) {
            if (sc.getTeacher().getTeacherId() == workingTeacherList.get(0).getTeacherId()) {
                teacherClassesList[0].add(sc);
            } else if (sc.getTeacher().getTeacherId() == workingTeacherList.get(1).getTeacherId()) {
                teacherClassesList[1].add(sc);
            } else if (sc.getTeacher().getTeacherId() == workingTeacherList.get(2).getTeacherId()) {
                teacherClassesList[2].add(sc);
            } 
        }
        
        // Creates an array of labels to hold the details for the swimming classes for the first teacher
        teacher1ClassesColumn = new JLabel[teacherClassesList[0].size()];
        
        // Iterate through the swimming classes list for teacher 1 to add all classes to the schedule.
        for (int i = 0; i < teacherClassesList[0].size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacherClassesList[0].get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher1ClassesColumn[i] = new JLabel();
                teacher1ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher1ClassesColumn[i].setOpaque(true);
                teacher1ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher1ClassesColumn[i].setLayout(new BorderLayout());
                teacher1ClassesColumn[i].setVisible(true);
                
                // Gets the text to go on this label (text: student names in class and class type)
                final String labelText = getTextForClassLabel(teacherClassesList[0].get(i));
                
                // Sets the text for the label, its font and its position
                teacher1ClassesColumn[i].setText(labelText);
                teacher1ClassesColumn[i].setFont(tableRecordFont);
                teacher1ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                
                // Adds a mouse listener to the label for when clicked
                teacher1ClassesColumn[i].addMouseListener(this);
                
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
        teacher2ClassesColumn = new JLabel[teacherClassesList[1].size()];
        
        // Iterate through the swimming classes list for teacher 2 to add all classes to the schedule.
        for (int i = 0; i < teacherClassesList[1].size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacherClassesList[1].get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher2ClassesColumn[i] = new JLabel();
                teacher2ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher2ClassesColumn[i].setOpaque(true);
                teacher2ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher2ClassesColumn[i].setLayout(new BorderLayout());
                teacher2ClassesColumn[i].setVisible(true);
                
                // Gets the text to go on this label (text: student names in class and class type)
                final String labelText = getTextForClassLabel(teacherClassesList[1].get(i));
                
                // Sets the text for the label, its font and its position
                teacher2ClassesColumn[i].setText(labelText);
                teacher2ClassesColumn[i].setFont(tableRecordFont);
                teacher2ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                
                // Adds a mouse listener to the label for when clicked
                teacher2ClassesColumn[i].addMouseListener(this);
                
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
        teacher3ClassesColumn = new JLabel[teacherClassesList[2].size()];
        
        // Iterate through the swimming classes list for teacher 3 to add all classes to the schedule.
        for (int i = 0; i < teacherClassesList[2].size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacherClassesList[2].get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher3ClassesColumn[i] = new JLabel();
                teacher3ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher3ClassesColumn[i].setOpaque(true);
                teacher3ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher3ClassesColumn[i].setLayout(new BorderLayout());
                teacher3ClassesColumn[i].setVisible(true);
                
                // Gets the text to go on this label (text: student names in class and class type)
                final String labelText = getTextForClassLabel(teacherClassesList[2].get(i));
                
                // Sets the text for the label, its font and its position
                teacher3ClassesColumn[i].setText(labelText);
                teacher3ClassesColumn[i].setFont(tableRecordFont);
                teacher3ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                
                // Adds a mouse listener to the label for when clicked
                teacher3ClassesColumn[i].addMouseListener(this);
                
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
    
    /**
     * Gets the text to go on the swimming class label records in the table.
     * @param swimmingClass The swimming class to generate the text for
     * @return labelText The text to go on the label
     */
    public String getTextForClassLabel(SwimmingClasses swimmingClass) {
        // Creates a string to hold the label text
        String labelText = "";
        
        // Gets all student records that are in this swimming class
        final List<StudentRecord> swimmingClassStudentRecordList = studentRecordController.getSwimmingClassStudentRecords(swimmingClass);
        
        // Adds all student records in this class to the text to be displayed on the label
        for (StudentRecord studentRecord: swimmingClassStudentRecordList) {
            labelText = labelText + "<HTML> <br>" + studentRecord.getStudentName();
        }
        
        // Adds blank lines so that spacing between student names and the class type remain the same if class size is not 5.
        for (int i = swimmingClassStudentRecordList.size(); i < 5; i++) {
            labelText = labelText + "<HTML> <br>";
        }
        
        // Adds a blank line between the student names and the swimming class
        labelText = labelText + "<HTML> <br>";
        
        // Adds the swimming class type to the text to be displayed on the label
        labelText = labelText + "<HTML> <br> <font color='gray'>" + swimmingClass.getClassType();
        
        return labelText;
    }
    
    /**
     * Adds the button panel to allow the user to enter editing mode.
     */
    public void addEditingButtonPanel() {
        // Creates/initialises the button panel to hold the editing buttons
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(250, 200));
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.white);
        
        // Creates the edit button to enter editing mode
        editButton = new JButton("Enter Editing Mode");
        editButton.setPreferredSize(new Dimension(175, 45));
        editButton.addActionListener(this);
        editButton.setToolTipText("<html> Click this button to <b> edit </b> the swimming class schedule. </html>");
        editButton.setIcon(new ImageIcon("images/icons/bullet_edit.png"));
        
        // Adds the edit button to the button panel
        buttonPanel.add(editButton);
        
        // The Gridbag Layout constraints logic to add the label to the correct x & y grid space.
        c.gridx = workingTeacherList.size() + 1;
        c.gridy = 0;
        c.gridheight = 2;
                
        // Adds the button panel to the correct space in the gridbag layout.
        this.add(buttonPanel, c);
        
        // Resets the grid height for when adding other components to this layout
        c.gridheight = 1;
    }
    
    /**
     * Method to enter the Day Schedule Panel into editing mode so that;
     * -Teachers
     * -Timeslots
     * can be edited.
     */
    public void enterEditingMode() {
        // Removes the edit button from the button panel
        buttonPanel.remove(editButton);
        
        // Creates/Initializes the update button to go on the button panel (to update changes to the schedule)
        updateButton = new JButton("Update Schedule!");
        updateButton.setPreferredSize(new Dimension(175, 45));
        updateButton.addActionListener(this);
        updateButton.setToolTipText("<html> Click this button to cancel <b> editting </b> the swimming class schedule. </html>");
        updateButton.setIcon(new ImageIcon("images/icons/accept.png"));
        
        // Adds the stop edit button to the button panel
        buttonPanel.add(updateButton);
        
        // Creates/Initializes the cancel button to go on the button panel (to cancel changes to the schedule)
        cancelEditButton = new JButton("Cancel Editing Done");
        cancelEditButton.setPreferredSize(new Dimension(175, 45));
        cancelEditButton.addActionListener(this);
        cancelEditButton.setToolTipText("<html> Click this button to cancel <b> editting </b> the swimming class schedule. </html>");
        cancelEditButton.setIcon(new ImageIcon("images/icons/cancel.png"));
        
        // Adds the stop edit button to the button panel
        buttonPanel.add(cancelEditButton);
        
        // Creates/initialises a label used to space out the update and cancel buttons from the add timeslot button
        final JLabel spacingLabel = new JLabel();
        spacingLabel.setPreferredSize(new Dimension(175, 30));
        spacingLabel.setOpaque(false);
        
        // Adds the spacing label to the button panel
        buttonPanel.add(spacingLabel);
        
        // Creates/Initializes the add timeslot button to go on the button panel
        addTimeslotButton = new JButton("Add a Timeslot");
        addTimeslotButton.setPreferredSize(new Dimension(175, 45));
        addTimeslotButton.addActionListener(this);
        addTimeslotButton.setToolTipText("<html> Click this button to <b> add </b> a timeslot to the swimming class schedule. </html>");
        addTimeslotButton.setIcon(new ImageIcon("images/icons/clock_add.png"));
        
        // Adds the add timeslot button to the button panel
        buttonPanel.add(addTimeslotButton);
        
        // Calls method to change the teachers column headers into combo boxes allowing editing of class teachers
        addTeacherEditComboBox();
         
        // Calls method to add delete buttons to each timeslot panel so that timeslots can be deleted
        addTimeslotDeleteButtons();
        
        this.updateUI();
    }
    
    /**
     * Adds the combo boxes to the teacher name headers so that the teacher for the swimming classes in that column
     * can be edited.
     */
    public void addTeacherEditComboBox() {
        // Loops through the teacher list removing the teacher name text label
        for (int i = 0; i < workingTeacherList.size(); i++) {
            teacherHeader[i].remove(teacherHeaderText[i]);
        }
        
        // Initializes the size of the combo box array equivelant to the amount fo teachers
        teacherNameList = new JComboBox[workingTeacherList.size()];
        
        // Gets all the teachers not working on the specified day.
        notWorkingTeacherList = teacherController.getTeachersNotForDay(day);
        
        // Creates an array of string to hold the teachers that dont work on this day to be added to the combo box for selection
        final String[] convertedNotWorkingTeacherList = new String[notWorkingTeacherList.size()];
        
        // Adds each teacher that doesnt work on this day to the array of string
        for (int i = 0; i < notWorkingTeacherList.size(); i++) {
            convertedNotWorkingTeacherList[i] = notWorkingTeacherList.get(i).getTeacherName();
        }
        
        // Loops through the size of the teachers working on this day
        for (int i = 0; i < workingTeacherList.size(); i++) {
            // Creates a combo box for each teacher column header allowing editing of that columns swimming class teacher
            teacherNameList[i] = new JComboBox(convertedNotWorkingTeacherList);
            teacherNameList[i].addItem(workingTeacherList.get(i).getTeacherName());
            teacherNameList[i].setSelectedItem(workingTeacherList.get(i).getTeacherName());
            teacherNameList[i].setFont(tableHeaderFont);
            teacherNameList[i].setPreferredSize(new Dimension(200, 35));
            
            // Text will appear in the centre of the combo box
            ((JLabel) teacherNameList[i].getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            
            teacherHeader[i].add(teacherNameList[i]);
        }
    }
    
    /**
     * Adds delete buttons to the timeslot column panels allowing each timeslot to be deleted.
     */
    public void addTimeslotDeleteButtons() {
        // Creates an array of buttons to hold the delete buttons for the timeslots
        deleteTimeslotButton = new JButton[timeslotList.size()];
        
        // Loops through the timeslots list and adds a delete button onto each timeslots panel
        for (int i = 0; i < timeslotList.size(); i++) {
            deleteTimeslotButton[i] = new JButton("Delete");
            deleteTimeslotButton[i].setPreferredSize(new Dimension(90, 30));
            deleteTimeslotButton[i].addActionListener(this);
            deleteTimeslotButton[i].setToolTipText("<html> Click this button to <b> delete </b> this timeslot "
                    + "and associated swimming classes from this days swimming class schedule. </html>");
            deleteTimeslotButton[i].setIcon(new ImageIcon("images/icons/16x16/clock_delete.png"));
            deleteTimeslotButton[i].setAlignmentX(Component.LEFT_ALIGNMENT); 
            timeslotColumn[i].add(deleteTimeslotButton[i], BorderLayout.PAGE_END);
        }
    }
    
    /**
     * Method that runs when the update button is pressed, attempts to update the schedule in the database
     * with the new edited values (of the teacher column header).
     */
    public void checkForUpdates() {
        // Creates a flag used to detect if the teachers of any swimming class column have changed
        final boolean[] changeFlag = new boolean[teacherNameList.length];

        // Loops through the array of combo boxes checking if the values have changed since editing
        for (int i = 0; i < teacherNameList.length; i++) {
            if (workingTeacherList.get(i).getTeacherName().equals((String) teacherNameList[i].getSelectedItem())) {
                changeFlag[i] = false;
            } else {
                changeFlag[i] = true;
            }
        }

        // If there has been a change with any of the swimming class teachers columns
        if (booleanArrayContains(changeFlag)) {
            // If there are not any duplicate values
            if (!checkComboBoxesForDuplicates()) {
                // Loops through all the teachers working on this day
                for (int i = 0; i < teacherNameList.length; i++) {
                    // If the value of that index in the combo box list has changed...
                    if (changeFlag[i]) {

                        // Creates a teacher object to hold the teacher that is selected by the combo box
                        Teacher teacher = null;

                        // Loops through all not working teachers until one teachers name matches and sets them to teacher.
                        for (Teacher t : notWorkingTeacherList) {
                            if (t.getTeacherName().equals(teacherNameList[i].getSelectedItem())) {
                                teacher = t;
                                break;
                            }
                        }

                        // Finds what day it is and removes old teacher and sets new teacher to working on that day
                        if (day == Day.MONDAY) {
                            workingTeacherList.get(i).setWorkMonday(false);
                            teacher.setWorkMonday(true);
                        } else if (day == Day.TUESDAY) {
                            workingTeacherList.get(i).setWorkTuesday(false);
                            teacher.setWorkTuesday(true);
                        } else if (day == Day.WEDNESDAY) {
                            workingTeacherList.get(i).setWorkWednesday(false);
                            teacher.setWorkWednesday(true);
                        } else if (day == Day.THURSDAY) {
                            workingTeacherList.get(i).setWorkThursday(false);
                            teacher.setWorkThursday(true);
                        } else if (day == Day.FRIDAY) {
                            workingTeacherList.get(i).setWorkFriday(false);
                            teacher.setWorkFriday(true);
                        } else if (day == Day.SATURDAY) {
                            workingTeacherList.get(i).setWorkSaturday(false);
                            teacher.setWorkSaturday(true);
                        } else if (day == Day.SUNDAY) {
                            workingTeacherList.get(i).setWorkSunday(false);
                            teacher.setWorkSunday(true);
                        }

                        // Updates both teacher records
                        teacherController.updateTeacher(workingTeacherList.get(i));
                        teacherController.updateTeacher(teacher);

                        // Loops through the entire list of classes for that teacher and changes to the new teacher.
                        for (int x = 0; x < teacherClassesList[i].size(); x++) {
                            teacherClassesList[i].get(x).setTeacher(teacher);
                            // Updates the swimming class record with new teacher
                            swimmingClassesController.updateSwimmingClass(teacherClassesList[i].get(x));
                        }

                        // Re-loads this panel to finish and load changes.
                        smsBodyPanelRef.addSchedulePanel(day, false);
                    }
                }
            // A single teacher is teaching multiple column classes on one day so display error.
            } else {
                JOptionPane.showMessageDialog(null, "<HTML> There is a teacher teaching <b> <font color='red'> more than one </font> </b> column of classes! "
                        + "<HTML> A teacher can only teach <u>one</u> class on a single day! </HTML> \n", "Incorrect Timeslot time", JOptionPane.OK_OPTION);
            }
        } else {
            // No changes made in editing so reload normal panel
            smsBodyPanelRef.addSchedulePanel(day, false);
        }
    }

    /**
     * Checks if any of the combo boxes contain duplicate values.
     * @return whether they contain duplicates.
     */
    public boolean checkComboBoxesForDuplicates() {
        // creates an array to hold boolean duplicate flags for each teacher working on this day
        final boolean[] duplicate = new boolean[teacherNameList.length];
        
        // check if any of the strings are the same in the array
        for (int i = 0; i < teacherNameList.length; i++) {
            // Initialises to true because 1 duplicate in comparison (compared against its own index in the array) 
            duplicate[i] = true;
            for (int x = 0; x < teacherNameList.length; x++) {
                if (teacherNameList[i].getSelectedItem().equals(teacherNameList[x].getSelectedItem())) {
                    // flips the boolean if there is a duplicate (so if no duplicates ALL should be false)
                    duplicate[i] = !duplicate[i];
                }
            }
        }
        return booleanArrayContains(duplicate);
    }
    
    /**
     * Checks if any index in an array of booleans contains true.
     * @param booleanFlag The array of booleans to check
     * @return Whether n index in the array contains true or not
     */
    public boolean booleanArrayContains(boolean[] booleanFlag) {
        // Loops through the entire boolean array.
        for (boolean flag: booleanFlag) {
            // If any of them are true, return true.
            if (flag) { 
                return true;
            }
        }
        // If this statement is reached then no indexs are true, so return false
        return false;
    }
    
    /**
     * Method that runs when the add timeslot button is pressed, attempts to add a new timeslot to 
     * the schedule including the creation of enough empty swimming classes for each teacher that teaches on
     * this day in the database.
     */
    public void addTimeslot() {
        String timeslotTimeString = null;
            
            // Shows input dialog asking for the new timeslot time to create
            timeslotTimeString = JOptionPane.showInputDialog(null,
                    "<HTML> Please enter the <b> new </b> Timeslot time to create! </HTML> \n"
                            + "<HTML> The timeslot time must be in <font color='red'> 24 hour time </font> using a colon ':' </HTML>\n"
                            + "(eg, 14:00)", 
                    "Enter new Timeslot time");

            // If the timeslot time entered is not equal to null (if they didnt click 'cancel' button) do...
            if (timeslotTimeString != null) {
                
                // Checks the input for the user is valid (contains numbers and ':') and must be format 00:00
                if (timeslotTimeString.matches("[0-9:]*") && timeslotTimeString.matches("\\d{2}:\\d{2}")) {
                    
                    // Removes the colon from the string
                    timeslotTimeString = timeslotTimeString.replace(":", "");
                    
                    // Converts the timeslot time string into an int
                    final int timeslotTimeInt = Integer.parseInt(timeslotTimeString);
                    
                    // Creates/Initialises a duplicate flag to false (no dupes)
                    boolean duplicateFlag = false;
                    
                    // Loops through the list of timeslots for this day checking if any of them have the same time
                    for (Timeslot timeslot: timeslotList) {
                        if (timeslot.getTime() == timeslotTimeInt) {
                            // If one does have the same time, change the duplicate flag to true
                            duplicateFlag = true;
                        }
                    }
                    
                    // Checks if the duplicate flag is false
                    if (!duplicateFlag) {
                    
                        // Creates/Initializes a new timeslot for this day with the time specified by the suer
                        final Timeslot newTimeslot = new Timeslot(day, timeslotTimeInt);

                        // Creates the timeslot in the database
                        timeslotController.createTimeslot(newTimeslot);

                        // Loops through the amount of teachers working on this day and creates a swimming class for that timeslot for them
                        for (int i = 0; i < workingTeacherList.size(); i++) {
                            final SwimmingClasses swimmingClass = new SwimmingClasses(SwimmingLevel.MOMS_AND_DUCKS, 
                                                                        newTimeslot, workingTeacherList.get(i), 5);
                            swimmingClassesController.createSwimmingClass(swimmingClass);
                        }

                        // Re-loads this panel with the values newly stored in the database
                        smsBodyPanelRef.addSchedulePanel(day, true);
                    } else {
                        // If there is a duplicate timeslot time entered, display error message
                        JOptionPane.showMessageDialog(null, "<HTML> The Timeslot you entered was a <b> <font color='red'> duplicate time </font> </b> of an existing timeslot. \n"
                                + "Please enter a unique timeslot time!\n"
                                            , "Incorrect Timeslot time", JOptionPane.OK_OPTION);
                    }
                    
                } else {
                    // if the timeslot time was an invalid input, display error message
                    JOptionPane.showMessageDialog(null, "<HTML> The Timeslot you entered was an <b> <font color='red'> invalid </font> </b> timeslot time. </HTML> \n"
                                            , "Incorrect Timeslot time", JOptionPane.OK_OPTION);
                }
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        // If the source of the button press was the edit button...
        if (e.getSource() == editButton) {
            
            // Calls the method to enter the day schedule panel into editing mode
            enterEditingMode();
        
        // If the source of the button press was the update button...
        } else if (e.getSource() == updateButton) {
            
            // Calls the method to attempt to update the schedule in the database with the changes made
            checkForUpdates();
         
        // If the source of the button press was the cancel editing button...
        } else if (e.getSource() == cancelEditButton) {
            
            // Reloads the origional panel without saving any changes
            smsBodyPanelRef.addSchedulePanel(day, false);
         
        // If the source of the button press was the add timeslot button button...
        } else if (e.getSource() == addTimeslotButton) {
            
            // Calls method to add a timeslot to this days schedule in the database
            addTimeslot();
        
        // If the source was none of those then it should be a delete timeslot button
        } else {
            // Loop through all delete buttons checking if the source came from that button
            for (int i = 0; i < deleteTimeslotButton.length; i++) {
                if (e.getSource() == deleteTimeslotButton[i]) {
                    
                    // Shows an Option Pane confirming if wanting to delete the chosen timeslot
                    final int answer = JOptionPane.showConfirmDialog(null, 
                            "<HTML> Do you want to <b> <font color='red'> delete </font> </b>this timeslot and all swimming classes associated with it?!</HTML> \n"
                                    , "Delete Timeslot and associated Swimming Classes?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    // Switch to determine users choice.
                    switch (answer) {
                        // The user wants to delete that timeslot from the db.
                        case 0: timeslotController.deleteTimeslot(timeslotList.get(i));
                                // Reloads the schedule for the day with the new database values
                                smsBodyPanelRef.addSchedulePanel(day, true);
                                break;
                        // The user does not want to format the db so don't do anything.
                        case 1: break;
                        default:break;
                    }
                }
            }
        }
    
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Checks the mouse was double clicked
        if (e.getClickCount() == 2) {
            // loops through the first column label array checking if it was the source of the mouse click
            for (int i = 0; i < teacher1ClassesColumn.length; i++) {
                if (e.getSource() == teacher1ClassesColumn[i]) {
                    // Loads the appropriate swimming class that was clicking into the view inidividual swimming class panel
                    smsBodyPanelRef.addViewIndividualSCPanel(teacherClassesList[0].get(i));
                }
            }
            // loops through the second column label array checking if it was the source of the mouse click
            for (int i = 0; i < teacher2ClassesColumn.length; i++) {
                if (e.getSource() == teacher2ClassesColumn[i]) {
                    // Loads the appropriate swimming class that was clicking into the view inidividual swimming class panel
                    smsBodyPanelRef.addViewIndividualSCPanel(teacherClassesList[1].get(i));
                }
            }
            // loops through the third column label array checking if it was the source of the mouse click
            for (int i = 0; i < teacher3ClassesColumn.length; i++) {
                if (e.getSource() == teacher3ClassesColumn[i]) {
                    // Loads the appropriate swimming class that was clicking into the view inidividual swimming class panel
                    smsBodyPanelRef.addViewIndividualSCPanel(teacherClassesList[2].get(i));
                }
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
