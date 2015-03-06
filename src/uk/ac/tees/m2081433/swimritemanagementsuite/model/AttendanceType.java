package uk.ac.tees.m2081433.swimritemanagementsuite.model;

/**
 * Enum for the different attendance types a student can have for each lesson block attendance.
 */
public enum AttendanceType {
    /**
     * The student is present for their lesson.
     */
    PRESENT, 
    
    /**
     * The student is absent for their lesson.
     */
    ABSENT, 
    
    /**
     * The student is absent for their lesson, but has been granted a fun swim.
     */
    FUN_SWIM, 
    
    /**
     * The student had been granted a fun swim and has used the fun swim.
     */
    FUN_SWIM_TAKEN
}
