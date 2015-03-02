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
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.MainActionEventController;

/**
 *
 * @author Bedford
 */
public class smsToolbarPanel extends JPanel {
    
    // Creates a button group to contain all the toggle buttons in the toolbar.
    ButtonGroup toolbarButtonGroup;
    
    // Declares all button names for each button (to be used as static references in the ActionListener class).
    public static final String MONBUTTON_NAME = "Mon";
    public static final String TUEBUTTON_NAME = "Tue";
    public static final String WEDBUTTON_NAME = "Wed";
    public static final String THUBUTTON_NAME = "Thu";
    public static final String FRIBUTTON_NAME = "Fri";
    public static final String SATBUTTON_NAME = "Sat";
    public static final String SUNBUTTON_NAME = "Sun";
    public static final String REGBUTTON_NAME = "Register Todays Classes";
    public static final String VIEWALLSRBUTTON_NAME = "<html> View <b> All </b> Student Records </html>";
    public static final String ADDSRBUTTON_NAME = "<html> Add a <b> New </b> Student Record </html>";
    public static final String VIEWALLSTBUTTON_NAME = "<html> View <b> All </b> Support Tickets <html>";
    public static final String ADDSTBUTTON_NAME = "<html> Add a <b> New </b> Support Tickets <html>";
    
    // Declares all the buttons used (to be used as static references for changing selected toggle button.
    public static JToggleButton monButton;
    public static JToggleButton tueButton;
    public static JToggleButton wedButton;
    public static JToggleButton thuButton;
    public static JToggleButton friButton;
    public static JToggleButton satButton;
    public static JToggleButton sunButton;
    public static JToggleButton regButton;
    public static JToggleButton viewAllSRButton;
    public static JToggleButton addSRButton;
    public static JToggleButton viewAllSTButton;
    public static JToggleButton addSTButton;
    
    MainActionEventController mainActionEventControllerRef;
    
