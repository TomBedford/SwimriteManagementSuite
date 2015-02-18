package swimritemanagementsuite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.Date;

/**
 * This class manages the access to the database and database creation and maintenance.
 *
 * @author Thomas Bedford (m2081433)
 */
public class DatabaseManager {

    // DATABASE ACCESS OBJECTS FOR EACH DATABASE TABLE.
    /**
     * The URL that links to the Online MySQL Swimrite Management Suite database.
     */
    private final static String DATABASE_URL = "jdbc:mysql://localhost:8888/SwimriteManagementSuite";

    /**
     * This is the username for the MySql database.
     */
    private final String dbUsername = "root";

    /**
     * This is the password for the MySql database.
     */
    private final String dbPassword = "root";

    /**
     * The data access object used to access the Attendance Record table in the mySQL database.
     */
    private Dao<AttendanceRecord, Integer> attendanceRecordDao;

    /**
     * The data access object used to access the Swimming Classes table in the mySQL database.
     */
    private Dao<SwimmingClasses, Integer> swimmingClassDao;

    /**
     * The data access object used to access the Lesson Block table in the mySQL database.
     */
    private Dao<LessonBlock, Integer> lessonBlockDao;

    /**
     * The data access object used to access the Lesson Payment table in the mySQL database.
     */
    private Dao<LessonPayment, Integer> lessonPaymentDao;

    /**
     * The data access object used to access the Student Record table in the mySQL database.
     */
    private Dao<StudentRecord, Integer> studentRecordDao;

    /**
     * The data access object used to access the Teacher table in the mySQL database.
     */
    private Dao<Teacher, Integer> teacherDao;

    /**
     * The data access object used to access the Timeslot table in the mySQL database.
     */
    private Dao<Timeslot, Integer> timeslotDao;

    // DATABASE TABLE OBJECTS
    /**
     * Attendance Record object used for creating, updating and deleting attendance records from the database.
     */
    AttendanceRecord attendanceRecord;

    /**
     * Swimming Classes object used for creating, updating and deleting swimming classes from the database.
     */
    SwimmingClasses swimmingClass;

    /**
     * Lesson Block object used for creating, updating and deleting lesson blocks from the database.
     */
    LessonBlock lessonBlock;

    /**
     * Lesson Payment object used for creating, updating and deleting lesson payments from the database.
     */
    LessonPayment lessonPayment;

    /**
     * Student Record object used for creating, updating and deleting student records from the database.
     */
    StudentRecord studentRecord;

    /**
     * Teacher object used for creating, updating and deleting teachers from the database.
     */
    Teacher teacher;

    /**
     * Timeslot object used for creating, updating and deleting timeslots from the database.
     */
    Timeslot timeslot;

    /**
     * Default constructor of the database manager class.
     */
    public DatabaseManager() {

    }

    /**
     * Creates and returns the database JDBC connection source that links to the Swimrite Management Suite online MySQL
     * database.
     *
     * @return connectionSource This is the JDBC connection source that leads to the MySQL database.
     */
    public JdbcConnectionSource createDatabaseConnection() {
        JdbcConnectionSource connectionSource = null;

        try {
            // create our data source.
            connectionSource = new JdbcConnectionSource(DATABASE_URL, dbUsername, dbPassword);
            System.out.println("Connection Source set up correctly");
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("createDatabaseConnection: Error establishing connection source");
        }

        return connectionSource;
    }

