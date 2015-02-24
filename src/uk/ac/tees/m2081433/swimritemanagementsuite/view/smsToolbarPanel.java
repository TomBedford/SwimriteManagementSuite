package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;

/**
 *
 * @author Bedford
 */
public class smsToolbarPanel extends JPanel implements ActionListener {
    
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
    JToggleButton addSTButton;
    
    smsBodyPanel smsBodyPanel;
    
    public smsToolbarPanel() {
        
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
        
        // smsBodyPanel = new smsBodyPanel();
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
        this.add(swimmingClassesPanel);
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
        this.add(studentRecordsPanel);
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
        
        // Creates and Sets attributes for the view all button to go on the support ticket panel.
        viewAllSTButton = new JToggleButton("<html> View <b> All </b> Support Tickets <html>");
        viewAllSTButton.setPreferredSize(new Dimension(200, 50));
        viewAllSTButton.addActionListener(this);
        viewAllSTButton.setToolTipText("<html> Click this button to view <b> all </b> Swimrite Leisure student support tickets. </html>");
        
        // Sets the icon for the view all support tickets toggle button.
        viewAllSTButton.setIcon(new ImageIcon("images/icons/premium_support.png"));
        
        // Creates and Sets attributes for the add button to go on the support ticket panel.
        addSTButton = new JToggleButton("<html> Add a <b> New </b> Support Tickets <html>");
        addSTButton.setPreferredSize(new Dimension(200, 50));
        addSTButton.addActionListener(this);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == monButton) {
            
            System.out.println("MONDAY BUTTON PRESSED!");
            
//            DaySchedulePanel daySchedulePanel;
//            
//            try {
//                daySchedulePanel = new DaySchedulePanel(Day.MONDAY);
//                
//                JScrollPane dayScheduleScrollPane = new JScrollPane(daySchedulePanel);
//                dayScheduleScrollPane.setPreferredSize(new Dimension(1380, 575));
//                
//                smsBodyPanel.removeWelcomePanel();
//                smsBodyPanel.revalidate();
//                
//                smsBodyPanel.add(dayScheduleScrollPane);
//                smsBodyPanel.revalidate();
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(smsToolbarPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
        }
        else if(e.getSource() == tueButton) {
            // TODO
            System.out.println("TUESDAY BUTTON PRESSED!");
        }
        else if(e.getSource() == wedButton) {
            // TODO
            System.out.println("WEDNESDAY BUTTON PRESSED!");
        }
        else if(e.getSource() == thuButton) {
            // TODO
            System.out.println("THURSDAY BUTTON PRESSED!");
        }
        else if(e.getSource() == friButton) {
            // TODO
            System.out.println("FRIDAY BUTTON PRESSED!");
        }
        else if(e.getSource() == satButton) {
            // TODO
            System.out.println("SATURDAY BUTTON PRESSED!");
        }
        else if(e.getSource() == sunButton) {
            // TODO
            System.out.println("SUNDAY BUTTON PRESSED!");
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
        } else if(e.getSource() == addSTButton) {
            // TODO
        }
        
    }
    
}
