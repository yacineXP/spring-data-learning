package com.luv2code.hibernate.demou;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailsDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			// start transaction
			session.beginTransaction();

			// associate the objects
			int id = 1;
			InstructorDetail theInstructorDetail = session.get(InstructorDetail.class, id);
			Instructor theInstructor = theInstructorDetail.getInstructor();

			System.out.println(theInstructor);

			// save the instructor : This will save Instructor details also because of the
			// cascade.all

			// commit transaction
			//session.getTransaction().commit();

			//
			System.out.println("Instructor Details");
		} 
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			
			factory.close();
		}
	}

}
