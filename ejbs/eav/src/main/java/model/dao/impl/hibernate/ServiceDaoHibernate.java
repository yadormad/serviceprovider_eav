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

public class ServiceDaoHibernate extends AbstractEntityManager<ServicesEntity> implements GenericDao<ServiceObject> {

    @Override
    public ServiceObject persist(ServiceObject object) {
        ServicesEntity entity = persistObject(toEntity(object));
        object.setId(entity.getId());
        return object;
    }

    @Override
    public ServiceObject getById(Integer id) {
        ServicesEntity entity = getObjectById(id, ServicesEntity.class);
        return toObject(entity);
    }

    @Override
    public List<ServiceObject> getAll() {
        List<ServiceObject> objectList = new ArrayList<>();
        for(ServicesEntity entity: getAllObjects(ServicesEntity.class)) {
            objectList.add(toObject(entity));
        }
        return objectList;
    }

    @Override
    public void delete(ServiceObject object) {
        deleteObject(getObjectById(object.getId(), ServicesEntity.class));
    }

    private ServicesEntity toEntity(ServiceObject object) {
        Session session = HibernateController.getInstance().getSession();
        session.getTransaction().begin();
        ServicesEntity entity;
        if(object.getId() != null) {
            entity = session.get(ServicesEntity.class, object.getId());
            if (entity == null) {
                entity = new ServicesEntity();
                object.setId(entity.getId());
            }
        } else {
            entity = new ServicesEntity();
            object.setId(entity.getId());
        }

        entity.setOwner(ClientDaoHibernate.toEntity(object.getOwner()));
        entity.setType(ServiceTypeDaoHibernate.toEntity(object.getServiceType()));

        Hibernate.initialize(entity.getValuesEntitySet());

        for(ServiceAttributeObject attributeObject:object.getAttributeValueMap().keySet()) {
            ServiceValueObject valueObject = object.getAttributeValueMap().get(attributeObject);
            ServiceValuesEntity valueEntity;
            if(valueObject.getId() == null) {
                valueEntity = new ServiceValuesEntity();
                valueEntity.setAttributeEntity(ServiceAttributeDaoHibernate.toEntity(attributeObject));
            } else {
                valueEntity = session.get(ServiceValuesEntity.class, object.getId());
                if(valueEntity == null || !ServiceAttributeDaoHibernate.toObject(valueEntity.getAttributeEntity()).equals(attributeObject)) {
                    valueEntity = new ServiceValuesEntity();
                    valueEntity.setAttributeEntity(ServiceAttributeDaoHibernate.toEntity(attributeObject));
                }
            }
            if(attributeObject.isListed()) {
                valueEntity.setCatalogValue(ServiceCatalogDaoHibernate.toEntity(valueObject.getCatalogValue()));
            } else {
                valueEntity.setValue(valueObject.getValue());
            }
            session.save(valueEntity);
            entity.getValuesEntitySet().add(valueEntity);
        }
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    private ServiceObject toObject(ServicesEntity entity) {
        ServiceObject serviceObject = new ServiceObject(entity.getId(), ServiceTypeDaoHibernate.toObject(entity.getType()), ClientDaoHibernate.toObject(entity.getOwner()));
        for (ServiceValuesEntity valuesEntity:entity.getValuesEntitySet()) {
            serviceObject.getAttributeValueMap().put(ServiceAttributeDaoHibernate.toObject(valuesEntity.getAttributeEntity()), new ServiceValueObject(valuesEntity.getId(), valuesEntity.getValue(), ServiceCatalogDaoHibernate.toObject(valuesEntity.getCatalogValue())));
        }
        return serviceObject;
    }
}
