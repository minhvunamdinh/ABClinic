package com.medical.examination.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.medical.examination.entity.Customer;
import com.medical.examination.entity.Test;
import com.medical.examination.entity.TestType;
import com.medical.examination.findparams.TestTypeFindParams;
import com.medical.examination.repository.TestRepository;
import com.medical.examination.repository.TestTypeRepository;
import com.medical.examination.service.TestTypeService;

@Service
public class TestTypeServiceImpl implements TestTypeService {
	
	@Autowired
	TestTypeRepository testTypeRepository;
	
	@Autowired
	TestRepository testRepository;

	@Override
	public TestType saveTestType(TestType testType) {
		return this.testTypeRepository.save(testType);
	}

	@Override
	public void deleteTestType(Long id) {
		this.testTypeRepository.deleteById(id);
	}

	@Override
	public TestType getTestTypeById(Long id) {
		return testTypeRepository.getTestTypeById(id);
	}

	@Override
	public Page<TestType> findTestType(Pageable pageable, TestTypeFindParams findParams) {
		Page<TestType> pageResult = this.testTypeRepository.findAll(new Specification<TestType>() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TestType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (findParams != null) {
					if (findParams.getTypeName() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("typeName")),
								"%" + findParams.getTypeName().trim().toLowerCase() + "%")));
					}
					if (findParams.getStatus() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), findParams.getStatus())));
//						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lstTest").get("status"), Long.valueOf(findParams.getStatus()))));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public Boolean changeStatusTestType(Long id, String type) {
		TestType testType = this.getTestTypeById(id);
		List<Test> lstTest = testType.getLstTest();
		if(type.equals("active")) {
			testType.setStatus(1);
			for(Test test: lstTest) {
				test.setStatus(1L);
			}
		}else if(type.equals("deactive")) {
			testType.setStatus(0);
			for(Test test: lstTest) {
				test.setStatus(0L);
			}
		}
		this.testRepository.saveAll(lstTest);
		this.saveTestType(testType);
		return true;
	}

}
