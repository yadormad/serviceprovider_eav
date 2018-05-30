package hibernate.controller;

import model.dao.DaoFactory;
import model.dao.impl.hibernate.HibernateDaoFactory;

public class DaoLoader {
    private static DaoFactory daoFactory;

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            synchronized (DaoLoader.class) {
                if (daoFactory == null) {
                    daoFactory = new HibernateDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
