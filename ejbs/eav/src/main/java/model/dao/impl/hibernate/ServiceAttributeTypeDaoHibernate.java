package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceAttributeTypesEntity;
import model.service.obj.ServiceAttributeTypeObject;

import java.util.ArrayList;
import java.util.List;

public class ServiceAttributeTypeDaoHibernate extends AbstractEntityManager<ServiceAttributeTypesEntity> implements GenericDao<ServiceAttributeTypeObject> {

    @Override
    public ServiceAttributeTypeObject persist(ServiceAttributeTypeObject object) {
        ServiceAttributeTypesEntity entity = persistObject(toEntity(object));
        object.setId(entity.getId());
        return object;
    }

    @Override
    public ServiceAttributeTypeObject getById(Integer id) {
        ServiceAttributeTypesEntity entity = getObjectById(id, ServiceAttributeTypesEntity.class);
        return toObject(entity);
    }

    @Override
    public List<ServiceAttributeTypeObject> getAll() {
        List<ServiceAttributeTypeObject> objectList = new ArrayList<>();
        for(ServiceAttributeTypesEntity entity: getAllObjects(ServiceAttributeTypesEntity.class)) {
            objectList.add(toObject(entity));
        }
        return objectList;
    }

    @Override
    public void delete(ServiceAttributeTypeObject object) {
        deleteObject(getObjectById(object.getId(), ServiceAttributeTypesEntity.class));
    }

    public static ServiceAttributeTypesEntity toEntity(ServiceAttributeTypeObject object) {
        ServiceAttributeTypesEntity entity = new ServiceAttributeTypesEntity();
        entity.setName(object.getName());
        entity.setJavaClass(object.getJavaClassName());
        return entity;
    }

    protected static ServiceAttributeTypeObject toObject(ServiceAttributeTypesEntity entity) {
        return new ServiceAttributeTypeObject(entity.getId(), entity.getName(), entity.getJavaClass());
    }
}
