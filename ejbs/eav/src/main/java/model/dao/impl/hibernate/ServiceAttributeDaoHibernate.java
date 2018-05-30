package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceAttributesEntity;
import model.service.obj.ServiceAttributeObject;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ServiceAttributeDaoHibernate extends AbstractEntityManager<ServiceAttributesEntity> implements GenericDao<ServiceAttributeObject> {

    @Override
    public ServiceAttributeObject persist(ServiceAttributeObject object) {
        ServiceAttributesEntity entity = persistObject(toEntity(object));
        object.setId(entity.getId());
        return object;
    }

    @Override
    public ServiceAttributeObject getById(Integer id) {
        ServiceAttributesEntity entity = getObjectById(id, ServiceAttributesEntity.class);
        return toObject(entity);
    }

    @Override
    public List<ServiceAttributeObject> getAll() {
        List<ServiceAttributeObject> objectList = new ArrayList<>();
        for (ServiceAttributesEntity entity : getAllObjects(ServiceAttributesEntity.class)) {
            objectList.add(toObject(entity));
        }
        return objectList;
    }

    @Override
    public void delete(ServiceAttributeObject object) {
        deleteObject(getObjectById(object.getId(), ServiceAttributesEntity.class));
    }

    protected static ServiceAttributesEntity toEntity(ServiceAttributeObject object) {
        Session session = HibernateController.getInstance().getSession();
        ServiceAttributesEntity entity;
        if(object.getId() != null) {
            entity = session.get(ServiceAttributesEntity.class, object.getId());
            if (entity == null) {
                entity = new ServiceAttributesEntity();
                object.setId(entity.getId());
            }
        } else {
            entity = new ServiceAttributesEntity();
            object.setId(entity.getId());
        }
        entity.setName(object.getName());
        entity.setListed(object.isListed());
        entity.setMultiple(object.isMultiple());
        entity.setAttributeType(ServiceAttributeTypeDaoHibernate.toEntity(object.getAttributeType()));
        session.close();
        return entity;
    }

    protected static ServiceAttributeObject toObject(ServiceAttributesEntity entity) {
        ServiceAttributeObject attributeObject = new ServiceAttributeObject(entity.getId(), entity.getName(), entity.isListed(), entity.isMultiple(), ServiceAttributeTypeDaoHibernate.toObject(entity.getAttributeType()));
        if(attributeObject.isListed()) {
            //TODO: парсинг каталогизированных значений
        }
        return attributeObject;
    }
}
