package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LessonBlockController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LessonBlockDateInputVerifier;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LessonBlockPaymentInputVerifier;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.LessonPaymentController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.AttendanceType;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonPayment;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.PaymentType;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;

/**
 * This class loads an individual lesson block record onto this lesson block panel.
 */
public class LessonBlockPanel extends JPanel implements ActionListener {
    
    /**
     * The lesson block to load onto the panel.
     */
    LessonBlock lessonBlockRef;
    
    /**
     * The number of the lesson block.
     */
    int lessonBlockNumberRef;
    
    /**
     * The swimrite management suite body panel referenced for displaying an individual student record.
     */
    SMSBodyPanel smsBodyPanelRef;
    
    /**
     * The student record sent as a param when reloading the view individual student record after deletion of this lb.
     */
    StudentRecord studentRecordRef;
    
    /**
     * The controller to interact with the lesson block table in the database.
     */
    LessonBlockController lessonBlockController = new LessonBlockController();
    
    /**
     * The controller to create, edit/update lesson payments from the database.
     */
    LessonPaymentController lessonPaymentController = new LessonPaymentController();
    
    /**
     * The input verifier used for the lesson payment form.
     */
    LessonBlockPaymentInputVerifier lessonBlockPaymentInputVerifier;
    
    /**
     * The input verifier used for the dates in the lesson block table.
     */
    LessonBlockDateInputVerifier lessonBlockDateInputVerifier;
    
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
    public static String paymentYearFieldName = "Payment Year Field";
    
    /**
     * The payment taker field text field name needed for referencing in the input verifier.
     */
    public static String paymentTakerFieldName = "Payment Taker Field";
    
    
    
    /**
     * The text field for the payment amount for the lesson payment.
     */
    JTextField paymentAmountField;
    
    /**
     * The combo boc for the different possible payment types for the lesson payment.
     */
    JComboBox paymentTypeList;
    
    /**
     * The text field for the payment date (day) for the lesson payment.
     */
    JTextField paymentDayField;
    
    /**
     * The text field for the payment date (month) for the lesson payment.
     */
    JTextField paymentMonthField;
    
    /**
     * The text field for the payment date (year) for the lesson payment.
     */
    JTextField paymentYearField;
    
    /**
     * The text field for the payment taker for the lesson payment.
     */
    JTextField paymentTakerField;
    
    
    
    /**
     * An array of String to hold the column names for the lesson block table.
     */
    String[] columnNames;
    
    /**
     * The default cell editor for the lesson block attendance table.
     */
    DefaultCellEditor lessonBlockTableEditor;
    
    /**
     * The JTable to hold the lesson block attendance and date columns.
     */
    JTable lessonBlockTable;
    
    /**
     * The table model for the lesson block table.
     */
    LessonBlockTableModel lessonBlockTableModel;
            
    
    
    /**
     * The button panel to hold the edit, delete, update and cancel buttons.
     */
    JPanel buttonPanel;
    
    /**
     * The edit button to start editing a lesson block and lesson payment for that block.
     */
    JButton editButton;
    
    /**
     * The delete button to delete the lesson block record from the database.
     */
    JButton deleteButton;
    
    /**
     * The update button to update the lesson block and lesson payment for that block.
     */
    JButton updateButton;
    
    /**
     * The cancel button to cancel editing for the lesson block and lesson payment for that block.
     */
    JButton cancelButton;
    
    
    
    /**
     * The attendance editing button panel to hold the buttons used for editing the attendance of the lesson block.
     */
    JPanel attendanceEditingPanel;
    
    /**
     * The array for all the buttons to change attendance of the appropriate attendance row to present.
     */
    JButton[] presentButton;
    
    /**
     * The array for all the buttons to change attendance of the appropriate attendance row to absent.
     */
    JButton[] absentButton;
    
    /**
     * The array for all the buttons to change attendance of the appropriate attendance row to fun swim.
     */
    JButton[] funSwimButton;
    
    /**
     * The array for all the buttons to change attendance of the appropriate attendance row to fun swim taken.
     */
    JButton[] funSwimTakenButton;
    
    /**
     * The array for all the buttons to change attendance of the appropriate attendance row to null/clear it.
     */
    JButton[] clearAttendanceButton;
    
    
    
