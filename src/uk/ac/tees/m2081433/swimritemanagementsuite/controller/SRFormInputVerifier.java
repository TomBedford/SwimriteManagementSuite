package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.AddSRPanel;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.UpdateableSRPanel;

/**
 * Validates inputs in Text Fields in the add new Student Record Form.
 */
public class SRFormInputVerifier extends InputVerifier {

    /**
     * Overided method call to verify Text Field inputs from the user when they click out of the text field.
     * @param input The Component to verify the input of.
     * @return true Boolean is always returned true so the user can click out of the form field.
     */
    @Override
    public boolean verify(JComponent input) {
       
        final JTextField inputEditable = (JTextField) input;
        
        // Only verifies the text field component if its field is editable.
        if (inputEditable.isEditable() == true) {
            // Gets the name of the component to verify
            final String componentName = input.getName();

            // Gets the text contained within the text field
            final String fieldText = ((JTextField) input).getText().trim();

            /**
             * If else statement to determine which Text Field the user has inputted into.
             * If a text field input is determined to be invalid the text field will turn YELLOW and an appropriate
             * error message will be displayed.
             */
            if (componentName.equals(AddSRPanel.studentNameFieldName) || componentName.equals(UpdateableSRPanel.studentNameFieldName)) {

                // If the text field contains anything other than a-z and A-Z and spaces or is larger than max characters stored in db column
                if (!fieldText.matches("[a-zA-Z ]+") || fieldText.length() > 255) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.dobDayFieldName) || componentName.equals(UpdateableSRPanel.dobDayFieldName)) {

                // If the text field contains anything other numbers and is more than 2 characters in length (valid day)
                if (!fieldText.matches("[0-9]+") || fieldText.length() > 2) {
                    input.setBackground(Color.yellow);
                    return true;
                } else {
                    // If it is a valid input, convert the DOB day string into an integer
                    final int dobDay = Integer.parseInt(fieldText);

                    // Check if the integer is between 1 and 31 (valid days of the month)
                    if (dobDay < 1 || dobDay > 31) {
                        input.setBackground(Color.yellow);
                        return true;
                    }
                }

            } else if (componentName.equals(AddSRPanel.dobMonthFieldName) || componentName.equals(UpdateableSRPanel.dobMonthFieldName)) {

                // If the text field contains anything other numbers and is more than 2 characters in length (valid month) 
                if (!fieldText.matches("[0-9]+") || fieldText.length() > 2) {
                    input.setBackground(Color.yellow);
                    return true;
                } else {
                    // If it is a valid input, convert the DOB month string into an integer
                    final int dobMonth = Integer.parseInt(fieldText);

                    // Checks if the integer is between 1 and 12 (valid months of the year)
                    if (dobMonth < 1 || dobMonth > 12) {
                        input.setBackground(Color.yellow);
                        return true;
                    }
                }

            } else if (componentName.equals(AddSRPanel.dobYearFieldName) || componentName.equals(UpdateableSRPanel.dobYearFieldName)) {

                // If the text field contains anything other numbers and is not 4 characters in length (valid year) 
                if (!fieldText.matches("[0-9]+") || fieldText.length() != 4) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.telephoneNoFieldName) || componentName.equals(UpdateableSRPanel.telephoneNoFieldName)) {

                // If the text field contains anything other numbers and is not 11 characters long (valid phone number length) 
                if (!fieldText.matches("[0-9]+") || fieldText.length() != 11) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.addressLine1FieldName) || componentName.equals(UpdateableSRPanel.addressLine1FieldName)) {

                // If the text field contains anything other a-z or A-Z or numbers or spaces
                if (!fieldText.matches("[0-9a-zA-Z ]+")) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.addressLine2FieldName) || componentName.equals(UpdateableSRPanel.addressLine2FieldName)) {

                // If the text field contains anything other a-z or A-Z or spaces 
                if (!fieldText.matches("[a-zA-Z ]+")) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.addressCityFieldName) || componentName.equals(UpdateableSRPanel.addressCityFieldName)) {

                // If the text field contains anything other a-z or A-Z or spaces 
                if (!fieldText.matches("[a-zA-Z ]+")) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.addressCountyFieldName) || componentName.equals(UpdateableSRPanel.addressCountyFieldName)) {

                // If the text field contains anything other a-z or A-Z or spaces 
                if (!fieldText.matches("[a-zA-Z ]+")) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.addressPostcodeFieldName) || componentName.equals(UpdateableSRPanel.addressPostcodeFieldName)) {

                // If the text field contains anything other a-z or A-Z or numbers or spaces and must be between 6 and 9 characters (valid postcode) 
                if (!fieldText.matches("[0-9a-zA-Z ]+") || fieldText.length() < 6 || fieldText.length() > 9) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.hasIllnessFieldName) || componentName.equals(UpdateableSRPanel.hasIllnessFieldName)) {

                // If the text field is empty or is larger than max characters allowed to be stored in db. 
                if (fieldText.length() == 0 || fieldText.length() > 255) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

            } else if (componentName.equals(AddSRPanel.parentNameFieldName) || componentName.equals(UpdateableSRPanel.parentNameFieldName)) {

                // If the text field contains anything other a-z or A-Z or spaces or is larger than max characters stored in db column 
                if (!fieldText.matches("[a-zA-Z ]+") || fieldText.length() > 255) {
                    input.setBackground(Color.yellow);
                    return true;
                } 

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
