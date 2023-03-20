package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			Instructor tempInstructor = new Instructor("Yacine", "BAGHDADLI", "dary@luv2code.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/", "Java Programing");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start transaction
			session.beginTransaction();

			// save the instructor : This will save Instructor details also because of the
			// cascade.all
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			//
			System.out.println("Instructor saved ");
		} finally {
			session.close();
			factory.close();
		}
	}

}