    /**
     * Sets up the database if tables have not already been initialized.
     *
     * @param connectionSource The connection to the Swimrite Management Suite MySQL database
     */
    public void setupDatabase(JdbcConnectionSource connectionSource) {

        try {
            //Creates all tables within the database if they do not already exist.
            TableUtils.createTableIfNotExists(connectionSource, AttendanceRecord.class);
            TableUtils.createTableIfNotExists(connectionSource, SwimmingClasses.class);
            TableUtils.createTableIfNotExists(connectionSource, LessonBlock.class);
            TableUtils.createTableIfNotExists(connectionSource, LessonPayment.class);
            TableUtils.createTableIfNotExists(connectionSource, StudentRecord.class);
            TableUtils.createTableIfNotExists(connectionSource, Teacher.class);
            TableUtils.createTableIfNotExists(connectionSource, Timeslot.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            //e.printStackTrace();
            System.out.println("setupDatabase: Error creating database tables");
        }

    }

    /**
     * Initializes all Database Access Objects needed for each table in the database.
     *
     * @param connectionSource The JBDC connection source to the Swimrite Management Suite MySQL database.
     */
    public void initializeDaos(JdbcConnectionSource connectionSource) {
        try {
            attendanceRecordDao = DaoManager.createDao(connectionSource, AttendanceRecord.class);
            swimmingClassDao = DaoManager.createDao(connectionSource, SwimmingClasses.class);
            lessonBlockDao = DaoManager.createDao(connectionSource, LessonBlock.class);
            lessonPaymentDao = DaoManager.createDao(connectionSource, LessonPayment.class);
            studentRecordDao = DaoManager.createDao(connectionSource, StudentRecord.class);
            teacherDao = DaoManager.createDao(connectionSource, Teacher.class);
            timeslotDao = DaoManager.createDao(connectionSource, Timeslot.class);
        } catch (SQLException e) {
            // print stack trace to help diagnose error + appropriate message to console.
            e.printStackTrace();
            System.out.println("initializeDaos: Error initializing the database access objects");
        }
    }

    // CREATE, UPDATE AND DELETE FOR THE ATTENDANCE RECORD DATABASE TABLE.
    /**
     * Creates an Attendance Record and persists it to the database.
     *
     * @throws Exception SQL Exception.
     */
    public void createAttendanceRecord() throws Exception {
        // Create an instance of an Attendance Record
        attendanceRecord = new AttendanceRecord();

        // Persist the Attendance Record object to the database.
        attendanceRecordDao.create(attendanceRecord);
    }

    /**
     *
     * TODO: UPDATE THE TABLE TO HAVE MORE LESSON BLOCK ROWS RATHER THAN REPLACEMENT.
     *
     * Updates a specific Attendance Record within the attendance record database table.
     *
     * @param attendanceId The Attendance Record to update.
     * @param lessonBlock The new lesson block to be added to the Attendance Record.
     * @throws Exception SQL Exception.
     */
    public void updateAttendanceRecord(int attendanceId, LessonBlock lessonBlock) throws Exception {
        // Retrieves the desired attendance record.
        attendanceRecord = attendanceRecordDao.queryForId(attendanceId);

        // If the attendance record object isnt null then the new lesson block is set and the database is updated.
        if (attendanceRecord != null) {
            attendanceRecord.setLessonBlock(lessonBlock);
            attendanceRecordDao.update(attendanceRecord);
        }

    }

    /**
     * Deletes a specified Attendance Record from the attendance record database table.
     *
     * @param attendanceId the attendance record to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteAttendanceRecord(int attendanceId) throws Exception {
        // Retrieves the desired attendance record.
        attendanceRecord = attendanceRecordDao.queryForId(attendanceId);

        // Deletes the desired attendance record.
        attendanceRecordDao.delete(attendanceRecord);
    }

    // CREATE, UPDATE AND DELETE FOR THE LESSON BLOCK DATABASE TABLE.
    /**
     * Creates a lesson block and persists it to the database.
     *
     * @param lessonPayment The payment information for the payment of this lesson block.
     * @param lesson1Date The date of the first lesson.
     * @param lesson2Date The date of the second lesson.
     * @param lesson3Date The date of the third lesson.
     * @param lesson4Date The date of the fourth lesson.
     * @param lesson5Date The date of the fifth lesson.
     * @param lesson6Date The date of the sixth lesson.
     * @param lesson7Date The date of the seventh lesson.
     * @param lesson8Date The date of the eighth lesson.
     * @param lesson9Date The date of the ninth lesson.
     * @param lesson10Date The date of the tenth lesson.
     * @throws Exception SQL exception.
     */
    public void createLessonBlock(LessonPayment lessonPayment, Date lesson1Date, Date lesson2Date, Date lesson3Date, Date lesson4Date,
            Date lesson5Date, Date lesson6Date, Date lesson7Date, Date lesson8Date,
            Date lesson9Date, Date lesson10Date) throws Exception {
        // Create an instance of a lesson block.
        lessonBlock = new LessonBlock(lessonPayment, lesson1Date, lesson2Date, lesson3Date, lesson4Date, lesson5Date,
                lesson6Date, lesson7Date, lesson8Date, lesson9Date, lesson10Date);

        // Persists the Lesson Block object to the database.
        lessonBlockDao.create(lessonBlock);
    }

    /**
     * Updates a specific Lesson Block within the Lesson Block table within the database.
     *
     * @param blockId The unique ID of the lesson block to update.
     * @param lessonPayment The updated lesson payment information of the lesson block.
     * @param lesson1Date The updated date of the first lesson.
     * @param lesson1Attendance The updated attendance boolean of the first lesson.
     * @param lesson2Date The updated date of the second lesson.
     * @param lesson2Attendance The updated attendance boolean of the second lesson.
     * @param lesson3Date The updated date of the third lesson.
     * @param lesson3Attendance The updated attendance boolean of the third lesson.
     * @param lesson4Date The updated date of the fourth lesson.
     * @param lesson4Attendance The updated attendance boolean of the fourth lesson.
     * @param lesson5Date The updated date of the fifth lesson.
     * @param lesson5Attendance The updated attendance boolean of the fifth lesson.
     * @param lesson6Date The updated date of the sixth lesson.
     * @param lesson6Attendance The updated attendance boolean of the sixth lesson.
     * @param lesson7Date The updated date of the seventh lesson.
     * @param lesson7Attendance The updated attendance boolean of the seventh lesson.
     * @param lesson8Date The updated date of the eighth lesson.
     * @param lesson8Attendance The updated attendance boolean of the eighth lesson.
     * @param lesson9Date The updated date of the ninth lesson.
     * @param lesson9Attendance The updated attendance boolean of the ninth lesson.
     * @param lesson10Date The updated date of the tenth lesson.
     * @param lesson10Attendance The updated attendance boolean of the tenth lesson.
     * @throws Exception SQL Exception.
     */
    public void updateLessonBlock(int blockId, LessonPayment lessonPayment, Date lesson1Date, boolean lesson1Attendance, Date lesson2Date, boolean lesson2Attendance,
            Date lesson3Date, boolean lesson3Attendance, Date lesson4Date, boolean lesson4Attendance, Date lesson5Date, boolean lesson5Attendance,
            Date lesson6Date, boolean lesson6Attendance, Date lesson7Date, boolean lesson7Attendance, Date lesson8Date, boolean lesson8Attendance,
            Date lesson9Date, boolean lesson9Attendance, Date lesson10Date, boolean lesson10Attendance) throws Exception {
        // Retrieves the desired Lesson Block from the database.
        lessonBlock = lessonBlockDao.queryForId(blockId);

        // If the Lesson Block object isnt null then the new updated lesson block values are set and updated.
        if (lessonBlock != null) {
            lessonBlock.setLessonPayment(lessonPayment);
            lessonBlock.setLesson1Date(lesson1Date);
            lessonBlock.setLesson1Attendance(lesson1Attendance);
            lessonBlock.setLesson2Date(lesson2Date);
            lessonBlock.setLesson2Attendance(lesson2Attendance);
            lessonBlock.setLesson3Date(lesson3Date);
            lessonBlock.setLesson3Attendance(lesson3Attendance);
            lessonBlock.setLesson4Date(lesson4Date);
            lessonBlock.setLesson4Attendance(lesson4Attendance);
            lessonBlock.setLesson5Date(lesson5Date);
            lessonBlock.setLesson5Attendance(lesson5Attendance);
            lessonBlock.setLesson6Date(lesson6Date);
            lessonBlock.setLesson6Attendance(lesson6Attendance);
            lessonBlock.setLesson7Date(lesson7Date);
            lessonBlock.setLesson7Attendance(lesson7Attendance);
            lessonBlock.setLesson8Date(lesson8Date);
            lessonBlock.setLesson8Attendance(lesson8Attendance);
            lessonBlock.setLesson9Date(lesson9Date);
            lessonBlock.setLesson9Attendance(lesson9Attendance);
            lessonBlock.setLesson10Date(lesson10Date);
            lessonBlock.setLesson10Attendance(lesson10Attendance);
            lessonBlockDao.update(lessonBlock);
        }
    }

    /**
     * Deletes a specified Lesson Block from the lesson block database table.
     *
     * @param blockId the specified lesson block to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteLessonBlock(int blockId) throws Exception {
        // Retrieves the desired lesson block.
        lessonBlock = lessonBlockDao.queryForId(blockId);

        // Deletes the desired Lesson Block from the lesson block table in the database.
        lessonBlockDao.delete(lessonBlock);
    }

    // CREATE, UPDATE AND DELETE FOR THE LESSON PAYMENT DATABASE TABLE.
    /**
     * Creates a Lesson Payment and persists it to the database.
     *
     * @param paymentType The type of payment made (eg, debit, cheque or cash).
     * @param paymentDate The date the payment was made on,
     * @param paymentAmount The amount of payment being made.
     * @param paymentTaker The taker of the payment (receptionists name).
     * @throws Exception SQL Exception.
     */
    public void createLessonPayment(String paymentType, Date paymentDate, double paymentAmount, String paymentTaker) throws Exception {
        // Create an instance of a Lesson Payment.
        lessonPayment = new LessonPayment(paymentType, paymentDate, paymentAmount, paymentTaker);

        // Persist the Lesson Payment object to the database.
        lessonPaymentDao.create(lessonPayment);
    }

    /**
     * Updates a Lesson Payment record in the Lesson Payment database table.
     *
     * @param paymentId The unique ID of the lesson payment to be updated.
     * @param paymentType The updated payment type of the lesson payment.
     * @param paymentDate The updated payment date of the lesson payment.
     * @param paymentAmount The updated payment amount of the lesson payment.
     * @param paymentTaker The updated payment taker of the lesson payment.
     * @throws Exception SQL Exception.
     */
    public void updateLessonPayment(int paymentId, String paymentType, Date paymentDate, double paymentAmount, String paymentTaker) throws Exception {
        // Retrieves the desired lesson payment record from the database.
        lessonPayment = lessonPaymentDao.queryForId(paymentId);

        // If the lesson payment object isnt null then the new updated lesson payment values are set and updated.
        if (lessonPayment != null) {
            lessonPayment.setPaymentType(paymentType);
            lessonPayment.setPaymentDate(paymentDate);
            lessonPayment.setPaymentAmount(paymentAmount);
            lessonPayment.setPaymentTaker(paymentTaker);
            lessonPaymentDao.update(lessonPayment);
        }
    }

    /**
     * Deletes a specified Lesson Payment from the Lesson Payment database table.
     *
     * @param paymentId The unique Id of the specified lesson payment to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteLessonPayment(int paymentId) throws Exception {
        // Retrieves the desired lesson payment.
        lessonPayment = lessonPaymentDao.queryForId(paymentId);

        // Deletes the desired Lesson Payment from the Lesson Payment table in the database.
        lessonPaymentDao.delete(lessonPayment);
    }

    // CREATE, UPDATE AND DELETE FOR THE STUDENT RECORD DATABASE TABLE.
    /**
     * Creates a Student Record and persists it to the database.
     *
     * @param studentName The name of the student.
     * @param studentDOB The date of birth of the student.
     * @param telephoneNo The telephone number of the student.
     * @param studentAddress The home address of the student.
     * @param hasIllness Information as to whether the student has any illnesses/disabilities
     * @param parentName The students parents name.
     * @param abilityLevel The ability level of the student.
     * @param swimmingClass The class the student is in.
     * @param attendance The attendance record of the student.
     * @throws Exception SQL Exception.
     */
    public void createStudentRecord(String studentName, Date studentDOB, int telephoneNo, String studentAddress,
            String hasIllness, String parentName, String abilityLevel, SwimmingClasses swimmingClass,
            AttendanceRecord attendance) throws Exception {
        // Create an instance of a Student Record.
        studentRecord = new StudentRecord(studentName, studentDOB, telephoneNo, studentAddress, hasIllness, parentName, abilityLevel, swimmingClass, attendance);

        // Presist the Student Record object to the database.
        studentRecordDao.create(studentRecord);
    }

    /**
     * Updates a Student Record in the student record database table.
     *
     * @param studentId The unique student ID of the student record to be updated.
     * @param studentName The updated name of the student.
     * @param studentDOB The updated date of birth of the student.
     * @param telephoneNo The updated telephone number of the student.
     * @param studentAddress The updated home address of the student.
     * @param hasIllness The updated information as to whether the student has any illnesses/disabilities.
     * @param parentName The updated name of the students parent.
     * @param abilityLevel The updated ability level of the student.
     * @param swimmingClass The updated swimming class of the student.
     * @param attendance The updated attendance record of the student.
     * @throws Exception SQL Exception.
     */
    public void updateStudentRecord(int studentId, String studentName, Date studentDOB, int telephoneNo, String studentAddress,
            String hasIllness, String parentName, String abilityLevel, SwimmingClasses swimmingClass,
            AttendanceRecord attendance) throws Exception {
        // Retrieves the desired Student Record from the database.
        studentRecord = studentRecordDao.queryForId(studentId);

        // If the student record object isnt null then the new updated student record values are set and updated.
        if (studentRecord != null) {
            studentRecord.setStudentName(studentName);
            studentRecord.setStudentDOB(studentDOB);
            studentRecord.setTelephoneNo(telephoneNo);
            studentRecord.setStudentAddress(studentAddress);
            studentRecord.setHasIllness(hasIllness);
            studentRecord.setParentName(parentName);
            studentRecord.setAbilityLevel(abilityLevel);
            studentRecord.setSwimmingClass(swimmingClass);
            studentRecord.setAttendance(attendance);
            studentRecordDao.update(studentRecord);
        }
    }

    // CREATE, UPDATE AND DELETE FOR THE SWIMMING CLASSES DATABASE TABLE.
    /**
     * Creates a Swimming Class and persists it to the database.
     *
     * @param classType The type of Swimming class it is.
     * @param timeslot The timeslot of the class (day/time)
     * @param teacher The teacher of the class.
     * @param maxCapacity The maximum student capacity of the class.
     * @throws Exception SQL Exception.
     */
    public void createSwimmingClass(String classType, Timeslot timeslot, Teacher teacher, int maxCapacity) throws Exception {
        // Create an instance of a Swimming Class.
        swimmingClass = new SwimmingClasses(classType, timeslot, teacher, maxCapacity);

        // Persist the Swimming Class object to the database.
        swimmingClassDao.create(swimmingClass);
    }

    /**
     * Updates a Swimming class record in the Swimming classes database.
     *
     * @param classId The swimming class to update.
     * @param classType The updated class type of the swimming class.
     * @param timeslot The updated timeslot of the swimming class.
     * @param teacher The updated teacher of the swimming class.
     * @param maxCapacity The updated max capacity of the swimming class.
     * @throws Exception SQL Exception.
     */
    public void updateSwimmingClass(int classId, String classType, Timeslot timeslot, Teacher teacher, int maxCapacity) throws Exception {
        // Retrieves the desired Swimming Class from the database.
        swimmingClass = swimmingClassDao.queryForId(classId);

        // If the swimming class object isnt null then the new updated swimming class values are set and updated.
        if (swimmingClass != null) {
            swimmingClass.setClassType(classType);
            swimmingClass.setTimeslot(timeslot);
            swimmingClass.setTeacher(teacher);
            swimmingClass.setMaxCapacity(maxCapacity);
            swimmingClassDao.update(swimmingClass);
        }
    }

    /**
     * Deletes a specified Swimming Class from the swimming classes database table.
     *
     * @param classId The unique Id of the specified swimming class to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteSwimmingClass(int classId) throws Exception {
        // Retrieves the desired swimming class.
        swimmingClass = swimmingClassDao.queryForId(classId);

        // Deletes the desired swimming class from the swimming classes table in the database.
        swimmingClassDao.delete(swimmingClass);
    }

    // CREATE, UPDATE AND DELETE FOR THE TEACHER DATABASE TABLE.
    /**
     * Create a Teacher and persists it to the teacher database table.
     *
     * @param teacherName The name of the teacher.
     * @throws Exception SQL Exception.
     */
    public void createTeacher(String teacherName) throws Exception {
        // Create an instance of a Teacher.
        teacher = new Teacher(teacherName);

        // Persist the Teacher object to the database.
        teacherDao.create(teacher);
    }

    /**
     * Updates a Teacher record in the teacher database table.
     *
     * @param teacherId The unique ID of the specified teacher record to be updated.
     * @param teacherName The updated name of the teacher.
     * @throws Exception SQL Exception.
     */
    public void updateTeacher(int teacherId, String teacherName) throws Exception {
        // Retrieves the desired Teacher from the database.
        teacher = teacherDao.queryForId(teacherId);

        // If the Teacher object isnt null then the new updated teacher values are set and updated.
        if (teacher != null) {
            teacher.setTeacherName(teacherName);
            teacherDao.update(teacher);
        }
    }

    /**
     * Deletes a specified Teacher from the Teacher database table.
     *
     * @param teacherId The unique ID of the Teacher record to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteTeacher(int teacherId) throws Exception {
        // Retrieves the desired Teacher from the database.
        teacher = teacherDao.queryForId(teacherId);

        // Deletes the desired Teacher from the Teacher table in the database.
        teacherDao.delete(teacher);
    }

    // CREATE, UPDATE AND DELETE FOR THE TIMESLOT DATABASE TABLE.
    /**
     * Create a Timeslot and persists it to the Timeslot database table.
     *
     * @param day The day of the timeslot.
     * @param time The time of the timeslot.
     * @throws Exception SQL Exception.
     */
    public void createTimeslot(String day, int time) throws Exception {
        // Create an instance of a Timeslot.
        timeslot = new Timeslot(day, time);

        // Persist the Timeslot object to the database.
        timeslotDao.create(timeslot);
    }

    /**
     * Updates a Timeslot in the Timeslot database table.
     *
     * @param timeslotId The unique ID of the specified timeslot to be updated.
     * @param day The updated day of the timeslot.
     * @param time The updated time of the timeslot.
     * @throws Exception SQL Exception.
     */
    public void updateTimeslot(int timeslotId, String day, int time) throws Exception {
        // Retrieves the desired Timeslot from the database.
        timeslot = timeslotDao.queryForId(timeslotId);

        // If the Timeslot object isnt null then the new updated timeslot values are set and updated.
        if (timeslot != null) {
            timeslot.setDay(day);
            timeslot.setTime(time);
            timeslotDao.update(timeslot);
        }
    }

    /**
     * Deletes a specified Timeslot from the Timeslot database table.
     *
     * @param timeslotId The unique ID of the specified Timeslot to be deleted.
     * @throws Exception SQL Exception.
     */
    public void deleteTimeslot(int timeslotId) throws Exception {
        // Retrieves the desired Timeslot from the database.
        timeslot = timeslotDao.queryForId(timeslotId);

        // Deletes the desired Timeslot from the Timeslot table in the database.
        timeslotDao.delete(timeslot);
    }
}
