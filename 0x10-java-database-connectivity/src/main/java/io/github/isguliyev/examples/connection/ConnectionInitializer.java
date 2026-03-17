package io.github.isguliyev.examples.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionInitializer {
    public static Connection getSQLiteConnection(String databaseName) {
        final String sqliteConnectionUrl = String.format("jdbc:sqlite:%s?foreign_keys=on", databaseName);

        try {
            return DriverManager.getConnection(sqliteConnectionUrl);
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to connect to SQLite database" ,exception);
        }
    }
}