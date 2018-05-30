package model.dao.impl.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateController {
    private SessionFactory ourSessionFactory;
    private final HibernateDaoFactory hibernateDaoFactory;
    private static HibernateController instance = null;

    private HibernateController() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ourSessionFactory = configuration.buildSessionFactory();
            hibernateDaoFactory = new HibernateDaoFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static synchronized HibernateController getInstance() {
        if (instance == null)
            synchronized (HibernateController.class) {
                if (instance == null)
                    instance = new HibernateController();
            }
        return instance;
    }

    public Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public HibernateDaoFactory getHibernateDaoFactory() {
        return hibernateDaoFactory;
    }
}
