package model.dao.impl.hibernate;

import org.hibernate.Session;

public interface ProviderEntity<O> {
    O toObject(Session session);
    void fromObject(O object, Session session);
    int getId();
}
