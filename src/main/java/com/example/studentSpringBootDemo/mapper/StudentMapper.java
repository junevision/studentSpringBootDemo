package com.example.studentSpringBootDemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentSpringBootDemo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author jun.lei
 * @created 11/02/2025 - 10:13
 * @description
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Select("SELECT * FROM student WHERE email = #{email}")
    Student findStudentByEmail(String email);
}
