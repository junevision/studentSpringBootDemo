package com.example.studentSpringBootDemo.controller;

import com.example.studentSpringBootDemo.common.ResponseMessage;
import com.example.studentSpringBootDemo.entity.Student;
import com.example.studentSpringBootDemo.dto.StudentDto;
import com.example.studentSpringBootDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jun.lei
 * @created 23/12/2024 - 11:32
 * @description
 */
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "{studentId}")
    public ResponseMessage<Student> getStudent(@PathVariable("studentId") Long studentId) {
        Student student = studentService.getStudent(studentId);
        return ResponseMessage.success(student);
    }

    @GetMapping
    public ResponseMessage<List<StudentDto>> getStudents() {
        List<StudentDto> students = studentService.getStudents();
        return ResponseMessage.success(students);
    }

    @PostMapping
    public ResponseMessage<Student> registerNewStudent(@Validated @RequestBody StudentDto studentDto) {
        Student student = studentService.addNewStudent(studentDto);
        return ResponseMessage.success(student);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseMessage<?> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseMessage.success();
    }

    @PutMapping
    public ResponseMessage<Student> updateStudent(@Validated @RequestBody StudentDto studentDto) {
        Student student = studentService.updateStudent(studentDto);
        return ResponseMessage.success(student);
    }
}
