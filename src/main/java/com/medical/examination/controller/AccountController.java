package com.medical.examination.controller;

import com.medical.examination.entity.Account;
import com.medical.examination.findparams.AccountFindParams;
import com.medical.examination.service.AccountService;
import com.medical.examination.utils.Constants;
import com.medical.examination.utils.FwResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@GetMapping("/get/{id}")
	public @ResponseBody FwResponse getAccountById(@PathVariable("id") Long id) {
		FwResponse response = new FwResponse();
		try {
			response.setData(accountService.getAccountById(id));
			response.setResult(Constants.RETURN_SUCCESS, Constants.QUERY_SUCCESS);
		} catch (Exception e) {
			response.setResult(Constants.RETURN_FAIL, Constants.QUERY_FAIL + e.getMessage());
		}
		return response;
	}

	@PostMapping("/save")
	public @ResponseBody FwResponse saveAccount(@RequestBody Account account){
		FwResponse response = new FwResponse();
		try {
		
			response.setData(accountService.saveAccount(account) );
			response.setErrCode("success");
			response.setResult(Constants.RETURN_SUCCESS, Constants.QUERY_SUCCESS);
		} catch (Exception e) {
			response.setResult(Constants.RETURN_FAIL, Constants.QUERY_FAIL + e.getMessage());
		}
		return response;
	}

	@DeleteMapping("/delete/{id}")
	public @ResponseBody FwResponse deleteAccount(@PathVariable("id") Long id) {
		FwResponse response = new FwResponse();
		try {
			accountService.deleteAccount(id);
			response.setResult(Constants.RETURN_SUCCESS, Constants.QUERY_SUCCESS);
		} catch (Exception e) {
			response.setResult(Constants.RETURN_FAIL, Constants.QUERY_FAIL + e.getMessage());
		}
		return response;
	}

	@GetMapping("/find")
	public @ResponseBody FwResponse findAccount(Pageable pageable, AccountFindParams accountFindParams) {
		FwResponse response = new FwResponse();
		try {
			response.setData(accountService.findAccount(pageable, accountFindParams));
			response.setResult(Constants.RETURN_SUCCESS, Constants.QUERY_SUCCESS);
		} catch (Exception e) {
			response.setResult(Constants.RETURN_FAIL, Constants.QUERY_FAIL + e.getMessage());
		}
		return response;
	}
}
