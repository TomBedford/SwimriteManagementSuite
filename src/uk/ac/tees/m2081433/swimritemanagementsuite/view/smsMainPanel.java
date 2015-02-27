package uk.ac.tees.m2081433.swimritemanagementsuite.view;
import java.awt.*;
import javax.swing.*;

/**
 * The main panel that holds the header and body panels.
 */
public class smsMainPanel extends JPanel  {
    
    /**
     * Defines default attributes of the panel and adds the header and body panels to itself.
     */
    public smsMainPanel() {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1400, 800));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        // Creates the Body Panel
        final smsBodyPanel smsBodyPanel = new smsBodyPanel();
        
        // Adds the Body Panel to this main panel.
        this.add(smsBodyPanel, BorderLayout.PAGE_END);
        
        // Creates the header panel 
        final smsHeaderPanel smsHeaderPanel = new smsHeaderPanel(smsBodyPanel);
        
        // Adds the header panel to this main panel.
        this.add(smsHeaderPanel, BorderLayout.PAGE_START);
    }
}
