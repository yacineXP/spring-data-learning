package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			// start transaction
			session.beginTransaction();

			//
			Course tempCourse = new Course("Pacman - How to score One Million Points");
			
			tempCourse.addReview(new Review("Great course ... love it!"));
			tempCourse.addReview(new Review("Cool course"));
			tempCourse.addReview(new Review("Love it!"));
			
			session.save(tempCourse);

			// commit transaction
			session.getTransaction().commit();

			//
			System.out.println("Courses saved ");
		} finally {
			session.close();
			factory.close();
		}
	}

}
