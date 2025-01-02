package com.example.studentSpringBootDemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author jun.lei
 * @created 02/01/2025 - 11:11
 * @description
 */
@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
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

            studentRepository.saveAll(
                    Arrays.asList(paul, alex)
            );
        };
    }
}
