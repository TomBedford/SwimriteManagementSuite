package swimritemanagementsuite.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.List;
import swimritemanagementsuite.model.Day;
import swimritemanagementsuite.model.SwimmingClasses;
import swimritemanagementsuite.model.Timeslot;

/**
 *
 * @author Bedford
 */
public class SwimmingClassesController {
    
    public SwimmingClassesController() {
        
    }
    
    public List<SwimmingClasses> getSwimmingClassesForDay(Day day) throws SQLException {
        
        
        // get our query builder from the DAO
        QueryBuilder<Timeslot, Integer> queryBuilder = DatabaseManager.timeslotDAO.queryBuilder();
        
        // the 'day' field must equal Monday.
        queryBuilder.where().eq(Timeslot.DAY_FIELD_NAME, day);
        
        // queryBuilder.selectColumns(Timeslot.DAY_FIELD_NAME);
        
        // outer query on UnitResult table
        QueryBuilder<SwimmingClasses,Integer> outerQuery = DatabaseManager.swimmingClassesDAO.queryBuilder();
        
        // where IN using the sub-query to query the foreign objects table
        outerQuery.where().in(SwimmingClasses.TIMESLOT_FIELD_NAME, queryBuilder);
        
        List<SwimmingClasses> swimmingClassesList = outerQuery.query();
        
        return swimmingClassesList;
    }
    
    
}
