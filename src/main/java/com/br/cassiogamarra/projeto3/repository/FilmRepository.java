package com.br.cassiogamarra.projeto3.repository;

import com.br.cassiogamarra.projeto3.entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FilmRepository {

    public SessionFactory sessionFactory;

    public boolean setup(String sgbd) {
        Configuration config = new Configuration();
        if(sgbd.equals("mysql")) {
            config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            config.setProperty("hibernate.connection.url", "jdbc:mysql://172.17.0.2:3306/sakila");
            config.setProperty("hibernate.connection.username", "root");
            config.setProperty("hibernate.connection.password", "123123");
            config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        } else if(sgbd.equals("postgresql")) {
            config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            config.setProperty("hibernate.connection.url", "jdbc:postgresql://ec2-107-20-153-39.compute-1.amazonaws.com:5432/d2u6psoupsn9k9");
            config.setProperty("hibernate.connection.username", "gwjuayuvialqga");
            config.setProperty("hibernate.connection.password", "4356cdfc80971c35dee64dd2eb79c3019137e3b962f04ddcc9da190b4bd70676");
            config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        }
        config.addAnnotatedClass(Film.class);
        try{
            sessionFactory = config.buildSessionFactory();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void close() {
        sessionFactory.close();
    }

    public Film findByTitle(String title) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try{
            Film film = session.createQuery("from Film where title='"+title+"'", Film.class).getSingleResult();

            session.close();

            return film;
        } catch(Exception e) {
            return null;
        }
    }

    public List<Film> findALl() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Film> films = session.createQuery("from Film", Film.class).getResultList();
        session.getTransaction().commit();

        return films;
    }
}
