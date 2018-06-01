package model.dao.impl.hibernate.eav;

import org.hibernate.Session;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface ProviderEntity<O> {
    O toObject(Session session);
    void fromObject(O object, Session session);
    int getId();
}
