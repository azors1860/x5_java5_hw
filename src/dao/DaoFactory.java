package dao;

import dao.exception.DaoExceptionDb;
import dao.exception.DaoExceptionJson;

public interface DaoFactory<T> {
    Dao<T> getDao() throws DaoExceptionJson, DaoExceptionDb;
}
