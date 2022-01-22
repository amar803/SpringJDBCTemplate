package com.pragra.learning.jdbctemplate.dao;

import com.pragra.learning.jdbctemplate.model.Student;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class StudentDao implements IStudentDao{
    private JdbcTemplate jdbcTemplate;

    public StudentDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    //write method for crud operation
    public List<Student> getAllStudent(){
        String sql="SELECT * from STUDENT";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }

    public Optional getByID(int id){
        String sql="SELECT * from STUDENT WHERE ID = ?";
      //  Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
        try {
            Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
            return Optional.ofNullable(student);
        }
        catch (Exception ex){
            log.error("Exception occured while accessing the data {}",ex.getMessage());
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean insertStudent(Student student){
        String sql= "insert into student values(?,?,?,?,?,?)";
        int update=0;
             try {
                update = jdbcTemplate.update(sql, student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getCourse(),
                student.getCreateDate(),
                student.getUpdateDate());
             }catch (DataAccessException ex){
                 ex.printStackTrace();
             }
           return update==1?true:false;

    }

    @Override
    public int createStudentBatch(List<Student> students) {
        String sql= "insert into student values(?,?,?,?,?,?)";

        List<Object []> param=new ArrayList<>();
        students.forEach(student -> param.add(Arrays.asList(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getCourse(),
                student.getCreateDate(),
                student.getUpdateDate()).toArray()));

        int[] ints = jdbcTemplate.batchUpdate(sql, param);
        return Arrays.stream(ints).sum();
      }

    @Override
    public boolean updateStudent(Student student) {

        Optional<Student> studentOptional=getByID(student.getId());

        if(studentOptional.isPresent()) {
            String sql = "Update student SET first_name=?, last_name=? Where id=?";
            int update = jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getId());

            return update==1?true:false;
        }
        else
            return false;

    }

    @Override
    public boolean deleteStudent(int id){
        String sql="delete from STUDENT where id= ?";
        int update = jdbcTemplate.update(sql, id);
        return update==1?true:false;
    }

}
