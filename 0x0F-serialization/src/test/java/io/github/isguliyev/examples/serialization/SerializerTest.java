package io.github.isguliyev.examples.serialization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Files;

public class SerializerTest {
    private static final String FILE_NAME = "employees.data";
    private Serializer<Employee> serializer = new Serializer<Employee>(FILE_NAME);
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        this.employees = List.of(
            new Employee("Adrian Shephard", "pipeWrench"),
            new Employee("Gordon Freeman", "crowbar")
        );
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(FILE_NAME));
    }

    @Test
    void serialize_serializesListOfObjects() throws IOException, ClassNotFoundException {
        this.serializer.serialize(this.employees);

        try (
            final FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            assertEquals(this.employees, (List<Employee>) objectInputStream.readObject());
        }
    }

    @Test
    void deserialize_deserializesListOfObjectsFromFile() throws IOException, ClassNotFoundException {
        try (
            final FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(this.employees);
        }

        assertEquals(this.employees, this.serializer.deserialize());
    }
}