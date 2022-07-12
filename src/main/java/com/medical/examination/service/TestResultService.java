package com.medical.examination.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import com.medical.examination.entity.TestResult;
import com.medical.examination.findparams.TestResultFindParams;

@Validated
public interface TestResultService {
	TestResult saveTestResult(TestResult testResult);
	void deleteTestResult(Long id);
	TestResult getTestResultById(Long id);
	Page<TestResult> findTestResult(Pageable pageable, TestResultFindParams findParams);
}
