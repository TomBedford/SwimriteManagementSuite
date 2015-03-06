package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LessonBlockController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.AttendanceType;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.PaymentType;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;

/**
 *
 */
public class LessonBlockPanel extends JPanel implements ActionListener{
    
    LessonBlock lessonBlockRef;
    
    int lessonBlockNumberRef;
    
    /**
     * The swimrite management suite body panel referenced for displaying an individual student record.
     */
    smsBodyPanel smsBodyPanelRef;
    
    StudentRecord studentRecordRef;
    
    LessonBlockController lessonBlockController;
    
    /**
     * The grid bag constraint to manipulate when adding components to the layout. 
     */
    GridBagConstraints c;
    
    /**
     * The font for the title label of the lesson block.
     */
    private Font titleLabelFont = new Font("Arial", Font.BOLD, 20);
    
    /**
     * The font (defining font style, font type and font size) for all form text labels.
     */
    private Font textFont = new Font("Arial", Font.PLAIN, 13);
    
    /**
     * The font for all records/rows in the table.
     */
    private Font tableRecordFont = new Font("Arial", Font.PLAIN, 14);
       
    /**
     * The font for all headers in the table.
     */
    private Font tableHeaderFont = new Font("Arial", Font.PLAIN, 16);
    
    /**
     * The colour used for the headers background.
     */
    private Color smsBlue = new Color(172, 172, 255);
    
    /**
    * The light gray colour for each alternative row in the table.
    */
    Color alternateRowColor = new Color(211, 211, 211);
    
    /**
     * The Payment Amount field text field name needed for referencing in the input verifier.
     */
    public static String paymentAmountFieldName = "Payment Amount Field";
    
    /**
     * The day of the payment date field text field name needed for referencing in the input verifier.
     */
    public static String paymentDayFieldName = "Payment Day Field";
    
    /**
     *  The month of the payment date field text field name needed for referencing in the input verifier.
     */
    public static String paymentMonthFieldName = "Payment Month Field";
    
    /**
     *  The year of the payment date field text field name needed for referencing in the input verifier.
     */
    public static String paymentFieldName = "Payment Year Field";
    
    /**
     * The payment taker field text field name needed for referencing in the input verifier.
     */
    public static String paymentTakerFieldName = "Payment Taker Field";
    
    JTextField paymentAmountField;
    
    JComboBox paymentTypeList;
    
    JTextField paymentDayField;
    
    JTextField paymentMonthField;
    
    JTextField paymentYearField;
    
    JTextField paymentTakerField;
    
    /**
     * An array of String to hold the column names for the lesson block table.
     */
    String[] columnNames;
    
    JTable lessonBlockTable;
    
    LessonBlockTableModel lessonBlockTableModel;
            
    
    
    JPanel buttonPanel;
    
    JButton editButton;
    
    /**
     * The delete button to delete the lesson block record from the database.
     */
    JButton deleteButton;
    
    JButton updateButton;
    
    JButton cancelButton;
    
    JPanel attendanceEditingPanel;
    
    JButton[] presentButton;
    
    JButton[] absentButton;
    
    JButton[] funSwimButton;
    
    JButton[] funSwimTakenButton;
    
    JButton[] clearAttendanceButton;
    
    public LessonBlockPanel(LessonBlock lessonBlock, int lessonBlockNumber, smsBodyPanel smsBodyPanel, StudentRecord studentRecord) {
        
        lessonBlockRef = lessonBlock;
        
        lessonBlockNumberRef = lessonBlockNumber;
        
        // Initialises the sms Body Panel reference used for changing panels on the body panel
        smsBodyPanelRef = smsBodyPanel;
        
        studentRecordRef = studentRecord;
        
        lessonBlockController = new LessonBlockController();
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // sets the ViewIndividualSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(450, 550));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        
        addTitleLabel();
        
        addPaymentDetails();
        
        addLessonBlockTable();
        
        addButtonPanel();
    }
    
