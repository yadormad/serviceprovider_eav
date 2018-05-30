package model.dao.impl.hibernate;

import model.client.ClientObject;
import model.dao.*;
import model.service.obj.*;

public class HibernateDaoFactory implements DaoFactory{

    private final GenericDao<ClientObject> clientDao;
    private final GenericDao<ServiceObject> serviceDao;
    private final GenericDao<ServiceAttributeObject> serviceAttributeDao;
    private final GenericDao<ServiceAttributeTypeObject> serviceAttributeTypeDao;
    private final GenericDao<ServiceTypeObject> serviceTypeDao;
    private final GenericDao<ServiceCatalogObject> serviceCatalogDao;

    public HibernateDaoFactory() {
        serviceCatalogDao = new ServiceCatalogDaoHibernate();
        serviceTypeDao = new ServiceTypeDaoHibernate();
        serviceAttributeTypeDao = new ServiceAttributeTypeDaoHibernate();
        serviceAttributeDao = new ServiceAttributeDaoHibernate();
        serviceDao = new ServiceDaoHibernate();
        clientDao = new ClientDaoHibernate();
    }

    @Override
    public GenericDao<ClientObject> getClientDao() {
        return clientDao;
    }

    @Override
    public GenericDao<ServiceObject> getServiceDao() {
        return serviceDao;
    }

    @Override
    public GenericDao<ServiceAttributeObject> getServiceAttributeDao() {
        return serviceAttributeDao;
    }

    @Override
    public GenericDao<ServiceAttributeTypeObject> getServiceAttributeTypeDao() {
        return serviceAttributeTypeDao;
    }

    @Override
    public GenericDao<ServiceTypeObject> getServiceTypeDao() {
        return serviceTypeDao;
    }

    @Override
    public GenericDao<ServiceCatalogObject> getServiceCatalogDao() {
        return serviceCatalogDao;
    }
}
