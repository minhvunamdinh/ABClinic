package com.medical.examination.service.impl;

import com.medical.examination.entity.Customer;
import com.medical.examination.findparams.CustomerFindParams;
import com.medical.examination.repository.CustomerRepository;
import com.medical.examination.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long id) {
		this.customerRepository.deleteById(id);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return this.customerRepository.getCustomerById(id);
	}

	@Override
	public Page<Customer> findCustomer(Pageable pageable, CustomerFindParams findParams) {
		Page<Customer> pageResult = this.customerRepository.findAll(new Specification<Customer>() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (findParams != null) {
					if (findParams.getFullname() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("username")),
								"%" + findParams.getFullname().trim().toLowerCase() + "%")));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}

	@Override
	public Long getCountCustomerById(Long id) {
		return this.customerRepository.getCountCustomerById(id);
	}

}
