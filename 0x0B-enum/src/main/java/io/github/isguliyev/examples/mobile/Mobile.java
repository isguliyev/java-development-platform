package io.github.isguliyev.examples.mobile;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Mobile {
    private final List<Contact> contacts;

    public Mobile() {
        this.contacts = new ArrayList<Contact>();
    }

    public Optional<Contact> findContactByName(String name) {
        return this.contacts.stream().filter(
            (contact) -> contact.getName().equals(name)
        ).findFirst();
    }

    public void addContact(Contact contact) {
        if (this.contacts.contains(contact)) {
            throw new IllegalArgumentException("Contact already exists");
        }

        this.contacts.add(contact);
    }

    public void replaceContact(Contact oldContact, Contact newContact) {
        if (!this.contacts.contains(oldContact)) {
            throw new IllegalArgumentException("Old contact does not exist");
        }

        if (this.contacts.contains(newContact)) {
            throw new IllegalArgumentException("New contact already exists");
        }

        this.contacts.set(this.contacts.indexOf(oldContact), newContact);
    }

    public void deleteContact(Contact contact) {
        if (!this.contacts.contains(contact)) {
            throw new IllegalArgumentException("Contact does not exists");
        }

        this.contacts.remove(contact);
    }

    public List<Contact> getContacts() {
        return new ArrayList<Contact>(this.contacts);
    }
}