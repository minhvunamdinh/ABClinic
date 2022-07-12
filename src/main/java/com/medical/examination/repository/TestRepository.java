package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medical.examination.entity.Test;

public interface TestRepository extends CrudRepository<Test, Long>,
PagingAndSortingRepository<Test, Long>, JpaSpecificationExecutor<Test> {

	@Query(value = "SELECT t FROM Test t WHERE t.id = :id")
	public Test getTestById(@Param("id") Long id);
}
