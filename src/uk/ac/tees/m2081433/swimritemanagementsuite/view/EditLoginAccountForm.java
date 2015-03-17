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
 * This class adds components to itself so that a user can edit an existing login account in the login account database.
 */
public class EditLoginAccountForm extends JPanel implements ActionListener {
    
    /**
     * The form panel reference from when canceling the adding of a student.
     */
    JPanel formPanel;
    
    /**
     * The SMS Body Panel reference for when swapping panels on the body panel.
     */
    SMSBodyPanel smsBodyPanel;
    
    /**
     * The Login Account to perform editing upon.
     */
    LoginAccount loginAccount;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * The input verifier for the edit login account form (validates text field inputs).
     */
    LoginAccountFormInputVerifier inputVerifier = new LoginAccountFormInputVerifier();
    
    /**
     * The Login Account Controller to interact with the Login Account database table.
     */
    LoginAccountController loginAccountController = new LoginAccountController();
    
    /**
     * The panel that changes depending on what the user wants to edit about the login account.
     */
    JPanel dynamicEditPanel;
    
    
    
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
     * The panel to hold all buttons needed for this panel.
     */
    JPanel buttonPanel;
    
    /**
     * The button to change the login accounts password.
     */
    JButton changePasswordButton;
    
    /**
     * The button to change the login accounts Security Question & Answer.
     */
    JButton changeSecurityQ;
    
    /**
     * The button to cancel the editing of the login account.
     */
    JButton cancelButton;
    
    /**
     * The button to update the login accounts password using the input specified in the form.
     */
    JButton updateChangePasswordButton;
    
    /**
     * The button to cancel the editing of the login accounts password.
     */
    JButton cancelChangePasswordButton;
    
    /**
     * The button to update the login accounts security question & answer using the input specified in the form.
     */
    JButton updateChangeSecurityQButton;
    
    /**
     * The button to cancel the editing of the login accounts security question & answer.
     */
    JButton cancelChangeSecurityQButton;
    
    
    
