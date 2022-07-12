package com.medical.examination.service.impl;

import java.util.ArrayList;
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
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}

}
