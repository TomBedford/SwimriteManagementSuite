package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingLevel;

/**
 *
 */
public class UpdateableSRPanel extends JPanel implements ActionListener {
    
    /**
     * The student record to display.
     */
    StudentRecord studentRecord;
    
    /**
     * The swimrite management suite body panel referenced for displaying an individual student record.
     */
    smsBodyPanel smsBodyPanelRef;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * The Student Record controller for querying from the Student Record database table.
     */
    StudentRecordController studentRecordController;
    
    /**
     * The input verifier for the student record form (validates text field inputs).
     */
    SRFormInputVerifier inputVerifier;
    
    /**
     * The font (defining font style, font type and font size) for all form text labels.
     */
    Font textFont = new Font("Arial", Font.PLAIN, 17);
    
    
    
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
     * The panel that holds all buttons for this layout.
     */
    JPanel buttonPanel;
    
    /**
     * The edit button to edit the fields of the student record.
     */
    JButton editButton;
    
    /**
     * The delete button to delete the student record from the database.
     */
    JButton deleteButton;
    
    /**
     * The update button to update the student record with the new field entries.
     * 
     */
    JButton updateButton;
    
    /**
     * The cancel button to cancel the editing of the student record.
     * 
     */
    JButton cancelButton;
    

    
    /**
     * Creates a student record form that can be edited/updated and deleted.
     * @param sr The student record to load into the form.
     * @param smsBodyPanel The body panel reference for when changing panels.
     */
    public UpdateableSRPanel(StudentRecord sr, smsBodyPanel smsBodyPanel) {
        // Initialises the student record to be displayed using the student record provided as a param.
        studentRecord = sr;
        
        // Initialises the sms Body Panel reference used for changing panels on the body panel
        smsBodyPanelRef = smsBodyPanel;
        
        // Initialises the student record controller needed to update the student record in the student record table
        studentRecordController = new StudentRecordController();
        
        // The input verifier for each text field input in this form.
        inputVerifier = new SRFormInputVerifier();
        
        // sets the ViewIndividualSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(700, 565));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // Loads the student record form onto this panel
        loadSRForm();
        
        // Loads the edit and delete button onto this panel
        loadButtons();
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
    }
    
