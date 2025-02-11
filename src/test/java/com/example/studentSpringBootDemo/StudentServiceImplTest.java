package com.example.studentSpringBootDemo;

import com.example.studentSpringBootDemo.dto.StudentDto;
import com.example.studentSpringBootDemo.entity.Student;
import com.example.studentSpringBootDemo.exception.ServiceException;
import com.example.studentSpringBootDemo.mapper.StudentMapper;
import com.example.studentSpringBootDemo.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.Serializable;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Captor
    private ArgumentCaptor<Student> studentArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void canGetAllStudents() {
        studentServiceImpl.getStudents();
        verify(studentMapper).selectList(null);
    }

    @Test
    void canAddNewStudent() {
        StudentDto studentDto = new StudentDto(null, "John", "john@test.com", "2000-01-01");
        studentServiceImpl.addNewStudent(studentDto);
        verify(studentMapper).insert(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertEquals(studentDto.getName(), capturedStudent.getName());
        assertEquals(studentDto.getEmail(), capturedStudent.getEmail());
        assertEquals(LocalDate.parse(studentDto.getDateOfBirth()), capturedStudent.getDateOfBirth());
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        StudentDto studentDto = new StudentDto(null, "John", "john@test.com", "2000-01-01");
        when(studentMapper.findStudentByEmail(studentDto.getEmail())).thenReturn(new Student());
        assertThatThrownBy(() -> studentServiceImpl.addNewStudent(studentDto))
                .isInstanceOf(ServiceException.class)
                .hasMessageContaining("Student email already exists");
        verify(studentMapper, never()).insert((Student) any());
    }

    @Test
    void canDeleteStudent() {
        Long studentId = 1L;
        when(studentMapper.selectById(studentId)).thenReturn(new Student());
        studentServiceImpl.deleteStudent(studentId);
        verify(studentMapper).deleteById(studentId);
    }

    @Test
    void willThrowWhenStudentDoesNotExist() {
        Long studentId = 1L;
        when(studentMapper.selectById(studentId)).thenReturn(null);
        assertThatThrownBy(() -> studentServiceImpl.deleteStudent(studentId))
                .isInstanceOf(ServiceException.class)
                .hasMessageContaining("Student not exists");
        verify(studentMapper, never()).deleteById((Serializable) any());
    }

    @Test
    void canUpdateStudent() {
        Long studentId = 1L;
        Student student = new Student("John", "john@test.com", LocalDate.of(2000, 1, 1));
        when(studentMapper.selectById(studentId)).thenReturn(student);
        StudentDto studentDto = new StudentDto(studentId, "Jane", "jane@test.com", "2000-01-01");
        studentServiceImpl.updateStudent(studentDto);
        assertEquals("Jane", student.getName());
        assertEquals("jane@test.com", student.getEmail());
        assertEquals(LocalDate.of(2000, 1, 1), student.getDateOfBirth());
    }

    @Test
    void willThrowWhenUpdatingToExistingEmail() {
        Long studentId = 1L;
        Student student = new Student("John", "john@test.com", LocalDate.of(2000, 1, 1));
        when(studentMapper.selectById(studentId)).thenReturn(student);
        when(studentMapper.findStudentByEmail("jane@test.com")).thenReturn(new Student());
        StudentDto studentDto = new StudentDto(studentId, "Jane", "jane@test.com", "2000-01-01");
        assertThatThrownBy(() -> studentServiceImpl.updateStudent(studentDto))
                .isInstanceOf(ServiceException.class)
                .hasMessageContaining("Student email already exists");
    }
}