    /**
     * Adds the title for the lesson block, the lesson payment form and lesson block table to this panel.
     * @param lessonBlock The lesson block to load onto the panel.
     * @param lessonBlockNumber The number of this lesson block.
     * @param smsBodyPanel The reference to the body panel to change panels.
     * @param studentRecord The student record associated with this lesson block.
     */
    public LessonBlockPanel(LessonBlock lessonBlock, int lessonBlockNumber, SMSBodyPanel smsBodyPanel, StudentRecord studentRecord) {
        
        // The lesson block to load onto this lesson block panel.
        lessonBlockRef = lessonBlock;
        
        // The number that this lesson block is.
        lessonBlockNumberRef = lessonBlockNumber;
        
        // Initialises the sms Body Panel reference used for changing panels on the body panel
        smsBodyPanelRef = smsBodyPanel;
        
        // The student record associated with this lesson block
        studentRecordRef = studentRecord;
        
        // The input verifier for the lesson payment form
        lessonBlockPaymentInputVerifier = new LessonBlockPaymentInputVerifier();
        
        // The input verifier for the lesson block date column in the lesson block table.
        lessonBlockDateInputVerifier = new LessonBlockDateInputVerifier();
        
        // Initialises the grid bag layout constraint
        c = new GridBagConstraints();
        
        // sets the ViewIndividualSRPanel JPanels default attributes
        this.setPreferredSize(new Dimension(463, 550));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        
        // Adds the title of this lesson block to this panel
        addTitleLabel();
        
        // Adds the lesson payment form to this panel
        addLessonPaymentForm();
        
        // Adds the lesson block table to this form
        addLessonBlockTable();
        
        // Adds the button panel to contain edit, delete, update and cancel buttons.
        addButtonPanel();
    }
    
    /**
     * Adds a label containing the title of this lesson block panel to this panel.
     */
    public void addTitleLabel() {
        // creates, initialises and sets the attributes for the title label
        final JLabel titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(450, 30));
        titleLabel.setOpaque(true);
        titleLabel.setVisible(true);
        titleLabel.setBackground(Color.white);
        titleLabel.setFont(titleLabelFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Sets the text for the label
        titleLabel.setText("Lesson Block No. " + lessonBlockNumberRef);
        
        // The coordinates for where to add this component to the layout.
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(titleLabel, c);
        
        // Resets the grid width for other components added
        c.gridwidth = 1;
    }
    
    /**
     * Adds the lesson payment form components to the lesson payment form panel to this panel.
     */
    public void addLessonPaymentForm() {
        /**
         * The JPanel to hold all payment detail labels and text fields and spacing components.
         */
        final JPanel lessonPaymentFormPanel = new JPanel();
        lessonPaymentFormPanel.setPreferredSize(new Dimension(450, 90));
        lessonPaymentFormPanel.setVisible(true);
        lessonPaymentFormPanel.setBackground(Color.white);
        
        
        
        /**
         * Adds the payment amount label and text field to the payment details panel.
         */
        final JLabel paymentAmountLabel = new JLabel("Payment Amount:   £");
        paymentAmountLabel.setPreferredSize(new Dimension(125, 20));
        setLabelAttributes(paymentAmountLabel);
        lessonPaymentFormPanel.add(paymentAmountLabel);
        
        paymentAmountField = new JTextField(4);
        paymentAmountField.setName(paymentAmountFieldName);
        paymentAmountField.setPreferredSize(new Dimension(30, 20));
        setTextFieldAttributes(paymentAmountField);
        lessonPaymentFormPanel.add(paymentAmountField);
        
        
        
        /**
         * Adds the Payment Type label and payment type combo box to the payment details panel.
         */
        final JLabel paymentTypeLabel = new JLabel("Payment Type:");
        paymentTypeLabel.setPreferredSize(new Dimension(90, 20));
        setLabelAttributes(paymentTypeLabel);
        lessonPaymentFormPanel.add(paymentTypeLabel);
        
        paymentTypeList = new JComboBox(PaymentType.values());
        paymentTypeList.setFont(textFont);
        paymentTypeList.setEnabled(false);
        lessonPaymentFormPanel.add(paymentTypeList);
        
        
        
        /**
         * Adds the payment date label to this layout and the payment date fields onto the payment details panel.
         */
        final JLabel paymentDateLabel = new JLabel("Payment Date:");
        paymentDateLabel.setPreferredSize(new Dimension(100, 20));
        setLabelAttributes(paymentDateLabel);
        lessonPaymentFormPanel.add(paymentDateLabel);
        
        // Payment day for date field
        paymentDayField = new JTextField(2);
        paymentDayField.setName(paymentDayFieldName);
        paymentDayField.setPreferredSize(new Dimension(20, 20));
        setTextFieldAttributes(paymentDayField);
        lessonPaymentFormPanel.add(paymentDayField);
        
        
        // Label to hold a '/' SLASH inbetween the day and month of the payment date
        final JLabel firstSlashLabel = new JLabel("<html><span style='font-size:14px'>/</span></html>");
        firstSlashLabel.setPreferredSize(new Dimension(10, 20));
        firstSlashLabel.setBackground(Color.white);
        firstSlashLabel.setOpaque(true);
        firstSlashLabel.setVisible(true);
        lessonPaymentFormPanel.add(firstSlashLabel);
        
        // Payment month for date field
        paymentMonthField = new JTextField(2);
        paymentMonthField.setName(paymentMonthFieldName);
        paymentMonthField.setPreferredSize(new Dimension(20, 20));
        setTextFieldAttributes(paymentMonthField);
        lessonPaymentFormPanel.add(paymentMonthField);
        
        
        // Label to hold a '/' SLASH inbetween the month and year of the payment date 
        final JLabel secondSlashLabel = new JLabel("<html><span style='font-size:14px'>/</span></html>");
        secondSlashLabel.setPreferredSize(new Dimension(10, 20));
        secondSlashLabel.setBackground(Color.white);
        secondSlashLabel.setOpaque(true);
        secondSlashLabel.setVisible(true);
        lessonPaymentFormPanel.add(secondSlashLabel);
        
        // Payment year for dat field
        paymentYearField = new JTextField(3);
        paymentYearField.setName(paymentYearFieldName);
        paymentYearField.setPreferredSize(new Dimension(25, 20));
        setTextFieldAttributes(paymentYearField);
        lessonPaymentFormPanel.add(paymentYearField);
        
        
        
        // Adds a component to space out components on the payment details panel
        final JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(170, 20));
        spacingLabel1.setOpaque(false);
        lessonPaymentFormPanel.add(spacingLabel1);
        
        
        
