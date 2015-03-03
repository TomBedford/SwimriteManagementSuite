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
    public List<SwimmingClasses> getClassesByDay(Day day) {
        
        List<SwimmingClasses> swimmingClassesList = null;
        
        try {
        
            Timeslot timeslot = new Timeslot();
            timeslot.setDay(day);

            List<Timeslot> ts2;
            swimmingClassesList = new ArrayList<>();

            ts2 = DatabaseManager.timeslotDAO.queryForMatching(timeslot);  

            for (Timeslot t : ts2) {
                swimmingClassesList.addAll(swimmingClassesList.size(), t.getSwimmingClasses());
            }
        
        } catch (SQLException e) {
            System.out.println("getTeacherssForDay: Error getting teachers for a specific day.");
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
