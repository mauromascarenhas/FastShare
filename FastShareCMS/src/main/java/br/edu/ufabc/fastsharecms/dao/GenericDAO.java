package br.edu.ufabc.fastsharecms.dao;

import java.util.List;

/**
 *
 * @author Mauro
 * @param <T>
 */
public interface GenericDAO<T> {
    public Boolean exists(T item);
    public boolean exists(Long index);
    public Boolean insert(T item);
    public Boolean remove(T item);
    public Boolean remove(Long index);
    public Boolean update(Long index, T newData);
    public T select(Long index);
    public List<T> selectAll();
}