        /**
         * Adds the payment taker label and text field to the payment details panel
         */
        final JLabel paymentTakerLabel = new JLabel("Payment Taker: ");
        paymentTakerLabel.setPreferredSize(new Dimension(100, 20));
        setLabelAttributes(paymentTakerLabel);
        lessonPaymentFormPanel.add(paymentTakerLabel);
        
        // The payment taker field
        paymentTakerField = new JTextField(15);
        paymentTakerField.setName(paymentTakerFieldName);
        paymentTakerField.setPreferredSize(new Dimension(50, 20));
        setTextFieldAttributes(paymentTakerField);
        lessonPaymentFormPanel.add(paymentTakerField);
        
        
        
         // Adds a component to space out components on the payment details panel
        final JLabel spacingLabel2 = new JLabel();
        spacingLabel2.setPreferredSize(new Dimension(150, 20));
        spacingLabel2.setOpaque(false);
        lessonPaymentFormPanel.add(spacingLabel2);
        
        
        
        // Sets all of the payment detail field values if the lesson payment record exists
        if (lessonBlockRef.getLessonPayment() != null) {
            paymentAmountField.setText(lessonBlockRef.getLessonPayment().getPaymentAmount());
            paymentTypeList.setSelectedItem(lessonBlockRef.getLessonPayment().getPaymentType());
            final String[] paymentDate = lessonPaymentController.unformatPaymentDate(lessonBlockRef.getLessonPayment().getPaymentDate());
            paymentDayField.setText(paymentDate[0]);
            paymentMonthField.setText(paymentDate[1]);
            paymentYearField.setText(paymentDate[2]);
            paymentTakerField.setText(lessonBlockRef.getLessonPayment().getPaymentTaker());
        }
        
        
        
        // The coordinates for where to add the lesson payment form panel component to the layout.
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(lessonPaymentFormPanel, c);
        
