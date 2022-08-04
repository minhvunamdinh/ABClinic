package com.medical.examination.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.medical.examination.entity.Test;
import com.medical.examination.entity.TestResult;
import com.medical.examination.findparams.TestResultFindParams;
import com.medical.examination.repository.TestResultRepository;
import com.medical.examination.service.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {
	
	@Autowired
	TestResultRepository testResultRepository;

	@Override
	public TestResult saveTestResult(TestResult testResult) {
		testResult.setIsCalled(0L);
		return testResultRepository.save(testResult);
	}

	@Override
	public void deleteTestResult(Long id) {
		this.testResultRepository.deleteById(id);
	}

	@Override
	public TestResult getTestResultById(Long id) {
		return this.testResultRepository.getTestResultById(id);
	}

	@Override
	public Page<TestResult> findTestResult(Pageable pageable, TestResultFindParams findParams) {
		Page<TestResult> pageResult = this.testResultRepository.findAll(new Specification<TestResult>() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TestResult> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (findParams != null) {
					if (findParams.getCode() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("code")),
								"%" + findParams.getCode().trim().toLowerCase() + "%")));
					}
					if(findParams.getAccountId() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("clinicWorking").get("account").get("id"),findParams.getAccountId())));
					}
					if(findParams.getCustomerId() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("clinicWorking").get("customer").get("id"),findParams.getCustomerId())));
					}
					if(findParams.getIsCalled() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isCalled"),findParams.getIsCalled())));
					}
					if(findParams.isFindCustomerReturning() == true) {
						Date toDay = new Date();
						toDay.setHours(0);
						toDay.setMinutes(0);
						toDay.setSeconds(0);
						Date threeMoreDay = new Date();
						threeMoreDay.setDate(toDay.getDate()+3);
						threeMoreDay.setHours(23);
						threeMoreDay.setMinutes(59);
						threeMoreDay.setSeconds(59);
						toDay.setDate(toDay.getDate()-1);
						predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("timeReturn"), toDay)));
						predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("timeReturn"), threeMoreDay)));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}

	@Override
	public void updateIsCalledCustomerReturn(Long id, Long isCalled) {
		this.testResultRepository.updateIsCalledCustomerReturn(id, isCalled);
	}

}
