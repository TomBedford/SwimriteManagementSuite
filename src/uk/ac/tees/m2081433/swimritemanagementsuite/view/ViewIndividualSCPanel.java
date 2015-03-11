package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;

/**
 *
 */
public class ViewIndividualSCPanel extends JPanel {
    
    
    public ViewIndividualSCPanel(SwimmingClasses swimmingClass, SMSBodyPanel smsBodyPanel) {
        // sets the smsWelcomePanel JPanels default attributes
        this.setPreferredSize(new Dimension(1375, 605));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout());
    }
}
