package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LoginAccountFormInputVerifier;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LoginAccountController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;

/**
 * This class adds components to itself so that a user can add a new login account to the login account database.
 */
public class AddLoginAccountForm extends JPanel implements ActionListener {
    
    /**
     * The form panel reference from when canceling the adding of a student.
     */
    JPanel formPanel;
    
    /**
     * The SMS Body Panel reference for when swapping panels on the body panel.
     */
    SMSBodyPanel smsBodyPanel;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * The input verifier for the add login account form (validates text field inputs).
     */
    LoginAccountFormInputVerifier inputVerifier = new LoginAccountFormInputVerifier();
    
    /**
     * The Login Account Controller to interact with the Login Account database table.
     */
    LoginAccountController loginAccountController = new LoginAccountController();
    
    
    
    /**
     * The Username field text field name needed for referencing in the input verifier.
     */
    public static String usernameFieldName = "username";
    
    /**
     * The Password field text field name needed for referencing in the input verifier.
     */
    public static String passwordFieldName = "password";
    
    /**
     * The Password Confirmation field text field name needed for referencing in the input verifier.
     */
    public static String passwordConfirmationFieldName = "passwordConfirmation";
    
    /**
     * The Security Question field text field name needed for referencing in the input verifier.
     */
    public static String securityQuestionFieldName = "securityQuestion";
    
    /**
     * The Security Question Answer field text field name needed for referencing in the input verifier.
     */
    public static String securityQuestionAnswerFieldName = "securityQuestionAnswer";
    
    
    
    
    /**
     * The font (defining font style, font type and font size) for all form text labels.
     */
    Font labelFont = new Font("Arial", Font.PLAIN, 18);
    
    /**
     * The font (defining font style, font type and font size) for all form text field inputs.
     */
    Font textFont = new Font("Arial", Font.PLAIN, 17);
    
    /**
     * The add login account username form text field.
     */
    JTextField usernameField;
    
    /**
     * The login account password form text field.
     */
    JTextField passwordField;
    
    /**
     * The login account password confirmation form text field.
     */
    JTextField passwordConfirmationField;
    
    /**
     * The login account security question form text field.
     */
    JTextField securityQuestionField;
    
    /**
     * The login account security question answer form text field.
     */
    JTextField securityQuestionAnswerField;
    
    /**
     * The combo box specifiying as to whether the login account is an admin.
     */
    JComboBox adminComboBox;
    
    
    
    /**
     * The button to create a new login account using the input specified in the form.
     */
    JButton createButton;
    
    /**
     * The button to clear all of the form fields.
     */
    JButton clearFormButton;
    
    /**
     * The button to cancel the adding of a new login account.
     */
    JButton cancelFormButton;
    
    
    /**
     * Adds form text labels, text field input and info icons for each field as well as buttons to Add, Clear the form
     * and cancel the creation of a new login account.
     * @param fP The reference to the panel this panel is on 
     * @param smsBP The reference to the body panel that contains the pan
     */
    public AddLoginAccountForm(JPanel fP, SMSBodyPanel smsBP) {
        // Creates and initializes the panel add a new login account
        this.setPreferredSize(new Dimension(650, 450));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        
        // Initializes the reference for the panel that this panel is on.
        formPanel = fP;
        
        // Initializes this panels body panel reference
        smsBodyPanel = smsBP;
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // Adds all form components needed to add a new login account.
        addFormComponents();
        
        // First invisible label putting spacing between the form components and the button panel
        final JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(650, 30));
        spacingLabel1.setOpaque(false);
        spacingLabel1.setVisible(true);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;
        this.add(spacingLabel1, c);
        
        // resets the grid width for when adding other components
        c.gridwidth = 1;
        
