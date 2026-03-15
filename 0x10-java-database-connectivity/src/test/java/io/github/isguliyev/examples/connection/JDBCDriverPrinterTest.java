package io.github.isguliyev.examples.connection;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class JDBCDriverPrinterTest {
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream standardOutputStream;

    @BeforeAll
    void setUpAll() {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.standardOutputStream = System.out;

        System.setOut(new PrintStream(this.byteArrayOutputStream, true));
    }

    @AfterAll
    void tearDownAll() {
        System.setOut(this.standardOutputStream);
    }

    @AfterEach
    void tearDown() {
        this.byteArrayOutputStream.reset();
    }

    @Test
    void printAllDrivers_printsAllAvailableJdbcDriverNamesAndVersions() {
        JDBCDriverPrinter.printAllDrivers();

        assertTrue(this.byteArrayOutputStream.toString().contains("org.sqlite"));
    }
}