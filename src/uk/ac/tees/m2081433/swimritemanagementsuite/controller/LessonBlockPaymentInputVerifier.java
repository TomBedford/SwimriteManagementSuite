/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.LessonBlockPanel;

/**
 * The input verifier for the lesson blocks lesson payment form.
 */
public class LessonBlockPaymentInputVerifier extends InputVerifier {
    
    /**
     * Overided method call to verify Text Field inputs from the user when they click out of the text field.
     * @param input The Component to verify the input of.
     * @return true Boolean is always returned true so the user can click out of the form field.
     */
    @Override
    public boolean verify(JComponent input) {
        // Cats the input to a JTextField
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
            if (componentName.equals(LessonBlockPanel.paymentAmountFieldName)) {
                // If the text field contains anything other numbers and a decimal point
                if (!fieldText.matches("[0-9.]*") || fieldText.equals("")) {
                    input.setBackground(Color.yellow);
                    // Always return true to yield focus!!!
                    return true;
                }
            } else if (componentName.equals(LessonBlockPanel.paymentDayFieldName)) {
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
            } else if (componentName.equals(LessonBlockPanel.paymentMonthFieldName)) {
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
            } else if (componentName.equals(LessonBlockPanel.paymentYearFieldName)) {
                // If the text field contains anything other numbers and is not 4 characters in length (valid year) 
                if (!fieldText.matches("[0-9]+") || fieldText.length() != 4) {
                    input.setBackground(Color.yellow);
                    return true;
                } 
            } else if (componentName.equals(LessonBlockPanel.paymentTakerFieldName)) {
                // If the text field contains anything other than a-z and A-Z and spaces or is larger than max characters stored in db column
                if (!fieldText.matches("[a-zA-Z ]+") || fieldText.length() > 255) {
                    input.setBackground(Color.yellow);
                    return true;
                } 
            } 
        }
        // If this statement is reached then it is a valid input so reset the background to white and return true
        input.setBackground(Color.white);
        return true;
    }
}
