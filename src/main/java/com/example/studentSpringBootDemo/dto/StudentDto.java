package com.example.studentSpringBootDemo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.Period;

/**
 * @author jun.lei
 * @created 09/01/2025 - 11:10
 * @description
 */
@Data
public class StudentDto {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "email format is not correct")
    private String email;
    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;
    private Integer age;

    public StudentDto() {
    }

    public StudentDto(String name, String email, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(LocalDate.parse(dateOfBirth));
    }

    public StudentDto(Long id, String name, String email, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(LocalDate.parse(dateOfBirth));
    }

    private Integer calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
