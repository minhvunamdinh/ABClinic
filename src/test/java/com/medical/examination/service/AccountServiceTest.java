package com.medical.examination.service;

import com.medical.examination.entity.Account;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.request.AccountRequest;
import com.medical.examination.service.impl.AccountServiceImpl;
import lombok.experimental.SuperBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.security.auth.login.AccountNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    private Account acc;
    private Account newAccount;

    @BeforeEach
    void setUp() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2000-05-05");
        acc = Account.builder().id(1L).username("account1")
                .password("123456").isActive(1L)
                .fullname("Name1").address("address")
                .dob(date1).gender(1L).role(1L).isWorking(1L).status(1L).email("email@gmail.com").build();

        newAccount = new Account(1L,"account1", "123456", 1L, "Name1", "Address"
                , date1, 1L, 1L, 1L, 1L, "email1@gmail.com");
    }

    @DisplayName("JUnit test for saveAccount method")
    @Test
    void givenAccountObject_whenSaveCategory_thenReturnAccountObject() {
        // given - precondition or setup
        given(accountRepository.save(newAccount)).willReturn(newAccount);
        // when -  action or the behaviour that we are going test
        Account account = accountService.saveAccount(newAccount);
        System.out.println(account);
        // then - verify the output
        Assertions.assertThat(account).isEqualTo(newAccount);
        Assertions.assertThat(account).isNotNull();
    }
    @DisplayName("JUnit test for getAccountById method")
    @Test
    void givenAccountObject_whenGetAccountById_thenReturnAccountObject() {
        // given - precondition or setup
        //Account acc = Account.b
        System.out.println(acc.getId());
        given(accountRepository.getAccountById(acc.getId())).willReturn(acc);

        // when -  action or the behaviour that we are going test
        Account account = accountService.getAccountById(acc.getId());
        System.out.println(account);
        // then - verify the output
        Assertions.assertThat(account).isNotNull();
    }

}