package com.hegazy.ssecuritypart21.controller;

import com.hegazy.ssecuritypart21.model.Contact;
import com.hegazy.ssecuritypart21.repo.ContactRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /*
    @PostMapping("/contact")
    @PreFilter("filterObject.contactName != 'Test'")
    public Contact saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        if(!contacts.isEmpty()){
            Contact contact = contacts.get(0);
            contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
            return contactRepository.save(contact);
        }
        else{
            return null;
        }
    }
    */

    /**
    * Handles HTTP POST requests to save contact inquiry details.
    * Accepts a list of Contact objects in the request body, processes the first contact,
    * assigns a generated service request number and creation date, saves it to the repository,
    * and returns a list containing the saved contact.
    * 
    * The method is secured with a {@PostFilter} annotation to exclude contacts
    * with the name "Test" from the returned list.
    *
    * @param contacts the list of Contact objects received in the request body
    * @return a list containing the saved Contact object, or an empty list if input is empty
    */
    @PostMapping("/contact")
    @PostFilter("filterObject.contactName != 'Test'")
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        List<Contact> returnContacts = new ArrayList<>();
        if(!contacts.isEmpty()){
            Contact contact = contacts.get(0);
            contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
            Contact savedContact = contactRepository.save(contact);
            returnContacts.add(savedContact);
        }
        return returnContacts;
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }

}