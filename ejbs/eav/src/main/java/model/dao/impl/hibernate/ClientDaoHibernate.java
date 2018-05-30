package model.dao.impl.hibernate;

import model.client.ClientObject;
import model.dao.GenericDao;
import model.dao.impl.hibernate.eav.ClientsEntity;

import java.util.List;

public class ClientDaoHibernate extends AbstractEntityManager<ClientsEntity, ClientObject> implements GenericDao<ClientObject> {

    @Override
    public ClientObject persist(ClientObject object) throws InstantiationException, IllegalAccessException {
        object = persistObject(object, ClientsEntity.class);
        return object;
    }

    @Override
    public ClientObject getById(Integer id) {
        return getObjectById(id, ClientsEntity.class);
    }

    @Override
    public List<ClientObject> getAll() {
        return getAllObjects(ClientsEntity.class);
    }

    @Override
    public void delete(ClientObject object) {
        deleteObject(object, ClientsEntity.class);
    }
}