    /**
     * Adds form text labels, text field input and info icons for each field as well as buttons to update and 
     * cancel the editing of a login account.
     * @param fP The reference to the panel this panel is on 
     * @param smsBP The reference to the body panel that contains the pan
     * @param lA The login account to load onto this panel (and to allow the editing of)
     */
    public EditLoginAccountForm(JPanel fP, SMSBodyPanel smsBP, LoginAccount lA) {
        // Creates and initializes the panel add a new login account
        this.setPreferredSize(new Dimension(650, 450));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        
        // Initializes the reference for the panel that this panel is on
        formPanel = fP;
        
        // Initializes this panels body panel reference
        smsBodyPanel = smsBP;
        
        // Initializes the login account to be editied using this panel
        loginAccount = lA;
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        /**
         * Adds the username of the login account to the top of this panel.
         */
        // Adds the username panel that displays the username of the login account being edited.
        final JPanel usernamePanel = new JPanel();
        usernamePanel.setPreferredSize(new Dimension(650, 50));
        usernamePanel.setVisible(true);
        usernamePanel.setBackground(Color.white);
        
        // Label to hold the login accounts username text.
        final JLabel usernameLabelText = new JLabel(loginAccount.getUsername());
        usernameLabelText.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabelText.setPreferredSize(new Dimension(650, 50));
        usernameLabelText.setFont(new Font("Arial", Font.PLAIN, 25));
        usernameLabelText.setBackground(Color.white);
        usernameLabelText.setOpaque(true);
        usernameLabelText.setVisible(true);
        usernamePanel.add(usernameLabelText, c);
        
        // The coordinates for where to add the username panel component to this layout.
        c.gridx = 0;
        c.gridy = 0;
        this.add(usernamePanel, c);
        
        
        // Adds the dynamic editing panel which will hold componenets used to edit different aspects of the login account
        dynamicEditPanel = new JPanel();
        dynamicEditPanel.setPreferredSize(new Dimension(650, 345));
        dynamicEditPanel.setVisible(true);
        dynamicEditPanel.setBackground(Color.white);
        dynamicEditPanel.setLayout(new GridBagLayout());
        
        // The coordinates for where to add the username panel component to this layout.
        c.gridx = 0;
        c.gridy = 1;
        this.add(dynamicEditPanel, c);
        
        
        // Creates and initializes the panel to hold all of the buttons for this panel
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(650, 55));
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.white);
        
        // The coordinates for where to add the button panel to the layout
        c.gridx = 0;
        c.gridy = 2;
        this.add(buttonPanel, c);
        
        // Adds the first layer of buttons to the button panel
        addButtonsToButtonPanel();
    }
    
    /**
     * Adds the buttons to change the password, change the security question & answer and to cancel editing.
     */
    public void addButtonsToButtonPanel() {
        // The button to change the login accounts password
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setPreferredSize(new Dimension(150, 45));
        changePasswordButton.addActionListener(this);
        changePasswordButton.setToolTipText("<html> Click this button to change the login accounts <b> password</b>. </html>");
        changePasswordButton.setIcon(new ImageIcon("images/icons/bullet_edit.png"));
        
        // Adds the change login accounts password to the button panel
        buttonPanel.add(changePasswordButton);
        
        
        // The button to change the login accounts security question and answer
        changeSecurityQ = new JButton("Change Security Question & Answer");
        changeSecurityQ.setPreferredSize(new Dimension(250, 45));
        changeSecurityQ.addActionListener(this);
        changeSecurityQ.setToolTipText("<html> Click this button to change the login accounts <b> security question & answer</b>. </html>");
        changeSecurityQ.setIcon(new ImageIcon("images/icons/bullet_edit.png"));
        
        // Adds the change login accounts security question and answer to the button panel
        buttonPanel.add(changeSecurityQ);
        
        
        // The button to change the login accounts security question and answer
        cancelButton = new JButton("Cancel editting");
        cancelButton.setPreferredSize(new Dimension(150, 45));
        cancelButton.addActionListener(this);
        cancelButton.setToolTipText("<html> Click this button to <b> cancel</b> the editing of the login account. </html>");
        cancelButton.setIcon(new ImageIcon("images/icons/cancel.png"));
        
        // Adds the change login accounts security question and answer to the button panel
        buttonPanel.add(cancelButton);
        
        // Updates the UI of the button panel
        buttonPanel.updateUI();
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
     * Adds the change password form to the dynamic form panel.
     */
    public void addChangePasswordForm() {
        // The panel to hold all form components to change the login accounts password
        final JPanel changePasswordForm = new JPanel();
        changePasswordForm.setPreferredSize(new Dimension(650, 345));
        changePasswordForm.setVisible(true);
        changePasswordForm.setBackground(Color.white);
        changePasswordForm.setLayout(new GridBagLayout());
        
        /**
         * Password label and text field and info label.
         */
        // Label to hold the login accounts new password form field text.
        final JLabel passwordLabel = new JLabel("New Password:");
        setLabelAttributes(passwordLabel);
        passwordLabel.setPreferredSize(new Dimension(200, 50));

        // The coordinates for where to add this component to the edit login account password form layout.
        c.gridx = 0;
        c.gridy = 0;
        changePasswordForm.add(passwordLabel, c);
        
        // The text field input for login accounts new password
        passwordField = new JTextField(15);
        passwordField.setInputVerifier(inputVerifier);
        passwordField.setName(passwordFieldName);
        passwordField.setFont(textFont);
        
        // The coordinates for where to add this component to the edit login account password form layout.
        c.gridx = 1;
        c.gridy = 0;
        changePasswordForm.add(passwordField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts password field
        final JLabel info1Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info1Label);
        // The info image icon for the label
        info1Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info1Label.setToolTipText("<HTML> The <b> new password </b>field MUST contain atleast: "
                + "One upper and lower case letter, a number and be atleast 5 characters long.</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 0;
        changePasswordForm.add(info1Label, c);
        
        
        /**
         * Password Confirmation label and text field and info label.
         */
        // Label to hold the login accounts password confirmation form field text.
        final JLabel passwordConfirmationLabel = new JLabel("Password Confirmation:");
        setLabelAttributes(passwordConfirmationLabel);
        passwordConfirmationLabel.setPreferredSize(new Dimension(200, 50));
        
        // The coordinates for where to add this component to the edit login account password form layout.
        c.gridx = 0;
        c.gridy = 1;
        changePasswordForm.add(passwordConfirmationLabel, c);
        
        // The text field input for login accounts password confirmation
        passwordConfirmationField = new JTextField(15);
        passwordConfirmationField.setInputVerifier(inputVerifier);
        passwordConfirmationField.setName(passwordConfirmationFieldName);
        passwordConfirmationField.setFont(textFont);
        
        // The coordinates for where to add this component to the edit login account password form layout.
        c.gridx = 1;
        c.gridy = 1;
        changePasswordForm.add(passwordConfirmationField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts password confirmation field
        final JLabel info2Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info2Label);
        // The info image icon for the label
        info2Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info2Label.setToolTipText("<HTML> The <b> password confirmation </b>field cannot: "
                + "be empty, or be different from the password entered in the password field.</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 1;
        changePasswordForm.add(info2Label, c);
        
        // Adds the change password form to the dynamic edit panel and updates the UI.
        dynamicEditPanel.add(changePasswordForm);
        dynamicEditPanel.updateUI();
    }
    
    /**
     * Removes all buttons from the button panel and adds all the buttons needed to change the login accounts
     * password.
     */
    public void addChangePasswordFormButtons() {
        // Removes all buttons currently on the button panel and updates the UI
        buttonPanel.removeAll();
        buttonPanel.updateUI();
        
        // The button to update the new password of the login account
        updateChangePasswordButton = new JButton("Update New Password");
        updateChangePasswordButton.setPreferredSize(new Dimension(200, 45));
        updateChangePasswordButton.addActionListener(this);
        updateChangePasswordButton.setToolTipText("<html> Click this button to update the login account with their <b>new password</b>. </html>");
        updateChangePasswordButton.setIcon(new ImageIcon("images/icons/accept.png"));
        
        // Adds the update change login accounts password to the button panel
        buttonPanel.add(updateChangePasswordButton);
        
        
        // The button to cancel the updating/changing of the login accounts password
        cancelChangePasswordButton = new JButton("Cancel");
        cancelChangePasswordButton.setPreferredSize(new Dimension(150, 45));
        cancelChangePasswordButton.addActionListener(this);
        cancelChangePasswordButton.setToolTipText("<html> Click this button to <b> cancel</b> the updating of the login accounts new password. </html>");
        cancelChangePasswordButton.setIcon(new ImageIcon("images/icons/cancel.png"));
        
        // Adds the button to cancel the updating/changing of the login accounts password to the button panel
        buttonPanel.add(cancelChangePasswordButton);
        
        // Updates the button panels ui with the new buttons
        buttonPanel.updateUI();
    }
    
    /**
     * Adds the change security question form to the dynamic form panel.
     */
    public void addChangeSecurityQuestionForm() {
        // The panel to hold all form components to change the login accounts security question
        final JPanel changeSecurityQuestionForm = new JPanel();
        changeSecurityQuestionForm.setPreferredSize(new Dimension(650, 345));
        changeSecurityQuestionForm.setVisible(true);
        changeSecurityQuestionForm.setBackground(Color.white);
        changeSecurityQuestionForm.setLayout(new GridBagLayout());
        
        /**
         * Security Question label and text field and info label.
         */
        // Label to hold the login accounts security question form field text.
        final JLabel securityQuestionLabel = new JLabel("Security Question:");
        setLabelAttributes(securityQuestionLabel);
        securityQuestionLabel.setPreferredSize(new Dimension(220, 50));

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 0;
        changeSecurityQuestionForm.add(securityQuestionLabel, c);
        
        // The text field input for login accounts security question
        securityQuestionField = new JTextField(15);
        securityQuestionField.setInputVerifier(inputVerifier);
        securityQuestionField.setName(securityQuestionFieldName);
        securityQuestionField.setFont(textFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 0;
        changeSecurityQuestionForm.add(securityQuestionField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts security question field
        final JLabel info1Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info1Label);
        // The info image icon for the label
        info1Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info1Label.setToolTipText("<HTML> The <b> security question </b>field cannot: "
                + "be empty.</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 0;
        changeSecurityQuestionForm.add(info1Label, c);
        
        
        /**
         * Security Question Answer label and text field and info label.
         */
        // Label to hold the login accounts security question answer form field text.
        final JLabel securityQuestionAnswerLabel = new JLabel("Security Question Answer:");
        setLabelAttributes(securityQuestionAnswerLabel);
        securityQuestionAnswerLabel.setPreferredSize(new Dimension(220, 50));

        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 0;
        c.gridy = 1;
        changeSecurityQuestionForm.add(securityQuestionAnswerLabel, c);
        
        // The text field input for login accounts security question answer
        securityQuestionAnswerField = new JTextField(15);
        securityQuestionAnswerField.setInputVerifier(inputVerifier);
        securityQuestionAnswerField.setName(securityQuestionAnswerFieldName);
        securityQuestionAnswerField.setFont(textFont);
        
        // The coordinates for where to add this component to the add login account form layout.
        c.gridx = 1;
        c.gridy = 1;
        changeSecurityQuestionForm.add(securityQuestionAnswerField, c);
        
        // Label to hold the information tooltip and tooltip icon for the login accounts username field
        final JLabel info2Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info2Label);
        // The info image icon for the label
        info2Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info2Label.setToolTipText("<HTML> The <b> security question answer </b>field cannot: "
                + "be empty.</b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 1;
        changeSecurityQuestionForm.add(info2Label, c);
        
        // Adds the change security question form to the dynamic edit panel and updates the UI.
        dynamicEditPanel.add(changeSecurityQuestionForm);
        dynamicEditPanel.updateUI();
    }
    
    /**
     * Removes all buttons from the button panel and adds all the buttons needed to change the login accounts
     * security question.
     */
    public void addChangeSecurityQuestionFormButtons() {
        // Removes all buttons currently on the button panel and updates the UI
        buttonPanel.removeAll();
        buttonPanel.updateUI();
        
        // The button to update the new security question & answer of the login account
        updateChangeSecurityQButton = new JButton("Update New Security Question & Answer");
        updateChangeSecurityQButton.setPreferredSize(new Dimension(290, 45));
        updateChangeSecurityQButton.addActionListener(this);
        updateChangeSecurityQButton.setToolTipText("<html> Click this button to update the login account with their <b>new security question & answer</b>. </html>");
        updateChangeSecurityQButton.setIcon(new ImageIcon("images/icons/accept.png"));
        
        // Adds the update change login accounts security question & answer to the button panel
        buttonPanel.add(updateChangeSecurityQButton);
        
        
        // The button to cancel the updating/changing of the login accounts security question & answer
        cancelChangeSecurityQButton = new JButton("Cancel");
        cancelChangeSecurityQButton.setPreferredSize(new Dimension(150, 45));
        cancelChangeSecurityQButton.addActionListener(this);
        cancelChangeSecurityQButton.setToolTipText("<html> Click this button to <b> cancel</b> the updating of the login accounts new security question & answer. </html>");
        cancelChangeSecurityQButton.setIcon(new ImageIcon("images/icons/cancel.png"));
        
        // Adds the button to cancel the updating/changing of the login accounts security question & answer to the button panel
        buttonPanel.add(cancelChangeSecurityQButton);
        
        // Updates the button panels ui with the new buttons
        buttonPanel.updateUI();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the source of the button press was the change password button
        if (e.getSource() == changePasswordButton) {
            
            // Adds the change password form to the dynamic form panel
            addChangePasswordForm();
            
            // Adds the change password form buttons to the button panel
            addChangePasswordFormButtons();
            
        // If the source of the button press was the change security question button
        } else if (e.getSource() == changeSecurityQ) {
            
            // Adds the change security question form to the dynamic form panel
            addChangeSecurityQuestionForm();
            
            // Adds the change security question form buttons to the button panel
            addChangeSecurityQuestionFormButtons();
            
        // If the souce of the button press was the cancel button
        } else if (e.getSource() == cancelButton) {
            // Removes this panel from the form panel
            formPanel.remove(this);
            formPanel.updateUI();
        
        // If the source of the button press was the update change password button     
        } else if (e.getSource() == updateChangePasswordButton) {
            
            // boolean used as a flag if any form fields are left empty or contains an invalid input
            boolean invalidField = true;
            
            // Verifies the input form fields
            inputVerifier.verify(passwordField);
            inputVerifier.verify(passwordConfirmationField);
            
            // if any of the textfields backgrounds are not white flag as a field being invalid.
            if (passwordField.getBackground() == Color.white && passwordConfirmationField.getBackground() == Color.white) {
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
                if (!passwordField.getText().equals(passwordConfirmationField.getText())) {
                        JOptionPane.showMessageDialog(null,
                                "The passwords entered do not match!",
                                "Error Passwords do not match!",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Creates the message digest object as null
                        MessageDigest messageDigest = null;
        
                        try {
                            // The message Digest to salt and generate the hash set for the password
                            messageDigest = MessageDigest.getInstance("SHA-512");

                        } catch (NoSuchAlgorithmException exception) {
                            System.out.println("Error hashing password for change password");
                        }
                        
                        // Checks that the message digest was initialized properly
                        if (messageDigest != null) {
                            // Adds the login accounts salt to the message digest
                            messageDigest.update(hexStringToByteArray(loginAccount.getSalt()));

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

                            // Resets the message digest for future hashes
                            messageDigest.reset();

                            // Sets the login accounts new password
                            loginAccount.setPassword(sbPassword.toString());
                            
                            // Updates the login account in the database
                            loginAccountController.update(loginAccount);

                            // Removes the change password form from the dynamic edit panel and updates the UI.
                            dynamicEditPanel.removeAll();
                            dynamicEditPanel.updateUI();

                            // Removes the buttons to change the password and puts the other back on.
                            buttonPanel.removeAll();
                            addButtonsToButtonPanel(); 
                            
                            // Confirmation message that the password was updated successfully
                            JOptionPane.showMessageDialog(null,
                                "The password has been successfully updated!",
                                "Password Updated Successfully!",
                                JOptionPane.PLAIN_MESSAGE);
                        }
                    }
            }
        // If the source of the button press was the cancel change password button    
        } else if (e.getSource() == cancelChangePasswordButton) {
            
            // Removes the change password form from the dynamic edit panel and updates the UI.
            dynamicEditPanel.removeAll();
            dynamicEditPanel.updateUI();
            
            // Removes the buttons to change the password and puts the other back on.
            buttonPanel.removeAll();
            addButtonsToButtonPanel();  
            
        // If the source of the button press was the update change security question button
        } else if (e.getSource() == updateChangeSecurityQButton) {
            
            // boolean used as a flag if any form fields are left empty or contains an invalid input
            boolean invalidField = true;
            
            // Verifies the input form fields
            inputVerifier.verify(securityQuestionField);
            inputVerifier.verify(securityQuestionAnswerField);
            
            // if any of the textfields backgrounds are not white flag as a field being invalid.
            if (securityQuestionField.getBackground() == Color.white && securityQuestionAnswerField.getBackground() == Color.white) {
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
                
                MessageDigest messageDigest = null;
        
                try {
                    // The message Digest to salt and generate the hash set for the password
                    messageDigest = MessageDigest.getInstance("SHA-512");

                } catch (NoSuchAlgorithmException exception) {
                    System.out.println("Error hashing password for edit login account security q");
                }

                if (messageDigest != null) {
                    // Adds the salt to the message digest
                    messageDigest.update(hexStringToByteArray(loginAccount.getSalt()));

                    // Updates the message digest using the security answer string converted to a byte array
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

                    // Sets the login accounts new security question
                    loginAccount.setSecurityQuestion(securityQuestionField.getText());

                    // Sets the login accounts new security question answer
                    loginAccount.setSecurityQuestionAnswer(sbSecurityQuestionAnswer.toString());

                    // Updates the login account in the database
                    loginAccountController.update(loginAccount);

                    // Removes the change security question & answer form from the dynamic edit panel and updates the UI.
                    dynamicEditPanel.removeAll();
                    dynamicEditPanel.updateUI();

                    // Removes the buttons to change the security question and answer and puts the other back on.
                    buttonPanel.removeAll();
                    addButtonsToButtonPanel();

                    // Confirmation message that the security question & answer was updated successfully
                    JOptionPane.showMessageDialog(null,
                            "The security question & answer has been successfully updated!",
                            "Security Question & Answer Updated Successfully!",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        // If the source of the button press was the cancel change security question button
        } else if (e.getSource() == cancelChangeSecurityQButton) {
            
            // Removes the change security question form from the dynamic edit panel and updates the UI.
            dynamicEditPanel.removeAll();
            dynamicEditPanel.updateUI();
            
            // Removes the buttons to change the security question and puts the other back on.
            buttonPanel.removeAll();
            addButtonsToButtonPanel();  
        }
    }
    
    /**
     * Method to convert a hexadecimal string (the salt) back into a byte array used to hash passwords 
     * and security question answers.
     * @param  s The hex string to convert back into a byte array
     * @return Byte Array
     * source: http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
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
