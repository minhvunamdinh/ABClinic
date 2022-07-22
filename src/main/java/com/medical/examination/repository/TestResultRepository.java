package com.medical.examination.repository;

import com.medical.examination.entity.TestResult;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TestResultRepository extends CrudRepository<TestResult, Long>,
PagingAndSortingRepository<TestResult, Long>, JpaSpecificationExecutor<TestResult> {

	@Query(value = "SELECT t FROM TestResult t WHERE t.id = :id")
	public TestResult getTestResultById(@Param("id") Long id);
}
