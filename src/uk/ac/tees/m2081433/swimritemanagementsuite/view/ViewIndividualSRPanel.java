package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LessonBlockController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;

/**
 * This panel holds components for viewing, editing/updating and deleting a student record and
 * to add, edit/update and delete lesson blocks related to the student record.
 */
public class ViewIndividualSRPanel extends JPanel implements ActionListener {
    
    /**
     * The student record to display.
     */
    private final StudentRecord studentRecordRef;
    
    /**
     * The swimrite management suite body panel referenced for re-displaying a the student record.
     */
    private final SMSBodyPanel smsBodyPanelRef;
    
    /**
     * The grid bag constraint to manipulate when adding components to this layout. 
     */
    private final GridBagConstraints c;
    
    /**
     * The Lesson Block controller for when querying the Lesson Block database table.
     */
    private final LessonBlockController lessonBlockController = new LessonBlockController();;
    
    /**
     * Holds all Lesson blocks related to the student record.
     */
    private List<LessonBlock> lessonBlockList;
    
    /**
     * The add button to add a new lesson block to the student record.
     */
    private JButton addLBButton;
    
    /**
     * Panel used to hold the add lb button and to space out components.
     */
    private JPanel addLBButtonPanel;
    

    /**
     * Sets attributes for this layout and calls methods to add components to the layout.
     * @param studentRecord The student record to display on the updateble form.
     * @param smsBodyPanel The body panel to ref to change panel.
     */
    public ViewIndividualSRPanel(StudentRecord studentRecord, SMSBodyPanel smsBodyPanel) {
        // Initialises the student record to be displayed using the student record provided as a param.
        studentRecordRef = studentRecord;
        
        // Initialises the sms Body Panel reference used for changing panels on the body panel
        smsBodyPanelRef = smsBodyPanel;
        
        // Gets the panel width dependant on how many lesson blocks the student record has.
        final int panelWidth = calculatePanelWidth();
        
        // sets the ViewIndividualSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(panelWidth, 565));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // Loads the student record form onto this panel
        loadSRForm();
        
        // Loads the add lesson block button panel onto this panel
        addAddLessonBlockButtonPanel();
        
        // Loads the lesson blocks for this student record
        loadLessonBlocks();
    }
    
    /**
     * Calculates the width of this panel dependant on how may lesson blocks to load up.
     * @return The width of this panel.
     */
    private int calculatePanelWidth() {
        
        // Gets the lesson blocks associated with this student record.
        lessonBlockList = lessonBlockController.getLessonBlocksByStudent(studentRecordRef);
        
        // Sets the standard panel width.
        int panelWidth = 1375;
        
        // If there are more than 1 lesson block associated with the student record add extra width for each panel.
        if (lessonBlockList.size() > 1) {
            panelWidth = panelWidth + (463 * (lessonBlockList.size() - 1));
        }
        
        return panelWidth;
    }
    
    /**
     * Adds the updateable student record panel onto this panel.
     */
    private void loadSRForm() {
        // Creates and initialises the updateable student record panel
        final UpdateableSRPanel updatableSRPanel = new UpdateableSRPanel(studentRecordRef, smsBodyPanelRef);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 0;
        this.add(updatableSRPanel, c);
    }
    
    /**
     * Adds the add a lesson block button panel to this layout.
     */
    private void addAddLessonBlockButtonPanel() {
        // initialises and set the attributes to hold the add lesson block button
        addLBButtonPanel = new JPanel();
        addLBButtonPanel.setBackground(Color.white);
        addLBButtonPanel.setPreferredSize(new Dimension(210, 50));
        addLBButtonPanel.setOpaque(true);
        addLBButtonPanel.setVisible(true);
        
        // Initialises the add lesson block button with its attributes (inc button tooltip and icon)
        addLBButton = new JButton("Add Lesson Block");
        addLBButton.setPreferredSize(new Dimension(150, 45));
        addLBButton.addActionListener(this);
        addLBButton.setToolTipText("<html> Click this button to <b> add </b> a lesson block. </html>");
        addLBButton.setIcon(new ImageIcon("images/icons/add.png"));
        
        // Adds the add lesson block button to the panel
        addLBButtonPanel.add(addLBButton);
        
        // The coordinates for where to add the lesson block button panel component to the layout.
        c.gridx = 1;
        c.gridy = 0;
        this.add(addLBButtonPanel, c);
    }
    
    
    /**
     * Adds the students lesson blocks to this panel.
     */
    private void loadLessonBlocks() {
        
        // Sorts the lesson blocks by their id (descending order/most recently created lesson blocks first).
        lessonBlockList = lessonBlockController.sortLessonBlockssByLessonBlockId(lessonBlockList);
        
        // Iterates thrpigh the lesson block list adding each to this layout.
        for (int i = 0; i < lessonBlockList.size(); i++) {
            // Creats and initialises a lesson block panel to the added to this layout.
            final LessonBlockPanel lessonBlockPanel = new LessonBlockPanel(lessonBlockList.get(i), lessonBlockList.size() - i, smsBodyPanelRef, studentRecordRef);
            
            // The coordinates for where to add this component to the layout.
            c.gridx = 2 + i;
            c.gridy = 0;
            this.add(lessonBlockPanel, c);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // If the add lesson block button is pressed
        if (e.getSource() == addLBButton) {
            // Creates a new lesson block associated with this student record
            final LessonBlock lessonBlock = new LessonBlock(studentRecordRef);
            
            // Create the lesson block in the lesson block database table.
            lessonBlockController.create(lessonBlock);
            
            // Reload this individual students student record.
            smsBodyPanelRef.addViewIndividualSRPanel(studentRecordRef);
        }
    }
}
