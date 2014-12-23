package swimritemanagementsuite;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 * This class represents the Lesson Payment database table holding information about
 * lesson block payments, declaring database fields and specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "LessonPayment")
public class LessonPayment {
    
    /**
     * Primary Key: Auto generated Payment ID number.
     */
    @DatabaseField(columnName = "paymentId", generatedId = true)
    private int paymentId;
    
    /**
     * The type of payment being made (visa, cheque or cash) (field cannot be null).
     */
    @DatabaseField (columnName = "paymentType", canBeNull = false)
    private String paymentType;
    
    /**
     * The date the payment was made on (field cannot be null).
     */
    @DatabaseField (columnName = "paymentDate", canBeNull = false)
    private Date paymentDate;

    /**
     * The amount of money being payed for the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "paymentAmount", canBeNull = false)
    private double paymentAmount;
    
    /**
     * The taker of the payment (receptionists name) (field cannot be null).
     */
    @DatabaseField (columnName = "paymentTaker", canBeNull = false)
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
    public LessonPayment(String paymentType, Date paymentDate, double paymentAmount, String paymentTaker) {
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
        return paymentId;
    }

    /**
     * Accessor to retrieve the type of the payment.
     * @return paymentType The type of payment made.
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Mutator to set the new type of payment.
     * @param paymentType The updated type of payment made.
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Accessor to retrieve the date the payment was made.
     * @return paymentDate The date payment was made.
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Mutator to set the new date the payment was made on.
     * @param paymentDate The updated date payment was made on.
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Accessor to retrieve the amount of payment made.
     * @return paymentAmount The amount of payment made.
     */
    public double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Mutator to set the new amount of payment made.
     * @param paymentAmount The updated amount of payment.
     */
    public void setPaymentAmount(double paymentAmount) {
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