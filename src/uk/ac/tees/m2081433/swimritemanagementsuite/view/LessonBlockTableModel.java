package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.AttendanceType;

/**
 *
 */
class LessonBlockTableModel extends AbstractTableModel {
    
    private Object[][] data;
    private String[] headers;
    private boolean dateEditable;

    public LessonBlockTableModel(Object[][] data, String[] headerss) {
        super();
        this.data = data;
        this.headers = headerss;
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

    @Override
    public Object getValueAt(int row, int col) {
        
        if (col != 1) {
            return data[row][col];
        } else {
            
            if (data[row][col] != null) {
            
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

    @Override
    public Class<?> getColumnClass(int col) {
        if (col != 1) {
            return String.class;
        } else {
            return ImageIcon.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
     switch (col) {
         case 0:
             return dateEditable;
         default:
             return false;
      }
    }
    
    public void setDateEditable(Boolean isEditable) {
        dateEditable = isEditable;
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
