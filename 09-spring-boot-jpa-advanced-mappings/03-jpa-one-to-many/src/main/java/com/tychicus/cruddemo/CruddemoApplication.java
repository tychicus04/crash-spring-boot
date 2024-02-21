package com.tychicus.cruddemo;

import com.tychicus.cruddemo.dao.AppDAO;
import com.tychicus.cruddemo.entity.Course;
import com.tychicus.cruddemo.entity.Instructor;
import com.tychicus.cruddemo.entity.InstructorDetail;
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
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCourseForInstructor(appDAO);
//			findInstructorWithCoursesJoinFetch(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
			deleteCourseById(appDAO);
//			System.out.println("Hello World!")
//			;
		};
	}


	private void deleteCourseById(AppDAO appDAO) {
		int theId = 10;

        System.out.println("Finding course id: " + theId);
        Course tempCourse = appDAO.findCourseById(theId);

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