    public void addTitleLabel() {
        JLabel titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(450, 30));
        titleLabel.setOpaque(true);
        titleLabel.setVisible(true);
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(titleLabelFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        titleLabel.setText("Lesson Block No. " + lessonBlockNumberRef);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(titleLabel, c);
        
        // Resets the grid width for other components added
        c.gridwidth = 1;
    }
    
    public void addPaymentDetails() {
        /**
         * The JPanel to hold all payment detail labels and text fields and spacing components.
         */
        JPanel paymentDetailsPanel = new JPanel();
        paymentDetailsPanel.setPreferredSize(new Dimension(450, 90));
        paymentDetailsPanel.setVisible(true);
        paymentDetailsPanel.setBackground(Color.white);
        
        
        
        /**
         * Adds the payment amount label and text field to the payment details panel.
         */
        final JLabel paymentAmountLabel = new JLabel("Payment Amount:   £");
        paymentAmountLabel.setPreferredSize(new Dimension(125, 20));
        setLabelAttributes(paymentAmountLabel);
        paymentDetailsPanel.add(paymentAmountLabel);
        
        
        paymentAmountField = new JTextField(4);
        paymentAmountField.setName(paymentAmountFieldName);
        paymentAmountField.setPreferredSize(new Dimension(30, 20));
        setTextFieldAttributes(paymentAmountField);
        paymentDetailsPanel.add(paymentAmountField);
        
        
        
        /**
         * Adds the Payment Type label and payment type combo box to the payment details panel.
         */
        final JLabel paymentTypeLabel = new JLabel("Payment Type:");
        paymentTypeLabel.setPreferredSize(new Dimension(100, 20));
        setLabelAttributes(paymentTypeLabel);
        paymentDetailsPanel.add(paymentTypeLabel);
        
        // The combo box for the students current swimming/ability level
        paymentTypeList = new JComboBox(PaymentType.values());
        paymentTypeList.setFont(textFont);
        paymentTypeList.setEnabled(false);
        paymentDetailsPanel.add(paymentTypeList);
        
        
        
        /**
         * Adds the payment date label to this layout and the payment date fields onto the payment details panel.
         */
        final JLabel paymentDateLabel = new JLabel("Payment Date:");
        paymentDateLabel.setPreferredSize(new Dimension(100, 20));
        setLabelAttributes(paymentDateLabel);
        paymentDetailsPanel.add(paymentDateLabel);
        
        
        // The text field input for the students day of their date of birth
        paymentDayField = new JTextField(2);
        paymentDayField.setName(paymentDayFieldName);
        paymentDayField.setPreferredSize(new Dimension(20, 20));
        setTextFieldAttributes(paymentDayField);
        paymentDetailsPanel.add(paymentDayField);
        
        
        // Label to hold a '/' SLASH inbetween the day and month of the payment date
        final JLabel firstSlashLabel = new JLabel("<html><span style='font-size:14px'>/</span></html>");
        firstSlashLabel.setPreferredSize(new Dimension(10, 20));
        firstSlashLabel.setBackground(Color.white);
        firstSlashLabel.setOpaque(true);
        firstSlashLabel.setVisible(true);
        paymentDetailsPanel.add(firstSlashLabel);
        
        
        // The text field input for the students month of their date of birth
        paymentMonthField = new JTextField(2);
        paymentMonthField.setName(paymentMonthFieldName);
        paymentMonthField.setPreferredSize(new Dimension(20, 20));
        setTextFieldAttributes(paymentMonthField);
        paymentDetailsPanel.add(paymentMonthField);
        
        
        // Label to hold a '/' SLASH inbetween the month and year of the payment date 
        final JLabel secondSlashLabel = new JLabel("<html><span style='font-size:14px'>/</span></html>");
        secondSlashLabel.setPreferredSize(new Dimension(10, 20));
        secondSlashLabel.setBackground(Color.white);
        secondSlashLabel.setOpaque(true);
        secondSlashLabel.setVisible(true);
        paymentDetailsPanel.add(secondSlashLabel);
        
        
        // The text field input for the students year of their date of birth
        paymentYearField = new JTextField(3);
        paymentYearField.setName(paymentFieldName);
        paymentYearField.setPreferredSize(new Dimension(25, 20));
        setTextFieldAttributes(paymentYearField);
        paymentDetailsPanel.add(paymentYearField);
        
        
        
        // Adds a component to space out components on the payment details panel
        final JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(140, 20));
        spacingLabel1.setOpaque(false);
        paymentDetailsPanel.add(spacingLabel1);
        
        
        
