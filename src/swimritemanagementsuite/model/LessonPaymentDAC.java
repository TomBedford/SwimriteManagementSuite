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
public class LessonPaymentDAC {
    
    /**
     * The Data Access Object used to access the Lesson Payment table in the mySQL database.
     */
    private Dao<LessonPayment, Integer> lessonPaymentDAO;
    
    /**
     * Lesson Payment object used for creating, updating and deleting lesson payments from the database.
     */
    LessonPayment lessonPayment;
    
    public LessonPaymentDAC() {
        
    }
    
    public LessonPaymentDAC(ConnectionSource connectionSource) {
        try {
            lessonPaymentDAO = DaoManager.createDao(connectionSource, LessonPayment.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the Lesson Payment database access objects");
        }
    }
    
    // CREATE, UPDATE AND DELETE FOR THE LESSON PAYMENT DATABASE TABLE.
    /**
     * Creates a Lesson Payment and persists it to the database.
     *
     * @param paymentType The type of payment made (eg, card, cheque or cash).
     * @param paymentDate The date the payment was made on,
     * @param paymentAmount The amount of payment being made.
     * @param paymentTaker The taker of the payment (receptionists name).
     * @throws Exception SQL Exception.
     */
    public void createLessonPayment(PaymentType paymentType, Date paymentDate, double paymentAmount, String paymentTaker) throws Exception {
        // Create an instance of a Lesson Payment.
        lessonPayment = new LessonPayment(paymentType, paymentDate, paymentAmount, paymentTaker);

        // Persist the Lesson Payment object to the database.
        lessonPaymentDAO.create(lessonPayment);
    }

    /**
     * Updates a Lesson Payment record in the Lesson Payment database table.
     *
     * @param paymentId The unique ID of the lesson payment to be updated.
     * @param paymentType The updated payment type of the lesson payment.
     * @param paymentDate The updated payment date of the lesson payment.
     * @param paymentAmount The updated payment amount of the lesson payment.
     * @param paymentTaker The updated payment taker of the lesson payment.
     * @throws Exception SQL Exception.
     */
    public void updateLessonPayment(int paymentId, PaymentType paymentType, Date paymentDate, double paymentAmount, String paymentTaker) throws Exception {
        // Retrieves the desired lesson payment record from the database.
        lessonPayment = lessonPaymentDAO.queryForId(paymentId);

        // If the lesson payment object isnt null then the new updated lesson payment values are set and updated.
        if (lessonPayment != null) {
            lessonPayment.setPaymentType(paymentType);
            lessonPayment.setPaymentDate(paymentDate);
            lessonPayment.setPaymentAmount(paymentAmount);
            lessonPayment.setPaymentTaker(paymentTaker);
            lessonPaymentDAO.update(lessonPayment);
        }
    }

    /**
     * Deletes a specified Lesson Payment from the Lesson Payment database table.
     *
     * @param paymentId The unique Id of the specified lesson payment to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteLessonPayment(int paymentId) throws Exception {
        // Retrieves the desired lesson payment.
        lessonPayment = lessonPaymentDAO.queryForId(paymentId);

        // Deletes the desired Lesson Payment from the Lesson Payment table in the database.
        lessonPaymentDAO.delete(lessonPayment);
    }
}
