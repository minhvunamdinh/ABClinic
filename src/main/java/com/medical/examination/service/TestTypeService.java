package com.medical.examination.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import com.medical.examination.entity.TestType;
import com.medical.examination.findparams.TestTypeFindParams;

@Validated
public interface TestTypeService {
	TestType saveTestType(TestType testType);
	void deleteTestType(Long id);
	TestType getTestTypeById(Long id);
	Page<TestType> findTestType(Pageable pageable, TestTypeFindParams findParams);
	Boolean changeStatusTestType(Long id, String type);
}
