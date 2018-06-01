package model.service.obj;

import model.ProviderObject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ServiceTypeObject implements ProviderObject {
    private Integer id;
    private String name;
    private String description;
    private Set<ServiceAttributeObject> attributeSet;

    public ServiceTypeObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ServiceTypeObject(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Set<ServiceAttributeObject> getAttributeSet() {
        if(attributeSet == null) attributeSet = Collections.synchronizedSet(new HashSet<>());
        return attributeSet;
    }

    public void setAttributeSet(Set<ServiceAttributeObject> attributeSet) {
        this.attributeSet = attributeSet;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceTypeObject that = (ServiceTypeObject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
