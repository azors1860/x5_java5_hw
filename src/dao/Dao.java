package dao;

import dao.exception.DaoExceptionDb;
import dao.exception.DaoExceptionJson;
import exceptions.UnknownAccountException;

import java.util.List;

public interface Dao<T> {
    void create(T item) throws DaoExceptionDb, DaoExceptionJson;
    T read(int id) throws UnknownAccountException, DaoExceptionDb, DaoExceptionJson;
    void update(T item) throws UnknownAccountException, DaoExceptionDb, DaoExceptionJson;
    void delete(int id) throws DaoExceptionDb, DaoExceptionJson;
    List<T> getListAccounts() throws DaoExceptionJson, DaoExceptionDb;
}
