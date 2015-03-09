package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * The input verifier for the date column in the lesson block table.
 */
public class LessonBlockDateInputVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        // Casts the input component into a JTextField 
        final JTextField field = (JTextField) input;
        
        // Gets the text within the text field and sets it to the text string
        final String text = field.getText();
        
        // If the text is not null
        if (!text.equals("")) {
            // Check that the text only contains numbers and '/' and that the pattern matches DD/MM/YYYY
            if (!text.matches("[0-9/]*") || !text.matches("\\d{2}/\\d{2}/\\d{4}")) {
                // If not, return false
                return false;
            } else {
                // Splits the string using '/'
                final String[] unformattedDate = text.split("/");
                
                // If it is a valid input, convert the date day string into an integer
                final int dateDay = Integer.parseInt(unformattedDate[0]);
                
                // If it is a valid input, convert the date month string into an integer
                final int dateMonth = Integer.parseInt(unformattedDate[1]);
                
                // Checks that both the date and month numbers are valid date numbers
                if (dateDay < 1 || dateDay > 31 || dateMonth < 1 || dateMonth > 12) {
                    // If they're invalid numbers return false
                    return false;
                }
            }
        }
        // Otherwise input is valid so return true
        return true;
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        // Verifies the input in a cell
        final boolean valid = verify(input);
        
        // If the input was not valid display an error message
        if (!valid) {
            JOptionPane.showMessageDialog(null, "<HTML> The date you entered was an <b> <font color='red'> invalid </font> </b>date, please try again. </HTML> \n"
                                            + "The date numbers have to be valid dates!"
                                            + "The date format has to be:  DD/MM/YYYY  (eg. 23/10/1992)"
                                            , "Invalid Date Entered", JOptionPane.OK_OPTION);
        }
        // Returns whether the cell yields focus dependant on valid or invalid input
        return valid;
    }  
}
