package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Bedford
 */
public class smsMenuBar extends JMenuBar {
    
    public smsMenuBar(){

        this.setBackground(Color.blue);
        
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
        this.add(swimmingClassesMenu);
        
        JMenu studentRecordsMenu = new JMenu("Student Records");
        studentRecordsMenu.setBackground(Color.blue);
        JMenuItem viewAllSRMenuItem = new JMenuItem("View all Student Records");
        studentRecordsMenu.add(viewAllSRMenuItem);
        studentRecordsMenu.addSeparator();
        JMenuItem addStudentMenuItem = new JMenuItem("Add a new Student Record");
        studentRecordsMenu.add(addStudentMenuItem);
        
        this.add(studentRecordsMenu);
        
        JMenu supportTicketsMenu = new JMenu("Support Tickets");
        supportTicketsMenu.setBackground(Color.blue);
        JMenuItem viewAllSTMenuItem = new JMenuItem("View all Support Tickets");
        supportTicketsMenu.add(viewAllSTMenuItem);
        
        this.add(supportTicketsMenu);
        
    }
    
}
