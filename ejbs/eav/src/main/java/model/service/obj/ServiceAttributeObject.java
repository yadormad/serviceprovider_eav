package model.service.obj;

import model.ProviderObject;

import java.util.*;

public class ServiceAttributeObject implements ProviderObject {
    private Integer id;
    private String name;
    private boolean isListed;
    private ServiceAttributeTypeObject attributeType;
    private Map<Integer, ServiceCatalogObject> catalogValueMap;

    public ServiceAttributeObject(String name, boolean isListed, ServiceAttributeTypeObject attributeType) {
        this.name = name;
        this.isListed = isListed;
        this.attributeType = attributeType;
        if(isListed) {
            catalogValueMap = new HashMap<>();
        }
    }

    public ServiceAttributeObject(Integer id, String name, boolean isListed, ServiceAttributeTypeObject attributeType) {
        this.id = id;
        this.name = name;
        this.isListed = isListed;
        this.attributeType = attributeType;
        if(isListed) {
            catalogValueMap = new HashMap<>();
        }
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isListed() {
        return isListed;
    }

    public void setListed(boolean listed) {
        isListed = listed;
    }

    public ServiceAttributeTypeObject getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(ServiceAttributeTypeObject attributeType) {
        this.attributeType = attributeType;
    }

    public Map<Integer, ServiceCatalogObject> getCatalogValueMap() {
        if(catalogValueMap == null) catalogValueMap = new HashMap<>();
        return catalogValueMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceAttributeObject that = (ServiceAttributeObject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
