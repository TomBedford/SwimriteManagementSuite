/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.HeaderActionEventController;

/**
 * The Toolbar for the Swimrite Management Suite application to navigate throughout the applications functionality.
 */
public class SMSToolbarPanel extends JPanel {
    
    /**
     * Creates a button group to contain all the toggle buttons in the toolbar.
     */
    private final ButtonGroup toolbarButtonGroup;
    
    // Declares all button names for each button (to be used as static references in the ActionListener class)
    /**
     * The name for the Monday toggle button.
     */
    public static final String MONBUTTON_NAME = "Mon";
    
    /**
     * The name for the Tuesday toggle button.
     */
    public static final String TUEBUTTON_NAME = "Tue";
    
    /**
     * The name for the Wednesday toggle button.
     */
    public static final String WEDBUTTON_NAME = "Wed";
    
    /**
     * The name for the Thursday toggle button.
     */
    public static final String THUBUTTON_NAME = "Thu";
    
    /**
     * The name for the Friday toggle button.
     */
    public static final String FRIBUTTON_NAME = "Fri";
    
    /**
     * The name for the Saturday toggle button.
     */
    public static final String SATBUTTON_NAME = "Sat";
    
    /**
     * The name for the Sunday toggle button.
     */
    public static final String SUNBUTTON_NAME = "Sun";
    
    /**
     * The name for the register todays classes toggle button.
     */
    public static final String REGBUTTON_NAME = "Register Todays Classes";
    
    /**
     * The name for the view all student records toggle button.
     */
    public static final String VIEWALLSRBUTTON_NAME = "<html> View <b> All </b> Student Records </html>";
    
    /**
     * The name for the add a student record toggle button.
     */
    public static final String ADDSRBUTTON_NAME = "<html> Add a <b> New </b> Student Record </html>";
    
    /**
     * The name for the view all support tickets toggle button.
     */
    public static final String VIEWALLSTBUTTON_NAME = "<html> View <b> All </b> Support Tickets <html>";
    
    /**
     * The name for the add a new support ticket toggle button.
     */
    public static final String ADDSTBUTTON_NAME = "<html> Add a <b> New </b> Support Tickets <html>";
    
    
    
    // Declares all the buttons used (to be used as static references for changing selected toggle button.
    /**
     * The toggle button to view Mondays class schedule.
     */
    public static JToggleButton monButton;
    
    /**
     * The toggle button to view Tuesdays class schedule.
     */
    public static JToggleButton tueButton;
    
    /**
     * The toggle button to view Wednesdays class schedule.
     */
    public static JToggleButton wedButton;
    
    /**
     * The toggle button to view Thursdays class schedule.
     */
    public static JToggleButton thuButton;
    
    /**
     * The toggle button to view Fridays class schedule.
     */
    public static JToggleButton friButton;
    
    /**
     * The toggle button to view Saturdays class schedule.
     */
    public static JToggleButton satButton;
    
    /**
     * The toggle button to view Sundays class schedule.
     */
    public static JToggleButton sunButton;
    
    /**
     * The toggle button to register todays classes.
     */
    public static JToggleButton regButton;
    
    /**
     * The toggle button to view all student records.
     */
    public static JToggleButton viewAllSRButton;
    
    /**
     * The toggle button to add a new student records.
     */
    public static JToggleButton addSRButton;
    
    /**
     * The toggle button to view all support tickets.
     */
    public static JToggleButton viewAllSTButton;
    
    /**
     * The toggle button to add a new support ticket.
     */
    public static JToggleButton addSTButton;
    
    /**
     * The toggle button that will be invisible so that it can be selected when menu bar options are chosen
     * that do not require any of the other toggle buttons to be selected.
     */
    public static JToggleButton invisButton = new JToggleButton();
    
    /**
     * Declares the button dimension for standard buttons on the jPanel.
     */
    private final Dimension standardButtonDimension = new Dimension(200, 50);
    
    
    
    /**
     * The extended action event controller for all components in the header panel.
     */
    private final HeaderActionEventController headerActionEventController;
    
    
    
    /**
     * The Toolbar for the Swimrite Management Suite application to navigate throughout the applications functionality.
     * @param hAEC The action event controller for this panel.
     */
    public SMSToolbarPanel(HeaderActionEventController hAEC) {
        
        // Initializes the button group for all toggle buttons on this panel
        toolbarButtonGroup = new ButtonGroup();
        toolbarButtonGroup.add(invisButton);
        
        // Initializes the header action event controller
        headerActionEventController = hAEC;
        
        // sets the smsToolbarPanel JPanel attributes
        this.setPreferredSize(new Dimension(995, 150));
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.setBackground(Color.white);
        
        // Adds other toolbar components to the smsToolbadPanel.
        // Adds the Swimming Classes tool to the toolbar.
        addSwimmingClassesTool();
        
        // Adds the Student Records tool to the toolbar.
        addStudentRecordsTool();
        
        // Adds the Support Ticket tool to the toolbar.
        addSupportTicketTool();
    }
    
