package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medical.examination.entity.TestResult;

public interface TestResultRepository extends CrudRepository<TestResult, Long>,
PagingAndSortingRepository<TestResult, Long>, JpaSpecificationExecutor<TestResult> {

	@Query(value = "SELECT t FROM TestResult t WHERE t.id = :id")
	public TestResult getTestResultById(@Param("id") Long id);
	
	@Query(value = "UPDATE TestResult t SET t.isCalled = :isCalled WHERE t.id = :id")
	@Modifying
	public void updateIsCalledCustomerReturn(@Param("id") Long id , @Param("isCalled") Long isCalled);
}
