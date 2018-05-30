package model.dao.impl.hibernate.eav;

import model.dao.impl.hibernate.ServiceAttributeTypeDaoHibernate;
import model.dao.impl.hibernate.ServiceTypeDaoHibernate;
import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceAttributeTypeObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service_attributes", schema = "public", catalog = "provider_eav")
public class ServiceAttributesEntity {
    private int id;
    private String name;
    private boolean isListed, isMultiple;
    private ServiceAttributeTypesEntity attributeType;
    private Set<ServiceValueCatalogEntity> valueCatalog;
    private Set<ServiceValuesEntity> valuesEntitySet;
    private Set<ServiceTypesEntity> mappedTypesSet;

    @ManyToMany
    @JoinTable(
            name = "service_attribute_type_map",
            joinColumns = { @JoinColumn(name = "attribute_id") },
            inverseJoinColumns = { @JoinColumn(name = "type_id") }
    )
    public Set<ServiceTypesEntity> getMappedTypesSet() {
        return mappedTypesSet;
    }

    public void setMappedTypesSet(Set<ServiceTypesEntity> mappedTypes) {
        this.mappedTypesSet = mappedTypes;
    }

    @OneToMany(mappedBy = "attributeEntity")
    public Set<ServiceValueCatalogEntity> getValueCatalog() {
        return valueCatalog;
    }

    public void setValueCatalog(Set<ServiceValueCatalogEntity> valueCatalog) {
        this.valueCatalog = valueCatalog;
    }

    @OneToMany(mappedBy = "attributeEntity")
    public Set<ServiceValuesEntity> getValuesEntitySet() {
        return valuesEntitySet;
    }

    public void setValuesEntitySet(Set<ServiceValuesEntity> valuesEntities) {
        this.valuesEntitySet = valuesEntities;
    }

    @ManyToOne
    @JoinColumn(name = "service_attribute_type_id", nullable = false)
    public ServiceAttributeTypesEntity getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(ServiceAttributeTypesEntity attributeType) {
        this.attributeType = attributeType;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "is_listed")
    public boolean isListed() {
        return isListed;
    }

    public void setListed(boolean listed) {
        isListed = listed;
    }

    @Basic
    @Column(name = "is_multiple")
    public boolean isMultiple() {
        return isMultiple;
    }

    public void setMultiple(boolean multiple) {
        isMultiple = multiple;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceAttributesEntity that = (ServiceAttributesEntity) o;
        return id == that.id &&
                isListed == that.isListed &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, isListed);
    }

    public void fromObject(ServiceAttributeObject object) {
        this.attributeType = ServiceAttributeTypeDaoHibernate.toEntity(object.getAttributeType());
        this.name = object.getName();
        this.isListed = object.isListed();
        this.isMultiple = object.isMultiple();
    }
}
