package com.pragra.learning.jdbctemplate.dao;

import com.pragra.learning.jdbctemplate.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentDao {

    List<Student> getAllStudent();//get all students
    Optional getByID(int id);
    boolean insertStudent(Student student);
    int createStudentBatch(List<Student> students);
    boolean deleteStudent(int id);
    boolean updateStudent(Student student);
}
