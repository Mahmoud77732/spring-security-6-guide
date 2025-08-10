package com.hegazy.ssecuritypart26.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hegazy.ssecuritypart26.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

    Optional<Customer> findByEmail(String email);

}
