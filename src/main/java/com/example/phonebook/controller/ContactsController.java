package com.example.phonebook.controller;

import com.example.phonebook.converter.ContactDtoToContactConverter;
import com.example.phonebook.converter.ContactToContactDtoConverter;
import com.example.phonebook.dto.BaseResponse;
import com.example.phonebook.dto.ContactDto;
import com.example.phonebook.entity.Contact;
import com.example.phonebook.service.ContactsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@AllArgsConstructor
public class ContactsController {
    private final ContactsService contactsService;
    private final ContactDtoToContactConverter contactDtoToContactConverter;
    private final ContactToContactDtoConverter contactToContactDtoConverter;

    @GetMapping
    public List<ContactDto> getContacts(@RequestParam String term) {
        return contactToContactDtoConverter.convert(contactsService.getContacts(term));
    }

    @PostMapping
    public BaseResponse createContact(@RequestBody ContactDto contactDto) {
        Contact contact = contactDtoToContactConverter.convert(contactDto);
        contact.setId(null);
        return contactsService.createContact(contact);
    }

    @DeleteMapping()
    public BaseResponse deleteContact(@RequestParam String ids) {
        return contactsService.deleteContact(ids.split(","));
    }

    @PutMapping
    public BaseResponse saveContact(@RequestBody ContactDto contactDto) {
        Contact contact = contactDtoToContactConverter.convert(contactDto);
        return contactsService.saveContact(contact);
    }
}
