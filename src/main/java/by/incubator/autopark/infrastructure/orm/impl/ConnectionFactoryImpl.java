package by.incubator.autopark.infrastructure.orm.impl;

import by.incubator.autopark.infrastructure.core.annotations.Property;
import by.incubator.autopark.infrastructure.orm.ConnectionFactory;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

@NoArgsConstructor
public class ConnectionFactoryImpl implements ConnectionFactory {
    @Property
    private String url;
    @Property
    private String username;
    @Property
    private String password;
    private Connection connection;

    @SneakyThrows
    @Override
    public Connection getConnection() {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}
