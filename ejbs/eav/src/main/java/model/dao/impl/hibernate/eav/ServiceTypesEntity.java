package model.dao.impl.hibernate.eav;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service_types", schema = "public", catalog = "provider_eav")
public class ServiceTypesEntity {
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
}
