package com.example.studentSpringBootDemo.controller;

import com.example.studentSpringBootDemo.model.ResponseMessage;
import com.example.studentSpringBootDemo.model.Student;
import com.example.studentSpringBootDemo.model.dto.StudentDto;
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
    public ResponseMessage getStudent(@PathVariable("studentId") Long studentId) {
        Student student = studentService.getStudent(studentId);
        return ResponseMessage.success(student);
    }

    @GetMapping
    public ResponseMessage getStudents() {
        List<StudentDto> students = studentService.getStudents();
        return ResponseMessage.success(students);
    }

    @PostMapping
    public ResponseMessage registerNewStudent(@Validated @RequestBody StudentDto studentDto) {
        Student student = studentService.addNewStudent(studentDto);
        return ResponseMessage.success(student);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseMessage deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseMessage.success();
    }

    @PutMapping
    public ResponseMessage updateStudent(@Validated @RequestBody StudentDto studentDto) {
        Student student = studentService.updateStudent(studentDto);
        return ResponseMessage.success(student);
    }
}
