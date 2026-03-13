package io.github.isguliyev.examples.serialization;

import java.util.List;
import java.util.ArrayList;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

public class Serializer <T extends Serializable> {
    private final String fileName;

    public Serializer(String fileName) {
        this.fileName = fileName;
    }

    @SuppressWarnings("unchecked")
    public List<T> deserialize() {
        final ArrayList<T> objects = new ArrayList<T>();

        try (
            final FileInputStream fileInputStream = new FileInputStream(this.fileName);
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            objects.addAll((List<T>) objectInputStream.readObject());
        } catch(Exception exception) {
            System.err.println(exception.toString());
        }

        return objects;
    }

    public void serialize(List<T> objects) {
        try (
            final FileOutputStream fileOutputStream = new FileOutputStream(this.fileName);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(objects);
        } catch(Exception exception) {
            System.err.println(exception.toString());
        }
    }

    public String getFileName() {
        return this.fileName;
    }
}