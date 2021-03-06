/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;
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
    private SMSWelcomePanel smsWelcomePanel; 
    
    /**
     * Holds the currently displayed panel.
     */
    private JScrollPane currentlyDisplayedPanel;
    
    /**
     * The reference to the panel this body panel is on (for when logging out).
     */
    private final SMSMainPanel smsMainPanelRef;
    
    /**
     * panel to hold dynamically swapping panels.
     * @param smsMainPanel The reference to the panel component this panel is on
     */
    public SMSBodyPanel(SMSMainPanel smsMainPanel) {
        // sets the smsBodyPanel JPanel attributes.
        this.setPreferredSize(new Dimension(1400, 625));
        this.setVisible(true);
        this.setBackground(Color.white);
        
        // Initializes the main body panel reference for when displaying the login screen.
        smsMainPanelRef = smsMainPanel;
        
        // Adds the welcome panel to this body panel.
        addWelcomePanel();
    }
    
    /**
     * Adds a panel to this body panel and sets it as the currently displayed panel.
     * @param panelToDisplay The JPanel to display on the body panel.
     */
    private void addCurrentlyDisplayedPanel(JPanel panelToDisplay) {
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
    public final void addWelcomePanel() {
        // initialises the welcome panel.
        smsWelcomePanel = new SMSWelcomePanel();
        
        // Sets the invisible button to be selected as no other button is appropriate to set as selected.
        SMSToolbarPanel.invisButton.setSelected(true);
        
        // Adds the welcome panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(smsWelcomePanel);
    }
    
    /**
     * Adds the swimming class schedule panel for a specified day to the body panel.
     * @param day The specified day to load the schedule for.
     * @param editMode the boolean as to whether the day schedule panel should be loaded in editing mode.
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
        final DaySchedulePanel daySchedulePanel = new DaySchedulePanel(day, this, editMode);
        
        // Adds the day schedule panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(daySchedulePanel);
    }
    
    /**
     * Removes the currently displayed panel on the body panel and displays the add student record panel whilst
     * also setting it as the new currently displayed panel.
     */
    public void addAddSRPanel() {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Sets the appropriate button to be selected on the toolbar.
        SMSToolbarPanel.addSRButton.setSelected(true);
        
        // Creates the add student record panel
        final AddSRPanel addSRPanel = new AddSRPanel();
        
        // Adds the add student record panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(addSRPanel);
    }
    
    /**
     * Removes the currently displayed panel on the body panel and displays the view all student record panel whilst
     * also setting it as the new currently displayed panel.
     */
    public void addViewAllSRPanel() {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Sets the appropriate button to be selected on the toolbar.
        SMSToolbarPanel.viewAllSRButton.setSelected(true);
        
        // Creates the view all student records panel
        final ViewAllSRPanel viewAllSRPanel = new ViewAllSRPanel(this);
        
        // Adds the view all student records panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(viewAllSRPanel);
    }
    
    /**
     * Removes the currently displayed panel on the body panel and displays the view individual student record
     * panel whilst also setting it as the new currently displayed panel.
     * @param studentRecord the student record to display on the view individual student record panel
     */
    public void addViewIndividualSRPanel(StudentRecord studentRecord) {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Creates the view individual student record panel sending the student record to be displayed as a param.
        final ViewIndividualSRPanel viewIndividualSRPanel = new ViewIndividualSRPanel(studentRecord, this);
        
        // Adds the view individual student record panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(viewIndividualSRPanel);
    }
    
    /**
     * Removes the currently displayed panel on the body panel and displays the view individual swimming
     * class panel whilst also setting it as the new currently displayed panel.
     * @param swimmingClass the swimming class to display on the view individual swimming class panel.
     */
    public void addViewIndividualSCPanel(SwimmingClasses swimmingClass) {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Creates the view individual swimming class panel sending the swimming class to be displayed as a param.
        final ViewIndividualSCPanel viewIndividualSCPanel = new ViewIndividualSCPanel(swimmingClass, this);
        
        // Adds the view individual swimming class panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(viewIndividualSCPanel);
    }
    
    /**
     * Removes the currently displayed panel on the body panel and displays the create, edit and delete
     * teachers panel whilst also setting it as the new currently displayed panel.
     */
    public void addViewCEDTeachersPanel() {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Creates the Create, Edit and Delete Teachers Panel to be displayed on the body panel
        final CEDTeachersPanel cedTeachersPanel = new CEDTeachersPanel(this);
        
        // Sets the invisible button to be selected as no other button is appropriate to set as selected.
        SMSToolbarPanel.invisButton.setSelected(true);
        
        // Adds the view create, edit and delete teachers panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(cedTeachersPanel);
    }
    
    /**
     * Removes the currently displayed panel on the body panel and displays the create, edit and delete
     * login accounts panel whilst also setting it as the new currently displayed panel.
     */
    public void addViewCEDLoginAccountsPanel() {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Creates the Create, Edit and Delete Login Accounts Panel to be displayed on the body panel
        final CEDLoginAccountsPanel cedLoginAccountsPanel = new CEDLoginAccountsPanel(this);
        
        // Sets the invisible button to be selected as no other button is appropriate to set as selected.
        SMSToolbarPanel.invisButton.setSelected(true);
        
        // Adds the view create, edit and delete login accounts panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(cedLoginAccountsPanel);
    }
    
    /**
     * Removes all panels from the main panel (both body and header panels etc.)and displays the log in panel.
     */
    public void logOut() {
        // Removes all panels on the main panel
        smsMainPanelRef.removeAll();
        
        // Creates the login screen for the SMS and adds it to the main panel.
        final SMSLoginPanel smsLoginPanel = new SMSLoginPanel(smsMainPanelRef, true);
        smsMainPanelRef.add(smsLoginPanel, BorderLayout.CENTER);
        
        // Updates the UI of the main panel with the login screen on it.
        smsMainPanelRef.updateUI();
    }
    
    /**
     * Removes the currently displayed panel on the body panel and displays the register panel (to register
     * students for the current days swimming lessons), whilst also setting it as the new currently displayed panel.
     */
    public void addRegisterPanel() {
        // Removes the currently displayed panel from this body panel.
        removeCurrentlyDisplayedPanel();
        
        // Creates the register todays classes panel to be displayed on the body panel
        final RegisterPanel registerPanel = new RegisterPanel(this);
        
        // Sets the register button to be selected
        SMSToolbarPanel.regButton.setSelected(true);
        
        // Adds the register panel as the curretly displayed panel on this body panel.
        addCurrentlyDisplayedPanel(registerPanel);
    }
    
    /**
     * Shows a dialog with all licensing information required from use of libraries and icons within the application.
     */
    public void displayAbout() {
        JOptionPane.showMessageDialog(null,
            "<HTML> <H2> Swimrite Management Suite Liscencing </H2> </HTML>\n"
                    + "<HTML> <H3> ORMLite </H3> </HTML>\n"
                    + "Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted, provided that this permission notice appear in all copies.\n"
                    + "\n"
                    + "THE SOFTWARE IS PROVIDED \"AS IS\" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY \n"
                    + "AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM \n"
                    + "LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR \n"
                    + "PERFORMANCE OF THIS SOFTWARE.\n"
                    + "\n" 
                    + "The author may be contacted via http://ormlite.com/"
                    + "\n"
                    + "\n"
                    + "<HTML> <H3> FatCow Icons </H3> </HTML>\n"
                    + "These icon sets are licensed under a Creative Commons Attribution 3.0 License. This means you can freely use these icons for any purpose, private and commercial,\n"
                    + "including online services, templates, themes and software. However, you should include a link to this page: \n"
                    + "http://www.fatcow.com/free-icons \n"
                    + "The icons may not be resold, sub-licensed, rented, transferred or otherwise made available for use. ", "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
