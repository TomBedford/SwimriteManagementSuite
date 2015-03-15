package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LoginAccountController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;

/**
 * The login dialog needed when certain operations in the application require administrator permission.
 */
public class LoginDialog {
    
    /**
     * The static method to display a login dialog and checks whether the details entered were a valid admin.
     * @param infoText The text to display on the login dialog (what they are logging in to do..)
     * @return boolean as to whether the details are correct or not.
     */
    public static boolean adminLoginDialog(String infoText) {
        /**
         * The Login Account Controller to interact with the Login Account database table.
         */
        final LoginAccountController loginAccountController = new LoginAccountController();
        
        // The gridbag constrint for the login dialog panel
        final GridBagConstraints c = new GridBagConstraints();
        
        // The panel to hold all login label and text field components
        final JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(600, 100));
        loginPanel.setVisible(true);
        loginPanel.setLayout(new GridBagLayout());
        
        // The coordinates for where to add the information text component to the layout
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        loginPanel.add(new JLabel(infoText), c);
        
        // Resets grid width for when adding other components
        c.gridwidth = 1;
        
        // The coordinates for where to add the spacing component to the layout (between info text and login components)
        c.gridx = 0;
        c.gridy = 1;
        loginPanel.add(new JLabel(" "), c);
        
        // The coordinates for where to add the username text label component to the layout.
        c.gridx = 0;
        c.gridy = 2;
        loginPanel.add(new JLabel("Username:"), c);
        
        // Creates the text field input to hold the username
        final JTextField usernameField = new JTextField(30);
        
        // The coordinates for where to add the username text field component to the layout.
        c.gridx = 1;
        c.gridy = 2;
        loginPanel.add(usernameField, c);
        
        // The coordinates for where to add the password text label component to the layout.
        c.gridx = 0;
        c.gridy = 3;
        loginPanel.add(new JLabel("Password:"), c);
        
        // Creates the password field input to hold the users password
        final JPasswordField passwordField = new JPasswordField(30);
        
        // The coordinates for where to add the passowrd text field component to the layout.
        c.gridx = 1;
        c.gridy = 3;
        loginPanel.add(passwordField, c);

        // Shows the confirm dialog we just created
        final int result = JOptionPane.showConfirmDialog(null, loginPanel, "Administrator Login", JOptionPane.OK_CANCEL_OPTION);
        
        // If the result of the button press was the ok button
        if (result == JOptionPane.OK_OPTION) {
           // Gets all admin accounts from the login account database
           final List<LoginAccount> adminAccounts = loginAccountController.getAllAdminLoginAccounts();
           
           // If the list of admin accounts is not empty
           if (!adminAccounts.isEmpty()) {
               
               // Creates a login account to hold the info of the login account if a username match is found
               LoginAccount loginAccountMatch = null;
               
               // Loops through the list of admin accounts
               for (LoginAccount lA: adminAccounts) {
                   // Checks if the username is equal to the one entered
                   if (lA.getUsername().equals(usernameField.getText())) {
                       // If so, sets the login account match to the login account where the username matched 
                       loginAccountMatch = lA;
                   }
               }
               
               // Checks if the login account match is null (if there wasnt a match in the admin list)
               if (loginAccountMatch == null) {
                   // Displays no username match error
                   JOptionPane.showMessageDialog(null,
                                "No Administrator login account exists with the username specified, please try again.\n",
                                "Error Admin Username Doesn't Exist!",
                                JOptionPane.ERROR_MESSAGE);
                } else {
                    // Otherwise the admin account does exist
                    // Convert the password char array into a string
                    final String passwordString = String.valueOf(passwordField.getPassword());

                    MessageDigest messageDigest = null;

                    try {
                        // The message Digest to salt and generate the hash set for the password
                        messageDigest = MessageDigest.getInstance("SHA-512");

                    } catch (NoSuchAlgorithmException exception) {
                        System.out.println("Error hashing password to check if admin password");
                    }

                    if (messageDigest != null) {
                        // Adds the salt to the message digest
                        messageDigest.update(hexStringToByteArray(loginAccountMatch.getSalt()));

                        // Updates the message digest using the password string
                        messageDigest.update(passwordString.getBytes());

                        // Generates the hashed password data
                        final byte[] hashedCheckPassword = messageDigest.digest();

                        // Creates a string buffer to hold the converted byte array
                        final StringBuffer sbCheckPassword = new StringBuffer();

                        /**
                         * Algorithm to convert byte array into string resourced from:
                         * http://www.mkyong.com/java/java-md5-hashing-example/
                         */
                        for (int i = 0; i < hashedCheckPassword.length; i++) {
                            sbCheckPassword.append(Integer.toString((hashedCheckPassword[i] & 0xff) + 0x100, 16).substring(1));
                        }

                        // Resets the message digest for future hashes
                        messageDigest.reset();

                        // Checks if the password entered was equal to the password in the database
                        if (sbCheckPassword.toString().equals(loginAccountMatch.getPassword())) {
                            // Returns true as the username and password are correct
                            return true;
                        } else {
                            // Displays password match error
                            JOptionPane.showMessageDialog(null,
                                         "The password entered is incorrect.\n",
                                         "Error Admin Password Incorrect!",
                                         JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
        // If this statement is reached then they have not entered any details or details are incorrect
        return false;
    }

    /**
     * Method to convert a hexadecimal string (the salt) back into a byte array used to hash passwords and security
     * question answers.
     *
     * @param s The hex string to convert back into a byte array
     * @return Byte Array source:
     * http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
     */
    public static byte[] hexStringToByteArray(String s) {
        final int len = s.length();
        final byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }
}
