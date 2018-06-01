package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceTypesEntity;
import model.service.obj.ServiceTypeObject;
import java.util.*;

public class ServiceTypeDaoHibernate extends AbstractEntityManager<ServiceTypesEntity, ServiceTypeObject> implements GenericDao<ServiceTypeObject> {

    @Override
    public ServiceTypeObject persist(ServiceTypeObject object) throws InstantiationException, IllegalAccessException {
        return persistObject(object, ServiceTypesEntity.class);
    }

    @Override
    public ServiceTypeObject getById(Integer id) {
        return getObjectById(id, ServiceTypesEntity.class);
    }

    @Override
    public List<ServiceTypeObject> getAll() {
        return getAllObjects(ServiceTypesEntity.class);
    }

    @Override
    public void delete(ServiceTypeObject object) {
        deleteObject(object, ServiceTypesEntity.class);
    }
}
