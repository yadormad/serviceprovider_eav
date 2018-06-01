package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceAttributesEntity;
import model.service.obj.ServiceAttributeObject;

import java.util.List;

public class ServiceAttributeDaoHibernate extends AbstractEntityManager<ServiceAttributesEntity, ServiceAttributeObject> implements GenericDao<ServiceAttributeObject> {

    @Override
    public ServiceAttributeObject persist(ServiceAttributeObject object) throws InstantiationException, IllegalAccessException {
        return persistObject(object, ServiceAttributesEntity.class);
    }

    @Override
    public ServiceAttributeObject getById(Integer id) {
        return getObjectById(id, ServiceAttributesEntity.class);
    }

    @Override
    public List<ServiceAttributeObject> getAll() {
        return getAllObjects(ServiceAttributesEntity.class);
    }

    @Override
    public void delete(ServiceAttributeObject object) {
        deleteObject(object, ServiceAttributesEntity.class);
    }
}
