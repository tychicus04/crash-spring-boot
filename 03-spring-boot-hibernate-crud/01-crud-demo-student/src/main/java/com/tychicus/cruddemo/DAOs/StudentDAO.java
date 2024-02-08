package com.tychicus.cruddemo.DAOs;

import com.tychicus.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName();

    void update(Student theStudent);

    void delete(Integer id);

    int deleteAll();
}
