package swimritemanagementsuite.model;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Bedford
 */
public class LessonBlockDAC {
    
    /**
     * The Data Access Object used to access the Lesson Block table in the mySQL database.
     */
    private Dao<LessonBlock, Integer> lessonBlockDAO;
    
    /**
     * Lesson Block object used for creating, updating and deleting lesson blocks from the database.
     */
    LessonBlock lessonBlock;
    
    public LessonBlockDAC() {
        
    }
    
    public LessonBlockDAC(ConnectionSource connectionSource) {
        try {
            lessonBlockDAO = DaoManager.createDao(connectionSource, LessonBlock.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the Lesson Block database access objects");
        }
        
    }
    
    // CREATE, UPDATE AND DELETE FOR THE LESSON BLOCK DATABASE TABLE.
    /**
     * Creates a lesson block and persists it to the database.
     *
     * @param lessonPayment The payment information for the payment of this lesson block.
     * @param lesson1Date The date of the first lesson.
     * @param lesson2Date The date of the second lesson.
     * @param lesson3Date The date of the third lesson.
     * @param lesson4Date The date of the fourth lesson.
     * @param lesson5Date The date of the fifth lesson.
     * @param lesson6Date The date of the sixth lesson.
     * @param lesson7Date The date of the seventh lesson.
     * @param lesson8Date The date of the eighth lesson.
     * @param lesson9Date The date of the ninth lesson.
     * @param lesson10Date The date of the tenth lesson.
     * @throws Exception SQL exception.
     */
    public void createLessonBlock(LessonPayment lessonPayment, Date lesson1Date, Date lesson2Date, Date lesson3Date, Date lesson4Date,
            Date lesson5Date, Date lesson6Date, Date lesson7Date, Date lesson8Date,
            Date lesson9Date, Date lesson10Date) throws Exception {
        // Create an instance of a lesson block.
        lessonBlock = new LessonBlock(lessonPayment, lesson1Date, lesson2Date, lesson3Date, lesson4Date, lesson5Date,
                lesson6Date, lesson7Date, lesson8Date, lesson9Date, lesson10Date);

        // Persists the Lesson Block object to the database.
        lessonBlockDAO.create(lessonBlock);
    }

    /**
     * Updates a specific Lesson Block within the Lesson Block table within the database.
     *
     * @param blockId The unique ID of the lesson block to update.
     * @param lessonPayment The updated lesson payment information of the lesson block.
     * @param lesson1Date The updated date of the first lesson.
     * @param lesson1Attendance The updated attendance boolean of the first lesson.
     * @param lesson2Date The updated date of the second lesson.
     * @param lesson2Attendance The updated attendance boolean of the second lesson.
     * @param lesson3Date The updated date of the third lesson.
     * @param lesson3Attendance The updated attendance boolean of the third lesson.
     * @param lesson4Date The updated date of the fourth lesson.
     * @param lesson4Attendance The updated attendance boolean of the fourth lesson.
     * @param lesson5Date The updated date of the fifth lesson.
     * @param lesson5Attendance The updated attendance boolean of the fifth lesson.
     * @param lesson6Date The updated date of the sixth lesson.
     * @param lesson6Attendance The updated attendance boolean of the sixth lesson.
     * @param lesson7Date The updated date of the seventh lesson.
     * @param lesson7Attendance The updated attendance boolean of the seventh lesson.
     * @param lesson8Date The updated date of the eighth lesson.
     * @param lesson8Attendance The updated attendance boolean of the eighth lesson.
     * @param lesson9Date The updated date of the ninth lesson.
     * @param lesson9Attendance The updated attendance boolean of the ninth lesson.
     * @param lesson10Date The updated date of the tenth lesson.
     * @param lesson10Attendance The updated attendance boolean of the tenth lesson.
     * @throws Exception SQL Exception.
     */
    public void updateLessonBlock(int blockId, LessonPayment lessonPayment, Date lesson1Date, AttendanceType lesson1Attendance, Date lesson2Date, AttendanceType lesson2Attendance,
            Date lesson3Date, AttendanceType lesson3Attendance, Date lesson4Date, AttendanceType lesson4Attendance, Date lesson5Date, AttendanceType lesson5Attendance,
            Date lesson6Date, AttendanceType lesson6Attendance, Date lesson7Date, AttendanceType lesson7Attendance, Date lesson8Date, AttendanceType lesson8Attendance,
            Date lesson9Date, AttendanceType lesson9Attendance, Date lesson10Date, AttendanceType lesson10Attendance) throws Exception {
        // Retrieves the desired Lesson Block from the database.
        lessonBlock = lessonBlockDAO.queryForId(blockId);

        // If the Lesson Block object isnt null then the new updated lesson block values are set and updated.
        if (lessonBlock != null) {
            lessonBlock.setLessonPayment(lessonPayment);
            lessonBlock.setLesson1Date(lesson1Date);
            lessonBlock.setLesson1Attendance(lesson1Attendance);
            lessonBlock.setLesson2Date(lesson2Date);
            lessonBlock.setLesson2Attendance(lesson2Attendance);
            lessonBlock.setLesson3Date(lesson3Date);
            lessonBlock.setLesson3Attendance(lesson3Attendance);
            lessonBlock.setLesson4Date(lesson4Date);
            lessonBlock.setLesson4Attendance(lesson4Attendance);
            lessonBlock.setLesson5Date(lesson5Date);
            lessonBlock.setLesson5Attendance(lesson5Attendance);
            lessonBlock.setLesson6Date(lesson6Date);
            lessonBlock.setLesson6Attendance(lesson6Attendance);
            lessonBlock.setLesson7Date(lesson7Date);
            lessonBlock.setLesson7Attendance(lesson7Attendance);
            lessonBlock.setLesson8Date(lesson8Date);
            lessonBlock.setLesson8Attendance(lesson8Attendance);
            lessonBlock.setLesson9Date(lesson9Date);
            lessonBlock.setLesson9Attendance(lesson9Attendance);
            lessonBlock.setLesson10Date(lesson10Date);
            lessonBlock.setLesson10Attendance(lesson10Attendance);
            lessonBlockDAO.update(lessonBlock);
        }
    }

    /**
     * Deletes a specified Lesson Block from the lesson block database table.
     *
     * @param blockId the specified lesson block to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteLessonBlock(int blockId) throws Exception {
        // Retrieves the desired lesson block.
        lessonBlock = lessonBlockDAO.queryForId(blockId);

        // Deletes the desired Lesson Block from the lesson block table in the database.
        lessonBlockDAO.delete(lessonBlock);
    }
}
