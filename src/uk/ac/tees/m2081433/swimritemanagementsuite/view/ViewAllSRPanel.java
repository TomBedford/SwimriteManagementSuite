package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * This panel displays all student records within the Swimrite Management Suite database.
 */
public class ViewAllSRPanel extends JPanel {
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    public ViewAllSRPanel() {
        // sets the ViewAllSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(1375, 565));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
    }
}
