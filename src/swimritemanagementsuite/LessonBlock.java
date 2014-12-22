package swimritemanagementsuite;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 * This class represents the Lesson block database table that records an individual lesson
 * date and the students attendance for that lesson, declaring database fields and 
 * specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 * 
 * @author Thomas Bedford (m2081433)
 */
@DatabaseTable(tableName = "LessonBlock")
public class LessonBlock {
    
    /**
     * Primary Key: Auto generated Lesson Block ID number.
     */
    @DatabaseField(columnName = "blockId", generatedId = true)
    private int blockId;
    
    /**
     * Foreign Key: The payment id of the lesson block.
     */
    @DatabaseField (columnName = "paymentId", foreign = true)
    private int paymentId;
    
    /**
     * The date of the first lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson1Date", canBeNull = false)
    private Date lesson1Date;
    
    /**
     * A boolean that informs whether the student attended the first lesson of the block.
     */
    @DatabaseField (columnName = "lesson1Attendance")
    private boolean lesson1Attendance;
    
    /**
     * The date of the second lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson2Date", canBeNull = false)
    private Date lesson2Date;
    
    /**
     * A boolean that informs whether the student attended the second lesson of the block.
     */
    @DatabaseField (columnName = "lesson2Attendance")
    private boolean lesson2Attendance;
    
    /**
     * The date of the third lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson3Date", canBeNull = false)
    private Date lesson3Date;
    
    /**
     * A boolean that informs whether the student attended the third lesson of the block.
     */
    @DatabaseField (columnName = "lesson3Attendance")
    private boolean lesson3Attendance;
    
    /**
     * The date of the fourth lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson4Date", canBeNull = false)
    private Date lesson4Date;
    
    /**
     * A boolean that informs whether the student attended the fourth lesson of the block.
     */
    @DatabaseField (columnName = "lesson4Attendance")
    private boolean lesson4Attendance;
    
    /**
     * The date of the fifth lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson5Date", canBeNull = false)
    private Date lesson5Date;
    
    /**
     * A boolean that informs whether the student attended the fifth lesson of the block.
     */
    @DatabaseField (columnName = "lesson5Attendance")
    private boolean lesson5Attendance;
    
    /**
     * The date of the sixth lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson6Date", canBeNull = false)
    private Date lesson6Date;
    
    /**
     * A boolean that informs whether the student attended the sixth lesson of the block.
     */
    @DatabaseField (columnName = "lesson6Attendance")
    private boolean lesson6Attendance;
    
    /**
     * The date of the seventh lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson7Date", canBeNull = false)
    private Date lesson7Date;
    
    /**
     * A boolean that informs whether the student attended the seventh lesson of the block.
     */
    @DatabaseField (columnName = "lesson7Attendance")
    private boolean lesson7Attendance;
    
    /**
     * The date of the eighth lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson8Date", canBeNull = false)
    private Date lesson8Date;
    
    /**
     * A boolean that informs whether the student attended the eighth lesson of the block.
     */
    @DatabaseField (columnName = "lesson8Attendance")
    private boolean lesson8Attendance;
    
    /**
     * The date of the ninth lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson9Date", canBeNull = false)
    private Date lesson9Date;
    
    /**
     * A boolean that informs whether the student attended the ninth lesson of the block.
     */
    @DatabaseField (columnName = "lesson9Attendance")
    private boolean lesson9Attendance;
    
    /**
     * The date of the tenth lesson of the lesson block (field cannot be null).
     */
    @DatabaseField (columnName = "lesson10Date", canBeNull = false)
    private Date lesson10Date;
    
    /**
     * A boolean that informs whether the student attended the tenth lesson of the block.
     */
    @DatabaseField (columnName = "lesson10Attendance")
    private boolean lesson10Attendance;
    

