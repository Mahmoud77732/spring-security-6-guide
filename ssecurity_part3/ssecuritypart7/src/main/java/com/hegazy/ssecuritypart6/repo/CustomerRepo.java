package com.hegazy.ssecuritypart6.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hegazy.ssecuritypart6.model.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long>{

    Optional<Customer> findByEmail(String email);

}
