package com.medical.examination.service.impl;

import com.medical.examination.entity.Account;
import com.medical.examination.findparams.AccountFindParams;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account saveAccount(Account account) {
		return this.accountRepository.save(account);
	}

	@Override
	public void deleteAccount(Long id) {
		this.accountRepository.deleteById(id);
	}

	@Override
	public Account getAccountById(Long id) {
		return this.accountRepository.getAccountById(id);
	}

	@Override
	public Page<Account> findAccount(Pageable pageable, AccountFindParams findParams) {
		Page<Account> pageResult = this.accountRepository.findAll(new Specification<Account>() {
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
					if (findParams.getIsWorking() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isWorking"), findParams.getIsWorking())));
					}
					if (findParams.getRole() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("role"), findParams.getRole())));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}
	
	public static void main(String[] args) {
	}

}
