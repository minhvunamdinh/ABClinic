package com.medical.examination.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.examination.entity.Account;
import com.medical.examination.entity.ClinicWorking;
import com.medical.examination.entity.Customer;
import com.medical.examination.entity.Invoice;
import com.medical.examination.entity.Test;
import com.medical.examination.entity.TestDTO;
import com.medical.examination.entity.TestResult;
import com.medical.examination.entity.TestType;
import com.medical.examination.findparams.AccountFindParams;
import com.medical.examination.findparams.ClinicWorkingFindParams;
import com.medical.examination.findparams.CustomerFindParams;
import com.medical.examination.findparams.InvoiceFindParams;
import com.medical.examination.findparams.TestFindParams;
import com.medical.examination.findparams.TestResultFindParams;
import com.medical.examination.findparams.TestTypeFindParams;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.repository.ClinicWorkingRepository;
import com.medical.examination.request.AccountRequest;
import com.medical.examination.request.ChangePasswordRequest;
import com.medical.examination.request.MedicalExaminationRequest;
import com.medical.examination.request.ResultTestInvoiceRequest;
import com.medical.examination.request.UpdateAccountRequest;
import com.medical.examination.service.AccountService;
import com.medical.examination.service.ClinicWorkingService;
import com.medical.examination.service.CustomerService;
import com.medical.examination.service.InvoiceService;
import com.medical.examination.service.TestResultService;
import com.medical.examination.service.TestService;
import com.medical.examination.service.TestTypeService;
import com.medical.examination.utils.AccountDetail;
import com.medical.examination.utils.Constants;
import com.medical.examination.utils.MessageResponse;

@Controller
public class RoutingController extends BaseController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ClinicWorkingService clinicWorkingService;
	
	@Autowired
	TestTypeService testTypeService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	TestResultService testResultService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping({ "", "/home" })
	public String viewHomePage(Model model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) attr.getRequest();
		HttpSession session = request.getSession();
		account = (AccountDetail) session.getAttribute("CURRENT_USER");
		if(account.getRole() == 1) {
			try {
				model.addAttribute("title", "home");
				
				session.removeAttribute("MESSAGE");
				
				AccountFindParams accountFindParams = new AccountFindParams();
				accountFindParams.setIsActive(1L);//tk kich hoat
				accountFindParams.setRole(2L);//quyen basi
				List<Account> lstBacSi = this.accountService.findAccount(PageRequest.of(0, 1000), accountFindParams).getContent();
				List<String> bacSiArr = new ArrayList<String>();
				if(lstBacSi != null) {
					lstBacSi.forEach(item -> {
						bacSiArr.add(item.getFullname());
					});
				}
				
				Pageable pageAble = PageRequest.of(0, 1000, Sort.by(Sort.Order.desc("createdDate")));
				InvoiceFindParams invoiceFindParams = new InvoiceFindParams();
				int currentMonth = new Date().getMonth() + 1;
				model.addAttribute("currentMonth", currentMonth);
				if(invoiceFindParams.getMonth() == null) {
					invoiceFindParams.setMonth((long) currentMonth);
				}
				Page<Invoice> invoiceData = this.invoiceService.findInvoice(pageAble, invoiceFindParams);
				List<Double> lstDoctorIncome = new ArrayList<Double>();
				List<Double> lstDoctorInterest = new ArrayList<Double>();
				List<Double> lstDoctorCountInvoice = new ArrayList<Double>();
				Double totalSellPrice = 0D;
				Double totalInterests = 0D;
				
				for(String bacSi: bacSiArr) {
					invoiceFindParams.setAccountName(bacSi);
					List<Invoice> lstInv = this.invoiceService.findInvoice(pageAble, invoiceFindParams).getContent();
					if(lstInv != null) {
						Double totalIncome = 0D;
						Double totalInterest = 0D;
						for(Invoice i: lstInv) {
							totalIncome += i.getTotalSellPrice();
							totalInterest += (i.getTotalSellPrice() - i.getTotalCostPrice());
						}
						totalSellPrice += totalIncome;
						totalInterests += totalInterest;
						lstDoctorIncome.add(totalIncome);
						lstDoctorInterest.add(totalInterest);
						lstDoctorCountInvoice.add((double) lstInv.size());
					}
				}
				
				CustomerFindParams cusFindParams = new CustomerFindParams();
				cusFindParams.setFindNewCustomer(true);
				Page<Customer> cusData = this.customerService.findCustomer(pageAble, cusFindParams);
				model.addAttribute("totalSellPrice", totalSellPrice);
				model.addAttribute("totalInterests", totalInterests);
				model.addAttribute("totalInvoice", invoiceData.getTotalElements());
				model.addAttribute("countNewCustomer", cusData.getTotalElements());
				model.addAttribute("bacSiArr", bacSiArr);
				model.addAttribute("lstDoctorIncome", lstDoctorIncome);
				model.addAttribute("lstDoctorInterest", lstDoctorInterest);
				model.addAttribute("lstDoctorCountInvoice", lstDoctorCountInvoice);
				return createView(model, "index");
			} catch (Exception e) {
				e.printStackTrace();
				return "error.html";
			}
		}else {
			return "redirect:/medical-examination";
		}
			
