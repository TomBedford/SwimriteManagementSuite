package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;

/**
 * The body panel that holds all components that will dynamically swap in and out of view.
 */
public class smsBodyPanel extends JPanel {
    
    /**
     * The welcome panel displayed on initial load of the application.
     */
    smsWelcomePanel smsWelcomePanel; 
    
    /**
     * Holds the currently displayed panel.
     */
    JScrollPane currentlyDisplayedPanel;
    
    /**
     * panel to hold dynamically swapping panels.
     */
    public smsBodyPanel() {
        // sets the smsBodyPanel JPanel attributes.
        this.setPreferredSize(new Dimension(1400, 595));
        this.setVisible(true);
        this.setBackground(Color.white);
        
        // Adds the welcome panel to this body panel.
        addWelcomePanel();
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
        smsWelcomePanel = new smsWelcomePanel();
        
        // Adds the welcome panel to a scroll pane and sets the size of it.
        currentlyDisplayedPanel = new JScrollPane(smsWelcomePanel);
        currentlyDisplayedPanel.setPreferredSize(new Dimension(1380, 575));
        
        // Adds the scroll pane to the body panel and updates the ui.
        this.add(currentlyDisplayedPanel);
        this.updateUI(); 
    }
    
    /**
     * Adds the swimming class schedule panel for a specified day to the body panel.
     * @param day The specified day to load the schedule for.
     */
    public void addSchedulePanel(Day day) {
        // Removes the currently displayed panel.
        removeCurrentlyDisplayedPanel();
        
        // sets the correct toggle button to the selected on the toolbar (when selected from the menu bar)
        if (day == Day.MONDAY) {
            smsToolbarPanel.monButton.setSelected(true);
        } else if (day == Day.TUESDAY) {
            smsToolbarPanel.tueButton.setSelected(true);
        } else if (day == Day.WEDNESDAY) {
            smsToolbarPanel.wedButton.setSelected(true);
        } else if (day == Day.THURSDAY) {
            smsToolbarPanel.thuButton.setSelected(true);
        } else if (day == Day.FRIDAY) {
            smsToolbarPanel.friButton.setSelected(true);
        } else if (day == Day.SATURDAY) {
            smsToolbarPanel.satButton.setSelected(true);
        } else if (day == Day.SUNDAY) {
            smsToolbarPanel.sunButton.setSelected(true);
        }
        
        // creates the schedule panel for the specified day
        DaySchedulePanel daySchedulePanel = new DaySchedulePanel(day);
        
        // Adds the schedule panel to the scroll pane and sets the size of the scroll pane
        currentlyDisplayedPanel = new JScrollPane(daySchedulePanel);
        currentlyDisplayedPanel.setPreferredSize(new Dimension(1380, 575));
        
        // Adds the scroll pane to this body panel.
        this.add(currentlyDisplayedPanel);
    }
    
    
    
}
