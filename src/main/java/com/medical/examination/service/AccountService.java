package com.medical.examination.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import com.medical.examination.entity.Account;
import com.medical.examination.findparams.AccountFindParams;

@Validated
public interface AccountService {
	Account saveAccount(Account account);
	void deleteAccount(Long id);
	Account getAccountById(Long id);
	Page<Account> findAccount(Pageable pageable, AccountFindParams findParams);
}
