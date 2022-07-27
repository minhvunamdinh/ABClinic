package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

import com.medical.examination.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>,
PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	@Query(value = "SELECT c FROM Customer c WHERE c.id = :id")
	public Customer getCustomerById(@Param("id") Long id);
	
	@Query(value = "SELECT COUNT(1) FROM Customer c WHERE c.id = :id")
	public Long getCountCustomerById(@Param("id") Long id);
	
	/**
	 * Check bệnh nhân đã có phiếu khám bệnh trong phiên khám hôm nay chưa?
	 * @param id
	 * @return
	 */
	@Query(value = "SELECT COUNT(1) FROM customer a LEFT JOIN clinic_working b ON a.id = b.customer_id WHERE b.created_date >= curdate() AND b.created_date <= (curdate() + INTERVAL 1 DAY) AND a.id = :id", nativeQuery = true)
	public Long checkCustomerExaminating(@Param("id") Long id);
}
