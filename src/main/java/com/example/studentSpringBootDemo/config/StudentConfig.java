package com.example.studentSpringBootDemo.config;

import com.example.studentSpringBootDemo.entity.Student;
import com.example.studentSpringBootDemo.mapper.StudentMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

/**
 * @author jun.lei
 * @created 02/01/2025 - 11:11
 * @description
 */
@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentMapper studentMapper, JdbcTemplate jdbcTemplate) {
        return args -> {
            // Truncate the student table to reset IDs
            jdbcTemplate.execute("TRUNCATE TABLE student");

            Student paul = new Student(
                    1L,
                    "Paul",
                    "paul@test.com",
                    LocalDate.of(2000, 1, 5)
                    );

            Student alex = new Student(
                    "Alex",
                    "alex@test.com",
                    LocalDate.of(2001, 5, 25)
                    );

            studentMapper.insert(paul);
            studentMapper.insert(alex);
        };
    }
}
