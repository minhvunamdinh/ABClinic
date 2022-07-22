package com.medical.examination.repository;

import com.medical.examination.entity.TestType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TestTypeRepository extends CrudRepository<TestType, Long>,
PagingAndSortingRepository<TestType, Long>, JpaSpecificationExecutor<TestType> {

	@Query(value = "SELECT t FROM TestType t WHERE t.id = :id")
	public TestType getTestTypeById(@Param("id") Long id);
}
