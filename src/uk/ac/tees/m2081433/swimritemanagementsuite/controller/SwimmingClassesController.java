/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.DatabaseTableController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * This controller interacts (create, update and delete, etc.) with the Swimming Classes table within the database.
 */
public class SwimmingClassesController implements DatabaseTableController<SwimmingClasses> {
    
    /**
     * Reference to the student record controller to update student records when deleting swimming classes.
     */
    private final StudentRecordController studentRecordController = new StudentRecordController();
    
    
    
    /**
     * Creates a Swimming Class in the database using the provided parameter.
     * @param swimmingClass the swimming class to create in the db.
     */
    @Override
    public void create(SwimmingClasses swimmingClass) {
        try {
            // Creates the swimming class in the database
            DatabaseManager.swimmingClassesDAO.create(swimmingClass);
        } catch (SQLException e) {
            System.out.println("createSwimmingClasses: Error creating the swimming class.");
        }
    }
    
    /**
     * Updates a Swimming Class in the database using the updated Swimming Class object provided as a parameter.
     * @param swimmingClass The swimming class with updated values to update in the db table.
     */
    @Override
    public void update(SwimmingClasses swimmingClass) {
        try {
            DatabaseManager.swimmingClassesDAO.update(swimmingClass);
        } catch (SQLException e) {
            System.out.println("updateSwimmingClasses: Error updating swimming class (controller).");
        }
    }
    
    /**
     * Deletes a swimming class from the database that matches the swimming object provided as a parameter
     * and un-enrolls all student records associated with the swimming class from the swimming class.
     * @param swimmingClass The swimming class to delete from the database.
     */
    @Override
    public void delete(SwimmingClasses swimmingClass) {
        try {
            // Deletes the swimming class from the database.
            DatabaseManager.swimmingClassesDAO.delete(swimmingClass);
            
            // Creates and initializes a list to hold all student records associated with the swimming class.
            List<StudentRecord> swimmingClassesStudentRecords;
            
            // Gets all student records associated with this swimming class
            swimmingClassesStudentRecords = studentRecordController.getSwimmingClassStudentRecords(swimmingClass);
            
            // Loops through each student record in the list un-enrolling them from the swimming class
            for (StudentRecord studentRecord : swimmingClassesStudentRecords) {
                studentRecord.setSwimmingClass(null);
                studentRecordController.update(studentRecord);
            }
        } catch (SQLException e) {
            System.out.println("deleteSwimmingClass: Error deleting the swimming class (controller).");
        }
    }
    
    
    
    /**
     * Gets all Swimming Classes for the specified day (parameter).
     * @param day The day to retrieve all the swimming classes for
     * @return A list of swimming classes on that day
     */
    public List<SwimmingClasses> getClassesByDay(Day day) {
        
        // The list to hold all swimming classes on the day
        final List<SwimmingClasses> swimmingClassesList = new ArrayList<>();
        
        try {
            // Creates a timeslot and sets the day to retrieve all the records for
            final Timeslot timeslot = new Timeslot();
            timeslot.setDay(day);

            // Creates a list to hold all timeslots for the specified day
            final List<Timeslot> timeslotsOnDay = DatabaseManager.timeslotDAO.queryForMatching(timeslot);  

            // Loops through all the timeslots getting the swimming classes for that timeslot and adding it to the list.
            for (Timeslot t : timeslotsOnDay) {
                swimmingClassesList.addAll(swimmingClassesList.size(), t.getSwimmingClasses());
            }
        } catch (SQLException e) {
            System.out.println("getTeacherssForDay: Error getting teachers for a specific day.");
        }
        
        return swimmingClassesList;
    }
    
    /**
     * Gets all swimming classes associated with the specified timeslot (paramater).
     * @param timeslot the timeslot to get all the swimming classes for.
     * @return a list of swimming classes that are associated with the timeslot
     */
    public List<SwimmingClasses> getClassesByTimeslot(Timeslot timeslot) {
        
        // Creates and initializes a list to hold all swimming classes associated with the timeslot.
        List<SwimmingClasses> timeslotSwimmingClasses = null;
            
        try {
            // Gets all swimming classes associated with this timeslot
            timeslotSwimmingClasses = DatabaseManager.swimmingClassesDAO.queryForEq(SwimmingClasses.TIMESLOT_COLUMN_NAME, timeslot);

        } catch (SQLException e) {
            System.out.println("deleteTimeslot: Error deleting the timeslot (controller).");
        }
        return timeslotSwimmingClasses;
    }
    
    
    /**
     * A Method to sort the swimming classes by the teacherId.
     * @param swimmingClassesList - the unsorted swimming class list.
     * @return swimmingClassesList - the sorted swimming class list.
     */
    public List<SwimmingClasses> sortClassesByTeacherId(List<SwimmingClasses> swimmingClassesList) {
        
        // Calls the overided method CompareTo to sort by the classes TeacherId.
        Collections.sort(swimmingClassesList);
        
        // Returns the sorted list.
        return swimmingClassesList;
    }
    
    /**
     * A Method to sort the swimming classes by the timeslot time of the swimming classes.
     * @param swimmingClassesList - the unsorted swimming class list.
     * @return swimmingClassesList - the sorted swimming class list.
     */
    public List<SwimmingClasses> sortClassesByTimeslotTime(List<SwimmingClasses> swimmingClassesList) {
        
        // Calls the overided method Compare to sort by the classes TeacherId.
        Collections.sort(swimmingClassesList, new Comparator<SwimmingClasses>() {
            @Override
            public int compare(SwimmingClasses one, SwimmingClasses two) {
                if (one.getTimeslot().getTime() <  two.getTimeslot().getTime()) {
                    return -1;
                } else if (one.getTimeslot().getTime() == two.getTimeslot().getTime()) {
                    return 0;
                }
                return 1;
            }
        });
        
        // Returns the sorted list.
        return swimmingClassesList;
    }
}
