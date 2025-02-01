package com.example.studentSpringBootDemo;

import com.example.studentSpringBootDemo.model.Student;
import com.example.studentSpringBootDemo.model.dto.StudentDto;
import com.example.studentSpringBootDemo.repository.StudentRepository;
import com.example.studentSpringBootDemo.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

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
        verify(studentRepository).findAll();
    }

    @Test
    void canAddNewStudent() {
        StudentDto studentDto = new StudentDto("John", "john@test.com", "2000-01-01");
        studentServiceImpl.addNewStudent(studentDto);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertEquals(studentDto.getName(), capturedStudent.getName());
        assertEquals(studentDto.getEmail(), capturedStudent.getEmail());
        assertEquals(LocalDate.parse(studentDto.getDateOfBirth()), capturedStudent.getDateOfBirth());
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        StudentDto studentDto = new StudentDto("John", "john@test.com", "2000-01-01");
        when(studentRepository.findStudentByEmail(studentDto.getEmail())).thenReturn(Optional.of(new Student()));
        assertThatThrownBy(() -> studentServiceImpl.addNewStudent(studentDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email is taken");
        verify(studentRepository, never()).save(any());
    }

    @Test
    void canDeleteStudent() {
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(true);
        studentServiceImpl.deleteStudent(studentId);
        verify(studentRepository).deleteById(studentId);
    }

    @Test
    void willThrowWhenStudentDoesNotExist() {
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(false);
        assertThatThrownBy(() -> studentServiceImpl.deleteStudent(studentId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with id " + studentId + " does not exist");
        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    void canUpdateStudent() {
        Long studentId = 1L;
        Student student = new Student("John", "john@test.com", LocalDate.of(2000, 1, 1));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
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
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.findStudentByEmail("jane@test.com")).thenReturn(Optional.of(new Student()));
        StudentDto studentDto = new StudentDto(studentId, "Jane", "jane@test.com", "2000-01-01");
        assertThatThrownBy(() -> studentServiceImpl.updateStudent(studentDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email is taken");
    }
}
