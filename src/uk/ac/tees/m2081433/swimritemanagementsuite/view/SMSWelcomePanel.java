package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The default welcome panel / Homepage when the application loads up.
 */
public class SMSWelcomePanel extends JPanel {
    
    /**
     * The default welcome panel of the application, defines this default welcome panels attributes.
     */
    public SMSWelcomePanel() {
        // sets the smsWelcomePanel JPanels default attributes
        this.setPreferredSize(new Dimension(1375, 605));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout());
        
        // Declares the label and label attributes to hold the welcome logo
        final JLabel smsWelcomeLogoLabel = new JLabel();
        smsWelcomeLogoLabel.setPreferredSize(new Dimension(1375, 605));
        smsWelcomeLogoLabel.setOpaque(true);
        smsWelcomeLogoLabel.setVisible(true);
        
        // Sets the image icon of the JLabel to the Swimrite Management Suite Welcome Logo.
        smsWelcomeLogoLabel.setIcon(new ImageIcon("images/SMS_Welcome_Logo.jpg"));
        
        // Adds the welcome logo label with the image icon to the welcome JPanel.
        this.add(smsWelcomeLogoLabel, BorderLayout.CENTER);
    }
}
