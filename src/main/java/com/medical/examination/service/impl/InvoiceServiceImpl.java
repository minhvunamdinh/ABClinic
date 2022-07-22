package com.medical.examination.service.impl;

import com.medical.examination.entity.Invoice;
import com.medical.examination.findparams.InvoiceFindParams;
import com.medical.examination.repository.InvoiceRepository;
import com.medical.examination.service.InvoiceService;
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
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public Invoice saveInvoice(Invoice invoice) {
		return this.invoiceRepository.save(invoice);
	}

	@Override
	public void deleteInvoice(Long id) {
		this.invoiceRepository.deleteById(id);
	}

	@Override
	public Invoice getInvoiceById(Long id) {
		return this.invoiceRepository.getInvoiceById(id);
	}

	@Override
	public Page<Invoice> findInvoice(Pageable pageable, InvoiceFindParams findParams) {
		Page<Invoice> pageResult = this.invoiceRepository.findAll(new Specification<Invoice>() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (findParams != null) {
					if (findParams.getCode() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("code")),
								"%" + findParams.getCode().trim().toLowerCase() + "%")));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}

}
