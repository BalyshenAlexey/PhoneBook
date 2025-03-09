package com.example.phonebook.service;

import com.example.phonebook.dao.ContactsRepository;
import com.example.phonebook.dto.BaseResponse;
import com.example.phonebook.entity.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactsServiceImpl implements ContactsService {
    private final ContactsRepository contactsRepository;

    @Override
    public List<Contact> getContacts(String term) {
        if (term == null || term.trim().isEmpty()) {
            return contactsRepository.findAll();
        }

        return contactsRepository.findByNameOrSurnameOrPhoneContaining(term, term, term);
    }

    @Override
    public BaseResponse createContact(Contact contact) {
        String contactValidationResult = contactValidation(contact);

        if (contactValidationResult != null) {
            return BaseResponse.getErrorResponse(contactValidationResult);
        }

        contactsRepository.save(contact);

        return BaseResponse.getSuccessResponse();
    }

    @Override
    public BaseResponse deleteContact(int id) {
        contactsRepository.deleteById(id);
        return BaseResponse.getSuccessResponse();
    }

    @Override
    public BaseResponse saveContact(Contact contact) {
        String contactValidationResult = contactValidation(contact);

        if (contactValidationResult != null) {
            return BaseResponse.getErrorResponse(contactValidationResult);
        }

        contactsRepository.save(contact);

        return BaseResponse.getSuccessResponse();
    }

    private String contactValidation(Contact contact) {
        if (contact == null) {
            return "Контакт не может быть NULL";
        }

        if (contact.getName() == null || contact.getName().isEmpty()) {
            return "Поле \"Имя\" обязательно для заполнения";
        }

        if (contact.getSurname() == null || contact.getSurname().isEmpty()) {
            return "Поле \"Фамилия\" обязательно для заполнения";
        }

        if (contact.getPhone() == null || contact.getPhone().isEmpty()) {
            return "Поле \"Номер телефона\" обязательно для заполнения";
        }

        if (contactsRepository.existsByPhone(contact.getPhone())) {
            return "Пользователь с таким номером уже существует";
        }

        return null;
    }
}
