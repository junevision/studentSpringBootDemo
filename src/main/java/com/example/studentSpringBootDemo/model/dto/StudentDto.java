package com.example.studentSpringBootDemo.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author jun.lei
 * @created 09/01/2025 - 11:10
 * @description
 */
@Data
public class StudentDto {

    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;
    @Email(message = "email format is not correct")
    private String email;
    @NotEmpty(message = "Date of birth is required")
    private String dateOfBirth;

    public StudentDto() {
    }

    public StudentDto(String name, String email, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public StudentDto(Long id, String name, String email, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
