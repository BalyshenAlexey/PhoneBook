package com.example.phonebook.converter;

import com.example.phonebook.dto.ContactDto;
import com.example.phonebook.entity.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactDtoToContactConverter implements Converter<ContactDto, Contact> {
    @Override
    public Contact convert(ContactDto source) {
        return new Contact(source.getId(), source.getName(), source.getSurname(), source.getPhone());
    }
}
