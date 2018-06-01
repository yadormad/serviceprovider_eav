package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceAttributeTypesEntity;
import model.service.obj.ServiceAttributeTypeObject;

import java.util.List;

public class ServiceAttributeTypeDaoHibernate extends AbstractEntityManager<ServiceAttributeTypesEntity, ServiceAttributeTypeObject> implements GenericDao<ServiceAttributeTypeObject> {

    @Override
    public ServiceAttributeTypeObject persist(ServiceAttributeTypeObject object) throws InstantiationException, IllegalAccessException {
        return persistObject(object, ServiceAttributeTypesEntity.class);
    }

    @Override
    public ServiceAttributeTypeObject getById(Integer id) {
        return getObjectById(id, ServiceAttributeTypesEntity.class);
    }

    @Override
    public List<ServiceAttributeTypeObject> getAll() {
        return getAllObjects(ServiceAttributeTypesEntity.class);
    }

    @Override
    public void delete(ServiceAttributeTypeObject object) {
        deleteObject(object, ServiceAttributeTypesEntity.class);
    }
}
