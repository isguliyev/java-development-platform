package io.github.isguliyev.examples.mobile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

public class MobileTest {
    private Mobile mobile;
    private Contact contact;

    @BeforeEach
    void setUp() {
        this.mobile = new Mobile();

        this.contact = new Contact("Roronoa Zoro", "000-000-0000", NumberType.MOBILE);

        this.mobile.addContact(this.contact);
    }

    @Test
    void findContactByName_returnsOptionalContact() {
        assertEquals(
            Optional.of(this.contact),
            this.mobile.findContactByName(this.contact.getName())
        );
    }

    @Test
    void addContact_throwsIllegalArgumentException_whenContactAlreadyExists() {
        assertThrows(IllegalArgumentException.class, () -> this.mobile.addContact(this.contact));
    }

    @Nested
    class ReplaceContactTest {
        @Test
        void replaceContact_replacesOldContactWithNewContact_whenContactsAreValid() {
            final Contact newContact = new Contact("Luffy", "000-000-0000", NumberType.MOBILE);

            mobile.replaceContact(contact, newContact);

            assertEquals(
                Optional.of(newContact),
                mobile.findContactByName(newContact.getName())
            );
        }

        @Test
        void replaceContact_throwsIllegalArgumentException_whenOldContactDoesNotExists() {
            mobile.deleteContact(contact);

            assertThrows(
                IllegalArgumentException.class,
                () -> mobile.replaceContact(contact, contact)
            );
        }

        @Test
        void replaceContact_throwsIllegalArgumentException_whenNewContactAlreadyExists() {
            assertThrows(
                IllegalArgumentException.class,
                () -> mobile.replaceContact(contact, contact)
            );
        }
    }

    @Nested
    class DeleteContactTest {
        @Test
        void deleteContact_deletesContact_whenContactExists() {
            final int initialSize = mobile.getContacts().size();

            mobile.deleteContact(contact);

            assertEquals(initialSize - 1, mobile.getContacts().size());
        }

        @Test
        void deleteContact_throwsIllegalArgumentException_whenContactDoesNotExists() {
            mobile.deleteContact(contact);

            assertThrows(
                IllegalArgumentException.class,
                () -> mobile.deleteContact(contact)
            );
        }
    }
}