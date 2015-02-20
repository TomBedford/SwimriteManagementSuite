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
        
        // the 'password' field must be equal to "qwerty"
        queryBuilder.where().eq(Teacher.WORKMONDAY_FIELD_NAME, true);
        
        // prepare our sql statement
        PreparedQuery<Teacher> preparedQuery = queryBuilder.prepare();
        
        // query for all accounts that have "qwerty" as a password
        List<Teacher> teacherList = DatabaseManager.teacherDAO.query(preparedQuery);
        
        return teacherList;
    }
}
