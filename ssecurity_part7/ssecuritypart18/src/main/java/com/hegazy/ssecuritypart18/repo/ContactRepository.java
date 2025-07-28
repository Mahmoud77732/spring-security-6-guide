package com.hegazy.ssecuritypart18.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hegazy.ssecuritypart18.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
	
}