/**
 * Swimrite Management Suite.
 * @author Thomas Bedford (M2081433)
 * @contact m2081433@live.tees.ac.uk
 * 
 * Teesside University, UK
 * Created for BSc Computing: Final Year Project - Part 1: Artefact 2014/15
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This class represents the Lesson block database table that records an individual lesson
 * date and the students attendance for that lesson, declaring database fields and 
 * specific attributes of the fields. 
 * Accessor and mutator methods are also available for each of the fields 
 * (except a mutator for the auto generated ID).
 */
@DatabaseTable(tableName = "LessonBlock")
public class LessonBlock implements Comparable<LessonBlock> {
    
    /**
     * The column name for the Lesson Block Id.
     */
    public static final String BLOCKID_COLUMN_NAME = "blockId"; 
    
    /**
     * The column name for student record associated with this lesson block.
     */
    public static final String STUDENTRECORD_COLUMN_NAME = "studentRecord"; 
    
    /**
     * The column name for the Lesson Payment.
     */
    public static final String LESSONPAYMENT_COLUMN_NAME = "lessonPayment"; 
    
    /**
     * The column name for the date for the 1st lesson.
     */
    public static final String LESSON1DATE_COLUMN_NAME = "lesson1Date"; 
    
    /**
     * The column name for the attendance type for the 1st lesson.
     */
    public static final String LESSON1ATTENDANCE_COLUMN_NAME = "lesson1Attendance"; 
    
    /**
     * The column name for the date for the 2nd lesson.
     */
    public static final String LESSON2DATE_COLUMN_NAME = "lesson2Date"; 
    
    /**
     * The column name for the attendance type for the 2nd lesson.
     */
    public static final String LESSON2ATTENDANCE_COLUMN_NAME = "lesson2Attendance"; 
    
    /**
     * The column name for the date for the 3rd lesson.
     */
    public static final String LESSON3DATE_COLUMN_NAME = "lesson3Date"; 
    
    /**
     * The column name for the attendance type for the 3rd lesson.
     */
    public static final String LESSON3ATTENDANCE_COLUMN_NAME = "lesson3Attendance"; 
    
    /**
     * The column name for the date for the 4th lesson.
     */
    public static final String LESSON4DATE_COLUMN_NAME = "lesson4Date"; 
    
    /**
     * The column name for the attendance type for the 4th lesson.
     */
    public static final String LESSON4ATTENDANCE_COLUMN_NAME = "lesson4Attendance"; 
    
    /**
     * The column name for the date for the 5th lesson.
     */
    public static final String LESSON5DATE_COLUMN_NAME = "lesson5Date"; 
    
    /**
     * The column name for the attendance type for the 5th lesson.
     */
    public static final String LESSON5ATTENDANCE_COLUMN_NAME = "lesson5Attendance"; 
    
    /**
     * The column name for the date for the 6th lesson.
     */
    public static final String LESSON6DATE_COLUMN_NAME = "lesson6Date"; 
    
    /**
     * The column name for the attendance type for the 6th lesson.
     */
    public static final String LESSON6ATTENDANCE_COLUMN_NAME = "lesson6Attendance"; 
    
    /**
     * The column name for the date for the 7th lesson.
     */
    public static final String LESSON7DATE_COLUMN_NAME = "lesson7Date"; 
    
    /**
     * The column name for the attendance type for the 7th lesson.
     */
    public static final String LESSON7ATTENDANCE_COLUMN_NAME = "lesson7Attendance"; 
    
    /**
     * The column name for the date for the 8th lesson.
     */
    public static final String LESSON8DATE_COLUMN_NAME = "lesson8Date"; 
    
    /**
     * The column name for the attendance type for the 8th lesson.
     */
    public static final String LESSON8ATTENDANCE_COLUMN_NAME = "lesson8Attendance"; 
    
    /**
     * The column name for the date for the 9th lesson.
     */
    public static final String LESSON9DATE_COLUMN_NAME = "lesson9Date"; 
    
    /**
     * The column name for the attendance type for the 1st lesson.
     */
    public static final String LESSON9ATTENDANCE_COLUMN_NAME = "lesson9Attendance"; 
    
    /**
     * The column name for the date for the 10th lesson.
     */
    public static final String LESSON10DATE_COLUMN_NAME = "lesson10Date"; 
    
