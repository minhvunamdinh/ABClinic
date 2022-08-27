package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.medical.examination.entity.ClinicWorking;

public interface ClinicWorkingRepository extends CrudRepository<ClinicWorking, Long>,
	PagingAndSortingRepository<ClinicWorking, Long>, JpaSpecificationExecutor<ClinicWorking> {

	@Query(value = "SELECT MAX(no) FROM clinic_working where DATE(created_date) >= DATE(SYSDATE())", nativeQuery = true)
	public Long getMaxNo();
	
	@Query(value = "SELECT c FROM ClinicWorking c WHERE c.id = :id")
	public ClinicWorking getClinickWorkingById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE clinic_working c SET c.account_id = :accountId WHERE c.id = :id", nativeQuery = true)
	void updateAccountIdClinicWorking(@Param("accountId") Long accountId, @Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE clinic_working c SET c.created_by = :createdBy WHERE c.id = :id", nativeQuery = true)
	void updateCreatedByClinicWorking(@Param("createdBy") Long createdBy, @Param("id") Long id);
	
}
