package com.medical.examination.service;

import com.medical.examination.entity.Invoice;
import com.medical.examination.findparams.InvoiceFindParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface InvoiceService {
	Invoice saveInvoice(Invoice invoice);
	void deleteInvoice(Long id);
	Invoice getInvoiceById(Long id);
	Page<Invoice> findInvoice(Pageable pageable, InvoiceFindParams findParams);
}
