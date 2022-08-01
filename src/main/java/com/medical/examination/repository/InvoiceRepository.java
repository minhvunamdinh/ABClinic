package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medical.examination.entity.Invoice;
public interface InvoiceRepository extends CrudRepository<Invoice, Long>,
PagingAndSortingRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
	@Query(value = "SELECT i FROM Invoice i WHERE i.id = :id")
	public Invoice getInvoiceById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE invoice SET is_discounted = :isDiscounted WHERE id = :id", nativeQuery = true)
	void updateIsDiscounted(@Param("id") Long id, @Param("isDiscounted") Long isDiscounted);
}