    /**
     * The column name for the attendance type for the 10th lesson.
     */
    public static final String LESSON10ATTENDANCE_COLUMN_NAME = "lesson10Attendance"; 
    
    
    
    /**
     * Primary Key: Auto generated Lesson Block ID number.
     */
    @DatabaseField(columnName = BLOCKID_COLUMN_NAME, generatedId = true)
    private int blockId;
    
    /**
     * Foreign Key: The student record associated with this lesson block.
     */
    @DatabaseField (columnName = STUDENTRECORD_COLUMN_NAME, foreign = true, foreignAutoRefresh = true)
    private StudentRecord studentRecord;
    
    /**
     * Foreign Key: The payment info of the lesson block.
     */
    @DatabaseField (columnName = LESSONPAYMENT_COLUMN_NAME, foreign = true, foreignAutoRefresh = true)
    private LessonPayment lessonPayment;
    
    /**
     * The date of the first lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON1DATE_COLUMN_NAME)
    private String lesson1Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the first lesson of the block.
     */
    @DatabaseField (columnName = LESSON1ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson1Attendance;
    
    /**
     * The date of the second lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON2DATE_COLUMN_NAME)
    private String lesson2Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the second lesson of the block.
     */
    @DatabaseField (columnName = LESSON2ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson2Attendance;
    
    /**
     * The date of the third lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON3DATE_COLUMN_NAME)
    private String lesson3Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the third lesson of the block.
     */
    @DatabaseField (columnName = LESSON3ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson3Attendance;
    
    /**
     * The date of the fourth lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON4DATE_COLUMN_NAME)
    private String lesson4Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the fourth lesson of the block.
     */
    @DatabaseField (columnName = LESSON4ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson4Attendance;
    
    /**
     * The date of the fifth lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON5DATE_COLUMN_NAME)
    private String lesson5Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the fifth lesson of the block.
     */
    @DatabaseField (columnName = LESSON5ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson5Attendance;
    
    /**
     * The date of the sixth lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON6DATE_COLUMN_NAME)
    private String lesson6Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the sixth lesson of the block.
     */
    @DatabaseField (columnName = LESSON6ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson6Attendance;
    
    /**
     * The date of the seventh lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON7DATE_COLUMN_NAME)
    private String lesson7Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the seventh lesson of the block.
     */
    @DatabaseField (columnName = LESSON7ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson7Attendance;
    
    /**
     * The date of the eighth lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON8DATE_COLUMN_NAME)
    private String lesson8Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the eighth lesson of the block.
     */
    @DatabaseField (columnName = LESSON8ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson8Attendance;
    
    /**
     * The date of the ninth lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON9DATE_COLUMN_NAME)
    private String lesson9Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the ninth lesson of the block.
     */
    @DatabaseField (columnName = LESSON9ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson9Attendance;
    
    /**
     * The date of the tenth lesson of the lesson block.
     */
    @DatabaseField (columnName = LESSON10DATE_COLUMN_NAME)
    private String lesson10Date;
    
    /**
     * An Enum that holds the attendance type of the students attendance for the tenth lesson of the block.
     */
    @DatabaseField (columnName = LESSON10ATTENDANCE_COLUMN_NAME)
    private AttendanceType lesson10Attendance;
    

    
    /**
     * Default constructor of the Lesson Block class.
     */
    public LessonBlock() {
        // ORMLite needs a no-arg constructor
    }

    /**
     * Constructor that sets the student record associated with this lesson block.
     * @param studentRecord The student record associated with this lesson block.
     */
    public LessonBlock(StudentRecord studentRecord) {
        this.studentRecord = studentRecord;
    }
    
    

    /**
     * Accessor to retrieve the auto generated Lesson Block Id.
     * @return blockId The auto generated lesson block Id.
     */
    public int getBlocktId() {
        return blockId;
    }

    /**
     * Accessor to retrieve the student record associated with this lesson block.
     * @return studentRecord The student record associated with this lesson block.
     */
    public StudentRecord getStudentRecord() {
        return studentRecord;
    }

    /**
     * Mutator to set the new student record associated with this lesson block.
     * @param studentRecord The updated student record associated with this lesson block.
     */
    public void setStudentRecord(StudentRecord studentRecord) {
        this.studentRecord = studentRecord;
    }

    /**
     * Accessor to retrieve the payment info of the lesson block.
     * @return lessonPayment The payment info of the lesson block.
     */
    public LessonPayment getLessonPayment() {
        return lessonPayment;
    }

    /**
     * Mutator to set the new payment info of the lesson block.
     * @param lessonPayment The updated payment info of the lesson block.
     */
    public void setLessonPayment(LessonPayment lessonPayment) {
        this.lessonPayment = lessonPayment;
    }

    /**
     * Accessor to retrieve the date of lesson 1.
     * @return lesson1Date The date of lesson 1.
     */
    public String getLesson1Date() {
        return lesson1Date;
    }

    /**
     * Mutator to set the new date of lesson 1.
     * @param lesson1Date the updated date of lesson 1.
     */
    public void setLesson1Date(String lesson1Date) {
        this.lesson1Date = lesson1Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 1.
     * @return lesson1Attendance Enum as to their attendance type for lesson 1.
     */
    public AttendanceType getLesson1Attendance() {
        return lesson1Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 1.
     * @param lesson1Attendance The updated attendance record for lesson 1.
     */
    public void setLesson1Attendance(AttendanceType lesson1Attendance) {
        this.lesson1Attendance = lesson1Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 2.
     * @return lesson2Date The date of lesson 2.
     */
    public String getLesson2Date() {
        return lesson2Date;
    }

    /**
     * Mutator to set the new date of lesson 2.
     * @param lesson2Date the updated date of lesson 2.
     */
    public void setLesson2Date(String lesson2Date) {
        this.lesson2Date = lesson2Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 2.
     * @return lesson2Attendance Enum as to their attendance type for lesson 2.
     */
    public AttendanceType getLesson2Attendance() {
        return lesson2Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 2.
     * @param lesson2Attendance The updated attendance record for lesson 2.
     */
    public void setLesson2Attendance(AttendanceType lesson2Attendance) {
        this.lesson2Attendance = lesson2Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 3.
     * @return lesson3Date The date of lesson 3.
     */
    public String getLesson3Date() {
        return lesson3Date;
    }

    /**
     * Mutator to set the new date of lesson 3.
     * @param lesson3Date the updated date of lesson 3.
     */
    public void setLesson3Date(String lesson3Date) {
        this.lesson3Date = lesson3Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 3.
     * @return lesson3Attendance Enum as to their attendance type for lesson 3.
     */
    public AttendanceType getLesson3Attendance() {
        return lesson3Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 3.
     * @param lesson3Attendance The updated attendance record for lesson 3.
     */
    public void setLesson3Attendance(AttendanceType lesson3Attendance) {
        this.lesson3Attendance = lesson3Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 4.
     * @return lesson4Date The date of lesson 4.
     */
    public String getLesson4Date() {
        return lesson4Date;
    }

    /**
     * Mutator to set the new date of lesson 4.
     * @param lesson4Date the updated date of lesson 4.
     */
    public void setLesson4Date(String lesson4Date) {
        this.lesson4Date = lesson4Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 4.
     * @return lesson4Attendance Enum as to their attendance type for lesson 4.
     */
    public AttendanceType getLesson4Attendance() {
        return lesson4Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 4.
     * @param lesson4Attendance The updated attendance record for lesson 4.
     */
    public void setLesson4Attendance(AttendanceType lesson4Attendance) {
        this.lesson4Attendance = lesson4Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 5.
     * @return lesson5Date The date of lesson 5.
     */
    public String getLesson5Date() {
        return lesson5Date;
    }

    /**
     * Mutator to set the new date of lesson 5.
     * @param lesson5Date the updated date of lesson 5.
     */
    public void setLesson5Date(String lesson5Date) {
        this.lesson5Date = lesson5Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 5.
     * @return lesson5Attendance Enum as to their attendance type for lesson 5.
     */
    public AttendanceType getLesson5Attendance() {
        return lesson5Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 5.
     * @param lesson5Attendance The updated attendance record for lesson 5.
     */
    public void setLesson5Attendance(AttendanceType lesson5Attendance) {
        this.lesson5Attendance = lesson5Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 6.
     * @return lesson6Date The date of lesson 6.
     */
    public String getLesson6Date() {
        return lesson6Date;
    }

    /**
     * Mutator to set the new date of lesson 6.
     * @param lesson6Date the updated date of lesson 6.
     */
    public void setLesson6Date(String lesson6Date) {
        this.lesson6Date = lesson6Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 6.
     * @return lesson6Attendance Enum as to their attendance type for lesson 6.
     */
    public AttendanceType getLesson6Attendance() {
        return lesson6Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 6.
     * @param lesson6Attendance The updated attendance record for lesson 6.
     */
    public void setLesson6Attendance(AttendanceType lesson6Attendance) {
        this.lesson6Attendance = lesson6Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 7.
     * @return lesson7Date The date of lesson 7.
     */
    public String getLesson7Date() {
        return lesson7Date;
    }

    /**
     * Mutator to set the new date of lesson 7.
     * @param lesson7Date the updated date of lesson 7.
     */
    public void setLesson7Date(String lesson7Date) {
        this.lesson7Date = lesson7Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 7.
     * @return lesson7Attendance Enum as to their attendance type for lesson 7.
     */
    public AttendanceType getLesson7Attendance() {
        return lesson7Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 7.
     * @param lesson7Attendance The updated attendance record for lesson 7.
     */
    public void setLesson7Attendance(AttendanceType lesson7Attendance) {
        this.lesson7Attendance = lesson7Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 8.
     * @return lesson1Date The date of lesson 8.
     */
    public String getLesson8Date() {
        return lesson8Date;
    }

    /**
     * Mutator to set the new date of lesson 8.
     * @param lesson8Date the updated date of lesson 8.
     */
    public void setLesson8Date(String lesson8Date) {
        this.lesson8Date = lesson8Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 8.
     * @return lesson8Attendance Enum as to their attendance type for lesson 8.
     */
    public AttendanceType getLesson8Attendance() {
        return lesson8Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 8.
     * @param lesson8Attendance The updated attendance record for lesson 8.
     */
    public void setLesson8Attendance(AttendanceType lesson8Attendance) {
        this.lesson8Attendance = lesson8Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 9.
     * @return lesson9Date The date of lesson 9.
     */
    public String getLesson9Date() {
        return lesson9Date;
    }

    /**
     * Mutator to set the new date of lesson 9.
     * @param lesson9Date the updated date of lesson 9.
     */
    public void setLesson9Date(String lesson9Date) {
        this.lesson9Date = lesson9Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 9.
     * @return lesson9Attendance Enum as to their attendance type for lesson 9.
     */
    public AttendanceType getLesson9Attendance() {
        return lesson9Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 9.
     * @param lesson9Attendance The updated attendance record for lesson 9.
     */
    public void setLesson9Attendance(AttendanceType lesson9Attendance) {
        this.lesson9Attendance = lesson9Attendance;
    }

    /**
     * Accessor to retrieve the date of lesson 10.
     * @return lesson10Date The date of lesson 10.
     */
    public String getLesson10Date() {
        return lesson10Date;
    }

    /**
     * Mutator to set the new date of lesson 10.
     * @param lesson10Date the updated date of lesson 10.
     */
    public void setLesson10Date(String lesson10Date) {
        this.lesson10Date = lesson10Date;
    }

    /**
     * Accessor to retrieve whether the student attended lesson 10.
     * @return lesson10Attendance Enum as to their attendance type for lesson 10.
     */
    public  AttendanceType getLesson10Attendance() {
        return lesson10Attendance;
    }

    /**
     * Mutator to set the new attendance record of lesson 10.
     * @param lesson10Attendance The updated attendance record for lesson 10.
     */
    public void setLesson10Attendance(AttendanceType lesson10Attendance) {
        this.lesson10Attendance = lesson10Attendance;
    }
    
    /**
     * Overided method to compare and sort Lesson Blocks by their Lesson Block ID (in descending order).
     * @param o The Lesson Block to compare against.
     * @return Int - Result changes depending on the outcome of the comparison.
     */
    @Override
    public int compareTo(LessonBlock o) {
        // If the teacher id of this teacher is larger than the teacher params teacher id passed in return 1.
        if (this.getBlocktId() > o.getBlocktId()) {
            return -1;
            
        // If the teacher id of this teacher is equal to the teacher params teacher id passed in return 0.
        } else if (this.getBlocktId() == o.getBlocktId()) {
            return 0;
           
        // Otherwise the teacher params teacher id passed in is larger so return -1.
        } else {
            return -1;
        } 
    }
}