package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medical.examination.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>,
PagingAndSortingRepository<Account, Long>, JpaSpecificationExecutor<Account> {
	Account findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	@Query(value = "SELECT a FROM Account a WHERE a.id = :id")
	Account getAccountById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE Account c SET c.isActive = :status WHERE c.id = :id")
	public void updateAccountStatus(@Param("id") Long id, @Param("status") Long status);
}
