package uk.ac.tees.m2081433.swimritemanagementsuite.view;
import java.awt.*;
import javax.swing.*;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.HeaderActionEventController;

/**
 * This is the Header panel at the top (North/Page Start) of the Main Panel that contains 3 major components
 * The SMS Logo, The SMS Menu Bar and the SMS Toolbar.
 */
public class SMSHeaderPanel extends JPanel {
    
    /**
     * The Extended Action Event Controller to handle all action Events for components contained within the header panel.
     */
    HeaderActionEventController headerActionEventController;
    
    /**
     * Declares the name of the smsLogoLabel (to be used as static references in the MouseListener class).
     */
    public static final String SMSLOGOLABEL_NAME = "smsLogoLabel";
    
    
    
    /**
     * The header panel adds all the SMS logo, menubar and toolbar to its layout.
     * @param smsBodyPanel The reference to the body panel for the header action event controller to swap panels at runtime.
     */
    public SMSHeaderPanel(SMSBodyPanel smsBodyPanel) {
        
        // Sets the header panel JPanel attributes
        this.setPreferredSize(new Dimension(1400, 180));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        // Adds a seperator to the bottom of the header panel.
        this.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.PAGE_END);
        
        // Initialises the class to handle all of the Action Events for this class, smsMenuBar & smsToolbar.
        headerActionEventController = new HeaderActionEventController(smsBodyPanel);
        
        // Creates and adds the menu bar to the header panel.
        final SMSMenuBar smsMenuBar = new SMSMenuBar(headerActionEventController);
        this.add(smsMenuBar, BorderLayout.PAGE_START);
        
        // Adds the Swimrite Management Suites Logo to this header panels layout
        addSMSLogoLabel();
        
        // Creates and adds the toolbar to the header panel.
        final SMSToolbarPanel smsToolbar = new SMSToolbarPanel(headerActionEventController);
        this.add(smsToolbar, BorderLayout.LINE_END);
    }
    
    /**
     * Adds the Swimrite Management Suites Logo to the SMSHeaderPanel.
     */
    public void addSMSLogoLabel() {
        
        // Declares the label and label attributes to hold the logo and adds it the the header panel.
        final JLabel smsLogoLabel = new JLabel();
        smsLogoLabel.setName(SMSLOGOLABEL_NAME);
        smsLogoLabel.setPreferredSize(new Dimension(405, 150));
        smsLogoLabel.setOpaque(true);
        smsLogoLabel.setVisible(true);
        smsLogoLabel.addMouseListener(headerActionEventController);
        
        // Sets the image icon of the JLabel to the Swimrite Management Suite Logo.
        smsLogoLabel.setIcon(new ImageIcon("images/SMS_Logo.jpg"));
        
        // Adds the logo to the header panel
        this.add(smsLogoLabel, BorderLayout.LINE_START);
    }
}
