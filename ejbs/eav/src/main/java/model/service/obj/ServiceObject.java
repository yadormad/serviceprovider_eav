package model.service.obj;

import model.ProviderObject;
import model.client.ClientObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServiceObject implements ProviderObject {
    private Integer id;
    private ConcurrentMap<ServiceAttributeObject, ServiceValueObject> attributeValueMap;
    private ServiceTypeObject serviceType;
    private ClientObject owner;

    public ServiceObject(Integer id, ServiceTypeObject serviceType, ClientObject owner) {
        this.id = id;
        this.serviceType = serviceType;
        this.owner = owner;
    }

    public ServiceObject(ServiceTypeObject serviceType, ClientObject owner) {
        this.serviceType = serviceType;
        this.owner = owner;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public ConcurrentMap<ServiceAttributeObject, ServiceValueObject> getAttributeValueMap() {
        if (attributeValueMap == null) attributeValueMap = new ConcurrentHashMap<>();
        return attributeValueMap;
    }

    public void setAttributeValueMap(ConcurrentMap<ServiceAttributeObject, ServiceValueObject> attributeValueMap) {
        this.attributeValueMap = attributeValueMap;
    }

    public ServiceTypeObject getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypeObject serviceType) {
        this.serviceType = serviceType;
    }

    public ClientObject getOwner() {
        return owner;
    }

    public void setOwner(ClientObject owner) {
        this.owner = owner;
    }
}

