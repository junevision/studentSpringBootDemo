package com.example.studentSpringBootDemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author jun.lei
 * @created 21/12/2024 - 22:14
 * @description
 */
@Data
@TableName(value = "student")
public class Student {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String email;
    @TableField(value = "date_of_birth")
    private LocalDate dateOfBirth;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
