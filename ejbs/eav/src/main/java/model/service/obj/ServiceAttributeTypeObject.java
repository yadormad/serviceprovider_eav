package model.service.obj;

import model.ProviderObject;

public class ServiceAttributeTypeObject implements ProviderObject {
    private Integer id;
    private String name;
    private String javaClassName;

    public ServiceAttributeTypeObject(Integer id, String name, String javaClassName) {
        this.id = id;
        this.name = name;
        this.javaClassName = javaClassName;
    }

    public ServiceAttributeTypeObject(String name, String javaClassName) {
        this.name = name;
        this.javaClassName = javaClassName;
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

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }
}
