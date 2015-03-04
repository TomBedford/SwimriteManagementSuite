package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.SRFormInputVerifier;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.StudentRecordController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;

/**
 * This panel displays a form allowing the user to create a new Student Record.
 */
public class AddSRPanel extends JPanel implements ActionListener {
    
    /**
     * The Student Record controller for when inserting records into the Student Record table.
     */
    StudentRecordController studentRecordController;
    
    /**
     * The input verifier for the student record form (validates text field inputs).
     */
    SRFormInputVerifier inputVerifier;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    
    
    /**
     * The Student Name field text field name needed for referencing in the input verifier.
     */
    public static String studentNameFieldName = "Student Name Field";
    
    /**
     * The Students day of their date of birth field text field name needed for referencing in the input verifier.
     */
    public static String dobDayFieldName = "Date of Birth Day Field";
    
    /**
     * The Students month of their date of birth field text field name needed for referencing in the input verifier.
     */
    public static String dobMonthFieldName = "Date of Birth Month Field";
    
    /**
     * The Students year of their date of birth field text field name needed for referencing in the input verifier.
     */
    public static String dobYearFieldName = "Date of Birth Year Field";
    
    /**
     * The Students telephone number field text field name needed for referencing in the input verifier.
     */
    public static String telephoneNoFieldName = "Telephone No. Field";
    
    /**
     * The Students 1st line of their address field text field name needed for referencing in the input verifier.
     */
    public static String addressLine1FieldName = "Address Line 1 Field";
    
    /**
     * The Students 2nd line of their address field text field name needed for referencing in the input verifier.
     */
    public static String addressLine2FieldName = "Address Line 2 Field";
    
    /**
     * The Students city of their address field text field name needed for referencing in the input verifier.
     */
    public static String addressCityFieldName = "Address City Field";
    
    /**
     * The Students county of their address field text field name needed for referencing in the input verifier.
     */
    public static String addressCountyFieldName = "Address County Field";
    
    /**
     * The Students postcode of their address field text field name needed for referencing in the input verifier.
     */
    public static String addressPostcodeFieldName = "Address Postcode Field";
    
    /**
     * The Students has illnesses or disabilities field text field name needed for referencing in the input verifier.
     */
    public static String hasIllnessFieldName = "Any Illnesses or Disabilities Field";
    
    /**
     * The Students parents name field text field name needed for referencing in the input verifier.
     */
    public static String parentNameFieldName = "Parent Name Field";
    
    
    
    /**
     * The text field input for the students name.
     */
    JTextField studentNameField;
    
    /**
     * The text field input for the students day of their date of birth.
     */
    JTextField studentDOBDayField;
    
    /**
     * The text field input for the students month of their date of birth.
     */
    JTextField studentDOBMonthField;
    
    /**
     * The text field input for the students year of their date of birth.
     */
    JTextField studentDOBYearField;
    
    /**
     * The text field input for the students telephone number.
     */
    JTextField studentTelephoneNoField;
    
    /**
     * The text field input for the students 1st line of their address.
     */
    JTextField addressLine1Field;
    
    /**
     * The text field input for the students 2nd line of their address.
     */
    JTextField addressLine2Field;
    
    /**
     * The text field input for the students city in their address.
     */
    JTextField addressCityField;
    
    /**
     * The text field input for the students county in their address.
     */
    JTextField addressCountyField;
     
    /**
     * The text field input for the students postcode in their address.
     */
    JTextField addressPostcodeField;
    
    /**
     * The text field input for the student whether they have any illnesses or disabilities.
     */
    JTextField hasIllnessField;
    
    /**
     * The text field input for the students parents name.
     */
    JTextField parentNameField;
    
    /**
     * The combo box used to display the different possible swimming levels at Swimrite Leisure.
     */
    JComboBox swimmingLevelList;
    
    
    
    /**
     * The font (defining font style, font type and font size) for all form text labels.
     */
    Font labelFont = new Font("Arial", Font.PLAIN, 18);
    
    /**
     * The font (defining font style, font type and font size) for all form text field inputs.
     */
    Font textFont = new Font("Arial", Font.PLAIN, 17);
    
    
    /**
     * The button used to submit a student record to the database.
     */
    JButton submitButton;
     
    /**
     * The button used to clear all form fields.
     */
    JButton clearButton;
    
    
    
