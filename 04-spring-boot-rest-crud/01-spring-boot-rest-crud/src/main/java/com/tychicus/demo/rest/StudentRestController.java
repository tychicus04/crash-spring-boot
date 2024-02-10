package com.tychicus.demo.rest;

import com.tychicus.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//        check the student again list size
//        just index into the list ... keep it simple for now

        if((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }


        return theStudents.get(studentId);
    }


}
