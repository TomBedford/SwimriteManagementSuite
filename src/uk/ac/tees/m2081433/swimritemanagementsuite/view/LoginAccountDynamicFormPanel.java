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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LoginAccountController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;

/**
 * This panel contains buttons to add, edit and delete login accounts and holds a swappable panel
 * to swap forms in and out when adding and editing login accounts.
 */
public class LoginAccountDynamicFormPanel extends JPanel implements ActionListener {
    
    /**
     * The SMS Body Panel reference for when swapping panels on the body panel.
     */
    private final SMSBodyPanel smsBodyPanel;
    
    /**
     * The login account table to check the selected index for editing and deletion of login accounts.
     */
    private final LoginAccountTable loginAccountTable;
    
    /**
     * The list that contains all login accounts.
     */
    private final List<LoginAccount> loginAccountList;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    private final GridBagConstraints c;
    
    /**
     * The Login Account Controller to interact with the Login Account database table.
     */
    private final LoginAccountController loginAccountController = new LoginAccountController();
    
    /**
     * The button to add a Login Account.
     */
    private JButton addLoginAccountButton;
    
    /**
     * The button to edit a Login Account.
     */
    private JButton editLoginAccountButton;
    
    /**
     * The button to delete a Login Account.
     */
    private JButton deleteLoginAccountButton;
    
    
    
    /**
     * The panel that holds all swappable forms for adding and editing login accounts.
     */
    private JPanel formPanel;
    
    /**
     * The panel that contains the form to add a new login account.
     */
    private AddLoginAccountForm addLoginAccountForm;
    
    /**
     * The panel that contains buttons and forms to edit an existing login account.
     */
    private EditLoginAccountForm editLoginAccountForm;
    
    /**
     * This constructor adds the button panel containing the add, edit and delete login accounts buttons
     * and adds a swappable panel to swap forms in and out when adding and editing login accounts.
     * @param smsBP the reference to the body panel to reload the CED login accounts panel after adding, editing and deleting.
     * @param lAT The reference to the table to check the selected index.
     * @param lAL The list of login accounts to get the right account to edit and/or delete.
     */
    public LoginAccountDynamicFormPanel(SMSBodyPanel smsBP, LoginAccountTable lAT, List<LoginAccount> lAL) {
        // Initializes this panels default attributes
        this.setPreferredSize(new Dimension(650, 500));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        
        // Initializes this panels body panel reference
        smsBodyPanel = smsBP;
        
        // Initializes the table that holds all the login accounts
        loginAccountTable = lAT;
        
        // Initializes the list containing all login accounts
        loginAccountList = lAL;
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // Adds the button panel to this layout containing the add, edit and delete login accounts buttons
        addButtonPanel();
        
        // Adds the panel that will dynamically hold all form panel for adding and editing login accounts
        addFormPanel();
    }
    
