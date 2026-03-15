package io.github.isguliyev.examples.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionInitializer {
    private static final String SQLITE_URL_PREFIX = "jdbc:sqlite:";

    public static Connection getSQLiteConnection(String databaseName) {
        final String sqliteConnectionUrl = SQLITE_URL_PREFIX + databaseName;

        try {
            return DriverManager.getConnection(sqliteConnectionUrl);
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to connect to SQLite database" ,exception);
        }
    }
}