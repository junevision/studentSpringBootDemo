package com.example.studentSpringBootDemo.service;

import com.example.studentSpringBootDemo.exception.ErrorCode;
import com.example.studentSpringBootDemo.exception.ServiceException;
import com.example.studentSpringBootDemo.entity.Student;
import com.example.studentSpringBootDemo.dto.StudentDto;
import com.example.studentSpringBootDemo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDto getStudent(Long studentId) {
        Student student = Optional.ofNullable(studentMapper.selectById(studentId))
                .orElseThrow(() -> new ServiceException(ErrorCode.STUDENT_NOT_EXISTS));
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDateOfBirth().toString()
        );
    }

    @Override
    public List<StudentDto> getStudents() {
        return studentMapper.selectList(null).stream().map(student -> new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDateOfBirth().toString()
        )).collect(Collectors.toList());
    }

    @Override
    public StudentDto addNewStudent(StudentDto studentDto) {

        Student existingStudent = studentMapper.findStudentByEmail(studentDto.getEmail());
        if (existingStudent != null) {
            throw new ServiceException(ErrorCode.STUDENT_EMAIL_ALREADY_EXISTS);
        }

        Student student = new Student(
                studentDto.getName(),
                studentDto.getEmail(),
                LocalDate.parse(studentDto.getDateOfBirth())
        );

        studentMapper.insert(student);

        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDateOfBirth().toString()
        );
    }

    @Override
    public void deleteStudent(Long studentId) {
        if (studentMapper.selectById(studentId) == null) {
            throw new ServiceException(ErrorCode.STUDENT_NOT_EXISTS);
        }
        studentMapper.deleteById(studentId);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = Optional.ofNullable(studentMapper.selectById(studentDto.getId()))
                .orElseThrow(() -> new ServiceException(ErrorCode.STUDENT_NOT_EXISTS));

        if (studentDto.getName() != null && !studentDto.getName().isEmpty() && !Objects.equals(student.getName(), studentDto.getName())) {
            student.setName(studentDto.getName());
        }

        if (studentDto.getEmail() != null && !studentDto.getEmail().isEmpty() && !Objects.equals(student.getEmail(), studentDto.getEmail())) {
            Student existingStudent = studentMapper.findStudentByEmail(studentDto.getEmail());
            if (existingStudent != null) {
                throw new ServiceException(ErrorCode.STUDENT_EMAIL_ALREADY_EXISTS);
            }
            student.setEmail(studentDto.getEmail());
        }

        if (studentDto.getDateOfBirth() != null && !studentDto.getDateOfBirth().isEmpty() && !Objects.equals(student.getDateOfBirth(), LocalDate.parse(studentDto.getDateOfBirth()))) {
            student.setDateOfBirth(LocalDate.parse(studentDto.getDateOfBirth()));
        }

        studentMapper.updateById(student);

        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDateOfBirth().toString()
        );
    }
}
