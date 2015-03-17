package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.StudentRecordController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.SwimmingClassesController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;

/**
 * This panel shows the details for an individual swimming class allowing the user to add and remove students from
 * the swimming class whilst also being able to change the swimming class type.
 */
public class ViewIndividualSCPanel extends JPanel implements ActionListener {
    
    /**
     * The Swimming Class to load the details of and manipulate.
     */
    SwimmingClasses swimmingClass;
    
    /**
     * The SMS Body Panel reference for when swapping panels on the body panel.
     */
    SMSBodyPanel smsBodyPanel;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * The Student Record Controller to interact with the Student Record database.
     */
    StudentRecordController studentRecordController = new StudentRecordController();
    
    /**
     * The Swimming Classes Controller to interact with the Swimming Classes database.
     */
    SwimmingClassesController swimmingClassesController = new SwimmingClassesController();
    
    /**
     * The list to hold all student records associated with this swimming class.
     */
    List<StudentRecord> swimmingClassSRList;
    
    /**
     * The array of strings to hold the column headers/names for the swimming class student records table.
     */
    String[] columnNames;
    
    /**
     * The extended JTable swimming class student records table displaying all student records in the swimming class.
     */
    SwimmingClassSRTable swimmingClassSRTable;
    
    /**
     * The label that holds the title of this panel.
     */
    JLabel titleLabel;
    
    
    
    /**
     * The combo box to contain all the different swimming class types.
     */
    JComboBox classTypeComboBox;
    
    /**
     * The panel to hold the change class type button so that buttons can be swapped at runtime.
     */
    JPanel changeClassTypeButtonPanel;
    
    /**
     * The button to change the class type of the swimming class.
     */
    JButton changeClassTypeButton;
    
    /**
     * The button to enroll a student record in this swimming class.
     */
    JButton addSRButton;
    
    /**
     * The button to un-enroll a student record from this swimming class.
     */
    JButton removeSRButton;
    
    /**
     * The button to update the swimming class type of the class.
     */
    JButton updateClassTypeButton;
    
    
    /**
     * Sets the default attributes of this panel and then procedes to calls methods to add vital components
     * to this layout.
     * @param sC The swimming class to load the details from.
     * @param smsBP The body panel reference to swap panels displayed to the user.
     */
    public ViewIndividualSCPanel(SwimmingClasses sC, SMSBodyPanel smsBP) {
        
        // Initializes this panels swimming class to the one to be loaded
        swimmingClass = sC;
        
        // Initializes this panels body panel reference
        smsBodyPanel = smsBP;
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // sets the View Inidividual Swimming Class JPanels default attributes
        this.setPreferredSize(new Dimension(1375, 605));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Loads the list of All student records in this swimming class
        swimmingClassSRList = studentRecordController.getSwimmingClassStudentRecords(swimmingClass);
        
        
        
        // Adds the title of this swimming class to this panel
        addSwimmingClassTitle();
        
        // Adds a table containing this classes students to this panel
        addSwimmingClassStudentsTable();
        
        // Change class type button and adds add & remove students from the class buttons
        addEditingButtonPanel();
        
        // Invisible label used to push all other components in the layout to the top.
        final JLabel pullComponentsUp = new JLabel();
        
        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 99999;
        c.weighty = 1.0;
        c.fill = 1;
        this.add(pullComponentsUp, c);
        
        // Resets these values so as not to mess with the placement of other layout components that are loaded on runtime
        c.weighty = 0;
        c.fill = 0;
    }
    
    /**
     * Adds a title panel containing two title labels with text about the swimming class to the top of this panel.
     */
    public void addSwimmingClassTitle() {
        
        // Creates and initializes the panel to hold the title text labels
        final JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(1200, 100));
        titlePanel.setVisible(true);
        titlePanel.setBackground(Color.white);
        titlePanel.setLayout(new BorderLayout());
        
        // initializes the label to hold the swimming class day, time and class type
        titleLabel = new JLabel();
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        titleLabel.setBackground(Color.white);
        titleLabel.setPreferredSize(new Dimension(1200, 50));
        titleLabel.setOpaque(true);
        titleLabel.setVisible(true);
        
        // Calls the method to set the title text of the title text label.
        setTitleLabelText();
        
