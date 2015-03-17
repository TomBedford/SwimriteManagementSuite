package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.TeacherController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;

/**
 * This class is used to Create, Edit and Delete Teachers at Swimrite Leisure.
 */
public class CEDTeachersPanel extends JPanel implements ActionListener {

    /**
     * The SMS Body Panel reference for when swapping panels on the body panel.
     */
    private final SMSBodyPanel smsBodyPanel;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    private final GridBagConstraints c;
    
    /**
     * The Teacher Controller to interact with the Teacher database table.
     */
    private final TeacherController teacherController = new TeacherController();
    
    /**
     * The list to hold all Teachers that teach at Swimrite Leisure.
     */
    private final List<Teacher> teacherList;
    
    /**
     * The array of strings to hold the column headers/names for the teacher table.
     */
    private String[] columnNames;
    
    /**
     * The extended JTable Teachers table displaying all teachers that teach at Swimrite Leisure.
     */
    private TeacherTable teacherTable;
    
    
    
    /**
     * The button to add a teacher.
     */
    private JButton addTeacherButton;
    
    /**
     * The button to edit a teacher.
     */
    private JButton editTeacherButton;
    
    /**
     * The button to delete a teacher.
     */
    private JButton deleteTeacherButton;
    
    
    
    /**
     * Creates the teacher table with buttons to create, edit and delete teachers within the table.
     * @param smsBP The body panel reference so that panels displayed can be changed.
     */
    public CEDTeachersPanel(SMSBodyPanel smsBP) {
        // Initializes this panels body panel reference
        smsBodyPanel = smsBP;
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // sets the create, edit and delete teachers JPanels default attributes
        this.setPreferredSize(new Dimension(1375, 605));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Loads the list of All teachers at swimrite leisure.
        teacherList = teacherController.getAllTeachers();
        
        
        
        // Creates and initializes the title label
        final JLabel titleLabel = new JLabel("Swimrite Teachers");
        titleLabel.setPreferredSize(new Dimension(1200, 100));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.white);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        
        // The coordinates for where to add the spacing component to the layout
        c.gridx = 0;
        c.gridy = 0;
        this.add(titleLabel, c);
        
        
        // Adds the teacher table containing all teachers at swimrite to this panel
        addTeacherTable();
        
        // Adds a button panel to contain all buttons needed for this panel
        addButtonPanel();
        
        
        
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
     * Creates the Teacher table to display all the Teachers that teach at Swimrite Leisure.
     */
    private void addTeacherTable() {
        // The an array of column names for the table
        columnNames = new String[]{" Teacher Name",
                                " Monday",
                                " Tuesday",
                                " Wednesday",
                                " Thursday",
                                " Friday",
                                " Saturday", 
                                " Sunday"};
        
        // Creates a table model to hold the teachers (converted from a list) and the column names
        // This table model is extended so that work days can have image icons if the teacher works on that day
        final TeacherTableModel model = new TeacherTableModel(convertListForTable(), columnNames);
        
        // The teacher JTable (attributes initialised within the class) initialised using the model
        teacherTable = new TeacherTable(model);
        
        // Initializes the scroll pane to hold the teacher table, setting attributes
        final JScrollPane tableScrollPane = new JScrollPane(teacherTable);
        tableScrollPane.setPreferredSize(new Dimension(1200, 400));

        // The coordinates for where to add the table component to the layout
        c.gridx = 0;
        c.gridy = 1;
        this.add(tableScrollPane, c);
    }
    
    /**
     * Converts a list of teachers into a two dimensional array of objects suitable for the teacher tables parameters.
     * @return 2 Dimensional array of object containing the formatted teachers list.
     */
    private Object[][] convertListForTable() {

        /**
         * The two dimensional array to hold the Teachers Records.
         * size of the teacher list is used for the first array size.
         * size of the column names (the amount of different data columns to put into the object for the table)
         */
        final Object[][] tableTeacherData = new Object[teacherList.size()][columnNames.length];
        
        // Loops through the entire teacher list to add the correct data from the list to the object array
        for (int i = 0; i < teacherList.size(); i++) {
            // Adds the teachers name to that index of the object array
            tableTeacherData[i][0] = teacherList.get(i).getTeacherName();
            // Adds the teachers work monday boolean to the object array
            tableTeacherData[i][1] = teacherList.get(i).getWorkMonday();
            // Adds the teachers work tuesday boolean to the object array
            tableTeacherData[i][2] = teacherList.get(i).getWorkTuesday();
            // Adds the teachers work wednesday boolean to the object array
            tableTeacherData[i][3] = teacherList.get(i).getWorkWednesday();
            // Adds the teachers work thuesday boolean to the object array
            tableTeacherData[i][4] = teacherList.get(i).getWorkThursday();
            // Adds the teachers work friday boolean to the object array
            tableTeacherData[i][5] = teacherList.get(i).getWorkFriday();
            // Adds the teachers work saturday boolean to the object array
            tableTeacherData[i][6] = teacherList.get(i).getWorkSaturday();
            // Adds the teachers work sunday boolean to the object array
            tableTeacherData[i][7] = teacherList.get(i).getWorkSunday();
        }
        // Returns the converted 2 dimensional objecty array
        return tableTeacherData;
    }
    
