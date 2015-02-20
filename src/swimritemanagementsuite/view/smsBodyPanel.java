/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Bedford
 */
public class smsBodyPanel extends JPanel{
    
    smsWelcomePanel smsWelcomePanel; 
    
    public smsBodyPanel() throws SQLException {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1400, 595));
        this.setVisible(true);
        this.setBackground(Color.white);
        
        
        DaySchedulePanel daySchedulePanel = new DaySchedulePanel();
        
        JScrollPane dayScheduleScrollPane = new JScrollPane(daySchedulePanel);
        dayScheduleScrollPane.setPreferredSize(new Dimension(1380, 575));
        
        this.add(dayScheduleScrollPane);
      
        
//        smsWelcomePanel = new smsWelcomePanel();
//        this.add(smsWelcomePanel);
    }
    
    public void removeWelcomePanel() {
        if(smsWelcomePanel.isDisplayable()) { 
            this.remove(smsWelcomePanel);
            this.updateUI();   
        }
    }
    
}
