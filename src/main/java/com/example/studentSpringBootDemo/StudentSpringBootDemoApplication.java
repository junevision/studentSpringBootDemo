package com.example.studentSpringBootDemo;

import com.example.studentSpringBootDemo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class StudentSpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentSpringBootDemoApplication.class, args);
    }

    @GetMapping
    public List<Student> hello() {
        return Arrays.asList(new Student(1L, "Jun", "jun@test.com", LocalDate.of(2000, 1, 5), 24));
    }
}
