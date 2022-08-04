package com.medical.examination.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.medical.examination.entity.ClinicWorking;
import com.medical.examination.findparams.ClinicWorkingFindParams;
import com.medical.examination.repository.ClinicWorkingRepository;
import com.medical.examination.service.ClinicWorkingService;

@Service
public class ClinicWorkingServiceImpl implements ClinicWorkingService {
	
	@Autowired
	ClinicWorkingRepository clinicWorkingRepository;

	@Override
	public ClinicWorking saveClinicWorking(ClinicWorking clinicWorking) {
		Long maxNo = clinicWorkingRepository.getMaxNo();
		//Lay max gia tri so thu tu kham suc khoe de insert
		if(maxNo!=null) {
			clinicWorking.setNo(maxNo+1);
		}else {
			clinicWorking.setNo(1L);
		}
		clinicWorking.setCreatedDate(new Date());
		clinicWorking.setStatus(0L);
		return clinicWorkingRepository.save(clinicWorking);
	}

	@Override
	public void deleteClinicWorking(Long id) {
		this.clinicWorkingRepository.deleteById(id);
	}

	@Override
	public ClinicWorking getClinicWorkingById(Long id) {
		return this.clinicWorkingRepository.getClinickWorkingById(id);
	}

	@Override
	public Page<ClinicWorking> findClinicWorking(Pageable pageable, ClinicWorkingFindParams findParams) {
		Page<ClinicWorking> pageResult = this.clinicWorkingRepository.findAll(new Specification<ClinicWorking>() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ClinicWorking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (findParams != null) {
					if (findParams.getAccountId() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("account").get("id"), findParams.getAccountId())));
					}
					if (findParams.getCustomerId() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("customer").get("id"), findParams.getCustomerId())));
					}
					if (findParams.getStatus() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), findParams.getStatus())));
					}
				}
				Date date = new Date();
				date.setDate(new Date().getDate() - 1);
				date.setHours(0);
				date.setMinutes(0);
				date.setSeconds(0);
				predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), date))); //chi hien thi ban ghi trong ngay hom nay
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return pageResult;
	}

	@Override
	public ClinicWorking updateClinicWorking(ClinicWorking clinicWorking) {
		return this.clinicWorkingRepository.save(clinicWorking);
	}

	@Override
	public void updateAccountIdClinicWorking(Long accountId, Long id) {
		this.clinicWorkingRepository.updateAccountIdClinicWorking(accountId, id);
	}

}
