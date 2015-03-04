package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
public class ViewIndividualSRPanel extends JPanel {
    
    /**
     * The student record to display.
     */
    StudentRecord studentRecord;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * The Student Record controller for when inserting records into the Student Record table.
     */
    StudentRecordController studentRecordController;
    
    /**
     * The input verifier for the student record form (validates text field inputs).
     */
    SRFormInputVerifier inputVerifier;
    
    /**
     * The font (defining font style, font type and font size) for all form text labels.
     */
    Font labelFont = new Font("Arial", Font.PLAIN, 17);
    
    
    
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
    
    
    
    public ViewIndividualSRPanel(StudentRecord sr) {
        // Initialises the student record to be displayed using the student record provided as a param.
        studentRecord = sr;
        
        // Initialises the student record controller needed to add a student record to the db.
        studentRecordController = new StudentRecordController();
        
        // The input verifier for each text field input in this form.
        inputVerifier = new SRFormInputVerifier();
        
        // sets the ViewIndividualSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(1360, 565));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        loadSRForm();
        
        loadLessonBlocks();
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
        final JLabel studentNameLabel = new JLabel("Student Name:");
        setLabelAttributes(studentNameLabel);

        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 0;
        this.add(studentNameLabel, c);
        
        // The text field input for the students name
        studentNameField = new JTextField(15);
        studentNameField.setName(studentNameFieldName);
        studentNameField.setText(studentRecord.getStudentName());
        setTextFieldAttributes(studentNameField);
        
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
        studentTelephoneNoField.setName(telephoneNoFieldName);
        studentTelephoneNoField.setText(studentRecord.getTelephoneNo());
        setTextFieldAttributes(studentTelephoneNoField);
        
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
        addressLine1Field.setName(addressLine1FieldName);
        addressLine1Field.setText(studentRecord.getStudentAddress().getAddressLine1());
        setTextFieldAttributes(addressLine1Field);
        
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
        addressLine2Field.setName(addressLine2FieldName);
        addressLine2Field.setText(studentRecord.getStudentAddress().getAddressLine2());
        setTextFieldAttributes(addressLine2Field);
        
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
        addressCityField.setName(addressCityFieldName);
        addressCityField.setText(studentRecord.getStudentAddress().getCity());
        setTextFieldAttributes(addressCityField);
        
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
        addressCountyField.setName(addressCountyFieldName);
        addressCountyField.setText(studentRecord.getStudentAddress().getCounty());
        setTextFieldAttributes(addressCountyField);
        
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
        addressPostcodeField.setName(addressPostcodeFieldName);
        addressPostcodeField.setText(studentRecord.getStudentAddress().getPostcode());
        setTextFieldAttributes(addressPostcodeField);
        
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
        hasIllnessField.setName(hasIllnessFieldName);
        hasIllnessField.setText(studentRecord.getHasIllness());
        setTextFieldAttributes(hasIllnessField);
        
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
        parentNameField.setName(parentNameFieldName);
        parentNameField.setText(studentRecord.getParentName());
        setTextFieldAttributes(parentNameField);
        
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
        swimmingLevelList.setSelectedItem(studentRecord.getSwimmingLevel());
        swimmingLevelList.setEnabled(false);
        swimmingLevelList.setFont(labelFont);
        
        // The coordinates for where to add this component to the layout
        c.gridx = 2;
        c.gridy = 10;
        this.add(swimmingLevelList, c);
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
    
    public void setTextFieldAttributes(JTextField textField) {
        textField.setInputVerifier(inputVerifier);
        textField.setEditable(false);
        textField.setFont(labelFont);
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
     * Adds the students lesson blocks to this panel.
     */
    public void loadLessonBlocks() {
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}
