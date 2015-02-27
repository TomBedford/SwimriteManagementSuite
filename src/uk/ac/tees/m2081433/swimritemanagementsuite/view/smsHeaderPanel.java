package uk.ac.tees.m2081433.swimritemanagementsuite.view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.MainActionEventController;

/**
 *
 * @author Bedford
 */
public class smsHeaderPanel extends JPanel{
    
    MainActionEventController mainActionEventController;
    
    // Declares the name of the smsLogoLabel (to be used as static references in the MouseListener class).
    public static final String SMSLOGOLABEL_NAME = "smsLogoLabel";
    
    public smsHeaderPanel(smsBodyPanel smsBodyPanel) {
        
        // Sets the HomeController JPanel attributes
        this.setPreferredSize(new Dimension(1400, 180));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        // Adds a seperator to the bottom of the header panel.
        this.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.PAGE_END);
        
        // Initialises the class to handle all of the Action Events for smsMenuBar & smsToolbar.
        mainActionEventController = new MainActionEventController(smsBodyPanel);
        
        // Creates and adds the menu bar to the header panel.
        smsMenuBar smsMenuBar = new smsMenuBar(mainActionEventController);
        this.add(smsMenuBar, BorderLayout.PAGE_START);
        
        addSMSLogoLabel();
        
        smsToolbarPanel smsToolbar = new smsToolbarPanel(mainActionEventController);
        this.add(smsToolbar, BorderLayout.LINE_END);
    }
    
    public void addSMSLogoLabel() {
        
        // Declares the label and label attributes to hold the logo and adds it the the toobar panel.
        JLabel smsLogoLabel = new JLabel();
        smsLogoLabel.setName(SMSLOGOLABEL_NAME);
        smsLogoLabel.setPreferredSize(new Dimension(405, 150));
        smsLogoLabel.setOpaque(true);
        smsLogoLabel.setVisible(true);
        smsLogoLabel.addMouseListener(mainActionEventController);
        
        // Sets the image icon of the JLabel to the Swimrite Management Suite Logo.
        smsLogoLabel.setIcon(new ImageIcon("images/SMS_Logo.jpg"));
        
        this.add(smsLogoLabel, BorderLayout.LINE_START);
    }
}
