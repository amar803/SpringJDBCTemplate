package com.pragra.learning.jdbctemplate;

import com.pragra.learning.jdbctemplate.dao.StudentDao;
import com.pragra.learning.jdbctemplate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class JdbcTemplateApplication {

    @Autowired //Must need it
    private StudentDao dao;

    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            System.out.println(dao.getAllStudent());
            System.out.println(dao.getByID(1));

            Student student=
                    Student.builder()
                            .id(((int) Math.floor(Math.random()*10000)))
                            .firstName("Pranav")
                            .lastName("kartik")
                            .course("Fullstack")
                            .createDate(Instant.now())
                            .updateDate(Instant.now())
                            .build();
            System.out.println("*******************");

            dao.insertStudent(student);
            System.out.println(dao.getAllStudent());

            dao.deleteStudent(2);
            System.out.println("Deleting with ID two");
            System.out.println(dao.getAllStudent());

            List<Student> students=new ArrayList<>();
            int i =0;
            students.add(Student.builder()
                    .id(((int) Math.floor(Math.random()*10000)))
                    .firstName("Rohit")
                    .lastName("asd")
                    .course("Computer science")
                    .createDate(Instant.now())
                    .updateDate(Instant.now())
                    .build());
        };
    }



}
