package uk.ac.tees.m2081433.swimritemanagementsuite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Login Account database table showing all login accounts
 * that can access the swimrite management system, declaring database fields and specific
 * attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 */
@DatabaseTable(tableName = "LoginAccount")
public class LoginAccount {
    
    /**
     * The column name for the Login Account ID field.
     */
    public static final String LOGINACCOUNTID_COLUMN_NAME = "loginAccountId";
    
    /**
     * The column name for the Login Accounts username field.
     */
    public static final String USERNAME_COLUMN_NAME = "username";
    
    /**
     * The column name for the Login Accounts password field.
     */
    public static final String PASSWORD_COLUMN_NAME = "password";
    
    /**
     * The column name for the Login Accounts salt (for the hashed password) field.
     */
    public static final String SALT_COLUMN_NAME = "salt";
    
    /**
     * The column name for the Login Accounts Admin field.
     */
    public static final String ADMIN_COLUMN_NAME = "admin";
    
    /**
     * The column name for the Login Accounts security question field.
     */
    public static final String SECURITYQUESTION_COLUMN_NAME = "securityQuestion";
    
    /**
     * The column name for the Login Accounts security question answer field.
     */
    public static final String SECURITYQUESTIONANSWER_COLUMN_NAME = "securityQuestionAnswer";
    

    
    /**
     * Primary Key: Auto generated Login Account ID number.
     */
    @DatabaseField(columnName = LOGINACCOUNTID_COLUMN_NAME, generatedId = true)
    private int loginAccountId;
    
    /**
     * The username of the login account (field cannot be null and must be unique).
     */
    @DatabaseField (columnName = USERNAME_COLUMN_NAME, canBeNull = false, unique = true)
    private String username;
    
    /**
     * The hashed password of the login account (field cannot be null).
     */
    @DatabaseField (columnName = PASSWORD_COLUMN_NAME, canBeNull = false)
    private String password;
    
    /**
     * The salt for the login accounts hashed password (field cannot be null).
     */
    @DatabaseField (columnName = SALT_COLUMN_NAME, canBeNull = false)
    private String salt;
    
    /**
     * Whether this login account has Administrator privileges (field cannot be null).
     */
    @DatabaseField (columnName = ADMIN_COLUMN_NAME, canBeNull = false)
    private Boolean admin;
    
    /**
     * The Security Question for the login account (field cannot be null).
     */
    @DatabaseField (columnName = SECURITYQUESTION_COLUMN_NAME, canBeNull = false)
    private String securityQuestion;
    
    /**
     * The Security Question Answer for the login account (field cannot be null).
     */
    @DatabaseField (columnName = SECURITYQUESTIONANSWER_COLUMN_NAME, canBeNull = false)
    private String securityQuestionAnswer;
    
    

    /**
     * Default constructor of the Login Account class.
     */
    public LoginAccount() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new Login Account in the Login Account database table.
     * @param username The user name of the login account
     * @param password The password of the login account
     * @param salt The salt for hashing of the password account
     * @param admin Whether the account is allowed administrator privileges.
     * @param securityQuestion The security question of the login account
     * @param securityQuestionAnswer The security question answer of the login account.
     */
    public LoginAccount(String username, String password, String salt, Boolean admin, String securityQuestion, String securityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.admin = admin;
        this.securityQuestion = securityQuestion;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    
    
    /**
     * Accessor to retrieve the username of the login account.
     * @return username The username of the login account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Mutator to set the new username of the login account.
     * @param username The updated username of the login account.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Accessor to retrieve the password of the login account.
     * @return password The password of the login account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mutator to set the new password of the login account.
     * @param password The updated password of the login account.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Accessor to retrieve the passwords hashing salt for the login account.
     * @return salt The passwords hashing salt for the login account
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Mutator to set the new passwords hashing salt for the login account.
     * @param salt The updated passwords hasing salt for the login account.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Accessor to retrieve whether the login account has administrator privileges.
     * @return admin Boolean as to whether they have admin privileges
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * Mutator to set the new boolean as to whether the login account has administrator privileges.
     * @param admin The updated admin status of the login account.
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * Accessor to retrieve the security question for the login account.
     * @return securityQuestion The security question for the login account
     */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * Mutator to set the new security question for the login account.
     * @param securityQuestion The updated security question for the login account.
     */
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    /**
     * Accessor to retrieve the security question answer for the login account.
     * @return securityQuestionAnswer The security question answer for the login account
     */
    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    /**
     * Mutator to set the new security question answer for the login account.
     * @param securityQuestionAnswer The updated security question answer for the login account.
     */
    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }
}