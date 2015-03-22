package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LoginAccountController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;

/**
 * This class creates a login screen for the swimrite management suite.
 */
public class SMSLoginPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    
    /**
     * The reference to the panel this panel is on so it can display the sms once the authentication
     * process is successful.
     */
    private SMSMainPanel smsMainPanelRef;
    
    /**
     * The Login Account Controller to interact with the Login Account database table.
     */
    private final LoginAccountController loginAccountController = new LoginAccountController();
    
    /**
     * The GridBagConstraints to specify where to place a component on a gridbag layout.
     */
    private final GridBagConstraints c = new GridBagConstraints();
    
    /**
     * The add login account username text field input.
     */
    private final JTextField usernameField;
    
    /**
     * The login account password password field.
     */
    private final JPasswordField passwordField;
    
    /**
     * The button to Log in to the SMS using the account username and password provided in the fields.
     */
    private final JButton logInButton;
    
    /**
     * The forgotten password label. (clicked if the user has forgotten their password)
     */
    private final JLabel forgottenPasswordLabel;
    
    
    
    /**
     * Creates and adds components to this layout to create a login screen for the Swimrite Management Suite.
     * @param smsMainPanel The reference to the main panel to put on the SMS components once authentication process 
     * is successful.
     */
    public SMSLoginPanel(SMSMainPanel smsMainPanel) {
        // Sets this panels default attributes
        this.setPreferredSize(new Dimension(1400, 800));
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);
        
        // Initializes the reference to the main panel
        smsMainPanelRef = smsMainPanel;
        
        /**
         * The font for all text on this layout
         */
        final Font loginFont = new Font("Arial", Font.PLAIN, 18);
        
        
        
        /**
         * Creates and adds the logo to this panel.
         */
        // Declares the label and label attributes to hold the logo and adds it the the header panel.
        final JLabel smsLogoLabel = new JLabel();
        smsLogoLabel.setPreferredSize(new Dimension(405, 150));
        smsLogoLabel.setOpaque(true);
        smsLogoLabel.setVisible(true);
        
        // Sets the image icon of the JLabel to the Swimrite Management Suite Logo.
        smsLogoLabel.setIcon(new ImageIcon("images/SMS_Logo.jpg"));
        
        // The coordinates for where to add the logo component to the layout.
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(smsLogoLabel, c);
        
        // Resets the grid width for other components added
        c.gridwidth = 1;
        
        
        
        /**
         * Creates and adds the spacing label to this layout. (space between the logo and the login form)
         */
        // Declares the label and label attributes to hold the info text label for logging in.
        final JLabel smsSpacingLabel = new JLabel();
        smsSpacingLabel.setPreferredSize(new Dimension(405, 30));
        smsSpacingLabel.setOpaque(true);
        smsSpacingLabel.setVisible(true);
        smsSpacingLabel.setBackground(Color.white);
        
        // The coordinates for where to add the logo component to the layout.
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        this.add(smsSpacingLabel, c);
        
        // Resets the grid width for other components added
        c.gridwidth = 1;
        
        
        /**
         * Creates and add the username text label and text field to this layout.
         */
        // Label to hold the login accounts username field text.
        final JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setPreferredSize(new Dimension(90, 30));
        usernameLabel.setOpaque(true);
        usernameLabel.setVisible(true);
        usernameLabel.setBackground(Color.white);
        usernameLabel.setFont(loginFont);

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 2;
        this.add(usernameLabel, c);
        
        // The text field input for the login accounts username
        usernameField = new JTextField(20);
        usernameField.setFont(loginFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 2;
        this.add(usernameField, c);
        
        
        /**
         * Creates and add the password text label and text field to this layout.
         */
        // Label to hold the login accounts username field text.
        final JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setPreferredSize(new Dimension(90, 30));
        passwordLabel.setOpaque(true);
        passwordLabel.setVisible(true);
        passwordLabel.setBackground(Color.white);
        passwordLabel.setFont(loginFont);

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 3;
        this.add(passwordLabel, c);
        
        // The text field input for the login accounts username
        passwordField = new JPasswordField(20);
        passwordField.setFont(loginFont);
        // Adds a key listener so the user can press 'enter' to login
        passwordField.addKeyListener(this);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 3;
        this.add(passwordField, c);
        
        
        /**
         * Creates the log in button for this panel
         */
        // The button to log into the swimrite management suite using the username and password in the fields.
        logInButton = new JButton("Log In");
        logInButton.setPreferredSize(new Dimension(110, 45));
        logInButton.addActionListener(this);
        logInButton.setToolTipText("<html> Click this button to <b> Log Into </b> the Swimrite Management Suite. </html>");
        logInButton.setIcon(new ImageIcon("images/icons/bullet_go.png"));
        logInButton.setFont(loginFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(logInButton, c);
        
        /**
         * Adds a spacing label between the login and the forgot password.
         */
        final JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(110, 15));
        spacingLabel1.setOpaque(false);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 5;
        this.add(spacingLabel1, c);
        
        
        /**
         * Creates the forgot password label (incase the user has forgotten their password).
         */
        // Label to hold the login accounts username field text.
        forgottenPasswordLabel = new JLabel();
        forgottenPasswordLabel.setPreferredSize(new Dimension(110, 15));
        forgottenPasswordLabel.setOpaque(true);
        forgottenPasswordLabel.setVisible(true);
        forgottenPasswordLabel.setBackground(Color.white);
        forgottenPasswordLabel.setVerticalAlignment(SwingConstants.TOP);
        forgottenPasswordLabel.addMouseListener(this);
        forgottenPasswordLabel.setText("<HTML><u>Forgot Password?</u></HTML>");
        forgottenPasswordLabel.setForeground(Color.BLUE);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 6;
        this.add(forgottenPasswordLabel, c);
        
        
        /**
         * Adds a spacing label to push all components up on the layout
         */
        final JLabel spacingLabel2 = new JLabel();
        spacingLabel2.setPreferredSize(new Dimension(110, 200));
        spacingLabel2.setOpaque(false);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 7;
        this.add(spacingLabel2, c);
    }
 
    /**
     * Authenticates the login details from the text field and password fields inputs.
     */
    private void authenticateLogin() {
        // Authenticates the login using the input provided in the fields
        final boolean auth = loginAccountController.authenticateLogin(usernameField.getText(),
                String.valueOf(passwordField.getPassword()));

        // If the authentication proccess was successfull display the swimrite management suite
        if (auth == true) {
            // Removes this panel from the main panel
            smsMainPanelRef.removeAll();

            // Creates the Body Panel
            final SMSBodyPanel smsBodyPanel = new SMSBodyPanel(smsMainPanelRef);

            // Adds the Body Panel to the main panel (South).
            smsMainPanelRef.add(smsBodyPanel, BorderLayout.PAGE_END);

            // Creates the header panel 
            final SMSHeaderPanel smsHeaderPanel = new SMSHeaderPanel(smsBodyPanel);

            // Adds the header panel to the main panel (North).
            smsMainPanelRef.add(smsHeaderPanel, BorderLayout.PAGE_START);

            smsMainPanelRef.updateUI();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the source of the button press was the log in button
        if (e.getSource() == logInButton) {
            
            // Calls the method to authenticate the login
            authenticateLogin();
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == forgottenPasswordLabel) {
            final String username = (String) JOptionPane.showInputDialog(
                    null,
                    "Please enter your username",
                    "Enter Username",
                    JOptionPane.PLAIN_MESSAGE);

            // Gets a list of all login accounts
            final List<LoginAccount> loginAccountList = loginAccountController.getAllLoginAccounts();

            // Creates the login account to hold the matching usernames login account
            LoginAccount loginAccount = null;

            // Loops through all of the log in accounts finding the matching username and setting the loginAccount to it
            for (LoginAccount lA : loginAccountList) {
                if (lA.getUsername().equals(username)) {
                    loginAccount = lA;
                }
            }

            // If the login account is null display error message
            if (loginAccount == null) {
                JOptionPane.showMessageDialog(null,
                        "No Login Account matches the username specified.",
                        "Error Username Does Not Exist!",
                        JOptionPane.ERROR_MESSAGE);
                // Else the login account does exist
            } else {
                // Shows dialog for the user to answer the security question
                final String securityQuestionAnswer = (String) JOptionPane.showInputDialog(
                        null,
                        "Please answer your security question correctly to continue: \n"
                        + loginAccount.getSecurityQuestion(),
                        "Please Answer The Security Question",
                        JOptionPane.PLAIN_MESSAGE);
                
                // if the security question answer isnt null continue...
                if (securityQuestionAnswer != null) {

                    MessageDigest messageDigest = null;

                    try {
                        // The message Digest to salt and generate the hash set for the password
                        messageDigest = MessageDigest.getInstance("SHA-512");

                    } catch (NoSuchAlgorithmException exception) {
                        System.out.println("Error hashing security question answer");
                    }

                    if (messageDigest != null) {
                        // Adds the salt to the message digest
                        messageDigest.update(hexStringToByteArray(loginAccount.getSalt()));

                        // Updates the message digest using the security question answer string convereted to a byte array
                        messageDigest.update(securityQuestionAnswer.getBytes());

                        // Generates the hashed password data
                        final byte[] hashedSecurityQuestionAnswer = messageDigest.digest();

                        // Creates a string buffer to hold the converted byte array
                        final StringBuffer sbSecurityQuestionAnswer = new StringBuffer();

                        /**
                         * Algorithm to convert byte array into string resourced from:
                         * http://www.mkyong.com/java/java-md5-hashing-example/
                         */
                        for (int i = 0; i < hashedSecurityQuestionAnswer.length; i++) {
                            sbSecurityQuestionAnswer.append(Integer.toString((hashedSecurityQuestionAnswer[i] & 0xff) + 0x100, 16).substring(1));
                        }

                        // Resets the message digest for future hashes
                        messageDigest.reset();

                        // If the security questions answers match...
                        if (loginAccount.getSecurityQuestionAnswer().equals(sbSecurityQuestionAnswer.toString())) {
                         
                            // Declares both fields to go on the reset password panel
                            final JTextField resetPasswordField = new JTextField(15);
                            final JTextField resetPasswordConfirmationField = new JTextField(15);

                            // Creates the panel for the reset password components to go on
                            final JPanel resetPasswordPanel = new JPanel();
                            resetPasswordPanel.setPreferredSize(new Dimension(350, 100));
                            resetPasswordPanel.setLayout(new GridBagLayout());
                            
                            // The coordinates for where to add this component to the reset password panel.
                            c.gridx = 0;
                            c.gridy = 0;
                            resetPasswordPanel.add(new JLabel("New Password:"), c);
                            
                            // The coordinates for where to add this component to the reset password panel.
                            c.gridx = 1;
                            c.gridy = 0;
                            resetPasswordPanel.add(resetPasswordField, c);
                            
                            // The coordinates for where to add this component to the reset password panel.
                            c.gridx = 0;
                            c.gridy = 1;
                            resetPasswordPanel.add(new JLabel("Confirm Password:"), c);
                            
                            // The coordinates for where to add this component to the reset password panel.
                            c.gridx = 1;
                            c.gridy = 1;
                            resetPasswordPanel.add(resetPasswordConfirmationField, c);

                            // int to hold the users choice in the upcoming dialog
                            int result;
                            
                            // boolean as to whether the input was valid
                            boolean validPasswordChange = false;
                            
                            do {
                            
                                // Shows the dialog to reset the users password
                                result = JOptionPane.showConfirmDialog(null, resetPasswordPanel, 
                                         "Reset Login Accounts Password", JOptionPane.OK_CANCEL_OPTION);

                                // If the ok button was pressed on the panel
                                if (result == JOptionPane.OK_OPTION) {

                                    // The password field must contain One upper and lower case letter, a number and be atleast 5 characters long
                                    if (!resetPasswordField.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$") 
                                            || resetPasswordField.getText().length() < 5) {

                                        // Shows error dalog
                                        JOptionPane.showMessageDialog(null,
                                        "Error invalid password, the password must contain atleast:\n"
                                                + "- 1 upper case letter\n"
                                                + "- 1 lower case letter\n"
                                                + "- Be atleast 5 characters long\n"
                                                + "Please try again.",
                                        "Error Invalid Password!",
                                        JOptionPane.ERROR_MESSAGE);

                                    // Else the password meets the requirements so continue resetting the password...
                                    } else {
                                        // Checks if the password fields and the password confirmation field are the same.
                                        if (!resetPasswordField.getText().equals(resetPasswordConfirmationField.getText())) {
                                            // If not display error, otherwise continue
                                            JOptionPane.showMessageDialog(null,
                                            "<HTML> Error the password fields do <b>not</b> match!</HTML>\n"
                                                    + "Please try again.",
                                            "Error Passwords Do NOT Match!",
                                            JOptionPane.ERROR_MESSAGE);
                                        } else {
                                            // Adds the salt to the message digest
                                            messageDigest.update(hexStringToByteArray(loginAccount.getSalt()));

                                            // Updates the message digest using the password string convereted to a byte array
                                            messageDigest.update(resetPasswordField.getText().getBytes());

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

                                            // Sets the new password of the account
                                            loginAccount.setPassword(sbPassword.toString());

                                            // Updates the login account in the database with the new password
                                            loginAccountController.update(loginAccount);

                                            // Shows dialog confirming password change was successfull
                                            JOptionPane.showMessageDialog(null,
                                            "Your login accounts password has been updated successfully.",
                                            "Password Updated Successfully!",
                                            JOptionPane.OK_OPTION);

                                            // The password was valid and changed so the flag is true
                                            validPasswordChange = true;
                                        }
                                    }
                                }
                            // Loops through while the passwords entered are not valid and if the result was the 'ok' button
                            } while (!validPasswordChange && result == JOptionPane.OK_OPTION);
                            // If the security question answers don't match show error dialog
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "The security question answer is incorrect.",
                                    "Error Security Question Answer Incorrect!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == forgottenPasswordLabel) {
            forgottenPasswordLabel.setForeground(Color.darkGray);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == forgottenPasswordLabel) {
            forgottenPasswordLabel.setForeground(Color.BLUE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // If the key pressed was the 'Enter' key
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Calls the method to authenticate the login
            authenticateLogin();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    /**
     * Method to convert a hexadecimal string (the salt) back into a byte array used to hash passwords 
     * and security question answers.
     * @param  s The hex string to convert back into a byte array
     * @return Byte Array
     * source: http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
     */
    private static byte[] hexStringToByteArray(String s) {
        final int len = s.length();
        final byte[] data = new byte[len / 2];
        
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i + 1), 16));
        }
        
        return data;
    }
    
}
