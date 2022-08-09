package com.medical.examination.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.medical.examination.entity.TestResult;
import com.medical.examination.findparams.TestResultFindParams;
import com.medical.examination.service.TestResultService;
import com.medical.examination.utils.AccountDetail;

public class BaseController {
	
	public AccountDetail account;
	
	@Autowired
	TestResultService testResultService;
	
	public String createView(Model model, String viewName) {
		//Check user login
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) attr.getRequest();
		HttpSession session = request.getSession();
		account = (AccountDetail) session.getAttribute("CURRENT_USER");
		if(account != null) {
        	model.addAttribute("id", account.getId());
        	model.addAttribute("currentUser", account);
        	model.addAttribute("fullname", account.getFullname());
        }
		
		TestResultFindParams testResultFindParams = new TestResultFindParams();
		testResultFindParams.setFindCustomerReturning(true); //Tìm bệnh nhân hẹn khám trong 3 ngày
		Page<TestResult> testResultData = this.testResultService.findTestResult(PageRequest.of(0, 1000), testResultFindParams);
		if(testResultData != null) {
			model.addAttribute("countCustomerReturn", testResultData.getTotalElements());
		}
		
		return viewName;
	}
}
