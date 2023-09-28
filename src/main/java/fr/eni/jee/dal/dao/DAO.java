package fr.eni.jee.dal.dao;

import java.util.List;

import fr.eni.jee.dal.DALException;

public interface DAO<T> {
    void insert(T var) throws DALException;
    T selectById(int id) throws DALException;
    List<T> selectAll() throws DALException;
    void update(T var) throws DALException;
    void delete(int id) throws DALException;

}
