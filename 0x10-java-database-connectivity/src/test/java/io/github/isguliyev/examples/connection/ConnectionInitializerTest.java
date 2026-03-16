package io.github.isguliyev.examples.connection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionInitializerTest {
    private static final String DATABASE_NAME = "test.db";
    private final File databaseFile = new File(DATABASE_NAME);

    @AfterEach
    void tearDown() {
        if (this.databaseFile.exists()) {
            this.databaseFile.delete();
        }
    }

    @Test
    void getSQLiteConnection_returnsConnectionObject() throws SQLException {
        try (Connection connection = ConnectionInitializer.getSQLiteConnection(DATABASE_NAME)) {
            assertTrue(connection.isValid(1));
        }
    }

    @Test
    void getSQLiteConnection_createsDatabaseFile() throws SQLException {
        try (Connection connection = ConnectionInitializer.getSQLiteConnection(DATABASE_NAME)) {
            assertTrue(this.databaseFile.exists());
        }
    }
}