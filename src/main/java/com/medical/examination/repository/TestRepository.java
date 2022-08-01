package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.medical.examination.entity.Test;

@Repository
public interface TestRepository extends CrudRepository<Test, Long>,
PagingAndSortingRepository<Test, Long>, JpaSpecificationExecutor<Test> {

	@Query(value = "SELECT t FROM Test t WHERE t.id = :id")
	public Test getTestById(@Param("id") Long id);
	
	@Query(value = "SELECT t FROM Test t WHERE t.testName = ':testName'")
	public Test getTestByTestName(@Param("testName") String testName);
	
	public Test findByTestName(String testName);
	
	@Modifying
	@Query(value = "UPDATE Test t SET t.status = :status WHERE t.id = :id")
	public void updateTestStatus(@Param("id") Long id, @Param("status") Long status);
}
