package com.medical.examination.service;

import com.medical.examination.entity.TestType;
import com.medical.examination.findparams.TestTypeFindParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TestTypeService {
	TestType saveTestType(TestType testType);
	void deleteTestType(Long id);
	TestType getTestTypeById(Long id);
	Page<TestType> findTestType(Pageable pageable, TestTypeFindParams findParams);
}
