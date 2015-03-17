package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.DatabaseTableController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 * This controller interacts (create and delete, etc.) with the Timeslot table within the database.
 */
public class TimeslotController implements DatabaseTableController<Timeslot> {
    
    /**
     * Controller reference to delete all Swimming Classes associated with a timeslot that is being deleted.
     */
    private final SwimmingClassesController swimmingClassesController = new SwimmingClassesController();

    
    
    /**
     * Creates a Timeslot in the database using the provided parameter.
     * @param timeslot the timeslot to create in the db.
     */
    @Override
    public void create(Timeslot timeslot) {
        try {
            // Creates the timeslot in the database
            DatabaseManager.timeslotDAO.create(timeslot);
        } catch (SQLException e) {
            System.out.println("createTimeslot: Error creating the timeslot.");
        }
    }
    
    /**
     * Updates a Timeslot in the database using the updated Timeslot object provided as a parameter.
     * @param timeslot The timeslot with updated values to update in the db table.
     */
    @Override
    public void update(Timeslot timeslot) {
        try {
            // Updates the teacher in the database
            DatabaseManager.timeslotDAO.update(timeslot);
        } catch (SQLException e) {
            System.out.println("updateTimeslot: Error updating timeslot.");
        }
    }
    
    /**
     * Deletes a Timeslot from the database that matches the Timeslot object provided as a parameter
     * and deletes all swimming classes associated with the Timeslot.
     * @param timeslot The timeslot to delete from the database.
     */
    @Override
    public void delete(Timeslot timeslot) {
        try {
            // Deletes the timeslot from the database.
            DatabaseManager.timeslotDAO.delete(timeslot);
            
            // Creates and initializes a list to hold all swimming classes associated with this timeslot.
            final List<SwimmingClasses> timeslotSwimmingClasses = swimmingClassesController.getClassesByTimeslot(timeslot);
            
            // Loops through each lesson block in the list deleting it
            for (SwimmingClasses swimmingClass : timeslotSwimmingClasses) {
                swimmingClassesController.delete(swimmingClass);
            }
        } catch (SQLException e) {
            System.out.println("deleteTimeslot: Error deleting the timeslot (controller).");
        }
    }
    
    /**
     * Gets all Timeslots for the specified day (parameter).
     * @param day the day to get all of the timeslots for
     * @return A list of timeslots associated with that specific day
     */
    public List<Timeslot> getTimeslotsForDay(Day day) {
        
        // The list to hold all of the timeslots associated with the specified day.
        List<Timeslot> timeslotList = null;
        
        try {
            // query for all accounts that have "qwerty" as a password
            timeslotList = DatabaseManager.timeslotDAO.queryForEq(Timeslot.DAY_COLUMN_NAME, day);

        } catch (SQLException e) {
            System.out.println("getTimeslotsForDay: Error getting timeslots for a specific day.");
        }
        
        return timeslotList;
    }
    
    
    
    /**
     * Sorts a list of timeslots by the time of the timeslot (Ascending order).
     * @param timeslotList - The unsorted list of timeslots.
     * @return timeslotList - The sorted list of timeslots.
     */
    public List<Timeslot> sortTimeslotsByTime(List<Timeslot> timeslotList) {
        // Calls the overided method CompareTo to sort by the timeslots time in ascending order).
        Collections.sort(timeslotList);
        
        // Returns the sorted list.
        return timeslotList;
    }
    
//    /**
//     * BUBBLE SORT!
//     * Reorders a list of Timeslot objects by time (from lowest to highest).
//     * @param timeslotList
//     * @return 
//     */
//    public List<Timeslot> sortTimeslots(List<Timeslot> timeslotList) {
//        
//        boolean swapped;
//        
//        do {
//            
//            swapped = false;
//            
//        for (int i = 1; i < timeslotList.size(); i++) {
//            
//            if (timeslotList.get(i - 1).getTime() > timeslotList.get(i).getTime()) {
//                
//                // SWAPS the timeslot object indexs in the list.
//                Timeslot t1 = timeslotList.get(i - 1);
//                
//                Timeslot t2 = timeslotList.get(i);
//                
//                timeslotList.set(i - 1, t2);
//                
//                timeslotList.set(i, t1);
//                
//                swapped = true;
//            }
//        }
//        } while(swapped);
//        
//        return timeslotList;
//        
//    }
    
}
