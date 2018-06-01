package model.dao.impl.hibernate.eav;

import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceCatalogObject;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service_attributes", schema = "public", catalog = "provider_eav")
public class ServiceAttributesEntity implements ProviderEntity<ServiceAttributeObject>{
    private int id;
    private String name;
    private boolean isListed;
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
    @SequenceGenerator(name="provider_sequence",sequenceName="provider_seq")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="provider_sequence")
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

    @Override
    public ServiceAttributeObject toObject(Session session) {
        ServiceAttributeObject attributeObject = new ServiceAttributeObject(this.id, this.name, this.isListed, this.attributeType.toObject(session));
        Hibernate.initialize(this.valueCatalog);
        if(attributeObject.isListed()) {
            if(this.valueCatalog == null) this.valueCatalog = new HashSet<>();
            for(ServiceValueCatalogEntity valueCatalogEntity:this.valueCatalog) {
                attributeObject.getCatalogValueMap().put(valueCatalogEntity.getId(), valueCatalogEntity.toObject(session));
            }
        }
        return attributeObject;
    }

    @Override
    public void fromObject(ServiceAttributeObject object, Session session) {
        this.attributeType = session.get(ServiceAttributeTypesEntity.class, object.getAttributeType().getId());
        Hibernate.initialize(this.attributeType.getAttributesEntitySet());
        this.attributeType.getAttributesEntitySet().add(this);
        this.name = object.getName();
        this.isListed = object.isListed();
        Hibernate.initialize(this.valueCatalog);
        if(this.isListed) {
            if(this.valueCatalog == null) this.valueCatalog = new HashSet<>();
            Collection<ServiceValueCatalogEntity> removedCatalogEntities = new HashSet<>(this.valueCatalog);
            if(object.getCatalogValueMap() != null && !object.getCatalogValueMap().isEmpty()) {
                for (ServiceCatalogObject catalogObject : object.getCatalogValueMap().values()) {
                    ServiceValueCatalogEntity catalogEntity;
                    if (catalogObject.getId() == null) {
                        catalogEntity = new ServiceValueCatalogEntity();
                        catalogEntity.setAttributeEntity(this);
                    } else {
                        catalogEntity = session.get(ServiceValueCatalogEntity.class, catalogObject.getId());
                        removedCatalogEntities.remove(catalogEntity);
                    }
                    catalogEntity.fromObject(catalogObject, session);
                    session.save(catalogEntity);
                }
            }
            for(ServiceValueCatalogEntity catalogEntity:removedCatalogEntities) {
                this.valueCatalog.remove(catalogEntity);
                session.remove(catalogEntity);
            }
        } else {
            if (this.valueCatalog != null) {
                for (ServiceValueCatalogEntity catalogEntity : this.valueCatalog) {
                    session.remove(catalogEntity);
                }
                this.valueCatalog.clear();
            }
        }
    }
}