    /**
     * Adds the buttons to view all class schedule days and to register todays classes.
     */
    private void addSwimmingClassesTool() {
        
        // Declares the button dimension for the days of the week buttons (Mon-Sun).
        final Dimension daysButtonDimension = new Dimension(50, 50);
        
        // Creates and Sets the swimming classes JPanel attributes
        final JPanel swimmingClassesPanel = new JPanel();
        swimmingClassesPanel.setPreferredSize(new Dimension(400, 140));
        swimmingClassesPanel.setVisible(true);
        swimmingClassesPanel.setLayout(new FlowLayout());
        swimmingClassesPanel.setBackground(Color.white);
        
        // Adds a border with a title to the swimming classes panel.
        final TitledBorder swimmingClassesBorder = BorderFactory.createTitledBorder("Swimming Classes");
        swimmingClassesPanel.setBorder(swimmingClassesBorder);
        
        // Creates all the buttons for the swimming classes panel, setting size, action event controller and tool tip.
        // Button to view mondays class schedule
        monButton = new JToggleButton(MONBUTTON_NAME);
        monButton.setPreferredSize(daysButtonDimension);
        monButton.addActionListener(headerActionEventController);
        monButton.setToolTipText("<html> Click this button to view <b> Mondays </b> swimming class schedule. </html>");
        
        // Button to view tuesdays class schedule
        tueButton = new JToggleButton(TUEBUTTON_NAME);
        tueButton.setPreferredSize(daysButtonDimension);
        tueButton.addActionListener(headerActionEventController);
        tueButton.setToolTipText("<html> Click this button to view <b> Tuesdays </b> swimming class schedule. </html>");
        
        // Button to view wednesday class schedule
        wedButton = new JToggleButton(WEDBUTTON_NAME);
        wedButton.setPreferredSize(daysButtonDimension);
        wedButton.addActionListener(headerActionEventController);
        wedButton.setToolTipText("<html> Click this button to view <b> Wednesdays </b> swimming class schedule. </html>");
        
        // Button to view thursday class schedule
        thuButton = new JToggleButton(THUBUTTON_NAME);
        thuButton.setPreferredSize(daysButtonDimension);
        thuButton.addActionListener(headerActionEventController);
        thuButton.setToolTipText("<html> Click this button to view <b> Thursdays </b> swimming class schedule. </html>");
        
        // Button to view friday class schedule
        friButton = new JToggleButton(FRIBUTTON_NAME);
        friButton.setPreferredSize(daysButtonDimension);
        friButton.addActionListener(headerActionEventController);
        friButton.setToolTipText("<html> Click this button to view <b> Fridays </b> swimming class schedule. </html>");
        
        // Button to view saturday class schedule
        satButton = new JToggleButton(SATBUTTON_NAME);
        satButton.setPreferredSize(daysButtonDimension);
        satButton.addActionListener(headerActionEventController);
        satButton.setToolTipText("<html> Click this button to view <b> Saturdays </b> swimming class schedule. </html>");
        
        // Button to view sunday class schedule
        sunButton = new JToggleButton(SUNBUTTON_NAME);
        sunButton.setPreferredSize(daysButtonDimension);
        sunButton.addActionListener(headerActionEventController);
        sunButton.setToolTipText("<html> Click this button to view <b> Sundays </b> swimming class schedule. </html>");
        
        // Button to register todays classes
        regButton = new JToggleButton(REGBUTTON_NAME);
        regButton.setPreferredSize(standardButtonDimension);
        regButton.addActionListener(headerActionEventController);
        regButton.setToolTipText("<html> Click this button to activley <b> register </b> students for <b> todays </b> classes. </html>");
        
        // Creates the image icon that contains the icon for the register student toggle button.
        regButton.setIcon(new ImageIcon("images/icons/page_edit.png"));
        
        // Adds the created buttons to the toolbar button group.
        toolbarButtonGroup.add(monButton);
        toolbarButtonGroup.add(tueButton);
        toolbarButtonGroup.add(wedButton);
        toolbarButtonGroup.add(thuButton);
        toolbarButtonGroup.add(friButton);
        toolbarButtonGroup.add(satButton);
        toolbarButtonGroup.add(sunButton);
        toolbarButtonGroup.add(regButton);
        
        // Adds all the buttons to the swimming classes jpanel.
        swimmingClassesPanel.add(monButton);
        swimmingClassesPanel.add(tueButton);
        swimmingClassesPanel.add(wedButton);
        swimmingClassesPanel.add(thuButton);
        swimmingClassesPanel.add(friButton);
        swimmingClassesPanel.add(satButton);
        swimmingClassesPanel.add(sunButton);
        swimmingClassesPanel.add(regButton);
        
        // Adds the swimming class panel to the sms toolbar panel.
        this.add(swimmingClassesPanel);
    }