    /**
     * Default constructor of the Lesson Block class.
     */
    public LessonBlock() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Parameterized constructor to create a new block of lessons in the Lesson Block database table.
     * @param paymentId The corresponding payment Id of the lesson blocks
     * @param lesson1Date The date of the first lesson of the lesson block.
     * @param lesson2Date The date of the second lesson of the lesson block.
     * @param lesson3Date The date of the third lesson of the lesson block.
     * @param lesson4Date The date of the fourth lesson of the lesson block.
     * @param lesson5Date The date of the fifth lesson of the lesson block.
     * @param lesson6Date The date of the sixth lesson of the lesson block.
     * @param lesson7Date The date of the seventh lesson of the lesson block.
     * @param lesson8Date The date of the eighth lesson of the lesson block.
     * @param lesson9Date The date of the ninth lesson of the lesson block.
     * @param lesson10Date The date of the tenth lesson of the lesson block.
     */
    public LessonBlock(int paymentId, Date lesson1Date, Date lesson2Date, Date lesson3Date, Date lesson4Date,
                        Date lesson5Date, Date lesson6Date, Date lesson7Date, Date lesson8Date, 
                            Date lesson9Date, Date lesson10Date) {
        this.paymentId = paymentId;
        this.lesson1Date = lesson1Date;
        this.lesson2Date = lesson1Date;
        this.lesson3Date = lesson1Date;
        this.lesson4Date = lesson1Date;
        this.lesson5Date = lesson1Date;
        this.lesson6Date = lesson1Date;
        this.lesson7Date = lesson1Date;
        this.lesson8Date = lesson1Date;
        this.lesson9Date = lesson1Date;
        this.lesson10Date = lesson1Date;
        
    }

    /**
     * Accessor to retrieve the auto generated Lesson Block Id.
     * @return blockId The auto generated lesson block Id.
     */
    public int getBlocktId() {
        return blockId;
    }

    /**
     * Accessor to retrieve the payment Id of the lesson block.
     * @return paymentId The payment Id of the lesson block.
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Mutator to set the new payment Id of the lesson block.
     * @param paymentId The updated payment Id of the lesson block.
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Accessor to retrieve the date of lesson 1.
     * @return lesson1Date The date of lesson 1.
     */
    public Date getLesson1Date() {
        return lesson1Date;
    }

