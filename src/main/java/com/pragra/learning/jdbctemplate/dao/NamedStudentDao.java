package com.pragra.learning.jdbctemplate.dao;

import com.pragra.learning.jdbctemplate.model.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NamedStudentDao implements IStudentDao{

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Student> getAllStudent() {
        return null;
    }

    @Override
    public Optional getByID(int id) {
        return Optional.empty();
    }

    @Override
    public boolean insertStudent(Student student){
        String sql= "insert into student values(:id,:first_name,:last_name;:course,:create_date,:update_date)";
        int update=0;
        try {
            update = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(student));
        }catch (DataAccessException ex){
            ex.printStackTrace();
        }
        return update==1?true:false;

    }

    @Override
    public int createStudentBatch(List<Student> students) {
        return 0;
    }

    @Override
    public boolean deleteStudent(int id) {
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }
}
