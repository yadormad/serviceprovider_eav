package model.dao.impl.hibernate;

import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ServiceAttributesEntity;
import model.dao.impl.hibernate.eav.ServiceTypesEntity;
import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceTypeObject;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.*;

public class ServiceTypeDaoHibernate extends AbstractEntityManager<ServiceTypesEntity> implements GenericDao<ServiceTypeObject> {

    @Override
    public ServiceTypeObject persist(ServiceTypeObject object) {
        ServiceTypesEntity entity = persistObject(toEntity(object));
        object.setId(entity.getId());
        return object;
    }

    @Override
    public ServiceTypeObject getById(Integer id) {
        ServiceTypesEntity entity = getObjectById(id, ServiceTypesEntity.class);
        return toObject(entity);
    }

    @Override
    public List<ServiceTypeObject> getAll() {
        List<ServiceTypeObject> objectList = new ArrayList<>();
        for(ServiceTypesEntity entity: getAllObjects(ServiceTypesEntity.class)) {
            objectList.add(toObject(entity));
        }
        return objectList;
    }

    @Override
    public void delete(ServiceTypeObject object) {
        deleteObject(getObjectById(object.getId(), ServiceTypesEntity.class));
    }

    protected static ServiceTypesEntity toEntity(ServiceTypeObject object) {
        /*ServiceTypesEntity entity = new ServiceTypesEntity();
        if(object.getId() != null) {
            entity.setId(object.getId());
        }
        entity.setName(object.getName());
        entity.setDescription(object.getDescription());
        return entity;*/

        Session session = HibernateController.getInstance().getSession();
        ServiceTypesEntity entity;
        if(object.getId() != null) {
            entity = session.get(ServiceTypesEntity.class, object.getId());
            if (entity == null) {
                entity = new ServiceTypesEntity();
                object.setId(entity.getId());
            }
        } else {
            entity = new ServiceTypesEntity();
            object.setId(entity.getId());
        }

        entity.setName(object.getName());
        entity.setDescription(object.getDescription());

        Hibernate.initialize(entity.getMappedAttributesSet());

        Set<ServiceAttributeObject> objectAttributes = new HashSet<>(object.getAttributeSet());
        Set<ServiceAttributesEntity> removedAttributes = new HashSet<>();
        for(ServiceAttributesEntity attributeEntity : entity.getMappedAttributesSet()) {
            ServiceAttributeObject attributeObject = ServiceAttributeDaoHibernate.toObject(attributeEntity);
            if(objectAttributes.contains(attributeObject)) {
                objectAttributes.remove(attributeObject);
            } else {
                removedAttributes.add(attributeEntity);
            }
        }
        entity.getMappedAttributesSet().removeAll(removedAttributes);
        for (ServiceAttributeObject attributeObject:objectAttributes) {
            ServiceAttributesEntity attributesEntity = session.get(ServiceAttributesEntity.class, attributeObject.getId());
            entity.getMappedAttributesSet().add(attributesEntity);
        }
        session.close();
        return entity;
    }

    protected static ServiceTypeObject toObject(ServiceTypesEntity entity) {
        ServiceTypeObject serviceTypeObject =  new ServiceTypeObject(entity.getId(), entity.getName(), entity.getDescription());
        Hibernate.initialize(entity.getMappedAttributesSet());
        for(ServiceAttributesEntity attributeEntity : entity.getMappedAttributesSet()) {
            serviceTypeObject.getAttributeSet().add(ServiceAttributeDaoHibernate.toObject(attributeEntity));
        }
        return serviceTypeObject;

    }
}
