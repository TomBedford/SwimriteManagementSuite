package uk.ac.tees.m2081433.swimritemanagementsuite.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.TeacherController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.SwimmingClassesController;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.TimeslotController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 *
 * @author Bedford
 */
public class DaySchedulePanel extends JPanel {
    
    TeacherController teacherController;
    TimeslotController timeslotController;
    SwimmingClassesController swimmingClassesController;
    
    List<Teacher> teacherList;
    List<Timeslot> timeslotList;
    List<SwimmingClasses> swimmingClassesList;
    
    GridBagConstraints c;
    
    public DaySchedulePanel(Day day) throws SQLException {
        
        teacherController = new TeacherController();
        timeslotController = new TimeslotController();
        swimmingClassesController = new SwimmingClassesController();
        
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1360, 3500));
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        
        c = new GridBagConstraints();
        
        loadSchedule(day);
        
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
        
        // Gets all the teachers for the specified day.
        teacherList = teacherController.getTeachersForDay(selectedDay);
        
        // Sorts all of the teachers by the teacher Id of the teacher (Ascending order).
        teacherList = teacherController.sortTeachersByTeacherId(teacherList);
        
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
        
        // Gets all the timeslots for the specified day.
        timeslotList = timeslotController.getTimeslotsForDay(selectedDay);
        
        // Sorts all of the timeslots by the time of the timeslot (Ascending order).
        timeslotList = timeslotController.sortTimeslotsByTime(timeslotList);
        
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
        
        // Gets all swimming classes for the specified day.
        swimmingClassesList = swimmingClassesController.getClassesByDay(selectedDay);
        
        // Sorts all of the swimming classes by the teacher Id (Ascending order).
        swimmingClassesList = swimmingClassesController.sortClassesByTeacherId(swimmingClassesList);
        
        List<SwimmingClasses> teacher1ClassesList = new ArrayList();
        List<SwimmingClasses> teacher2ClassesList = new ArrayList();
        List<SwimmingClasses> teacher3ClassesList = new ArrayList();
        
        for (SwimmingClasses sc : swimmingClassesList) {
            if (sc.getTeacher().getTeacherId() == teacherList.get(0).getTeacherId()) {
                teacher1ClassesList.add(sc);
            } 
            else if (sc.getTeacher().getTeacherId() == teacherList.get(1).getTeacherId()) {
                teacher2ClassesList.add(sc);
            } 
            else if (sc.getTeacher().getTeacherId() == teacherList.get(2).getTeacherId()) {
                teacher3ClassesList.add(sc);
            } 
            else {
                System.out.println("ERROR INPUTTING CLASSES INTO SEPERATE TEACHER LISTS");
            }
        }
        
        // Creates an array of labels to hold the details for the swimming classes for the first teacher
        JLabel[] teacher1ClassesColumn = new JLabel[teacher1ClassesList.size()];
        
        // Iterate through the swimming classes list for teacher 1 to add all classes to the schedule.
        for (int i = 0; i < teacher1ClassesList.size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacher1ClassesList.get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher1ClassesColumn[i] = new JLabel();
                teacher1ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher1ClassesColumn[i].setOpaque(true);
                teacher1ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher1ClassesColumn[i].setLayout(new BorderLayout());
                teacher1ClassesColumn[i].setVisible(true);
                teacher1ClassesColumn[i].setText("Class Type: " + teacher1ClassesList.get(i).getClassType());
                teacher1ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                teacher1ClassesColumn[i].setVerticalAlignment(JLabel.BOTTOM);
                
                // The Gridbag Layout constraints logic to add the label to the correct x & y grid space.
                c.gridx = 1;
                c.gridy = i + 1;
                
                // Adds the label to the correct space in the gridbag layout.
                this.add(teacher1ClassesColumn[i], c);
                
            } else {
                
                System.out.println("ERROR MATCHING TEACHER CLASS 1 LIST TIME TO TIMESLOT TIME.");
                
            }
        }
        
        // Creates an array of labels to hold the details for the swimming classes for the second teacher
        JLabel[] teacher2ClassesColumn = new JLabel[teacher2ClassesList.size()];
        
        // Iterate through the swimming classes list for teacher 2 to add all classes to the schedule.
        for (int i = 0; i < teacher2ClassesList.size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacher2ClassesList.get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher2ClassesColumn[i] = new JLabel();
                teacher2ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher2ClassesColumn[i].setOpaque(true);
                teacher2ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher2ClassesColumn[i].setLayout(new BorderLayout());
                teacher2ClassesColumn[i].setVisible(true);
                teacher2ClassesColumn[i].setText("Class Type: " + teacher2ClassesList.get(i).getClassType());
                teacher2ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                teacher2ClassesColumn[i].setVerticalAlignment(JLabel.BOTTOM);
                
                // The Gridbag Layout constraints logic to add the label to the correct x & y grid space.
                c.gridx = 2;
                c.gridy = i + 1;
                
                // Adds the label to the correct space in the gridbag layout.
                this.add(teacher2ClassesColumn[i], c);
                
            } else {
                
                System.out.println("ERROR MATCHING TEACHER CLASS 2 LIST TIME TO TIMESLOT TIME.");
                
            }
        }
        
        // Creates an array of labels to hold the details for the swimming classes for the third teacher
        JLabel[] teacher3ClassesColumn = new JLabel[teacher3ClassesList.size()];
        
        // Iterate through the swimming classes list for teacher 3 to add all classes to the schedule.
        for (int i = 0; i < teacher3ClassesList.size(); i++) {
            // If the swimming classes timeslot time is equal to the time of the same index in the timeslot list
            if (teacher3ClassesList.get(i).getTimeslot().getTime() == timeslotList.get(i).getTime()) {
                // Create and assign label attributes
                teacher3ClassesColumn[i] = new JLabel();
                teacher3ClassesColumn[i].setPreferredSize(new Dimension(300, 150));
                teacher3ClassesColumn[i].setOpaque(true);
                teacher3ClassesColumn[i].setBorder(BorderFactory.createRaisedBevelBorder());
                teacher3ClassesColumn[i].setLayout(new BorderLayout());
                teacher3ClassesColumn[i].setVisible(true);
                teacher3ClassesColumn[i].setText("Class Type: " + teacher3ClassesList.get(i).getClassType());
                teacher3ClassesColumn[i].setHorizontalAlignment(SwingConstants.CENTER);
                teacher3ClassesColumn[i].setVerticalAlignment(JLabel.BOTTOM);
                
                // The Gridbag Layout constraints logic to add the label to the correct x & y grid space.
                c.gridx = 3;
                c.gridy = i + 1;
                
                // Adds the label to the correct space in the gridbag layout.
                this.add(teacher3ClassesColumn[i], c);
                
            } else {
                
                System.out.println("ERROR MATCHING CLASS 3 LIST TIME TO TIMESLOT TIME.");
                
            }
        }
    }
}
