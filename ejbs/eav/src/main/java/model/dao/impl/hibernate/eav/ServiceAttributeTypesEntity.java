package model.dao.impl.hibernate.eav;

import model.service.obj.ServiceAttributeTypeObject;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service_attribute_types", schema = "public", catalog = "provider_eav")
public class ServiceAttributeTypesEntity implements ProviderEntity<ServiceAttributeTypeObject> {
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

    @Override
    public ServiceAttributeTypeObject toObject(Session session) {
        return new ServiceAttributeTypeObject(this.id, this.name, this.javaClass);
    }

    @Override
    public void fromObject(ServiceAttributeTypeObject object, Session session) {
        this.name = object.getName();
        this.javaClass = object.getJavaClassName();
    }

}
