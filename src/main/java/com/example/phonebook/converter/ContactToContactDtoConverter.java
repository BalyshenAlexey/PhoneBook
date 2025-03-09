package com.example.phonebook.converter;

import com.example.phonebook.dto.ContactDto;
import com.example.phonebook.entity.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactToContactDtoConverter implements Converter<Contact, ContactDto> {
    @Override
    public ContactDto convert(Contact source) {
        return new ContactDto(source.getId(), source.getName(), source.getSurname(), source.getPhone());
    }
}
