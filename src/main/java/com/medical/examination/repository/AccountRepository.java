package com.medical.examination.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
}
