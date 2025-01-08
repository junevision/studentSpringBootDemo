package com.example.studentSpringBootDemo.student;

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

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Captor
    private ArgumentCaptor<Student> studentArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void canGetAllStudents() {
        studentService.getStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void canAddNewStudent() {
        Student student = new Student("John", "john@test.com", LocalDate.of(2000, 1, 1));
        studentService.addNewStudent(student);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertEquals(student, capturedStudent);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        Student student = new Student("John", "john@test.com", LocalDate.of(2000, 1, 1));
        when(studentRepository.findStudentByEmail(student.getEmail())).thenReturn(Optional.of(student));
        assertThatThrownBy(() -> studentService.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email is taken");
        verify(studentRepository, never()).save(any());
    }

    @Test
    void canDeleteStudent() {
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(true);
        studentService.deleteStudent(studentId);
        verify(studentRepository).deleteById(studentId);
    }

    @Test
    void willThrowWhenStudentDoesNotExist() {
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(false);
        assertThatThrownBy(() -> studentService.deleteStudent(studentId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with id " + studentId + " does not exist");
        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    void canUpdateStudent() {
        Long studentId = 1L;
        Student student = new Student("John", "john@test.com", LocalDate.of(2000, 1, 1));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        studentService.updateStudent(studentId, "Jane", "jane@test.com");
        assertEquals("Jane", student.getName());
        assertEquals("jane@test.com", student.getEmail());
    }

    @Test
    void willThrowWhenUpdatingToExistingEmail() {
        Long studentId = 1L;
        Student student = new Student("John", "john@test.com", LocalDate.of(2000, 1, 1));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.findStudentByEmail("jane@test.com")).thenReturn(Optional.of(new Student()));
        assertThatThrownBy(() -> studentService.updateStudent(studentId, "Jane", "jane@test.com"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email is taken");
    }
}
