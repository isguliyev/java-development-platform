package io.github.isguliyev.examples.connection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionInitializerTest {
    private final String databaseName = "test.db";
    private File file;

    @BeforeEach
    void setUp() {
        this.file = new File(this.databaseName);

        if (this.file.exists()) {
            this.file.delete();
        }
    }

    @AfterEach
    void tearDown() {
        this.file.delete();
    }

    @Test
    void getSQLiteConnection_returnsConnectionObject() throws SQLException {
        try (Connection connection = ConnectionInitializer.getSQLiteConnection(this.databaseName)) {
            assertTrue(connection.isValid(1));
        }
    }

    @Test
    void getSQLiteConnection_createsDatabaseFile() throws SQLException {
        try (Connection connection = ConnectionInitializer.getSQLiteConnection(this.databaseName)) {
            assertTrue(this.file.exists());
        }
    }
}