package model.service.obj;

import model.ProviderObject;

import java.io.Serializable;

public class ServiceCatalogObject implements ProviderObject {
    private Integer id;
    private String value;

    public ServiceCatalogObject(String value) {
        this.value = value;
    }

    public ServiceCatalogObject(Integer id, String value) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