    /**
     * Adds a button panel containing all buttons (Create, Edit and Delete) needed for this panel.
     */
    private void addButtonPanel() {
        // Creates and initializes a label used to put space between table and button panel
        final JLabel spacingLabel2 = new JLabel();
        spacingLabel2.setPreferredSize(new Dimension(1200, 30));
        spacingLabel2.setOpaque(false);
        
        // The coordinates for where to add the spacing component to the layout
        c.gridx = 0;
        c.gridy = 2;
        this.add(spacingLabel2, c);
        
        // Creates and initializes the panel to hold all of the buttons for this panel
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1200, 60));
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.white);
        
        
        // The button to add a teacher to the teacher table and database
        addTeacherButton = new JButton("Add Teacher");
        addTeacherButton.setPreferredSize(new Dimension(200, 50));
        addTeacherButton.addActionListener(this);
        addTeacherButton.setToolTipText("<html> Click this button to <b> add </b> a Teacher. </html>");
        addTeacherButton.setIcon(new ImageIcon("images/icons/user_add.png"));
        
        // Adds the add teacher button to the button panel
        buttonPanel.add(addTeacherButton);
        
        // The button to edit a teacher in the teacher table and database
        editTeacherButton = new JButton("Edit Teacher");
        editTeacherButton.setPreferredSize(new Dimension(200, 50));
        editTeacherButton.addActionListener(this);
        editTeacherButton.setToolTipText("<html> Click this button to <b> edit </b> the seletected Teachers name. </html>");
        editTeacherButton.setIcon(new ImageIcon("images/icons/user_edit.png"));
        
        // Adds the edit a teacher button to the button panel
        buttonPanel.add(editTeacherButton);
        
        // The button to delete a teacher from the teacher table and database
        deleteTeacherButton = new JButton("Delete Teacher");
        deleteTeacherButton.setPreferredSize(new Dimension(200, 50));
        deleteTeacherButton.addActionListener(this);
        deleteTeacherButton.setToolTipText("<html> Click this button to <b> delete </b> the selected Teacher. </html>");
        deleteTeacherButton.setIcon(new ImageIcon("images/icons/user_delete.png"));
        
        // Adds the delete a teacher button to the button panel
        buttonPanel.add(deleteTeacherButton);
        
        
        
        // The coordinates for where to add the button panel component to the layout
        c.gridx = 0;
        c.gridy = 3;
        this.add(buttonPanel, c);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // If the source of the button press was the add button
        if (e.getSource() ==  addTeacherButton) {
            
            // Shows input dialog asking for the teacher to creates name
            final String teacherName = JOptionPane.showInputDialog(null,
                    "<HTML> Please enter the <u> name</u>  of the teacher you want to create.</HTML> \n"
                    , "Enter Teachers Name");

            // If the teacherName is not null create the teacher
            if (teacherName != null) {
                
                // flag to detect if the name entered is a duplicate
                boolean duplicateFlag = false;
                
                for (Teacher t: teacherList) {
                    if (t.getTeacherName().equals(teacherName)) {
                        duplicateFlag = true;
                    }
                }
                
                if (!duplicateFlag) {
                    // Creates a new teacher with the name specified
                    final Teacher newTeacher = new Teacher(teacherName);

                    // Creates the teacher in the database
                    teacherController.create(newTeacher);
                    
                    // Re-loads this panel with the new teacher in the table
                    smsBodyPanel.addViewCEDTeachersPanel();
                } else {
                    // Displays an error message that the teachers name is a duplicate
                    JOptionPane.showMessageDialog(null, "The Teachers name entered is a duplicate! \n"
                            + "When creating a teacher ensure that the teachers name is unique..."
                                                , "Duplicate Teacher Name!", JOptionPane.OK_OPTION);
                }
            }
        // If the source of the button press was the edit button    
        } else if (e.getSource() ==  editTeacherButton) {
            
            // Checks if anything is selected in the table
            if (teacherTable.isColumnSelected(0) || teacherTable.isColumnSelected(1) || teacherTable.isColumnSelected(2)
                    || teacherTable.isColumnSelected(3) || teacherTable.isColumnSelected(4) || teacherTable.isColumnSelected(5) 
                    || teacherTable.isColumnSelected(5) || teacherTable.isColumnSelected(6) || teacherTable.isColumnSelected(7)) {
                
                // Shows input dialog asking for the teachers new name
                final String teacherName = JOptionPane.showInputDialog(null,
                        "<HTML> Please enter the new <u> name</u>  of the Teacher.</HTML> \n"
                        , "Enter The NEW Teachers Name");
                
                // If the teacherName is not null create the teacher
                if (teacherName != null) {

                    // flag to detect if the name entered is a duplicate
                    boolean duplicateFlag = false;

                    for (Teacher t: teacherList) {
                        if (t.getTeacherName().equals(teacherName)) {
                            duplicateFlag = true;
                        }
                    }

                    if (!duplicateFlag) {
                        
                        // Gets the index from the row that is selected/was clicked
                        final int index = teacherTable.getSelectedRow();
                        
                        // Sets the teacher new name
                        teacherList.get(index).setTeacherName(teacherName);
                        
                        // Updates the teacher record in the database
                        teacherController.update(teacherList.get(index));
                        
                        // Re-loads this panel with the edited teacher updated in the table
                        smsBodyPanel.addViewCEDTeachersPanel();
                    } else {
                        // Displays an error message that the teachers name is a duplicate
                        JOptionPane.showMessageDialog(null, "The Teachers name entered is a duplicate! \n"
                                + "When editing a teachers name ensure that the teachers name is unique..."
                                                    , "Duplicate Teacher Name!", JOptionPane.OK_OPTION);
                    }
                }
            } else {
                // Displays an error message that no teacher is currently selected
                JOptionPane.showMessageDialog(null, "No Teacher is selected! \n"
                        + "To edit a Teacher please select the Teacher in the table and click 'Edit Teacher'"
                                            , "No Teacher Selected", JOptionPane.OK_OPTION);
            }
            
        // If the source of the button press was the delete button    
        } else if (e.getSource() ==  deleteTeacherButton) {
            
            // Checks if anything is selected in the table
            if (teacherTable.isColumnSelected(0) || teacherTable.isColumnSelected(1) || teacherTable.isColumnSelected(2)
                    || teacherTable.isColumnSelected(3) || teacherTable.isColumnSelected(4) || teacherTable.isColumnSelected(5) 
                    || teacherTable.isColumnSelected(5) || teacherTable.isColumnSelected(6) || teacherTable.isColumnSelected(7)) {
                
                // Gets the index from the row that is selected/was clicked
                final int index = teacherTable.getSelectedRow();
                
                // Checks if the teacher is currently working on any days
                if (teacherList.get(index).getWorkMonday() == false && teacherList.get(index).getWorkTuesday() == false 
                        && teacherList.get(index).getWorkWednesday() == false && teacherList.get(index).getWorkThursday() == false 
                        && teacherList.get(index).getWorkFriday() == false && teacherList.get(index).getWorkSaturday() == false 
                        && teacherList.get(index).getWorkSunday() == false) {
                
                    // Shows an Option Pane asking if wants to remove teacher from the database
                    final int answer = JOptionPane.showConfirmDialog(null, 
                            "<HTML> Do you want to <b> <font color='red'> delete </font> </b> <u>" 
                                    + teacherList.get(index).getTeacherName()
                                    + "</u>  from the database?</HTML>"
                                    , "Delete Teacher From Database?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    // Switch to determine users choice.
                    switch (answer) {
                        // The user wants delete the teacher
                        case 0: teacherController.delete(teacherList.get(index));
                                // Re-loads this panel with the updated table
                                smsBodyPanel.addViewCEDTeachersPanel();
                                break;

                        // The user does not want to delete the teacher so don't do anything.
                        case 1:  break;
                        default: break;
                    }
                } else {
                    // Displays an error message that a teacher cant be deleted if they are currently teaching on a day
                    JOptionPane.showMessageDialog(null, "You can't delete a teacher that is currently working on a day(s)! \n"
                            + "To delete the teacher make sure they do not work on any days of the week"
                                                , "Teacher Currently Teaches Classes!", JOptionPane.OK_OPTION);
                }
            } else {
                // Displays an error message that no teacher is currently selected
                JOptionPane.showMessageDialog(null, "No Teacher is selected! \n"
                        + "To edit a Teacher please select the Teacher in the table and click 'Edit Teacher'"
                                            , "No Teacher Selected", JOptionPane.OK_OPTION);
            }
        }
    }
}
