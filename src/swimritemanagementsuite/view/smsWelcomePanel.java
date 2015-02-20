/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bedford
 */
public class smsWelcomePanel extends JPanel{
    
    public smsWelcomePanel() {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1380, 575));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("<html><h1>Welcome!</h1></html>");
        
        this.add(welcomeLabel);
        
    }
}
