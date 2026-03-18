package io.github.isguliyev.examples.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionInitializerTest {
    private static final String DATABASE_NAME = "test.db";

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(Path.of(DATABASE_NAME));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(DATABASE_NAME));
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
            assertTrue(Files.exists(Path.of(DATABASE_NAME)));
        }
    }
}