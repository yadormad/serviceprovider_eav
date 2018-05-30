package model.dao;

import model.client.ClientObject;
import model.service.obj.*;

public interface DaoFactory {
    GenericDao<ClientObject> getClientDao();
    GenericDao<ServiceAttributeObject> getServiceAttributeDao();
    GenericDao<ServiceAttributeTypeObject> getServiceAttributeTypeDao();
    GenericDao<ServiceObject> getServiceDao();
    GenericDao<ServiceTypeObject> getServiceTypeDao();
    GenericDao<ServiceCatalogObject> getServiceCatalogDao();
}
