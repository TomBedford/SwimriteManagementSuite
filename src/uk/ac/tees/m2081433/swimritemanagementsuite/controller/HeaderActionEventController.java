/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.SMSBodyPanel;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.SMSHeaderPanel;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.SMSMenuBar;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.SMSToolbarPanel;

/**
 * This is the action event controller for all action events from the SMSHeader panel components (SMSToolBar and
 * SMSMenuBar.
 */
public class HeaderActionEventController implements ActionListener, MouseListener {

    /**
     * Reference to the body panel to swap out components when different buttons/menu items are pressed.
     */
    private final SMSBodyPanel smsBodyPanelRef;
    
    /**
     * Constructor that sets the reference to the body panel.
     * @param smsBodyPanel reference to the body panel so panels can be swapped in and out of view on the body panel.
     */
    public HeaderActionEventController(SMSBodyPanel smsBodyPanel) {
        // The body panel to be referenced when swapping panels on the body panel.
        smsBodyPanelRef = smsBodyPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Checks what the source of the action event is
        if (e.getSource() instanceof JToggleButton) {
            // If it's a toggle button cast the source to the toggle button.
            final JToggleButton toggleButtonPressed = (JToggleButton) e.getSource();
            
            /**
             * Checks the text of the button pressed against all the button names in the toolbar and calls the
             * appropriate method to display the appropriate new panel on the body panel.
             */
            switch (toggleButtonPressed.getText()) {
                case SMSToolbarPanel.MONBUTTON_NAME:
                    displayMondaySchedule();
                    break;

                case SMSToolbarPanel.TUEBUTTON_NAME:
                    displayTuesdaySchedule();
                    break;

                case SMSToolbarPanel.WEDBUTTON_NAME:
                    displayWednesdaySchedule();
                    break;

                case SMSToolbarPanel.THUBUTTON_NAME:
                    displayThursdaySchedule();
                    break;

                case SMSToolbarPanel.FRIBUTTON_NAME:
                    displayFridaySchedule();
                    break;

                case SMSToolbarPanel.SATBUTTON_NAME:
                    displaySaturdaySchedule();
                    break;

                case SMSToolbarPanel.SUNBUTTON_NAME:
                    displaySundaySchedule();
                    break;

                case SMSToolbarPanel.REGBUTTON_NAME:
                    displayRegisterClasses();
                    break;

                case SMSToolbarPanel.VIEWALLSRBUTTON_NAME:
                    displayViewAllSR();
                    break;

                case SMSToolbarPanel.ADDSRBUTTON_NAME:
                    displayAddSR();
                    break;

                case SMSToolbarPanel.VIEWALLSTBUTTON_NAME:
                    displayViewAllST();
                    break;

                case SMSToolbarPanel.ADDSTBUTTON_NAME:
                    displayAddST();
                    break;

                default:
                    System.out.println("ERROR: default switch case for JToggleButton reached!");
                    break;
            }
        // Otherwise the source of the action event is a JMenuItem (only other possibility)
        } else {
            // casts the source to a JMenuItem.
            final JMenuItem menuItemPressed = (JMenuItem) e.getSource();
            
            /**
             * Checks the text of the menu item pressed against all the menu items names in the toolbar and calls the
             * appropriate method to display the appropriate new panel on the body panel.
             */
            switch (menuItemPressed.getText()) {
                case SMSMenuBar.MONMENUITEM_NAME:
                    displayMondaySchedule();
                    break;

                case SMSMenuBar.TUEMENUITEM_NAME:
                    displayTuesdaySchedule();
                    break;

                case SMSMenuBar.WEDMENUITEM_NAME:
                    displayWednesdaySchedule();
                    break;

                case SMSMenuBar.THUMENUITEM_NAME:
                    displayThursdaySchedule();
                    break;

                case SMSMenuBar.FRIMENUITEM_NAME:
                    displayFridaySchedule();
                    break;

                case SMSMenuBar.SATMENUITEM_NAME:
                    displaySaturdaySchedule();
                    break;

                case SMSMenuBar.SUNMENUITEM_NAME:
                    displaySundaySchedule();
                    break;

                case SMSMenuBar.REGMENUITEM_NAME:
                    displayRegisterClasses();
                    break;

                case SMSMenuBar.VIEWALLSRMENUITEM_NAME:
                    displayViewAllSR();
                    break;

                case SMSMenuBar.ADDSRMENUITEM_NAME:
                    displayAddSR();
                    break;

                case SMSMenuBar.VIEWALLSTMENUITEM_NAME:
                    displayViewAllST();
                    break;

                case SMSMenuBar.ADDSTMENUITEM_NAME:
                    displayAddST();
                    break;

                case SMSMenuBar.ABOUTMENUITEM_NAME:
                    displayAbout();
                    break;

                case SMSMenuBar.CEDTEACHERSMENUITEM_NAME:
                    displayCEDTeachers();
                    break;

                case SMSMenuBar.CEDLOGINACCOUNTSMENUITEM_NAME:
                    displayCEDLoginAccounts();
                    break;

                case SMSMenuBar.INITIALISEDBMENUITEM_NAME:
                    displayInitialiseDB();
                    break;
                 
                case SMSMenuBar.LOGOUTMENUITEM_NAME:
                    displayLogOut();
                    break;

                default:
                    System.out.println("ERROR: default switch case for JMenuItem reached!");
                    break;
            }
        }
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // Checks what the source of the mouse event is
        if (e.getSource() instanceof JLabel) {
            // If it's a toggle button cast the source to the toggle button b.
            final JLabel labelPressed = (JLabel) e.getSource();
            // Checks the text of the button pressed against all the button names in the toolbar.
            switch (labelPressed.getName()) {
                case SMSHeaderPanel.SMSLOGOLABEL_NAME:
                    displayWelcomeScreen();
                    break;
                default:
                    System.out.println("ERROR: default switch case for mouse clicked reached!");
                    break;
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
    
    
    /**
     * Calls the method to display the class schedule panel for Monday on the body panel not in edit mode.
     */
    private void displayMondaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.MONDAY, false);
    }
    
    /**
     * Calls the method to display the class schedule panel for Tuesday on the body panel not in edit mode.
     */
    private void displayTuesdaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.TUESDAY, false);
    }
    
    /**
     * Calls the method to display the class schedule panel for Wednesday on the body panel not in edit mode.
     */
    private void displayWednesdaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.WEDNESDAY, false);
    }
    
    /**
     * Calls the method to display the class schedule panel for Thursday on the body panel not in edit mode.
     */
    private void displayThursdaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.THURSDAY, false);
    }
    
    /**
     * Calls the method to display the class schedule panel for Friday on the body panel not in edit mode.
     */
    private void displayFridaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.FRIDAY, false);
    }
    
    /**
     * Calls the method to display the class schedule panel for Saturday on the body panel not in edit mode.
     */
    private void displaySaturdaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.SATURDAY, false);
    }
    
    /**
     * Calls the method to display the class schedule panel for Sunday on the body panel not in edit mode.
     */
    private void displaySundaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.SUNDAY, false);
    }
    
    /**
     * Calls the method to display the register todays classes panel on the body panel.
     */
    private void displayRegisterClasses() {
        smsBodyPanelRef.addRegisterPanel();
    }
    
    /**
     * Calls the method to display the view all student records panel on the body panel.
     */
    private void displayViewAllSR() {
        smsBodyPanelRef.addViewAllSRPanel();
    }
    
    /**
     * Calls the method to display the add student record panel on the body panel.
     */
    private void displayAddSR() {
        smsBodyPanelRef.addAddSRPanel();
    }
    
    /**
     * Calls the method to display the view all support tickets panel on the body panel.
     */
    private void displayViewAllST() {
        // TODO: Complete view all support tickets.
    }
    
    /**
     * Calls the method to display the add support ticket panel on the body panel.
     */
    private void displayAddST() {
        // TODO: Complete add a support ticket.
    }
    
    /**
     * Calls the method to display the about option pane.
     */
    private void displayAbout() {
        smsBodyPanelRef.displayAbout();
    }
    
    /**
     * Calls the method to display the create, edit and delete teachers panel on the body panel.
     */
    private void displayCEDTeachers() {
        smsBodyPanelRef.addViewCEDTeachersPanel();
    }
    
    /**
     * Calls the method to display the create, edit and delete login accounts panel on the body panel.
     */
    private void displayCEDLoginAccounts() {
        smsBodyPanelRef.addViewCEDLoginAccountsPanel();
    }
    
    /**
     * Creates and Initializes the class to run through the format/initialize the database to default values dialogs.
     */
    private void displayInitialiseDB() {
        final InitialiseDefaultDatabase initialiseDefaultDatabase = new InitialiseDefaultDatabase(smsBodyPanelRef);
    }
    
    /**
     * Calls the method to display the log out screen on the main panel.
     */
    private void displayLogOut() {
        smsBodyPanelRef.logOut();
    }
    
    /**
     * Calls the method to display the welcome screen panel on the body panel.
     */
    private void displayWelcomeScreen() {
        smsBodyPanelRef.removeCurrentlyDisplayedPanel();
        smsBodyPanelRef.addWelcomePanel();
    }
}
