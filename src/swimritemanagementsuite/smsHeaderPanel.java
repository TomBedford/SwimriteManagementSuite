package swimritemanagementsuite;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Bedford
 */
public class smsHeaderPanel extends JPanel implements ActionListener {
    
    // Declares the smsToolbarPanel to be referenced from other methods.
    JPanel smsToolbarPanel;
    
    // Creates a button group to contain all the toggle buttons in the toolbar.
    ButtonGroup toolbarButtonGroup = new ButtonGroup();
    
    // Declares all buttons to the present on the toolbar.
    JToggleButton monButton;
    JToggleButton tueButton;
    JToggleButton wedButton;
    JToggleButton thuButton;
    JToggleButton friButton;
    JToggleButton satButton;
    JToggleButton sunButton;
    JToggleButton regButton;
    JToggleButton viewAllSRButton;
    JToggleButton addStudentButton;
    JToggleButton viewAllSTButton;
                    
    
    public smsHeaderPanel(){
        
        // Sets the HomeController JPanel attributes
        this.setPreferredSize(new Dimension(1400, 180));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        // Adds a seperator to the bottom of the header panel.
        this.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.PAGE_END);
        
        addMenuBar();
        
        addSMSLogoLabel();
        
        addToolbarPanel();
    }
    
    public void addMenuBar() {
        // Creates the menu bar to hold the submenus and menu items. 
        JMenuBar smsMenuBar = new JMenuBar();
        
        smsMenuBar.setBackground(Color.blue);
        
        // Creates the first menu and adds it to the sms menu bar.
        JMenu swimmingClassesMenu = new JMenu("Swimming Classes");
        
        swimmingClassesMenu.setBackground(Color.blue);
        
        JMenu classDayMenu = new JMenu("View class schedule for...");
        
        JMenuItem monMenuItem = new JMenuItem("Monday");
        classDayMenu.add(monMenuItem);
        JMenuItem tueMenuItem = new JMenuItem("Tuesday");
        classDayMenu.add(tueMenuItem);
        JMenuItem wedMenuItem = new JMenuItem("Wednesday");
        classDayMenu.add(wedMenuItem);
        JMenuItem thuMenuItem = new JMenuItem("Thursday");
        classDayMenu.add(thuMenuItem);
        JMenuItem friMenuItem = new JMenuItem("Friday");
        classDayMenu.add(friMenuItem);
        JMenuItem satMenuItem = new JMenuItem("Saturday");
        classDayMenu.add(satMenuItem);
        JMenuItem sunMenuItem = new JMenuItem("Sunday");
        classDayMenu.add(sunMenuItem);
        
        JMenuItem regMenuItem = new JMenuItem("Register students for todays classes");
        
        swimmingClassesMenu.add(classDayMenu);
        swimmingClassesMenu.addSeparator();
        swimmingClassesMenu.add(regMenuItem);
        smsMenuBar.add(swimmingClassesMenu);
        
        JMenu studentRecordsMenu = new JMenu("Student Records");
        studentRecordsMenu.setBackground(Color.blue);
        JMenuItem viewAllSRMenuItem = new JMenuItem("View all Student Records");
        studentRecordsMenu.add(viewAllSRMenuItem);
        studentRecordsMenu.addSeparator();
        JMenuItem addStudentMenuItem = new JMenuItem("Add a new Student Record");
        studentRecordsMenu.add(addStudentMenuItem);
        
        smsMenuBar.add(studentRecordsMenu);
        
        JMenu supportTicketsMenu = new JMenu("Support Tickets");
        supportTicketsMenu.setBackground(Color.blue);
        JMenuItem viewAllSTMenuItem = new JMenuItem("View all Support Tickets");
        supportTicketsMenu.add(viewAllSTMenuItem);
        
        smsMenuBar.add(supportTicketsMenu);
        
        this.add(smsMenuBar, BorderLayout.PAGE_START);
    }
    
    public void addSMSLogoLabel(){
        
        // Declares the label and label attributes to hold the logo and adds it the the toobar panel.
        JLabel smsLogoLabel = new JLabel();
        smsLogoLabel.setPreferredSize(new Dimension(405, 150));
        smsLogoLabel.setOpaque(true);
        smsLogoLabel.setVisible(true);
        
        // Sets the image icon of the JLabel to the Swimrite Management Suite Logo.
        smsLogoLabel.setIcon(new ImageIcon("images/SMS_Logo.jpg"));
        
        this.add(smsLogoLabel, BorderLayout.LINE_START);
    }
    
    public void addToolbarPanel(){
        
        smsToolbarPanel = new JPanel();
        
        // sets the smsToolbarPanel JPanel attributes
        smsToolbarPanel.setPreferredSize(new Dimension(995, 150));
        smsToolbarPanel.setVisible(true);
        smsToolbarPanel.setLayout(new FlowLayout());
        smsToolbarPanel.setBackground(Color.white);
        
        // Adds other toolbar components to the smsToolbadPanel.
        // Adds the Swimming Classes tool to the toolbar.
        addSwimmingClassesTool();
        
        // Adds the Student Records tool to the toolbar.
        addStudentRecordsTool();
        
        // Adds the Support Ticket tool to the toolbar.
        addSupportTicketTool();
        
        // Adds the smsToolbarPanel the the right/east side of the header panel.
        this.add(smsToolbarPanel, BorderLayout.LINE_END);
    }
    
    public void addSwimmingClassesTool(){
        
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
        monButton = new JToggleButton("Mon");
        monButton.setPreferredSize(daysButtonDimension);
        monButton.addActionListener(this);
        monButton.setToolTipText("<html> Click this button to view <b> Mondays </b> swimming class schedule. </html>");
        
        tueButton = new JToggleButton("Tue");
        tueButton.setPreferredSize(daysButtonDimension);
        tueButton.addActionListener(this);
        tueButton.setToolTipText("<html> Click this button to view <b> Tuesdays </b> swimming class schedule. </html>");
        
        wedButton = new JToggleButton("Wed");
        wedButton.setPreferredSize(daysButtonDimension);
        wedButton.addActionListener(this);
        wedButton.setToolTipText("<html> Click this button to view <b> Wednesdays </b> swimming class schedule. </html>");
        
        thuButton = new JToggleButton("Thu");
        thuButton.setPreferredSize(daysButtonDimension);
        thuButton.addActionListener(this);
        thuButton.setToolTipText("<html> Click this button to view <b> Thursdays </b> swimming class schedule. </html>");
        
        friButton = new JToggleButton("Fri");
        friButton.setPreferredSize(daysButtonDimension);
        friButton.addActionListener(this);
        friButton.setToolTipText("<html> Click this button to view <b> Fridays </b> swimming class schedule. </html>");
        
        satButton = new JToggleButton("Sat");
        satButton.setPreferredSize(daysButtonDimension);
        satButton.addActionListener(this);
        satButton.setToolTipText("<html> Click this button to view <b> Saturdays </b> swimming class schedule. </html>");
        
        sunButton = new JToggleButton("Sun");
        sunButton.setPreferredSize(daysButtonDimension);
        sunButton.addActionListener(this);
        sunButton.setToolTipText("<html> Click this button to view <b> Sundays </b> swimming class schedule. </html>");
        
        regButton = new JToggleButton("Register Todays Classes");
        regButton.setPreferredSize(new Dimension(200, 50));
        regButton.addActionListener(this);
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
        smsToolbarPanel.add(swimmingClassesPanel);
    }

    public void addStudentRecordsTool(){
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
        viewAllSRButton = new JToggleButton("<html> View <b> All </b> Student Records </html>");
        viewAllSRButton.setPreferredSize(studentButtonDimension);
        viewAllSRButton.addActionListener(this);
        viewAllSRButton.setToolTipText("<html> Click this button to view the student records of <b> all </b> students registered at Swimrite Leisure. </html>");
        
        // Sets the icon for the view all student records toggle button.
        viewAllSRButton.setIcon(new ImageIcon("images/icons/users.png"));
        
        addStudentButton = new JToggleButton("<html> Add a <b> New </b> Student Record </html>");
        addStudentButton.setPreferredSize(studentButtonDimension);
        addStudentButton.addActionListener(this);
        addStudentButton.setToolTipText("<html> Click this button to add a <b> new </b> student record to the Swimrite Leisure database. </html>");
        
        // Sets the icon for the add a new student record toggle button.
        addStudentButton.setIcon(new ImageIcon("images/icons/user_add.png"));
        
        // Adds the created buttons to the toolbar button group.
        toolbarButtonGroup.add(viewAllSRButton);
        toolbarButtonGroup.add(addStudentButton);
        
        // Adds all buttons to the student record panel.
        studentRecordsPanel.add(viewAllSRButton);
        studentRecordsPanel.add(addStudentButton);
        
        // Adds the student records panel to the sms toolbar panel.
        smsToolbarPanel.add(studentRecordsPanel);
    }
    
    public void addSupportTicketTool(){
        // Creates and Sets the support ticket JPanel attributes.
        JPanel supportTicketPanel = new JPanel();
        supportTicketPanel.setPreferredSize(new Dimension(250, 140));
        supportTicketPanel.setVisible(true);
        supportTicketPanel.setLayout(new FlowLayout());
        supportTicketPanel.setBackground(Color.white);
        
        // Adds a border to the support ticket panel.
        TitledBorder supportTicketBorder = BorderFactory.createTitledBorder("Support Tickets");
        supportTicketPanel.setBorder(supportTicketBorder);
        
        // Creates and Sets attributes for the button to go on the support ticket panel.
        viewAllSTButton = new JToggleButton("<html> View <b> All </b> Support Tickets <html>");
        viewAllSTButton.setPreferredSize(new Dimension(200, 50));
        viewAllSTButton.addActionListener(this);
        viewAllSTButton.setToolTipText("<html> Click this button to view <b> all </b> Swimrite Leisure student support tickets. </html>");
        
        // Sets the icon for the view all support tickets toggle button.
        viewAllSTButton.setIcon(new ImageIcon("images/icons/premium_support.png"));
        
        // Adds the created button to the toolbar button group.
        toolbarButtonGroup.add(viewAllSTButton);
        
        // Adds all buttons to the support ticket panel.
        supportTicketPanel.add(viewAllSTButton);
        
        // Adds the student records panel to the sms toolbar panel.
        smsToolbarPanel.add(supportTicketPanel);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == monButton) {
            // TODO
        }
        else if(e.getSource() == tueButton) {
            // TODO
        }
        else if(e.getSource() == wedButton) {
            // TODO
        }
        else if(e.getSource() == thuButton) {
            // TODO
        }
        else if(e.getSource() == friButton) {
            // TODO
        }
        else if(e.getSource() == satButton) {
            // TODO
        }
        else if(e.getSource() == sunButton) {
            // TODO
        }
        else if(e.getSource() == regButton) {
            // TODO
        }
        else if(e.getSource() == viewAllSRButton) {
            // TODO
        }
        else if(e.getSource() == addStudentButton) {
            // TODO
        }
        else if(e.getSource() == viewAllSTButton) {
            // TODO
        }
        
    }
    
}
