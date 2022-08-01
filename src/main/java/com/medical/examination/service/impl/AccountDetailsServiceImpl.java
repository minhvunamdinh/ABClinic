package com.medical.examination.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.medical.examination.entity.Account;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.utils.AccountDetail;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) attr.getRequest();
		HttpSession session = request.getSession();
		
		Account account = accountRepository.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}else {
			if(account.getIsActive() == 0) {
				session.setAttribute("MESSAGE", "Tài khoản này đã bị vô hiệu hóa");
				return null;
			}
		}

		session.setAttribute("CURRENT_USER", AccountDetail.build(account));
		return AccountDetail.build(account);
	}

}
