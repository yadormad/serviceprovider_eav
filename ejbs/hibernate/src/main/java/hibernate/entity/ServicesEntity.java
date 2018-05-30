package hibernate.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "services", schema = "public", catalog = "provider_db")
public class ServicesEntity {
    private int id;
    private String name;
    private String type;
    private Date startDate;
    private Date endDate;
    private ClientsEntity client;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    public ClientsEntity getClient() {
        return client;
    }

    public void setClient(ClientsEntity client) {
        this.client = client;
    }

    @Id
    @SequenceGenerator(name="service_sequence",sequenceName="service_id_sequence")
    @GeneratedValue(strategy= GenerationType.AUTO,generator="service_sequence")
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "startdate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startdate) {
        this.startDate = startdate;
    }

    @Basic
    @Column(name = "enddate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date enddate) {
        this.endDate = enddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicesEntity that = (ServicesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, startDate, endDate);
    }
}
