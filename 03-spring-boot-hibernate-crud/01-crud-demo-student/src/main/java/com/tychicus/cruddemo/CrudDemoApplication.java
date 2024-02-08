package com.tychicus.cruddemo;

import com.tychicus.cruddemo.DAOs.StudentDAO;
import com.tychicus.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
			createMultipleStudent(studentDAO);
//			readStudent(studentDAO);
//			queryForFindAllStudents(studentDAO);
//			queryForFindByLastNameStudents(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			deleteAllStudents(studentDAO);
		};
		

	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
        int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student id:" + studentId);
        studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
//		retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);


//		change first name to "John"
		System.out.println("Updating student with id...");
		myStudent.setFirstname("John");


//		Update the student
		studentDAO.update(myStudent);

//		display the updated student
		System.out.println("Updated my student" + myStudent);
	}

	private void queryForFindAllStudents(StudentDAO studentDAO) {

//		get a list of students
		List<Student> theStudents = studentDAO.findAll();

//		display a list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForFindByLastNameStudents(StudentDAO studentDAO) {

//		get a list of students
		List<Student> theStudents = studentDAO.findByLastName();

//		display a list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

//		create the student object
		System.out.println("Creating new student object...");
        Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");

//		save the student object
		System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

//		display id of the saved student
		int theId  = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

//		retrieve the student based on the id: primary key
		System.out.println("Retrieving the student based on the id: "  + theId);
        Student myStudent = studentDAO.findById(theId);

//        display the student
		System.out.println("Found my student: " + myStudent);

//		display the student


	}

	private void createStudent(StudentDAO studentDAO) {

//		create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "pauldoe@gmail.com");

//		save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

//		display id of the student object
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

	private void createMultipleStudent(StudentDAO studentDAO) {

//		create multiple student objects
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("Tychicus", "Clark", "tychicus@gmail.com");
		Student tempStudent2 = new Student("Silas", "Halmilton", "silas@gmail.com");
		Student tempStudent3 = new Student("Jacob", "Hangry", "jacob@gmail.com");

//		save multiple student objects
		System.out.println("Saving multiple students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}
}
