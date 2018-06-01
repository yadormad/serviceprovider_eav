package model.service.obj;

import model.ProviderObject;

import java.io.Serializable;

public class ServiceValueObject implements ProviderObject {
    private Integer id;
    private String value;
    private ServiceCatalogObject catalogValue;

    public ServiceValueObject(Integer id, String value, ServiceCatalogObject catalogValue) {
        this.id = id;
        this.value = value;
        this.catalogValue = catalogValue;
    }

    public ServiceValueObject(String value, ServiceCatalogObject catalogValue) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ServiceCatalogObject getCatalogValue() {
        return catalogValue;
    }

    public void setCatalogValue(ServiceCatalogObject catalogValue) {
        this.catalogValue = catalogValue;
    }
}
