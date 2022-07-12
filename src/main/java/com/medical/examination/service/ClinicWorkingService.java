package com.medical.examination.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import com.medical.examination.entity.ClinicWorking;
import com.medical.examination.findparams.ClinicWorkingFindParams;

@Validated
public interface ClinicWorkingService {
	ClinicWorking saveClinicWorking(ClinicWorking clinicWorking);
	void deleteClinicWorking(Long id);
	ClinicWorking getClinicWorkingById(Long id);
	Page<ClinicWorking> findClinicWorking(Pageable pageable, ClinicWorkingFindParams findParams);
}
