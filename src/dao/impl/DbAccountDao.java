package dao.impl;

import dao.Dao;
import dao.exception.DaoExceptionDb;
import dao.exception.DaoExceptionJson;
import exceptions.UnknownAccountException;
import pack.Account;
import dao.DataSourcePool;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbAccountDao implements Dao<Account> {

    private final String INSERT_ACCOUNT = "INSERT INTO accounts (holder, amount) VALUES (?,?);";
    private final String SELECT_ACCOUNT = "SELECT * FROM accounts WHERE id = ?";
    private final String UPDATE_ACCOUNT = "UPDATE accounts SET amount = ?, holder = ? WHERE id = ?";
    private final String DELETE_ACCOUNT = "DELETE FROM accounts WHERE id = ?";
    private final String SELECT_ALL_ACCOUNTS = "SELECT * FROM accounts";


    @Override
    public void create(Account item) throws DaoExceptionDb {

        if (item == null) {
            throw new NullPointerException("Input parameter == null");
        }
        try (Connection connection = DataSourcePool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT)) {
            statement.setString(1, item.getHolder());
            statement.setInt(2, item.getAmount());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoExceptionDb("Error creating the account", e);
        }
    }

    @Override
    public void update(Account item) throws DaoExceptionDb {

        if (item == null) {
            throw new NullPointerException("Input parameter == null");
        }
        try (Connection connection = DataSourcePool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT)) {
            statement.setInt(1, item.getAmount());
            statement.setString(2, item.getHolder());
            statement.setInt(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoExceptionDb("Error updating the account", e);
        }
    }

    @Override
    public void delete(int id) throws DaoExceptionDb {
        try (Connection connection = DataSourcePool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoExceptionDb("Error deleting the account", e);
        }

    }

    @Override
    public ArrayList<Account> getListAccounts() throws DaoExceptionDb {
        ArrayList<Account> accounts = new ArrayList<>();
        try (Connection connection = DataSourcePool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCOUNTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String holder = resultSet.getString("holder");
                int amount = resultSet.getInt("amount");
                accounts.add(new Account(id, holder, amount));
            }
        } catch (SQLException e) {
            throw new DaoExceptionDb("Error reading the account", e);
        }
        if (accounts == null) {
            throw new DaoExceptionDb("Error reading the account");
        }
        return accounts;
    }

    @Override
    public Account read(int id) throws UnknownAccountException, DaoExceptionDb {
        Account account = null;
        try (Connection connection = DataSourcePool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String holder = resultSet.getString("holder");
                int amount = resultSet.getInt("amount");
                account = new Account(id, holder, amount);
            }
        } catch (SQLException e) {
            throw new DaoExceptionDb("Error reading the account id " + id, e);
        }
        if (account == null) {
            throw new UnknownAccountException("Аккаунт id" + id + " не найден");
        }
        return account;
    }
}


