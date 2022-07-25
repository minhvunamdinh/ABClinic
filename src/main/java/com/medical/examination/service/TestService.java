package com.medical.examination.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import com.medical.examination.entity.Test;
import com.medical.examination.findparams.TestFindParams;

@Validated
public interface TestService {
	Test saveTest(Test test);
	void deleteTest(Long id);
	Test getTestById(Long id);
	Test getTestByTestName(String testName);
	Test findByTestName(String testName);
	Page<Test> findTest(Pageable pageable, TestFindParams findParams);
}