    /**
     * Adds Student Name field label and text field input to the layout of this panel.
     */
    public void loadStudentNameRow() {
        // Label to hold the student name form field text.
        final JLabel studentNameLabel = new JLabel("  Student Name:");
        setLabelAttributes(studentNameLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 0;
        this.add(studentNameLabel, c);
        
        // The text field input for the students name
        studentNameField = new JTextField(15);
        studentNameField.setName(studentNameFieldName);
        studentNameField.setText(studentRecord.getStudentName());
        setTextFieldAttributes(studentNameField);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 0;
        this.add(info1Label, c);
    }

    /**
     * Adds Students DOB field labels and text field inputs to the layout of this panel.
     */
    public void loadStudentDOBRow() {
        // Label to hold the student date of birth form field text
        final JLabel studentDOBLabel = new JLabel("  Date of Birth (DOB):");
        setLabelAttributes(studentDOBLabel);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 1;
        this.add(studentDOBLabel, c);
        
        // Label to hold all the fields for the DOB
        final JPanel dobFieldHolderLabel = new JPanel();
        dobFieldHolderLabel.setPreferredSize(new Dimension(200, 50));
        dobFieldHolderLabel.setBackground(Color.white);
        dobFieldHolderLabel.setOpaque(true);
        dobFieldHolderLabel.setVisible(true);
        
        final String[] studentDOB = studentRecordController.unformatDOB(studentRecord.getStudentDOB());
        
        // The text field input for the students day of their date of birth
        studentDOBDayField = new JTextField(2);
        studentDOBDayField.setName(dobDayFieldName);
        studentDOBDayField.setText(studentDOB[0]);
        setTextFieldAttributes(studentDOBDayField);
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
        studentDOBMonthField.setName(dobMonthFieldName);
        studentDOBMonthField.setText(studentDOB[1]);
        setTextFieldAttributes(studentDOBMonthField);
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
        studentDOBYearField.setName(dobYearFieldName);
        studentDOBYearField.setText(studentDOB[2]);
        setTextFieldAttributes(studentDOBYearField);
        dobFieldHolderLabel.add(studentDOBYearField);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 1;
        this.add(info2Label, c);
    }

    /**
     * Adds Students telephone number field label and text field input to the layout of this panel.
     */
    public void loadStudentTelephoneNoRow() {
        // Label to hold the student telephone number form field text.
        final JLabel studentTelephoneNoLabel = new JLabel("  Telephone Number:");
        setLabelAttributes(studentTelephoneNoLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 2;
        this.add(studentTelephoneNoLabel, c);
        
        // The text field input for the students telephone number
        studentTelephoneNoField = new JTextField(15);
        studentTelephoneNoField.setName(telephoneNoFieldName);
        studentTelephoneNoField.setText(studentRecord.getTelephoneNo());
        setTextFieldAttributes(studentTelephoneNoField);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 2;
        this.add(info3Label, c);
    }

    /**
     * Adds all of the Students address field labels and text field inputs to the layout of this panel.
     */
    public void loadStudentAddressRow() {
        // Label to hold the student address line 1 form field text.
        final JLabel addressLine1Label = new JLabel("  Address Line 1:");
        setLabelAttributes(addressLine1Label);

        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 3;
        this.add(addressLine1Label, c);
        
        // The text field input for the students 1st line of their address
        addressLine1Field = new JTextField(15);
        addressLine1Field.setName(addressLine1FieldName);
        addressLine1Field.setText(studentRecord.getStudentAddress().getAddressLine1());
        setTextFieldAttributes(addressLine1Field);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 3;
        this.add(info4Label, c);
        
        
        
        // Label to hold the student address line 2 form field text.
        final JLabel addressLine2Label = new JLabel("  Address Line 2:");
        setLabelAttributes(addressLine2Label);

        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 4;
        this.add(addressLine2Label, c);
        
        // The text field input for the students 2nd line of their address
        addressLine2Field = new JTextField(15);
        addressLine2Field.setName(addressLine2FieldName);
        addressLine2Field.setText(studentRecord.getStudentAddress().getAddressLine2());
        setTextFieldAttributes(addressLine2Field);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 4;
        this.add(info5Label, c);
        
        
        
        // Label to hold the student city for their address form field text
        final JLabel addressCityLabel = new JLabel("  City:");
        setLabelAttributes(addressCityLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 5;
        this.add(addressCityLabel, c);
        
        // The text field input for the students city in their address
        addressCityField = new JTextField(15);
        addressCityField.setName(addressCityFieldName);
        addressCityField.setText(studentRecord.getStudentAddress().getCity());
        setTextFieldAttributes(addressCityField);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 5;
        this.add(info6Label, c);
        
        
        
        // Label to hold the student county for their address form field text
        final JLabel addressCountyLabel = new JLabel("  County:");
        setLabelAttributes(addressCountyLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 6;
        this.add(addressCountyLabel, c);
        
        // The text field input for the students county in their address
        addressCountyField = new JTextField(15);
        addressCountyField.setName(addressCountyFieldName);
        addressCountyField.setText(studentRecord.getStudentAddress().getCounty());
        setTextFieldAttributes(addressCountyField);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 6;
        this.add(info7Label, c);
        
        
        
        // Label to hold the student postcode for their address form field text
        final JLabel addressPostcodeLabel = new JLabel("  Postcode:");
        setLabelAttributes(addressPostcodeLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 7;
        this.add(addressPostcodeLabel, c);
        
        // The text field input for the students postcode in their address
        addressPostcodeField = new JTextField(15);
        addressPostcodeField.setName(addressPostcodeFieldName);
        addressPostcodeField.setText(studentRecord.getStudentAddress().getPostcode());
        setTextFieldAttributes(addressPostcodeField);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 7;
        this.add(info8Label, c);
    }

    /**
     * Adds Student has illnesses or disabilities field label and text field input to the layout of this panel.
     */
    public void loadHasIllnessRow() {
        // Label to hold the has illness form field text
        final JLabel hasIllnessLabel = new JLabel("  Any Illnesses or Disabilities:");
        setLabelAttributes(hasIllnessLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 8;
        this.add(hasIllnessLabel, c);
        
        // The text field input for the students has illnesses or disabilites
        hasIllnessField = new JTextField(15);
        hasIllnessField.setName(hasIllnessFieldName);
        hasIllnessField.setText(studentRecord.getHasIllness());
        setTextFieldAttributes(hasIllnessField);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 8;
        this.add(info9Label, c);
    }

    /**
     * Adds Students parent name field label and text field input to the layout of this panel.
     */
    public void loadParentNameRow() {
        // Label to hold the parents name form field text
        final JLabel parentNameLabel = new JLabel("  Parents Name:");
        setLabelAttributes(parentNameLabel);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 9;
        this.add(parentNameLabel, c);
        
        // The text field input for the students parents name
        parentNameField = new JTextField(15);
        parentNameField.setName(parentNameFieldName);
        parentNameField.setText(studentRecord.getParentName());
        setTextFieldAttributes(parentNameField);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 1;
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
        c.gridx = 2;
        c.gridy = 9;
        this.add(info10Label, c);
    }

    /**
     * Adds Students current swimming level/ability field label and combo box to the layout of this panel.
     */
    public void loadSwimmingLevelRow() {
        // Label to hold the ability level form field text
        final JLabel swimmingLevelLabel = new JLabel("  Current Swimming Level:");
        setLabelAttributes(swimmingLevelLabel);

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 10;
        this.add(swimmingLevelLabel, c);
        
        // The combo box for the students current swimming/ability level
        swimmingLevelList = new JComboBox(SwimmingLevel.values());
        swimmingLevelList.setSelectedItem(studentRecord.getSwimmingLevel());
        swimmingLevelList.setEnabled(false);
        swimmingLevelList.setFont(textFont);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 1;
        c.gridy = 10;
        this.add(swimmingLevelList, c);
    }
    
    /**
     * Sets the standard attributes for the text labels for this form (avoiding repetition).
     * @param label The JLabel to set the standard attributes of
     */
    public void setLabelAttributes(JLabel label) {
        // Text is on the left, with label background being white and the label font defined also
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(textFont);
        label.setBackground(Color.white);
        label.setPreferredSize(new Dimension(225, 50));
        label.setOpaque(true);
        label.setVisible(true);
    }
    
    /**
     * Sets the standard attributes for the text fields for this from (avoiding repetition).
     * @param textField 
     */
    public void setTextFieldAttributes(JTextField textField) {
        textField.setInputVerifier(inputVerifier);
        textField.setEditable(false);
        textField.setFont(textFont);
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
     * Loads the edit and delete buttons onto a button panel that is added to this layout.
     */
    public void loadButtons() {
        // Creates the panel to hold the buttons and sets the JPanels attributes
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 50));
        buttonPanel.setVisible(true);
        buttonPanel.setBackground(Color.white);
      
        // Initialises the edit button with its attributes (inc button tooltip and icon)
        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(90, 45));
        editButton.addActionListener(this);
        editButton.setToolTipText("<html> Click this button to <b> edit </b> the student record. </html>");
        editButton.setIcon(new ImageIcon("images/icons/bullet_edit.png"));
        buttonPanel.add(editButton);
        
        // Initialises the delete button with its attributes (inc button tooltip and icon)
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(90, 45));
        deleteButton.addActionListener(this);
        deleteButton.setToolTipText("<html> Click this button to <b> delete </b> the student record. </html>");
        deleteButton.setIcon(new ImageIcon("images/icons/delete.png"));
        buttonPanel.add(deleteButton);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 2;
        c.gridy = 10;
        c.gridwidth = 2;
        this.add(buttonPanel, c);
        
        // Resets the grid width constraint for when adding other components and components on runtime.
        c.gridwidth = 1;
    }
    
    /**
     * Enters edit mode so the student record form can be edited and adds and removes the appropriate buttons.
     */
    public void enterEditMode() {
        // Enables all text fields and the combo box to be interacted with by the user.
        studentNameField.setEditable(true);
        studentDOBDayField.setEditable(true);
        studentDOBMonthField.setEditable(true);
        studentDOBYearField.setEditable(true);
        studentTelephoneNoField.setEditable(true);
        addressLine1Field.setEditable(true);
        addressLine2Field.setEditable(true);
        addressCityField.setEditable(true);
        addressCountyField.setEditable(true);
        addressPostcodeField.setEditable(true);
        hasIllnessField.setEditable(true);
        parentNameField.setEditable(true);
        swimmingLevelList.setEnabled(true);
            
        // Removes the edit button from the button panel
        buttonPanel.remove(editButton);
        buttonPanel.remove(deleteButton);
            
        // Creates the update button and sets its attributes
        updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(90, 45));
        updateButton.addActionListener(this);
        updateButton.setToolTipText("<html> Click this button to <b> update </b> the student record with the new text field values. </html>");
        updateButton.setIcon(new ImageIcon("images/icons/accept.png"));
        
        // Adds the update button to the button panel
        buttonPanel.add(updateButton);
            
        // Creates the cancel button and sets its attributes
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(90, 45));
        cancelButton.addActionListener(this);
        cancelButton.setToolTipText("<html> Click this button to <b> cancel </b> the editting of the student record and keep the old field values. </html>");
        cancelButton.setIcon(new ImageIcon("images/icons/cancel.png"));
        
        // Adds the cancel button to the button panel
        buttonPanel.add(cancelButton);
            
        this.updateUI();
    }
    
    /**
     * Exits edit mode making form inputs unaccessible via interaction.
     */
    public void exitEditMode() {
        // Disables all text fields and the combo box disabling interaction from the user.
        studentNameField.setEditable(false);
        studentDOBDayField.setEditable(false);
        studentDOBMonthField.setEditable(false);
        studentDOBYearField.setEditable(false);
        studentTelephoneNoField.setEditable(false);
        addressLine1Field.setEditable(false);
        addressLine2Field.setEditable(false);
        addressCityField.setEditable(false);
        addressCountyField.setEditable(false);
        addressPostcodeField.setEditable(false);
        hasIllnessField.setEditable(false);
        parentNameField.setEditable(false);
        swimmingLevelList.setEnabled(false);
        
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
        
        buttonPanel.remove(updateButton);
        buttonPanel.remove(cancelButton);
        
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        this.updateUI();
    }
    
    /**
     * Validates the student record form and updates the student record if valid.
     */
    public void attemptUpdateStudentRecord() {
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
                    "Error Empty or Invalid Field(s)",
                    JOptionPane.ERROR_MESSAGE);
        
        // Otherwise the form is valid
        } else {
            // Sets the student record to its new values
            studentRecord.setStudentName(studentNameField.getText());
            studentRecord.setStudentDOB(studentRecordController.formatDOB(studentDOBDayField.getText(),
                                            studentDOBMonthField.getText(), studentDOBYearField.getText()));
            studentRecord.setTelephoneNo(studentTelephoneNoField.getText());
            studentRecord.getStudentAddress().setAddressLine1(addressLine1Field.getText());
            studentRecord.getStudentAddress().setAddressLine2(addressLine2Field.getText());
            studentRecord.getStudentAddress().setCity(addressCityField.getText());
            studentRecord.getStudentAddress().setCounty(addressCountyField.getText());
            studentRecord.getStudentAddress().setPostcode(addressPostcodeField.getText());
            studentRecord.setHasIllness(hasIllnessField.getText());
            studentRecord.setParentName(parentNameField.getText());

            // Updates the student record in the database
            studentRecordController.updateStudentRecord(studentRecord);
            
            // Calls the method to exit the forms edit mode.
            exitEditMode();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // If the source of the button press was the edit button...
        if (e.getSource() == editButton) {
            
            // Calls method to change the form into an editable mode.
            enterEditMode();
            
        // If the source of the button press was the delete button
        } else if (e.getSource() == deleteButton) {
            
            // Option pane to confirm if the user wants to delete the student record
            final int answer = JOptionPane.showConfirmDialog(null, 
                "<HTML> Do you want to <b> <font color='red'> delete</font> </b> </HTML> \n"
                        + studentRecord.getStudentName() + "s" + " Student Record?"
                        , "Delete Student Record?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            // Switch to determine users choice.
            switch (answer) {
                // The user wants to delete the student record from the db so it is delted.
                case 0: studentRecordController.deleteStudentRecord(studentRecord);
                        // Takes the user back to the viewl all student records panel.
                        smsBodyPanelRef.addViewAllSRPanel();
                        break;
                    
                // The user does not want to delete the student record from the db so don't do anything.
                case 1: break;
                    
                default:break;
            }
        // If the source of the button press was the update button
        } else if (e.getSource() == updateButton) {
            
            // Calls the method to attempt to update the students record.
            attemptUpdateStudentRecord();
            
        // If the source of the button press was the cancel button  
        } else if (e.getSource() == cancelButton) {
            
            // resets the input fields to their correct values
            studentNameField.setText(studentRecord.getStudentName());
            final String[] studentDOB = studentRecordController.unformatDOB(studentRecord.getStudentDOB());
            studentDOBDayField.setText(studentDOB[0]);
            studentDOBMonthField.setText(studentDOB[1]);
            studentDOBYearField.setText(studentDOB[2]);
            studentTelephoneNoField.setText(studentRecord.getTelephoneNo());
            addressLine1Field.setText(studentRecord.getStudentAddress().getAddressLine1());
            addressLine2Field.setText(studentRecord.getStudentAddress().getAddressLine2());
            addressCityField.setText(studentRecord.getStudentAddress().getCity());
            addressCountyField.setText(studentRecord.getStudentAddress().getCounty());
            addressPostcodeField.setText(studentRecord.getStudentAddress().getPostcode());
            hasIllnessField.setText(studentRecord.getHasIllness());
            parentNameField.setText(studentRecord.getParentName());
            swimmingLevelList.setSelectedItem(studentRecord.getSwimmingLevel());
            
            // Calls the method to exit the forms edit mode.
            exitEditMode();
        }
    }
}
