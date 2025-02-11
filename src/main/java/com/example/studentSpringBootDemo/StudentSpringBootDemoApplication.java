package com.example.studentSpringBootDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.studentSpringBootDemo.mapper")
public class StudentSpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentSpringBootDemoApplication.class, args);
    }
}
