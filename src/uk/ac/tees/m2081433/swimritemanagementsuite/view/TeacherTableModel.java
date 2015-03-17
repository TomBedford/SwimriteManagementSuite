package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 * The table model for the Teacher table allows work days to have image icon if true in teacher table.
 */
class TeacherTableModel extends AbstractTableModel {
    
    /**
     * Two dimensional object array to hold the data for the table.
     */
    private final Object[][] data;
    
    /**
     * Array of string to hold the table headers.
     */
    private final String[] headers;

    /**
     * Initializes the table model calling the super class and setting the data for the rows and the headers.
     * @param tableData the data to be stored in the table.
     * @param tableHeaders The headers for the table.
     */
    public TeacherTableModel(Object[][] tableData, String[] tableHeaders) {
        super();
        // Sets the data to be stored in the table
        this.data = tableData;
        // Sets the table headers
        this.headers = tableHeaders;
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
     * Overridden method to render image icons in the work day columns.
     * @param row the row of the cell to render
     * @param col the column of the cell to render
     * @return the object to store in that cell
     */
    @Override
    public Object getValueAt(int row, int col) {
        
        // If the column is not equal to the attendance type column
        if (col == 0) {
            // Return the normal object data
            return data[row][col];
        // Otherwise it is the attendance type column
        } else {
            
            // If the data in that cell is not equal to null
            if (data[row][col] != null) {
            
                // return the appropriate image icon for the work boolean
                if (data[row][col].equals(true)) {
                    return new ImageIcon("images/icons/16x16/accept.png");
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    /**
     * Returns the proper class types for each column as the work days columns should now be image icon.
     * @param col the column to check the class type of
     * @return the class type of that column
     */
    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 0) {
            return String.class;
        } else {
            return ImageIcon.class;
        }
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
