package db.edu.seu.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernanateUitl {

    private static SessionFactory sessionFactory = getSessionFactory();

    private static SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSession()
    {
        return sessionFactory;
    }
    public static void shutdown()
    {
        sessionFactory.close();
    }

}
