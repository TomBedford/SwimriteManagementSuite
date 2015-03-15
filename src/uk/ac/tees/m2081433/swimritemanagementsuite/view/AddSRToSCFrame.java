package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.StudentRecordController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.SwimmingClassesController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;

/**
 * Loads a table of all Student Records where one can be selected to add to the swimming class.
 */
public class AddSRToSCFrame extends JFrame implements ActionListener, KeyListener, MouseListener {
    
    /**
     * The swimming class to add the selected student record to.
     */
    SwimmingClasses swimmingClass;
    
    /**
     * The body panel reference to refresh the swimming classes panel once a new student has been added.
     */
    SMSBodyPanel smsBodyPanel;
    
    /**
     * The panel that holds all components on this frame.
     */
    JPanel addSRToSCPanel;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * Button to filter student records by all students.
     */
    JButton searchButton;
    
    /**
     * The search student records (by student name) text input field.
     */
    JTextField searchSRField;
    
    /**
     * List to contain all the student records to be displayed on the panel.
     */
    List<StudentRecord> studentRecordList;
    
    /**
     * The Student Record controller for querying the Student Record database table.
     */
    StudentRecordController studentRecordController = new StudentRecordController();
    
    /**
     * The Swimming Classes controller for updating the swimming class in the database.
     */
    SwimmingClassesController swimmingClassesController = new SwimmingClassesController();
    
    /**
     * The array of strings to hold the column headers/names for the swimming class student records table.
     */
    String[] columnNames;
    
    /**
     * The extended JTable specifically for holding student records.
     */
    StudentRecordTable srTable;
    
    /**
     * Sorting object for filtering students by student name and filtering by column heading.
     */
    TableRowSorter<TableModel> sorter;
    
    /**
     * The button to add the selected student record to the swimming class.
     */
    JButton addSRToSCButton;
    
    /**
     * The button to cancel adding a student to the swimming class.
     */
    JButton cancelAddSRToSCButton;
    
    
    
    /**
     * Loads a search bar, table of all student records and add & cancel buttons to allow the user to select a 
     * student record to enroll on the swimming class provided as a param.
     * @param sc The swimming class to add the student record to
     * @param smsBP The reference to the body panel to refresh the view on the individual sc once a new sr has been added.
     */
    public AddSRToSCFrame(SwimmingClasses sc, SMSBodyPanel smsBP) {
        
        // Initializes the swimming class to add the selected student record to
        swimmingClass = sc;
        
        // Initializes the body panel so that the swimming class panel can be refreshed once a new student is added
        smsBodyPanel = smsBP;
        
        // settings for the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1210, 550);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Add/Enroll a Student in this Swimming Class");
        
        // sets the AddSRToSCPanel JPanels default attributes
        addSRToSCPanel = new JPanel();
        addSRToSCPanel.setPreferredSize(new Dimension(1210, 550));
        addSRToSCPanel.setVisible(true);
        addSRToSCPanel.setBackground(Color.white);
        addSRToSCPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        addSRToSCPanel.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // Adds the search text field and search button to the panel
        addSearchBar();
        
        // Adds the student record table to the panel
        addStudentRecordTable();
        
        // Adds the add & cancel buttons to a panel that is added to this panel.
        addFooterButtonPanel();
        
        // Adds the panel to the frame
        this.add(addSRToSCPanel);
    }
    
    /**
     * Adds a text field (used for searching student records by student name) and a search button 
     * to this panel.
     */
    public void addSearchBar() {
        // Panel to hold the search table components
        final JPanel searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(1205, 50));
        searchPanel.setVisible(true);
        searchPanel.setBackground(Color.white);
        
        
        // The text field used for inputing search/filter text
        searchSRField = new JTextField(24);
        // Adds a key listener so the user can press 'enter' to search/filter
        searchSRField.addKeyListener(this);
        
