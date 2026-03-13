package io.github.isguliyev.examples.serialization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializerTest {
    private String fileName;
    private Serializer<Employee> serializer;
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        this.fileName = "employees.data";
        this.serializer = new Serializer<Employee>(this.fileName);

        this.employees = List.of(
            new Employee("Adrian Shephard", "pipeWrench"),
            new Employee("Gordon Freeman", "crowbar")
        );
    }

    @Test
    void serialize_serializesListOfObjects() throws Exception {
        this.serializer.serialize(this.employees);

        try (
            final FileInputStream fileInputStream = new FileInputStream(this.fileName);
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            assertEquals(this.employees, (List<Employee>) objectInputStream.readObject());
        }
    }

    @Test
    void deserialize_deserializesListOfObjectsFromFile() throws Exception {
        try (
            final FileOutputStream fileOutputStream = new FileOutputStream(this.fileName);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(this.employees);
        }

        assertEquals(this.employees, this.serializer.deserialize());
    }
}