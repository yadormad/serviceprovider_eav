package model.service.obj;

import model.ProviderObject;
import model.client.ClientObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServiceObject implements ProviderObject {
    private Integer id;
    private ConcurrentMap<ServiceAttributeObject, ServiceValueObject> attributeValueMap;
    private ServiceTypeObject serviceType;

    public ServiceObject(Integer id, ServiceTypeObject serviceType) {
        this.id = id;
        this.serviceType = serviceType;
    }

    public ServiceObject(ServiceTypeObject serviceType) {
        this.serviceType = serviceType;
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

}

