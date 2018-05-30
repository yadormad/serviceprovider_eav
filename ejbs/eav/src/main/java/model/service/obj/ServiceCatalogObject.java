package model.service.obj;

import model.ProviderObject;

import java.io.Serializable;

public class ServiceCatalogObject implements ProviderObject {
    private Integer id;
    private Serializable value;

    public ServiceCatalogObject(Serializable value) {
        this.value = value;
    }

    public ServiceCatalogObject(Integer id, Serializable value) {
        this.id = id;
        this.value = value;
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
}
