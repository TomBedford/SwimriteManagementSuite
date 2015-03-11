package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.HeaderActionEventController;

/**
 * The Menu Bar for the Swimrite Management Suite application to navigate throughout the applications functionality.
 */
public class SMSMenuBar extends JMenuBar {
    
    // Declares all Menu Item names (to be used as static references in the ActionListener class).
    /**
     * The name of the menu item to view Mondays class schedule.
     */
    public static final String MONMENUITEM_NAME = "Monday";
    
    /**
     * The name of the menu item to view Tuesdays class schedule.
     */
    public static final String TUEMENUITEM_NAME = "Tuesday";
    
    /**
     * The name of the menu item to view Wednesdays class schedule.
     */
    public static final String WEDMENUITEM_NAME = "Wednesday";
    
    /**
     * The name of the menu item to view Thursdays class schedule.
     */
    public static final String THUMENUITEM_NAME = "Thursday";
    
    /**
     * The name of the menu item to view Fridays class schedule.
     */
    public static final String FRIMENUITEM_NAME = "Friday";
    
    /**
     * The name of the menu item to view Saturdays class schedule.
     */
    public static final String SATMENUITEM_NAME = "Saturday";
    
    /**
     * The name of the menu item to view Sundays class schedule.
     */
    public static final String SUNMENUITEM_NAME = "Sunday";
    
    /**
     * The name of the menu item to register students for todays classes.
     */
    public static final String REGMENUITEM_NAME = "Register students for todays classes";
    
    /**
     * The name of the menu item to view all student records.
     */
    public static final String VIEWALLSRMENUITEM_NAME = "View all Student Records";
    
    /**
     * The name of the menu item to add a new student record.
     */
    public static final String ADDSRMENUITEM_NAME = "Add a New Student Record";
    
    /**
     * The name of the menu item to view all support tickets.
     */
    public static final String VIEWALLSTMENUITEM_NAME = "View all Support Tickets";
    
    /**
     * The name of the menu item to add a new support ticket.
     */
    public static final String ADDSTMENUITEM_NAME = "Add a New Support Ticket";
    
    /**
     * The name of the menu item to view the create, edit and delete teachers view.
     */
    public static final String CEDTEACHERSMENUITEM_NAME = "Create, Edit and Delete Teachers";
    
    /**
     * The name of the menu item to view the create, edit and delete login accounts view.
     */
    public static final String CEDLOGINACCOUNTSMENUITEM_NAME = "Create, Edit and Delete Login Accounts";
    
