package model.dao.impl.hibernate;

import model.ProviderObject;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractEntityManager<E extends ProviderEntity<O>, O extends ProviderObject> {

    void deleteObject(O object, Class<E> eClass) {
        Session session = HibernateController.getInstance().getSession();
        session.getTransaction().begin();
        session.remove(session.get(eClass, object.getId()));
        session.getTransaction().commit();
        session.close();
    }

    O persistObject(O object, Class<E> eClass) throws IllegalAccessException, InstantiationException {
        Session session = HibernateController.getInstance().getSession();
        session.getTransaction().begin();
        E entity = null;
        if(object.getId() != null) {
            entity = session.get(eClass, object.getId());
        }
        if(entity == null) {
            entity = eClass.newInstance();
        }
        entity.fromObject(object, session);
        session.save(entity);
        object.setId(entity.getId());
        session.getTransaction().commit();
        session.close();
        return object;
    }

    O getObjectById(Integer id, Class<E> T) {
        Session session = HibernateController.getInstance().getSession();
        session.getTransaction().begin();
        E entity = session.get(T, id);
        O object = entity.toObject(session);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    List<O> getAllObjects(Class<E> T) {
        Session session = HibernateController.getInstance().getSession();
        TypedQuery<E> allEntitiesQuery = session.createQuery("from " + T.getName(), T);
        List<E> allEntities = allEntitiesQuery.getResultList();
        List<O> allObjects = new ArrayList<>();
        for(E entity : allEntities) {
            allObjects.add(entity.toObject(session));
        }
        session.close();
        return allObjects;
    }
}
