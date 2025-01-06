package com.example.studentSpringBootDemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jun.lei
 * @created 23/12/2024 - 11:47
 * @description
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("email is taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalArgumentException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
}
