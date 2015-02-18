package swimritemanagementsuite.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author Bedford
 */
public class TimeslotDAC {
    /**
     * The Data Access Object used to access the Timeslot table in the mySQL database.
     */
    private Dao<Timeslot, Integer> timeslotDAO;
    
    /**
     * Timeslot object used for creating, updating and deleting timeslots from the database.
     */
    Timeslot timeslot;
    
    public TimeslotDAC() {
        
    }
    
    public TimeslotDAC(ConnectionSource connectionSource){
        try {
            timeslotDAO = DaoManager.createDao(connectionSource, Timeslot.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the Timeslot database access objects");
        }
    }
    
    // CREATE, UPDATE AND DELETE FOR THE TIMESLOT DATABASE TABLE.
    /**
     * Create a Timeslot and persists it to the Timeslot database table.
     *
     * @param day The day of the timeslot.
     * @param time The time of the timeslot.
     * @throws Exception SQL Exception.
     */
    public void createTimeslot(Day day, int time) throws Exception {
        // Create an instance of a Timeslot.
        timeslot = new Timeslot(day, time);

        // Persist the Timeslot object to the database.
        timeslotDAO.create(timeslot);
    }

    /**
     * Updates a Timeslot in the Timeslot database table.
     *
     * @param timeslotId The unique ID of the specified timeslot to be updated.
     * @param day The updated day of the timeslot.
     * @param time The updated time of the timeslot.
     * @throws Exception SQL Exception.
     */
    public void updateTimeslot(int timeslotId, Day day, int time) throws Exception {
        // Retrieves the desired Timeslot from the database.
        timeslot = timeslotDAO.queryForId(timeslotId);

        // If the Timeslot object isnt null then the new updated timeslot values are set and updated.
        if (timeslot != null) {
            timeslot.setDay(day);
            timeslot.setTime(time);
            timeslotDAO.update(timeslot);
        }
    }

    /**
     * Deletes a specified Timeslot from the Timeslot database table.
     *
     * @param timeslotId The unique ID of the specified Timeslot to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteTimeslot(int timeslotId) throws Exception {
        // Retrieves the desired Timeslot from the database.
        timeslot = timeslotDAO.queryForId(timeslotId);

        // Deletes the desired Timeslot from the Timeslot table in the database.
        timeslotDAO.delete(timeslot);
    }
        
}