    /**
     * Adds the buttons to view all student records and to add a new student record.
     */
    private void addStudentRecordsTool() {
        // Creates and Sets the student records JPanel attributes.
        final JPanel studentRecordsPanel = new JPanel();
        studentRecordsPanel.setPreferredSize(new Dimension(250, 140));
        studentRecordsPanel.setVisible(true);
        studentRecordsPanel.setLayout(new FlowLayout());
        studentRecordsPanel.setBackground(Color.white);
        
        // Adds a border with a title to the student records panel.
        final TitledBorder studentRecordsBorder = BorderFactory.createTitledBorder("Student Records");
        studentRecordsPanel.setBorder(studentRecordsBorder);
        
        // Creates all the buttons for the student records panel, setting size, action event controller and tool tip.
        // The view all student record button
        viewAllSRButton = new JToggleButton(VIEWALLSRBUTTON_NAME);
        viewAllSRButton.setPreferredSize(standardButtonDimension);
        viewAllSRButton.addActionListener(headerActionEventController);
        viewAllSRButton.setToolTipText("<html> Click this button to view the student records of <b> all </b> students registered at Swimrite Leisure. </html>");
        
        // Sets the icon for the view all student records toggle button.
        viewAllSRButton.setIcon(new ImageIcon("images/icons/users.png"));
        
        // The add a student record button
        addSRButton = new JToggleButton(ADDSRBUTTON_NAME);
        addSRButton.setPreferredSize(standardButtonDimension);
        addSRButton.addActionListener(headerActionEventController);
        addSRButton.setToolTipText("<html> Click this button to add a <b> new </b> student record to the Swimrite Leisure database. </html>");
        
        // Sets the icon for the add a new student record toggle button.
        addSRButton.setIcon(new ImageIcon("images/icons/user_add.png"));
        
        // Adds the created buttons to the toolbar button group.
        toolbarButtonGroup.add(viewAllSRButton);
        toolbarButtonGroup.add(addSRButton);
        
        // Adds all buttons to the student record panel.
        studentRecordsPanel.add(viewAllSRButton);
        studentRecordsPanel.add(addSRButton);
        
        // Adds the student records panel to the sms toolbar panel.
        this.add(studentRecordsPanel);
    }
    
    /**
     * Adds the buttons to view all support tickets and to add a new support ticket.
     */
    private void addSupportTicketTool() {
        // Creates and Sets the support ticket JPanel attributes.
        final JPanel supportTicketPanel = new JPanel();
        supportTicketPanel.setPreferredSize(new Dimension(250, 140));
        supportTicketPanel.setVisible(true);
        supportTicketPanel.setLayout(new FlowLayout());
        supportTicketPanel.setBackground(Color.white);
        
        // Adds a border with a title to the support ticket panel.
        final TitledBorder supportTicketBorder = BorderFactory.createTitledBorder("Support Tickets");
        supportTicketPanel.setBorder(supportTicketBorder);
        
        // Creates all the buttons for the support ticket panel, setting size, action event controller and tool tip.
        // The view all support tickets button
        viewAllSTButton = new JToggleButton(VIEWALLSTBUTTON_NAME);
        viewAllSTButton.setPreferredSize(standardButtonDimension);
        viewAllSTButton.addActionListener(headerActionEventController);
        viewAllSTButton.setToolTipText("<html> Click this button to view <b> all </b> Swimrite Leisure student support tickets. </html>");
        
        // Sets the icon for the view all support tickets toggle button.
        viewAllSTButton.setIcon(new ImageIcon("images/icons/premium_support.png"));
        
        // Creates and Sets attributes for the add button to go on the support ticket panel.
        // The add a new support ticket button
        addSTButton = new JToggleButton(ADDSTBUTTON_NAME);
        addSTButton.setPreferredSize(standardButtonDimension);
        addSTButton.addActionListener(headerActionEventController);
        addSTButton.setToolTipText("<html> Click this button to add a <b> new </b> Swimrite Leisure student support ticket. </html>");
        
        // Sets the icon for the add a support ticket toggle button.
        addSTButton.setIcon(new ImageIcon("images/icons/pencil_add.png"));
        
        // DISABLES BOTH BUTTONS TO FALSE UNTIL COMPLETE
        viewAllSTButton.setEnabled(false);
        addSTButton.setEnabled(false);
        
        // Adds the created buttons to the toolbar button group.
        toolbarButtonGroup.add(viewAllSTButton);
        toolbarButtonGroup.add(addSTButton);
        
        // Adds all buttons to the support ticket panel.
        supportTicketPanel.add(viewAllSTButton);
        supportTicketPanel.add(addSTButton);
        
        // Adds the student records panel to the sms toolbar panel.
        this.add(supportTicketPanel);
    }  
}
