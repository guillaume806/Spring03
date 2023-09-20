package com.example.spring03.services;

import com.example.spring03.models.ContactDAO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class ContactService {
    private final Map<UUID, ContactDAO> contacts;

    public ContactService() {
        this.contacts = new HashMap<>();

        ContactDAO contactA = ContactDAO.builder()
                .id(UUID.randomUUID())
                .name("Bernie")
                .email("fzafa@zafaz.fr")
                .build();

        ContactDAO contactB = ContactDAO.builder()
                .id(UUID.randomUUID())
                .name("Kurt")
                .email("Bg59@efzaf.fr")
                .build();

        ContactDAO contactC = ContactDAO.builder()
                .id(UUID.randomUUID())
                .name("Angusyoung")
                .email("BlackANgus@fzafaz.fr")
                .build();

        contacts.put(contactA.getId(), contactA);
        contacts.put(contactB.getId(), contactB);
        contacts.put(contactC.getId(), contactC);
    }

    public List<ContactDAO> getContacts() {
        return contacts.values().stream().toList();
    }

    public Optional<ContactDAO> getContactById(UUID id) {
        return contacts.values().stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public void addContact(ContactDAO contactData) {
        if (contactData.getId() == null) {
            contactData.setId(UUID.randomUUID());
        }

        contacts.put(contactData.getId(), contactData);

    }
}