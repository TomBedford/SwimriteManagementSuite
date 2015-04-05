/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.AttendanceType;

/**
 * The table model for the lesson block table.
 */
class LessonBlockTableModel extends AbstractTableModel {
    
    /**
     * Two dimensional object array to hold the data for the table.
     */
    private final Object[][] data;
    
    /**
     * Array of string to hold the table headers.
     */
    private final String[] headers;
    
    /**
     * Boolean as to whether the date column is editable.
     */
    private boolean dateEditable;

    /**
     * Initializes the table model calling the super class and setting the data for the rows and the headers.
     * @param tableData the data to be stored in the table.
     * @param tableHeaders The headers for the table.
     */
    public LessonBlockTableModel(Object[][] tableData, String[] tableHeaders) {
        super();
        // Sets the data to be stored in the table
        this.data = tableData;
        // Sets the table headers
        this.headers = tableHeaders;
        // Initializes the date column not to be editable.
        dateEditable = false;
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return headers[col];
    }

    /**
     * Overridden method to render image icons in the attendance type column.
     * @param row the row of the cell to render
     * @param col the column of the cell to render
     * @return the object to store in that cell
     */
    @Override
    public Object getValueAt(int row, int col) {
        
        // If the column is not equal to the attendance type column
        if (col != 1) {
            // Return the normal object data
            return data[row][col];
        // Otherwise it is the attendance type column
        } else {
            
            // If the data in that cell is not equal to null
            if (data[row][col] != null) {
            
                // return the appropriate image icon for the attendance type
                if (data[row][col].equals(AttendanceType.PRESENT)) {
                    return new ImageIcon("images/icons/16x16/accept.png");
                } else if (data[row][col].equals(AttendanceType.ABSENT)) {
                    return new ImageIcon("images/icons/16x16/cancel.png");
                } else if (data[row][col].equals(AttendanceType.FUN_SWIM)) {
                    return new ImageIcon("images/icons/16x16/fun_swim.png");
                } else if (data[row][col].equals(AttendanceType.FUN_SWIM_TAKEN)) {
                    return new ImageIcon("images/icons/16x16/fun_swim_taken.png");
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    /**
     * Returns the proper class types for each column as the attendance type column should now be image icon.
     * @param col the column to check the class type of
     * @return the class type of that column
     */
    @Override
    public Class<?> getColumnClass(int col) {
        if (col != 1) {
            return String.class;
        } else {
            return ImageIcon.class;
        }
    }
    
    /**
     * Overridden method to determine whether the date column cells are editable.
     * @param row the row of the cell to check if it is editable
     * @param col the column of the cell to check if it is editable
     * @return whether the column is editable or not.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            // If the column is the date column check whether the column is set to editable
            case 0:
                    return dateEditable;
            default:
                    return false;
        }
    }
    
    /**
     * Sets whether the date column in the lesson block table is editable or not.
     * @param isEditable The new boolean value as to whether the date column in the lesson block table is editable
     */
    protected void setDateEditable(Boolean isEditable) {
        dateEditable = isEditable;
    }
    
    /**
     * Overridden method to set the appropriate values of each cell in the table.
     * @param value the object to put in the cell
     * @param row the row of the cell to input data
     * @param col the column of the cell to input data
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
