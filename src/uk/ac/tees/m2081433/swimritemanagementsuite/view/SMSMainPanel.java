/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.view;
import java.awt.*;
import javax.swing.*;

/**
 * The main panel on the frame that holds the header and body panels.
 */
public class SMSMainPanel extends JPanel  {
    
    /**
     * Defines default attributes of the panel and adds the header and body panels to itself.
     * @param dbSetupSuccessful boolean as to whether the database setup was successful.
     */
    public SMSMainPanel(boolean dbSetupSuccessful) {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1400, 800));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        // Creates the login screen for the SMS.
        final SMSLoginPanel smsLoginPanel = new SMSLoginPanel(this, dbSetupSuccessful);
        this.add(smsLoginPanel, BorderLayout.CENTER);
    }
}
