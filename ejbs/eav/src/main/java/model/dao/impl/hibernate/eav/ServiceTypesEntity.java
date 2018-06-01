package model.dao.impl.hibernate.eav;

import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceTypeObject;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service_types", schema = "public", catalog = "provider_eav")
public class ServiceTypesEntity implements ProviderEntity<ServiceTypeObject>{
    private int id;
    private String name;
    private String description;
    private Set<ServicesEntity> servicesEntitySet;
    private Set<ServiceAttributesEntity> mappedAttributesSet;

    @ManyToMany(mappedBy = "mappedTypesSet")
    public Set<ServiceAttributesEntity> getMappedAttributesSet() {
        return mappedAttributesSet;
    }

    public void setMappedAttributesSet(Set<ServiceAttributesEntity> mappedAttributesSet) {
        this.mappedAttributesSet = mappedAttributesSet;
    }

    @OneToMany(mappedBy = "type")
    public Set<ServicesEntity> getServicesEntitySet() {
        return servicesEntitySet;
    }

    public void setServicesEntitySet(Set<ServicesEntity> servicesEntitySet) {
        this.servicesEntitySet = servicesEntitySet;
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
    @Column(name = "description")
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
        ServiceTypesEntity that = (ServiceTypesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public ServiceTypeObject toObject(Session session) {
        ServiceTypeObject serviceTypeObject = new ServiceTypeObject(this.id, this.name, this.description);
        Hibernate.initialize(this.mappedAttributesSet);
        for(ServiceAttributesEntity attributesEntity:this.mappedAttributesSet) {
            serviceTypeObject.getAttributeSet().add(attributesEntity.toObject(session));
        }
        return serviceTypeObject;
    }

    @Override
    public void fromObject(ServiceTypeObject object, Session session) {
        this.name = object.getName();
        this.description = object.getDescription();
        Hibernate.initialize(this.mappedAttributesSet);
        if(this.mappedAttributesSet == null) this.mappedAttributesSet = new HashSet<>();
        Set<ServiceAttributeObject> objectAttributes = new HashSet<>(object.getAttributeSet());
        Set<ServiceAttributesEntity> removedAttributes = new HashSet<>(this.mappedAttributesSet);

        for(ServiceAttributeObject attributeObject:objectAttributes) {
            ServiceAttributesEntity attributesEntity = session.get(ServiceAttributesEntity.class, attributeObject.getId());
            if(this.mappedAttributesSet.contains(attributesEntity)) {
                removedAttributes.remove(attributesEntity);
            } else {
                this.mappedAttributesSet.add(attributesEntity);
                Hibernate.initialize(attributesEntity.getMappedTypesSet());
                attributesEntity.getMappedTypesSet().add(this);
                session.save(attributesEntity);
            }
        }
        for(ServiceAttributesEntity attributesEntity:removedAttributes) {
            this.mappedAttributesSet.remove(attributesEntity);
            Hibernate.initialize(attributesEntity.getMappedTypesSet());
            attributesEntity.getMappedTypesSet().remove(this);
            session.save(attributesEntity);
        }
    }
}
