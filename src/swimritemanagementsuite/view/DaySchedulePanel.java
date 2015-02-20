package swimritemanagementsuite.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import swimritemanagementsuite.model.Day;
import swimritemanagementsuite.controller.TeacherController;
import swimritemanagementsuite.model.Teacher;
import java.util.List;
import swimritemanagementsuite.controller.SwimmingClassesController;
import swimritemanagementsuite.controller.TimeslotController;
import swimritemanagementsuite.model.SwimmingClasses;
import swimritemanagementsuite.model.Timeslot;

/**
 *
 * @author Bedford
 */
public class DaySchedulePanel extends JPanel {
    
    TeacherController teacherController;
    TimeslotController timeslotController;
    SwimmingClassesController swimmingClassesController;
    
    List<Timeslot> timeslotList;
    
    GridBagConstraints c;
    
    public DaySchedulePanel() throws SQLException {
        
        teacherController = new TeacherController();
        timeslotController = new TimeslotController();
        swimmingClassesController = new SwimmingClassesController();
        
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1350, 3000));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        c = new GridBagConstraints();
        
        loadSchedule(Day.MONDAY);
        
        // NEEDED TO PULL THE TABLE UP TO THE TOP
        JLabel pullComponentsUp = new JLabel();
        pullComponentsUp.setPreferredSize(new Dimension(0, 0));
        pullComponentsUp.setOpaque(false);
        pullComponentsUp.setVisible(true);
        
        c.gridx = 0;
        c.gridy = 1000;
        c.weighty = 1.0;
        c.fill = 1;
        
        this.add(pullComponentsUp, c);

    }
    
    public void loadSchedule(Day selectedDay) throws SQLException{
        
        // Creates the first label to hold the column heading for Timeslots.
        JLabel row1column1 = new JLabel("Time", SwingConstants.CENTER);
        row1column1.setPreferredSize(new Dimension(150, 50));
        row1column1.setOpaque(true);
        row1column1.setBorder(BorderFactory.createRaisedBevelBorder());
        row1column1.setVisible(true);
        
        c.gridx = 0;
        c.gridy = 0;
        
        this.add(row1column1, c);
        
        addTeachersRow(selectedDay);
        
        addTimeslotColumn(selectedDay);
        
        addSwimmingClasses(selectedDay);
    }
    
    /**
     * Method to load the teachers name that teach on the selected day.
     * @param selectedDay The selected day to load the teacher data from.
     */
    public void addTeachersRow(Day selectedDay) throws SQLException {
        
        List<Teacher> teacherList = teacherController.getTeachersForDay(selectedDay);
        
        JLabel[] teacherRow = new JLabel[3];
            for (int i = 0; i < 3; i++) {
            
            teacherRow[i] = new JLabel();
            teacherRow[i].setPreferredSize(new Dimension(300, 50));
            teacherRow[i].setOpaque(true);
            teacherRow[i].setBorder(BorderFactory.createRaisedBevelBorder());
            teacherRow[i].setVisible(true);
            
            c.gridx = i + 1;
            c.gridy = 0;
            
            if (i + 1 <= teacherList.size()) {
                teacherRow[i].setText(teacherList.get(i).getTeacherName());
                teacherRow[i].setHorizontalAlignment(SwingConstants.CENTER);
            }
            
            this.add(teacherRow[i], c);
            
        }
    }
    
    public void addTimeslotColumn(Day selectedDay) throws SQLException {
        
        timeslotList = timeslotController.getTimeslotsForDay(selectedDay);
        
        // Sorts the list of timeslots by time (from lowest to highest).
        timeslotList = timeslotController.sortTimeslots(timeslotList);
        
        JLabel[] timeslotColumn = new JLabel[timeslotList.size()];
        
        for (int i = 0; i < timeslotList.size(); i++) {
            
            timeslotColumn[i] = new JLabel();
            timeslotColumn[i].setPreferredSize(new Dimension(150, 150));
            timeslotColumn[i].setOpaque(true);
            timeslotColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
            timeslotColumn[i].setVisible(true);
            
            String labelText = Integer.toString(timeslotList.get(i).getTime());
                    
            String formattedLabelText = new StringBuilder(labelText).insert(labelText.length()-2, ":").toString();
            
            timeslotColumn[i].setText(formattedLabelText);
            timeslotColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
            
            c.gridx = 0;
            c.gridy = i + 1;
            
            this.add(timeslotColumn[i], c);
            
        }
        
    }
    
    public void addSwimmingClasses(Day selectedDay) throws SQLException {
        
        //List<SwimmingClasses> swimmingClassesList = swimmingClassesController.getSwimmingClassesForDay(selectedDay);
        
        int column = 1;
        
        int row = 1;
        
        int counter = 1;
        
        int rowAmount = timeslotList.size() * 3;
        
        JLabel[] swimmingClassesRow = new JLabel[rowAmount];
        
        JLabel[] classTypeTemplate = new JLabel[rowAmount];
        
        for (int i = 0; i < rowAmount; i++) {
            
            swimmingClassesRow[i] = new JLabel();
            swimmingClassesRow[i].setPreferredSize(new Dimension(300, 150));
            swimmingClassesRow[i].setOpaque(true);
            swimmingClassesRow[i].setBorder(BorderFactory.createRaisedBevelBorder());
            swimmingClassesRow[i].setVisible(true);
            
//            if (i + 1 <= swimmingClassesList.size()) {
//                
//                swimmingClassesRow[i].setText(swimmingClassesList.get(i).getClassType().name());
//                swimmingClassesRow[i].setHorizontalAlignment(SwingConstants.CENTER);
//            }
            
            // TEMPLATE
//            classTypeTemplate[i] = new JLabel();
//            classTypeTemplate[i].setText("Class Type: Beginners");
//            classTypeTemplate[i].setHorizontalAlignment(JLabel.RIGHT);
//            classTypeTemplate[i].setVerticalAlignment(JLabel.BOTTOM);
//            swimmingClassesRow[i].setPreferredSize(new Dimension(300, 150));
//            swimmingClassesRow[i].setOpaque(true);
//            swimmingClassesRow[i].setVisible(true);
//            
//            swimmingClassesRow[i].add(classTypeTemplate[i]);
            
            
            c.gridx = row;
            c.gridy = column;
            this.add(swimmingClassesRow[i], c);
            
            row = row + 1;
       
            // if row is divisible by 3 add one to column.
            if(counter == 3) {
                column = column + 1;
                row = 1;
                counter = 0;
            }
            
            counter++;
        }
        
        
    }
    
}
