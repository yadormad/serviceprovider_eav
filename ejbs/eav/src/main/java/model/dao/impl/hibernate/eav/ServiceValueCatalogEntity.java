package model.dao.impl.hibernate.eav;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service_value_catalog", schema = "public", catalog = "provider_eav")
public class ServiceValueCatalogEntity{
    private int id;
    private Serializable value;
    private ServiceAttributesEntity attributeEntity;
    private Set<ServiceValuesEntity> valuesEntitySet;

    @OneToMany(mappedBy = "catalogValue")
    public Set<ServiceValuesEntity> getValuesEntitySet() {
        return valuesEntitySet;
    }

    public void setValuesEntitySet(Set<ServiceValuesEntity> valuesEntitySet) {
        this.valuesEntitySet = valuesEntitySet;
    }

    @ManyToOne
    @JoinColumn(name = "service_attribute_id", nullable = false)
    public ServiceAttributesEntity getAttributeEntity() {
        return attributeEntity;
    }

    public void setAttributeEntity(ServiceAttributesEntity attributeEntity) {
        this.attributeEntity = attributeEntity;
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
    @Column(name = "value")
    public Serializable getValue() {
        return value;
    }

    public void setValue(Serializable value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceValueCatalogEntity that = (ServiceValueCatalogEntity) o;
        return id == that.id &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, value);
    }
}