        // Adds the title label to the top/north of the title panel
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        
        
        
        // Creates and initializes the teacher label to hold the teachers name for the class
        final JLabel teacherLabel = new JLabel(swimmingClass.getTeacher().getTeacherName());
        teacherLabel.setHorizontalAlignment(SwingConstants.CENTER);
        teacherLabel.setVerticalAlignment(SwingConstants.TOP);
        teacherLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        teacherLabel.setBackground(Color.white);
        teacherLabel.setPreferredSize(new Dimension(1200, 30));
        teacherLabel.setOpaque(true);
        teacherLabel.setVisible(true);
        
        // Adds the teacher label to the center of the title panel
        titlePanel.add(teacherLabel, BorderLayout.CENTER);
        
        
        // The coordinates for where to add the title panel component to the layout.
        c.gridx = 0;
        c.gridy = 0;
        
        this.add(titlePanel, c);
    }
    
    /**
     * Sets the title label text for this swimming class panel.
     */
    public void setTitleLabelText() {
        // Adds the class day to the title text string
        String titleText = swimmingClass.getTimeslot().getDay() + " ";
        
        // Converts the timeslot time into a string.
        final String defaultTime = Integer.toString(swimmingClass.getTimeslot().getTime());
        
        // Formats the time string with a colon so that it looks like a time (eg, 1300 -> 13:00)
        final String formattedTime = new StringBuilder(defaultTime).insert(defaultTime.length() - 2, ":").toString();
        
        // Adds the formatted swimming class time to the title text string
        titleText = titleText + formattedTime + " - ";
        
        // Adds the class type to the title text string
        titleText = titleText + swimmingClass.getClassType();
        
        // Sets the text for the title label to the title text
        titleLabel.setText(titleText);
        
        this.updateUI();
    }
       
    /**
     * Creates the swimming class table to display all the students currently enrolled on this swimming class.
     */
    public void addSwimmingClassStudentsTable() {
        // The an array of column names for the table
        columnNames = new String[]{" Student Name",
                                " Date of Birth",
                                " Current Swimming Level"};
        
        // Creates a table model to hold the student records (converted from a list) in this class and the column names
        final TableModel model = new DefaultTableModel(convertListForTable(swimmingClassSRList), columnNames);
        
        // The swimming class student record JTable (attributes initialised within the class) initialised using the model
        swimmingClassSRTable = new SwimmingClassSRTable(model);
        
        // Initializes the scroll pane to hold the swimming class student record table, setting attributes
        final JScrollPane tableScrollPane = new JScrollPane(swimmingClassSRTable);
        tableScrollPane.setPreferredSize(new Dimension(1200, 255));

        // The coordinates for where to add the table component to the layout
        c.gridx = 0;
        c.gridy = 1;
        this.add(tableScrollPane, c);
    }
    
    /**
     * Converts a list of student records into a two dimensional array of objects suitable for the swimming class
     * student records tables parameters.
     * @param studentRecordList The list of student records to convert
     * @return 2 Dimensional array of object containing the formatted student records list.
     */
    public Object[][] convertListForTable(List<StudentRecord> studentRecordList) {

        /**
         * The two dimensional array to hold the student records.
         * size of the student record list is used for the first array size.
         * size of the column names (the amount of different data columns to put into the object for the table)
         */
        final Object[][] tableStudentRecordData = new Object[studentRecordList.size()][columnNames.length];
        
        // Loops through the entire student record list to add the correct data from the list to the object array
        for (int i = 0; i < studentRecordList.size(); i++) {
            // Adds the students name to that index of the object array
            tableStudentRecordData[i][0] = studentRecordList.get(i).getStudentName();
            // Adds the students date of birth to that index of the object array
            tableStudentRecordData[i][1] = studentRecordList.get(i).getStudentDOB();
            // Adds the students swimming level to that index of the object array
            tableStudentRecordData[i][2] = studentRecordList.get(i).getSwimmingLevel();
        }
        // Returns the converted 2 dimensional objecty array
        return tableStudentRecordData;
    }
       
    /**
     * Adds a button panel containing buttons that allow the user to:
     * - Change the swimming class type
     * - Add Students to the class
     * - Remove students from the class.
     */
    public void addEditingButtonPanel() {
        // Creates and initializes a label used to put space between table and edit panel buttons
        final JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(1200, 50));
        spacingLabel1.setOpaque(false);
        
        // The coordinates for where to add the spacing component to the layout
        c.gridx = 0;
        c.gridy = 2;
        this.add(spacingLabel1, c);
        
        
        
        // Creates and initializes the panel to hold the the editing options (buttons & combo box)
        final JPanel editingPanel = new JPanel();
        editingPanel.setPreferredSize(new Dimension(1200, 60));
        editingPanel.setVisible(true);
        editingPanel.setBackground(Color.white);
        
        
        // Initializes the combo box containing the values of all different swimming levels
        classTypeComboBox = new JComboBox(SwimmingLevel.values());
        classTypeComboBox.setSelectedItem(swimmingClass.getClassType());
        classTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        classTypeComboBox.setPreferredSize(new Dimension(225, 50));
        classTypeComboBox.setEnabled(false);
        
        // Adds the class type combo box to the editing button panel
        editingPanel.add(classTypeComboBox);
        
        
        // Creates and initializes the panel to hold change class type button (for when swapping buttons on this panel)
        changeClassTypeButtonPanel = new JPanel();
        changeClassTypeButtonPanel.setPreferredSize(new Dimension(230, 50));
        changeClassTypeButtonPanel.setVisible(true);
        changeClassTypeButtonPanel.setBackground(Color.white);
        changeClassTypeButtonPanel.setLayout(new GridLayout(1, 1));
        
        // The change class type button to change the swimming classes class type
        changeClassTypeButton = new JButton("Change Swimming Class Type");
        changeClassTypeButton.setPreferredSize(new Dimension(225, 45));
        changeClassTypeButton.addActionListener(this);
        changeClassTypeButton.setToolTipText("<html> Click this button to <b> change </b> the class type of this swimming class. </html>");
        changeClassTypeButton.setIcon(new ImageIcon("images/icons/bullet_edit.png"));
        
        // Adds the change class type button to the change class type button panel
        changeClassTypeButtonPanel.add(changeClassTypeButton);
        
        // Adds the change class type button panel to the editing button panel
        editingPanel.add(changeClassTypeButtonPanel);
        
        
        
        // Creates and initializes the label to put space between the change class type button and the add, remove sr buttons
        final JLabel spacingLabel2 = new JLabel();
        spacingLabel2.setPreferredSize(new Dimension(280, 50));
        spacingLabel2.setOpaque(false);
        
        editingPanel.add(spacingLabel2);
        
        
        
        // The button to enroll a student in this swimming class
        addSRButton = new JButton("Add Student To Class");
        addSRButton.setPreferredSize(new Dimension(220, 50));
        addSRButton.addActionListener(this);
        addSRButton.setToolTipText("<html> Click this button to <b> add/enroll </b> a student in this swimming class. </html>");
        addSRButton.setIcon(new ImageIcon("images/icons/add.png"));
        
        // Adds the add student record button to the editing button panel
        editingPanel.add(addSRButton);
        
        // The button to un-enroll a student from this swimming class
        removeSRButton = new JButton("Remove Student From Class");
        removeSRButton.setPreferredSize(new Dimension(220, 50));
        removeSRButton.addActionListener(this);
        removeSRButton.setToolTipText("<html> Click this button to <b> remove/un-enroll </b> the selected student from this swimming class. </html>");
        removeSRButton.setIcon(new ImageIcon("images/icons/delete.png"));
        
        // Adds the remove student record button to the editing button panel
        editingPanel.add(removeSRButton);
        
        
        
        // The coordinates for where to add the editing panel component to the layout
        c.gridx = 0;
        c.gridy = 3;
        this.add(editingPanel, c);
    }
    
    /**
     * Disables the editing of the swimming classes type by making the combo box disabled and removing the update button.
     */
    public void disableClassTypeEditing() {
        // Disables the combo box from use
        classTypeComboBox.setEnabled(false);

        // Remove the update class type button from the panel.
        changeClassTypeButtonPanel.remove(updateClassTypeButton);

        // The change class type button to change the swimming classes class type
        changeClassTypeButton = new JButton("Change Swimming Class Type");
        changeClassTypeButton.setPreferredSize(new Dimension(225, 45));
        changeClassTypeButton.addActionListener(this);
        changeClassTypeButton.setToolTipText("<html> Click this button to <b> change </b> the class type of this swimming class. </html>");
        changeClassTypeButton.setIcon(new ImageIcon("images/icons/bullet_edit.png"));

        // Adds the change class type button to the change class type button panel
        changeClassTypeButtonPanel.add(changeClassTypeButton);

        this.updateUI();
    }
    
    /**
     * Attempts to add/enroll a student record in this swimming class.
     */
    public void attemptAddSR() {
        // If the current capacity of the class is not equal to the max capacity then add a student.
        if (swimmingClass.getCurrentCapacity() != swimmingClass.getMaxCapacity()) {
            
            // Initializes the pop up frame for the user to select the new student to add to the class.
            final AddSRToSCFrame addSRToSCFrame = new AddSRToSCFrame(swimmingClass, smsBodyPanel);
            
        } else {
            // Displays an error message that the class is at maximum capacity
            JOptionPane.showMessageDialog(null, "<HTML> The swimming class is at <b> <font color='red'> maximum </font> </b> capacity. \n"
                    + "To add a new student please remove a currently enrolled student. \n"
                                            , "Swimming Class is FULL", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the source of the button press was the change class type button
        if (e.getSource() == changeClassTypeButton) {
            
            // Remove the change class type button from the panel.
            changeClassTypeButtonPanel.remove(changeClassTypeButton);
            
            // The button to update the swimming class type of the class
            updateClassTypeButton = new JButton("Update Swimming Class Type");
            updateClassTypeButton.setPreferredSize(new Dimension(200, 50));
            updateClassTypeButton.addActionListener(this);
            updateClassTypeButton.setToolTipText("<html> Click this button to <b> update </b> the class type of this swimming class. </html>");
            updateClassTypeButton.setIcon(new ImageIcon("images/icons/accept.png"));
            
            // Adds the update swimming class type button to the change class type panel
            changeClassTypeButtonPanel.add(updateClassTypeButton);
            
            // Enables the combo box so the user can select the new class type
            classTypeComboBox.setEnabled(true);
            
            this.updateUI();
            
        // If the source of the button press was the add student button
        } else if (e.getSource() ==  addSRButton) {
            
            // Attempts to add a student record to the swimming class
            attemptAddSR();
            
        // If the source of the button press was the remove student button
        } else if (e.getSource() ==  removeSRButton) {
            
            // Checks if anything is selected in the table
            if (swimmingClassSRTable.isColumnSelected(0) || swimmingClassSRTable.isColumnSelected(1) || swimmingClassSRTable.isColumnSelected(2)) {
                
                // Gets the index from the row that is selected/was clicked
                final int index = swimmingClassSRTable.getSelectedRow();
                
                // Shows an Option Pane asking if wants to remove the student from the class
                final int answer = JOptionPane.showConfirmDialog(null, 
                        "<HTML> Do you want to <b> <font color='red'> remove </font> </b> <u>" 
                                + swimmingClassSRList.get(index).getStudentName() 
                                + "</u>  from this swimming class?</HTML>"
                                , "Remove Student From Swimming Class?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
                // Switch to determine users choice.
                switch (answer) {
                    // The user wants remove the student from the class
                    case 0: // Sets the student records swimming class to null
                            swimmingClassSRList.get(index).setSwimmingClass(null);

                            // Updates the student record in the database.
                            studentRecordController.update(swimmingClassSRList.get(index));

                            // Deincrements the current capacity of the swimming class
                            swimmingClass.setCurrentCapacity(swimmingClass.getCurrentCapacity() - 1);

                            // Updates the swimming class in the database
                            swimmingClassesController.update(swimmingClass);

                            // Re-loads the panel for the specified swimming class with the new student enrolled
                            smsBodyPanel.addViewIndividualSCPanel(swimmingClass);
                            
                             break;
                        
                    // The user does not want to remove the student record from the class so don't do anything.
                    case 1:  break;
                    default: break;
                }
            } else {
                // Displays an error message that no student record is currently selected
                JOptionPane.showMessageDialog(null, "<HTML> No Student Record is selected! \n"
                        + "To remove a student record from this class please select the record and click 'Remove Student From Class'"
                                            , "No Student Record Selected", JOptionPane.OK_OPTION);
            }
            
        // If the source of the button press was the update class type button    
        } else if (e.getSource() ==  updateClassTypeButton) {
           
            // If the selected class type has changed
            if (classTypeComboBox.getSelectedItem() != swimmingClass.getClassType()) {
                
                // Sets the new class type of the swimming class
                swimmingClass.setClassType((SwimmingLevel) classTypeComboBox.getSelectedItem());
                
                // Updates the swimming class in the database
                swimmingClassesController.update(swimmingClass);
                
                disableClassTypeEditing();
                
                // Calls the method to set the new title text of the title text label.
                setTitleLabelText();
            } else {
                disableClassTypeEditing();
            }
        }
    }
}
