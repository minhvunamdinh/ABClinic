package com.medical.examination.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.medical.examination.entity.Invoice;
import com.medical.examination.findparams.InvoiceFindParams;

@Validated
@Transactional
public interface InvoiceService {
	Invoice saveInvoice(Invoice invoice);
	void deleteInvoice(Long id);
	Invoice getInvoiceById(Long id);
	Page<Invoice> findInvoice(Pageable pageable, InvoiceFindParams findParams);
	void updateIsDiscounted(Long id, Long isDiscounted);
}
