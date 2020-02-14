package hibernate.demo;

import hibernate.entity.Course;
import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;
import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] argz) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create objects
            Instructor tempInstructor1 =
                    new Instructor("Vladimir", "Makarov", "WW@luv2code.com");

            InstructorDetail tempInstructorDetail1 =
                    new InstructorDetail("http://www.Conflict.com/youtube", "Will of single man");

            Instructor tempInstructor2 =
                    new Instructor("Mikhail", "Harkov", "Zaton@luv2code.com");

            InstructorDetail tempInstructorDetail2 =
                    new InstructorDetail("http://www.Survival.com/youtube", "Outdoor camping");

            // associate objects
            tempInstructor1.setInstructorDetail(tempInstructorDetail1);
            tempInstructor2.setInstructorDetail(tempInstructorDetail2);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            System.out.println("Saving instructor: " + tempInstructor1);
            session.save(tempInstructor1);
            System.out.println("Saving instructor: " + tempInstructor2);
            session.save(tempInstructor2);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {

            // clean up
            session.close();

            factory.close();
        }
    }
}