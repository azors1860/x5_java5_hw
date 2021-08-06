package dao.impl;

import dao.Dao;
import dao.exception.DaoExceptionJson;
import exceptions.UnknownAccountException;
import org.codehaus.jackson.map.ObjectMapper;
import pack.Account;

import java.io.*;
import java.util.*;

public class JsonAccountDao implements Dao<Account> {

    private final static File file = new File("resources/accounts.json");
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void create(Account item) throws DaoExceptionJson {

        if (item==null){
            throw new NullPointerException("Input parameter == null");
        }
        List<Account> accounts = getListAccounts();
        accounts.add(item);
        writerFileJson(accounts);
    }

    @Override
    public void update(Account item) throws UnknownAccountException, DaoExceptionJson {

        if (item==null){
            throw new NullPointerException("Input parameter == null");
        }
        List<Account> accounts = getListAccounts();
        int searchId = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == item.getId()) {
                searchId = i;
                break;
            }
        }
        if (searchId == -1) {
            throw new UnknownAccountException("Аккаунт с указанным id не найден");
        }
        accounts.set(searchId,item);
        writerFileJson(accounts);
    }

    @Override
    public void delete(int id) throws DaoExceptionJson {
        List<Account> accounts = getListAccounts();
        accounts.removeIf(temp -> temp.getId() == id);
        writerFileJson(accounts);
    }

    @Override
    public Account read(int id) throws UnknownAccountException, DaoExceptionJson {
        Account account = null;
        List<Account> accounts = getListAccounts();
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                account = acc;
            }
        }

        if (account == null) {
            throw new UnknownAccountException("Аккаунт не найден");
        }
        return account;
    }


    public List<Account> getListAccounts() throws DaoExceptionJson {
        List<Account> accounts;
        try {
            accounts = Arrays.asList(mapper.readValue(file, Account[].class));
        } catch (IOException e) {
            throw new DaoExceptionJson("Error getting the accounts", e);
        }

        if (accounts == null){
            throw new DaoExceptionJson("Failed to initialize the object");
        }else {
            return accounts;
        }
    }

    private void writerFileJson(Object array) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileOutputStream, array);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
