package com.medical.examination.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.medical.examination.entity.Account;
import com.medical.examination.findparams.AccountFindParams;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.service.AccountService;

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
		}, pageable);
		return pageResult;
	}
	
	public static void main(String[] args) {
	}

	@Override
	public void updateAccountStatus(Long id, Long status) {
		this.accountRepository.updateAccountStatus(id, status);
	}

}
