package com.example.studentSpringBootDemo.service;

import com.example.studentSpringBootDemo.model.Student;
import com.example.studentSpringBootDemo.model.dto.StudentDto;

import java.util.List;

public interface StudentService {
    Student getStudent(Long studentId);
    List<StudentDto> getStudents();
    Student addNewStudent(StudentDto studentDto);
    void deleteStudent(Long studentId);
    Student updateStudent(StudentDto studentDto);
}
