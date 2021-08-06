package dao.impl;

import dao.DaoType;
import dao.DataSourcePool;
import dao.exception.DaoExceptionDb;
import dao.exception.DaoExceptionJson;
import org.codehaus.jackson.map.ObjectMapper;
import pack.Account;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataSourceInitializer {
    private final DaoType type;

    public DataSourceInitializer(DaoType type) {
        this.type = type;
    }

    public void initialise() throws DaoExceptionJson, DaoExceptionDb {

        if (type == DaoType.db) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/db.sql"))) {
                StringBuilder stringBuilder = new StringBuilder();
                while (bufferedReader.ready()) {
                    stringBuilder.append(bufferedReader.readLine()).append("\n");
                }
                try (Connection connection = DataSourcePool.getConnection();
                     PreparedStatement statement = connection.prepareStatement(stringBuilder.toString())) {
                    statement.execute();
                }
            } catch (IOException | SQLException e) {
                throw new DaoExceptionDb("Error db initialization", e);
            }

        } else if (type == DaoType.json) {
            final File file = new File("resources/accounts.json");
            final ObjectMapper mapper = new ObjectMapper();
            try {
                if (!file.exists()) {
                    file.createNewFile();
                    Account[] accounts = {
                            (new Account(1, "Иванов Иван", 500)),
                            (new Account(2, "Валентинов Валентин", 600)),
                            (new Account(3, "Константинов Константин", 700)),
                            (new Account(4, "Михайлов Михаил", 800)),
                            (new Account(5, "Гай Игоорь", 900)),
                            (new Account(6, "Носов Николай", 1000)),
                            (new Account(7, "Николаев Константин", 10100)),
                            (new Account(8, "Михайлов Алексей", 2300)),
                            (new Account(9, "Константинов Шон", 15005)),
                            (new Account(10, "Бабушкин Михаил", 0))
                    };
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(fileOutputStream, accounts);
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                throw new DaoExceptionJson("Error initialization file", e);
            }
        } else {
            throw new UnsupportedOperationException("Dao type not found");
        }
    }
}