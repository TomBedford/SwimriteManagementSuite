package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;

/**
 * The body panel that holds all components that will dynamically swap in and out of view.
 */
public class SMSBodyPanel extends JPanel {
    
    /**
     * The welcome panel displayed on initial load of the application.
     */
    SMSWelcomePanel smsWelcomePanel; 
    
    /**
     * Holds the currently displayed panel.
     */
    JScrollPane currentlyDisplayedPanel;
    
    /**
     * panel to hold dynamically swapping panels.
     */
    public SMSBodyPanel() {
        // sets the smsBodyPanel JPanel attributes.
        this.setPreferredSize(new Dimension(1400, 625));
        this.setVisible(true);
        this.setBackground(Color.white);
        
        // Adds the welcome panel to this body panel.
        addWelcomePanel();
    }
    
    /**
     * Adds a panel to this body panel and sets it as the currently displayed panel.
     * @param panelToDisplay The JPanel to display on the body panel.
     */
    public void addCurrentlyDisplayedPanel(JPanel panelToDisplay) {
        // Adds the panel provided as a param to the currently displayed panel scroll pane and sets the size of it.
        currentlyDisplayedPanel = new JScrollPane(panelToDisplay);
        currentlyDisplayedPanel.setPreferredSize(new Dimension(1380, 615));
        
        // Adds the scroll pane to the body panel and updates the ui.
        this.add(currentlyDisplayedPanel);
        this.updateUI(); 
    }
    
    /**
     * Removes the currently displayed panel on the body panel.
     */
    public void removeCurrentlyDisplayedPanel() {
        // Removes the currently displayed panel.
        this.remove(currentlyDisplayedPanel);
        
        // Updates the UI after the change.
        this.updateUI();   
    }
    
    /**
     * Adds the welcome panel to this body panel.
     */
    public void addWelcomePanel() {
        // initialises the welcome panel.
        smsWelcomePanel = new SMSWelcomePanel();
        
        addCurrentlyDisplayedPanel(smsWelcomePanel);
    }
    
    /**
     * Adds the swimming class schedule panel for a specified day to the body panel.
     * @param day The specified day to load the schedule for.
     */
    public void addSchedulePanel(Day day, Boolean editMode) {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // sets the correct toggle button to the selected on the toolbar (when selected from the menu bar)
        if (day == Day.MONDAY) {
            SMSToolbarPanel.monButton.setSelected(true);
        } else if (day == Day.TUESDAY) {
            SMSToolbarPanel.tueButton.setSelected(true);
        } else if (day == Day.WEDNESDAY) {
            SMSToolbarPanel.wedButton.setSelected(true);
        } else if (day == Day.THURSDAY) {
            SMSToolbarPanel.thuButton.setSelected(true);
        } else if (day == Day.FRIDAY) {
            SMSToolbarPanel.friButton.setSelected(true);
        } else if (day == Day.SATURDAY) {
            SMSToolbarPanel.satButton.setSelected(true);
        } else if (day == Day.SUNDAY) {
            SMSToolbarPanel.sunButton.setSelected(true);
        }
        
        // Creates the schedule panel for the specified day
        DaySchedulePanel daySchedulePanel = new DaySchedulePanel(day, this, editMode);
        
        // Adds the day schedule panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(daySchedulePanel);
    }
    
    public void addAddSRPanel() {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Sets the appropriate button to be selected on the toolbar.
        SMSToolbarPanel.addSRButton.setSelected(true);
        
        // Creates the add student record panel
        AddSRPanel addSRPanel = new AddSRPanel();
        
        // Adds the add student record panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(addSRPanel);
    }
    
    public void addViewAllSRPanel() {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Sets the appropriate button to be selected on the toolbar.
        SMSToolbarPanel.viewAllSRButton.setSelected(true);
        
        // Creates the view all student records panel
        ViewAllSRPanel viewAllSRPanel = new ViewAllSRPanel(this);
        
        // Adds the view all student records panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(viewAllSRPanel);
    }
    
    public void addViewIndividualSRPanel(StudentRecord studentRecord) {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Creates the view individual student record panel sending the student record to be displayed as a param.
        ViewIndividualSRPanel viewIndividualSRPanel = new ViewIndividualSRPanel(studentRecord, this);
        
        // Adds the view individual student record panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(viewIndividualSRPanel);
    }
    
    public void addViewIndividualSCPanel(SwimmingClasses swimmingClass) {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Creates the view individual swimming class panel sending the swimming class to be displayed as a param.
        ViewIndividualSCPanel viewIndividualSCPanel = new ViewIndividualSCPanel(swimmingClass, this);
        
        // Adds the view individual swimming class panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(viewIndividualSCPanel);
    }
    
}
