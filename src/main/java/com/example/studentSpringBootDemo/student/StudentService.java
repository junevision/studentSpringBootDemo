package com.example.studentSpringBootDemo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author jun.lei
 * @created 23/12/2024 - 11:47
 * @description
 */
@Service
public class StudentService {
    public List<Student> getStudents() {
        return Arrays.asList(
                new Student(1L,
                        "Jun",
                        "jun@test.com",
                        LocalDate.of(2000, 1, 5),
                        24
                )
        );
    }
}
