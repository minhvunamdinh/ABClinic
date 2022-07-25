package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medical.examination.entity.Test;

@Repository
public interface TestRepository extends CrudRepository<Test, Long>,
PagingAndSortingRepository<Test, Long>, JpaSpecificationExecutor<Test> {

	@Query(value = "SELECT t FROM Test t WHERE t.id = :id")
	public Test getTestById(@Param("id") Long id);
	
	@Query(value = "SELECT t FROM Test t WHERE t.testName = ':testName'")
	public Test getTestByTestName(@Param("testName") String testName);
	
	public Test findByTestName(String testName);
}
