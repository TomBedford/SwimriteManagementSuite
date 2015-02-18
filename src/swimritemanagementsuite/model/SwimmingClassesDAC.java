package swimritemanagementsuite.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author Bedford
 */
public class SwimmingClassesDAC {
    
    /**
     * The Data Access Object used to access the Swimming Classes table in the mySQL database.
     */
    private Dao<SwimmingClasses, Integer> swimmingClassDAO;
    
    /**
     * Swimming Classes object used for creating, updating and deleting swimming classes from the database.
     */
    SwimmingClasses swimmingClass;
    
    public SwimmingClassesDAC(){
        
    }
    
    public SwimmingClassesDAC(ConnectionSource connectionSource){
        try {
            swimmingClassDAO = DaoManager.createDao(connectionSource, SwimmingClasses.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the Swimming Classes database access objects");
        }
    }
    
    // CREATE, UPDATE AND DELETE FOR THE SWIMMING CLASSES DATABASE TABLE.
    /**
     * Creates a Swimming Class and persists it to the database.
     *
     * @param classType The type of Swimming class it is.
     * @param timeslot The timeslot of the class (day/time)
     * @param teacher The teacher of the class.
     * @param maxCapacity The maximum student capacity of the class.
     * @throws Exception SQL Exception.
     */
    public void createSwimmingClass(SwimmingLevel classType, Timeslot timeslot, Teacher teacher, int maxCapacity) throws Exception {
        // Create an instance of a Swimming Class.
        swimmingClass = new SwimmingClasses(classType, timeslot, teacher, maxCapacity);

        // Persist the Swimming Class object to the database.
        swimmingClassDAO.create(swimmingClass);
    }

    /**
     * Updates a Swimming class record in the Swimming classes database.
     *
     * @param classId The swimming class to update.
     * @param classType The updated class type of the swimming class.
     * @param timeslot The updated timeslot of the swimming class.
     * @param teacher The updated teacher of the swimming class.
     * @param maxCapacity The updated max capacity of the swimming class.
     * @throws Exception SQL Exception.
     */
    public void updateSwimmingClass(int classId, SwimmingLevel classType, Timeslot timeslot, Teacher teacher, int maxCapacity) throws Exception {
        // Retrieves the desired Swimming Class from the database.
        swimmingClass = swimmingClassDAO.queryForId(classId);

        // If the swimming class object isnt null then the new updated swimming class values are set and updated.
        if (swimmingClass != null) {
            swimmingClass.setClassType(classType);
            swimmingClass.setTimeslot(timeslot);
            swimmingClass.setTeacher(teacher);
            swimmingClass.setMaxCapacity(maxCapacity);
            swimmingClassDAO.update(swimmingClass);
        }
    }

    /**
     * Deletes a specified Swimming Class from the swimming classes database table.
     *
     * @param classId The unique Id of the specified swimming class to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteSwimmingClass(int classId) throws Exception {
        // Retrieves the desired swimming class.
        swimmingClass = swimmingClassDAO.queryForId(classId);

        // Deletes the desired swimming class from the swimming classes table in the database.
        swimmingClassDAO.delete(swimmingClass);
    }
}