        // Resets the grid width for other components added
        c.gridwidth = 1;
    }
    
    /**
     * Sets the default attributes for lesson payment labels on the lesson payment form panel.
     * @param label the label to set the attributes for
     */
    public void setLabelAttributes(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(textFont);
        label.setBackground(Color.white);
        label.setOpaque(true);
        label.setVisible(true);
    }
    
    /**
     * Sets the default attributes for the text fields on the lesson payment form panel.
     * @param textField the text field to set the attributes for
     */
    public void setTextFieldAttributes(JTextField textField) {
        textField.setInputVerifier(lessonBlockPaymentInputVerifier);
        textField.setEditable(false);
        textField.setFont(textFont);
    }
    
    /**
     * Adds the lesson block table to this layout.
     */
    public void addLessonBlockTable() {
        
        // The an array of column names for the lesson block attendance table
        columnNames = new String[]{" Lesson Date",
                                " Attendance"};
        
        // initialises the adapted table editor for the lesson block table to edit the date column
        createLessonBlockTableEditor();
        
        // Creates a new lesson block table using the table editor (will contain table data) and the array of column names
        lessonBlockTable = new JTable(convertLessonBlockForTable(), columnNames) {
            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                // Gets the column index from the table
                final int modelColumn = convertColumnIndexToModel(column);

                // If the index is 0 then this is the date column and return the lesso block table editor to endable date editing
                if (modelColumn == 0) {
                    return lessonBlockTableEditor;
                } else {
                    // Otherwise return the default table cell editor
                    return super.getCellEditor(row, column);
                }
            }
            
            // Renders the colours of the rows to have interleaving colours
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
        
        // The lesson block table model using data and column names
        lessonBlockTableModel = new LessonBlockTableModel(convertLessonBlockForTable(), columnNames);
        
        // Sets the lesson blocks table model (to load data and column values)
        lessonBlockTable.setModel(lessonBlockTableModel);
        
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
        
        // Initializes the scroll pane to hold the lesson block attendance table, setting attributes
        final JScrollPane tableScrollPane = new JScrollPane(lessonBlockTable);
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
         * The two dimensional array to hold the lesson block attendance details.
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
    
    /**
     * initializes the table editor for the lesson block table.
     * @return 
     */
    private void createLessonBlockTableEditor() {
        // initialises the table editor delcaring it will use a text field
        lessonBlockTableEditor = new DefaultCellEditor(new JTextField()) {
            {
                // Sets the input verifier for the date column
                getComponent().setInputVerifier(lessonBlockDateInputVerifier);
            }

            // Determines whether the table cell should yeild depending on the validity of the data entered
            @Override
            public boolean stopCellEditing() {
                if (!lessonBlockDateInputVerifier.shouldYieldFocus(getComponent())) {
                    return true;
                }
                return super.stopCellEditing();
            }

            // Gets the cell component in the table that is being edited
            @Override
            public JTextField getComponent() {
                return (JTextField) super.getComponent();
            }
        };
    }
    
    /**
     * Adds the button panel to this layout that will hold the edit, delete, update and cancel buttons.
     */
    public void addButtonPanel() {
        // The button panel to hold the edit, delete, update and cancel buttons
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(210, 35));
        buttonPanel.setBackground(Color.white);
        buttonPanel.setVisible(true);
        
        // Initialises the edit button with its attributes (inc button tooltip and icon)
        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(100, 30));
        editButton.addActionListener(this);
        editButton.setToolTipText("<html> Click this button to <b> edit </b> the lesson block records. </html>");
        editButton.setIcon(new ImageIcon("images/icons/bullet_edit.png"));
        
        // Adds the edit button to the button panel
        buttonPanel.add(editButton);
        
        // Initialises the delete button with its attributes (inc button tooltip and icon)
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.addActionListener(this);
        deleteButton.setToolTipText("<html> Click this button to <b> delete </b> the lesson block record. </html>");
        deleteButton.setIcon(new ImageIcon("images/icons/16x16/delete.png"));
        
        // Adds the delete button to the button panel
        buttonPanel.add(deleteButton);
        
        // The coordinates for where to add the button panel component to the layout.
        c.gridx = 1;
        c.gridy = 3;
        this.add(buttonPanel, c);
    }
    
    /**
     * Adds the attendance editing panel to this layout so that attendance types for the lesson block can be edited.
     */
    public void addAttendanceEditingPanel() {
        // The panel to hold the attendance editing buttons
        attendanceEditingPanel = new JPanel();
        attendanceEditingPanel.setPreferredSize(new Dimension(210, 395));
        attendanceEditingPanel.setBackground(Color.white);
        attendanceEditingPanel.setVisible(true);
        
        // Adds a spacing component to this layout (space next to the tables column title)
        final JLabel spacingLabel1 = new JLabel();
        spacingLabel1.setPreferredSize(new Dimension(210, 32));
        spacingLabel1.setOpaque(false);
        attendanceEditingPanel.add(spacingLabel1);
        
        // Initialises the array to hold the buttons to edit the attendance type to present
        presentButton = new JButton[10];
        
        // Initialises the array to hold the buttons to edit the attendance type to absent
        absentButton = new JButton[10];
        
        // Initialises the array to hold the buttons to edit the attendance type to fun swim
        funSwimButton = new JButton[10];
        
        // Initialises the array to hold the buttons to edit the attendance type to fun swim
        funSwimTakenButton = new JButton[10];
        
        // Initialises the array to hold the buttons to edit the attendance type to null
        clearAttendanceButton = new JButton[10];
        
        // Iterates through the amount of rows in the table to add all the attendance editing buttons
        for (int i = 0; i < lessonBlockTable.getRowCount(); i++) {
            // Initialises a present button for that row and adds it to the attendance editing panel
            presentButton[i] = new JButton();
            presentButton[i].setPreferredSize(new Dimension(30, 30));
            presentButton[i].addActionListener(this);
            presentButton[i].setToolTipText("<html> Click this button to mark that the student was <b> present </b> for this lesson. </html>");
            presentButton[i].setIcon(new ImageIcon("images/icons/16x16/accept.png"));
            attendanceEditingPanel.add(presentButton[i]);
            
            // Initialises a absent button for that row and adds it to the attendance editing panel
            absentButton[i] = new JButton();
            absentButton[i].setPreferredSize(new Dimension(30, 30));
            absentButton[i].addActionListener(this);
            absentButton[i].setToolTipText("<html> Click this button to mark that the student was <b> absent </b> for this lesson. </html>");
            absentButton[i].setIcon(new ImageIcon("images/icons/16x16/cancel.png"));
            attendanceEditingPanel.add(absentButton[i]);
            
            // Initialises a fun swim button for that row and adds it to the attendance editing panel
            funSwimButton[i] = new JButton();
            funSwimButton[i].setPreferredSize(new Dimension(30, 30));
            funSwimButton[i].addActionListener(this);
            funSwimButton[i].setToolTipText("<html> Click this button to mark that the student was granted a <b> fun swim </b> for this lesson. </html>");
            funSwimButton[i].setIcon(new ImageIcon("images/icons/16x16/fun_swim.png"));
            attendanceEditingPanel.add(funSwimButton[i]);
            
            // Initialises a fun swim taken button for that row and adds it to the attendance editing panel
            funSwimTakenButton[i] = new JButton();
            funSwimTakenButton[i].setPreferredSize(new Dimension(30, 30));
            funSwimTakenButton[i].addActionListener(this);
            funSwimTakenButton[i].setToolTipText("<html> Click this button to mark that the student was granted a fun swim, but it has been <b> taken </b> for this lesson. </html>");
            funSwimTakenButton[i].setIcon(new ImageIcon("images/icons/16x16/fun_swim_taken.png"));
            attendanceEditingPanel.add(funSwimTakenButton[i]);
            
            // Initialises a clear button for that row and adds it to the attendance editing panel
            clearAttendanceButton[i] = new JButton();
            clearAttendanceButton[i].setPreferredSize(new Dimension(30, 30));
            clearAttendanceButton[i].addActionListener(this);
            clearAttendanceButton[i].setToolTipText("<html> Click this button to <b> clear </b> the attendance for this lesson. </html>");
            clearAttendanceButton[i].setIcon(new ImageIcon("images/icons/16x16/blank.png"));
            attendanceEditingPanel.add(clearAttendanceButton[i]);
            
            // Adds a spacing label (at the end of this row) so that the next row of buttons starts on the next row.
            final JLabel spacingLabel2 = new JLabel();
            spacingLabel2.setPreferredSize(new Dimension(30, 30));
            spacingLabel2.setOpaque(false);
            attendanceEditingPanel.add(spacingLabel2);
        }
        
        // The coordinates for where to add the attendance editing panel component to this layout.
        c.gridx = 1;
        c.gridy = 2;
        this.add(attendanceEditingPanel, c);
    }
    
    /**
     * Method to enter the lesson block panel into an editable mode.
     */
    public void enterEditingMode() {
        // Enables all text fields and the combo box to be interacted with by the user.
            paymentAmountField.setEditable(true);
            paymentTypeList.setEnabled(true);
            paymentDayField.setEditable(true);
            paymentMonthField.setEditable(true);
            paymentYearField.setEditable(true);
            paymentTakerField.setEditable(true);
            
            // Enables the date column in the lesson block table to be editable
            lessonBlockTableModel.setDateEditable(true);
            
            // Displays the attendance editing buttons for the JTables attendance column
            addAttendanceEditingPanel();
            
            // Removes the edit & delete button from the button panel
            buttonPanel.remove(editButton);
            buttonPanel.remove(deleteButton);
            
            // Creates the update button and sets its attributes
            updateButton = new JButton("Update");
            updateButton.setPreferredSize(new Dimension(100, 30));
            updateButton.addActionListener(this);
            updateButton.setToolTipText("<html> Click this button to <b> update </b> the lesson block record with the new field values. </html>");
            updateButton.setIcon(new ImageIcon("images/icons/16x16/accept.png"));
            
            // Adds the update button to the button panel
            buttonPanel.add(updateButton);
            
            // Creates the cancel button and sets its attributes
            cancelButton = new JButton("Cancel");
            cancelButton.setPreferredSize(new Dimension(100, 30));
            cancelButton.addActionListener(this);
            cancelButton.setToolTipText("<html> Click this button to <b> cancel </b> the editting of the lesson block record and keep the old field values. </html>");
            cancelButton.setIcon(new ImageIcon("images/icons/16x16/cancel.png"));
            
            // Adds the cancel button to the button panel
            buttonPanel.add(cancelButton);
            
            // Updates the UI after panel changes
            this.updateUI();
    }
    
    /**
     * Attempts to update the lesson block and lesson payment for that block with the values supplied.
     */
    public void attemptLessonBlockUpdate() {
        // boolean used as a flag if any form field is left empty
        boolean invalidField = true;

        // Verifies all text fields on the lesson payment form
        lessonBlockPaymentInputVerifier.verify(paymentAmountField);
        lessonBlockPaymentInputVerifier.verify(paymentDayField);
        lessonBlockPaymentInputVerifier.verify(paymentMonthField);
        lessonBlockPaymentInputVerifier.verify(paymentYearField);
        lessonBlockPaymentInputVerifier.verify(paymentTakerField);

        // if any of the textfields backgrounds are not white flag as a field(s) being invalid.
        if (paymentAmountField.getBackground() == Color.white && paymentDayField.getBackground() == Color.white
                && paymentMonthField.getBackground() == Color.white && paymentYearField.getBackground() == Color.white
                && paymentTakerField.getBackground() == Color.white) {
            invalidField = false;
        }

        // If any form fields were invalid display an error message
        if (invalidField == true) {
            JOptionPane.showMessageDialog(null,
                    "A field(s) has been left empty or contains an invalid entry\n"
                    + "Invalid field(s) highlighted in yellow\n"
                    + "Please complete the lesson block form correctly.\n",
                    "Error Empty or Invalid Field(s)",
                    JOptionPane.ERROR_MESSAGE);
            // Otherwise the fields contain valid values
        } else {

            // If the lesson payment for the lesson block is not null set the new values and update in db
            if (lessonBlockRef.getLessonPayment() != null) {
                lessonBlockRef.getLessonPayment().setPaymentAmount(paymentAmountField.getText());
                lessonBlockRef.getLessonPayment().setPaymentType((PaymentType) paymentTypeList.getSelectedItem());
                lessonBlockRef.getLessonPayment().setPaymentDate(
                        lessonPaymentController.formatpaymentDate(paymentDayField.getText(),
                                paymentMonthField.getText(), paymentYearField.getText()));
                lessonBlockRef.getLessonPayment().setPaymentTaker(paymentTakerField.getText());

                lessonPaymentController.update(lessonBlockRef.getLessonPayment());
                // Otherwise create a lesson payment for the lesson block with the set values and add to db.
            } else {
                // Creates a lesson payment with the field values given by the user.
                final LessonPayment lessonPayment = new LessonPayment(paymentAmountField.getText(),
                        (PaymentType) paymentTypeList.getSelectedItem(),
                        lessonPaymentController.formatpaymentDate(paymentDayField.getText(),
                                paymentMonthField.getText(), paymentYearField.getText()),
                        paymentTakerField.getText());

                // Creates a record of that lesson payment in the database
                lessonPaymentController.create(lessonPayment);

                // Sets the lesson payment of the lesson block to the newly created instance
                lessonBlockRef.setLessonPayment(lessonPayment);
            }

            // Loops through the tables rows
            for (int i = 0; i < lessonBlockTable.getRowCount(); i++) {
                // Checks if the date value is not equal to null
                if (lessonBlockTableModel.getValueAt(i, 0) != null) {
                    // Checks which row the value came from and updates/sets the appropriate numbered lesson date
                    if (i == 0) {
                        lessonBlockRef.setLesson1Date(lessonBlockTableModel.getValueAt(0, 0).toString());
                    } else if (i == 1) {
                        lessonBlockRef.setLesson2Date(lessonBlockTableModel.getValueAt(1, 0).toString());
                    } else if (i == 2) {
                        lessonBlockRef.setLesson3Date(lessonBlockTableModel.getValueAt(2, 0).toString());
                    } else if (i == 3) {
                        lessonBlockRef.setLesson4Date(lessonBlockTableModel.getValueAt(3, 0).toString());
                    } else if (i == 4) {
                        lessonBlockRef.setLesson5Date(lessonBlockTableModel.getValueAt(4, 0).toString());
                    } else if (i == 5) {
                        lessonBlockRef.setLesson6Date(lessonBlockTableModel.getValueAt(5, 0).toString());
                    } else if (i == 6) {
                        lessonBlockRef.setLesson7Date(lessonBlockTableModel.getValueAt(6, 0).toString());
                    } else if (i == 7) {
                        lessonBlockRef.setLesson8Date(lessonBlockTableModel.getValueAt(7, 0).toString());
                    } else if (i == 8) {
                        lessonBlockRef.setLesson9Date(lessonBlockTableModel.getValueAt(8, 0).toString());
                    } else if (i == 9) {
                        lessonBlockRef.setLesson10Date(lessonBlockTableModel.getValueAt(9, 0).toString());
                    }
                    // Otherwise the value is equal to null
                } else {
                    // Checks which row the value came from and updates/sets the appropriate numbered lesson date
                    if (i == 0) {
                        lessonBlockRef.setLesson1Date(null);
                    } else if (i == 1) {
                        lessonBlockRef.setLesson2Date(null);
                    } else if (i == 2) {
                        lessonBlockRef.setLesson3Date(null);
                    } else if (i == 3) {
                        lessonBlockRef.setLesson4Date(null);
                    } else if (i == 4) {
                        lessonBlockRef.setLesson5Date(null);
                    } else if (i == 5) {
                        lessonBlockRef.setLesson6Date(null);
                    } else if (i == 6) {
                        lessonBlockRef.setLesson7Date(null);
                    } else if (i == 7) {
                        lessonBlockRef.setLesson8Date(null);
                    } else if (i == 8) {
                        lessonBlockRef.setLesson9Date(null);
                    } else if (i == 9) {
                        lessonBlockRef.setLesson10Date(null);
                    }
                }

                // Checks if the attendance type value is not equal to null
                if (lessonBlockTableModel.getValueAt(i, 1) != null) {
                    // Sets attendance type to null
                    AttendanceType attendanceType = null;

                    // Sets the attendance type to the appropriate value dependant on what the cell currently contains
                    if (lessonBlockTableModel.getValueAt(i, 1).toString().equals("images/icons/16x16/accept.png")) {
                        attendanceType = AttendanceType.PRESENT;
                    } else if (lessonBlockTableModel.getValueAt(i, 1).toString().equals("images/icons/16x16/cancel.png")) {
                        attendanceType = AttendanceType.ABSENT;
                    } else if (lessonBlockTableModel.getValueAt(i, 1).toString().equals("images/icons/16x16/fun_swim.png")) {
                        attendanceType = AttendanceType.FUN_SWIM;
                    } else if (lessonBlockTableModel.getValueAt(i, 1).toString().equals("images/icons/16x16/fun_swim_taken.png")) {
                        attendanceType = AttendanceType.FUN_SWIM_TAKEN;
                    }

                    // Checks which row the value came from and updates/sets the appropriate Numbered lesson attendance
                    if (i == 0) {
                        lessonBlockRef.setLesson1Attendance(attendanceType);
                    } else if (i == 1) {
                        lessonBlockRef.setLesson2Attendance(attendanceType);
                    } else if (i == 2) {
                        lessonBlockRef.setLesson3Attendance(attendanceType);
                    } else if (i == 3) {
                        lessonBlockRef.setLesson4Attendance(attendanceType);
                    } else if (i == 4) {
                        lessonBlockRef.setLesson5Attendance(attendanceType);
                    } else if (i == 5) {
                        lessonBlockRef.setLesson6Attendance(attendanceType);
                    } else if (i == 6) {
                        lessonBlockRef.setLesson7Attendance(attendanceType);
                    } else if (i == 7) {
                        lessonBlockRef.setLesson8Attendance(attendanceType);
                    } else if (i == 8) {
                        lessonBlockRef.setLesson9Attendance(attendanceType);
                    } else if (i == 9) {
                        lessonBlockRef.setLesson10Attendance(attendanceType);
                    }
                    // Otherwise the value is equal to null
                } else {
                    // Checks which row the value came from and updates/sets the appropriate Numbered lesson attendance.
                    if (i == 0) {
                        lessonBlockRef.setLesson1Attendance(null);
                    } else if (i == 1) {
                        lessonBlockRef.setLesson2Attendance(null);
                    } else if (i == 2) {
                        lessonBlockRef.setLesson3Attendance(null);
                    } else if (i == 3) {
                        lessonBlockRef.setLesson4Attendance(null);
                    } else if (i == 4) {
                        lessonBlockRef.setLesson5Attendance(null);
                    } else if (i == 5) {
                        lessonBlockRef.setLesson6Attendance(null);
                    } else if (i == 6) {
                        lessonBlockRef.setLesson7Attendance(null);
                    } else if (i == 7) {
                        lessonBlockRef.setLesson8Attendance(null);
                    } else if (i == 8) {
                        lessonBlockRef.setLesson9Attendance(null);
                    } else if (i == 9) {
                        lessonBlockRef.setLesson10Attendance(null);
                    }
                }
            }

            // Updates the lesson block with the updated values
            lessonBlockController.update(lessonBlockRef);

            // Disables all text fields and the combo box from interaction by the user.
            paymentAmountField.setEditable(false);
            paymentTypeList.setEnabled(false);
            paymentDayField.setEditable(false);
            paymentMonthField.setEditable(false);
            paymentYearField.setEditable(false);
            paymentTakerField.setEditable(false);

            // Disables the date column in the lesson block table from being editable
            lessonBlockTableModel.setDateEditable(false);

            // Removes the attendance editing panel to exit editing mode
            this.remove(attendanceEditingPanel);

            // Removes the button panel containing the update & cancel buttons
            this.remove(buttonPanel);

            // Adds the button panel back with the edit & delete buttons
            addButtonPanel();

            // Updates the lesson block panel after panel changes
            this.updateUI();
        }
    }
    
    /**
     * Method to exit the lesson block panel from the editable mode.
     */
    public void exitEditingMode() {
        // Sets all of the payment detail field values back to their original values if the lesson payment record exists
        if (lessonBlockRef.getLessonPayment() != null) {
            paymentAmountField.setText(lessonBlockRef.getLessonPayment().getPaymentAmount());
            paymentTypeList.setSelectedItem(lessonBlockRef.getLessonPayment().getPaymentType());
            final String[] paymentDate = lessonPaymentController.unformatPaymentDate(lessonBlockRef.getLessonPayment().getPaymentDate());
            paymentDayField.setText(paymentDate[0]);
            paymentMonthField.setText(paymentDate[1]);
            paymentYearField.setText(paymentDate[2]);
            paymentTakerField.setText(lessonBlockRef.getLessonPayment().getPaymentTaker());
        }

        // Sets the background colour of all the text fields back to white
        paymentAmountField.setBackground(Color.white);
        paymentTypeList.setBackground(Color.white);
        paymentDayField.setBackground(Color.white);
        paymentMonthField.setBackground(Color.white);
        paymentYearField.setBackground(Color.white);
        paymentTakerField.setBackground(Color.white);

        // Disables all text fields and the combo box from interaction by the user.
        paymentAmountField.setEditable(false);
        paymentTypeList.setEnabled(false);
        paymentDayField.setEditable(false);
        paymentMonthField.setEditable(false);
        paymentYearField.setEditable(false);
        paymentTakerField.setEditable(false);

        // Disables the date column in the lesson block table from being editable
        lessonBlockTableModel.setDateEditable(false);

        // Resets the data held within the lesson block table
        lessonBlockTableModel = new LessonBlockTableModel(convertLessonBlockForTable(), columnNames);

        // Resets the table model for the table with the origional values
        lessonBlockTable.setModel(lessonBlockTableModel);

        // Removes the attendance editing panel to exit editing mode
        this.remove(attendanceEditingPanel);

        // Removes the button panel containing the update & cancel buttons
        this.remove(buttonPanel);

        // Adds the button panel back with the edit & delete buttons
        addButtonPanel();

        // Updates the lesson block panel after panel changes
        this.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // If the source of the button press was the edit button...
        if (e.getSource() == editButton) {
            
            // Calls method to enter the lesson block panel into editing mode
            enterEditingMode();
            
        // If the source of the button press was the update button
        } else if (e.getSource() == deleteButton) {
            // Confirms with the user that they want to delete this lesson block
            final int answer = JOptionPane.showConfirmDialog(null, 
                "<HTML> Do you want to <b> <font color='red'> delete</font> </b> Lesson Block No. " + lessonBlockNumberRef + "?</HTML>"
                        , "Delete Lesson Block?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            // Switch to determine users choice.
            switch (answer) {
                // The user wants to delete the lesson block from the db.
                case 0: lessonBlockController.delete(lessonBlockRef);
                            
                        // re-loads the panel to display the individual student again
                        smsBodyPanelRef.addViewIndividualSRPanel(studentRecordRef);
                         break;
                // The user does not want to format the db so don't do anything.
                case 1:  break;
                default: break;
            } 
        // If the source of the button press was the update button
        } else if (e.getSource() == updateButton) {
            
            // Calls the method to attempt to update the lesson block and lesson payment with new values
            attemptLessonBlockUpdate();
            
        // If the source of the button press was the cancel button  
        } else if (e.getSource() == cancelButton) {
         
            // Calls the method to exit editing mode for the lesson block panel
            exitEditingMode();
            
        // Otherwise the action event came from one of the attendance editing buttons
        } else {
            // Loops through the amount of the present type of button (10) (all buttons will have the same amount)
            for (int i = 0; i < presentButton.length; i++) {
                // Checks where the source came from and updates the appropriate cell with the appropriate value
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
