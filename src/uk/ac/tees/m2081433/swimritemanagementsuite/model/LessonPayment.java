package uk.ac.tees.m2081433.swimritemanagementsuite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 * This class represents the Lesson Payment database table holding information about
 * lesson block payments, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 */
@DatabaseTable(tableName = "LessonPayment")
public class LessonPayment {
    
    /**
     * The column name for the Lesson Payment ID.
     */
    public static final String LESSONPAYMENTID_COLUMN_NAME = "lessonPaymentId"; 
    
    /**
     * The column name for the Payment Type.
     */
    public static final String PAYMENTTYPE_COLUMN_NAME = "paymentType"; 
    
    /**
     * The column name for the Payment Date.
     */
    public static final String PAYMENTDATE_COLUMN_NAME = "paymentDate"; 
    
    /**
     * The column name for the Payment Amount.
     */
    public static final String PAYMENTAMOUNT_COLUMN_NAME = "paymentAmount"; 
    
    /**
     * The column name for the Payment Takers Name.
     */
    public static final String PAYMENTTAKER_COLUMN_NAME = "paymentTaker"; 
    
    
    
    /**
     * Primary Key: Auto generated Payment ID number.
     */
    @DatabaseField(columnName = LESSONPAYMENTID_COLUMN_NAME, generatedId = true)
    private int lessonPaymentId;
    
    /**
     * The type of payment being made (card, cheque or cash) (field cannot be null).
     */
    @DatabaseField (columnName = PAYMENTTYPE_COLUMN_NAME, canBeNull = false)
    private PaymentType paymentType;
    
    /**
     * The date the payment was made on (field cannot be null).
     */
    @DatabaseField (columnName = PAYMENTDATE_COLUMN_NAME, canBeNull = false)
    private String paymentDate;

    /**
     * The amount of money being payed for the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = PAYMENTAMOUNT_COLUMN_NAME, canBeNull = false)
    private String paymentAmount;
    
    /**
     * The taker of the payment (receptionists name) (field cannot be null).
     */
    @DatabaseField (columnName = PAYMENTTAKER_COLUMN_NAME, canBeNull = false)
    private String paymentTaker;
    
    
    
    /**
     * Default constructor of the Lesson Payment class.
     */
    public LessonPayment() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new payment in the lesson payment database table.
     * @param paymentType The type of payment being given.
     * @param paymentDate The date the payment was made.
     * @param paymentAmount The payment amount.
     * @param paymentTaker The taker of the payment.
     */
    public LessonPayment(PaymentType paymentType, String paymentDate, String paymentAmount, String paymentTaker) {
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentTaker = paymentTaker;
    }
    
    

    /**
     * Accessor to retrieve the auto generated payment Id.
     * @return paymentId The auto generated payment Id.
     */
    public int getPaymentId() {
        return lessonPaymentId;
    }

    /**
     * Accessor to retrieve the type of the payment.
     * @return paymentType The type of payment made.
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Mutator to set the new type of payment.
     * @param paymentType The updated type of payment made.
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Accessor to retrieve the date the payment was made.
     * @return paymentDate The date payment was made.
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * Mutator to set the new date the payment was made on.
     * @param paymentDate The updated date payment was made on.
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Accessor to retrieve the amount of payment made.
     * @return paymentAmount The amount of payment made.
     */
    public String getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Mutator to set the new amount of payment made.
     * @param paymentAmount The updated amount of payment.
     */
    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Accessor to retrieve the the name of the payment taker.
     * @return paymentTaker The name of the payment taker.
     */
    public String getPaymentTaker() {
        return paymentTaker;
    }

    /**
     * Mutator to set the new name of the payment taker.
     * @param paymentTaker The updated payment taker
     */
    public void setPaymentTaker(String paymentTaker) {
        this.paymentTaker = paymentTaker;
    }

}