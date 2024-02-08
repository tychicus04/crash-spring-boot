package com.tychicus.cruddemo.DAOs;

import com.tychicus.cruddemo.entity.Student;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);
}
