package com.medical.examination.service;

import com.medical.examination.entity.*;
import com.medical.examination.repository.InvoiceRepository;
import com.medical.examination.repository.TestResultRepository;
import com.medical.examination.service.impl.InvoiceServiceImpl;
import com.medical.examination.service.impl.TestResultServiceImpl;
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

class TestResultServiceTest {
    @Mock
    private TestResultRepository testResultRepository;
    @InjectMocks
    private TestResultServiceImpl testResultService;

    private TestResult testResult;

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
        testResult = TestResult.builder().id(1L).code("code").lstTest("1,2,3,4,5")
                .lstMedicine("").examinationCard("ỔN").examinationFee(200000.0)
                .totalCostPrice(150000.0).totalSellPrice(250000.0)
                .createdDate(date_created).clinicWorking(clinicWorking)
                .status(1L).testResult("Tốt").diagnosticResult("OK").isCalled(0L)
                .timeReturn(date_return)
                .conclusion("Hãy ăn uống đầy đủ").prescription("Thuốc đặc biệt")
                .build();
    }

    @Test
    void saveTestResult() {
        // given - precondition or setup
        given(testResultRepository.save(testResult)).willReturn(testResult);
        // when -  action or the behaviour that we are going test
        TestResult testResult1 = testResultService.saveTestResult(testResult);
        // then - verify the output
        Assertions.assertThat(testResult1).isEqualTo(testResult);
        Assertions.assertThat(testResult1).isNotNull();
    }

    @Test
    void deleteTestResult() {
    }

    @Test
    void getTestResultById() {
        // given - precondition or setup
        given(testResultRepository.getTestResultById(testResult.getId())).willReturn(testResult);

        // when -  action or the behaviour that we are going test
        TestResult testResult1 = testResultService.getTestResultById(testResult.getId());
        // then - verify the output
        Assertions.assertThat(testResult1).isNotNull();
    }

    @Test
    void findTestResult() {
    }

    @Test
    void updateIsCalledCustomerReturn() {
        // given - precondition or setup
        doNothing().when(testResultRepository).updateIsCalledCustomerReturn(testResult.getId(), 1L);
        // when -  action or the behaviour that we are going test
        testResultService.updateIsCalledCustomerReturn(testResult.getId(), 1L);
        // then - verify the output
        Mockito.verify(testResultRepository, times(1)).updateIsCalledCustomerReturn(testResult.getId(), 1L);
    }

    @Test
    void countHistoryTestResultCustomer() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2000-05-05");
        Date date_created = dateFormat.parse("2022-08-21");
        Date date_return = dateFormat.parse("2022-08-23");
        Account account3 = Account.builder().id(3L).username("account3")
                .password("123456").isActive(1L)
                .fullname("Name2").address("address")
                .dob(date1).gender(1L).role(2L).isWorking(1L).status(1L).email("email@gmail.com").build();

        Customer customer = Customer.builder().id(1L).fullname("customer3")
                .phone("0123456789").gender(1L)
                .fullname("Name3").address("address")
                .job("Sinh Viên").address("Hòa Lạc").dob(date1).country("Việt Nam")
                .email("customer@gmail.com").desc("bình thường").createdDate(date_created)
                .build();
        ClinicWorking clinicWorking2 = ClinicWorking.builder().id(2L).account(account3).customer(customer)
                .no(1L).createdDate(date_created).status(1L)
                .build();
        TestResult testResult2 = TestResult.builder().id(2L).code("code").lstTest("1,2,3,4,5")
                .lstMedicine("").examinationCard("ỔN").examinationFee(200000.0)
                .totalCostPrice(150000.0).totalSellPrice(250000.0)
                .createdDate(date_created).clinicWorking(clinicWorking2)
                .status(1L).testResult("Tốt").diagnosticResult("OK").isCalled(0L)
                .timeReturn(date_return)
                .conclusion("Hãy ăn uống đầy đủ").prescription("Thuốc đặc biệt")
                .build();

        // given - precondition or setup
        given(testResultRepository.countHistoryTestResultCustomer(customer.getId())).willReturn(2);

        // when -  action or the behaviour that we are going test
        int count = testResultService.countHistoryTestResultCustomer(customer.getId());
        // then - verify the output
        Assertions.assertThat(count).isEqualTo(2);
    }
}