package dao.impl;

import dao.Dao;
import dao.DaoFactory;
import dao.DaoType;
import dao.exception.DaoExceptionDb;
import dao.exception.DaoExceptionJson;
import pack.Account;

public class AccountDaoFactory implements DaoFactory<Account> {

   private final String typeDao;

    public AccountDaoFactory(String typeDao) {
        if (typeDao == null) {
            throw new NullPointerException("Input parameter == null");
        }
        this.typeDao = typeDao;
    }

    @Override
    public Dao<Account> getDao() throws DaoExceptionJson, DaoExceptionDb {
        Dao<Account> dao;
        DaoType type = DaoType.valueOf(typeDao);
        new DataSourceInitializer(type).initialise();
        switch (type){
            case db: dao = new DbAccountDao();
            break;
            case json: dao = new JsonAccountDao();
            break;
            default: throw new UnsupportedOperationException("Ошибка: не найден DAO c данным значением :"+typeDao);
        }
        return dao;
    }
}