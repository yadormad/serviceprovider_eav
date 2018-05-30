package model.dao;

import java.util.List;

public interface GenericDao<T> {
    T persist(T object) throws InstantiationException, IllegalAccessException;
    T getById(Integer id);
    List<T> getAll();
    void delete(T object);

}
