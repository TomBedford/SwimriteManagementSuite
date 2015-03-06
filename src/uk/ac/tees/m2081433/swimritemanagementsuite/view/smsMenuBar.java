package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.MainActionEventController;

/**
 *
 * @author Bedford
 */
public class smsMenuBar extends JMenuBar {
    
    // Declares all Menu Item names (to be used as static references in the ActionListener class).
    public static final String MONMENUITEM_NAME = "Monday";
    
    public static final String TUEMENUITEM_NAME = "Tuesday";
    
    public static final String WEDMENUITEM_NAME = "Wednesday";
    
    public static final String THUMENUITEM_NAME = "Thursday";
    
    public static final String FRIMENUITEM_NAME = "Friday";
    
    public static final String SATMENUITEM_NAME = "Saturday";
    
    public static final String SUNMENUITEM_NAME = "Sunday";
    
    public static final String REGMENUITEM_NAME = "Register students for todays classes";
    
    public static final String VIEWALLSRMENUITEM_NAME = "View all Student Records";
    
    public static final String ADDSRMENUITEM_NAME = "Add a New Student Record";
    
    public static final String VIEWALLSTMENUITEM_NAME = "View all Support Tickets";
    
    public static final String ADDSTMENUITEM_NAME = "Add a New Support Ticket";
    
    public static final String CEDTEACHERSMENUITEM_NAME = "Create, Edit and Delete Teachers";
    
    public static final String CEDLOGINACCOUNTSMENUITEM_NAME = "Create, Edit and Delete Login Accounts";
    
    public static final String INITIALISEDBMENUITEM_NAME = "Initialise/Format to default database settings";
    
    MainActionEventController swapBodyPanelControllerRef;
    
    
    public smsMenuBar(MainActionEventController mainActionEventController){

        swapBodyPanelControllerRef = mainActionEventController;
        
        this.setBackground(Color.blue);
        
        // Creates the first menu and adds it to the sms menu bar.
        JMenu swimmingClassesMenu = new JMenu("Swimming Classes");
        swimmingClassesMenu.setBackground(Color.blue);
        
        JMenu classDayMenu = new JMenu("View class schedule for...");
        
        JMenuItem monMenuItem = new JMenuItem(MONMENUITEM_NAME);
        monMenuItem.addActionListener(swapBodyPanelControllerRef);
        classDayMenu.add(monMenuItem);
        
        JMenuItem tueMenuItem = new JMenuItem(TUEMENUITEM_NAME);
        tueMenuItem.addActionListener(swapBodyPanelControllerRef);
        classDayMenu.add(tueMenuItem);
        
        JMenuItem wedMenuItem = new JMenuItem(WEDMENUITEM_NAME);
        wedMenuItem.addActionListener(swapBodyPanelControllerRef);
        classDayMenu.add(wedMenuItem);
        
        JMenuItem thuMenuItem = new JMenuItem(THUMENUITEM_NAME);
        thuMenuItem.addActionListener(swapBodyPanelControllerRef);
        classDayMenu.add(thuMenuItem);
        
        JMenuItem friMenuItem = new JMenuItem(FRIMENUITEM_NAME);
        friMenuItem.addActionListener(swapBodyPanelControllerRef);
        classDayMenu.add(friMenuItem);
        
        JMenuItem satMenuItem = new JMenuItem(SATMENUITEM_NAME);
        satMenuItem.addActionListener(swapBodyPanelControllerRef);
        classDayMenu.add(satMenuItem);
        
        JMenuItem sunMenuItem = new JMenuItem(SUNMENUITEM_NAME);
        sunMenuItem.addActionListener(swapBodyPanelControllerRef);
        classDayMenu.add(sunMenuItem);
        
        JMenuItem regMenuItem = new JMenuItem(REGMENUITEM_NAME);
        regMenuItem.addActionListener(swapBodyPanelControllerRef);
        regMenuItem.setIcon(new ImageIcon("images/icons/16x16/page_edit.png"));
        
        swimmingClassesMenu.add(classDayMenu);
        
        swimmingClassesMenu.addSeparator();
        
        swimmingClassesMenu.add(regMenuItem);
        
        this.add(swimmingClassesMenu);
        
        
        
        JMenu studentRecordsMenu = new JMenu("Student Records");
        studentRecordsMenu.setBackground(Color.blue);
        
        JMenuItem viewAllSRMenuItem = new JMenuItem(VIEWALLSRMENUITEM_NAME);
        viewAllSRMenuItem.addActionListener(swapBodyPanelControllerRef);
        viewAllSRMenuItem.setIcon(new ImageIcon("images/icons/16x16/users.png"));
        studentRecordsMenu.add(viewAllSRMenuItem);
        
        studentRecordsMenu.addSeparator();
        
        JMenuItem addSRMenuItem = new JMenuItem(ADDSRMENUITEM_NAME);
        addSRMenuItem.addActionListener(swapBodyPanelControllerRef);
        addSRMenuItem.setIcon(new ImageIcon("images/icons/16x16/user_add.png"));
        studentRecordsMenu.add(addSRMenuItem);
        
        this.add(studentRecordsMenu);
        
        
        
        JMenu supportTicketsMenu = new JMenu("Support Tickets");
        supportTicketsMenu.setBackground(Color.blue);
        
        JMenuItem viewAllSTMenuItem = new JMenuItem(VIEWALLSTMENUITEM_NAME);
        viewAllSTMenuItem.addActionListener(swapBodyPanelControllerRef);
        viewAllSTMenuItem.setIcon(new ImageIcon("images/icons/16x16/premium_support.png"));
        supportTicketsMenu.add(viewAllSTMenuItem);
        
        JMenuItem addSTMenuItem = new JMenuItem(ADDSTMENUITEM_NAME);
        addSTMenuItem.addActionListener(swapBodyPanelControllerRef);
        addSTMenuItem.setIcon(new ImageIcon("images/icons/16x16/pencil_add.png"));
        supportTicketsMenu.add(addSTMenuItem);
        
        this.add(supportTicketsMenu);
        
        
       
        // sticks all menu items added below this line to the right hand-side of the JMenuBar.
        this.add(Box.createHorizontalGlue());
        
        JMenu advancedOptionsMenu = new JMenu("Advanced Options");
        advancedOptionsMenu.setBackground(Color.blue);
        
        JMenuItem cedTeachersMenuItem = new JMenuItem(CEDTEACHERSMENUITEM_NAME);
        cedTeachersMenuItem.addActionListener(swapBodyPanelControllerRef);
        advancedOptionsMenu.add(cedTeachersMenuItem);
        
        JMenuItem cedLoginAccountsMenuItem = new JMenuItem(CEDLOGINACCOUNTSMENUITEM_NAME);
        cedLoginAccountsMenuItem.addActionListener(swapBodyPanelControllerRef);
        advancedOptionsMenu.add(cedLoginAccountsMenuItem);
        
        JMenuItem initialiseDbMenuItem = new JMenuItem(INITIALISEDBMENUITEM_NAME);
        initialiseDbMenuItem.addActionListener(swapBodyPanelControllerRef);
        advancedOptionsMenu.add(initialiseDbMenuItem);
        
        this.add(advancedOptionsMenu);
    }
}