    public smsToolbarPanel(MainActionEventController mainActionEventController) {
        
        toolbarButtonGroup = new ButtonGroup();
        
        mainActionEventControllerRef = mainActionEventController;
        
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
    
    public void addSwimmingClassesTool() {
        
        // Declares the button dimension for the days of the week buttons (Mon-Sun).
        Dimension daysButtonDimension = new Dimension(50,50);
        
        // Creates and Sets the swimming classes JPanel attributes
        JPanel swimmingClassesPanel = new JPanel();
        swimmingClassesPanel.setPreferredSize(new Dimension(400, 140));
        swimmingClassesPanel.setVisible(true);
        swimmingClassesPanel.setLayout(new FlowLayout());
        swimmingClassesPanel.setBackground(Color.white);
        
        // Adds a border to the swimming classes panel.
        TitledBorder swimmingClassesBorder = BorderFactory.createTitledBorder("Swimming Classes");
        swimmingClassesPanel.setBorder(swimmingClassesBorder);
        
        // Creates all the buttons for this panel.
        monButton = new JToggleButton(MONBUTTON_NAME);
        monButton.setPreferredSize(daysButtonDimension);
        monButton.addActionListener(mainActionEventControllerRef);
        monButton.setToolTipText("<html> Click this button to view <b> Mondays </b> swimming class schedule. </html>");
        
        tueButton = new JToggleButton(TUEBUTTON_NAME);
        tueButton.setPreferredSize(daysButtonDimension);
        tueButton.addActionListener(mainActionEventControllerRef);
        tueButton.setToolTipText("<html> Click this button to view <b> Tuesdays </b> swimming class schedule. </html>");
        
        wedButton = new JToggleButton(WEDBUTTON_NAME);
        wedButton.setPreferredSize(daysButtonDimension);
        wedButton.addActionListener(mainActionEventControllerRef);
        wedButton.setToolTipText("<html> Click this button to view <b> Wednesdays </b> swimming class schedule. </html>");
        
        thuButton = new JToggleButton(THUBUTTON_NAME);
        thuButton.setPreferredSize(daysButtonDimension);
        thuButton.addActionListener(mainActionEventControllerRef);
        thuButton.setToolTipText("<html> Click this button to view <b> Thursdays </b> swimming class schedule. </html>");
        
        friButton = new JToggleButton(FRIBUTTON_NAME);
        friButton.setPreferredSize(daysButtonDimension);
        friButton.addActionListener(mainActionEventControllerRef);
        friButton.setToolTipText("<html> Click this button to view <b> Fridays </b> swimming class schedule. </html>");
        
        satButton = new JToggleButton(SATBUTTON_NAME);
        satButton.setPreferredSize(daysButtonDimension);
        satButton.addActionListener(mainActionEventControllerRef);
        satButton.setToolTipText("<html> Click this button to view <b> Saturdays </b> swimming class schedule. </html>");
        
        sunButton = new JToggleButton(SUNBUTTON_NAME);
        sunButton.setPreferredSize(daysButtonDimension);
        sunButton.addActionListener(mainActionEventControllerRef);
        sunButton.setToolTipText("<html> Click this button to view <b> Sundays </b> swimming class schedule. </html>");
        
        regButton = new JToggleButton(REGBUTTON_NAME);
        regButton.setPreferredSize(new Dimension(200, 50));
        regButton.addActionListener(mainActionEventControllerRef);
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

    public void addStudentRecordsTool() {
        // Declares the button dimension for the days of the week buttons (Mon-Sun).
        Dimension studentButtonDimension = new Dimension(200,50);
        
        // Creates and Sets the student records JPanel attributes.
        JPanel studentRecordsPanel = new JPanel();
        studentRecordsPanel.setPreferredSize(new Dimension(250, 140));
        studentRecordsPanel.setVisible(true);
        studentRecordsPanel.setLayout(new FlowLayout());
        studentRecordsPanel.setBackground(Color.white);
        
        // Adds a border to the student records panel.
        TitledBorder studentRecordsBorder = BorderFactory.createTitledBorder("Student Records");
        studentRecordsPanel.setBorder(studentRecordsBorder);
        
        // Creates and Sets attributes for all buttons to go on the student record panel.
        viewAllSRButton = new JToggleButton(VIEWALLSRBUTTON_NAME);
        viewAllSRButton.setPreferredSize(studentButtonDimension);
        viewAllSRButton.addActionListener(mainActionEventControllerRef);
        viewAllSRButton.setToolTipText("<html> Click this button to view the student records of <b> all </b> students registered at Swimrite Leisure. </html>");
        
        // Sets the icon for the view all student records toggle button.
        viewAllSRButton.setIcon(new ImageIcon("images/icons/users.png"));
        
        addSRButton = new JToggleButton(ADDSRBUTTON_NAME);
        addSRButton.setPreferredSize(studentButtonDimension);
        addSRButton.addActionListener(mainActionEventControllerRef);
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
    
    public void addSupportTicketTool() {
        // Creates and Sets the support ticket JPanel attributes.
        JPanel supportTicketPanel = new JPanel();
        supportTicketPanel.setPreferredSize(new Dimension(250, 140));
        supportTicketPanel.setVisible(true);
        supportTicketPanel.setLayout(new FlowLayout());
        supportTicketPanel.setBackground(Color.white);
        
        // Adds a border to the support ticket panel.
        TitledBorder supportTicketBorder = BorderFactory.createTitledBorder("Support Tickets");
        supportTicketPanel.setBorder(supportTicketBorder);
        
        // Creates and Sets attributes for the view all button to go on the support ticket panel.
        viewAllSTButton = new JToggleButton(VIEWALLSTBUTTON_NAME);
        viewAllSTButton.setPreferredSize(new Dimension(200, 50));
        viewAllSTButton.addActionListener(mainActionEventControllerRef);
        viewAllSTButton.setToolTipText("<html> Click this button to view <b> all </b> Swimrite Leisure student support tickets. </html>");
        
        // Sets the icon for the view all support tickets toggle button.
        viewAllSTButton.setIcon(new ImageIcon("images/icons/premium_support.png"));
        
        // Creates and Sets attributes for the add button to go on the support ticket panel.
        addSTButton = new JToggleButton(ADDSTBUTTON_NAME);
        addSTButton.setPreferredSize(new Dimension(200, 50));
        addSTButton.addActionListener(mainActionEventControllerRef);
        addSTButton.setToolTipText("<html> Click this button to add a <b> new </b> Swimrite Leisure student support ticket. </html>");
        
        // Sets the icon for the add a support ticket toggle button.
        addSTButton.setIcon(new ImageIcon("images/icons/pencil_add.png"));
        
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
