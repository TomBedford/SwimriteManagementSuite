package swimritemanagementsuite.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author Bedford
 */
public class TeacherDAC {
    
    /**
     * The Data Access Object used to access the Teacher table in the mySQL database.
     */
    private Dao<Teacher, Integer> teacherDAO;
    
    /**
     * Teacher object used for creating, updating and deleting teachers from the database.
     */
    Teacher teacher;
    
    public TeacherDAC(){
        
    }
    
    public TeacherDAC(ConnectionSource connectionSource){
        try {
            teacherDAO = DaoManager.createDao(connectionSource, Teacher.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the Teacher database access objects");
        }
    }
    
    // CREATE, UPDATE AND DELETE FOR THE TEACHER DATABASE TABLE.
    /**
     * Create a Teacher and persists it to the teacher database table.
     *
     * @param teacherName The name of the teacher.
     * @throws Exception SQL Exception.
     */
    public void createTeacher(String teacherName) throws Exception {
        // Create an instance of a Teacher.
        teacher = new Teacher(teacherName);

        // Persist the Teacher object to the database.
        teacherDAO.create(teacher);
    }

    /**
     * Updates a Teacher record in the teacher database table.
     *
     * @param teacherId The unique ID of the specified teacher record to be updated.
     * @param teacherName The updated name of the teacher.
     * @throws Exception SQL Exception.
     */
    public void updateTeacher(int teacherId, String teacherName) throws Exception {
        // Retrieves the desired Teacher from the database.
        teacher = teacherDAO.queryForId(teacherId);

        // If the Teacher object isnt null then the new updated teacher values are set and updated.
        if (teacher != null) {
            teacher.setTeacherName(teacherName);
            teacherDAO.update(teacher);
        }
    }

    /**
     * Deletes a specified Teacher from the Teacher database table.
     *
     * @param teacherId The unique ID of the Teacher record to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteTeacher(int teacherId) throws Exception {
        // Retrieves the desired Teacher from the database.
        teacher = teacherDAO.queryForId(teacherId);

        // Deletes the desired Teacher from the Teacher table in the database.
        teacherDAO.delete(teacher);
    }
}
