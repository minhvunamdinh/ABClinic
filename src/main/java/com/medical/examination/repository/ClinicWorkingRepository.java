package com.medical.examination.repository;

import com.medical.examination.entity.ClinicWorking;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ClinicWorkingRepository extends CrudRepository<ClinicWorking, Long>,
	PagingAndSortingRepository<ClinicWorking, Long>, JpaSpecificationExecutor<ClinicWorking> {

	@Query(value = "SELECT MAX(no) FROM clinic_working where DATE(created_date) >= DATE(SYSDATE())", nativeQuery = true)
	public Long getMaxNo();
	
	@Query(value = "SELECT c FROM ClinicWorking c WHERE c.id = :id")
	public ClinicWorking getClinickWorkingById(@Param("id") Long id);
	
}
