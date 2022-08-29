package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.medical.examination.entity.Account;
import com.medical.examination.findparams.AccountFindParams;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    void whenGetAll_shouldReturnList() throws ParseException {
        // 1. create mock data
        List<Account> mockAccounts = new ArrayList<Account>();
        Account account1 = new Account(1L,"UserName1", "Pass1",1L,"Name1","Address1", new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2000"),1L,1L,1L,1L,"email1@gmail.com");
        mockAccounts.add(account1);
        Account account2 = new Account(2L,"UserName2", "Pass2",1L,"Name2","Address2", new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2000"),1L,1L,1L,1L,"email2@gmail.com");

        mockAccounts.add(account2);

        Pageable pageAble = PageRequest.of(1, 10, Sort.by(Sort.Order.desc("id")));
        // 2. define behavior of Repository
        when(accountRepository.findAll(pageAble).getContent()).thenReturn(mockAccounts);

         // 3. call service method
        AccountFindParams findParams = new AccountFindParams();
        Page<Account> actualAccount =  accountService.findAccount(pageAble, findParams);
//
        // 4. assert the result
        assertThat(actualAccount.getContent().size()).isEqualTo(mockAccounts.size());

        // 4.1 ensure repository is called
        verify(accountRepository).findAll(new Specification<Account>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (findParams != null) {
                    if (findParams.getUsername() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("username")),
                                "%" + findParams.getUsername().trim().toLowerCase() + "%")));
                    }
                    if (findParams.getFullname() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("fullname")),
                                "%" + findParams.getFullname().trim().toLowerCase() + "%")));
                    }
                    if (findParams.getEmail() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
                                "%" + findParams.getEmail().trim().toLowerCase() + "%")));
                    }
                    if (findParams.getGender() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gender"), findParams.getGender())));
                    }
                    if (findParams.getIsWorking() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isWorking"), findParams.getIsWorking())));
                    }
                    if (findParams.getIsActive() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isActive"), findParams.getIsActive())));
                    }
                    if (findParams.getRole() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("role"), findParams.getRole())));
                    }
                    if (findParams.isBossUsing() == true) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.notEqual(root.get("role"), 1L)));
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageAble);
    }

    @Test
    void whenGetInvalidOne_shouldThrowException() throws ParseException {
        // 1. create mock data
        List<Account> mockAccounts = new ArrayList<>();
        Account account1 = new Account(4L,"UserName1", "Pass1",1L,"Name1","Address1", new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2000"),1L,1L,1L,1L,"email1@gmail.com");
        mockAccounts.add(account1);
        Account account2 = new Account(2L,"UserName2", "Pass2",1L,"Name2","Address2", new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2000"),1L,1L,1L,1L,"email2@gmail.com");

        mockAccounts.add(account2);
        Long accountId = 4L;

        // 2. define behavior of Repository

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account1));
        // 3. call service method
        Account actualAccount =  accountService.getAccountById(accountId);

        // 4. assert the result
        assertThat(actualAccount).isEqualTo(account1);

        // 4.1 ensure repository is called
        verify(accountRepository).findById(any(Long.class));
    }
}
