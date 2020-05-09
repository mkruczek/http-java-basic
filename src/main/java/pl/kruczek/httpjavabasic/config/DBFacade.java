package pl.kruczek.httpjavabasic.config;

import org.hibernate.SessionFactory;

//it's not necessary, only for example
public class DBFacade {

    public static SessionFactory getSessionFactory() {
        return DBConnection.getSessionFactory();
    }

    public static void close() {
        DBConnection.close();
    }
}
