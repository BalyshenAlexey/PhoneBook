package com.example.phonebook.service;

import com.example.phonebook.dto.BaseResponse;
import com.example.phonebook.entity.Contact;

import java.util.List;

public interface ContactsService {
    List<Contact> getContacts(String term);

    BaseResponse createContact(Contact contact);

    BaseResponse deleteContact(int id);

    BaseResponse saveContact(Contact contact);
}