    /**
     * The name of the menu item to start to initialize/formate the SMS db to default settings.
     */
    public static final String INITIALISEDBMENUITEM_NAME = "Initialise/Format to default database settings";
    
    
    
    
    /**
     * Creates a Menu Bar with menu items for each of the Swimrite Management Suite application features.
     * @param headerActionEventController The action event controller that handles this classes action events. 
     */
    public SMSMenuBar(HeaderActionEventController headerActionEventController) {
        
        // Sets the background colour of the menu bar to blue
        this.setBackground(Color.blue);
        
        
        
        // Creates the first menu 
        final JMenu swimmingClassesMenu = new JMenu("Swimming Classes");
        
        // Sets the backfround of the swimming class menu to blue (to match the menu bar)
        swimmingClassesMenu.setBackground(Color.blue);
        
        
        
        // Creates a sub menu within the swimming classes menu
        final JMenu classDayMenu = new JMenu("View class schedule for...");
        
        // Creates the menu item to view mondays schedule.
        final JMenuItem monMenuItem = new JMenuItem(MONMENUITEM_NAME);
        monMenuItem.addActionListener(headerActionEventController);
        classDayMenu.add(monMenuItem);
        
        // Creates the menu item to view tuesdays schedule.
        final JMenuItem tueMenuItem = new JMenuItem(TUEMENUITEM_NAME);
        tueMenuItem.addActionListener(headerActionEventController);
        classDayMenu.add(tueMenuItem);
        
        // Creates the menu item to view wednesdays schedule.
        final JMenuItem wedMenuItem = new JMenuItem(WEDMENUITEM_NAME);
        wedMenuItem.addActionListener(headerActionEventController);
        classDayMenu.add(wedMenuItem);
        
        // Creates the menu item to view thursdays schedule.
        final JMenuItem thuMenuItem = new JMenuItem(THUMENUITEM_NAME);
        thuMenuItem.addActionListener(headerActionEventController);
        classDayMenu.add(thuMenuItem);
        
        // Creates the menu item to view fridays schedule.
        final JMenuItem friMenuItem = new JMenuItem(FRIMENUITEM_NAME);
        friMenuItem.addActionListener(headerActionEventController);
        classDayMenu.add(friMenuItem);
        
        // Creates the menu item to view saturdays schedule.
        final JMenuItem satMenuItem = new JMenuItem(SATMENUITEM_NAME);
        satMenuItem.addActionListener(headerActionEventController);
        classDayMenu.add(satMenuItem);
        
        // Creates the menu item to view sundays schedule.
        final JMenuItem sunMenuItem = new JMenuItem(SUNMENUITEM_NAME);
        sunMenuItem.addActionListener(headerActionEventController);
        classDayMenu.add(sunMenuItem);
        
        // Adds the class day menu to the swimming classes menu
        swimmingClassesMenu.add(classDayMenu);
        
        // Adds a seperator to the swimming classes menu
        swimmingClassesMenu.addSeparator();
        
        
        
        // Creates the menu item to activley register students for their lessons.
        final JMenuItem regMenuItem = new JMenuItem(REGMENUITEM_NAME);
        regMenuItem.addActionListener(headerActionEventController);
        regMenuItem.setIcon(new ImageIcon("images/icons/16x16/page_edit.png"));
        
        // Adds the register students menu item to the swimming classes menu
        swimmingClassesMenu.add(regMenuItem);
        
        
        // Adds the swimming classes menu to this menu bar
        this.add(swimmingClassesMenu);
        
        
        
        // Creates the second menu 
        final JMenu studentRecordsMenu = new JMenu("Student Records");
        studentRecordsMenu.setBackground(Color.blue);
        
        // Creates the view all student records menu item and adds it to the student records menu
        final JMenuItem viewAllSRMenuItem = new JMenuItem(VIEWALLSRMENUITEM_NAME);
        viewAllSRMenuItem.addActionListener(headerActionEventController);
        viewAllSRMenuItem.setIcon(new ImageIcon("images/icons/16x16/users.png"));
        studentRecordsMenu.add(viewAllSRMenuItem);
        
        // Adds a seperator to the student record menu
        studentRecordsMenu.addSeparator();
        
        // Creates the add student records menu item and adds it to the student records menu
        final JMenuItem addSRMenuItem = new JMenuItem(ADDSRMENUITEM_NAME);
        addSRMenuItem.addActionListener(headerActionEventController);
        addSRMenuItem.setIcon(new ImageIcon("images/icons/16x16/user_add.png"));
        studentRecordsMenu.add(addSRMenuItem);
        
        // Adds the student records menu to this menu bar
        this.add(studentRecordsMenu);
        
        
        
        // Creates the third menu item
        final JMenu supportTicketsMenu = new JMenu("Support Tickets");
        supportTicketsMenu.setBackground(Color.blue);
        
        // Creates the view all support tickets menu item and adds it to the support tickets menu
        final JMenuItem viewAllSTMenuItem = new JMenuItem(VIEWALLSTMENUITEM_NAME);
        viewAllSTMenuItem.addActionListener(headerActionEventController);
        viewAllSTMenuItem.setIcon(new ImageIcon("images/icons/16x16/premium_support.png"));
        supportTicketsMenu.add(viewAllSTMenuItem);
        
        // Adds a seperator to the support tickets menu
        supportTicketsMenu.addSeparator();
        
        // Creates the add support ticket menu item and adds it to the suppor tickets menu
        final JMenuItem addSTMenuItem = new JMenuItem(ADDSTMENUITEM_NAME);
        addSTMenuItem.addActionListener(headerActionEventController);
        addSTMenuItem.setIcon(new ImageIcon("images/icons/16x16/pencil_add.png"));
        supportTicketsMenu.add(addSTMenuItem);
        
        // Adds the support ticket menu to this menu bar
        this.add(supportTicketsMenu);
        
        
       
        // Glues all menu items added below this statement to the right hand-side of the JMenuBar
        this.add(Box.createHorizontalGlue());
        
        // Creates the fourth menu item
        final JMenu advancedOptionsMenu = new JMenu("Advanced Options");
        advancedOptionsMenu.setBackground(Color.blue);
        
        // Creates the create, edit and delete teachers menu item and adds it to the advanced options menu bar
        final JMenuItem cedTeachersMenuItem = new JMenuItem(CEDTEACHERSMENUITEM_NAME);
        cedTeachersMenuItem.addActionListener(headerActionEventController);
        advancedOptionsMenu.add(cedTeachersMenuItem);
        
        // Adds a seperator to the advanced options menu
        advancedOptionsMenu.addSeparator();
        
        // Creates the create, edit and delete login accounts menu item and adds it to the advanced options menu bar
        final JMenuItem cedLoginAccountsMenuItem = new JMenuItem(CEDLOGINACCOUNTSMENUITEM_NAME);
        cedLoginAccountsMenuItem.addActionListener(headerActionEventController);
        advancedOptionsMenu.add(cedLoginAccountsMenuItem);
        
        // Adds a seperator to the advanced options menu
        advancedOptionsMenu.addSeparator();
        
        // Creates the initialise/format db menu item and adds it to the advanced options menu bar
        final JMenuItem initialiseDbMenuItem = new JMenuItem(INITIALISEDBMENUITEM_NAME);
        initialiseDbMenuItem.addActionListener(headerActionEventController);
        advancedOptionsMenu.add(initialiseDbMenuItem);
        
        // Adds the advanced option menu bar to this menu bar
        this.add(advancedOptionsMenu);
    }
}
