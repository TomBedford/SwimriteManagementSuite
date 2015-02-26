/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;

/**
 *
 * @author Bedford
 */
public class smsBodyPanel extends JPanel{
    
    smsWelcomePanel smsWelcomePanel; 
    
    JScrollPane currentlyDisplayedPanel;
    
    public smsBodyPanel() throws SQLException {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1400, 595));
        this.setVisible(true);
        this.setBackground(Color.white);
        
        addWelcomePanel();
    }
    
    public void removeCurrentlyDisplayedPanel() {
        this.remove(currentlyDisplayedPanel);
        this.updateUI();   
    }
    
    public void addWelcomePanel() {
        smsWelcomePanel smsWelcomePanel = new smsWelcomePanel();
        
        currentlyDisplayedPanel = new JScrollPane(smsWelcomePanel);
        currentlyDisplayedPanel.setPreferredSize(new Dimension(1380, 575));
        
        this.add(currentlyDisplayedPanel);
        this.updateUI(); 
    }
    
    public void addSchedulePanel(Day day) throws SQLException {
        removeCurrentlyDisplayedPanel();
        
        if(day == Day.MONDAY) {
            smsToolbarPanel.monButton.setSelected(true);
        } else if(day == Day.TUESDAY) {
            smsToolbarPanel.tueButton.setSelected(true);
        } else if(day == Day.WEDNESDAY) {
            smsToolbarPanel.wedButton.setSelected(true);
        } else if(day == Day.THURSDAY) {
            smsToolbarPanel.thuButton.setSelected(true);
        } else if(day == Day.FRIDAY) {
            smsToolbarPanel.friButton.setSelected(true);
        } else if(day == Day.SATURDAY) {
            smsToolbarPanel.satButton.setSelected(true);
        } else if(day == Day.SUNDAY) {
            smsToolbarPanel.sunButton.setSelected(true);
        }
        
        DaySchedulePanel daySchedulePanel = new DaySchedulePanel(day);
        
        
        currentlyDisplayedPanel = new JScrollPane(daySchedulePanel);
        currentlyDisplayedPanel.setPreferredSize(new Dimension(1380, 575));
        
        this.add(currentlyDisplayedPanel);
    }
    
    
    
}
