package model.dao.impl.hibernate.eav;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "service_values", schema = "public", catalog = "provider_eav")
public class ServiceValuesEntity {
    private int id;
    private Serializable value;
    private ServiceAttributesEntity attributeEntity;
    private ServicesEntity servicesEntity;
    private ServiceValueCatalogEntity catalogValue;

    @ManyToOne
    @JoinColumn(name = "service_attribute_id", nullable = false)
    public ServiceAttributesEntity getAttributeEntity() {
        return attributeEntity;
    }

    public void setAttributeEntity(ServiceAttributesEntity attributeEntity) {
        this.attributeEntity = attributeEntity;
    }

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    public ServicesEntity getServicesEntity() {
        return servicesEntity;
    }

    public void setServicesEntity(ServicesEntity servicesEntity) {
        this.servicesEntity = servicesEntity;
    }

    @ManyToOne
    @JoinColumn(name = "value_from_catalog_id", nullable = false)
    public ServiceValueCatalogEntity getCatalogValue() {
        return catalogValue;
    }

    public void setCatalogValue(ServiceValueCatalogEntity catalogValue) {
        this.catalogValue = catalogValue;
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
        ServiceValuesEntity that = (ServiceValuesEntity) o;
        return id == that.id &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, value);
    }
}
