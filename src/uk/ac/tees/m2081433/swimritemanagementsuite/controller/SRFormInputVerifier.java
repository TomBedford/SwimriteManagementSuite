package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.AddSRPanel;

/**
 * Validates inputs in Text Fields in the add new Student Record Form.
 */
public class SRFormInputVerifier extends InputVerifier {

    /**
     * Overided method call to verify Text Field inputs from the user when they click out of the text field.
     * @param input The Component to verify the input of.
     * @return boolean as to whether the input from the user was valid or not.
     */
    @Override
    public boolean verify(JComponent input) {
        // Gets the name of the component to verify
        String componentName = input.getName();
        
        // Gets the text contained within the text field
        String fieldText = ((JTextField) input).getText().trim();
    
        /**
         * If else statement to determine which Text Field the user has inputted into.
         * If a text field input is determined to be invalid the text field will turn YELLOW and an appropriate
         * error message will be displayed.
         */
        if (componentName.equals(AddSRPanel.studentNameFieldName)) {
            
            // If the text field contains anything other than a-z and A-Z and spaces or is larger than max characters stored in db column flag as invalid
            if (!fieldText.matches("[a-zA-Z ]+") || fieldText.length() > 255) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.studentNameFieldName, "be empty, or contain anything other than letters and spaces.");
                return false;
            } 
            
        } else if (componentName.equals(AddSRPanel.dobDayFieldName)) {
            
            // If the text field contains anything other numbers and is more than 2 characters in length (valid day) flag as invalid
            if (!fieldText.matches("[0-9]+") || fieldText.length() > 2) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.dobDayFieldName, "be empty, contain anything other than numbers, or be longer than 2 characters in length.");
                return false;
            } else {
                // If it is a valid input, convert the DOB day string into an integer
                int dobDay = Integer.parseInt(fieldText);
                
                // Check if the integer is between 1 and 31 (valid days of the month), if not flag as invalid
                if (dobDay < 1 || dobDay > 31) {
                    input.setBackground(Color.yellow);
                    displayFieldErrorMsg(AddSRPanel.dobDayFieldName, "be an invalid day (must be between 1 - 31).");
                    return false;
                }
            }
            
        } else if (componentName.equals(AddSRPanel.dobMonthFieldName)) {
            
            // If the text field contains anything other numbers and is more than 2 characters in length (valid month) flag as invalid.
            if (!fieldText.matches("[0-9]+") || fieldText.length() > 2) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.dobMonthFieldName, "be empty, contain anything other than numbers, or be longer than 2 characters in length.");
                return false;
            } else {
                // If it is a valid input, convert the DOB month string into an integer
                int dobMonth = Integer.parseInt(fieldText);
                
                // Checks if the integer is between 1 and 12 (valid months of the year), if not flag as invalid
                if (dobMonth < 1 || dobMonth > 12) {
                    input.setBackground(Color.yellow);
                    displayFieldErrorMsg(AddSRPanel.dobMonthFieldName, "be an invalid day (must be between 1 - 12).");
                    return false;
                }
            }
            
        } else if (componentName.equals(AddSRPanel.dobYearFieldName)) {
            
            // If the text field contains anything other numbers and is not 4 characters in length (valid year) flag as invalid.
            if (!fieldText.matches("[0-9]+") || fieldText.length() != 4) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.dobYearFieldName, "be empty, contain anything other than numbers, and must be 4 characters in length (eg. 1998).");
                return false;
            } 
            
        } else if (componentName.equals(AddSRPanel.telephoneNoFieldName)) {
            
            // If the text field contains anything other numbers and is not 11 characters long (valid phone number length) flag as invalid.
            if (!fieldText.matches("[0-9]+") || fieldText.length() != 11) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.telephoneNoFieldName, "be empty, contain anything other than numbers, and must be 11 characters in length (eg. 01642123456).");
                return false;
            } 
            
        } else if (componentName.equals(AddSRPanel.addressLine1FieldName)) {
            
            // If the text field contains anything other a-z or A-Z or numbers or spaces flag as invalid.
            if (!fieldText.matches("[0-9a-zA-Z ]+")) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.addressLine1FieldName, "be empty, or contain anything other than letters, numbers and spaces.");
                return false;
            } 
            
        } else if (componentName.equals(AddSRPanel.addressLine2FieldName)) {
            
            // If the text field contains anything other a-z or A-Z or spaces flag as invalid.
            if (!fieldText.matches("[a-zA-Z ]+")) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.addressLine2FieldName, "be empty, or contain anything other than letters and spaces.");
                return false;
            } 
            
        } else if (componentName.equals(AddSRPanel.addressCityFieldName)) {
            
            // If the text field contains anything other a-z or A-Z or spaces flag as invalid.
            if (!fieldText.matches("[a-zA-Z ]+")) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.addressCityFieldName, "be empty, or contain anything other than letters and spaces.");
                return false;
            } 
            
        } else if (componentName.equals(AddSRPanel.addressCountyFieldName)) {
            
            // If the text field contains anything other a-z or A-Z or spaces flag as invalid.
            if (!fieldText.matches("[a-zA-Z ]+")) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.addressCountyFieldName, "be empty, or contain anything other than letters and spaces.");
                return false;
            } 
            
        } else if (componentName.equals(AddSRPanel.addressPostcodeFieldName)) {
            
            // If the text field contains anything other a-z or A-Z or numbers or spaces and must be between 6 and 9 characters (valid postcode) flag as invalid.
            if (!fieldText.matches("[0-9a-zA-Z ]+") || fieldText.length() < 6 || fieldText.length() > 9) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.addressPostcodeFieldName, "be empty, contain anything other than letters, numbers or spaces, and must be between 6 - 8 characters long (eg. TS1 2PS).");
                return false;
            } 
            
        } else if (componentName.equals(AddSRPanel.parentNameFieldName)) {
            
            // If the text field contains anything other a-z or A-Z or spaces or is larger than max characters stored in db column flag as invalid.
            if (!fieldText.matches("[a-zA-Z ]+") || fieldText.length() > 255) {
                input.setBackground(Color.yellow);
                displayFieldErrorMsg(AddSRPanel.parentNameFieldName, "be empty, or contain anything other than letters and spaces.");
                return false;
            } 
            
        }
    
        // If this is reached the input must be valid so set the field to normal white colour.
        input.setBackground(Color.white);  
        
        // If this is reached the input is valid so return true.
        return true;
    }
    
    /**
     * Displays an appropriate error message if a field contains an invalid input.
     * @param fieldName The field there is an invalid input in.
     * @param fieldError The errors with the validity of this field.
     */
    public void displayFieldErrorMsg(String fieldName, String fieldError) {
        // Displays an error message dialog with the appropriate message.
        JOptionPane.showMessageDialog(null,
                        "The " + fieldName + " cannot: \n"
                                + fieldError,
                        fieldName + " Error",
                        JOptionPane.ERROR_MESSAGE);
    }
}