        searchPanel.add(searchSRField);
        
        
        // The search button, setting attributes 
        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(300, 25));
        searchButton.addActionListener(this);
        searchButton.setToolTipText("<html> Click this button to <b> search </b> all student records by the students name. </html>");
        
        searchPanel.add(searchButton);
        
        
        
        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 0;
        addSRToSCPanel.add(searchPanel, c);
    }
    
    /**
     * Loads a list of student records into the table that is then displayed on this panel.
     */
    public void addStudentRecordTable() {
        // Gets all the student records from the database and puts into list
        studentRecordList = studentRecordController.getAllStudentRecords();
        
        // The an array of column names for the table
        columnNames = new String[]{" Student Name",
                                " Date of Birth",
                                " Current Swimming Level",
                                " Swimming Class"};
        
        // Creates a table model to hold the student records (converted from a list) and the column names
        final TableModel model = new DefaultTableModel(convertListForTable(studentRecordList), columnNames);
        
        // The student record JTable (attributes initialised within the class) initialised using the model
        srTable = new StudentRecordTable(model);
        
        // Adds a mouse listener for when users want to see the details of a specific student record
        srTable.addMouseListener(this);
        
        // Initializes a sorter to filter table rows (student records by student name)
        sorter = new TableRowSorter(model);
        
        // Adds the sorter for the table
        srTable.setRowSorter(sorter);
        
        // Initializes the scroll pane to hold the student record table, setting attributes
        final JScrollPane tableScrollPane = new JScrollPane(srTable);
        tableScrollPane.setPreferredSize(new Dimension(1200, 400));

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 1;
        addSRToSCPanel.add(tableScrollPane, c);
    }
    
    /**
     * Converts a list of student records into a two dimensional array of objects suitable for the student records
     * tables parameters.
     * @param studentRecordList The list of student records to convert
     * @return 2 Dimensional array of object containing the formatted student records list.
     */
    public Object[][] convertListForTable(List<StudentRecord> studentRecordList) {

        /**
         * The two dimensional array to hold the student records.
         * size of the student record list is used for the first array size.
         * size of the column names (the amount of different data columns to put into the object for the table)
         */
        final Object[][] tableStudentRecord = new Object[studentRecordList.size()][columnNames.length];
        
        // Loops through the entire student record list to add the correct data from the list to the object array
        for (int i = 0; i < studentRecordList.size(); i++) {
            // Adds the students name to that index of the object array
            tableStudentRecord[i][0] = studentRecordList.get(i).getStudentName();
            // Adds the students date of birth to that index of the object array
            tableStudentRecord[i][1] = studentRecordList.get(i).getStudentDOB();
            // Adds the students swimming level to that index of the object array
            tableStudentRecord[i][2] = studentRecordList.get(i).getSwimmingLevel();
            
            // The string to hold the swimming class text for the student record.
            String swimmingClassText;
            
            // If the student is not in a swimming class display 'Waiting List', otherwise...
            if (studentRecordList.get(i).getSwimmingClass() == null) {
                swimmingClassText = "Waiting List";
            } else {
                // Converts the timeslot time into a string.
                final String defaultTime = Integer.toString(studentRecordList.get(i).getSwimmingClass().getTimeslot().getTime());
                
                // Formats the time string with a colon so that it looks like a time (eg, 1300 -> 13:00)
                final String formattedTime = new StringBuilder(defaultTime).insert(defaultTime.length() - 2, ":").toString();
                
                // Concatinates the time and day together 
                swimmingClassText = formattedTime + " " + studentRecordList.get(i).getSwimmingClass().getTimeslot().getDay();
            }
            // Adds the students swimming class to that index of the object array
            tableStudentRecord[i][3] = swimmingClassText;
        }
        // Returns the converted 2 dimensional objecty array
        return tableStudentRecord;
    }
    
    /**
     * Method to filter records in the student records table by the input from the search text field.
     */
    public void searchStudentRecordTable() {
        // Gets the text from the search text field
        final String searchText = searchSRField.getText();
                
        // If the search text is empty filter by nothing (showing all records in the list)
        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            // Otherwise search/filter using the search text (ignoring case), searching on column 0 (student name)
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0));
        }
    }
    
    /**
     * Creates a panel at the bottom of this panel to hold the add selected student to swimming class button.
     */
    public void addFooterButtonPanel() {
        // Panel to hold the add selected student button
        final JPanel footerPanel = new JPanel();
        footerPanel.setPreferredSize(new Dimension(1205, 60));
        footerPanel.setVisible(true);
        footerPanel.setBackground(Color.white);
        
        // The add selected student button, setting attributes 
        addSRToSCButton = new JButton("Add Selected Student");
        addSRToSCButton.setPreferredSize(new Dimension(200, 50));
        addSRToSCButton.addActionListener(this);
        addSRToSCButton.setToolTipText("<html> Click this button to <b> Add </b> the selected student record to the swimming class. </html>");
        addSRToSCButton.setIcon(new ImageIcon("images/icons/user_add.png"));
        
        // Adds the add selected student button to the footer panel
        footerPanel.add(addSRToSCButton);
        
        // The add selected student button, setting attributes 
        cancelAddSRToSCButton = new JButton("Cancel");
        cancelAddSRToSCButton.setPreferredSize(new Dimension(200, 50));
        cancelAddSRToSCButton.addActionListener(this);
        cancelAddSRToSCButton.setToolTipText("<html> Click this button to <b> Add </b> the selected student record to the swimming class. </html>");
        cancelAddSRToSCButton.setIcon(new ImageIcon("images/icons/cancel.png"));
        
        // Adds the add selected student button to the footer panel
        footerPanel.add(cancelAddSRToSCButton);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 2;
        addSRToSCPanel.add(footerPanel, c);
    }
    
    /**
     * Method to add the selected student record to the swimming class and updates both in the database.
     */
    public void addStudentRecordToSwimmingClass() {
        // Gets the index from the row that is selected/was clicked
        final int index = srTable.getSelectedRow();

        /**
         * If results are filtered then line below is needed to covert the selected index of the row into the real index
         * of the row in the data model to be used to get the correct student record.
         */
        final int realIndex = srTable.convertRowIndexToModel(index);

        // Flag used to check whether the student should be added to the swimming class
        boolean addStudent = true;

        // If the student is currently in a swimming class
        if (studentRecordList.get(realIndex).getSwimmingClass() != null) {

            // If the class the student is being added to is already in that class
            if (studentRecordList.get(realIndex).getSwimmingClass().getClassId() == swimmingClass.getClassId()) {
                // The student is already in this class so dont add student
                addStudent = false;
            } else {
                // Deincrement the swimming classes current capactiy by 1
                studentRecordList.get(realIndex).getSwimmingClass().setCurrentCapacity(
                        studentRecordList.get(realIndex).getSwimmingClass().getCurrentCapacity() - 1);

                // Update the swimming class in the database
                swimmingClassesController.updateSwimmingClass(studentRecordList.get(realIndex).getSwimmingClass());
            }
        }

        // Checks if the student should be added to the swimming class
        if (addStudent) {
            // Sets the student records new swimming class they are enrolled on.
            studentRecordList.get(realIndex).setSwimmingClass(swimmingClass);

            // Updates the student record in the database.
            studentRecordController.updateStudentRecord(studentRecordList.get(realIndex));

            // Increments the current capacity of the swimming class
            swimmingClass.setCurrentCapacity(swimmingClass.getCurrentCapacity() + 1);

            // Updates the swimming class in the database
            swimmingClassesController.updateSwimmingClass(swimmingClass);

            // Re-loads the panel for the specified swimming class with the new student enrolled
            smsBodyPanel.addViewIndividualSCPanel(swimmingClass);

            // Closes this frame as all processes on this frame have been completed.
            this.dispose();
        } else {
            // Displays an error message that the student is already in this swimming class
            JOptionPane.showMessageDialog(null, "The Student Record is already enrolled in this swimming class!", "Student Already Enrolled in this Class!", JOptionPane.OK_OPTION);
        }
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchButton) {
            // Calls the method to filter the table results by the input in the search text field.
            searchStudentRecordTable();
        } else if (e.getSource() == addSRToSCButton) {
            
            // Checks if anything is selected in the table
            if (srTable.isColumnSelected(0) || srTable.isColumnSelected(1) || srTable.isColumnSelected(2) || srTable.isColumnSelected(3)) {
                
                // Adds the selected student record to the swimming class 
                addStudentRecordToSwimmingClass();
                
            } else {
                // Displays an error message that no student record is currently selected
                JOptionPane.showMessageDialog(null, "<HTML> No Student Record is selected!\n"
                        + "To add a student record please 'double click' the student or select the record and click 'Add Selected Student'"
                                            , "No Student Record Selected", JOptionPane.OK_OPTION);
            }
        // If the cancel button is pressed    
        } else if (e.getSource() == cancelAddSRToSCButton) {
            // Close this frame
            this.dispose(); 
        }
    }

    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // If the key pressed was the 'Enter' key
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Calls the method to search/filter the student records in the table by the text field input
            searchStudentRecordTable();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // Checks if it was a double click
        if (e.getClickCount() == 2) { 
            
            // Adds the selected student record to the swimming class 
            addStudentRecordToSwimmingClass();
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
