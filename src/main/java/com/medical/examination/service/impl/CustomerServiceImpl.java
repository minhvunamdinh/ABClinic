package com.medical.examination.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.medical.examination.entity.Customer;
import com.medical.examination.findparams.CustomerFindParams;
import com.medical.examination.repository.CustomerRepository;
import com.medical.examination.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		if(customer.getId() == null) {
			customer.setCreatedDate(new Date());
		}
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
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("fullname")),
								"%" + findParams.getFullname().trim().toLowerCase() + "%")));
					}
					if (findParams.getPhone() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")),
								"%" + findParams.getPhone().trim().toLowerCase() + "%")));
					}
					if (findParams.getEmail() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
								"%" + findParams.getEmail().trim().toLowerCase() + "%")));
					}
					if (findParams.getGender() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gender"), findParams.getGender())));
					}
					if(findParams.isFindNewCustomer() == true) {
						Date firstDayOfMonth = new Date();
						firstDayOfMonth.setDate(1);
						firstDayOfMonth.setMonth((int) (new Date().getMonth()));
						firstDayOfMonth.setHours(0);
						firstDayOfMonth.setMinutes(0);
						firstDayOfMonth.setSeconds(0);

						Date newDateLastDayOfMonth = new Date();
						newDateLastDayOfMonth.setMonth((int) (firstDayOfMonth.getMonth()));
						newDateLastDayOfMonth.setHours(23);
						newDateLastDayOfMonth.setMinutes(59);
						newDateLastDayOfMonth.setSeconds(59);
						
				        Calendar calendar = Calendar.getInstance();  
				        calendar.setTime(newDateLastDayOfMonth);  

				        calendar.add(Calendar.MONTH, 1);  
				        calendar.set(Calendar.DAY_OF_MONTH, 1);  
				        calendar.add(Calendar.DATE, -1);  

				        Date lastDayOfMonth = calendar.getTime();
				        
				        System.out.println("1: " + firstDayOfMonth);
				        System.out.println("2: " + lastDayOfMonth);
				        
						predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), firstDayOfMonth)));
						predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), lastDayOfMonth)));
						
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

	@Override
	public Long checkCustomerExaminating(Long id) {
		return this.customerRepository.checkCustomerExaminating(id);
	}

}
