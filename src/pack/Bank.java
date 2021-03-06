package pack;

import dao.Dao;
import dao.exception.DaoExceptionDb;
import dao.exception.DaoExceptionJson;
import dao.impl.AccountDaoFactory;
import exceptions.NotEnoughMoneyException;
import exceptions.UnknownAccountException;

public class Bank implements AccountService {

    private final Dao<Account> accountDao;

    public Bank(String typeDb) throws DaoExceptionDb, DaoExceptionJson {
        accountDao = new AccountDaoFactory(typeDb).getDao();
    }

    @Override
    public void withDraw(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoExceptionDb, DaoExceptionJson {

        Account account = accountDao.read(accountId);
        account.setAmount(account.getAmount()-amount);
        accountDao.update(account);
    }

    @Override
    public void getBalance(int accountId) throws UnknownAccountException, DaoExceptionDb, DaoExceptionJson {

        Account account = accountDao.read(accountId);
        System.out.println(account.getAmount());
    }

    @Override
    public void deposit(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoExceptionDb, DaoExceptionJson {

        Account account = accountDao.read(accountId);
        account.setAmount(account.getAmount()+amount);
        accountDao.update(account);
    }

    @Override
    public void transfer(int from, int to, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoExceptionDb, DaoExceptionJson {

        Account accountFrom = accountDao.read(from);
        Account accountTo = accountDao.read(to);
        accountFrom.setAmount(accountFrom.getAmount()-amount);
        accountTo.setAmount(accountTo.getAmount()+amount);
        accountDao.update(accountFrom);
        accountDao.update(accountTo);
    }
}
