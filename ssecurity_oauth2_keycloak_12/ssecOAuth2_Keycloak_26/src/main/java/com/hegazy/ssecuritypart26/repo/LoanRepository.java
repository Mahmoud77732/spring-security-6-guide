package com.hegazy.ssecuritypart26.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.hegazy.ssecuritypart26.model.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {
	
	@PreAuthorize("hasRole('USER')")
	List<Loans> findByCustomerIdOrderByStartDtDesc(long customerId);

}
