package dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourcePool {

    private static BasicDataSource basicDataSource = null;

    private DataSourcePool() {
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (basicDataSource == null) {
            basicDataSource = new BasicDataSource();

            basicDataSource.setUsername("postgres");
            basicDataSource.setPassword("docker");
            basicDataSource.setDriverClassName("org.postgresql.Driver");
            basicDataSource.setUrl("jdbc:postgresql://localhost:5432/");
            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxIdle(10);
        }
        return basicDataSource.getConnection();
    }
}
