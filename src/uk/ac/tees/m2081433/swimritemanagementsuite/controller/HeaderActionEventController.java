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
 *
 */
public class HeaderActionEventController implements ActionListener, MouseListener {

    SMSBodyPanel smsBodyPanelRef;
    
    public HeaderActionEventController(SMSBodyPanel smsBodyPanel) {
        // The body panel to be referenced when swapping panels on the body panel.
        smsBodyPanelRef = smsBodyPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Checks what the source of the action event is
        if (e.getSource() instanceof JToggleButton) {
            // If it's a toggle button cast the source to the toggle button.
            JToggleButton toggleButtonPressed = (JToggleButton) e.getSource();
            
            // Checks the text of the button pressed against all the button names in the toolbar.
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
            JMenuItem menuItemPressed = (JMenuItem) e.getSource();
            
            // Checks the text of the button pressed against all the button names in the toolbar.
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

                case SMSMenuBar.CEDTEACHERSMENUITEM_NAME:
                    displayCEDTeachers();
                    break;

                case SMSMenuBar.CEDLOGINACCOUNTSMENUITEM_NAME:
                    displayCEDLoginAccounts();
                    break;

                case SMSMenuBar.INITIALISEDBMENUITEM_NAME:
                    displayInitialiseDB();
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
            JLabel labelPressed = (JLabel) e.getSource();
            // Checks the text of the button pressed against all the button names in the toolbar.
            switch (labelPressed.getName()) {
                case SMSHeaderPanel.SMSLOGOLABEL_NAME:
                    displayWelcomeScreen();
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
    
    
    
    public void displayMondaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.MONDAY, false);
    }
    
    public void displayTuesdaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.TUESDAY, false);
    }
    
    public void displayWednesdaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.WEDNESDAY, false);
    }
    
    public void displayThursdaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.THURSDAY, false);
    }
    
    public void displayFridaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.FRIDAY, false);
    }
    
    public void displaySaturdaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.SATURDAY, false);
    }
    
    public void displaySundaySchedule() {
        smsBodyPanelRef.addSchedulePanel(Day.SUNDAY, false);
    }
    
    public void displayRegisterClasses() {
        
    }
    
    public void displayViewAllSR() {
        smsBodyPanelRef.addViewAllSRPanel();
    }
    
    public void displayAddSR() {
        smsBodyPanelRef.addAddSRPanel();
    }
    
    public void displayViewAllST() {
        
    }
    
    public void displayAddST() {
        
    }
    
    public void displayCEDTeachers() {
        
    }
    
    public void displayCEDLoginAccounts() {
        
    }
    
    public void displayInitialiseDB() {
        
        InitialiseDefaultDatabase initialiseDefaultDatabase = new InitialiseDefaultDatabase();
        
    }
    
    public void displayWelcomeScreen() {
        smsBodyPanelRef.removeCurrentlyDisplayedPanel();
        smsBodyPanelRef.addWelcomePanel();
    }
}
