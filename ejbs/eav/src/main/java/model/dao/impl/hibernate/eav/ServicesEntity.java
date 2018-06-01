package model.dao.impl.hibernate.eav;

import model.client.ClientObject;
import model.dao.impl.hibernate.*;
import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceObject;
import model.service.obj.ServiceTypeObject;
import model.service.obj.ServiceValueObject;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "services", schema = "public", catalog = "provider_eav")
public class ServicesEntity implements ProviderEntity<ServiceObject>{
    private int id;
    private ServiceTypesEntity type;
    private ClientsEntity owner;
    private Set<ServiceValuesEntity> valuesEntitySet;

    @OneToMany(mappedBy = "servicesEntity")
    public Set<ServiceValuesEntity> getValuesEntitySet() {
        return valuesEntitySet;
    }

    public void setValuesEntitySet(Set<ServiceValuesEntity> valuesEntities) {
        this.valuesEntitySet = valuesEntities;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    public ClientsEntity getOwner() {
        return owner;
    }

    public void setOwner(ClientsEntity owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    public ServiceTypesEntity getType() {
        return type;
    }

    public void setType(ServiceTypesEntity type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicesEntity that = (ServicesEntity) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public ServiceObject toObject(Session session) {
        ServiceObject serviceObject = new ServiceObject(this.id, this.type.toObject(session));
        Hibernate.initialize(this.valuesEntitySet);
        if(this.valuesEntitySet == null) this.valuesEntitySet = new HashSet<>();
        for (ServiceValuesEntity valuesEntity:this.valuesEntitySet) {
            serviceObject.getAttributeValueMap().put(valuesEntity.getAttributeEntity().toObject(session), valuesEntity.toObject(session));
        }
        return serviceObject;
    }

    @Override
    public void fromObject(ServiceObject object, Session session) {
        ServiceTypesEntity serviceTypeEntity = session.get(ServiceTypesEntity.class, object.getServiceType().getId());
        this.type = serviceTypeEntity;

        Hibernate.initialize(this.valuesEntitySet);
        if(this.valuesEntitySet == null) this.valuesEntitySet = new HashSet<>();
        Set<ServiceValuesEntity> removedValuesEntitySet = new HashSet<>(this.valuesEntitySet);
        for(ServiceAttributeObject attributeObject:object.getAttributeValueMap().keySet()) {
            ServiceAttributesEntity attributesEntity = session.get(ServiceAttributesEntity.class, attributeObject.getId());
            ServiceValueObject valueObject = object.getAttributeValueMap().get(attributeObject);
            ServiceValuesEntity valueEntity = session.get(ServiceValuesEntity.class, valueObject.getId());
            if (valueEntity != null) {
                if (this.valuesEntitySet.contains(valueEntity)) {
                    removedValuesEntitySet.remove(valueEntity);
                } else {
                    this.valuesEntitySet.add(valueEntity);
                    valueEntity.setServicesEntity(this);
                }
                valueEntity.fromObject(valueObject, session);
            } else {
                valueEntity = new ServiceValuesEntity();
                valueEntity.fromObject(valueObject, session);
                valueEntity.setServicesEntity(this);
                this.valuesEntitySet.add(valueEntity);
                valueEntity.setAttributeEntity(attributesEntity);
            }
            session.save(valueEntity);
            session.save(attributesEntity);
        }
        for(ServiceValuesEntity removedValue : removedValuesEntitySet) {
            this.valuesEntitySet.remove(removedValue);
            Hibernate.initialize(removedValue.getAttributeEntity().getValuesEntitySet());
            removedValue.getAttributeEntity().getValuesEntitySet().remove(removedValue);
            session.remove(removedValue);
        }
    }
}
