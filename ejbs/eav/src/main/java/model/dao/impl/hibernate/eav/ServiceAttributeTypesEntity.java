package model.dao.impl.hibernate.eav;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service_attribute_types", schema = "public", catalog = "provider_eav")
public class ServiceAttributeTypesEntity {
    private int id;
    private String name;
    private String javaClass;
    private Set<ServiceAttributesEntity> attributesEntitySet;

    @OneToMany(mappedBy = "attributeType")
    public Set<ServiceAttributesEntity> getAttributesEntitySet() {
        return attributesEntitySet;
    }

    public void setAttributesEntitySet(Set<ServiceAttributesEntity> attributesEntitySet) {
        this.attributesEntitySet = attributesEntitySet;
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
    @Column(name = "java_class")
    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceAttributeTypesEntity that = (ServiceAttributeTypesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
