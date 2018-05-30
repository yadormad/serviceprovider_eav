package model.dao.impl.hibernate.eav;

import model.client.ClientObject;
import model.dao.impl.hibernate.ProviderEntity;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clients", schema = "public", catalog = "provider_eav")
public class ClientsEntity implements ProviderEntity<ClientObject> {
    private int id;
    private String login;
    private String info;
    private String pass;
    private Set<ServicesEntity> servicesEntitySet;

    @OneToMany(mappedBy = "owner")
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
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsEntity that = (ClientsEntity) o;
        return id == that.id &&
                Objects.equals(login, that.login) &&
                Objects.equals(info, that.info) &&
                Objects.equals(pass, that.pass);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, info, pass);
    }

    @Override
    public ClientObject toObject(Session session) {
        return new ClientObject(this.id, this.login, this.info, this.pass);
    }

    @Override
    public void fromObject(ClientObject object, Session session) {
        this.login = object.getLogin();
        this.pass = object.getPass();
        this.info = object.getInfo();
    }
}
