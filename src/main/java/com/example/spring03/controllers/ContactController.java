package com.example.spring03.controllers;


import com.example.spring03.exceptions.ResourceNotFound;
import com.example.spring03.models.ContactDAO;
import com.example.spring03.services.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public String listContacts(org.springframework.ui.Model model, @RequestParam(value = "name", defaultValue = "") String filterByName) {
        List<ContactDAO> contacts = contactService.getContacts();

        if (!filterByName.isEmpty() && !filterByName.isBlank()) {
            contacts = contacts.stream().filter(d -> d.getName().startsWith(filterByName)).toList();
        }

        model.addAttribute("contacts", contacts);

        return "contacts/list";
    }

    @GetMapping("/{contactId}")
    public String getContactDetails(@PathVariable("contactId") UUID id, org.springframework.ui.Model model) {
        Optional<ContactDAO> foundContact = contactService.getContactById(id);

        if (foundContact.isPresent()) {
            model.addAttribute("contact", foundContact.get());
            model.addAttribute("mode", "details");

            return "contacts/form";
        }

        throw new ResourceNotFound();
    }

    @GetMapping("/add")
    public String getContactForm(Model model, @RequestParam(value = "example", defaultValue = "default") String blabla) {
//        log.debug("blabla: " + blabla);

        model.addAttribute("contact", ContactDAO.builder().build());
        model.addAttribute("mode", "add");

        return "contacts/form";
    }

    @PostMapping("/add")
    public String addContactHandler(ContactDAO newContact) {
        contactService.addContact(newContact);

        return "redirect:/contacts";
    }
}