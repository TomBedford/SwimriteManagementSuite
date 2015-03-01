/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.smsBodyPanel;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.smsHeaderPanel;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.smsMenuBar;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.smsToolbarPanel;

/**
 *
 * @author Bedford
 */
public class MainActionEventController implements ActionListener, MouseListener {

    smsBodyPanel smsBodyPanelRef;
    
    public MainActionEventController(smsBodyPanel smsBodyPanel) {
        // The body panel to be referenced when swapping panels on the body panel.
        smsBodyPanelRef = smsBodyPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Checks what the source of the action event is
        if (e.getSource() instanceof JToggleButton) {
            // If it's a toggle button cast the source to the toggle button b.
            JToggleButton toggleButtonPressed = (JToggleButton) e.getSource();
            
            // Surrounded by try catch for the possible SQL Exceptions
            try {
                // Checks the text of the button pressed against all the button names in the toolbar.
                switch (toggleButtonPressed.getText()) {
                    case smsToolbarPanel.MONBUTTON_NAME:
                        displayMondaySchedule();
                        break;

                    case smsToolbarPanel.TUEBUTTON_NAME:
                        displayTuesdaySchedule();
                        break;

                    case smsToolbarPanel.WEDBUTTON_NAME:
                        displayWednesdaySchedule();
                        break;

                    case smsToolbarPanel.THUBUTTON_NAME:
                        displayThursdaySchedule();
                        break;

                    case smsToolbarPanel.FRIBUTTON_NAME:
                        displayFridaySchedule();
                        break;

                    case smsToolbarPanel.SATBUTTON_NAME:
                        displaySaturdaySchedule();
                        break;

                    case smsToolbarPanel.SUNBUTTON_NAME:
                        displaySundaySchedule();
                        break;

                    case smsToolbarPanel.REGBUTTON_NAME:
                        displayRegisterClasses();
                        break;

                    case smsToolbarPanel.VIEWALLSRBUTTON_NAME:
                        displayViewAllSR();
                        break;

                    case smsToolbarPanel.ADDSRBUTTON_NAME:
                        displayAddSR();
                        break;

                    case smsToolbarPanel.VIEWALLSTBUTTON_NAME:
                        displayViewAllST();
                        break;

                    case smsToolbarPanel.ADDSTBUTTON_NAME:
                        displayAddST();
                        break;

                    default:
                        System.out.println("ERROR: default switch case for JToggleButton reached!");
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainActionEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        // Otherwise the source of the action event is a JMenuItem (only other possibility)
        } else {
            // casts the source to a JMenuItem.
            JMenuItem menuItemPressed = (JMenuItem) e.getSource();
            
            // Surrounded by try catch for the possible SQL Exceptions
            try {
                // Checks the text of the button pressed against all the button names in the toolbar.
                switch (menuItemPressed.getText()) {
                    case smsMenuBar.MONMENUITEM_NAME:
                        displayMondaySchedule();
                        break;

                    case smsMenuBar.TUEMENUITEM_NAME:
                        displayTuesdaySchedule();
                        break;

                    case smsMenuBar.WEDMENUITEM_NAME:
                        displayWednesdaySchedule();
                        break;

                    case smsMenuBar.THUMENUITEM_NAME:
                        displayThursdaySchedule();
                        break;

                    case smsMenuBar.FRIMENUITEM_NAME:
                        displayFridaySchedule();
                        break;

                    case smsMenuBar.SATMENUITEM_NAME:
                        displaySaturdaySchedule();
                        break;

                    case smsMenuBar.SUNMENUITEM_NAME:
                        displaySundaySchedule();
                        break;

                    case smsMenuBar.REGMENUITEM_NAME:
                        displayRegisterClasses();
                        break;

                    case smsMenuBar.VIEWALLSRMENUITEM_NAME:
                        displayViewAllSR();
                        break;

                    case smsMenuBar.ADDSRMENUITEM_NAME:
                        displayAddSR();
                        break;

                    case smsMenuBar.VIEWALLSTMENUITEM_NAME:
                        displayViewAllST();
                        break;

                    case smsMenuBar.ADDSTMENUITEM_NAME:
                        displayAddST();
                        break;

                    case smsMenuBar.CEDTEACHERSMENUITEM_NAME:
                        displayCEDTeachers();
                        break;

                    case smsMenuBar.CEDLOGINACCOUNTSMENUITEM_NAME:
                        displayCEDLoginAccounts();
                        break;

                    case smsMenuBar.INITIALISEDBMENUITEM_NAME:
                        displayInitialiseDB();
                        break;

                    default:
                        System.out.println("ERROR: default switch case for JMenuItem reached!");
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainActionEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    public void mouseClicked(MouseEvent e) {
        // Checks what the source of the mouse event is
        if (e.getSource() instanceof JLabel) {
            // If it's a toggle button cast the source to the toggle button b.
            JLabel labelPressed = (JLabel) e.getSource();
            // Checks the text of the button pressed against all the button names in the toolbar.
            switch (labelPressed.getName()) {
                case smsHeaderPanel.SMSLOGOLABEL_NAME:
                    displayWelcomeScreen();
                    break;
            }
        }
        
    }  

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public void displayMondaySchedule() throws SQLException {
        smsBodyPanelRef.addSchedulePanel(Day.MONDAY);
    }
    
    public void displayTuesdaySchedule() throws SQLException {
        smsBodyPanelRef.addSchedulePanel(Day.TUESDAY);
    }
    
    public void displayWednesdaySchedule() throws SQLException {
        smsBodyPanelRef.addSchedulePanel(Day.WEDNESDAY);
    }
    
    public void displayThursdaySchedule() throws SQLException {
        smsBodyPanelRef.addSchedulePanel(Day.THURSDAY);
    }
    
    public void displayFridaySchedule() throws SQLException {
        smsBodyPanelRef.addSchedulePanel(Day.FRIDAY);
    }
    
    public void displaySaturdaySchedule() throws SQLException {
        smsBodyPanelRef.addSchedulePanel(Day.SATURDAY);
    }
    
    public void displaySundaySchedule() throws SQLException {
        smsBodyPanelRef.addSchedulePanel(Day.SUNDAY);
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
    
    public void displayInitialiseDB() throws SQLException {
        
        InitialiseDefaultDatabase initialiseDefaultDatabase = new InitialiseDefaultDatabase();
        
    }
    
    public void displayWelcomeScreen() {
        smsBodyPanelRef.removeCurrentlyDisplayedPanel();
        smsBodyPanelRef.addWelcomePanel();
    }
}
