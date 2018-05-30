package hibernate.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clients", schema = "public", catalog = "provider_db")
public class ClientsEntity {
    private int id;
    private String name;
    private String info;
    private Set<ServicesEntity> servicesEntitySet;

    @OneToMany(mappedBy = "client")
    public Set<ServicesEntity> getServicesEntitySet() {
        return servicesEntitySet;
    }

    public void setServicesEntitySet(Set<ServicesEntity> servicesEntitySet) {
        this.servicesEntitySet = servicesEntitySet;
    }

    @Id
    @SequenceGenerator(name="client_sequence", sequenceName="client_id_sequence")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="client_sequence")
    @Column(name = "id")
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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsEntity that = (ClientsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, info);
    }
}
