package com.medical.examination.service;

import com.medical.examination.entity.Account;
import com.medical.examination.entity.ClinicWorking;
import com.medical.examination.entity.Customer;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.repository.ClinicWorkingRepository;
import com.medical.examination.service.impl.AccountServiceImpl;
import com.medical.examination.service.impl.ClinicWorkingServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

@ExtendWith(MockitoExtension.class)
class ClinicWorkingServiceTest {

    @Mock
    private ClinicWorkingRepository clinicWorkingRepository;
    @InjectMocks
    private ClinicWorkingServiceImpl clinicWorkingService;

    private ClinicWorking clinicWorking;
    private Account account;

    @BeforeEach
    void setUp() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2000-05-05");
        Date date_created = dateFormat.parse("2022-08-18");


        account = Account.builder().id(2L).username("account2")
                .password("123456").isActive(1L)
                .fullname("Name2").address("address")
                .dob(date1).gender(1L).role(2L).isWorking(1L).status(1L).email("email@gmail.com").build();

        Customer customer = Customer.builder().id(1L).fullname("customer3")
                .phone("0123456789").gender(1L)
                .fullname("Name3").address("address")
                .job("Sinh Viên").address("Hòa Lạc").dob(date1).country("Việt Nam")
                .email("customer@gmail.com").desc("bình thường").createdDate(date_created)
                .build();
        clinicWorking = ClinicWorking.builder().id(1L).account(account).customer(customer)
                .no(1L).createdDate(date_created).status(1L)
                .build();
    }
    @DisplayName("JUnit test for saveClinicWorking method")
    @Test
    void saveClinicWorking() {
        // given - precondition or setup
        given(clinicWorkingRepository.save(clinicWorking)).willReturn(clinicWorking);
        // when -  action or the behaviour that we are going test
        ClinicWorking clinicWorking1 = clinicWorkingService.saveClinicWorking(clinicWorking);
        // then - verify the output
        Assertions.assertThat(clinicWorking1).isEqualTo(clinicWorking);
        Assertions.assertThat(clinicWorking1).isNotNull();
    }
    @DisplayName("JUnit test for updateClinicWorking method")
    @Test
    void updateClinicWorking() {
        // given - precondition or setup
        given(clinicWorkingRepository.save(clinicWorking)).willReturn(clinicWorking);
        clinicWorking.setStatus(3L);
        // when -  action or the behaviour that we are going test
        ClinicWorking clinicWorkingUpdate = clinicWorkingService.updateClinicWorking(clinicWorking);
        // then - verify the output
        Assertions.assertThat(clinicWorkingUpdate.getStatus()).isEqualTo(3L);
    }
    @DisplayName("JUnit test for getClinicWorkingById method")
    @Test
    void getClinicWorkingById() {
        //Account acc = Account.b
        given(clinicWorkingRepository.getClinickWorkingById(clinicWorking.getId())).willReturn(clinicWorking);

        // when -  action or the behaviour that we are going test
        ClinicWorking clinicWorking1 = clinicWorkingService.getClinicWorkingById(clinicWorking.getId());
        System.out.println(account);
        // then - verify the output
        Assertions.assertThat(clinicWorking1).isNotNull();
    }

    @DisplayName("JUnit test for updateAccountIdClinicWorking method")
    @Test
    void updateAccountIdClinicWorking() {
    }
}