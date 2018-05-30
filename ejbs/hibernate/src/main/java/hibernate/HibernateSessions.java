package hibernate;

import hibernate.entity.ClientsEntity;
import hibernate.entity.ServicesEntity;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class HibernateSessions {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static List<ClientsEntity> getAllClients() {
        Session session = getSession();
        TypedQuery<ClientsEntity> allClientsQuery = session.createQuery("from hibernate.entity.ClientsEntity", ClientsEntity.class);
        List<ClientsEntity> clientsEntities = allClientsQuery.getResultList();
        session.close();
        return clientsEntities;
    }

    public static void addClient(String name, String info) {
        Session session = HibernateSessions.getSession();
        session.getTransaction().begin();
        ClientsEntity client = new ClientsEntity();
        client.setName(name);
        client.setInfo(info);
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    public static String removeClient(Integer id) {
        Session session = HibernateSessions.getSession();
        session.getTransaction().begin();
        ClientsEntity client = session.get(ClientsEntity.class, id);
        session.remove(client);
        session.getTransaction().commit();
        session.close();
        return client.getName();
    }

    public static void addService(Integer clientId, String name, String type, Date startDate, Date endDate) {
        Session session = HibernateSessions.getSession();
        session.getTransaction().begin();
        ServicesEntity service = new ServicesEntity();
        service.setName(name);
        service.setType(type);
        service.setStartDate(startDate);
        service.setEndDate(endDate);
        ClientsEntity client = session.get(ClientsEntity.class, clientId);
        client.getServicesEntitySet().add(service);
        service.setClient(client);
        session.save(service);
        session.getTransaction().commit();
        session.close();
    }

    public static String removeService(Integer id) {
        Session session = HibernateSessions.getSession();
        session.getTransaction().begin();
        ServicesEntity service = session.get(ServicesEntity.class, id);
        session.remove(service);
        session.getTransaction().commit();
        session.close();
        return service.getName();
    }

    public static String updateService(Integer id, String name, Date startDate, Date endDate) {
        Session session = HibernateSessions.getSession();
        session.getTransaction().begin();
        ServicesEntity service = session.get(ServicesEntity.class, id);
        String previousName = service.getName();
        service.setName(name);
        service.setStartDate(startDate);
        service.setEndDate(endDate);
        session.update(service);
        session.getTransaction().commit();
        session.close();
        return previousName;
    }

    public static ClientsEntity getClient(Integer id) {
        Session session = HibernateSessions.getSession();
        ClientsEntity client = session.get(ClientsEntity.class, id);
        session.close();
        return client;
    }

    public static ClientsEntity getServiceOwner(Integer serviceId) {
        Session session = HibernateSessions.getSession();
        ClientsEntity client = session.get(ServicesEntity.class, serviceId).getClient();
        session.close();
        return client;
    }

    public static ServicesEntity getService(Integer id) {
        Session session = HibernateSessions.getSession();
        ServicesEntity service = session.get(ServicesEntity.class, id);
        session.close();
        return service;
    }

    public static Collection<ServicesEntity> getServicesOfClient(Integer clientId) {
        Session session = HibernateSessions.getSession();
        ClientsEntity client = session.get(ClientsEntity.class, clientId);
        Hibernate.initialize(client.getServicesEntitySet());
        Collection<ServicesEntity> servicesEntityCollection = client.getServicesEntitySet();
        session.close();
        return servicesEntityCollection;

    }

    public static Collection<ServicesEntity> findByType(String type) {
        Session session = getSession();
        TypedQuery<ServicesEntity> servicesByTypeQuery = session.createQuery("from hibernate.entity.ServicesEntity where type = :type", ServicesEntity.class);
        servicesByTypeQuery.setParameter("type", type);
        List<ServicesEntity> servicesByType = servicesByTypeQuery.getResultList();
        session.close();
        return servicesByType;
    }
    /*public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }*/
}