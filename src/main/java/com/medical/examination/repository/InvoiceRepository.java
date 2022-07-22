package com.medical.examination.repository;

import com.medical.examination.entity.Invoice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>,
PagingAndSortingRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
	@Query(value = "SELECT i FROM Invoice i WHERE i.id = :id")
	public Invoice getInvoiceById(@Param("id") Long id);
}
