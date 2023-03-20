package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			// start transaction
			session.beginTransaction();

			//
			int theId = 2;
			Instructor theInstructor = session.get(Instructor.class, theId);

			Course javaCourse = new Course("Java from zéro to héro");
			Course nodejsCourse = new Course("NodeJS from zéro to héro");

			theInstructor.addCoure(javaCourse);
			theInstructor.addCoure(nodejsCourse);

			//
			session.save(javaCourse);
			session.save(nodejsCourse);

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
