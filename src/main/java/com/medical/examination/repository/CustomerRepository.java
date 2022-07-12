package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medical.examination.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>,
PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	@Query(value = "SELECT c FROM Customer c WHERE c.id = :id")
	public Customer getCustomerById(@Param("id") Long id);
	
	@Query(value = "SELECT COUNT(1) FROM Customer c WHERE c.id = :id")
	public Long getCountCustomerById(@Param("id") Long id);
}
