package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.StudentRecordController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;

/**
 * This panel displays all student records within the Swimrite Management Suite database.
 */
public class ViewAllSRPanel extends JPanel implements ActionListener{
    
    /**
     * The Student Record controller for when inserting records into the Student Record table.
     */
    StudentRecordController studentRecordController;
    
    /**
     * List to contain all the student records to be displayed on the panel.
     */
    List<StudentRecord> studentRecordList;
    
    /**
     * Array of JPanels that hold each individual students details.
     */
    JPanel[] studentRecordPanels;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    public ViewAllSRPanel() {
        // Initialises the student record controller needed to add a student record to the db
        studentRecordController = new StudentRecordController();
        
        // Gets all the student records from the database
        getAllStudentRecords();
        
        // Calculates what the height of the view all student records panel
        int panelHeight = calculateViewAllSRPanelHeight();
        
        // sets the ViewAllSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(1360, panelHeight));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        loadFilterOptions();
        
        loadSearchBar();
        
        addLayoutSpacing();
        
        loadTableHeadings();
        
        loadStudentRecordPanels();
        
        // Invisible label used to push all other components in the layout to the top.
        JLabel pullComponentsUp = new JLabel();
        
        c.gridx = 0;
        c.gridy = 1000;
        c.weighty = 1.0;
        c.fill = 1;
        
        this.add(pullComponentsUp, c);
    }
    
    public void getAllStudentRecords() {
        studentRecordList = studentRecordController.getAllStudentRecords();
    }
    
    public int calculateViewAllSRPanelHeight() {
        // Gets the amount of student records from the size of the student records to display list.
        int amountOfSR = studentRecordList.size();
        
        // Calculates the height of the panel as each student records panels is 50 height.
        int panelHeight = (amountOfSR * 50) +  200;
        
        // If the panel height is less than the smallest required, set it to the smallest size.
        if (panelHeight < 575) {
            panelHeight = 575;
        }
        
        return panelHeight;
    }
    
    public void loadFilterOptions() {
        
        ButtonGroup filterButtonGroup = new ButtonGroup();
        
        JPanel filterButtonPanel = new JPanel();
        filterButtonPanel.setPreferredSize(new Dimension(600, 50));
        filterButtonPanel.setBackground(Color.white);
        filterButtonPanel.setOpaque(true);
        filterButtonPanel.setVisible(true);
        
        JLabel filterLabel = new JLabel("Filter Student Records By:");
        filterLabel.setPreferredSize(new Dimension(175, 50));
        filterLabel.setHorizontalAlignment(SwingConstants.LEFT);
        filterLabel.setBackground(Color.white);
        filterLabel.setOpaque(true);
        filterLabel.setVisible(true);
        
        filterButtonPanel.add(filterLabel);
        
        JRadioButton allFilterButton = new JRadioButton("All");
        allFilterButton.setName("All SR");
        allFilterButton.addActionListener(this);
        allFilterButton.setSelected(true);
        filterButtonGroup.add(allFilterButton);
        filterButtonPanel.add(allFilterButton);
        
        JRadioButton enrolledFilterButton = new JRadioButton("Enrolled");
        enrolledFilterButton.setName("Enrolled SR");
        enrolledFilterButton.addActionListener(this);
        filterButtonGroup.add(enrolledFilterButton);
        filterButtonPanel.add(enrolledFilterButton);
        
        JRadioButton waitingListFilterButton = new JRadioButton("Waiting List");
        waitingListFilterButton.setName("Waiting List SR");
        waitingListFilterButton.addActionListener(this);
        filterButtonGroup.add(waitingListFilterButton);
        filterButtonPanel.add(waitingListFilterButton);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        
        this.add(filterButtonPanel, c);
        
        // Resets the grid width so other components do not also take up other grid spaces
        c.gridwidth = 1;
    }
    
    public void loadSearchBar() {
        JTextField searchSRField = new JTextField(24);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 2;
        c.gridy = 1;
        this.add(searchSRField, c);
        
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(300, 25));
        //searchButton.addActionListener(this);
        searchButton.setToolTipText("<html> Click this button to <b> search </b> all student records by the students name. </html>");
        //searchButton.setIcon(new ImageIcon("images/icons/delete.png"));
        