        // Adds the button panel to contain all buttons needed for this panel.
        addButtonPanel();
    }
    
    /**
     * Adds all form text labels, form text input fields and info icons for each field needed for the add login
     * account form.
     */
    public void addFormComponents() {
        /**
         * Username label and text field and info label.
         */
        // Label to hold the login accounts username form field text.
        final JLabel usernameLabel = new JLabel("Username:");
        setLabelAttributes(usernameLabel);

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 0;
        this.add(usernameLabel, c);
        
        // The text field input for the login accounts username
        usernameField = new JTextField(15);
        usernameField.setInputVerifier(inputVerifier);
        usernameField.setName(usernameFieldName);
        usernameField.setFont(textFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 0;
        this.add(usernameField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts username field
        final JLabel info1Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info1Label);
        // The info image icon for the label
        info1Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info1Label.setToolTipText("<HTML> The <b> username </b>field cannot: "
                + "be empty, be unique or contain anything other than letters, numbers and underscores</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 0;
        this.add(info1Label, c);
        
        
        /**
         * Password label and text field and info label.
         */
        // Label to hold the login accounts password form field text.
        final JLabel passwordLabel = new JLabel("Password:");
        setLabelAttributes(passwordLabel);

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 1;
        this.add(passwordLabel, c);
        
        // The text field input for login accounts password
        passwordField = new JTextField(15);
        passwordField.setInputVerifier(inputVerifier);
        passwordField.setName(passwordFieldName);
        passwordField.setFont(textFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 1;
        this.add(passwordField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts password field
        final JLabel info2Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info2Label);
        // The info image icon for the label
        info2Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info2Label.setToolTipText("<HTML> The <b> password </b>field MUST contain atleast: "
                + "One upper and lower case letter, a number and be atleast 5 characters long.</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 1;
        this.add(info2Label, c);
        
        
        /**
         * Password Confirmation label and text field and info label.
         */
        // Label to hold the login accounts password confirmation form field text.
        final JLabel passwordConfirmationLabel = new JLabel("Password Confirmation:");
        setLabelAttributes(passwordConfirmationLabel);

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 2;
        this.add(passwordConfirmationLabel, c);
        
        // The text field input for login accounts password confirmation
        passwordConfirmationField = new JTextField(15);
        passwordConfirmationField.setInputVerifier(inputVerifier);
        passwordConfirmationField.setName(passwordConfirmationFieldName);
        passwordConfirmationField.setFont(textFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 2;
        this.add(passwordConfirmationField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts password confirmation field
        final JLabel info3Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info3Label);
        // The info image icon for the label
        info3Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info3Label.setToolTipText("<HTML> The <b> password confirmation </b>field cannot: "
                + "be empty, or be different from the password entered in the password field.</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 2;
        this.add(info3Label, c);
        
        
        /**
         * Security Question label and text field and info label.
         */
        // Label to hold the login accounts security question form field text.
        final JLabel securityQuestionLabel = new JLabel("Security Question:");
        setLabelAttributes(securityQuestionLabel);

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 3;
        this.add(securityQuestionLabel, c);
        
        // The text field input for login accounts security question
        securityQuestionField = new JTextField(15);
        securityQuestionField.setInputVerifier(inputVerifier);
        securityQuestionField.setName(securityQuestionFieldName);
        securityQuestionField.setFont(textFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 3;
        this.add(securityQuestionField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts security question field
        final JLabel info4Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info4Label);
        // The info image icon for the label
        info4Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info4Label.setToolTipText("<HTML> The <b> security question </b>field cannot: "
                + "be empty.</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 3;
        this.add(info4Label, c);
        
        
        /**
         * Security Question Answer label and text field and info label.
         */
        // Label to hold the login accounts security question answer form field text.
        final JLabel securityQuestionAnswerLabel = new JLabel("Security Question Answer:");
        setLabelAttributes(securityQuestionAnswerLabel);

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 4;
        this.add(securityQuestionAnswerLabel, c);
        
        // The text field input for login accounts security question answer
        securityQuestionAnswerField = new JTextField(15);
        securityQuestionAnswerField.setInputVerifier(inputVerifier);
        securityQuestionAnswerField.setName(securityQuestionAnswerFieldName);
        securityQuestionAnswerField.setFont(textFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 4;
        this.add(securityQuestionAnswerField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts username field
        final JLabel info5Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info5Label);
        // The info image icon for the label
        info5Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info5Label.setToolTipText("<HTML> The <b> security question answer </b>field cannot: "
                + "be empty.</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 4;
        this.add(info5Label, c);
        
        /**
         * Admin label and combo box and info label.
         */
        // Label to hold the login accounts security question answer form field text.
        final JLabel adminLabel = new JLabel("Administrator Status:");
        setLabelAttributes(adminLabel);

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 5;
        this.add(adminLabel, c);
        
        // The combo box for the login accounts admin status
        final String[] adminBoxOptions = {"No", "Yes"};
        adminComboBox = new JComboBox(adminBoxOptions);
        adminComboBox.setFont(textFont);
        adminComboBox.addActionListener(this);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 1;
        c.gridy = 5;
        this.add(adminComboBox, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts username field
        final JLabel info6Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info6Label);
        // The info image icon for the label
        info6Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info6Label.setToolTipText("<HTML> The <b> Administrator Status </b> of the login account.</HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 5;
        this.add(info6Label, c);
    }
    
    /**
     * Sets the standard attributes of the text labels for forms (avoiding repetition).
     * @param label The JLabel to set the standard attributes of
     */
    public void setLabelAttributes(JLabel label) {
        // Text is on the left, with label background being white and the label font defined also
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(labelFont);
        label.setBackground(Color.white);
        label.setPreferredSize(new Dimension(225, 50));
        label.setOpaque(true);
        label.setVisible(true);
    }
    
    /**
     * Sets the standard attributes of the info labels for forms (avoiding repetition).
     * @param label The info JLabel to set the standard attributes of
     */
    public void setInfoLabelAttributes(JLabel label) {
        label.setBackground(Color.white);
        label.setPreferredSize(new Dimension(50, 50));
        label.setOpaque(true);
        label.setVisible(true);
    }
    
    /**
     * Adds the button panel to contain the add login account, clear text fields 
     * and cancel adding of a login account buttons to this panel.
     */
    public void addButtonPanel() {
       
        // Creates and initializes the panel to hold all of the buttons for this panel
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(650, 50));
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.white);
        
        // The button to create a login account in the login account database table
        createButton = new JButton("Create Login Account");
        createButton.setPreferredSize(new Dimension(180, 45));
        createButton.addActionListener(this);
        createButton.setToolTipText("<html> Click this button to <b> add </b> the Login Account. </html>");
        createButton.setIcon(new ImageIcon("images/icons/user_add.png"));
        
        // Adds the add login account button to the button panel
        buttonPanel.add(createButton);
        
        
        // The button to clear the form fields
        clearFormButton = new JButton("Clear Form");
        clearFormButton.setPreferredSize(new Dimension(150, 45));
        clearFormButton.addActionListener(this);
        clearFormButton.setToolTipText("<html> Click this button to <b> clear </b> the form fields. </html>");
        clearFormButton.setIcon(new ImageIcon("images/icons/delete.png"));
        
        // Adds the clear form button to the button panel
        buttonPanel.add(clearFormButton);
        
        
        // The button to cancel the adding of a new login account
        cancelFormButton = new JButton("Cancel");
        cancelFormButton.setPreferredSize(new Dimension(150, 45));
        cancelFormButton.addActionListener(this);
        cancelFormButton.setToolTipText("<html> Click this button to <b> cancel </b> the adding of a new Login Account. </html>");
        cancelFormButton.setIcon(new ImageIcon("images/icons/cancel.png"));
        
        // Adds the cancel form button to the button panel
        buttonPanel.add(cancelFormButton);
        
        
        
        // The coordinates for where to add the button panel to the layout
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 3;
        this.add(buttonPanel, c);
    }
    
    /**
     * Salts and hashes the password and collects all input in the form fields and creates the login account in the
     * database.
     */
    public void createLoginAccount() {
        
        MessageDigest messageDigest = null;
        
        try {
            // The message Digest to salt and generate the hash set for the password
            messageDigest = MessageDigest.getInstance("SHA-512");
            
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing password for new login account");
        }

        if (messageDigest != null) {
            // Creates a ranndom object to generate a random salt byte array
            final Random rand = new Random();
            final byte[] salt = new byte[12];
            rand.nextBytes(salt);
            
            // Adds the salt to the message digest
            messageDigest.update(salt);

            // Updates the message digest using the password string convereted to a byte array
            messageDigest.update(passwordField.getText().getBytes());

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

            //convert the salt byte array into a hexidecimal string format
            final StringBuffer sbSalt = new StringBuffer();
            for (int i = 0; i < salt.length; i++) {
             sbSalt.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Resets the message digest for future hashes
            messageDigest.reset();
            
            
            // Adds the salt to the message digest
            messageDigest.update(salt);

            // Updates the message digest using the security answer string convereted to a byte array
            messageDigest.update(securityQuestionAnswerField.getText().getBytes());
            
            // Generates the hashed security question answer data
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
            
            
            // Boolean to hold the admin status
            boolean adminStatus;
            
            // Checks the users choise as to whether they are an admin
            if (adminComboBox.getSelectedItem().equals("Yes")) {
                adminStatus = true;
            } else {
                adminStatus = false;
            }
                   
            // Creates a login account using the form values provided and stores it in the database
            final LoginAccount newLoginAccount = new LoginAccount(usernameField.getText(), sbPassword.toString(), 
                    sbSalt.toString(), adminStatus, securityQuestionField.getText(), sbSecurityQuestionAnswer.toString());
        
            // Creates the new login account in the database
            loginAccountController.create(newLoginAccount);
            
            // Re-loads the create, edit and delete login accounts panel
            smsBodyPanel.addViewCEDLoginAccountsPanel();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the source of the button press was the create login account button
        if (e.getSource() == createButton) {
            
            // boolean used as a flag if any form field is left empty or contains an invalid input
            boolean invalidField = true;
            
            // Verifies all input text fields on the form
            inputVerifier.verify(usernameField);
            inputVerifier.verify(passwordField);
            inputVerifier.verify(passwordConfirmationField);
            inputVerifier.verify(securityQuestionField);
            inputVerifier.verify(securityQuestionAnswerField);
            
            // if any of the textfields backgrounds are not white flag as a field being invalid.
            if (usernameField.getBackground() == Color.white && passwordField.getBackground() == Color.white 
                    && passwordConfirmationField.getBackground() == Color.white && securityQuestionField.getBackground() == Color.white
                        && securityQuestionAnswerField.getBackground() == Color.white) {
                invalidField = false;
            }
            
            // If any form fields were invalid display an error message
            if (invalidField == true) {
                JOptionPane.showMessageDialog(null,
                                "A field(s) has been left empty or contains an invalid entry\n"
                                        + "Invalid field(s) highlighted in yellow\n"
                                        + "Please complete the form correctly.\n"
                                        + "(Tip: Hover over the information icon next to the invalid field to view what types of entrys are invaid)",
                                "Error Invalid Field(s)",
                                JOptionPane.ERROR_MESSAGE);
            } else {
                // check if username is duplicate
                final boolean usernameDuplicate = loginAccountController.checkDuplicateUsername(usernameField.getText());
                
                if (usernameDuplicate) {
                    JOptionPane.showMessageDialog(null,
                                "The username entered is a duplicate of an existing username, please enter a unique username.",
                                "Error Duplicate Username",
                                JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!passwordField.getText().equals(passwordConfirmationField.getText())) {
                        JOptionPane.showMessageDialog(null,
                                "The passwords entered do not match!",
                                "Error Passwords do not match!",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        
                        // Calls the method to create a login account
                        createLoginAccount();
                    }
                }
            }
        // If the source of the button press was the clear form button
        } else if (e.getSource() == clearFormButton) {
            
            // Sets all form fields to the default values
            usernameField.setText("");
            passwordField.setText("");
            passwordConfirmationField.setText("");
            securityQuestionField.setText("");
            securityQuestionAnswerField.setText("");
            adminComboBox.setSelectedIndex(0);
            
            // Seta all the form text fields back to the default white colour
            usernameField.setBackground(Color.white);
            passwordField.setBackground(Color.white);
            passwordConfirmationField.setBackground(Color.white);
            securityQuestionField.setBackground(Color.white);
            securityQuestionAnswerField.setBackground(Color.white);
            
        // If the source of the button press was the cancel form button
        } else if (e.getSource() == cancelFormButton) {
            
            // Removes this panel from the form panel
            formPanel.remove(this);
            formPanel.updateUI();
        } else if (e.getSource() == adminComboBox) {
            if (adminComboBox.getSelectedItem().equals("Yes")) {
                // Calls static method to load admin authentication dialog
                final boolean adminAuthentication = LoginDialog.adminLoginDialog("Administrator permission required to create another Administrator. Please Log In...");
                    
                // If admin authentication failed change the selection back to "No"
                if (adminAuthentication == false) {
                    adminComboBox.setSelectedIndex(0);
                }
            }
        }
    }
}
