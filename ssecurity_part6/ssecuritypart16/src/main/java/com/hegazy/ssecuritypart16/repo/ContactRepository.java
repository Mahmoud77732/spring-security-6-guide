package com.hegazy.ssecuritypart16.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hegazy.ssecuritypart16.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
	
}