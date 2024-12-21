package com.example.studentSpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class StudentSpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentSpringBootDemoApplication.class, args);
	}

	@GetMapping
	public List<String> hello() {
		return Arrays.asList("Hello", "World");
	}
}
