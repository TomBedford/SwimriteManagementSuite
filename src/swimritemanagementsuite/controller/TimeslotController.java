package swimritemanagementsuite.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.List;
import swimritemanagementsuite.model.Day;
import swimritemanagementsuite.model.Timeslot;

/**
 *
 * @author Bedford
 */
public class TimeslotController {
    
    public TimeslotController() {
        
    }
    
    public List<Timeslot> getTimeslotsForDay(Day day) throws SQLException {
        // get our query builder from the DAO
        QueryBuilder<Timeslot, Integer> queryBuilder = DatabaseManager.timeslotDAO.queryBuilder();
        
        // the 'day' field must equal Monday.
        queryBuilder.where().eq(Timeslot.DAY_COLUMN_NAME, day);
        
        // prepare our sql statement
        PreparedQuery<Timeslot> preparedQuery = queryBuilder.prepare();
        
        // query for all accounts that have "qwerty" as a password
        List<Timeslot> timeslotList = DatabaseManager.timeslotDAO.query(preparedQuery);
        
        return timeslotList;
    }
    
    /**
     * Reorders a list of Timeslot objects by time (from lowest to highest).
     * @param timeslotList
     * @return 
     */
    public List<Timeslot> sortTimeslots(List<Timeslot> timeslotList) {
        
        boolean swapped;
        
        do {
            
            swapped = false;
            
        for (int i = 1; i < timeslotList.size(); i++) {
            
            if (timeslotList.get(i - 1).getTime() > timeslotList.get(i).getTime()) {
                
                // SWAPS the timeslot object indexs in the list.
                Timeslot t1 = timeslotList.get(i - 1);
                
                Timeslot t2 = timeslotList.get(i);
                
                timeslotList.set(i - 1, t2);
                
                timeslotList.set(i, t1);
                
                swapped = true;
            }
        }
        } while(swapped);
        
        return timeslotList;
        
    }
    
}
