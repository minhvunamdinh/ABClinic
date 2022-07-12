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

import com.medical.examination.entity.Customer;
import com.medical.examination.entity.TestType;
import com.medical.examination.findparams.TestTypeFindParams;
import com.medical.examination.repository.TestTypeRepository;
import com.medical.examination.service.TestTypeService;

@Service
public class TestTypeServiceImpl implements TestTypeService {
	
	@Autowired
	TestTypeRepository testTypeRepository;

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
		return this.getTestTypeById(id);
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
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}

}
