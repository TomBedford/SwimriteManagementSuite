package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.util.List;
import java.sql.SQLException;
import java.util.Collections;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;

/**
 * This controller interacts (create, update and delete, etc.) with the Teacher table within the database.
 */
public class TeacherController {
    
    /**
     * Creates a Teacher in the database using the provided parameter.
     * @param teacher The teacher to add to the db.
     */
    public void createTeacher(Teacher teacher) {
        
        try {
            // Creates the teacher in the database
            DatabaseManager.teacherDAO.create(teacher);
        } catch (SQLException e) {
            System.out.println("createTeacher: Error creating the teacher.");
        }
         
    }
    
    /**
     * Updates a Teacher in the database using the updated Teacher object provided as a parameter.
     * @param teacher The teacher with updated values to update in the db table.
     */
    public void updateTeacher(Teacher teacher) {
        try {
            // Updates the teacher in the database
            DatabaseManager.teacherDAO.update(teacher);
        } catch (SQLException e) {
            System.out.println("updateTeacher: Error updating teacher.");
        }
    }
    
    /**
     * Deletes the provided Teacher (parameter) from the database.
     * @param teacher The teacher to delete from the database
     */
    public void deleteTeacher(Teacher teacher) {
        
        try {
            // Deletes the teacher in the database
            DatabaseManager.teacherDAO.delete(teacher);
        } catch (SQLException e) {
            System.out.println("deleteTeacher: Error deleting the teacher.");
        }
         
    }
    
    
    
    /**
     * Gets teachers that work on the specified day (parameter).
     * @param day The day to get all the teachers for
     * @return list of all teachers that work on the specified day
     */
    public List<Teacher> getTeachersForDay(Day day) {
        
        // List to hold all the teachers that work on the day
        List<Teacher> teacherList = null;
        
        try {
            if (day == Day.MONDAY) {
                
                // Gets all teacher where work monday column = true
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKMONDAY_COLUMN_NAME, true);

            } else if (day == Day.TUESDAY) {

                // Gets all teacher where work tuesday column = true
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKTUESDAY_COLUMN_NAME, true);

            } else if (day == Day.WEDNESDAY) {

                // Gets all teacher where work wednesday column = true
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKWEDNESDAY_COLUMN_NAME, true);

            } else if (day == Day.THURSDAY) {

                // Gets all teacher where work thursday column = true
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKTHURSDAY_COLUMN_NAME, true);

            } else if (day == Day.FRIDAY) {

                // Gets all teacher where work friday column = true
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKFRIDAY_COLUMN_NAME, true);

            } else if (day == Day.SATURDAY) {

                // Gets all teacher where work saturday column = true
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKSATURDAY_COLUMN_NAME, true);

            } else if (day == Day.SUNDAY) {

                // Gets all teacher where work sunday column = true
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKSUNDAY_COLUMN_NAME, true);

            }
        } catch (SQLException e) {
            System.out.println("getTeachersForDay: Error getting teachers for a specific day.");
        }
        
        return teacherList;
    }
    
    /**
     * Gets all teacher that don't work on the specified day.
     * @param day The day that it doesn't want to get teachers from.
     * @return A list of teachers that don't work on the specified day
     */
    public List<Teacher> getTeachersNotForDay(Day day) {
        
        // List to hold all teachers that dont work on the specified day
        List<Teacher> teacherList = null;
        
        try {
            if (day == Day.MONDAY) {
                
                // Gets all teacher where work monday column = false
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKMONDAY_COLUMN_NAME, false);

            } else if (day == Day.TUESDAY) {

                // Gets all teacher where work tuesday column = false
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKTUESDAY_COLUMN_NAME, false);

            } else if (day == Day.WEDNESDAY) {

                // Gets all teacher where work wednesday column = false
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKWEDNESDAY_COLUMN_NAME, false);

            } else if (day == Day.THURSDAY) {

                // Gets all teacher where work thursday column = false
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKTHURSDAY_COLUMN_NAME, false);

            } else if (day == Day.FRIDAY) {

                // Gets all teacher where work friday column = false
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKFRIDAY_COLUMN_NAME, false);

            } else if (day == Day.SATURDAY) {

                // Gets all teacher where work saturday column = false
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKSATURDAY_COLUMN_NAME, false);

            } else if (day == Day.SUNDAY) {

                // Gets all teacher where work sunday column = false
                teacherList = DatabaseManager.teacherDAO.queryForEq(Teacher.WORKSUNDAY_COLUMN_NAME, false);

            }
        } catch (SQLException e) {
            System.out.println("getTeachersNotForDay: Error getting teachers that dont work on the specified day.");
        }
        
        return teacherList;
    }
    
    
    
    /**
     * Sorts a list of teachers by the teacher id of the teacher (Ascending order).
     * @param teacherList - The unsorted list of teachers.
     * @return teacherList - The sorted list of teachers.
     */
    public List<Teacher> sortTeachersByTeacherId(List<Teacher> teacherList) {
        // Calls the overided method CompareTo to sort by the teachers id in ascending order).
        Collections.sort(teacherList);
        
        // Returns the sorted list.
        return teacherList;
    }
}
