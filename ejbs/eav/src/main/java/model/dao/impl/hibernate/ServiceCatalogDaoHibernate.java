package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceValueCatalogEntity;
import model.service.obj.ServiceCatalogObject;

import java.util.ArrayList;
import java.util.List;

public class ServiceCatalogDaoHibernate extends AbstractEntityManager<ServiceValueCatalogEntity, ServiceCatalogObject> implements GenericDao<ServiceCatalogObject> {
    @Override
    public ServiceCatalogObject persist(ServiceCatalogObject object) throws InstantiationException, IllegalAccessException {
        return persistObject(object, ServiceValueCatalogEntity.class);
    }

    @Override
    public ServiceCatalogObject getById(Integer id) {
        return getObjectById(id, ServiceValueCatalogEntity.class);
    }

    @Override
    public List<ServiceCatalogObject> getAll() {
        return getAllObjects(ServiceValueCatalogEntity.class);
    }

    @Override
    public void delete(ServiceCatalogObject object) {
        deleteObject(object, ServiceValueCatalogEntity.class);
    }
}
