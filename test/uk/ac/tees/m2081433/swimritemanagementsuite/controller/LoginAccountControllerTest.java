/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LoginAccount;

/**
 * A set of unit tests to exercise the LoginAccountController class.
 */
public class LoginAccountControllerTest {
    
    /**
     * The database manager where the all data access object is located.
     */
    static DatabaseManager dbManager;
    
    /**
     * The connection source to the database.
     */
    static JdbcConnectionSource smsConnectionSource;
    
    /**
     * The instance of the login account controller.
     */
    static LoginAccountController instance;
    
    /**
     * Occurs before the classes testing begins.
     */
    @BeforeClass
    public static void setUpClass() {
        dbManager = new DatabaseManager();
        
        smsConnectionSource = dbManager.createDatabaseConnection();
        
        dbManager.setupDatabase(smsConnectionSource);

        dbManager.initializeDaos(smsConnectionSource);
            
        instance = new LoginAccountController();
    }
    
    /**
     * Test of create method, of class LoginAccountController to check that a login account can be successfully 
     * created within the login account database table.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing: create - testing a login account can be created in the database table");
        
        final LoginAccount expectedResult = new LoginAccount("username1", "password", "salt", false, "Hi?", "Hi!");
        
        instance.create(expectedResult);
        
        List<LoginAccount> result = null;
        
        try {
            result = dbManager.loginAccountDAO.queryForMatching(expectedResult);
            
            assertEquals(expectedResult.getUsername(), result.get(0).getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccountControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class LoginAccountController to check records in the login account table can be
     * successfully updated.
     */
    @Test
    public void testUpdate() {
        System.out.println("Testing: update - testing a login account can be updated in the database table");
        
        final LoginAccount la = new LoginAccount("username2", "password", "salt", false, "Hi?", "Hi!");
        
        List<LoginAccount> result = null;
        
        try {
            dbManager.loginAccountDAO.create(la);
            
            result = dbManager.loginAccountDAO.queryForMatching(la);
            
            result.get(0).setUsername("Testing Update");
            
            instance.update(result.get(0));
            
            result = dbManager.loginAccountDAO.queryForMatching(result.get(0));
            
            assertEquals("Testing Update", result.get(0).getUsername());
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccountControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of delete method, of class LoginAccountController to check lesson payment records can be successfully 
     * deleted from the login account table.
     */
    @Test
    public void testDelete() {
        System.out.println("Testing: delete - testing a login account can be deleted in the database table");
        
        final LoginAccount la = new LoginAccount("username3", "password", "salt", false, "Hi?", "Hi!");
        
        List<LoginAccount> result = null;
        
        try {
            dbManager.loginAccountDAO.create(la);
        
            instance.delete(la);
        
            result = dbManager.loginAccountDAO.queryForMatching(la);
            
            assertEquals(0, result.size());
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccountControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAllLoginAccounts method, of class LoginAccountController to check that the method successfully
     * returns all login accounts.
     */
    @Test
    public void testGetAllLoginAccounts() {
        System.out.println("Testing: getAllLoginAccounts - testing that the method successfully returns all login accounts.");
        
        final LoginAccount la1 = new LoginAccount("username4", "password", "salt", false, "Hi?", "Hi!");
        
        final LoginAccount la2 = new LoginAccount("username5", "password", "salt", false, "Hi?", "Hi!");
        
        try {
            dbManager.loginAccountDAO.create(la1);
            dbManager.loginAccountDAO.create(la2);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccountControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        final List<LoginAccount> result = instance.getAllLoginAccounts();
        
        assertNotNull(result);
        assertTrue(result.size() > 1);
    }

    /**
     * Test of getAllAdminLoginAccounts method, of class LoginAccountController to check that this method successfully
     * returns a list of all admin accounts in the database.
     */
    @Test
    public void testGetAllAdminLoginAccounts() {
        System.out.println("Testing: getAllAdminLoginAccounts - test that this method successfully returns a list of all admin accounts in the database.");
        
        final LoginAccount la1 = new LoginAccount("username6", "password", "salt", true, "Hi?", "Hi!");
        
        final LoginAccount la2 = new LoginAccount("username7", "password", "salt", true, "Hi?", "Hi!");
        
        try {
            dbManager.loginAccountDAO.create(la1);
            dbManager.loginAccountDAO.create(la2);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccountControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        final List<LoginAccount> result = instance.getAllAdminLoginAccounts();
        
        for (LoginAccount loginAccount: result) {
            assertTrue(loginAccount.getAdmin());
        }
    }

    /**
     * Test of checkDuplicateUsername method, of class LoginAccountController to check that a username is not a 
     * duplicate of an existing one in the database.
     */
    @Test
    public void testCheckDuplicateUsername() {
        System.out.println("Testing: checkDuplicateUsername - that a username is not a duplicate of an existing one in the database.");
        final String username1 = "";
        final boolean expResult1 = false;
        final boolean result1 = instance.checkDuplicateUsername(username1);
        assertEquals(expResult1, result1);
        

        final LoginAccount la = new LoginAccount("TESTING_DUPLICATE_USERNAME", "password", "salt", true, "Hi?", "Hi!");
        
        try {
            dbManager.loginAccountDAO.create(la);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccountControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        final String username2 = "TESTING_DUPLICATE_USERNAME";
        final boolean expResult2 = false;
        final boolean result2 = instance.checkDuplicateUsername(username1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of authenticateLogin method, of class LoginAccountController to check that a username and password
     * can be authenticated as a valid login account.
     */
    @Test
    public void testAuthenticateLogin() {
        System.out.println("Testing: authenticateLogin - that a username and password can be authenticated as a valid login account.");
        
        final LoginAccount la = new LoginAccount("test_authentication", "f86220c0dfb92d71fd5a165473942c70e83e9a2a8c1242df0bfe160a0f2098524488a268a3c6acd2985cf6d3c5d7e73c933c926063808fe4e32567d3b8891a3e", "212dc1843e9f4a4ed6351f72", true, "Hi?", "Hi!");
        
        try {
            dbManager.loginAccountDAO.create(la);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccountControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final String username = "test_authentication";
        final String password = "123Aa";
        
        final boolean expResult = true;
        
        final boolean result = instance.authenticateLogin(username, password);
        
        assertEquals(expResult, result);
    }
    
}
