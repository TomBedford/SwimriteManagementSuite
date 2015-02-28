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
     * The student is ill and is absent for their lesson.
     */
    ILL, 
    
    /**
     * The student is on holiday and is absent for their lesson.
     */
    HOLIDAY
}
