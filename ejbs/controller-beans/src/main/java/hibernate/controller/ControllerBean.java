package hibernate.controller;

import admin.block.PessimisticBlockEnum;
import hibernate.HibernateSessions;
import hibernate.entity.ClientsEntity;
import hibernate.entity.ServicesEntity;
import hibernate.model.service.types.ServiceType;
import model.client.ClientObject;
import model.dao.GenericDao;
import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceAttributeTypeObject;
import model.service.obj.ServiceTypeObject;

import javax.ejb.Stateless;
import java.util.*;

@Stateless(name = "ControllerEJB")
public class ControllerBean {
    public void removeBlocks(String login) {
        for (PessimisticBlockEnum blocksMap : PessimisticBlockEnum.values()) {
            blocksMap.removeUser(login);
        }
    }

    public List<ServiceTypeObject> getAllServiceTypes() {
        GenericDao<ServiceTypeObject> serviceTypeDao = DaoLoader.getDaoFactory().getServiceTypeDao();
        if(serviceTypeDao.getAll() == null) return new ArrayList<>();
        return Objects.requireNonNull(serviceTypeDao).getAll();
    }

    public List<ServiceAttributeObject> getAllAttributes() {
        GenericDao<ServiceAttributeObject> serviceAttributeDao = DaoLoader.getDaoFactory().getServiceAttributeDao();
        if(serviceAttributeDao.getAll() == null) return new ArrayList<>();
        return Objects.requireNonNull(serviceAttributeDao).getAll();
    }


    public List<ServiceAttributeTypeObject> getAllAttributeTypes() {
        GenericDao<ServiceAttributeTypeObject> serviceAttributeTypeDao = DaoLoader.getDaoFactory().getServiceAttributeTypeDao();
        if(serviceAttributeTypeDao.getAll() == null) return new ArrayList<>();
        return Objects.requireNonNull(serviceAttributeTypeDao).getAll();
    }

    public void removeAttribute(Integer attributeId) {
        GenericDao<ServiceAttributeObject> serviceAttributeDao = DaoLoader.getDaoFactory().getServiceAttributeDao();
        serviceAttributeDao.delete(serviceAttributeDao.getById(attributeId));
    }

    public void addAttribute(String name, Integer attributeTypeId, boolean isListed) throws IllegalAccessException, InstantiationException {
        GenericDao<ServiceAttributeObject> serviceAttributeDao = DaoLoader.getDaoFactory().getServiceAttributeDao();
        ServiceAttributeTypeObject serviceAttributeType = DaoLoader.getDaoFactory().getServiceAttributeTypeDao().getById(attributeTypeId);
        ServiceAttributeObject attributeObject = new ServiceAttributeObject(name, isListed, serviceAttributeType);
        serviceAttributeDao.persist(attributeObject);
    }

    public void addServiceType (String name, String description) throws IllegalAccessException, InstantiationException {
        ServiceTypeObject serviceType = new ServiceTypeObject(name, description);
        DaoLoader.getDaoFactory().getServiceTypeDao().persist(serviceType);
    }

    public void removeServiceType(Integer typeId) {
        DaoLoader.getDaoFactory().getServiceTypeDao().delete(DaoLoader.getDaoFactory().getServiceTypeDao().getById(typeId));
    }

    public List<ClientObject> getAllClients() {
        return DaoLoader.getDaoFactory().getClientDao().getAll();
    }

    public String addClient (String name, String info, String pass1, String pass2) throws IllegalAccessException, InstantiationException {
        if(!pass1.equals(pass2)) return "Passwords don't match";
        DaoLoader.getDaoFactory().getClientDao().persist(new ClientObject(name, info, pass1));
        return "Client " + name + " successfully added";
    }

    public void deleteClient (Integer clientId) {
        DaoLoader.getDaoFactory().getClientDao().delete(DaoLoader.getDaoFactory().getClientDao().getById(clientId));
    }

    public String addService(Integer clientId, String name, ServiceType type, Date startDate, Date endDate) {
        if(startDate.after(endDate)) return "ERROR: Provision date can't be after disabling date";
        HibernateSessions.addService(clientId, name, type.name(), new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
        return "Service " + name + " successfully added";
    }

    public String deleteService (Integer id) {
        return "Service " + HibernateSessions.removeService(id) + " successfully deleted";
    }

    public String updateService (Integer id, String name, Date startDate, Date endDate) {
        if(startDate.after(endDate)) return "ERROR: Provision date can't be after disabling date";
        return "Service " + HibernateSessions.updateService(id, name, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime())) + " successfully updated to " + name;
    }

    public ClientsEntity getClient(Integer id) {
        return HibernateSessions.getClient(id);
    }


    public ClientsEntity getServiceOwner(Integer serviceId) {
        return HibernateSessions.getServiceOwner(serviceId);
    }

    public ServicesEntity getService(Integer id) {
        return HibernateSessions.getService(id);
    }

    public Collection<ServicesEntity> getServicesByClientId(Integer clientId) {
        return HibernateSessions.getServicesOfClient(clientId);
    }

    public Collection<ServiceType> getUnusedServiceTypes(Integer clientId) {
        ArrayList<ServiceType> unusedTypes = new ArrayList<>(Arrays.asList(ServiceType.values()));
        for (ServicesEntity usedService:getServicesByClientId(clientId)) {
            unusedTypes.remove(ServiceType.valueOf(usedService.getType().toUpperCase()));
        }
        return unusedTypes;
    }

    public Map<ServicesEntity, ClientsEntity> getServiceClientMap(ServiceType type) {
        Collection<ServicesEntity> servicesByType = HibernateSessions.findByType(type.name());
        Map<ServicesEntity, ClientsEntity> serviceClientMap = new HashMap<>();
        for(ServicesEntity service:servicesByType) {
            ClientsEntity client = getServiceOwner(service.getId());
            serviceClientMap.put(service, client);
        }
        return serviceClientMap;
    }

    public UUID blockClient(Integer clientId, UUID blockId, String login) {
        return PessimisticBlockEnum.DELETE_CLIENT_BLOCK.checkPermission(clientId, login, blockId, 60000);
    }

    public void unblockClient(Integer clientId, UUID blockId, String login) {
        PessimisticBlockEnum.DELETE_CLIENT_BLOCK.removeBlock(login, clientId, blockId);
    }
}
