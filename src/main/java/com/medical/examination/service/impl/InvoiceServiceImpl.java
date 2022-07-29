package com.medical.examination.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.medical.examination.entity.Invoice;
import com.medical.examination.entity.TestResult;
import com.medical.examination.findparams.InvoiceFindParams;
import com.medical.examination.repository.InvoiceRepository;
import com.medical.examination.service.InvoiceService;

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
					if(findParams.getAccountId() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("clinicWorking").get("account").get("id"),findParams.getAccountId())));
					}
					if(findParams.getMonth() != null) {
						Date firstDayOfMonth = new Date();
						firstDayOfMonth.setDate(1);
						firstDayOfMonth.setMonth((int) (findParams.getMonth() - 1));
						firstDayOfMonth.setHours(0);
						firstDayOfMonth.setMinutes(0);
						firstDayOfMonth.setSeconds(0);

						Date newDateLastDayOfMonth = new Date();
						newDateLastDayOfMonth.setMonth((int) (findParams.getMonth() - 1));
						newDateLastDayOfMonth.setHours(23);
						newDateLastDayOfMonth.setMinutes(59);
						newDateLastDayOfMonth.setSeconds(59);
						
				        Calendar calendar = Calendar.getInstance();  
				        calendar.setTime(newDateLastDayOfMonth);  

				        calendar.add(Calendar.MONTH, 1);  
				        calendar.set(Calendar.DAY_OF_MONTH, 1);  
				        calendar.add(Calendar.DATE, -1);  

				        Date lastDayOfMonth = calendar.getTime();
				        
						predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), firstDayOfMonth)));
						predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), lastDayOfMonth)));
						
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}
	
	public static void main(String[] args) {
		Date firstDayOfMonth = new Date();
		firstDayOfMonth.setDate(1);
		firstDayOfMonth.setMonth((int) (7 - 1));
		firstDayOfMonth.setHours(0);
		firstDayOfMonth.setMinutes(0);
		firstDayOfMonth.setMinutes(0);

		Date newDateLastDayOfMonth = new Date();
		newDateLastDayOfMonth.setHours(23);
		newDateLastDayOfMonth.setMinutes(59);
		newDateLastDayOfMonth.setSeconds(59);
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(newDateLastDayOfMonth);  

        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);  

        Date lastDayOfMonth = calendar.getTime();
        System.out.println("First Day of Month: " + firstDayOfMonth);  
        System.out.println("Last Day of Month: " + lastDayOfMonth);
	}

}
