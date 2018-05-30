package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceValueCatalogEntity;
import model.service.obj.ServiceCatalogObject;

import java.util.ArrayList;
import java.util.List;

public class ServiceCatalogDaoHibernate extends AbstractEntityManager<ServiceValueCatalogEntity> implements GenericDao<ServiceCatalogObject> {
    @Override
    public ServiceCatalogObject persist(ServiceCatalogObject object) {
        ServiceValueCatalogEntity entity = persistObject(toEntity(object));
        object.setId(entity.getId());
        return object;
    }

    @Override
    public ServiceCatalogObject getById(Integer id) {
        ServiceValueCatalogEntity entity = getObjectById(id, ServiceValueCatalogEntity.class);
        return toObject(entity);
    }

    @Override
    public List<ServiceCatalogObject> getAll() {
        List<ServiceCatalogObject> objectList = new ArrayList<>();
        for(ServiceValueCatalogEntity entity: getAllObjects(ServiceValueCatalogEntity.class)) {
            objectList.add(toObject(entity));
        }
        return objectList;
    }

    @Override
    public void delete(ServiceCatalogObject object) {
        deleteObject(getObjectById(object.getId(), ServiceValueCatalogEntity.class));
    }

    protected static ServiceValueCatalogEntity toEntity(ServiceCatalogObject object) {
        if (object == null) return null;
        ServiceValueCatalogEntity entity = new ServiceValueCatalogEntity();
        if(object.getId() != null) {
            entity.setId(object.getId());
        }
        entity.setValue(object.getValue());
        return entity;
    }

    protected static ServiceCatalogObject toObject(ServiceValueCatalogEntity entity) {
        if (entity == null) return null;
        return new ServiceCatalogObject(entity.getId(), entity.getValue());
    }
}