    /**
     * Adds the button panel containing the add, edit and delete login account buttons onto this panel.
     */
    private void addButtonPanel() {
        // Creates and initializes the panel to hold all of the buttons for this panel
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(650, 50));
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.white);
        
        // The button to add a login account to the login account database table
        addLoginAccountButton = new JButton("Add Login Account");
        addLoginAccountButton.setPreferredSize(new Dimension(200, 45));
        addLoginAccountButton.addActionListener(this);
        addLoginAccountButton.setToolTipText("<html> Click this button to <b> add </b> a Login Account. </html>");
        addLoginAccountButton.setIcon(new ImageIcon("images/icons/user_add.png"));
        
        // Adds the add login account button to the button panel
        buttonPanel.add(addLoginAccountButton);
        
        
        // The button to edit the selected login account in the database table
        editLoginAccountButton = new JButton("Edit Login Account");
        editLoginAccountButton.setPreferredSize(new Dimension(200, 45));
        editLoginAccountButton.addActionListener(this);
        editLoginAccountButton.setToolTipText("<html> Click this button to <b> edit </b> the seletected Login Account. </html>");
        editLoginAccountButton.setIcon(new ImageIcon("images/icons/user_edit.png"));
        
        // Adds the edit a login account button to the button panel
        buttonPanel.add(editLoginAccountButton);
        
        
        // The button to delete the selected login account from the login account database table
        deleteLoginAccountButton = new JButton("Delete Login Account");
        deleteLoginAccountButton.setPreferredSize(new Dimension(200, 45));
        deleteLoginAccountButton.addActionListener(this);
        deleteLoginAccountButton.setToolTipText("<html> Click this button to <b> delete </b> the selected Login Account. </html>");
        deleteLoginAccountButton.setIcon(new ImageIcon("images/icons/user_delete.png"));
        
        // Adds the delete a teacher button to the button panel
        buttonPanel.add(deleteLoginAccountButton);
        
        
        
        // The coordinates for where to add the button panel to the layout
        c.gridx = 0;
        c.gridy = 0;
        this.add(buttonPanel, c);
    }
    
    /**
     * Adds the form panel that will be used to hold panels added and removed at runtime containing
     * different forms (during adding and editing of login accounts).
     */
    private void addFormPanel() {
        // Creates and initializes the panel to hold all of the forms for this panel
        formPanel = new JPanel();
        formPanel.setPreferredSize(new Dimension(650, 450));
        formPanel.setVisible(true);
        formPanel.setBackground(Color.white);
        
        // The coordinates for where to add the spacing component to the layout
        c.gridx = 0;
        c.gridy = 1;
        this.add(formPanel, c);
    }
    
    /**
     * Converts a hexadecimal string into a byte array.
     * source: http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
     * @param s The hexadecimal string
     * @return The byte array from the hex string
     */
    private static byte[] hexStringToByteArray(String s) {
        final int length = s.length();
        final byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the source of the button press was the add login account button
        if (e.getSource() == addLoginAccountButton) {
            
            // If the add login panel doesnt equal null (its been initialized)
            if (addLoginAccountForm != null) {
                // Checks if the add login account is already on the panel, if so it removes it
                if (addLoginAccountForm.getParent() == formPanel) {
                    formPanel.remove(addLoginAccountForm);
                } 
            }
            
            // If the edit login panel doesnt equal null (its been initialized)
            if (editLoginAccountForm != null) {
                // Chekcs if the edit login account is already on the panel, if so it removes it
                if (editLoginAccountForm.getParent() == formPanel) {
                    formPanel.remove(editLoginAccountForm);
                }
            }
            
            // Calls the method to load the add login account form onto the form panel
            addLoginAccountForm = new AddLoginAccountForm(formPanel, smsBodyPanel);
                    
            // Adds the add login account form to the form panel and updates the UI to be displayed
            formPanel.add(addLoginAccountForm);
            formPanel.updateUI();
            
        // If the source of the button press was the edit login account button
        } else if (e.getSource() == editLoginAccountButton) {
            
            // Checks if anything is selected in the table
            if (loginAccountTable.isColumnSelected(0) || loginAccountTable.isColumnSelected(1)) {
                
                // Gets the index from the row that is selected
                final int index = loginAccountTable.getSelectedRow();
                
                // Creates a panel to hold the option dialog confirm password components
                final JPanel editPasswordpanel = new JPanel();
                // Sets the size of the panel
                editPasswordpanel.setPreferredSize(new Dimension(510, 30));
                
                // Adds a label describing what the user needs to do to the edit password panel
                editPasswordpanel.add(new JLabel("Inorder to edit " + loginAccountList.get(index).getUsername() + "'s login account please enter the accounts password:"));
                
                // The password field for the edit password panel and adds it to that panel
                final JPasswordField editPasswordField = new JPasswordField(41);
                editPasswordpanel.add(editPasswordField);
                
                // Shows the confirm dialog we just created
                final int result = JOptionPane.showConfirmDialog(null, editPasswordpanel, "Edit Account Login", JOptionPane.OK_CANCEL_OPTION);

                // If the result of the button press was the ok button
                if (result == JOptionPane.OK_OPTION) {

                    // If the password entered isnt null
                    if (editPasswordField.getPassword() != null) {
                        // Convert the password char array into a string
                        final String passwordString = String.valueOf(editPasswordField.getPassword());
                        
                        // Creates a message digest to salt and hash the password
                        MessageDigest messageDigest = null;

                        try {
                            // The message Digest to salt and generate the hash set for the password using SHA-512
                            messageDigest = MessageDigest.getInstance("SHA-512");

                        } catch (NoSuchAlgorithmException exception) {
                            System.out.println("Error hashing password for edit login account");
                        }

                        if (messageDigest != null) {

                            // Adds the salt to the message digest
                            messageDigest.update(hexStringToByteArray(loginAccountList.get(index).getSalt()));

                            // Updates the message digest using the password string convereted to a byte array
                            messageDigest.update(passwordString.getBytes());

                            // Generates the hashed password data
                            final byte[] hashedPassword = messageDigest.digest();

                            // Creates a string buffer to hold the converted byte array
                            final StringBuffer sbPassword = new StringBuffer();

                            /**
                             * Algorithm to convert byte array into string resourced from:
                             * http://www.mkyong.com/java/java-md5-hashing-example/
                             */
                            for (int i = 0; i < hashedPassword.length; i++) {
                             sbPassword.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
                            }

                            // Resets the message digest for future hashes
                            messageDigest.reset();

                            // If the password is the same as the one in the db, show editing panel for that login account
                            if (sbPassword.toString().equals(loginAccountList.get(index).getPassword())) {
                                // If the add login panel doesnt equal null (its been initialized)
                                if (addLoginAccountForm != null) {
                                    // Checks if the add login account is already on the panel, if so it removes it
                                    if (addLoginAccountForm.getParent() == formPanel) {
                                        formPanel.remove(addLoginAccountForm);
                                    } 
                                }

                                // If the edit login panel doesnt equal null (its been initialized)
                                if (editLoginAccountForm != null) {
                                    // Chekcs if the edit login account is already on the panel, if so it removes it
                                    if (editLoginAccountForm.getParent() == formPanel) {
                                        formPanel.remove(editLoginAccountForm);
                                    }
                                }

                                // Calls the method to load the add login account form onto the form panel
                                // Sends it the appropriate login account using the corresponding index from the table.
                                editLoginAccountForm = new EditLoginAccountForm(formPanel, smsBodyPanel, loginAccountList.get(index));

                                // Adds the add login account form to the form panel and updates the UI to be displayed
                                formPanel.add(editLoginAccountForm);
                                formPanel.updateUI();
                            } else {
                                // Password incorrect error message
                                JOptionPane.showMessageDialog(null,
                                    "The password entered was incorrect.",
                                    "Error Password Incorrect!",
                                    JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            } else {
                // Displays an error message that no login account is currently selected
                JOptionPane.showMessageDialog(null, "<HTML> No Login Account is selected!\n"
                        + "To edit a Login Account please select the login account and click 'Edit Login Acount'"
                                            , "No Login Account Selected", JOptionPane.OK_OPTION);
            }
            // If the source of the button press was the delete login account button
        } else if (e.getSource() == deleteLoginAccountButton) {

            // Checks if anything is selected in the table
            if (loginAccountTable.isColumnSelected(0) || loginAccountTable.isColumnSelected(1)) {

                // Gets the index from the row that is selected
                final int index = loginAccountTable.getSelectedRow();

                // Shows an Option Pane asking if wants to remove login account from the database
                final int answer = JOptionPane.showConfirmDialog(null,
                        "<HTML> Do you want to <b> <font color='red'> delete </font> </b> <u>"
                        + loginAccountList.get(index).getUsername()
                        + "</u>  from the database?</HTML>", "Delete Login Account From Database?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                // Switch to determine users choice.
                switch (answer) {
                    // The user wants delete the login account
                    case 0: // Calls static method to load admin authentication dialog
                            final boolean adminAuthentication = LoginDialog.adminLoginDialog("Only Administrators can delete Login Accounts. Please Log In to delete the account...");
                            
                            if (adminAuthentication == true) {
                                // Deletes the login account in the database
                                loginAccountController.delete(loginAccountList.get(index));
                                // Re-loads this panel with the updated table
                                smsBodyPanel.addViewCEDLoginAccountsPanel();
                            } 
                            break;
                        
                    // The user does not want to delete the login account so don't do anything.
                    case 1:
                            break;
                    default:
                            break;
                }
            } else {
                // Displays an error message that no student record is currently selected
                JOptionPane.showMessageDialog(null, "<HTML> No Login Account is selected!\n"
                        + "To delete a Login Account please select the login account and click 'Delete Login Acount'"
                                            , "No Login Account Selected", JOptionPane.OK_OPTION);
            }
        }
    }
}
