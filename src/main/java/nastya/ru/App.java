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

            Director director = new Director("James Cameron", 69);
            Movie movie = new Movie("Avatar", 2009, director);

            session.persist(director);
            session.persist(movie);

            director.setMovies(new ArrayList<>(Collections.singletonList(movie)));

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
