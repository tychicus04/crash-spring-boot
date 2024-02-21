package com.tychicus.cruddemo;

import com.tychicus.cruddemo.dao.AppDAO;
import com.tychicus.cruddemo.entity.Instructor;
import com.tychicus.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
//			createInstructor(appDAO);
//
//			findInstructor(appDAO);
//
//			deleteInstructor(appDAO);
//
//			findInstructDetail(appDAO);

			deleteInstructorDetail(appDAO);
//			System.out.println("Hello World!");
		};
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
