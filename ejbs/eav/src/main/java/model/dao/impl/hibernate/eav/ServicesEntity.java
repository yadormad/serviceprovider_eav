package model.dao.impl.hibernate.eav;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "services", schema = "public", catalog = "provider_eav")
public class ServicesEntity {
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
