package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import java.sql.SQLException;
import java.util.List;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.LessonBlock;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.StudentRecord;

/**
 *
 */
public class LessonBlockController {
    public List<LessonBlock> getLessonBlocksByStudent(StudentRecord studentRecord) {
        
        List<LessonBlock> studentLessonBlocks = null;
        
        try {
            studentLessonBlocks = DatabaseManager.lessonBlockDAO.queryForEq(LessonBlock.STUDENTRECORD_COLUMN_NAME, studentRecord);
        } catch (SQLException e) {
            System.out.println("getLessonBlocksByStudent: Error getting lesson blocks by the student record.");
        }
        
        return studentLessonBlocks;
    }
}
