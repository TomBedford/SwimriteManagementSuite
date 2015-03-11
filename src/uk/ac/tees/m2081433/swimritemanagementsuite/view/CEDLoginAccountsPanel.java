package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 */
public class CEDLoginAccountsPanel extends JPanel {
    
    /**
     * The SMS Body Panel reference for when swapping panels on the body panel.
     */
    SMSBodyPanel smsBodyPanel;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    public CEDLoginAccountsPanel(SMSBodyPanel smsBP) {
        // Initializes this panels body panel reference
        smsBodyPanel = smsBP;
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // sets the create, edit and delete teachers JPanels default attributes
        this.setPreferredSize(new Dimension(1375, 605));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
    }
}
