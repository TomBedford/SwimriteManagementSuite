package uk.ac.tees.m2081433.swimritemanagementsuite.view;
import java.awt.*;
import javax.swing.*;

/**
 * The main panel on the frame that holds the header and body panels.
 */
public class SMSMainPanel extends JPanel  {
    
    /**
     * Defines default attributes of the panel and adds the header and body panels to itself.
     */
    public SMSMainPanel() {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1400, 800));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        // Creates the login screen for the SMS.
        final SMSLoginPanel smsLoginPanel = new SMSLoginPanel(this);
        this.add(smsLoginPanel, BorderLayout.CENTER);
        
        
//        // Creates the Body Panel
//        final SMSBodyPanel smsBodyPanel = new SMSBodyPanel(this);
//        
//        // Adds the Body Panel to this main panel (South).
//        this.add(smsBodyPanel, BorderLayout.PAGE_END);
//        
//        // Creates the header panel 
//        final SMSHeaderPanel smsHeaderPanel = new SMSHeaderPanel(smsBodyPanel);
//        
//        // Adds the header panel to this main panel (North).
//        this.add(smsHeaderPanel, BorderLayout.PAGE_START);
    }
}
