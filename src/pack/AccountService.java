package pack;

import dao.exception.DaoExceptionDb;
import dao.exception.DaoExceptionJson;
import exceptions.NotEnoughMoneyException;
import exceptions.UnknownAccountException;

public interface AccountService {
    void withDraw(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoExceptionDb, DaoExceptionJson;

    void getBalance(int accountId)
            throws UnknownAccountException, DaoExceptionDb, DaoExceptionJson;

    void deposit(int accountId, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoExceptionDb, DaoExceptionJson;

    void transfer(int from, int to, int amount)
            throws NotEnoughMoneyException, UnknownAccountException, DaoExceptionDb, DaoExceptionJson;
}