    /**
     * Constructor defining panel attributes and loads all form components to go on the panel.
     */
    public AddSRPanel() {
        // Initialises the student record controller needed to add a student record to the db.
        studentRecordController = new StudentRecordController();
        
        // The input verifier for each text field input in this form.
        inputVerifier = new SRFormInputVerifier();
        
        // sets the AddSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(1375, 565));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // Adds spacing components to the layout (purely for aesthetic purposes)
        addLayoutSpacing();
        
        // Loads all form components for the create student record form
        loadSRForm();
    }
    
    /**
     * Adds invisible labels for spacing out other components aesthetically in the gridbag layout.
     */
    public void addLayoutSpacing() {
        // First invisible label (top left) pushing the form to the right
        final JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(450, 50));
        spacingLabel1.setOpaque(false);
        spacingLabel1.setVisible(true);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 0;
        this.add(spacingLabel1, c);
        
        // Second invisible label (bottom right, inbetween the form and the buttons) pushing buttons to right corner
        final JLabel spacingLabel2 = new JLabel();
        spacingLabel2.setPreferredSize(new Dimension(75, 50));
        spacingLabel2.setOpaque(false);
        spacingLabel2.setVisible(true);

        // The coordinates for where to add this component to the layout
        c.gridx = 3;
        c.gridy = 10;
        this.add(spacingLabel2, c);
    }
    
    /**
     * Loads each individual form component onto the label by calling each of the load form component methods.
     */
    public void loadSRForm() {
        // Adds the student name label and text field to the layout
        loadStudentNameRow();
        
        // Adds the student DOB label and text fields to the layout
        loadStudentDOBRow();
        
        // Adds the students telephone number label and text field to the layout
        loadStudentTelephoneNoRow();
        
        // Adds ALL the student address labels and text fields to the layout
        loadStudentAddressRow();
        
        // Adds whether the student has any illnesses or disabilites label and text field to the layout
        loadHasIllnessRow();
        
        // Adds the students parents name label and text field to the layout
        loadParentNameRow();
        
        // Adds the students swimming level label and text field to the layout
        loadSwimmingLevelRow();
        
        // Adds the submit and clear buttons to the layout
        loadButtons();
    }
    
    /**
     * Adds Student Name field label and text field input to the layout of this panel.
     */
    public void loadStudentNameRow() {
        // Label to hold the student name form field text.
        final JLabel studentNameLabel = new JLabel("Student Name:");
        setLabelAttributes(studentNameLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 0;
        this.add(studentNameLabel, c);
        
        // The text field input for the students name
        studentNameField = new JTextField(15);
        studentNameField.setInputVerifier(inputVerifier);
        studentNameField.setName(studentNameFieldName);
        studentNameField.setFont(textFont);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 0;
        this.add(studentNameField, c);
        
        // Label to hold the information tooltip and tooltip icon for the student name form field
        final JLabel info1Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info1Label);
        // The info image icon for the label
        info1Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info1Label.setToolTipText("<HTML> The <b> Student Name </b> field cannot: "
                + "be empty, or contain anything other than letters and spaces. </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 0;
        this.add(info1Label, c);
    }

    /**
     * Adds Students DOB field labels and text field inputs to the layout of this panel.
     */
    public void loadStudentDOBRow() {
        // Label to hold the student date of birth form field text
        final JLabel studentDOBLabel = new JLabel("Date of Birth (DOB):");
        setLabelAttributes(studentDOBLabel);

        // The coordinates for where to add this component to the layout
        c.gridx = 1;
        c.gridy = 1;
        this.add(studentDOBLabel, c);
        
        // Label to hold all the fields for the DOB
        final JPanel dobFieldHolderLabel = new JPanel();
        dobFieldHolderLabel.setPreferredSize(new Dimension(200, 50));
        dobFieldHolderLabel.setBackground(Color.white);
        dobFieldHolderLabel.setOpaque(true);
        dobFieldHolderLabel.setVisible(true);
        
        // The text field input for the students day of their date of birth
        studentDOBDayField = new JTextField(2);
        studentDOBDayField.setInputVerifier(inputVerifier);
        studentDOBDayField.setName(dobDayFieldName);
        studentDOBDayField.setFont(textFont);
        dobFieldHolderLabel.add(studentDOBDayField);
        
        // Label to hold a '/' SLASH inbetween the day and month of the students date of birth.
        final JLabel firstSlashLabel = new JLabel("<html><span style='font-size:16px'>/</span></html>");
        firstSlashLabel.setPreferredSize(new Dimension(15, 50));
        firstSlashLabel.setBackground(Color.white);
        firstSlashLabel.setOpaque(true);
        firstSlashLabel.setVisible(true);
        dobFieldHolderLabel.add(firstSlashLabel);
        
        // The text field input for the students month of their date of birth
        studentDOBMonthField = new JTextField(2);
        studentDOBMonthField.setInputVerifier(inputVerifier);
        studentDOBMonthField.setName(dobMonthFieldName);
        studentDOBMonthField.setFont(textFont);
        dobFieldHolderLabel.add(studentDOBMonthField);
        
        // Label to hold a '/' SLASH inbetween the month and year of the students date of birth.
        final JLabel secondSlashLabel = new JLabel("<html><span style='font-size:16px'>/</span></html>");
        secondSlashLabel.setPreferredSize(new Dimension(15, 50));
        secondSlashLabel.setBackground(Color.white);
        secondSlashLabel.setOpaque(true);
        secondSlashLabel.setVisible(true);
        dobFieldHolderLabel.add(secondSlashLabel);
        
        // The text field input for the students year of their date of birth
        studentDOBYearField = new JTextField(3);
        studentDOBYearField.setInputVerifier(inputVerifier);
        studentDOBYearField.setName(dobYearFieldName);
        studentDOBYearField.setFont(textFont);
        dobFieldHolderLabel.add(studentDOBYearField);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 1;
        this.add(dobFieldHolderLabel, c);
        
        // Label to hold the information tooltip and tooltip icon for the student date of birth form fields
        final JLabel info2Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info2Label);
        // The info image icon for the label
        info2Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info2Label.setToolTipText("<HTML> The <b> Student DOB </b>field cannot: "
                + "be empty, or contain anything other than valid numbers for day, month, year. <b> (Format: DD/MM/YYYY) </b> </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 1;
        this.add(info2Label, c);
    }

    /**
     * Adds Students telephone number field label and text field input to the layout of this panel.
     */
    public void loadStudentTelephoneNoRow() {
        // Label to hold the student telephone number form field text.
        final JLabel studentTelephoneNoLabel = new JLabel("Telephone Number:");
        setLabelAttributes(studentTelephoneNoLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 2;
        this.add(studentTelephoneNoLabel, c);
        
        // The text field input for the students telephone number
        studentTelephoneNoField = new JTextField(15);
        studentTelephoneNoField.setInputVerifier(inputVerifier);
        studentTelephoneNoField.setName(telephoneNoFieldName);
        studentTelephoneNoField.setFont(textFont);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 2;
        this.add(studentTelephoneNoField, c);
        
        // Label to hold the information tooltip and tooltip icon for the student telephone number form field
        final JLabel info3Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info3Label);
        // The info image icon for the label
        info3Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info3Label.setToolTipText("<HTML> The <b> Telephone no. </b>field cannot: "
                + "be empty, contain anything other than numbers, and must be 11 characters in length (eg. 07123456789). </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 2;
        this.add(info3Label, c);
    }

    /**
     * Adds all of the Students address field labels and text field inputs to the layout of this panel.
     */
    public void loadStudentAddressRow() {
        // Label to hold the student address line 1 form field text.
        final JLabel addressLine1Label = new JLabel("Address Line 1:");
        setLabelAttributes(addressLine1Label);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 3;
        this.add(addressLine1Label, c);
        
        // The text field input for the students 1st line of their address
        addressLine1Field = new JTextField(15);
        addressLine1Field.setInputVerifier(inputVerifier);
        addressLine1Field.setName(addressLine1FieldName);
        addressLine1Field.setFont(textFont);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 3;
        this.add(addressLine1Field, c);
        
        // Label to hold the information tooltip and tooltip icon for the address line 1 form field
        final JLabel info4Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info4Label);
        // The info image icon for the label
        info4Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info4Label.setToolTipText("<HTML> The <b> Address Line 1 </b>field cannot: "
                        + "be empty, or contain anything other than letters, numbers and spaces. </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 3;
        this.add(info4Label, c);
        
        
        
        // Label to hold the student address line 2 form field text.
        final JLabel addressLine2Label = new JLabel("Address Line 2:");
        setLabelAttributes(addressLine2Label);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 4;
        this.add(addressLine2Label, c);
        
        // The text field input for the students 2nd line of their address
        addressLine2Field = new JTextField(15);
        addressLine2Field.setInputVerifier(inputVerifier);
        addressLine2Field.setName(addressLine2FieldName);
        addressLine2Field.setFont(textFont);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 4;
        this.add(addressLine2Field, c);
        
        // Label to hold the information tooltip and tooltip icon for the address line 2 form field
        final JLabel info5Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info5Label);
        // The info image icon for the label
        info5Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info5Label.setToolTipText("<HTML> The <b> Address Line 2 </b>field cannot: "
                                    + "be empty, or contain anything other than letters and spaces. </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 4;
        this.add(info5Label, c);
        
        
        
        // Label to hold the student city for their address form field text
        final JLabel addressCityLabel = new JLabel("City:");
        setLabelAttributes(addressCityLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 5;
        this.add(addressCityLabel, c);
        
        // The text field input for the students city in their address
        addressCityField = new JTextField(15);
        addressCityField.setInputVerifier(inputVerifier);
        addressCityField.setName(addressCityFieldName);
        addressCityField.setFont(textFont);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 5;
        this.add(addressCityField, c);
        
        // Label to hold the information tooltip and tooltip icon for the city address form field
        final JLabel info6Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info6Label);
        // The info image icon for the label
        info6Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info6Label.setToolTipText("<HTML> The <b> Addresses City </b>field cannot: "
                                    + "be empty, or contain anything other than letters and spaces. </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 5;
        this.add(info6Label, c);
        
        
        
        // Label to hold the student county for their address form field text
        final JLabel addressCountyLabel = new JLabel("County:");
        setLabelAttributes(addressCountyLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 6;
        this.add(addressCountyLabel, c);
        
        // The text field input for the students county in their address
        addressCountyField = new JTextField(15);
        addressCountyField.setInputVerifier(inputVerifier);
        addressCountyField.setName(addressCountyFieldName);
        addressCountyField.setFont(textFont);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 6;
        this.add(addressCountyField, c);
        
        // Label to hold the information tooltip and tooltip icon for the city address form field
        final JLabel info7Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info7Label);
        // The info image icon for the label
        info7Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info7Label.setToolTipText("<HTML> The <b> Addresses County </b>field cannot: "
                                    + "be empty, or contain anything other than letters and spaces.  </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 6;
        this.add(info7Label, c);
        
        
        
        // Label to hold the student postcode for their address form field text
        final JLabel addressPostcodeLabel = new JLabel("Postcode:");
        setLabelAttributes(addressPostcodeLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 7;
        this.add(addressPostcodeLabel, c);
        
        // The text field input for the students postcode in their address
        addressPostcodeField = new JTextField(15);
        addressPostcodeField.setInputVerifier(inputVerifier);
        addressPostcodeField.setName(addressPostcodeFieldName);
        addressPostcodeField.setFont(textFont);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 7;
        this.add(addressPostcodeField, c);
        
        // Label to hold the information tooltip and tooltip icon for the postcode form field
        final JLabel info8Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info8Label);
        // The info image icon for the label
        info8Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info8Label.setToolTipText("<HTML> The <b> Addresses Postcode </b>field cannot: "
                                    + "be empty, contain anything other than letters, numbers or spaces, and must be between 6 - 8 characters long (eg. TS1 2PS). </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 7;
        this.add(info8Label, c);
    }

    /**
     * Adds Student has illnesses or disabilities field label and text field input to the layout of this panel.
     */
    public void loadHasIllnessRow() {
        // Label to hold the has illness form field text
        final JLabel hasIllnessLabel = new JLabel("Any Illnesses or Disabilities:");
        setLabelAttributes(hasIllnessLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 8;
        this.add(hasIllnessLabel, c);
        
        // The text field input for the students has illnesses or disabilites
        hasIllnessField = new JTextField(15);
        hasIllnessField.setInputVerifier(inputVerifier);
        hasIllnessField.setName(hasIllnessFieldName);
        hasIllnessField.setFont(textFont);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 2;
        c.gridy = 8;
        this.add(hasIllnessField, c);
        
        // Label to hold the information tooltip and tooltip icon for the has illnesses or disabilities form field
        final JLabel info9Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info9Label);
        // The info image icon for the label
        info9Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info9Label.setToolTipText("<HTML> The <b> Has Illnesses or Disabilities </b>field cannot: "
                                    + "be empty, or contain over 255 characters. </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 8;
        this.add(info9Label, c);
    }

    /**
     * Adds Students parent name field label and text field input to the layout of this panel.
     */
    public void loadParentNameRow() {
        // Label to hold the parents name form field text
        final JLabel parentNameLabel = new JLabel("Parents Name:");
        setLabelAttributes(parentNameLabel);

        // The coordinates for where to add this component to the layout
        c.gridx = 1;
        c.gridy = 9;
        this.add(parentNameLabel, c);
        
        // The text field input for the students parents name
        parentNameField = new JTextField(15);
        parentNameField.setInputVerifier(inputVerifier);
        parentNameField.setName(parentNameFieldName);
        parentNameField.setFont(textFont);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 2;
        c.gridy = 9;
        this.add(parentNameField, c);
        
        // Label to hold the information tooltip and tooltip icon for the parents name form field
        final JLabel info10Label = new JLabel();
        // Sets the standard attributes for a info label
        setInfoLabelAttributes(info10Label);
        // The info image icon for the label
        info10Label.setIcon(new ImageIcon("images/icons/information.png"));
        // The tooltip on mouse hover for the icon
        info10Label.setToolTipText("<HTML> The <b> Parents Name </b>field cannot: "
                                    + "be empty, or contain anything other than letters and spaces. </HTML>");
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 3;
        c.gridy = 9;
        this.add(info10Label, c);
    }

    /**
     * Adds Students current swimming level/ability field label and combo box to the layout of this panel.
     */
    public void loadSwimmingLevelRow() {
        // Label to hold the ability level form field text
        final JLabel swimmingLevelLabel = new JLabel("Current Swimming Level:");
        setLabelAttributes(swimmingLevelLabel);

        // The coordinates for where to add this component to the layout
        c.gridx = 1;
        c.gridy = 10;
        this.add(swimmingLevelLabel, c);
        
        // The combo box for the students current swimming/ability level
        swimmingLevelList = new JComboBox(SwimmingLevel.values());
        swimmingLevelList.setFont(textFont);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 2;
        c.gridy = 10;
        this.add(swimmingLevelList, c);
    }
    
    /**
     * Adds the submit and clear buttons to the layout of this panel.
     */
    public void loadButtons() {
        
        // Initialises the submit button with its attributes (inc button tooltip and icon)
        submitButton = new JButton("SUBMIT");
        submitButton.setPreferredSize(new Dimension(200, 50));
        submitButton.setFont(labelFont);
        submitButton.addActionListener(this);
        submitButton.setToolTipText("<html> Click this button to <b> submit </b> the new student record. </html>");
        submitButton.setIcon(new ImageIcon("images/icons/add.png"));
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 4;
        c.gridy = 10;
        this.add(submitButton, c);
        
        // Initialises the clear button with its attributes (inc button tooltip and icon)
        clearButton = new JButton("Clear Fields");
        clearButton.setPreferredSize(new Dimension(200, 50));
        clearButton.setFont(labelFont);
        clearButton.addActionListener(this);
        clearButton.setToolTipText("<html> Click this button to <b> clear </b> all fields on the student record form. </html>");
        clearButton.setIcon(new ImageIcon("images/icons/delete.png"));
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 5;
        c.gridy = 10;
        this.add(clearButton, c);
    }
    
    /**
     * Sets the standard attributes of the text labels for this form (avoiding repetition).
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
     * Sets the standard attributes of the info labels for this form (avoiding repetition).
     * @param label The info JLabel to set the standard attributes of
     */
    public void setInfoLabelAttributes(JLabel label) {
        label.setBackground(Color.white);
        label.setPreferredSize(new Dimension(50, 50));
        label.setOpaque(true);
        label.setVisible(true);
    }

    
    /**
     * Action Performed for both the submit button and clear button within the create student record form.
     * @param e The action event for the component that was pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // If the submit button is pressed...
        if (e.getSource() == submitButton) {
            
            // boolean used as a flag if any form field is left empty
            boolean invalidField = true;
            
            inputVerifier.verify(studentNameField);
            inputVerifier.verify(studentDOBDayField);
            inputVerifier.verify(studentDOBMonthField);
            inputVerifier.verify(studentDOBYearField);
            inputVerifier.verify(studentTelephoneNoField);
            inputVerifier.verify(addressLine1Field);
            inputVerifier.verify(addressLine2Field);
            inputVerifier.verify(addressCityField);
            inputVerifier.verify(addressCountyField);
            inputVerifier.verify(addressPostcodeField);
            inputVerifier.verify(hasIllnessField);
            inputVerifier.verify(parentNameField);
            
            // if any of the textfields backgrounds are not white flag as a field being invalid.
            if (studentNameField.getBackground() == Color.white && studentDOBDayField.getBackground() == Color.white 
                    && studentDOBMonthField.getBackground() == Color.white && studentDOBYearField.getBackground() == Color.white
                        && studentTelephoneNoField.getBackground() == Color.white && addressLine1Field.getBackground() == Color.white
                            && addressLine2Field.getBackground() == Color.white && addressCityField.getBackground() == Color.white
                                && addressCountyField.getBackground() == Color.white && addressPostcodeField.getBackground() == Color.white
                                    && hasIllnessField.getBackground() == Color.white && parentNameField.getBackground() == Color.white) {
                invalidField = false;
            }
            
            
            
            // If any form fields were empty display an error message
            if (invalidField == true) {
                JOptionPane.showMessageDialog(null,
                                "A field(s) has been left empty or contains an invalid entry\n"
                                        + "Invalid field(s) highlighted in yellow\n"
                                        + "Please complete the form correctly.\n"
                                        + "(Tip: Hover over the information icon next to the invalid field to view what types of entrys are invaid)",
                                "Error Empty Field(s)",
                                JOptionPane.ERROR_MESSAGE);
            } else {
                /**
                 * Otherwise form is ok to submit information as a new student record.
                 * boolean returned as to whether the student record was added to the db successfully.
                 */
                final boolean successfullyAdded = studentRecordController.createStudentRecord(studentNameField.getText(),
                                                                    studentDOBDayField.getText(),
                                                                    studentDOBMonthField.getText(),
                                                                    studentDOBYearField.getText(),
                                                                    studentTelephoneNoField.getText(),
                                                                    addressLine1Field.getText(),
                                                                    addressLine2Field.getText(),
                                                                    addressCityField.getText(),
                                                                    addressCountyField.getText(),
                                                                    addressPostcodeField.getText(),
                                                                    hasIllnessField.getText(),
                                                                    parentNameField.getText(),
                                                                    (SwimmingLevel) swimmingLevelList.getSelectedItem());
                
                // If the Student Record was successfully added reset all form fields back to empty
                if (successfullyAdded) {
                    // Resets the text fields
                    resetTextFields();
                    
                    JOptionPane.showMessageDialog(null,
                                "Student Record has been successfully created and added to the database!",
                                "Student Record created successfully!",
                                JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("images/icons/add.png"));
                } else {
                    // Display error message that the student record could not be added to the db
                    JOptionPane.showMessageDialog(null,
                                "The student record was unsuccessfully added to the database!",
                                "Error inserting student record into the database",
                                JOptionPane.ERROR_MESSAGE);
                }
            }
        // If the clear form button is pressed...
        } else if (e.getSource() == clearButton) {
            // Resets the text fields
            resetTextFields();
        }
    }
    
    /**
     * Resets the text fields to be empty and the background to white.
     */
    public void resetTextFields() { 
        // Resets all form text fields to empty
        studentNameField.setText("");
        studentDOBDayField.setText("");
        studentDOBMonthField.setText("");
        studentDOBYearField.setText("");
        studentTelephoneNoField.setText("");
        addressLine1Field.setText("");
        addressLine2Field.setText("");
        addressCityField.setText("");
        addressCountyField.setText("");
        addressPostcodeField.setText("");
        hasIllnessField.setText("");
        parentNameField.setText("");
            
        // Resets all the form background colour to white
        studentNameField.setBackground(Color.white);
        studentDOBDayField.setBackground(Color.white);
        studentDOBMonthField.setBackground(Color.white);
        studentDOBYearField.setBackground(Color.white);
        studentTelephoneNoField.setBackground(Color.white);
        addressLine1Field.setBackground(Color.white);
        addressLine2Field.setBackground(Color.white);
        addressCityField.setBackground(Color.white);
        addressCountyField.setBackground(Color.white);
        addressPostcodeField.setBackground(Color.white);
        hasIllnessField.setBackground(Color.white);
        parentNameField.setBackground(Color.white);
        
        // Sets the selected item in the combo box back to normal.
        swimmingLevelList.setSelectedIndex(0);
    }
}
