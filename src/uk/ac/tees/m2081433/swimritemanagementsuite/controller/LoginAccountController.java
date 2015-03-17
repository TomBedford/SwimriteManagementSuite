package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.DatabaseTableController;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;

/**
 * This controller interacts (create, update and delete, etc.) with the Login Account table within the database.
 */
public class LoginAccountController implements DatabaseTableController<LoginAccount> {
    /**
     * Creates a Login Account in the database using the provided parameter.
     * @param loginAccount The login account to add to the db.
     */
    @Override
    public void create(LoginAccount loginAccount) {
        try {
            // Creates the login account in the database
            DatabaseManager.loginAccountDAO.create(loginAccount);
        } catch (SQLException e) {
            System.out.println("createLoginAccount: Error creating the login account.");
        }
         
    }
    
    /**
     * Updates a Login Account in the database using the updated Login Account object provided as a parameter.
     * @param loginAccount The loginAccount with updated values to update in the db table.
     */
    @Override
    public void update(LoginAccount loginAccount) {
        try {
            // Updates the login account in the database
            DatabaseManager.loginAccountDAO.update(loginAccount);
        } catch (SQLException e) {
            System.out.println("updateLoginAccount: Error updating login account.");
        }
    }
    
    /**
     * Deletes the provided Login Account (parameter) from the database.
     * @param loginAccount The login account to delete from the database
     */
    @Override
    public void delete(LoginAccount loginAccount) {
        try {
            // Deletes the login account in the database
            DatabaseManager.loginAccountDAO.delete(loginAccount);
        } catch (SQLException e) {
            System.out.println("deleteLoginAccount: Error deleting the login account.");
        }
         
    }
    
    /**
     * Gets all login accounts from the login account table in the SMS database.
     * @return list of all teachers
     */
    public List<LoginAccount> getAllLoginAccounts() {
        // List to hold all the login accounts 
        List<LoginAccount> loginAccountList = null;
        
        try {
            // Gets all login accounts from the login account table in the database
            loginAccountList = DatabaseManager.loginAccountDAO.queryForAll();
        } catch (SQLException e) {
            System.out.println("getAllLoginAccounts: Error getting all login accounts.");
        }
        
        return loginAccountList;
    }
    
    /**
     * Gets all administrator accounts from the sms Login Account database table.
     * @return List of all administrator accounts in the login account table.
     */
    public List<LoginAccount> getAllAdminLoginAccounts() {
        // List to hold all the admin login accounts 
        List<LoginAccount> adminLoginAccountList = null;
        
        try {
            // Gets a login account if the admin field is set to true
            adminLoginAccountList = DatabaseManager.loginAccountDAO.queryForEq(LoginAccount.ADMIN_COLUMN_NAME, true);
        } catch (SQLException e) {
            System.out.println("getAllAdminLoginAccounts: Error getting all admin login accounts.");
        }
        
        return adminLoginAccountList;
    }
    
    /**
     * Checks if a given username already exists in the login account database.
     * @param username The username to check if it's in the database.
     * @return boolean was to whether the username is in the database.
     */
    public boolean checkDuplicateUsername(String username) {
        try {
            // Gets a login account if the username equals the one specified
            final List<LoginAccount> loginAccount = DatabaseManager.loginAccountDAO.queryForEq(LoginAccount.USERNAME_COLUMN_NAME, username);

            // If the list is empty then return false
            if (loginAccount.isEmpty()) {
                return false;
            } else {
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("deleteTimeslot: Error deleting the timeslot (controller).");
        }
        return true;
    }
    
    /**
     * Checks the username and password passed in are valid log in details.
     * @param username The username to authenticate
     * @param password The password for the username to authenticate
     * @return whether the username and password passed the authentication process.
     */
    public boolean authenticateLogin(String username, String password) {
        
        // Gets all login accounts from the Login Account database table
        final List<LoginAccount> loginAccountList = getAllLoginAccounts();
        
        // Creates the login account to hold the matching usernames login account
        LoginAccount loginAccount = null;
        
        // Loops through all of the log in accounts finding the matching username and setting the loginAccount to it
        for (LoginAccount lA: loginAccountList) {
            if (lA.getUsername().equals(username)) {
                loginAccount = lA;
            }
        }
        
        // If the login account is null display error message
        if (loginAccount == null) {
            JOptionPane.showMessageDialog(null,
                                "No Login Account matches the username specified.",
                                "Error Username Does Not Exist!",
                    JOptionPane.ERROR_MESSAGE);
            // Else the login account does exist
        } else {

            MessageDigest messageDigest = null;

            try {
                // The message Digest to salt and generate the hash set for the password
                messageDigest = MessageDigest.getInstance("SHA-512");

            } catch (NoSuchAlgorithmException e) {
                System.out.println("Error hashing password for authenticate login");
            }

            if (messageDigest != null) {
                // Adds the salt to the message digest
                messageDigest.update(hexStringToByteArray(loginAccount.getSalt()));

                // Updates the message digest using the password string convereted to a byte array
                messageDigest.update(password.getBytes());

                // Generates the hashed password data
                final byte[] hashedPassword = messageDigest.digest();

                // Creates a string buffer to hold the converted byte array
                final StringBuffer sbPassword = new StringBuffer();

                /**
                 * Algorithm to convert byte array into string resourced from:
                 * http://www.mkyong.com/java/java-md5-hashing-example/
                 */
                for (int i = 0; i < hashedPassword.length; i++) {
                    sbPassword.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
                }

                // Resets the message digest for future hashes
                messageDigest.reset();

                // If the password matches return true as the authentication process was successfull
                if (loginAccount.getPassword().equals(sbPassword.toString())) {
                    
                    return true;
                    
                // Otherwise the password doesnt match so show error    
                } else {
                    JOptionPane.showMessageDialog(null,
                                "The password was incorrect.",
                                "Error Password Incorrect!",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        // If this statement is reached then the login account hasnt been successfully authenticated so return false
        return false;
    }
    
    /**
     * Method to convert a hexadecimal string (the salt) back into a byte array used to hash passwords 
     * and security question answers.
     * @param  s The hex string to convert back into a byte array
     * @return Byte Array
     * source: http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
     */
    public static byte[] hexStringToByteArray(String s) {
        final int len = s.length();
        final byte[] data = new byte[len / 2];
        
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i + 1), 16));
        }
        
        return data;
    }
}
