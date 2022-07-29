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
import com.medical.examination.entity.TestType;
import com.medical.examination.findparams.TestFindParams;
import com.medical.examination.repository.TestRepository;
import com.medical.examination.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	TestRepository testRepository;

	@Override
	public Test saveTest(Test test) {
		return this.testRepository.save(test);
	}

	@Override
	public void deleteTest(Long id) {
		this.testRepository.deleteById(id);
	}

	@Override
	public Test getTestById(Long id) {
		return this.testRepository.getTestById(id);
	}

	@Override
	public Page<Test> findTest(Pageable pageable, TestFindParams findParams) {
		Page<Test> pageResult = this.testRepository.findAll(new Specification<Test>() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Test> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (findParams != null) {
					if (findParams.getTestName() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("testName")),
								"%" + findParams.getTestName().trim().toLowerCase() + "%")));
					}
					if (findParams.getTestTypeId() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("testType").get("id"), findParams.getTestTypeId())));
					}
					if(findParams.getStatus() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), findParams.getStatus())));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}

	@Override
	public Test getTestByTestName(String testName) {
		return this.getTestByTestName(testName);
	}

	@Override
	public Test findByTestName(String testName) {
		return this.testRepository.findByTestName(testName);
	}

	@Override
	public void updateTestStatus(Long id, Long status) {
		this.testRepository.updateTestStatus(id, status);
	}

}
