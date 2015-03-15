package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import uk.ac.tees.m2081433.swimritemanagementsuite.view.AddLoginAccountForm;

/**
 * The input verifier for the add Login Account form.
 */
public class LoginAccountFormInputVerifier extends InputVerifier {

    /**
     * Overridden method call to verify Text Field inputs from the user when they click out of the text field.
     *
     * @param input The Component to verify the input of.
     * @return true Boolean is always returned true so the user can click out of the form field.
     */
    @Override
    public boolean verify(JComponent input) {
        // Casts the input to a JTextField
        final JTextField inputEditable = (JTextField) input;

        // Gets the name of the component to verify
        final String componentName = input.getName();

        // Gets the text contained within the text field
        final String fieldText = ((JTextField) input).getText().trim();

        /**
         * If else statement to determine which Text Field the user has inputted into. If a text field input is
         * determined to be invalid the text field will turn YELLOW and an appropriate error message will be displayed.
         */
        if (componentName.equals(AddLoginAccountForm.usernameFieldName)) {
            // If the text field contains anything other than letters, numbers or underscores
            if (!fieldText.matches("[a-zA-Z0-9_]*") || fieldText.equals("")) {
                input.setBackground(Color.yellow);
                // Always return true to yield focus!!!
                return true;
            }
        } else if (componentName.equals(AddLoginAccountForm.passwordFieldName)) {
            // The text field must contain One upper and lower case letter, a number and be atleast 5 characters long
            if (!fieldText.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$") || fieldText.length() < 5) {
                input.setBackground(Color.yellow);
                return true;
            } 
        } else if (componentName.equals(AddLoginAccountForm.passwordConfirmationFieldName)) {
            // The text field must contain One upper and lower case letter, a number and be atleast 5 characters long
            if (!fieldText.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$") || fieldText.length() < 5) {
                input.setBackground(Color.yellow);
                return true;
            }
        } else if (componentName.equals(AddLoginAccountForm.securityQuestionFieldName)) {
            // If the text field is empty
            if (fieldText.equals("")) {
                input.setBackground(Color.yellow);
                return true;
            }
        } else if (componentName.equals(AddLoginAccountForm.securityQuestionAnswerFieldName)) {
            // If the text field is empty
            if (fieldText.equals("")) {
                input.setBackground(Color.yellow);
                return true;
            }
        }

        // If this statement is reached then it is a valid input so reset the background to white and return true
        input.setBackground(Color.white);
        return true;
    }
}
