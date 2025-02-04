package com.example.studentSpringBootDemo.service;

import com.example.studentSpringBootDemo.exception.ErrorCode;
import com.example.studentSpringBootDemo.exception.ServiceException;
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
                .orElseThrow(() -> new ServiceException(ErrorCode.STUDENT_NOT_EXISTS));
    }

    @Override
    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream().map(student -> new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDateOfBirth().toString()
        )).collect(Collectors.toList());
    }

    @Override
    public Student addNewStudent(StudentDto studentDto) {

        if (studentDto.getName() == null || studentDto.getName().isEmpty()) {
            throw new ServiceException(ErrorCode.PARAM_NOT_VALID);
        }

        if (studentDto.getEmail() == null || studentDto.getEmail().isEmpty()) {
            throw new ServiceException(ErrorCode.PARAM_NOT_VALID);
        }

        if (studentDto.getDateOfBirth() == null || studentDto.getDateOfBirth().isEmpty()) {
            throw new ServiceException(ErrorCode.PARAM_NOT_VALID);
        }

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentDto.getEmail());
        if (studentOptional.isPresent()) {
            throw new ServiceException(ErrorCode.STUDENT_EMAIL_ALREADY_EXISTS);
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
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new ServiceException(ErrorCode.STUDENT_NOT_EXISTS);
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public Student updateStudent(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId())
                .orElseThrow(() -> new ServiceException(ErrorCode.STUDENT_NOT_EXISTS));

        if (studentDto.getName() != null && !studentDto.getName().isEmpty() && !Objects.equals(student.getName(), studentDto.getName())) {
            student.setName(studentDto.getName());
        }

        if (studentDto.getEmail() != null && !studentDto.getEmail().isEmpty() && !Objects.equals(student.getEmail(), studentDto.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentDto.getEmail());
            if (studentOptional.isPresent()) {
                throw new ServiceException(ErrorCode.STUDENT_EMAIL_ALREADY_EXISTS);
            }
            student.setEmail(studentDto.getEmail());
        }

        if (studentDto.getDateOfBirth() != null && !studentDto.getDateOfBirth().isEmpty() && !Objects.equals(student.getDateOfBirth(), LocalDate.parse(studentDto.getDateOfBirth()))) {
            student.setDateOfBirth(LocalDate.parse(studentDto.getDateOfBirth()));
        }

        return student;
    }
}
