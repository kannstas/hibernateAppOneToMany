package nastya.ru;

import nastya.ru.model.Director;
import nastya.ru.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Director director = new Director("Rob Cohen", 74);
            director.addMovies(new Movie("Fast X", 2001));
            director.addMovies(new Movie("The Boy Next Door", 2014));

            session.persist(director);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
