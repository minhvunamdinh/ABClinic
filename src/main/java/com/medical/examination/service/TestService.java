package com.medical.examination.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.medical.examination.entity.Test;
import com.medical.examination.findparams.TestFindParams;


@Validated
@Transactional
public interface TestService {
	Test saveTest(Test test);
	void deleteTest(Long id);
	Test getTestById(Long id);
	Test getTestByTestName(String testName);
	Test findByTestName(String testName);
	Page<Test> findTest(Pageable pageable, TestFindParams findParams);
	void updateTestStatus(Long id, Long status);
	List<Test> saveAllTest(List<Test> lstTest);
}
