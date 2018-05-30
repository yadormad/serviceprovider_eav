package model.service.obj;

import model.ProviderObject;

import java.io.Serializable;

public class ServiceValueObject implements ProviderObject {
    private Integer id;
    private Serializable value;
    private ServiceCatalogObject catalogValue;

    public ServiceValueObject(Integer id, Serializable value, ServiceCatalogObject catalogValue) {
        this.id = id;
        this.value = value;
        this.catalogValue = catalogValue;
    }

    public ServiceValueObject(Serializable value, ServiceCatalogObject catalogValue) {
        this.value = value;
        this.catalogValue = catalogValue;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Serializable getValue() {
        return value;
    }

    public void setValue(Serializable value) {
        this.value = value;
    }

    public ServiceCatalogObject getCatalogValue() {
        return catalogValue;
    }

    public void setCatalogValue(ServiceCatalogObject catalogValue) {
        this.catalogValue = catalogValue;
    }
}