//		return createView(model, "index");
		
	}
	
	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		model.addAttribute("title", "login");
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) attr.getRequest();
		HttpSession session = request.getSession();
		String message = (String) session.getAttribute("MESSAGE");
		model.addAttribute("message", message);
		return createView(model, "account/login");
	}
	
	@GetMapping("/medical-examination")
	public String viewMedicalExaminationPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") ClinicWorkingFindParams clinicWorkingFindParams) {
		try {
			model.addAttribute("title", "Phiếu khám bệnh");
			
			//Lay thong tin user trong phien lam viec
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpServletRequest request = (HttpServletRequest) attr.getRequest();
			HttpSession session = request.getSession();
			account = (AccountDetail) session.getAttribute("CURRENT_USER");
			if(account.getRole() == 2) {
				clinicWorkingFindParams.setAccountId(account.getId()); //Neu la bac si dang nhap thi chi hien thi cac benh nhan cua minh
			}
			
			Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("id")));
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L); //Tim role = 2 bac si
			accountFindParams.setIsActive(1L); //account can kich hoat
			Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
			Page<Customer> cusData = customerService.findCustomer(PageRequest.of(0, 100), null);
			Page<ClinicWorking> data = clinicWorkingService.findClinicWorking(pageAble, clinicWorkingFindParams);
			if(data != null && accData != null) {
				model.addAttribute("pageSize", data.getSize());
				model.addAttribute("pageIndex", data.getNumber());
				model.addAttribute("totalPages", data.getTotalPages());
				model.addAttribute("totalElements", data.getTotalElements());
				List<ClinicWorking> lstClinicWorking = data.getContent();
				model.addAttribute("lstClinicWorking", lstClinicWorking);
				
				model.addAttribute("lstDoctor", accData.getContent());
				model.addAttribute("lstCustomer", cusData.getContent());
				
				//Lay param fix loi phan trang next page k lay dc filter
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(clinicWorkingFindParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id") && !entry.getKey().contains("createdDate")) {
						queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
			    }
				model.addAttribute("queryParams", queryParams);
			}
			
			return createView(model, "function/medical_examination/medical_examination.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/medical-examination-add")
	public String viewMedicalExaminationAddPage(Model model, @ModelAttribute("findParams") CustomerFindParams customerFindParams, MedicalExaminationRequest medicalExaminationRequest) {
		try {
			model.addAttribute("title", "Tạo phiếu khám bệnh");
			model.addAttribute("findParams", customerFindParams);
			Page<Customer> cusData = customerService.findCustomer(PageRequest.of(0, 100), null);
			model.addAttribute("lstCustomer", cusData.getContent());
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L); //Tim role = 2 bac si
			Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
			model.addAttribute("lstDoctor", accData.getContent());
			
			if(customerFindParams!=null && customerFindParams.getId()!=null) {
				Customer customer = this.customerService.getCustomerById(customerFindParams.getId());
				if(customer!=null) {
					medicalExaminationRequest.setCustomerId(customer.getId());
					medicalExaminationRequest.setFullname(customer.getFullname());
					medicalExaminationRequest.setPhone(customer.getPhone());
					medicalExaminationRequest.setDob(customer.getDob());
					medicalExaminationRequest.setEmail(customer.getEmail());
					medicalExaminationRequest.setCountry(customer.getCountry());
					medicalExaminationRequest.setAddress(customer.getAddress());
					medicalExaminationRequest.setJob(customer.getJob());
					medicalExaminationRequest.setGender(customer.getGender());
					medicalExaminationRequest.setDesc(customer.getDesc());
					model.addAttribute("medicalExaminationRequest", medicalExaminationRequest);
				}
			}

			return createView(model, "function/medical_examination/medical_examination_add.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/medical-examination-edit/{id}")
	public String viewMedicalExaminationEditPage(@PathVariable("id") Long id,Model model, MedicalExaminationRequest medicalExaminationRequest) {
		try {
			model.addAttribute("title", "Cập nhật phiếu khám bệnh");
			model.addAttribute("clinicWorkingId", id);
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L); //Tim role = 2 bac si
			Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
			model.addAttribute("lstDoctor", accData.getContent());
			
			ClinicWorking clinicWorking = this.clinicWorkingService.getClinicWorkingById(id);
			if(clinicWorking != null) {
				medicalExaminationRequest.setAccountId(clinicWorking.getAccount().getId());
				medicalExaminationRequest.setAddress(clinicWorking.getCustomer().getAddress());
				medicalExaminationRequest.setCountry(clinicWorking.getCustomer().getCountry());
				medicalExaminationRequest.setCustomerId(clinicWorking.getCustomer().getId());
				medicalExaminationRequest.setDesc(clinicWorking.getCustomer().getDesc());
				medicalExaminationRequest.setDob(clinicWorking.getCustomer().getDob());
				medicalExaminationRequest.setEmail(clinicWorking.getCustomer().getEmail());
				medicalExaminationRequest.setFullname(clinicWorking.getCustomer().getFullname());
				medicalExaminationRequest.setGender(clinicWorking.getCustomer().getGender());
				medicalExaminationRequest.setJob(clinicWorking.getCustomer().getJob());
				medicalExaminationRequest.setPhone(clinicWorking.getCustomer().getPhone());
			}

			return createView(model, "function/medical_examination/medical_examination_edit.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@PostMapping("/medical-examination-edit/{id}")
	public String addMedicalExaminationAddPage(@PathVariable("id") Long id, @Valid @ModelAttribute(value="medicalExaminationRequest") MedicalExaminationRequest medicalExaminationRequest, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			model.addAttribute("title", "Cập nhật phiếu khám bệnh");
			if (result.hasErrors()) {
				model.addAttribute("error", "Cập nhật thất bại!");
				model.addAttribute("clinicWorkingId", id);
				AccountFindParams accountFindParams = new AccountFindParams();
				accountFindParams.setRole(2L); //Tim role = 2 bac si
				Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
				model.addAttribute("lstDoctor", accData.getContent());
				return createView(model, "function/medical_examination/medical_examination_edit.html");
	        }
			
			Customer customer = this.customerService.getCustomerById(medicalExaminationRequest.getCustomerId());
			
			customer.setAddress(medicalExaminationRequest.getAddress());
			customer.setCountry(medicalExaminationRequest.getCountry());
			customer.setDesc(medicalExaminationRequest.getDesc());
			customer.setDob(medicalExaminationRequest.getDob());
			customer.setEmail(medicalExaminationRequest.getEmail());
			customer.setFullname(medicalExaminationRequest.getFullname());
			customer.setGender(medicalExaminationRequest.getGender());
			customer.setJob(medicalExaminationRequest.getJob());
			customer.setPhone(medicalExaminationRequest.getPhone());
			this.customerService.saveCustomer(customer);
			//insert clinic_woring
			this.clinicWorkingService.updateAccountIdClinicWorking(medicalExaminationRequest.getAccountId(), id);
			redirAttrs.addFlashAttribute("success", "Cập nhật phiếu khám thành công!");
			return "redirect:/medical-examination";
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/medical-examination-add")
	public String addMedicalExaminationAddPage(@Valid @ModelAttribute(value="medicalExaminationRequest") MedicalExaminationRequest medicalExaminationRequest, BindingResult result, Model model, RedirectAttributes redirAttrs,@ModelAttribute("findParams") CustomerFindParams customerFindParams) {
		try {
			model.addAttribute("title", "Tạo khám bệnh");
			if (result.hasErrors()) {
				model.addAttribute("error", "Thêm mới thất bại!");
				Page<Customer> cusData = customerService.findCustomer(PageRequest.of(0, 100), null);
				model.addAttribute("lstCustomer", cusData.getContent());
				AccountFindParams accountFindParams = new AccountFindParams();
				accountFindParams.setRole(2L); //Tim role = 2 bac si
				Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
				model.addAttribute("lstDoctor", accData.getContent());
				
				return createView(model, "function/medical_examination/medical_examination_add.html");
	        }else {
	        	Customer customer = new Customer();
	    		Customer newCustomerCreated = new Customer();
	    		ClinicWorking clinicWorking = new ClinicWorking();
	    		//Check benh nhan ton` tai hay chua neu co thi lay tu db ra, khong thi tao moi
	    		if(medicalExaminationRequest.getCustomerId() != null) {
	    			Long checkCustomerExist = this.customerService.getCountCustomerById(medicalExaminationRequest.getCustomerId());
	    			if(checkCustomerExist != null && checkCustomerExist > 0) {
	    				//Check bệnh nhân đã khám trong ngày hôm nay chưa?
	    				Long checkCustomerIsExaminating = this.customerService.checkCustomerExaminating(medicalExaminationRequest.getCustomerId());
	    				if(checkCustomerIsExaminating > 0) {
	    					model.addAttribute("error", "Bệnh nhân đã được tạo phiếu khám!");
	    					
	    					Page<Customer> cusData = customerService.findCustomer(PageRequest.of(0, 100), null);
	    					model.addAttribute("lstCustomer", cusData.getContent());
	    					AccountFindParams accountFindParams = new AccountFindParams();
	    					accountFindParams.setRole(2L); //Tim role = 2 bac si
	    					Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
	    					model.addAttribute("lstDoctor", accData.getContent());
	    					return createView(model, "function/medical_examination/medical_examination_add.html");
	    				}else {
	    					customer = this.customerService.getCustomerById(medicalExaminationRequest.getCustomerId());
	        				clinicWorking.setCustomer(customer);
	    				}
	    				
	    			}
	    		}else {
	    			//Tao customer truoc de lay id insert vao clinic_working
	    			customer.setFullname(medicalExaminationRequest.getFullname());
	    			customer.setPhone(medicalExaminationRequest.getPhone());
	    			customer.setDob(medicalExaminationRequest.getDob());
	    			customer.setEmail(medicalExaminationRequest.getEmail());
	    			customer.setCountry(medicalExaminationRequest.getCountry());
	    			customer.setAddress(medicalExaminationRequest.getAddress());
	    			customer.setJob(medicalExaminationRequest.getJob());
	    			customer.setGender(medicalExaminationRequest.getGender());
	    			customer.setDesc(medicalExaminationRequest.getDesc());
	    			newCustomerCreated = this.customerService.saveCustomer(customer);
	    			clinicWorking.setCustomer(newCustomerCreated);
	    		}
	    		
	    		//Lay accountId tu request de them vao clinic_woring
	    		Account account = this.accountService.getAccountById(medicalExaminationRequest.getAccountId());
	    		//insert clinic_woring
	    		clinicWorking.setAccount(account);
	    		this.clinicWorkingService.saveClinicWorking(clinicWorking);
	    		redirAttrs.addFlashAttribute("success", "Tạo phiếu khám thành công!");
	    		return "redirect:/medical-examination";
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@GetMapping("/medical-examination/delete/{id}")
	public String deleteClinicWorking(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
		try {
			this.clinicWorkingService.deleteClinicWorking(id);
			redirAttrs.addFlashAttribute("success", "Xóa thành công!");
			return "redirect:/medical-examination";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Xóa thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/medical-examination/{id}")
	public String viewMedicalExaminationDetailPage(@PathVariable("id") Long id,Model model) {
		try {
			model.addAttribute("title", "Chi tiết phiếu khám bệnh");
			ClinicWorking clinicWorking = this.clinicWorkingService.getClinicWorkingById(id);
			if(clinicWorking!=null && clinicWorking.getId() != null) {
				model.addAttribute("clinicWorking", clinicWorking);
			}
			return createView(model, "function/medical_examination/medical_examination_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/medical-examination-working/{clinicWorkingId}/{type}")
	public String viewMedicalExaminationWorkingPage(@PathVariable("clinicWorkingId") Long clinicWorkingId, Model model, @PathVariable("type") String type, ResultTestInvoiceRequest resultTestInvoiceRequest) {
		try {
			model.addAttribute("title", "Phiếu khám bệnh");
			model.addAttribute("clinicWorkingId", clinicWorkingId);
			model.addAttribute("type", type);
			ClinicWorking clinicWorking = this.clinicWorkingService.getClinicWorkingById(clinicWorkingId);
			if(type.contains("add")) {
				clinicWorking.setStatus(1L); //set trang thai 1 : dang kham
				this.clinicWorkingService.updateClinicWorking(clinicWorking); //Cap nhat lai ban ghi trang thai dang kham
				
				//Lay data xet nghiem
//				List<Test> lstTestMau = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(1L, 1L)).getContent();
//				List<Test> lstTestHoaSinh = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(2L, 1L)).getContent();
//				List<Test> lstTestViSinh = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(3L, 1L)).getContent();
//				List<Test> lstTestPhan = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(4L, 1L)).getContent();
//				List<Test> lstTestNuocTieu = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(5L, 1L)).getContent();
//				model.addAttribute("lstTestMau", lstTestMau);
//				model.addAttribute("lstTestHoaSinh", lstTestHoaSinh);
//				model.addAttribute("lstTestViSinh", lstTestViSinh);
//				model.addAttribute("lstTestPhan", lstTestPhan);
//				model.addAttribute("lstTestNuocTieu", lstTestNuocTieu);
				TestTypeFindParams testTypeFindParams = new TestTypeFindParams();
				testTypeFindParams.setStatus(1);
				List<TestType> lstTestType = this.testTypeService.findTestType(PageRequest.of(0, 1000), testTypeFindParams).getContent();
				model.addAttribute("lstTestType", lstTestType);
			}else {
				
			}
			Integer countHistoryCustomerTestResult = this.testResultService.countHistoryTestResultCustomer(clinicWorking.getCustomer().getId());
			if(countHistoryCustomerTestResult != null && countHistoryCustomerTestResult > 0) {
				System.out.println("count: " + countHistoryCustomerTestResult);
				model.addAttribute("countHistoryCustomerTestResult", countHistoryCustomerTestResult);
			}
			if(clinicWorking != null) {
				model.addAttribute("clinicWorking", clinicWorking);
				model.addAttribute("customerId", clinicWorking.getCustomer().getId());
			}
			return createView(model, "function/medical_examination/medical_examination_working.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@PostMapping("/medical-examination-working/{clinicWorkingId}/{type}")
	public String postMedicalExaminationWorkingPage(@PathVariable("clinicWorkingId") Long clinicWorkingId, @PathVariable("type") String type,Model model, @Valid @ModelAttribute("resultTestInvoiceRequest") ResultTestInvoiceRequest resultTestInvoiceRequest, BindingResult result, RedirectAttributes redirAttrs) {
		try {
			model.addAttribute("title", "Khám bệnh");
//			if (result.hasErrors()) {
//				model.addAttribute("error", "Thêm mới thất bại!");
//	            return createView(model, "function/medical_examination/medical_examination_working.html");
//	        }
			TestResult testResult = new TestResult();
			Invoice invoice = new Invoice();

			ClinicWorking clinicWorking = this.clinicWorkingService.getClinicWorkingById(clinicWorkingId);
			
			if(type.contains("add")) {
				if(resultTestInvoiceRequest.getDiagnosticResult() == null || resultTestInvoiceRequest.getDiagnosticResult().equalsIgnoreCase("")) {
					redirAttrs.addFlashAttribute("error", "Bạn chưa nhập kết quả chuẩn đoán");
					return "redirect:/medical-examination-working/"+clinicWorkingId+"/add";
				}
//				if(resultTestInvoiceRequest.getLstTest() == null || resultTestInvoiceRequest.getLstTest().equalsIgnoreCase("")) {
//					redirAttrs.addFlashAttribute("error", "Bạn chưa chỉ định xét nghiệm nào");
//					return "redirect:/medical-examination-working/"+clinicWorkingId+"/add";
//				}
				clinicWorking.setStatus(2L); //trang thai 2: cho ket qua xet nghiem
				//tao ket qua tet
				testResult.setClinicWorking(clinicWorking);
				testResult.setCreatedDate(new Date());
				testResult.setCode(resultTestInvoiceRequest.getCode());
				testResult.setDiagnosticResult(resultTestInvoiceRequest.getDiagnosticResult());
				testResult.setLstTest(resultTestInvoiceRequest.getLstTest());
				testResult.setLstTestId(resultTestInvoiceRequest.getLstTestId());
				testResult.setExaminationFee(resultTestInvoiceRequest.getTotalSellPrice());
				testResult.setTotalCostPrice(resultTestInvoiceRequest.getTotalCostPrice());
				testResult.setTotalSellPrice(resultTestInvoiceRequest.getTotalSellPrice());
				testResult.setStatus(0L);
			}else {
				if(resultTestInvoiceRequest.getTimeReturn() != null) {
					if((resultTestInvoiceRequest.getTimeReturn()).compareTo(new Date()) < 0) {
						redirAttrs.addFlashAttribute("error", "Ngày hẹn tái khám phải lớn hơn ngày hiện tại");
						return "redirect:/medical-examination-working/"+clinicWorkingId+"/edit";
					}
				}else {
					redirAttrs.addFlashAttribute("error", "Bạn chưa nhập ngày hẹn tái khám");
					return "redirect:/medical-examination-working/"+clinicWorkingId+"/edit";
				}
				if(resultTestInvoiceRequest.getTestResult() == null || resultTestInvoiceRequest.getTestResult().equalsIgnoreCase("")) {
					redirAttrs.addFlashAttribute("error", "Bạn chưa nhập kết quả xét nghiệm");
					return "redirect:/medical-examination-working/"+clinicWorkingId+"/edit";
				}
				if(resultTestInvoiceRequest.getConclusion() == null || resultTestInvoiceRequest.getConclusion().equalsIgnoreCase("")) {
					redirAttrs.addFlashAttribute("error", "Bạn chưa nhập kết luận");
					return "redirect:/medical-examination-working/"+clinicWorkingId+"/edit";
				}
				if(resultTestInvoiceRequest.getPrescription() == null || resultTestInvoiceRequest.getPrescription().equalsIgnoreCase("")) {
					redirAttrs.addFlashAttribute("error", "Bạn chưa nhập chỉ định thuốc");
					return "redirect:/medical-examination-working/"+clinicWorkingId+"/edit";
				}
				testResult = clinicWorking.getLstTestResult().get(0);
				String[] lstTest = testResult.getLstTest().split(",");
				String lstCostPrice = "";
				String lstSellPrice = "";
				if(lstTest.length > 1) {
					for(String item: lstTest) {
						Test test = this.testService.findByTestName(item);
						lstCostPrice += test.getCostPrice()!=null ? test.getCostPrice().toString() + "," : 0 + ",";
						lstSellPrice += test.getSellPrice()!=null ? test.getSellPrice().toString() + "," : 0 + ",";
					}
					lstCostPrice = lstCostPrice.substring(0, lstCostPrice.length()-1); //Xóa dấu "," ở cuối
					lstSellPrice = lstSellPrice.substring(0, lstSellPrice.length()-1);
				}
				clinicWorking.setStatus(3L); // trang thai 3 da kham xong
				
				//tao hoa don
				invoice.setClinicWorking(clinicWorking);
				invoice.setCreatedDate(new Date());
				invoice.setCode(resultTestInvoiceRequest.getCode());
				invoice.setLstTest(testResult.getLstTest());
				invoice.setLstTestId(testResult.getLstTestId());
				invoice.setTotalCostPrice(testResult.getTotalCostPrice() != null ? testResult.getTotalCostPrice() + 200000 : 200000);
				invoice.setTotalSellPrice(testResult.getTotalSellPrice() != null ? testResult.getTotalSellPrice() + 200000 : 200000);
				invoice.setTestResult(resultTestInvoiceRequest.getTestResult());
				invoice.setDiagnosticResult(testResult.getDiagnosticResult());
				invoice.setLstCostPrice(lstCostPrice);
				invoice.setLstSellPrice(lstSellPrice);
				invoice.setTimeReturn(resultTestInvoiceRequest.getTimeReturn());
				invoice.setConclusion(resultTestInvoiceRequest.getConclusion());
				invoice.setPrescription(resultTestInvoiceRequest.getPrescription());
				
				testResult.setConclusion(resultTestInvoiceRequest.getConclusion());
				testResult.setPrescription(resultTestInvoiceRequest.getPrescription());
				testResult.setTimeReturn(resultTestInvoiceRequest.getTimeReturn());
				testResult.setTestResult(resultTestInvoiceRequest.getTestResult());
				this.invoiceService.saveInvoice(invoice);
				
			}
			this.clinicWorkingService.updateClinicWorking(clinicWorking);
			this.testResultService.saveTestResult(testResult);
			
			return "redirect:/medical-examination";
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/get-medical-fee")
	public String getMedicalFee(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") TestResultFindParams findParams) {
		try {
			model.addAttribute("title", "Thu tiền khám");
			findParams.setFindCustomerReturning(false);
			Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("id")));
			Page<TestResult> dataTestResult = this.testResultService.findTestResult(pageAble, findParams);
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L);
			Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
			Page<Customer> cusData = customerService.findCustomer(PageRequest.of(0, 100), null);
			if(dataTestResult != null) {
				List<TestResult> lstTestResult = dataTestResult.getContent();
				model.addAttribute("lstTestResult", lstTestResult);
				model.addAttribute("pageSize", dataTestResult.getSize());
				model.addAttribute("pageIndex", dataTestResult.getNumber());
				model.addAttribute("totalPages", dataTestResult.getTotalPages());
				model.addAttribute("totalElements", dataTestResult.getTotalElements());
				model.addAttribute("lstDoctor", accData.getContent());
				model.addAttribute("lstCustomer", cusData.getContent());
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id")) {
						queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
				}
				model.addAttribute("queryParams", queryParams);
			}
			
			return createView(model, "function/medical_fee/get_medical_fee.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/get-medical-fee-detail/{id}")
	public String getMedicalFeeDetail(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("title", "Chi tiết phiếu xét nghiệm");
			TestResult testResult = this.testResultService.getTestResultById(id);
			String[] lstTest = testResult.getLstTest().split(",");
			List<Test> lstTestComplete = new ArrayList<Test>();
			if(lstTest.length > 1) {
				for(String item : lstTest) {
					Test test = this.testService.findByTestName(item.trim());
					lstTestComplete.add(test);
				}
				model.addAttribute("lstTestComplete", lstTestComplete);
			}
			model.addAttribute("testResult", testResult);
			return createView(model, "function/medical_fee/get_medical_fee_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	/**
	 * Xem danh sach lich su kham benh cua benh nhan
	 * @param page
	 * @param model
	 * @param customerId
	 * @return
	 */
	@GetMapping("/customer-test-result-history-list/{customerId}")
	public String viewCustomerTestResultHistoryList(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @PathVariable("customerId") Long customerId) {
		try {
			Customer customer = this.customerService.getCustomerById(customerId);
			model.addAttribute("title", "Lịch sử khám bệnh của bệnh nhân " + customer.getFullname());
			model.addAttribute("customerId", customerId);
			TestResultFindParams findParams = new TestResultFindParams();
			findParams.setCustomerId(customerId);
			findParams.setFindCustomerReturning(false);
			Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("createdDate")));
			Page<TestResult> dataTestResult = this.testResultService.findTestResult(pageAble, findParams);
			if(dataTestResult != null) {
				List<TestResult> lstTestResult = dataTestResult.getContent();
				model.addAttribute("lstTestResult", lstTestResult);
				model.addAttribute("pageSize", dataTestResult.getSize());
				model.addAttribute("pageIndex", dataTestResult.getNumber());
				model.addAttribute("totalPages", dataTestResult.getTotalPages());
				model.addAttribute("totalElements", dataTestResult.getTotalElements());
			}
			
			return createView(model, "function/customer_test_result_history/customer_test_result_history_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/customer-test-result-history-list/{customerId}/detail/{id}")
	public String viewCustomerTestResultHistoryDetail(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("title", "Chi tiết phiếu khám bệnh");
			TestResult testResult = this.testResultService.getTestResultById(id);
			String[] lstTest = testResult.getLstTest().split(",");
			List<Test> lstTestComplete = new ArrayList<Test>();
			if(lstTest.length > 1) {
				for(String item : lstTest) {
					Test test = this.testService.findByTestName(item.trim());
					lstTestComplete.add(test);
				}
				model.addAttribute("lstTestComplete", lstTestComplete);
			}
			
			model.addAttribute("testResult", testResult);
			return createView(model, "function/customer_test_result_history/customer_test_result_history_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@PostMapping("/post-medical-fee-detail/{id}")
	public String confirmMedicalFeeDetail(@PathVariable("id") Long id, Model model) {
		try {
			TestResult testResult = this.testResultService.getTestResultById(id);
			testResult.setStatus(1L);//Xác nhận thanh toán
			this.testResultService.saveTestResult(testResult);
			return "redirect:/get-medical-fee";
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/customer-returning-list")
	public String viewCustomerReturningPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") TestResultFindParams findParams) {
		try {
			model.addAttribute("title", "Danh sách bệnh nhân hẹn khám");
			model.addAttribute("findCustomerReturning", findParams.isFindCustomerReturning());
			
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L); //Tim role = 2 bac si
			accountFindParams.setIsActive(1L); //account can kich hoat
			Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
			Page<Customer> cusData = customerService.findCustomer(PageRequest.of(0, 100), null);
			if(accData != null) model.addAttribute("lstDoctor", accData.getContent());
			if(cusData != null) model.addAttribute("lstCustomer", cusData.getContent());
			
			Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("timeReturn")));
			//findParams.setFindCustomerReturning(true); //Tìm bệnh nhân hẹn khám trong 3 ngày
			
			Page<TestResult> testResultData = this.testResultService.findTestResult(pageAble, findParams);
			
			//TestResultFindParams countFindParam = new TestResultFindParams();
			
			//Page<TestResult> countTestResultData = this.testResultService.findTestResult(pageAble, countFindParam);
			if(testResultData != null) {
				model.addAttribute("pageSize", testResultData.getSize());
				model.addAttribute("pageIndex", testResultData.getNumber());
				model.addAttribute("totalPages", testResultData.getTotalPages());
				model.addAttribute("totalElements", testResultData.getTotalElements());
				List<TestResult> lstTestResult = testResultData.getContent();
				model.addAttribute("lstTestResult", lstTestResult);
				//model.addAttribute("countCustomerReturning", countTestResultData.getTotalElements());
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id")) {
						 queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
				}
				model.addAttribute("queryParams", queryParams);
			}
			
			return createView(model, "function/customer/customer_returning_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/customer-returning-detail/{id}")
	public String viewCustomerReturningDetailPage(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("title", "Chi tiết bệnh nhân hẹn khám");
			TestResult testResult = this.testResultService.getTestResultById(id);
			if(testResult != null) {
				model.addAttribute("testResult", testResult);
			}
			
			return createView(model, "function/customer/customer_returning_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/update-customer-returning/{id}")
	public String viewUpdateCustomerReturningDetailPage(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
		try {
			this.testResultService.updateIsCalledCustomerReturn(id, 1L); //Xác nhận đã liên lạc với bệnh nhân hẹn khám
			redirAttrs.addFlashAttribute("success", "Xác nhận thành công!");
			return "redirect:/customer-returning-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Xác nhận thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/invoice")
	public String viewInvoicePage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") InvoiceFindParams findParams) {
		try {
			model.addAttribute("title", "Hóa đơn");
			Page<Invoice> dataInvoice = this.invoiceService.findInvoice(PageRequest.of(0, 1000, Sort.by(Sort.Order.desc("id"))), findParams);
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L);
			Page<Account> accData = accountService.findAccount(PageRequest.of(0, 100), accountFindParams);
			Page<Customer> cusData = customerService.findCustomer(PageRequest.of(0, 100), null);
			List<Invoice> lstInvoice = dataInvoice.getContent();
			model.addAttribute("lstInvoice", lstInvoice);
			model.addAttribute("pageSize", dataInvoice.getSize());
			model.addAttribute("pageIndex", dataInvoice.getNumber());
			model.addAttribute("totalPages", dataInvoice.getTotalPages());
			model.addAttribute("totalElements", dataInvoice.getTotalElements());
			model.addAttribute("lstDoctor", accData.getContent());
			model.addAttribute("lstCustomer", cusData.getContent());
			ObjectMapper oMapper = new ObjectMapper();
			String queryParams = "";
			Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
			Set<Map.Entry<String, Object>> entrySet = map.entrySet();
			for(Map.Entry<String, Object> entry : entrySet) {
				if(entry.getValue() !=null && !entry.getKey().contains("id")) {
					queryParams += "&" + entry.getKey() + "=" + entry.getValue();
				}
			}
			model.addAttribute("queryParams", queryParams);
			return createView(model, "function/invoice/invoice_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/invoice-detail/{id}")
	public String viewInvoiceDetail(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("title", "Chi tiết Hóa đơn");
			Invoice invoice = this.invoiceService.getInvoiceById(id);
			String[] lstTest = invoice.getLstTest().split(",");
			String[] lstCostPrice = invoice.getLstCostPrice().split(",");
			String[] lstSellPrice = invoice.getLstSellPrice().split(",");
			List<TestDTO> lstTestComplete = new ArrayList<TestDTO>();

			if(lstTest.length > 1) {
				for(int i = 0; i < lstTest.length; i++) {
					TestDTO item = new TestDTO();
					item.setTestName(lstTest[i]);
					item.setCostPrice(Double.valueOf(lstCostPrice[i]));
					item.setSellPrice(Double.valueOf(lstSellPrice[i]));
					lstTestComplete.add(item);
				}
				model.addAttribute("lstTestComplete", lstTestComplete);
			}
			
//			String[] lstTest = invoice.getLstTest().split(",");
//			List<Test> lstTestComplete = new ArrayList<Test>();
//			for(String item : lstTest) {
//				Test test = this.testService.findByTestName(item.trim());
//				lstTestComplete.add(test);
//			}
			model.addAttribute("invoice", invoice);
			return createView(model, "function/invoice/invoice_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	//Customer area
	@GetMapping("/customer-list")
	public String viewCustomerPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") CustomerFindParams findParams) {
		try {
			model.addAttribute("title", "Danh sách bệnh nhân");
			Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("id")));
			Page<Customer> customerData = this.customerService.findCustomer(pageAble, findParams);
			if(customerData != null) {
				model.addAttribute("pageSize", customerData.getSize());
				model.addAttribute("pageIndex", customerData.getNumber());
				model.addAttribute("totalPages", customerData.getTotalPages());
				model.addAttribute("totalElements", customerData.getTotalElements());
				List<Customer> lstCustomer = customerData.getContent();
				model.addAttribute("lstCustomer", lstCustomer);
				
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id")) {
						queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
				}
				model.addAttribute("queryParams", queryParams);
			}
			
			return createView(model, "function/customer/customer_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/customer/create")
	public String viewCustomerDetailCreatePage(Customer customer, Model model) {
		try {
			model.addAttribute("title", "Thêm mới");

			
			model.addAttribute("type", "create");
			return createView(model, "function/customer/customer_create.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/customer/create")
	/**
	 * Submit form create customer
	 * @param customer
	 * @param result
	 * @param model
	 * @return
	 */
	public String createCustomer(@Valid @ModelAttribute(value="customer") Customer customer, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Thêm mới");
				redirAttrs.addFlashAttribute("error", "Thêm mới thất bại!");
	            return createView(model, "function/customer/customer_create.html");
	        }
	        
	        this.customerService.saveCustomer(customer);
	        redirAttrs.addFlashAttribute("success", "Thêm mới thành công!");
	        return "redirect:/customer-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Thêm mới thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/customer/{type}/{id}")
	public String viewCustomerDetailPage(@PathVariable("id") Long id, Customer customer, Model model, @PathVariable("type") String type) {
		try {
			//Lay thong tin benh nhanh qua id
			customer = this.customerService.getCustomerById(id);
			model.addAttribute("customer", customer);
			if(type.contains("update")) {
				model.addAttribute("title", "Cập nhật");
			}else if(type.contains("read")) {
				model.addAttribute("title", "Xem chi tiết");
			}
			
			model.addAttribute("type", type);
			return createView(model, "function/customer/customer_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/customer/update/{id}")
	/**
	 * Submit form update customer
	 * @param id
	 * @param customer
	 * @param result
	 * @param model
	 * @param redirAttrs
	 * @return
	 */
	public String updateCustomer(@PathVariable("id") Long id, @Valid @ModelAttribute(value="customer") Customer customer, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Cập nhật");
				model.addAttribute("type", "update");
				redirAttrs.addFlashAttribute("error", "Cập nhật thất bại!");
	            return createView(model, "function/customer/customer_detail.html");
	        }
	        
	        this.customerService.saveCustomer(customer);
	        redirAttrs.addFlashAttribute("success", "Cập nhật thành công!");
	        return "redirect:/customer-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Cập nhật thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/customer/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
		try {
			this.customerService.deleteCustomer(id);
			redirAttrs.addFlashAttribute("success", "Xóa thành công!");
			return "redirect:/customer-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Xóa thất bại!");
			return "error.html";
		}
	}
	
	//END Customer area
	
	//BOSS area
	@GetMapping("/list-invoice")
	public String viewBossInvoicePage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") InvoiceFindParams findParams) {
		try {
			model.addAttribute("title", "Hóa đơn");
			Pageable pageAble = PageRequest.of(page, 1000, Sort.by(Sort.Order.desc("createdDate")));
			if(findParams.getMonth() == null) {
				int currentMonth = new Date().getMonth() + 1;
				findParams.setMonth((long) currentMonth);
			}
			Page<Invoice> invoiceData = this.invoiceService.findInvoice(pageAble, findParams);
			
			if(invoiceData != null) {
				model.addAttribute("pageSize", invoiceData.getSize());
				model.addAttribute("pageIndex", invoiceData.getNumber());
				model.addAttribute("totalPages", invoiceData.getTotalPages());
				model.addAttribute("totalElements", invoiceData.getTotalElements());
				List<Invoice> lstInvoice = invoiceData.getContent();
				model.addAttribute("lstInvoice", lstInvoice);
				
				//Tinh tong don gia, tong thanh tien, tong tien lai 
				double countTotalCostPrice = 0;
				double countTotalSellPrice = 0;
				double countTotalInterest = 0;
				
				for(Invoice i : lstInvoice) {
					countTotalCostPrice += i.getTotalCostPrice();
					countTotalSellPrice += i.getTotalSellPrice();
					countTotalInterest += (i.getTotalSellPrice() - i.getTotalCostPrice());
				}
				
				if(countTotalCostPrice > 0 && countTotalSellPrice > 0 && countTotalInterest > 0) {
					model.addAttribute("countTotalCostPrice", countTotalCostPrice);
					model.addAttribute("countTotalSellPrice", countTotalSellPrice);
					model.addAttribute("countTotalInterest", countTotalInterest);
				}
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id")) {
						queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
				}
				model.addAttribute("queryParams", queryParams);
			}
			
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L); //Tim bac si
			accountFindParams.setIsActive(1L); //Tai khoan active
			List<Account> lstBacSi = this.accountService.findAccount(PageRequest.of(0, 1000, Sort.by(Sort.Order.desc("id"))), accountFindParams).getContent();
			if(lstBacSi != null) {
				model.addAttribute("lstBacSi", lstBacSi);
			}
			
			return createView(model, "function/boss/invoice_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/list-discount")
	public String viewBossDiscountPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") InvoiceFindParams findParams) {
		try {
			model.addAttribute("title", "Hóa đơn");
			Pageable pageAble = PageRequest.of(page, 1000, Sort.by(Sort.Order.desc("createdDate")));
			if(findParams.getMonth() == null) {
				int currentMonth = new Date().getMonth() + 1;
				findParams.setMonth((long) currentMonth);
			}
			findParams.setIsDiscount(0L);//Tìm hóa đơn chưa chiết khấu
			Page<Invoice> invoiceData = this.invoiceService.findInvoice(pageAble, findParams);
			
			if(invoiceData != null) {
				model.addAttribute("pageSize", invoiceData.getSize());
				model.addAttribute("pageIndex", invoiceData.getNumber());
				model.addAttribute("totalPages", invoiceData.getTotalPages());
				model.addAttribute("totalElements", invoiceData.getTotalElements());
				List<Invoice> lstInvoice = invoiceData.getContent();
				model.addAttribute("lstInvoice", lstInvoice);
				
				//Tinh tong don gia, tong thanh tien, tong tien lai 
				double countTotalCostPrice = 0;
				double countTotalSellPrice = 0;
				double countTotalInterest = 0;
				
				for(Invoice i : lstInvoice) {
					countTotalCostPrice += i.getTotalCostPrice();
					countTotalSellPrice += i.getTotalSellPrice();
					countTotalInterest += (i.getTotalSellPrice() - i.getTotalCostPrice());
				}
				
				if(countTotalCostPrice > 0 && countTotalSellPrice > 0 && countTotalInterest > 0) {
					model.addAttribute("countTotalCostPrice", countTotalCostPrice);
					model.addAttribute("countTotalSellPrice", countTotalSellPrice);
					model.addAttribute("countTotalInterest", countTotalInterest);
				}
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id")) {
						queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
				}
				model.addAttribute("queryParams", queryParams);
			}
			
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L); //Tim bac si
			accountFindParams.setIsActive(1L); //Tai khoan active
			List<Account> lstBacSi = this.accountService.findAccount(PageRequest.of(0, 1000, Sort.by(Sort.Order.desc("id"))), accountFindParams).getContent();
			if(lstBacSi != null) {
				model.addAttribute("lstBacSi", lstBacSi);
			}
			
			return createView(model, "function/boss/discount_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/discount-list-invoice")
	public String discountListInvoice(Model model, RedirectAttributes redirAttrs) {
		try {
			InvoiceFindParams findParams = new InvoiceFindParams();
			int currentMonth = new Date().getMonth() + 1;
			findParams.setMonth((long) currentMonth);
			List<Invoice> lstInvoice = this.invoiceService.findInvoice(PageRequest.of(0, 1000), findParams).getContent();
			if(lstInvoice != null) {
				lstInvoice.forEach(item -> {
					this.invoiceService.updateIsDiscounted(item.getId(), 1L);
				});
			}
			model.addAttribute("success", "Chiết khấu thành công!");
			return "redirect:/list-invoice";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Chiết khấu thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/view-invoice-detail/{id}")
	public String viewBossInvoiceDetail(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("title", "Chi tiết Hóa đơn");
			Invoice invoice = this.invoiceService.getInvoiceById(id);
			String[] lstTest = invoice.getLstTest().split(",");
			String[] lstCostPrice = invoice.getLstCostPrice().split(",");
			String[] lstSellPrice = invoice.getLstSellPrice().split(",");
			List<TestDTO> lstTestComplete = new ArrayList<TestDTO>();

			if(lstTest.length > 1) {
				for(int i = 0; i < lstTest.length; i++) {
					TestDTO item = new TestDTO();
					item.setTestName(lstTest[i]);
					item.setCostPrice(Double.valueOf(lstCostPrice[i]));
					item.setSellPrice(Double.valueOf(lstSellPrice[i]));
					lstTestComplete.add(item);
				}
				model.addAttribute("lstTestComplete", lstTestComplete);
			}
			model.addAttribute("invoice", invoice);
			
			return createView(model, "function/boss/invoice_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/test-type-list")
	public String viewTestTypePage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") TestTypeFindParams findParams) {
		try {
			model.addAttribute("title", "Loại xét nghiệm");
			Pageable pageAble = PageRequest.of(page, 10);
			Page<TestType> testTypeData = this.testTypeService.findTestType(pageAble, findParams);
			
			if(testTypeData != null) {
				model.addAttribute("pageSize", testTypeData.getSize());
				model.addAttribute("pageIndex", testTypeData.getNumber());
				model.addAttribute("totalPages", testTypeData.getTotalPages());
				model.addAttribute("totalElements", testTypeData.getTotalElements());
				List<TestType> lstTestType = testTypeData.getContent();
				model.addAttribute("lstTestType", lstTestType);
				
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id")) {
						queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
				}
				model.addAttribute("queryParams", queryParams);
			}
			
			return createView(model, "function/boss/test/test_type_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/test-type/create")
	public String viewTestTypeDetailCreatePage(TestType testType, Model model) {
		try {
			model.addAttribute("title", "Thêm mới");

			
			model.addAttribute("type", "create");
			return createView(model, "function/boss/test/test_type_create.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/test-type/create")
	public String createTestType(@Valid @ModelAttribute(value="testType") TestType testType, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Thêm mới");
				model.addAttribute("error", "Thêm mới thất bại!");
	            return createView(model, "function/boss/test/test_type_create.html");
	        }
	        
	        this.testTypeService.saveTestType(testType);
	        redirAttrs.addFlashAttribute("success", "Thêm mới thành công!");
	        return "redirect:/test-type-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Thêm mới thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/test-type/{type}/{id}")
	public String viewTestTypeDetailPage(@PathVariable("id") Long id, TestType testType, Model model, @PathVariable("type") String type) {
		try {
			//Lay thong tin benh nhanh qua id
			testType = this.testTypeService.getTestTypeById(id);
			model.addAttribute("testType", testType);
			if(type.contains("update")) {
				model.addAttribute("title", "Cập nhật");
			}else if(type.contains("read")) {
				model.addAttribute("title", "Xem chi tiết");
			}
			
			model.addAttribute("type", type);
			return createView(model, "function/boss/test/test_type_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/test-type/update/{id}")
	public String updateTestType(@PathVariable("id") Long id, @Valid @ModelAttribute(value="testType") TestType testType, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Cập nhật");
				model.addAttribute("error", "Cập nhật thất bại!");
	            return createView(model, "function/boss/test/test_type_detail.html");
	        }
	        
	        this.testTypeService.saveTestType(testType);
	        redirAttrs.addFlashAttribute("success", "Cập nhật thành công!");
	        return "redirect:/test-type-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Cập nhật thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/test-type/delete/{id}")
	public String deleteTestType(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
		try {
			this.testTypeService.deleteTestType(id);
			model.addAttribute("success", "Xóa thành công!");
			return "redirect:/test-type-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Xóa thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/change-status-test-type/{type}/{id}")
	public String changeStatusTestType(@PathVariable("id") Long id, @PathVariable("type") String type, Model model, RedirectAttributes redirAttrs) {
		try {
			if(this.testTypeService.changeStatusTestType(id, type)) {
				model.addAttribute("success", "Xóa thành công!");
			}
			return "redirect:/test-type-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Thao tác thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/test-list")
	public String viewTestPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") TestFindParams findParams) {
		try {
			model.addAttribute("title", "Danh sách xét nghiệm");
			Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("id")));
			Page<Test> testData = this.testService.findTest(pageAble, findParams);
			
			if(testData != null) {
				model.addAttribute("pageSize", testData.getSize());
				model.addAttribute("pageIndex", testData.getNumber());
				model.addAttribute("totalPages", testData.getTotalPages());
				model.addAttribute("totalElements", testData.getTotalElements());
				List<Test> lstTest = testData.getContent();
				model.addAttribute("lstTest", lstTest);
				
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id")) {
						queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
				}
				model.addAttribute("queryParams", queryParams);
			}
			
			List<TestType> lstTestType = this.testTypeService.findTestType(PageRequest.of(0, 1000), null).getContent();
			if(lstTestType != null) {
				model.addAttribute("lstTestType", lstTestType);
			}
			
			return createView(model, "function/boss/test/test_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/test/create")
	public String viewTestCreatePage(Test test, Model model) {
		try {
			model.addAttribute("title", "Thêm mới");
			model.addAttribute("type", "create");
			List<TestType> lstTestType = this.testTypeService.findTestType(PageRequest.of(0, 1000), null).getContent();
			if(lstTestType != null) {
				model.addAttribute("lstTestType", lstTestType);
			}
			return createView(model, "function/boss/test/test_create.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/test/create")
	public String createTest(@Valid @ModelAttribute(value="test") Test test, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Thêm mới");
				model.addAttribute("error", "Thêm mới thất bại!");
				List<TestType> lstTestType = this.testTypeService.findTestType(PageRequest.of(0, 1000), null).getContent();
				if(lstTestType != null) {
					model.addAttribute("lstTestType", lstTestType);
				}
	            return createView(model, "function/boss/test/test_create.html");
	        }
	        
	        this.testService.saveTest(test);
	        redirAttrs.addFlashAttribute("success", "Thêm mới thành công!");
	        return "redirect:/test-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Thêm mới thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/test/{type}/{id}")
	public String viewTestDetailPage(@PathVariable("id") Long id, Test test, Model model, @PathVariable("type") String type) {
		try {
			test = this.testService.getTestById(id);
			model.addAttribute("test", test);
			if(type.contains("update")) {
				model.addAttribute("title", "Cập nhật");
			}else if(type.contains("read")) {
				model.addAttribute("title", "Xem chi tiết");
			}
			
			List<TestType> lstTestType = this.testTypeService.findTestType(PageRequest.of(0, 1000), null).getContent();
			if(lstTestType != null) {
				model.addAttribute("lstTestType", lstTestType);
			}
			
			model.addAttribute("type", type);
			return createView(model, "function/boss/test/test_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/test/update/{id}")
	public String updateTest(@PathVariable("id") Long id, @Valid @ModelAttribute(value="test") Test test, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Cập nhật");
				model.addAttribute("error", "Cập nhật thất bại!");
				List<TestType> lstTestType = this.testTypeService.findTestType(PageRequest.of(0, 1000), null).getContent();
				if(lstTestType != null) {
					model.addAttribute("lstTestType", lstTestType);
				}
	            return createView(model, "function/boss/test/test_detail.html");
	        }
	        
	        this.testService.saveTest(test);
	        redirAttrs.addFlashAttribute("success", "Cập nhật thành công!");
	        return "redirect:/test-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Cập nhật thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/test/delete/{id}")
	public String deleteTest(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
		try {
			this.testService.deleteTest(id);
			model.addAttribute("success", "Xóa thành công!");
			return "redirect:/test-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Xóa thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/test-update-status/{type}/{id}")
	public String updateTestStatus(@PathVariable("id") Long id, @PathVariable("type") String type, Model model, RedirectAttributes redirAttrs) {
		try {
			if(type.equals("active")) {
				this.testService.updateTestStatus(id, 1L);
				model.addAttribute("success", "Thao tác thành công!");
			}else if(type.equals("deactive")){
				this.testService.updateTestStatus(id, 0L);
				model.addAttribute("success", "Thao tác thành công!");
			}else {
				model.addAttribute("error", "Thao tác thất bại!");
			}
			
			return "redirect:/test-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Thao tác thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/list-account")
	public String viewAccountPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") AccountFindParams findParams) {
		try {
			model.addAttribute("title", "Danh sách tài khoản");
			Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("id")));
			findParams.setBossUsing(true);
			Page<Account> accountData = this.accountService.findAccount(pageAble, findParams);
			
			if(accountData != null) {
				model.addAttribute("pageSize", accountData.getSize());
				model.addAttribute("pageIndex", accountData.getNumber());
				model.addAttribute("totalPages", accountData.getTotalPages());
				model.addAttribute("totalElements", accountData.getTotalElements());
				List<Account> lstAccount = accountData.getContent();
				model.addAttribute("lstAccount", lstAccount);
				
				ObjectMapper oMapper = new ObjectMapper();
				String queryParams = "";
				Map<String, Object> map = (Map<String, Object>) oMapper.convertValue(findParams, Map.class);
				Set<Map.Entry<String, Object>> entrySet = map.entrySet();
				for(Map.Entry<String, Object> entry : entrySet) {
					if(entry.getValue() !=null && !entry.getKey().contains("id") && !entry.getKey().contains("bossUsing")) {
						queryParams += "&" + entry.getKey() + "=" + entry.getValue();
					}
			    }
				model.addAttribute("queryParams", queryParams);
				System.out.println(queryParams);
			}
			
			return createView(model, "function/boss/account/account_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	
	@GetMapping("/account/create")
	public String viewAccountCreatePage(AccountRequest accountRequest, Model model) {
		try {
			model.addAttribute("title", "Thêm mới");
			model.addAttribute("type", "create");

			return createView(model, "function/boss/account/account_create.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/account/create")
	public String createAccount(@Valid @ModelAttribute(value="accountRequest") AccountRequest accountRequest, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Thêm mới");
				model.addAttribute("error", "Thêm mới thất bại!");
	            return createView(model, "function/boss/account/account_create.html");
	        }
//			if((accountRequest.getDob()).compareTo(new Date()) > 0) {
//				model.addAttribute("title", "Thêm mới");
//				model.addAttribute("error", "Ngày sinh phải nhỏ hơn ngày hiện tại!");
//	            return createView(model, "function/boss/account/account_create.html");
//			}
			if (accountRepository.existsByUsername(accountRequest.getUsername())) {
				model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
			}
			if (accountRepository.existsByEmail(accountRequest.getEmail())) {
				model.addAttribute("error", "Email đã tồn tại!");
			}
			BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
			// Create new user's account
			Account accountVerified = new Account(accountRequest.getUsername(), encode.encode(accountRequest.getPassword()),
					accountRequest.getIsActive(), accountRequest.getFullname(), accountRequest.getAddress(),
					accountRequest.getDob(), accountRequest.getGender(), accountRequest.getRole(),
					accountRequest.getIsWorking(), accountRequest.getStatus(), accountRequest.getEmail());
	        
	        this.accountService.saveAccount(accountVerified);
	        redirAttrs.addFlashAttribute("success", "Thêm mới thành công!");
	        return "redirect:/list-account";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Thêm mới thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/account/{type}/{id}")
	public String viewAccountDetailPage(@PathVariable("id") Long id, UpdateAccountRequest updateAccountRequest, Model model, @PathVariable("type") String type) {
		try {
			Account account = this.accountService.getAccountById(id);
			updateAccountRequest = updateAccountRequest.build(account);
			model.addAttribute("updateAccountRequest", updateAccountRequest);
			if(type.contains("update")) {
				model.addAttribute("title", "Cập nhật");
			}else if(type.contains("read")) {
				model.addAttribute("title", "Xem chi tiết");
			}
			
			model.addAttribute("type", type);
			return createView(model, "function/boss/account/account_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/account/update/{id}")
	public String updateAccount(@PathVariable("id") Long id, @Valid @ModelAttribute(value="updateAccountRequest") UpdateAccountRequest updateAccountRequest, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Cập nhật");
				model.addAttribute("error", "Cập nhật thất bại!");
				model.addAttribute("type", "update");
	            return createView(model, "function/boss/account/account_detail.html");
	        }
			Account account = this.accountService.getAccountById(id);
			account.setAddress(updateAccountRequest.getAddress());
			account.setDob(updateAccountRequest.getDob());
			account.setEmail(updateAccountRequest.getEmail());
			account.setFullname(updateAccountRequest.getFullname());
			account.setGender(updateAccountRequest.getGender());
			account.setIsActive(updateAccountRequest.getIsActive());
			account.setIsWorking(updateAccountRequest.getIsWorking());
			account.setRole(updateAccountRequest.getRole());
			account.setStatus(updateAccountRequest.getStatus());
			account.setUsername(updateAccountRequest.getUsername());
			account.setPhone(updateAccountRequest.getPhone());
	        this.accountService.saveAccount(account);
	        redirAttrs.addFlashAttribute("success", "Cập nhật thành công!");
	        return "redirect:/list-account";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Cập nhật thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/account/change-password/{idAcc}")
	public String viewAccountChangePasswordPage(@PathVariable("idAcc") Long id, ChangePasswordRequest changePasswordRequest, Model model) {
		try {
			model.addAttribute("idAcc", id);
			model.addAttribute("title", "Đổi mật khẩu");
			return createView(model, "function/boss/account/account_change_password.html");
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
		
	}
	
	@PostMapping("/account/change-password/{idAcc}")
	public String changePasswordAccount(@PathVariable("idAcc") Long id, @Valid @ModelAttribute(value="changePasswordRequest") ChangePasswordRequest changePasswordRequest, BindingResult result, Model model, RedirectAttributes redirAttrs) {
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (result.hasErrors()) {
				model.addAttribute("error", "Đổi mật khẩu thất bại!");
	            return createView(model, "function/boss/account/account_change_password.html");
	        }
			
			Account account = this.accountService.getAccountById(id);
			if (!encoder.matches(changePasswordRequest.getOldPassword(), account.getPassword())) {
				model.addAttribute("error", "Mật khẩu cũ không chính xác!");
	            return createView(model, "function/boss/account/account_change_password.html");
			}else {
				account.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
			}
			this.accountService.saveAccount(account);
	        redirAttrs.addFlashAttribute("success", "Đổi mật khẩu thành công!");
	        return "redirect:/list-account";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Đổi mật khẩu thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/account/delete/{id}")
	public String deleteAccount(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
		try {
			this.accountService.deleteAccount(id);
			model.addAttribute("success", "Xóa thành công!");
			return "redirect:/list-account";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Xóa thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/account-update-status/{type}/{id}")
	public String updateAccountActivatedStatus(@PathVariable("id") Long id, @PathVariable("type") String type, Model model, RedirectAttributes redirAttrs) {
		try {
			if(type.equals("active")) {
				this.accountService.updateAccountStatus(id, 1L);
				model.addAttribute("success", "Thao tác thành công!");
			}else if(type.equals("deactive")){
				this.accountService.updateAccountStatus(id, 0L);
				model.addAttribute("success", "Thao tác thành công!");
			}else {
				model.addAttribute("error", "Thao tác thất bại!");
			}
			
			return "redirect:/list-account";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Thao tác thất bại!");
			return "error.html";
		}
	}
	
	@GetMapping("/account-reset-password/{id}")
	public String resetPasswordAccount(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Account account = this.accountService.getAccountById(id);
			String newPassword = encoder.encode(Constants.DEFAULT_PASSWORD);
			account.setPassword(newPassword);
			this.accountService.saveAccount(account);
			redirAttrs.addFlashAttribute("success", "Đặt lại mật khẩu thành công!");
			return "redirect:/list-account";
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}
	//END BOSS area
}
