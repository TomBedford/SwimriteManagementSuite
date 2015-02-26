/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bedford
 */
public class smsWelcomePanel extends JPanel{
    
    public smsWelcomePanel() {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1375, 565));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout());
        
        // Declares the label and label attributes to hold the welcome logo
        JLabel smsWelcomeLogoLabel = new JLabel();
        smsWelcomeLogoLabel.setPreferredSize(new Dimension(1375, 565));
        smsWelcomeLogoLabel.setOpaque(true);
        smsWelcomeLogoLabel.setVisible(true);
        
        // Sets the image icon of the JLabel to the Swimrite Management Suite Welcome Logo.
        smsWelcomeLogoLabel.setIcon(new ImageIcon("images/SMS_Welcome_Logo.jpg"));
        
        // Adds the welcome logo label with the image icon to the welcome JPanel.
        this.add(smsWelcomeLogoLabel, BorderLayout.CENTER);
    }
}
