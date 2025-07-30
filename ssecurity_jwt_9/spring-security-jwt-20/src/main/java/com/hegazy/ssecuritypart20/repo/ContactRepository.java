package com.hegazy.ssecuritypart20.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hegazy.ssecuritypart20.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
	
}