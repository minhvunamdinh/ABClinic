package com.medical.examination.service;

import com.medical.examination.entity.Account;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.service.impl.AccountServiceImpl;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;
    private Account account2;
    private Account account3;

    @BeforeEach
    void setUp() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2000-05-05");
        account = Account.builder().id(1L).username("account1")
                .password("123456").isActive(1L)
                .fullname("Name1").address("address")
                .dob(date1).gender(1L).role(1L).isWorking(1L).status(1L).email("email@gmail.com").build();

        account2 = Account.builder().id(2L).username("account2")
                .password("123456").isActive(1L)
                .fullname("Name2").address("address")
                .dob(date1).gender(1L).role(2L).isWorking(1L).status(1L).email("email@gmail.com").build();

        account3 = Account.builder().id(3L).username("account3")
                .password("123456").isActive(1L)
                .fullname("Name3").address("address")
                .dob(date1).gender(1L).role(2L).isWorking(1L).status(1L).email("email@gmail.com").build();

    }

    @DisplayName("JUnit test for saveAccount method")
    @Test
    void givenAccountObject_whenSaveCategory_thenReturnAccountObject() {
        // given - precondition or setup
        given(accountRepository.save(account)).willReturn(account);
        // when -  action or the behaviour that we are going test
        Account account1 = accountService.saveAccount(account);
        // then - verify the output
        Assertions.assertThat(account1).isEqualTo(account);
        Assertions.assertThat(account1).isNotNull();
    }
    @DisplayName("JUnit test for getAccountById method")
    @Test
    void givenAccountObject_whenGetAccountById_thenReturnAccountObject() {
        // given - precondition or setup
        //Account acc = Account.b
        given(accountRepository.getAccountById(account.getId())).willReturn(account);

        // when -  action or the behaviour that we are going test
        Account account1 = accountService.getAccountById(account.getId());
        System.out.println(account);
        // then - verify the output
        Assertions.assertThat(account1).isNotNull();
    }
    @DisplayName("Junit test for updateAccountStatus")
    @Test
    void updateAccount() {
        // given - precondition or setup
        doNothing().when(accountRepository).updateAccountStatus(account.getId(), 0L);
        // when -  action or the behaviour that we are going test
        accountService.updateAccountStatus(account.getId(), 0L);
        // then - verify the output
        Mockito.verify(accountRepository, times(1)).updateAccountStatus(account.getId(),0L);

    }



}