package com.example.studentSpringBootDemo.service;

import com.example.studentSpringBootDemo.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto getStudent(Long studentId);
    List<StudentDto> getStudents();
    StudentDto addNewStudent(StudentDto studentDto);
    void deleteStudent(Long studentId);
    StudentDto updateStudent(StudentDto studentDto);
}
