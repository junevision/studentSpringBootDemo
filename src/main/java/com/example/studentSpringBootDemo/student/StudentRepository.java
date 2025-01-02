package com.example.studentSpringBootDemo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jun.lei
 * @created 31/12/2024 - 15:53
 * @description
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
