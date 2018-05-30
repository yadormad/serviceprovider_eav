package model.service.obj;

import model.ProviderObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceAttributeObject implements ProviderObject {
    private Integer id;
    private String name;
    private boolean isListed, isMultiple;
    private ServiceAttributeTypeObject attributeType;
    private List<ServiceCatalogObject> catalogValues;

    public ServiceAttributeObject(String name, boolean isListed, boolean isMultiple, ServiceAttributeTypeObject attributeType) {
        this.name = name;
        this.isListed = isListed;
        this.isMultiple = isMultiple;
        this.attributeType = attributeType;
        if(isListed) {
            catalogValues = new ArrayList<>();
        }
    }

    public ServiceAttributeObject(Integer id, String name, boolean isListed, boolean isMultiple, ServiceAttributeTypeObject attributeType) {
        this.id = id;
        this.name = name;
        this.isListed = isListed;
        this.isMultiple = isMultiple;
        this.attributeType = attributeType;
        if(isListed) {
            catalogValues = new ArrayList<>();
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

    public boolean isMultiple() {
        return isMultiple;
    }

    public void setMultiple(boolean multiple) {
        isMultiple = multiple;
    }

    public ServiceAttributeTypeObject getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(ServiceAttributeTypeObject attributeType) {
        this.attributeType = attributeType;
    }

    public List<ServiceCatalogObject> getCatalogValues() {
        return catalogValues;
    }

    public void setCatalogValues(List<ServiceCatalogObject> catalogValues) {
        this.catalogValues = catalogValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceAttributeObject that = (ServiceAttributeObject) o;
        return Objects.equals(id, that.id);
    }
}
