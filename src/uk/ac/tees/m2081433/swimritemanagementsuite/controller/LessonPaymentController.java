package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonPayment;

/**
 * This controller interacts (create, update and delete) with the lesson payment table within the database.
 */
public class LessonPaymentController {
    
    /**
     * Creates a lesson payment record in the database using the lesson payment provided as param.
     * @param lessonPayment The lesson payment record to add to the lesson payment database table
     */
    public void createLessonPayment(LessonPayment lessonPayment) {
        try {
            DatabaseManager.lessonPaymentDAO.create(lessonPayment);
        } catch (SQLException e) {
            System.out.println("createLessonPayment: Error creating the lesson payment for the lesson block.");
        }
    }
    
    /**
     * Updates a lesson payment record in the database using the updated lesson payment object provided as a param.
     * @param lessonPayment The lesson payment with updated values to update in the db table.
     */
    public void updateLessonPayment(LessonPayment lessonPayment) {
        try {
            DatabaseManager.lessonPaymentDAO.update(lessonPayment);
        } catch (SQLException e) {
            System.out.println("updateLessonPayment: Error updating lesson payment record (controller).");
        }
    }
    
    /**
     * Deletes a lesson payment record from the database that matches the lesson payment object provided as a param.
     * @param lessonPayment The lesson payment to delete from the database.
     */
    public void deleteLessonPayment(LessonPayment lessonPayment) {
        try {
            DatabaseManager.lessonPaymentDAO.delete(lessonPayment);
        } catch (SQLException e) {
            System.out.println("deleteLessonPayment: Error deleting the lesson payment record (controller).");
        }
    }
    
    /**
     * Formats/Concatenates the payment date params provided into a string suitable for db storage.
     * @param dateDay The day of the date of payment
     * @param dateMonth The month of the date of payment
     * @param dateYear The year of the date of payment
     * @return formattedPaymentDate The formatted date of birth string of the student.
     */
    public String formatpaymentDate(String dateDay, String dateMonth, String dateYear) {
        // If the day of the payment is only 1 character long add 0 to the front of it.
        if (dateDay.length() == 1) {
            dateDay = "0" + dateDay;
        }
        
        // If the month of the payment is only 1 character long add 0 to the front of it.
        if (dateMonth.length() == 1) {
            dateMonth = "0" + dateMonth;
        }
        
        // Concatenates the strings with slashes.
        final String formattedPaymentDate = dateDay + "/" + dateMonth + "/" + dateYear;
        
        return formattedPaymentDate;
    }
    
    /** 
     * Un-format the date of payment string by separating the Day, Month and Year as separate strings.
     * @param paymentDateString The string containing the day, month and year of the lessonblocks payment date
     * @return unformattedPaymentDate array the date of payment separated into 3 strings (day, month and year)
     */
    public String[] unformatPaymentDate(String paymentDateString) {
        
        final String[] unformattedPaymentDate = paymentDateString.split("/");
        
        return unformattedPaymentDate;
    }
}
