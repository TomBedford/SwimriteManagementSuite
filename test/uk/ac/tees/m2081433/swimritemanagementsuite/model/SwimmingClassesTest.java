/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.model;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.tees.m2081433.swimritemanagementsuite.controller.DatabaseManager;
import static uk.ac.tees.m2081433.swimritemanagementsuite.controller.DatabaseManager.swimmingClassesDAO;
import static uk.ac.tees.m2081433.swimritemanagementsuite.controller.DatabaseManager.teacherDAO;
import static uk.ac.tees.m2081433.swimritemanagementsuite.controller.DatabaseManager.timeslotDAO;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.SwimmingClasses;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Timeslot;

/**
 *
 * @author Bedford
 */
public class SwimmingClassesTest {
    
    public SwimmingClassesTest() {
        
        // DATABASE ACCESS OBJECTS FOR EACH DATABASE TABLE.
        /**
         * The URL that links to the Online MySQL Swimrite Management Suite database.
         */
        final String DATABASE_URL = "jdbc:mysql://localhost:8888/SwimriteManagementSuite";

        /**
         * This is the username for the MySql database.
         */
        final String dbUsername = "root";

        /**
         * This is the password for the MySql database.
         */
        final String dbPassword = "root";
        
        JdbcConnectionSource connectionSource;
        try {
            connectionSource = new JdbcConnectionSource(DATABASE_URL, dbUsername, dbPassword);
            
            swimmingClassesDAO = DaoManager.createDao(connectionSource, SwimmingClasses.class);
            timeslotDAO = DaoManager.createDao(connectionSource, Timeslot.class);
            
        } catch (SQLException ex) {
            Logger.getLogger(SwimmingClassesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Timeslot timeslot = new Timeslot();
        List<Timeslot> ts2;
        List<SwimmingClasses> classes;
        
        timeslot.setDay(Day.MONDAY);
        
        try {
            ts2 = DatabaseManager.timeslotDAO.queryForMatching(timeslot);
            
            
            
            //classes = new ArrayList<>(ts2.get(0).getSwimmingClass());
            // System.out.println(classes.size());
            
            
            classes = new ArrayList<>();
            
            
            
            for (Timeslot t : ts2) {
                classes.addAll(classes.size(), t.getSwimmingClasses());
//                classes.add(t.getSwimmingClass());
            
            }
            
            
            //List<SwimmingClasses> swimmingClassesByTimeslot = DatabaseManager.swimmingClassesDAO.queryForMatching(sc);
            //List<Timeslot> timeslotsList = DatabaseManager.timeslotDAO.queryForMatching(sc);
        } catch (SQLException ex) {
            Logger.getLogger(SwimmingClassesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @Test
    public void testSomeMethod() {
        
    }
    
}