        // The coordinates for where to add this component to the layout
        c.gridx = 3;
        c.gridy = 1;
        this.add(searchButton, c);
    }
    
    public void addLayoutSpacing() {
        // First invisible label putting spacing between the top of the panel and the search bar
        JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(300, 15));
        spacingLabel1.setOpaque(false);
        spacingLabel1.setVisible(true);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 0;
        this.add(spacingLabel1, c);
        
        // Second invisible label putting spacing between the search bar and the student records table
        JLabel spacingLabel2 = new JLabel();
        spacingLabel2.setPreferredSize(new Dimension(300, 15));
        spacingLabel2.setOpaque(false);
        spacingLabel2.setVisible(true);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 2;
        this.add(spacingLabel2, c);
    }
    
    public void loadTableHeadings() {
        // Label to hold the student name table column header.
        JLabel studentNameColumnLabel = new JLabel("Student Name");
        setLabelAttributes(studentNameColumnLabel);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 3;
        this.add(studentNameColumnLabel, c);
        
        // Label to hold the students date of birth table column header.
        JLabel studentDOBColumnLabel = new JLabel("Date of Birth");
        setLabelAttributes(studentDOBColumnLabel);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 1;
        c.gridy = 3;
        this.add(studentDOBColumnLabel, c);
        
        // Label to hold the students swimming/ability level table column header.
        JLabel swimmingLevelColumnLabel = new JLabel("Swimming/Ability Level");
        setLabelAttributes(swimmingLevelColumnLabel);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 2;
        c.gridy = 3;
        this.add(swimmingLevelColumnLabel, c);
        
        // Label to hold the students current swimming class table column header.
        JLabel swimmingClassColumnLabel = new JLabel("Current Swimming Class");
        setLabelAttributes(swimmingClassColumnLabel);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 3;
        c.gridy = 3;
        this.add(swimmingClassColumnLabel, c);
    }
    
    /**
     * Sets the standard attributes of the labels used for table headings (avoiding repetition).
     * @param label The JLabel to set the standard attributes of
     */
    public void setLabelAttributes(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createRaisedBevelBorder());
        label.setPreferredSize(new Dimension(300, 50));
        label.setOpaque(true);
        label.setVisible(true);
    }
    
    public void loadStudentRecordPanels() {
        
        // Creates an array of labels to hold the details for each student record.
        studentRecordPanels = new JPanel[studentRecordList.size()];
        
        for (int i = 0; i < studentRecordList.size(); i++) {
        
            studentRecordPanels[i] = new JPanel();
            studentRecordPanels[i].setPreferredSize(new Dimension(1200, 50));
            studentRecordPanels[i].setBorder(BorderFactory.createLineBorder(Color.black));
            studentRecordPanels[i].setLayout(new GridLayout(1, 4));
            studentRecordPanels[i].setOpaque(true);
            studentRecordPanels[i].setVisible(true);

            JLabel studentNameLabel = new JLabel(studentRecordList.get(i).getStudentName());
            studentNameLabel.setPreferredSize(new Dimension(300, 50));
            studentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            studentNameLabel.setBackground(Color.white);
            studentNameLabel.setOpaque(true);
            studentNameLabel.setVisible(true);
            studentRecordPanels[i].add(studentNameLabel);
            
            String studentDOB = studentRecordList.get(i).getStudentDOBDay() + "/" + studentRecordList.get(i).getStudentDOBMonth() 
                                    + "/" + studentRecordList.get(i).getStudentDOBYear();

            JLabel studentDOBLabel = new JLabel(studentDOB);
            studentDOBLabel.setPreferredSize(new Dimension(300, 50));
            studentDOBLabel.setHorizontalAlignment(SwingConstants.CENTER);
            studentDOBLabel.setBackground(Color.white);
            studentDOBLabel.setOpaque(true);
            studentDOBLabel.setVisible(true);
            studentRecordPanels[i].add(studentDOBLabel);

            JLabel swimmingLevelLabel = new JLabel("" + studentRecordList.get(i).getSwimmingLevel());
            swimmingLevelLabel.setPreferredSize(new Dimension(300, 50));
            swimmingLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
            swimmingLevelLabel.setBackground(Color.white);
            swimmingLevelLabel.setOpaque(true);
            swimmingLevelLabel.setVisible(true);
            studentRecordPanels[i].add(swimmingLevelLabel);
            
            JLabel swimmingClassLabel = new JLabel();
            swimmingClassLabel.setPreferredSize(new Dimension(290, 50));
            swimmingClassLabel.setHorizontalAlignment(SwingConstants.CENTER);
            swimmingClassLabel.setBackground(Color.white);
            swimmingClassLabel.setOpaque(true);
            swimmingClassLabel.setVisible(true);
            
            if (studentRecordList.get(i).getSwimmingClass() == null) {
                swimmingClassLabel.setText("Waiting List");
            } else {
                swimmingClassLabel.setText("" + studentRecordList.get(i).getSwimmingClass());
            }
            
            studentRecordPanels[i].add(swimmingClassLabel);

            // The coordinates for where to add this component to the layout
            c.gridx = 0;
            c.gridy = 4 + i;
            c.gridwidth = 4;
            this.add(studentRecordPanels[i], c);
        }
        
    }
    
    public void removeCurrentlyDisplayedSR() {
        for (int i = 0; i < studentRecordPanels.length; i++) {
                    this.remove(studentRecordPanels[i]);
                }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Checks what the source of the action event is
        if (e.getSource() instanceof JRadioButton) {
            // If it's a radio button cast the source to the radio button.
            JRadioButton radioButtonPressed = (JRadioButton) e.getSource();
            
            if (radioButtonPressed.getName() == "All SR") {
                System.out.println("ALL");
            } else if (radioButtonPressed.getName() == "Enrolled SR") {
                
                System.out.println("Enrolled");
                
                removeCurrentlyDisplayedSR();
                
                // empty list to store enrolled student records
                studentRecordList.clear();
                
                studentRecordList = studentRecordController.getEnrolledStudentRecords();
                
                this.updateUI();
                
            } else if (radioButtonPressed.getName() == "Waiting List SR") {
                System.out.println("Waiting list");
            }
            
    
        } else if (e.getSource() instanceof JButton) {
        
        }
    
    
    
    
    }
}