    /**
     * Mutator to set the new date of lesson 1.
     * @param lesson1Date the updated date of lesson 1.
     */
    public void setLesson1Date(Date lesson1Date) {
        this.lesson1Date = lesson1Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 1.
     * @return lesson1Attendance Boolean as to whether they attended lesson 1.
     */
    public boolean isLesson1Attendance() {
        return lesson1Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 1.
     * @param lesson1Attendance The updated attendance record for lesson 1.
     */
    public void setLesson1Attendance(boolean lesson1Attendance) {
        this.lesson1Attendance = lesson1Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 2.
     * @return lesson2Date The date of lesson 2.
     */
    public Date getLesson2Date() {
        return lesson2Date;
    }

    /**
     * Mutator to set the new date of lesson 2.
     * @param lesson2Date the updated date of lesson 2.
     */
    public void setLesson2Date(Date lesson2Date) {
        this.lesson2Date = lesson2Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 2.
     * @return lesson2Attendance Boolean as to whether they attended lesson 2.
     */
    public boolean isLesson2Attendance() {
        return lesson2Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 2.
     * @param lesson2Attendance The updated attendance record for lesson 2.
     */
    public void setLesson2Attendance(boolean lesson2Attendance) {
        this.lesson2Attendance = lesson2Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 3.
     * @return lesson3Date The date of lesson 3.
     */
    public Date getLesson3Date() {
        return lesson3Date;
    }

    /**
     * Mutator to set the new date of lesson 3.
     * @param lesson3Date the updated date of lesson 3.
     */
    public void setLesson3Date(Date lesson3Date) {
        this.lesson3Date = lesson3Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 3.
     * @return lesson3Attendance Boolean as to whether they attended lesson 3.
     */
    public boolean isLesson3Attendance() {
        return lesson3Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 3.
     * @param lesson3Attendance The updated attendance record for lesson 3.
     */
    public void setLesson3Attendance(boolean lesson3Attendance) {
        this.lesson3Attendance = lesson3Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 4.
     * @return lesson4Date The date of lesson 4.
     */
    public Date getLesson4Date() {
        return lesson4Date;
    }

    /**
     * Mutator to set the new date of lesson 4.
     * @param lesson4Date the updated date of lesson 4.
     */
    public void setLesson4Date(Date lesson4Date) {
        this.lesson4Date = lesson4Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 4.
     * @return lesson4Attendance Boolean as to whether they attended lesson 4.
     */
    public boolean isLesson4Attendance() {
        return lesson4Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 4.
     * @param lesson4Attendance The updated attendance record for lesson 4.
     */
    public void setLesson4Attendance(boolean lesson4Attendance) {
        this.lesson4Attendance = lesson4Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 5.
     * @return lesson5Date The date of lesson 5.
     */
    public Date getLesson5Date() {
        return lesson5Date;
    }

    /**
     * Mutator to set the new date of lesson 5.
     * @param lesson5Date the updated date of lesson 5.
     */
    public void setLesson5Date(Date lesson5Date) {
        this.lesson5Date = lesson5Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 5.
     * @return lesson5Attendance Boolean as to whether they attended lesson 5.
     */
    public boolean isLesson5Attendance() {
        return lesson5Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 5.
     * @param lesson5Attendance The updated attendance record for lesson 5.
     */
    public void setLesson5Attendance(boolean lesson5Attendance) {
        this.lesson5Attendance = lesson5Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 6.
     * @return lesson6Date The date of lesson 6.
     */
    public Date getLesson6Date() {
        return lesson6Date;
    }

    /**
     * Mutator to set the new date of lesson 6.
     * @param lesson6Date the updated date of lesson 6.
     */
    public void setLesson6Date(Date lesson6Date) {
        this.lesson6Date = lesson6Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 6.
     * @return lesson6Attendance Boolean as to whether they attended lesson 6.
     */
    public boolean isLesson6Attendance() {
        return lesson6Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 6.
     * @param lesson6Attendance The updated attendance record for lesson 6.
     */
    public void setLesson6Attendance(boolean lesson6Attendance) {
        this.lesson6Attendance = lesson6Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 7.
     * @return lesson7Date The date of lesson 7.
     */
    public Date getLesson7Date() {
        return lesson7Date;
    }

    /**
     * Mutator to set the new date of lesson 7.
     * @param lesson7Date the updated date of lesson 7.
     */
    public void setLesson7Date(Date lesson7Date) {
        this.lesson7Date = lesson7Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 7.
     * @return lesson7Attendance Boolean as to whether they attended lesson 7.
     */
    public boolean isLesson7Attendance() {
        return lesson7Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 7.
     * @param lesson7Attendance The updated attendance record for lesson 7.
     */
    public void setLesson7Attendance(boolean lesson7Attendance) {
        this.lesson7Attendance = lesson7Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 8.
     * @return lesson1Date The date of lesson 8.
     */
    public Date getLesson8Date() {
        return lesson8Date;
    }

    /**
     * Mutator to set the new date of lesson 8.
     * @param lesson8Date the updated date of lesson 8.
     */
    public void setLesson8Date(Date lesson8Date) {
        this.lesson8Date = lesson8Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 8.
     * @return lesson8Attendance Boolean as to whether they attended lesson 8.
     */
    public boolean isLesson8Attendance() {
        return lesson8Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 8.
     * @param lesson8Attendance The updated attendance record for lesson 8.
     */
    public void setLesson8Attendance(boolean lesson8Attendance) {
        this.lesson8Attendance = lesson8Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 9.
     * @return lesson9Date The date of lesson 9.
     */
    public Date getLesson9Date() {
        return lesson9Date;
    }

    /**
     * Mutator to set the new date of lesson 9.
     * @param lesson9Date the updated date of lesson 9.
     */
    public void setLesson9Date(Date lesson9Date) {
        this.lesson9Date = lesson9Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 9.
     * @return lesson9Attendance Boolean as to whether they attended lesson 9.
     */
    public boolean isLesson9Attendance() {
        return lesson9Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 9.
     * @param lesson9Attendance The updated attendance record for lesson 9.
     */
    public void setLesson9Attendance(boolean lesson9Attendance) {
        this.lesson9Attendance = lesson9Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 10.
     * @return lesson10Date The date of lesson 10.
     */
    public Date getLesson10Date() {
        return lesson10Date;
    }

    /**
     * Mutator to set the new date of lesson 10.
     * @param lesson10Date the updated date of lesson 10.
     */
    public void setLesson10Date(Date lesson10Date) {
        this.lesson10Date = lesson10Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 10.
     * @return lesson10Attendance Boolean as to whether they attended lesson 10.
     */
    public boolean isLesson10Attendance() {
        return lesson10Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 10.
     * @param lesson10Attendance The updated attendance record for lesson 10.
     */
    public void setLesson10Attendance(boolean lesson10Attendance) {
        this.lesson10Attendance = lesson10Attendance;
    }
    
}