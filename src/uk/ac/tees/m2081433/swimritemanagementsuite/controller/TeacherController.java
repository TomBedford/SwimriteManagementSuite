/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.m2081433.swimritemanagementsuite.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.util.List;
import java.sql.SQLException;
import java.util.Collections;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Day;
import uk.ac.tees.m2081433.swimritemanagementsuite.model.Teacher;

/**
 *
 * @author Bedford
 */
public class TeacherController {
    
    public TeacherController() {
        
    }
    
    public List<Teacher> getTeachersForDay(Day day) {
        
        List<Teacher> teacherList = null;
        
        try {
        // get our query builder from the DAO
        QueryBuilder<Teacher, Integer> queryBuilder = DatabaseManager.teacherDAO.queryBuilder();
        
        if(day == Day.MONDAY) {
            
            // the 'workMonday' field is equal to true.
            queryBuilder.where().eq(Teacher.WORKMONDAY_COLUMN_NAME, true);
            
        } else if (day == Day.TUESDAY) {
            
            // the 'workTuesday' field is equal to true.
            queryBuilder.where().eq(Teacher.WORKTUESDAY_COLUMN_NAME, true);
            
        } else if (day == Day.WEDNESDAY) {
            
            // the 'workWednesday' field is equal to true.
            queryBuilder.where().eq(Teacher.WORKWEDNESDAY_COLUMN_NAME, true);
            
        } else if (day == Day.THURSDAY) {
            
            // the 'workThursday' field is equal to true.
            queryBuilder.where().eq(Teacher.WORKTHURSDAY_COLUMN_NAME, true);
            
        } else if (day == Day.FRIDAY) {
            
            // the 'workFriday' field is equal to true.
            queryBuilder.where().eq(Teacher.WORKFRIDAY_COLUMN_NAME, true);
            
        } else if (day == Day.SATURDAY) {
            
            // the 'workSaturday' field is equal to true.
            queryBuilder.where().eq(Teacher.WORKSATURDAY_COLUMN_NAME, true);
            
        } else if (day == Day.SUNDAY) {
            
            // the 'workSunday' field is equal to true.
            queryBuilder.where().eq(Teacher.WORKSUNDAY_COLUMN_NAME, true);
            
        }
        
        // prepare our sql statement
        PreparedQuery<Teacher> preparedQuery = queryBuilder.prepare();
        
        // query for all teachers that have the specified work day field set to true.
        teacherList = DatabaseManager.teacherDAO.query(preparedQuery);
        
        } catch (SQLException e) {
            System.out.println("getTeacherssForDay: Error getting teachers for a specific day.");
        }
        
        return teacherList;
    }
    
    /**
     * Sorts a list of teachers by the teacher id of the teacher (Ascending order).
     * @param teacherList - The unsorted list of teachers.
     * @return teacherList - The sorted list of teachers.
     */
    public List<Teacher> sortTeachersByTeacherId(List<Teacher> teacherList) {
        // Calls the overided method CompareTo to sort by the teachers id in ascending order).
        Collections.sort(teacherList);
        
        // Returns the sorted list.
        return teacherList;
    }
    
    
    
}
