package com.medical.examination.service;

import com.medical.examination.entity.Account;
import com.medical.examination.entity.ClinicWorking;
import com.medical.examination.entity.Customer;
import com.medical.examination.repository.CustomerRepository;
import com.medical.examination.service.impl.CustomerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2000-05-05");
        Date date_created = dateFormat.parse("2022-08-18");
        customer1 = Customer.builder().id(1L).fullname("customer1")
                .phone("0123456789").gender(1L)
                .fullname("Name1").address("address")
                .job("Sinh Viên").address("Hòa Lạc").dob(date1).country("Việt Nam")
                .email("customer@gmail.com").desc("bình thường").createdDate(date_created)
                .build();
        customer2 = Customer.builder().id(1L).fullname("customer2")
                .phone("0123456789").gender(1L)
                .fullname("Name2").address("address")
                .job("Sinh Viên").address("Hòa Lạc").dob(date1).country("Việt Nam")
                .email("customer@gmail.com").desc("bình thường").createdDate(date_created)
                .build();
        customer3 = Customer.builder().id(1L).fullname("customer3")
                .phone("0123456789").gender(1L)
                .fullname("Name3").address("address")
                .job("Sinh Viên").address("Hòa Lạc").dob(date1).country("Việt Nam")
                .email("customer@gmail.com").desc("bình thường").createdDate(date_created)
                .build();
    }

    @DisplayName("JUnit test for saveCustomer method")
    @Test
    void saveCustomer() {
        // given - precondition or setup
        given(customerRepository.save(customer1)).willReturn(customer1);
        // when -  action or the behaviour that we are going test
        Customer customer = customerService.saveCustomer(customer1);
        // then - verify the output
        Assertions.assertThat(customer).isEqualTo(customer1);
        Assertions.assertThat(customer).isNotNull();
    }

    @DisplayName("JUnit test for deleteCustomer method")
    @Test
    void deleteCustomer() {
    }

    @DisplayName("JUnit test for getCustomerById method")
    @Test
    void getCustomerById() {
        //
        given(customerRepository.getCustomerById(customer1.getId())).willReturn(customer1);

        // when -  action or the behaviour that we are going test
        Customer customer = customerService.getCustomerById(customer1.getId());
        // then - verify the output
        Assertions.assertThat(customer).isNotNull();
    }

    @DisplayName("JUnit test for findCustomer method")
    @Test
    void findCustomer() {
    }

    @DisplayName("JUnit test for getCountCustomerById method")
    @Test
    void getCountCustomerById() {
        //
        given(customerRepository.getCountCustomerById(customer1.getId())).willReturn(1L);
        // when -  action or the behaviour that we are going test
        Long count = customerService.getCountCustomerById(customer1.getId());
        // then - verify the output
        Assertions.assertThat(count).isEqualTo(1L);
    }

    @DisplayName("JUnit test for checkCustomerExaminating method")
    @Test
    void checkCustomerExaminating() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = dateFormat.parse("2000-05-05");
        Date date_created = dateFormat.parse("2022-08-20");

        Account account = Account.builder().id(2L).username("account2")
                .password("123456").isActive(1L)
                .fullname("Name2").address("address")
                .dob(date1).gender(1L).role(2L).isWorking(1L).status(1L).email("email@gmail.com").build();
        ClinicWorking clinicWorking = ClinicWorking.builder().id(1L).account(account).customer(customer1)
                .no(1L).createdDate(date_created).status(3L)
                .build();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime next_day = today.plusDays(1);
        Long count = 0L;
        if (date_created.before(dateFormat.parse(today.toString()))
                && date_created.after(dateFormat.parse(next_day.toString())) ){
            count = 1L;
        }
        given(customerRepository.checkCustomerExaminating(customer1.getId())).willReturn(count);
        Long countcheck = customerService.checkCustomerExaminating(customer1.getId());
        // then - verify the output
        Assertions.assertThat(countcheck).isEqualTo(count);
    }
}