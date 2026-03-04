package io.github.isguliyev.print.stdout;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class MessageTest {
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream standardOutputStream;

    @BeforeAll
    public void setUpAll() {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.standardOutputStream = System.out;

        System.setOut(new PrintStream(this.byteArrayOutputStream, true));
    }

    @AfterAll
    public void tearDownAll() {
        System.setOut(this.standardOutputStream);
    }

    @AfterEach
    public void tearDown() {
        this.byteArrayOutputStream.reset();
    }

    @Test
    public void greet_printsHelloWorld() {
        Message.greet();

        assertEquals("Hello, World!\n", this.byteArrayOutputStream.toString());
    }
}