        /**
         * Adds the payment taker label and text field to the payment details panel
         */
        final JLabel paymentTakerLabel = new JLabel("Payment Taker: ");
        paymentTakerLabel.setPreferredSize(new Dimension(100, 20));
        setLabelAttributes(paymentTakerLabel);
        paymentDetailsPanel.add(paymentTakerLabel);
        
        
        paymentTakerField = new JTextField(15);
        paymentTakerField.setName(paymentTakerFieldName);
        paymentTakerField.setPreferredSize(new Dimension(50, 20));
        setTextFieldAttributes(paymentTakerField);
        paymentDetailsPanel.add(paymentTakerField);
        
        
        
         // Adds a component to space out components on the payment details panel
        final JLabel spacingLabel2 = new JLabel();
        spacingLabel2.setPreferredSize(new Dimension(120, 20));
        spacingLabel2.setOpaque(false);
        paymentDetailsPanel.add(spacingLabel2);
        
        
        
        // Sets all of the payment detail field values if the lesson payment record exists
        if (lessonBlockRef.getLessonPayment() != null) {
            paymentAmountField.setText(lessonBlockRef.getLessonPayment().getPaymentAmount());
            paymentTypeList.setSelectedItem(lessonBlockRef.getLessonPayment().getPaymentType());
            final String[] paymentDate = lessonBlockController.unformatPaymentDate(lessonBlockRef.getLessonPayment().getPaymentDate());
            paymentDayField.setText(paymentDate[0]);
            paymentMonthField.setText(paymentDate[1]);
            paymentYearField.setText(paymentDate[2]);
            paymentTakerField.setText(lessonBlockRef.getLessonPayment().getPaymentTaker());
        }
        
        
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(paymentDetailsPanel, c);
        
