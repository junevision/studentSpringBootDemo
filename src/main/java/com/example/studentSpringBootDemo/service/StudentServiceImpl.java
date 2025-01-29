package com.example.studentSpringBootDemo.service;

import com.example.studentSpringBootDemo.model.Student;
import com.example.studentSpringBootDemo.model.dto.StudentDto;
import com.example.studentSpringBootDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jun.lei
 * @created 23/12/2024 - 11:47
 * @description
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));
    }

    @Override
    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream().map(student -> new StudentDto(
                student.getName(),
                student.getEmail(),
                student.getDateOfBirth().toString()
        )).collect(Collectors.toList());
    }

    @Override
    public Student addNewStudent(StudentDto studentDto) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentDto.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email is taken");
        }
        Student student = new Student(
                studentDto.getName(),
                studentDto.getEmail(),
                LocalDate.parse(studentDto.getDateOfBirth())
        );
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public Student updateStudent(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId())
                .orElseThrow(() -> new IllegalStateException("student with id " + studentDto.getId() + " does not exist"));

        if (studentDto.getName() != null && studentDto.getName().length() > 0 && !Objects.equals(student.getName(), studentDto.getName())) {
            student.setName(studentDto.getName());
        }

        if (studentDto.getEmail() != null && studentDto.getEmail().length() > 0 && !Objects.equals(student.getEmail(), studentDto.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentDto.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email is taken");
            }
            student.setEmail(studentDto.getEmail());
        }
        return student;
    }
}
