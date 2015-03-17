package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 * A Table designed specifically to hold student records that attend a swimming class details.
 */
public class SwimmingClassSRTable extends JTable {
    
    /**
     * The font for all headers in the table.
     */
    private final Font tableHeaderFont = new Font("Arial", Font.PLAIN, 18);
    
    /**
     * The font for all records/rows in the table.
     */
    private final Font tableRecordFont = new Font("Arial", Font.PLAIN, 16);
       
    /**
     * The colour used for the headers background.
     */
    private final Color smsBlue = new Color(172, 172, 255);
        
    /**
     * The light gray colour for each alternative row in the table.
     */
    private final Color alternateRowColor = new Color(211, 211, 211);
        
    /**
     * Array to hold each of the column tool tips for the table headers.
     */
    private final String[] columnToolTips = {"The name of the student",
                                        "The date of birth of the student",
                                        "The current swimming/abililty level of the student"};
    
    /**
     * Constructor to create the specifically design swimming class student records table.
     * @param model the table model (inc. data and table column headings)
     */
    public SwimmingClassSRTable(TableModel model) {
        
        // Call to super with the Table Model containing the data and column headings
        super(model);
        
        //Sets the max size of the table until scrolling is used
        this.setPreferredScrollableViewportSize(new Dimension(1200, 250));
        // Makes sure the table is always big enough to fill the container
        this.setFillsViewportHeight(true);
        // Hides the grid lines both horizontal and vertical
        this.setShowGrid(false);
        // Makes sure only one row is selectable at a time
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Sets the font of all row/records in the table
        this.setFont(tableRecordFont);
        // Sets the height of the rows
        this.setRowHeight(40);
        // Sets the font of the headers
        this.getTableHeader().setFont(tableHeaderFont);
        // Sets the background colour for the headers row
        this.getTableHeader().setBackground(smsBlue);
        // Sets the size of the header row
        this.getTableHeader().setPreferredSize(new Dimension(300, 50));
        
        // sets the width of each column in the table
        TableColumn column;
        //for (int i = 0; i < columnNames.length; i++) {
        for (int i = 0; i < model.getColumnCount(); i++) {
            column = this.getColumnModel().getColumn(i);
            column.setPreferredWidth(300);
        }
    }
    
    /**
     * Stops student record cells from being editable.
     * @param data The columns data
     * @param column The column number
     * @return false Meaning not editable
     */
    @Override
    public boolean isCellEditable(int data, int column) {
        return false;
    }
            
    /**
     * Defines the colour of the rows in the table and the colour on selection of a row in the table.
     * @param r The table cell renderer object
     * @param data The data number
     * @param column The column that the cell is in
     * @return the manipulated table component with correct attributes
     */
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
            
    /**
     * Implements table header tool tips for each header.
     * @return The tooltip for that header.
     */
    @Override
    protected JTableHeader createDefaultTableHeader() {
        return new JTableHeader(columnModel) {
            // Get where the mouse is hovering and display the tooltip
            @Override
            public String getToolTipText(MouseEvent e) {
                final java.awt.Point p = e.getPoint();
                // The column currently hovered over
                final int index = columnModel.getColumnIndexAtX(p.x);
                // Gets the relative index for that columns tooltip for the Column Tooltip array
                final int realIndex = columnModel.getColumn(index).getModelIndex();
                
                // Returns the correct tooltip string
                return columnToolTips[realIndex];
            }
        };
    }
}
