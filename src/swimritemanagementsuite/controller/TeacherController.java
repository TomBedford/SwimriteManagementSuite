/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimritemanagementsuite.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.util.List;
import java.sql.SQLException;
import swimritemanagementsuite.model.Day;
import swimritemanagementsuite.model.Teacher;

/**
 *
 * @author Bedford
 */
public class TeacherController {
    
    public TeacherController() {
        
    }
    
    public List<Teacher> getTeachersForDay(Day day) throws SQLException {
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
        List<Teacher> teacherList = DatabaseManager.teacherDAO.query(preparedQuery);
        
        return teacherList;
    }
}
