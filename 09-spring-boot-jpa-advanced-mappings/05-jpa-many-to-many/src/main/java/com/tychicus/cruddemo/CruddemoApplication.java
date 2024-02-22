package com.tychicus.cruddemo;

import com.tychicus.cruddemo.dao.AppDAO;
import com.tychicus.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {

//			createCourseAndStudent(appDAO);

//			findCourseAndStudents(appDAO);

//			findStudentAndCourses(appDAO);

//			addMoreCoursesForStudent(appDAO);

//			deleteCourseById(appDAO);

			deleteStudentById(appDAO);
		};
		
	}

	private void deleteStudentById(AppDAO appDAO) {
		int theId = 1;

        System.out.println("Deleting student id: " + theId);

        appDAO.deleteStudentById(theId);

        System.out.println("DONE!");
	}
	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

//		create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");

        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        Course tempCourse3 = new Course("Pianist - The Romantic Pianist");

//		add courses to student
		System.out.println("Adding more courses...");
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);
		tempStudent.addCourse(tempCourse3);

		System.out.println("Updating student: "+ tempStudent);
		System.out.println("associated courses: " + tempStudent.getCourses());

		appDAO.updateStudent(tempStudent);

		System.out.println("Done!");

	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId = 1;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        System.out.println("Loaded student: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourses());

        System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 10;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded course: " + tempCourse);
        System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudent(AppDAO appDAO) {

//		create a course
		Course tempCourse = new Course("Pacman - How to score one million points");

//		create the student
		Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@gmail.com");

//		add student to the course
		tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

//		save the course and  associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("associated students: " + tempCourse.getStudents());

		appDAO.saveCourse(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourseAndReview(AppDAO appDAO) {
		int theId = 10;

        System.out.println("Deleting course id: " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("DONE!");
	}

	private void retrieveCourseAndReview(AppDAO appDAO) {

//		get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

//		print the course
		System.out.println(tempCourse);

//		print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("DONE!");
	}

	private void createCourseAndReview(AppDAO appDAO) {

//		create a course
		Course tempCourse = new Course("Pacman - How to score one million points");

//		add  some review
		tempCourse.addReview(new Review("Greate course ... loved it!"));
		tempCourse.addReview(new Review("Cool course, job well done!"));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

//		save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.saveCourse(tempCourse);
		System.out.println("DONE!");
	}
	private void deleteCourseById(AppDAO appDAO) {
		int theId = 10;

        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("DONE!");
	}
	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

        System.out.println("Finding course id: " + theId);
        Course tempCourse = appDAO.findCourseById(theId);

        System.out.println("Updating course id: " + theId);
        tempCourse.setTitle("Enjoy the Simple thing!");

        appDAO.updateCourse(tempCourse);

        System.out.println("DONE!");
	}
	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

        appDAO.updateInstructor(tempInstructor);

        System.out.println("DONE!");
	}
	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

//		find  the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("DONE!");
	}
	private void findCourseForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

//		find courses for instructor
		System.out.println("Finding courses for instructor id: "+ theId);
		List<Course> courses = appDAO.findCourseByInstructorId(theId);

//		associated the object
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}
	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}
	private void createInstructorWithCourses(AppDAO appDAO) {
		//		create the instructor
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan@gmail.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.youtube.com",
						"Video Game!!!");

//		associated the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

//		create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball MasterClass");
		Course tempCourse3= new Course("The Romantic pianist");

//		add the courses to instructor
		tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);
        tempInstructor.add(tempCourse3);

//		save the instructor
//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("DONE!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
        System.out.println("Delete InstructorDetail id: " + theId);

        appDAO.deleteInstructorDetailById(theId);

        System.out.println("Done!");
	}
	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Delete Instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}
	private void findInstructor(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding Instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}
	private void createInstructor(AppDAO appDAO) {

		/*
//		create the instructor
		Instructor tempInstructor = new Instructor("Chad", "Daddy", "daddy@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");
		*/

		//		create the instructor
		Instructor tempInstructor = new Instructor("Madhu", "Patel", "patel@gmail.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.luv2code.com/youtube",
						"Piano!!!");

//		associated the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
//		save the instructor
//
//		NOTE: this will also save the details object because of CascadeType.ALL
//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("DONE!");
	}
	private void findInstructDetail(AppDAO appDAO) {
//		get  the  instructor details object
		int theId =  2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

//		print the instructor details
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

//		print the associated instructor
		System.out.println("the associated instructor: "+ tempInstructorDetail.getInstuctor());
	}
}
