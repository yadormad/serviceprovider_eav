package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceValuesEntity;
import model.dao.impl.hibernate.eav.ServicesEntity;
import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceObject;
import model.service.obj.ServiceValueObject;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ServiceDaoHibernate extends AbstractEntityManager<ServicesEntity, ServiceObject> implements GenericDao<ServiceObject> {

    @Override
    public ServiceObject persist(ServiceObject object) throws InstantiationException, IllegalAccessException {
        return persistObject(object, ServicesEntity.class);
    }

    @Override
    public ServiceObject getById(Integer id) {
        return getObjectById(id, ServicesEntity.class);
    }

    @Override
    public List<ServiceObject> getAll() {
        return getAllObjects(ServicesEntity.class);
    }

    @Override
    public void delete(ServiceObject object) {
        deleteObject(object, ServicesEntity.class);
    }
}
