package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.DatabaseTableController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentAddress;

/**
 * This controller interacts (create, update and delete) with the Student Address table within the database.
 */
public class StudentAddressController implements DatabaseTableController<StudentAddress> {
    
    /**
     * Creates a Student Address in the database using the provided parameter.
     * @param studentAddress The student address to add to the db.
     */
    @Override
    public void create(StudentAddress studentAddress) {
        
        try {
            // Creates the student address in the database
            DatabaseManager.studentAddressDAO.create(studentAddress);
        } catch (SQLException e) {
            System.out.println("createStudentAddress: Error creating the student address.");
        }
         
    }
    
    /**
     * Updates a Student Address in the database using the updated Student Address object provided as a parameter.
     * @param studentAddress The student address with updated values to update in the db table.
     */
    @Override
    public void update(StudentAddress studentAddress) {
        
        try {
            // Updates the student address in the database
            DatabaseManager.studentAddressDAO.update(studentAddress);
        } catch (SQLException e) {
            System.out.println("updateStudentAddress: Error updating the student address.");
        }
         
    }
    
    /**
     * Deletes the provided Student Address (parameter) from the database.
     * @param studentAddress The student address to delete from the database
     */
    @Override
    public void delete(StudentAddress studentAddress) {
        
        try {
            // Deletes the student address in the database
            DatabaseManager.studentAddressDAO.delete(studentAddress);
        } catch (SQLException e) {
            System.out.println("deleteStudentAddress: Error deleting the student address.");
        }
         
    }
}
