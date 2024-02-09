package com.tychicus.demo.rest;

import com.tychicus.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

//    define @PostConstruct to load the students data ... only once!
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<Student>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rosie"));
        theStudents.add(new Student("Marry", "Smith"));
    }


//    define endpoints for "/student" return a list of students
    @GetMapping("/student")
    public List<Student> getStudents() {


        return theStudents;
    }

//    define endpoints for "/student/{studentId}" - return student at index
    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

//        just index into the list ... keep it simple for now
        return theStudents.get(studentId);
    }
}
