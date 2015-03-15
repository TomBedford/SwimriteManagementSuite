package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LoginAccountController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;

/**
 * This class is used to Create, Edit and Delete Login Accounts at Swimrite Leisure.
 */
public class CEDLoginAccountsPanel extends JPanel {
    
    /**
     * The SMS Body Panel reference for when swapping panels on the body panel.
     */
    SMSBodyPanel smsBodyPanel;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * The Login Account Controller to interact with the Login Account database table.
     */
    LoginAccountController loginAccountController = new LoginAccountController();
    
    /**
     * The list to hold all login accounts for the Swimrite Management Suite.
     */
    List<LoginAccount> loginAccountList;
    
    /**
     * The array of strings to hold the column headers/names for the login accounts table.
     */
    String[] columnNames;
    
    /**
     * The extended JTable Login Accounts table displaying all login accounts for the Swimrite Management Suite.
     */
    LoginAccountTable loginAccountTable;
    
    
    
    /**
     * Creates the login accounts table with buttons to create, edit and delete login accounts within the table.
     * @param smsBP The body panel reference so that panels displayed can be changed.
     */
    public CEDLoginAccountsPanel(SMSBodyPanel smsBP) {
        // Initializes this panels body panel reference
        smsBodyPanel = smsBP;
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // sets the create, edit and delete login accounts JPanels default attributes
        this.setPreferredSize(new Dimension(1375, 605));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Loads the list of All login accounts for the Swimrite Mnagement Suite.
        loginAccountList = loginAccountController.getAllLoginAccounts();
        
        // Creates and initializes the title label
        final JLabel titleLabel = new JLabel("SMS Login Accounts");
        titleLabel.setPreferredSize(new Dimension(1200, 100));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.white);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        
        // The coordinates for where to add the spacing component to the layout
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        this.add(titleLabel, c);
        
        // resets the grid width for when adding other components to this layout
        c.gridwidth = 1;
        
        // Adds the teacher table containing all teachers at swimrite to this panel
        addLoginAccountTable();
        
        // Creates and initializes a label used to put space between table (left) and the east panel (right)
        final JLabel spacingLabel = new JLabel();
        spacingLabel.setPreferredSize(new Dimension(50, 500));
        spacingLabel.setOpaque(false);
        
        // The coordinates for where to add the spacing component to the layout
        c.gridx = 1;
        c.gridy = 1;
        this.add(spacingLabel, c);
        
        // Adds a button panel to contain all buttons needed for this panel
        addDynamicFormPanel();
        
        // Invisible label used to push all other components in the layout to the top.
        final JLabel pullComponentsUp = new JLabel();
        
        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 99999;
        c.weighty = 1.0;
        c.fill = 1;
        this.add(pullComponentsUp, c);
        
        // Resets these values so as not to mess with the placement of other layout components that are loaded on runtime
        c.weighty = 0;
        c.fill = 0;
    }
    
    /**
     * Creates the Login Account table to display all the Login Accounts for the Swimrite Management Suite.
     */
    public void addLoginAccountTable() {
        // The an array of column names for the table
        columnNames = new String[]{" Username",
                                " Administrator"};
        
        // Creates a table model to hold the login accounts (converted from a list) and the column names
        // This table model is extended so that the admin column can have image icons if the login account is an admin
        final LoginAccountTableModel model = new LoginAccountTableModel(convertListForTable(), columnNames);
        
        // The Login Account JTable (attributes initialised within the class) initialised using the model
        loginAccountTable = new LoginAccountTable(model);
        
        // Initializes the scroll pane to hold the teacher table, setting attributes
        final JScrollPane tableScrollPane = new JScrollPane(loginAccountTable);
        tableScrollPane.setPreferredSize(new Dimension(500, 500));

        // The coordinates for where to add the table component to the layout
        c.gridx = 0;
        c.gridy = 1;
        this.add(tableScrollPane, c);
    }
        
    /**
     * Converts a list of Login Accounts into a two dimensional array of objects suitable for the Login Accounts
     * tables parameters.
     * @return 2 Dimensional array of object containing the formatted login accounts list.
     */
    public Object[][] convertListForTable() {

        /**
         * The two dimensional array to hold the Login Accounts.
         * size of the login account list is used for the first array size.
         * size of the column names (the amount of different data columns to put into the object for the table)
         */
        final Object[][] tableLoginAccountData = new Object[loginAccountList.size()][columnNames.length];
        
        // Loops through the entire login accounts list to add the correct data from the list to the object array
        for (int i = 0; i < loginAccountList.size(); i++) {
            // Adds the login accounts username to that index of the object array
            tableLoginAccountData[i][0] = loginAccountList.get(i).getUsername();
            // Adds the login accounts admin status to that index of the object array
            tableLoginAccountData[i][1] = loginAccountList.get(i).getAdmin();
            // Adds the login accounts security question to that index of the object array
            tableLoginAccountData[i][1] = loginAccountList.get(i).getAdmin();
        }
        // Returns the converted 2 dimensional objecty array
        return tableLoginAccountData;
    }
    
    /**
     * Adds a the login account dynamic form panel so users can easily add, edit and delete login accounts.
     */
    public void addDynamicFormPanel() {
        
        // Creates the login account dynamic form panel
        final LoginAccountDynamicFormPanel loginAccountDynamicFormPanel = new LoginAccountDynamicFormPanel(smsBodyPanel, loginAccountTable, loginAccountList);
        
        // The coordinates for where to add the east panel component to the layout
        c.gridx = 2;
        c.gridy = 1;
        this.add(loginAccountDynamicFormPanel, c);
    }
}
