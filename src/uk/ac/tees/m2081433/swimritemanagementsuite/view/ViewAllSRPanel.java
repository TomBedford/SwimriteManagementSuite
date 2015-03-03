package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.StudentRecordController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;

/**
 * This panel displays all student records within the Swimrite Management Suite database.
 */
public class ViewAllSRPanel extends JPanel implements ActionListener, KeyListener {
    
    /**
     * The Student Record controller for when inserting records into the Student Record table.
     */
    StudentRecordController studentRecordController;
    
    /**
     * List to contain all the student records to be displayed on the panel.
     */
    List<StudentRecord> studentRecordList;
    
    /**
     * Radio button to filter student records by all students.
     */
    JRadioButton allFilterButton;
       
    /**
     * Radio button to filter student records by enrolled students.
     */
    JRadioButton enrolledFilterButton;
        
    /**
     * Radio button to filter student records by unenrolled (waiting list) students.
     */
    JRadioButton waitingListFilterButton;
    
    /**
     * Button to filter student records by all students.
     */
    JButton searchButton;
    
    /**
     * The search student records (by student name) text input field.
     */
    JTextField searchSRField;
    
    /**
     * Scroll Pane to hold the student record table within.
     */
    JScrollPane tableScrollPane;
    
    /**
     * Sorting object for filtering students by student name and filtering by column heading.
     */
    TableRowSorter<TableModel> sorter;
    
    /**
     * An array of String to hold the column names for the student record table.
     */
    String[] columnNames;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    
    
    /**
     * Panel that displays student records in a table with a variety of filtering options.
     */
    public ViewAllSRPanel() {
        // Initialises the student record controller needed to add a student record to the db
        studentRecordController = new StudentRecordController();
        
        // Gets all the student records from the database and puts into list
        studentRecordList = studentRecordController.getAllStudentRecords();
        
        // sets the ViewAllSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(1360, 565));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        
        // Loads the filtering option (all, enrolled and waiting list) components onto this layout
        loadFilterOptions();
        
        // Loads the search bar components onto this layout
        loadSearchBar();
        
        // Adds spacing components to the layout for aesthetic purposes
        addLayoutSpacing();
        
        // Loads the student records table component (using all student records) and adds it to this layout.
        loadSRTable(studentRecordList);
        
        
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
     * Adds a 'filter by' title label and the radio button filter options (all, enrolled and waiting list)
     * to a panel to be placed on this layout. 
     */
    public void loadFilterOptions() {
        
        // Button group for the radio button filter options
        final ButtonGroup filterButtonGroup = new ButtonGroup();
        
        // The filter toolbar panel to hold the 'filter by' title label and filter options on, setting attributes
        final JPanel filterToolbarPanel = new JPanel();
        // note: panel width is double the standard width of a column so gridwidth = 2 to stretch across two cells.
        filterToolbarPanel.setPreferredSize(new Dimension(600, 50));
        filterToolbarPanel.setBackground(Color.white);
        filterToolbarPanel.setOpaque(true);
        filterToolbarPanel.setVisible(true);
        
        // The filter label containing the 'filter by' title text, setting attributes 
        final JLabel filterLabel = new JLabel("Filter Student Records By:");
        filterLabel.setPreferredSize(new Dimension(175, 50));
        filterLabel.setHorizontalAlignment(SwingConstants.LEFT);
        filterLabel.setBackground(Color.white);
        filterLabel.setOpaque(true);
        filterLabel.setVisible(true);
        
        // Adds the filter label to the filter toolbar panel
        filterToolbarPanel.add(filterLabel);
        
        /**
         * The filter button for the option 'All' to display all student records in the table. 
         * setting listener, default selected, adding to the button group and adding it to the panel.
         */
        allFilterButton = new JRadioButton("All");
        allFilterButton.addActionListener(this);
        allFilterButton.setSelected(true);
        filterButtonGroup.add(allFilterButton);
        filterToolbarPanel.add(allFilterButton);
        
        /**
         * The filter button for the option 'Enrolled' to display enrolled student records in the table. 
         * setting listener, default selected, adding to the button group and adding it to the panel.
         */
        enrolledFilterButton = new JRadioButton("Enrolled");
        enrolledFilterButton.addActionListener(this);
        filterButtonGroup.add(enrolledFilterButton);
        filterToolbarPanel.add(enrolledFilterButton);
        
        /**
         * The filter button for the option 'Waiting List' to display students currently unenrolled from
         * a swimming class to display in the table. 
         * setting listener, default selected, adding to the button group and adding it to the panel.
         */
        waitingListFilterButton = new JRadioButton("Waiting List");
        waitingListFilterButton.addActionListener(this);
        filterButtonGroup.add(waitingListFilterButton);
        filterToolbarPanel.add(waitingListFilterButton);
        
        // The layout constraints to place the filter toolbar panel correctly on this panel.
        c.gridx = 0;
        c.gridy = 1;
        // Allows the panel to stretch across two grids.
        c.gridwidth = 2;
        this.add(filterToolbarPanel, c);
        
        // Resets the grid width so other components do not also take up other grid spaces
        c.gridwidth = 1;
    }
    
    /**
     * Adds a text field (used for searching student records by student name) and a search button 
     * to this panel.
     */
    public void loadSearchBar() {
        // The text field used for inputing search/filter text
        searchSRField = new JTextField(24);
        // Adds a key listener so the user can press 'enter' to search/filter
        searchSRField.addKeyListener(this);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 2;
        c.gridy = 1;
        this.add(searchSRField, c);
        
        // The search button, setting attributes 
        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(300, 25));
        searchButton.addActionListener(this);
        searchButton.setToolTipText("<html> Click this button to <b> search </b> all student records by the students name. </html>");
        
        // The coordinates for where to add this component to the layout
        c.gridx = 3;
        c.gridy = 1;
        this.add(searchButton, c);
    }
    