        // Resets the grid width for other components added
        c.gridwidth = 1;
    }
    
    public void setLabelAttributes(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(textFont);
        label.setBackground(Color.white);
        label.setOpaque(true);
        label.setVisible(true);
    }
    
    public void setTextFieldAttributes(JTextField textField) {
        //textField.setInputVerifier(inputVerifier);
        textField.setEditable(false);
        textField.setFont(textFont);
    }
    
    public void addLessonBlockTable() {
        
        // The an array of column names for the lesson block attendance table
        columnNames = new String[]{" Lesson Date",
                                " Attendance"};
        
        lessonBlockTable = new JTable(convertLessonBlockForTable(), columnNames) {
            @Override
            public Component prepareRenderer(TableCellRenderer r, int data, int column) {
                // Gets the component to manipulate
                final Component c = super.prepareRenderer(r, data, column);
                
                // If the data (row) number is divisible by two then set the background to white, otherwise light gray
                if (data % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(alternateRowColor);
                }

                // If the cell is selected change the row to the colour smsBlue
                if (isCellSelected(data, column)) {

                    c.setBackground(smsBlue); 
                }

                return c;
            }
        };
        
        lessonBlockTableModel = new LessonBlockTableModel(convertLessonBlockForTable(), columnNames);
        
        lessonBlockTable.setModel(lessonBlockTableModel);
        
        //Sets the max size of the table until scrolling is used
        //lessonBlockTable.setPreferredScrollableViewportSize(new Dimension(1200, 475));
        // Makes sure the table is always big enough to fill the container
        lessonBlockTable.setFillsViewportHeight(true);
        // Shows the grid lines both horizontal and vertical
        lessonBlockTable.setShowGrid(true);
        // Makes sure only one row is selectable at a time
        lessonBlockTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Sets the font of all row/records in the table
        lessonBlockTable.setFont(tableRecordFont);
        // Sets the height of the rows
        lessonBlockTable.setRowHeight(35);
        // Sets the font of the headers
        lessonBlockTable.getTableHeader().setFont(tableHeaderFont);
        // Sets the background colour for the headers row
        lessonBlockTable.getTableHeader().setBackground(smsBlue);
        // Sets the size of the header row
        lessonBlockTable.getTableHeader().setPreferredSize(new Dimension(120, 40));
        
        // Initializes the scroll pane to hold the student record table, setting attributes
        JScrollPane tableScrollPane = new JScrollPane(lessonBlockTable);
        tableScrollPane.setPreferredSize(new Dimension(240, 395));

        // The coordinates for where to add this component to the layout
        c.gridx = 0;
        c.gridy = 2;
        this.add(tableScrollPane, c);
    }
    
    /**
     * Converts the lesson block object into a two dimensional array of objects suitable for the lesson block attendance
     * tables parameters.
     * @return 2 Dimensional array of object containing the formatted lesson block attendance details..
     */
    public Object[][] convertLessonBlockForTable() {

        /**
         * The two dimensional array to hold the lesson block attendance details
         * size of the student record list is used for the first array size.
         * size of the column names (the amount of different data columns to put into the object for the table)
         */
        final Object[][] tableLessonBlock = new Object[10][2];
        
        // Sets the lesson date and attendance values for the table
        tableLessonBlock[0][0] = lessonBlockRef.getLesson1Date();
        tableLessonBlock[0][1] = lessonBlockRef.getLesson1Attendance();
        
        tableLessonBlock[1][0] = lessonBlockRef.getLesson2Date();
        tableLessonBlock[1][1] = lessonBlockRef.getLesson2Attendance();
        
        tableLessonBlock[2][0] = lessonBlockRef.getLesson3Date();
        tableLessonBlock[2][1] = lessonBlockRef.getLesson3Attendance();
        
        tableLessonBlock[3][0] = lessonBlockRef.getLesson4Date();
        tableLessonBlock[3][1] = lessonBlockRef.getLesson4Attendance();
        
        tableLessonBlock[4][0] = lessonBlockRef.getLesson5Date();
        tableLessonBlock[4][1] = lessonBlockRef.getLesson5Attendance();
        
        tableLessonBlock[5][0] = lessonBlockRef.getLesson6Date();
        tableLessonBlock[5][1] = lessonBlockRef.getLesson6Attendance();
        
        tableLessonBlock[6][0] = lessonBlockRef.getLesson7Date();
        tableLessonBlock[6][1] = lessonBlockRef.getLesson7Attendance();
        
        tableLessonBlock[7][0] = lessonBlockRef.getLesson8Date();
        tableLessonBlock[7][1] = lessonBlockRef.getLesson8Attendance();
        
        tableLessonBlock[8][0] = lessonBlockRef.getLesson9Date();
        tableLessonBlock[8][1] = lessonBlockRef.getLesson9Attendance();
        
        tableLessonBlock[9][0] = lessonBlockRef.getLesson10Date();
        tableLessonBlock[9][1] = lessonBlockRef.getLesson10Attendance();
        
        // Returns the converted 2 dimensional objecty array
        return tableLessonBlock;
    }
    
    public void addButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(210, 35));
        buttonPanel.setBackground(Color.white);
        buttonPanel.setVisible(true);
        
        // Initialises the submit button with its attributes (inc button tooltip and icon)
        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(100, 30));
        editButton.addActionListener(this);
        editButton.setToolTipText("<html> Click this button to <b> edit </b> the lesson block records. </html>");
        editButton.setIcon(new ImageIcon("images/icons/bullet_edit.png"));
        buttonPanel.add(editButton);
        
        // Initialises the delete button with its attributes (inc button tooltip and icon)
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.addActionListener(this);
        deleteButton.setToolTipText("<html> Click this button to <b> delete </b> the lesson block record. </html>");
        deleteButton.setIcon(new ImageIcon("images/icons/16x16/delete.png"));
        buttonPanel.add(deleteButton);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 3;
        this.add(buttonPanel, c);
    }
    
    public void addAttendanceEditingPanel() {
        attendanceEditingPanel = new JPanel();
        attendanceEditingPanel.setPreferredSize(new Dimension(210, 395));
        attendanceEditingPanel.setBackground(Color.white);
        attendanceEditingPanel.setVisible(true);
        
        JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(210, 32));
        spacingLabel1.setOpaque(false);
        attendanceEditingPanel.add(spacingLabel1);
        
        presentButton = new JButton[10];
        
        absentButton = new JButton[10];
        
        funSwimButton = new JButton[10];
        
        funSwimTakenButton = new JButton[10];
        
        clearAttendanceButton = new JButton[10];
        
        for (int i = 0; i < 10; i++) {
            presentButton[i] = new JButton();
            presentButton[i].setPreferredSize(new Dimension(30, 30));
            presentButton[i].addActionListener(this);
            presentButton[i].setToolTipText("<html> Click this button to mark that the student was <b> present </b> for this lesson. </html>");
            presentButton[i].setIcon(new ImageIcon("images/icons/16x16/accept.png"));
            attendanceEditingPanel.add(presentButton[i]);
            
            absentButton[i] = new JButton();
            absentButton[i].setPreferredSize(new Dimension(30, 30));
            absentButton[i].addActionListener(this);
            absentButton[i].setToolTipText("<html> Click this button to mark that the student was <b> absent </b> for this lesson. </html>");
            absentButton[i].setIcon(new ImageIcon("images/icons/16x16/cancel.png"));
            attendanceEditingPanel.add(absentButton[i]);
            
            funSwimButton[i] = new JButton();
            funSwimButton[i].setPreferredSize(new Dimension(30, 30));
            funSwimButton[i].addActionListener(this);
            funSwimButton[i].setToolTipText("<html> Click this button to mark that the student was granted a <b> fun swim </b> for this lesson. </html>");
            funSwimButton[i].setIcon(new ImageIcon("images/icons/16x16/fun_swim.png"));
            attendanceEditingPanel.add(funSwimButton[i]);
            
            funSwimTakenButton[i] = new JButton();
            funSwimTakenButton[i].setPreferredSize(new Dimension(30, 30));
            funSwimTakenButton[i].addActionListener(this);
            funSwimTakenButton[i].setToolTipText("<html> Click this button to mark that the student was granted a fun swim, but it has been <b> taken </b> for this lesson. </html>");
            funSwimTakenButton[i].setIcon(new ImageIcon("images/icons/16x16/fun_swim_taken.png"));
            attendanceEditingPanel.add(funSwimTakenButton[i]);
            
            clearAttendanceButton[i] = new JButton();
            clearAttendanceButton[i].setPreferredSize(new Dimension(30, 30));
            clearAttendanceButton[i].addActionListener(this);
            clearAttendanceButton[i].setToolTipText("<html> Click this button to <b> clear </b> the attendance for this lesson. </html>");
            clearAttendanceButton[i].setIcon(new ImageIcon("images/icons/16x16/blank.png"));
            attendanceEditingPanel.add(clearAttendanceButton[i]);
            
            JLabel spacingLabel2 = new JLabel();
            spacingLabel2.setPreferredSize(new Dimension(30, 30));
            spacingLabel2.setOpaque(false);
            attendanceEditingPanel.add(spacingLabel2);
        }
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 1;
        c.gridy = 2;
        this.add(attendanceEditingPanel, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // If the source of the button press was the edit button...
        if (e.getSource() == editButton) {
            // Enables all text fields and the combo box to be interacted with by the user.
            paymentAmountField.setEditable(true);
            paymentTypeList.setEnabled(true);
            paymentDayField.setEditable(true);
            paymentMonthField.setEditable(true);
            paymentYearField.setEditable(true);
            paymentTakerField.setEditable(true);
            
            lessonBlockTableModel.setDateEditable(true);
            lessonBlockTable.setModel(lessonBlockTableModel);
            
            // Displays the editing buttons for the JTables attendance column
            addAttendanceEditingPanel();
            
            // Removes the edit button from the button panel
            buttonPanel.remove(editButton);
            buttonPanel.remove(deleteButton);
            
            // Creates the update button and sets its attributes
            updateButton = new JButton("Update");
            updateButton.setPreferredSize(new Dimension(100, 30));
            updateButton.addActionListener(this);
            updateButton.setToolTipText("<html> Click this button to <b> update </b> the lesson block record with the new field values. </html>");
            updateButton.setIcon(new ImageIcon("images/icons/16x16/accept.png"));
            buttonPanel.add(updateButton);
            
            // Creates the cancel button and sets its attributes
            cancelButton = new JButton("Cancel");
            cancelButton.setPreferredSize(new Dimension(100, 30));
            cancelButton.addActionListener(this);
            cancelButton.setToolTipText("<html> Click this button to <b> cancel </b> the editting of the lesson block record and keep the old field values. </html>");
            cancelButton.setIcon(new ImageIcon("images/icons/16x16/cancel.png"));
            buttonPanel.add(cancelButton);
            
            this.updateUI();
        // If the source of the button press was the update button
        } else if (e.getSource() == deleteButton) {
            
            int answer = JOptionPane.showConfirmDialog(null, 
                "<HTML> Do you want to <b> <font color='red'> delete</font> </b> this lesson block?</HTML>"
                        , "Delete Lesson Block?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            // Switch to determine users choice.
            switch (answer) {
                // The user wants to delete the student record from the db.
                case 0: lessonBlockController.deleteLessonBlock(lessonBlockRef);
                        smsBodyPanelRef.addViewIndividualSRPanel(studentRecordRef);
                         break;
                // The user does not want to format the db so don't do anything.
                case 1:  break;
                default: break;
            } 
        }else if (e.getSource() == updateButton) {
            
            // boolean used as a flag if any form field is left empty
            boolean invalidField = true;
            
//            inputVerifier.verify(studentNameField);
//            inputVerifier.verify(paymentDayField);
//            inputVerifier.verify(paymentMonthField);
//            inputVerifier.verify(paymentYearField);
//            inputVerifier.verify(studentTelephoneNoField);
//            inputVerifier.verify(addressLine1Field);
//            inputVerifier.verify(addressLine2Field);
//            inputVerifier.verify(addressCityField);
//            inputVerifier.verify(addressCountyField);
//            inputVerifier.verify(addressPostcodeField);
//            inputVerifier.verify(hasIllnessField);
//            inputVerifier.verify(parentNameField);
            
//            // if any of the textfields backgrounds are not white flag as a field being invalid.
//            if (studentNameField.getBackground() == Color.white && paymentDayField.getBackground() == Color.white 
//                    && paymentMonthField.getBackground() == Color.white && paymentYearField.getBackground() == Color.white
//                        && studentTelephoneNoField.getBackground() == Color.white && addressLine1Field.getBackground() == Color.white
//                            && addressLine2Field.getBackground() == Color.white && addressCityField.getBackground() == Color.white
//                                && addressCountyField.getBackground() == Color.white && addressPostcodeField.getBackground() == Color.white
//                                    && hasIllnessField.getBackground() == Color.white && parentNameField.getBackground() == Color.white) {
//                invalidField = false;
//            }
            
            
            
            // If any form fields were empty display an error message
            if (invalidField == true) {
                JOptionPane.showMessageDialog(null,
                                "A field(s) has been left empty or contains an invalid entry\n"
                                        + "Invalid field(s) highlighted in yellow\n"
                                        + "Please complete the lesson block form correctly.\n",
                                "Error Empty or Invalid Field(s)",
                                JOptionPane.ERROR_MESSAGE);
            } else {
//                studentRecord.setStudentName(studentNameField.getText());
//                studentRecord.setStudentDOB(studentRecordController.formatDOB(paymentDayField.getText(), 
//                                                paymentMonthField.getText(), paymentYearField.getText()));
//                studentRecord.setTelephoneNo(studentTelephoneNoField.getText());
//                studentRecord.getStudentAddress().setAddressLine1(addressLine1Field.getText());
//                studentRecord.getStudentAddress().setAddressLine2(addressLine2Field.getText());
//                studentRecord.getStudentAddress().setCity(addressCityField.getText());
//                studentRecord.getStudentAddress().setCounty(addressCountyField.getText());
//                studentRecord.getStudentAddress().setPostcode(addressPostcodeField.getText());
//                studentRecord.setHasIllness(hasIllnessField.getText());
//                studentRecord.setParentName(parentNameField.getText());
//                
//                Boolean successfullyUpdated = studentRecordController.updateStudentRecord(studentRecord);
//                
//                // If the Student Record was successfully updated 
//                if (successfullyUpdated) {
//                    JOptionPane.showMessageDialog(null,
//                                "Student Record has been successfully updated!",
//                                "Student Record updated successfully!",
//                                JOptionPane.INFORMATION_MESSAGE,
//                                new ImageIcon("images/icons/thumb_up.png"));
//                    exitEditMode();
//                    
//                } else {
//                    // Display error message that the student record could not be updated successfully
//                    JOptionPane.showMessageDialog(null,
//                                "The student record was unsuccessfully updated!",
//                                "Error updating student record",
//                                JOptionPane.ERROR_MESSAGE);
//                }
            }
        // If the source of the button press was the cancel button  
        } else if (e.getSource() == cancelButton) {
            
            // Sets all of the payment detail field values if the lesson payment record exists
            if (lessonBlockRef.getLessonPayment() != null) {
                paymentAmountField.setText(lessonBlockRef.getLessonPayment().getPaymentAmount());
                paymentTypeList.setSelectedItem(lessonBlockRef.getLessonPayment().getPaymentType());
                final String[] paymentDate = lessonBlockController.unformatPaymentDate(lessonBlockRef.getLessonPayment().getPaymentDate());
                paymentDayField.setText(paymentDate[0]);
                paymentMonthField.setText(paymentDate[1]);
                paymentYearField.setText(paymentDate[2]);
                paymentTakerField.setText(lessonBlockRef.getLessonPayment().getPaymentTaker());
            }
            
            paymentAmountField.setEditable(false);
            paymentTypeList.setEnabled(false);
            paymentDayField.setEditable(false);
            paymentMonthField.setEditable(false);
            paymentYearField.setEditable(false);
            paymentTakerField.setEditable(false);
            
            lessonBlockTableModel.setDateEditable(false);
            
            lessonBlockTableModel = new LessonBlockTableModel(convertLessonBlockForTable(), columnNames);
            
            lessonBlockTable.setModel(lessonBlockTableModel);
            
            this.remove(attendanceEditingPanel);
            
            this.remove(buttonPanel);
            
            addButtonPanel();
            
            this.updateUI();
        } else {
            for (int i = 0; i < presentButton.length; i++) {
                if (e.getSource() == presentButton[i]) {
                    lessonBlockTableModel.setValueAt(AttendanceType.PRESENT, i, 1);
                } else if (e.getSource() == absentButton[i]) {
                    lessonBlockTableModel.setValueAt(AttendanceType.ABSENT, i, 1);
                } else if (e.getSource() == funSwimButton[i]) {
                    lessonBlockTableModel.setValueAt(AttendanceType.FUN_SWIM, i, 1);
                } else if (e.getSource() == funSwimTakenButton[i]) {
                    lessonBlockTableModel.setValueAt(AttendanceType.FUN_SWIM_TAKEN, i, 1);
                } else if (e.getSource() == clearAttendanceButton[i]) {
                    lessonBlockTableModel.setValueAt(null, i, 1);
                }
            }
        }
    }
}
