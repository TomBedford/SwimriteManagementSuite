package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 *
 * @author Bedford
 */
public class TimeslotController {
    
    public TimeslotController() {
        
    }
    
    public List<Timeslot> getTimeslotsForDay(Day day) {
        
        List<Timeslot> timeslotList = null;
        
        try {
        // get our query builder from the DAO
        QueryBuilder<Timeslot, Integer> queryBuilder = DatabaseManager.timeslotDAO.queryBuilder();
        
        // the 'day' field must equal Monday.
        queryBuilder.where().eq(Timeslot.DAY_COLUMN_NAME, day);
        
        // prepare our sql statement
        PreparedQuery<Timeslot> preparedQuery = queryBuilder.prepare();
        
        // query for all accounts that have "qwerty" as a password
        timeslotList = DatabaseManager.timeslotDAO.query(preparedQuery);
        
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