    /**
     * Adds spacing components to this panel for aesthetic purposes.
     */
    public void addLayoutSpacing() {
        // First invisible label putting spacing between the top of the panel and the search bar
        final JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(300, 15));
        spacingLabel1.setOpaque(false);
        spacingLabel1.setVisible(true);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 0;
        this.add(spacingLabel1, c);
        
        // Second invisible label putting spacing between the search bar and the student records table
        final JLabel spacingLabel2 = new JLabel();
        spacingLabel2.setPreferredSize(new Dimension(300, 15));
        spacingLabel2.setOpaque(false);
        spacingLabel2.setVisible(true);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 2;
        this.add(spacingLabel2, c);
    }
    
    /**
     * Loads a list of student records into the table that is then displayed on this panel.
     * @param srListToDisplay The list of student records to load into the table.
     */
    public void loadSRTable(List<StudentRecord> srListToDisplay) {
        
        // The an array of column names for the table
        columnNames = new String[]{" Student Name",
                                " Date of Birth",
                                " Current Swimming Level",
                                " Swimming Class"};
        
        // Creates a table model to hold the student records (converted from a list) and the column names
        final TableModel model = new DefaultTableModel(convertListForTable(srListToDisplay), columnNames);
        
        // The student record JTable (attributes initialised within the class) initialised using the model
        final StudentRecordTable srTable = new StudentRecordTable(model);
        
        // Initializes a sorter to filter table rows (student records by student name)
        sorter = new TableRowSorter<TableModel>(model);
        
        // Adds the sorter for the table
        srTable.setRowSorter(sorter);
        
        // Initializes the scroll pane to hold the student record table, setting attributes
        tableScrollPane = new JScrollPane(srTable);
        tableScrollPane.setPreferredSize(new Dimension(1200, 475));

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        this.add(tableScrollPane, c);
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
     * Action Listener for when filter option radio buttons or the search button is pressed.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Checks what the source of the action event is
        if (e.getSource() instanceof JRadioButton) {
            // If it's a radio button cast the source to a radio button
            final JRadioButton radioButtonPressed = (JRadioButton) e.getSource();
            
            // Checks which radio button has had the action
            if (radioButtonPressed == allFilterButton) {
                
                // removes the current table from this layout
                this.remove(tableScrollPane);
                
                // Clears the list of student records
                studentRecordList.clear();
                
                // Loads the list of All student records
                studentRecordList = studentRecordController.getAllStudentRecords();
                
                // loads the student record table with the new list
                loadSRTable(studentRecordList);
                
                // Updates the graphical user interface (GUI)
                this.updateUI();
                
            } else if (radioButtonPressed == enrolledFilterButton) {
                
                // removes the current table from this layout
                this.remove(tableScrollPane);
                
                // Clears the list of student records
                studentRecordList.clear();
                
                // Loads the list of all Enrolled student records
                studentRecordList = studentRecordController.getEnrolledStudentRecords();
                
                // loads the student record table with the new list
                loadSRTable(studentRecordList);
                
                // Updates the graphical user interface (GUI)
                this.updateUI();
                
            } else if (radioButtonPressed == waitingListFilterButton) {
                
                // removes the current table from this layout
                this.remove(tableScrollPane);
                
                // Clears the list of student records
                studentRecordList.clear();
                
                // Loads the list of all unenrolled/waiting list student records
                studentRecordList = studentRecordController.getWaitingListStudentRecords();
                
                // loads the student record table with the new list
                loadSRTable(studentRecordList);
                
                // Updates the graphical user interface (GUI)
                this.updateUI();
            }
        } else if (e.getSource() instanceof JButton) {
            // If it's a button cast the source to a button
            final JButton buttonPressed = (JButton) e.getSource();
            
            // Checks that the button pressed was the search button
            if (buttonPressed == searchButton) {
                // Calls the method to search/filter the student records in the table by the text field input
                searchStudentRecordTable();
            }
        }
    }

    /**
     * Key Pressed Listener for when the enter key is pressed in the search text field to search.
     * @param e the key pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // If the key pressed was the 'Enter' key
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Calls the method to search/filter the student records in the table by the text field input
            searchStudentRecordTable();
        }
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
