package com.medical.examination.service;

import com.medical.examination.entity.Customer;
import com.medical.examination.findparams.CustomerFindParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CustomerService {
	Customer saveCustomer(Customer customer);
	void deleteCustomer(Long id);
	Customer getCustomerById(Long id);
	Long getCountCustomerById(Long id);
	Page<Customer> findCustomer(Pageable pageable, CustomerFindParams findParams);
	Long checkCustomerExaminating(Long id);
}
