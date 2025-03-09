package com.example.phonebook.dao;

import com.example.phonebook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByNameOrSurnameOrPhoneContaining(String name, String surname, String phone);

    boolean existsByPhone(String phone);
}
