package com.medical.examination.controller;

import com.medical.examination.utils.AccountDetail;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController {
	
	public AccountDetail account;
	
	//@PostConstruct
	public void init() {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpServletRequest request = (HttpServletRequest) attr.getRequest();
			HttpSession session = request.getSession();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
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
		return viewName;
	}
}
