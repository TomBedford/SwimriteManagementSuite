package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 *
 * @author Bedford
 */
public class SwimmingClassesController {
    
    public SwimmingClassesController() {
        
    }
    
    /**
     * Method to get all swimming classes for a specified day.
     * @param day
     * @return
     * @throws SQLException 
     */
    public List<SwimmingClasses> getClassesByDay(Day day) throws SQLException {
        
        // Would have liked to have used the query builder, however ORMlite had problems.
        Timeslot timeslot = new Timeslot();
        timeslot.setDay(day);
        
        List<Timeslot> timeslotList = new ArrayList<>();
        
        timeslotList = DatabaseManager.timeslotDAO.queryForMatching(timeslot);
        
        List<SwimmingClasses> swimmingClassesList = new ArrayList<>();
        
        for(Timeslot t : timeslotList) {
            
            SwimmingClasses sc = new SwimmingClasses();
            sc.setTimeslot(t);
            
            List<SwimmingClasses> swimmingClassesByTimeslot = new ArrayList<>();
            swimmingClassesByTimeslot = DatabaseManager.swimmingClassesDAO.queryForMatching(sc);
            swimmingClassesList.addAll(swimmingClassesList.size()  , swimmingClassesByTimeslot);
        }
        
        return swimmingClassesList;
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
    
}
