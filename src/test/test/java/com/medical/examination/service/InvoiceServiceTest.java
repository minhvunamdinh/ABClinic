package com.medical.examination.service;

import com.medical.examination.entity.Account;
import com.medical.examination.entity.ClinicWorking;
import com.medical.examination.entity.Customer;
import com.medical.examination.entity.Invoice;
import com.medical.examination.repository.InvoiceRepository;
import com.medical.examination.service.impl.InvoiceServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;
    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    private Invoice invoice;

    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2000-05-05");
        Date date_created = dateFormat.parse("2022-08-20");
        Date date_return = dateFormat.parse("2022-08-23");
        Account account = Account.builder().id(2L).username("account2")
                .password("123456").isActive(1L)
                .fullname("Name2").address("address")
                .dob(date1).gender(1L).role(2L).isWorking(1L).status(1L).email("email@gmail.com").build();

        Customer customer = Customer.builder().id(1L).fullname("customer3")
                .phone("0123456789").gender(1L)
                .fullname("Name3").address("address")
                .job("Sinh Viên").address("Hòa Lạc").dob(date1).country("Việt Nam")
                .email("customer@gmail.com").desc("bình thường").createdDate(date_created)
                .build();
        ClinicWorking clinicWorking = ClinicWorking.builder().id(1L).account(account).customer(customer)
                .no(1L).createdDate(date_created).status(1L)
                .build();
        invoice = Invoice.builder().id(1L).code("code").lstTest("1,2,3,4,5")
                .lstMedicine("").totalCostPrice(150000).totalSellPrice(250000)
                .createdDate(date_created).clinicWorking(clinicWorking)
                .isDiscounted(0L).testResult("Tốt").diagnosticResult("OK").lstCostPrice("30000,30000,30000,30000,30000")
                .lstSellPrice("50000,50000,50000,50000,50000").timeReturn(date_return)
                .conclusion("Hãy ăn uống đầy đủ").prescription("Thuốc đặc biệt")
                .build();
    }

    @Test
    void saveInvoice() {
        // given - precondition or setup
        given(invoiceRepository.save(invoice)).willReturn(invoice);
        // when -  action or the behaviour that we are going test
        Invoice invoice_save = invoiceService.saveInvoice(invoice);
        // then - verify the output
        Assertions.assertThat(invoice_save).isEqualTo(invoice);
        Assertions.assertThat(invoice_save).isNotNull();
    }

    @Test
    void deleteInvoice() {
    }

    @Test
    void getInvoiceById() {
        // given - precondition or setup
        given(invoiceRepository.getInvoiceById(invoice.getId())).willReturn(invoice);

        // when -  action or the behaviour that we are going test
        Invoice invoice1 = invoiceService.getInvoiceById(invoice.getId());
        // then - verify the output
        Assertions.assertThat(invoice1).isNotNull();
    }

    @Test
    void findInvoice() {
    }

    @Test
    void updateIsDiscounted() {
        // given - precondition or setup
        doNothing().when(invoiceRepository).updateIsDiscounted(invoice.getId(), 1L);
        // when -  action or the behaviour that we are going test
        invoiceService.updateIsDiscounted(invoice.getId(), 1L);
        // then - verify the output
        Mockito.verify(invoiceRepository, times(1)).updateIsDiscounted(invoice.getId(),1L);
    }
}