package com.medical.examination.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.medical.examination.entity.Account;
import com.medical.examination.entity.ClinicWorking;
import com.medical.examination.entity.Customer;
import com.medical.examination.entity.Invoice;
import com.medical.examination.entity.Test;
import com.medical.examination.entity.TestResult;
import com.medical.examination.entity.TestType;
import com.medical.examination.findparams.AccountFindParams;
import com.medical.examination.findparams.ClinicWorkingFindParams;
import com.medical.examination.findparams.CustomerFindParams;
import com.medical.examination.findparams.InvoiceFindParams;
import com.medical.examination.findparams.TestFindParams;
import com.medical.examination.findparams.TestResultFindParams;
import com.medical.examination.findparams.TestTypeFindParams;
import com.medical.examination.repository.ClinicWorkingRepository;
import com.medical.examination.request.MedicalExaminationRequest;
import com.medical.examination.request.ResultTestInvoiceRequest;
import com.medical.examination.service.AccountService;
import com.medical.examination.service.ClinicWorkingService;
import com.medical.examination.service.CustomerService;
import com.medical.examination.service.InvoiceService;
import com.medical.examination.service.TestResultService;
import com.medical.examination.service.TestService;
import com.medical.examination.service.TestTypeService;
import com.medical.examination.utils.AccountDetail;

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
	
	
	
	@GetMapping({ "", "/home" })
	public String viewHomePage(Model model) {
		model.addAttribute("title", "home");
		
		
		return createView(model, "index");
	}
	
	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		model.addAttribute("title", "login");
		return createView(model, "account/login");
	}
	
	@GetMapping("/medical-examination")
	public String viewMedicalExaminationPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") ClinicWorkingFindParams clinicWorkingFindParams) {
		model.addAttribute("title", "Phiếu khám bệnh");
		
		//Lay thong tin user trong phien lam viec
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) attr.getRequest();
		HttpSession session = request.getSession();
		account = (AccountDetail) session.getAttribute("CURRENT_USER");
		if(account.getRole() == 2) {
			clinicWorkingFindParams.setAccountId(account.getId()); //Neu la bac si dang nhap thi chi hien thi cac benh nhan cua minh
		}
		
		Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.asc("no")));
		AccountFindParams accountFindParams = new AccountFindParams();
		accountFindParams.setRole(2L); //Tim role = 2 bac si
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
		}
		
		return createView(model, "function/medical_examination/medical_examination.html");
	}
	
	@GetMapping("/medical-examination-add")
	public String viewMedicalExaminationAddPage(Model model, @ModelAttribute("findParams") CustomerFindParams customerFindParams, MedicalExaminationRequest medicalExaminationRequest) {
		model.addAttribute("title", "Tạo khám bệnh");
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
	}
	
	@PostMapping("/medical-examination-add")
	public String addMedicalExaminationAddPage(@Valid @ModelAttribute(value="medicalExaminationRequest") MedicalExaminationRequest medicalExaminationRequest, BindingResult result, Model model, RedirectAttributes redirAttrs,@ModelAttribute("findParams") CustomerFindParams customerFindParams) {
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
			return null;
		}
	}
	
	@GetMapping("/medical-examination/{id}")
	public String viewMedicalExaminationDetailPage(@PathVariable("id") Long id,Model model) {
		model.addAttribute("title", "Chi tiết phiếu khám bệnh");
		ClinicWorking clinicWorking = this.clinicWorkingService.getClinicWorkingById(id);
		if(clinicWorking!=null && clinicWorking.getId() != null) {
			model.addAttribute("clinicWorking", clinicWorking);
		}
		return createView(model, "function/medical_examination/medical_examination_detail.html");
	}
	
	@GetMapping("/medical-examination-working/{clinicWorkingId}/{type}")
	public String viewMedicalExaminationWorkingPage(@PathVariable("clinicWorkingId") Long clinicWorkingId, Model model, @PathVariable("type") String type) {
		try {
			model.addAttribute("title", "Khám bệnh");
			model.addAttribute("clinicWorkingId", clinicWorkingId);
			model.addAttribute("type", type);
			ClinicWorking clinicWorking = this.clinicWorkingService.getClinicWorkingById(clinicWorkingId);
			if(type.contains("add")) {
				clinicWorking.setStatus(1L); //set trang thai 1 : dang kham
				this.clinicWorkingService.updateClinicWorking(clinicWorking); //Cap nhat lai ban ghi trang thai dang kham
				
				//Lay data xet nghiem
				List<Test> lstTestMau = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(1L)).getContent();
				List<Test> lstTestHoaSinh = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(2L)).getContent();
				List<Test> lstTestViSinh = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(3L)).getContent();
				List<Test> lstTestPhan = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(4L)).getContent();
				List<Test> lstTestNuocTieu = this.testService.findTest(PageRequest.of(0, 100), new TestFindParams(5L)).getContent();
				model.addAttribute("lstTestMau", lstTestMau);
				model.addAttribute("lstTestHoaSinh", lstTestHoaSinh);
				model.addAttribute("lstTestViSinh", lstTestViSinh);
				model.addAttribute("lstTestPhan", lstTestPhan);
				model.addAttribute("lstTestNuocTieu", lstTestNuocTieu);
				
				
			}else {
				
			}
			if(clinicWorking != null) {
				model.addAttribute("clinicWorking", clinicWorking);
			}
			return createView(model, "function/medical_examination/medical_examination_working.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/medical-examination-working/{clinicWorkingId}/{type}")
	public String postMedicalExaminationWorkingPage(@PathVariable("clinicWorkingId") Long clinicWorkingId, @PathVariable("type") String type,Model model, @ModelAttribute("resultTestInvoiceRequest") ResultTestInvoiceRequest resultTestInvoiceRequest) {
		try {
			model.addAttribute("title", "Khám bệnh");
			
			TestResult testResult = new TestResult();
			Invoice invoice = new Invoice();

			ClinicWorking clinicWorking = this.clinicWorkingService.getClinicWorkingById(clinicWorkingId);
			
			if(type.contains("add")) {
				clinicWorking.setStatus(2L); //trang thai 2: cho ket qua xet nghiem
				//tao ket qua tet
				testResult.setClinicWorking(clinicWorking);
				testResult.setCreatedDate(new Date());
				testResult.setCode(resultTestInvoiceRequest.getCode());
				testResult.setDiagnosticResult(resultTestInvoiceRequest.getDiagnosticResult());
				testResult.setLstTest(resultTestInvoiceRequest.getLstTest());
				testResult.setExaminationFee(resultTestInvoiceRequest.getTotalSellPrice());
				testResult.setTotalCostPrice(resultTestInvoiceRequest.getTotalCostPrice());
				testResult.setTotalSellPrice(resultTestInvoiceRequest.getTotalSellPrice());
				testResult.setStatus(0L);
			}else {
				clinicWorking.setStatus(3L); // trang thai 3 da kham xong
				testResult = clinicWorking.getLstTestResult().get(0);
				//tao hoa don
				invoice.setClinicWorking(clinicWorking);
				invoice.setCreatedDate(new Date());
				invoice.setCode(resultTestInvoiceRequest.getCode());
				invoice.setLstTest(testResult.getLstTest());
				invoice.setTotalCostPrice(testResult.getTotalCostPrice() + 200000);
				invoice.setTotalSellPrice(testResult.getTotalSellPrice() + 200000);
				
				
				testResult.setTimeReturn(resultTestInvoiceRequest.getTimeReturn());
				testResult.setTestResult(resultTestInvoiceRequest.getTestResult());
				this.invoiceService.saveInvoice(invoice);
				
			}
			this.clinicWorkingService.updateClinicWorking(clinicWorking);
			this.testResultService.saveTestResult(testResult);
			
			return "redirect:/medical-examination";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/get-medical-fee")
	public String getMedicalFee(Model model) {
		try {
			model.addAttribute("title", "Thu tiền khám");
			TestResultFindParams findParams = new TestResultFindParams();
			Pageable pageAble = PageRequest.of(0, 1000, Sort.by(Sort.Order.desc("id")));
			List<TestResult> lstTestResult = this.testResultService.findTestResult(pageAble, findParams).getContent();
			model.addAttribute("lstTestResult", lstTestResult);
			return createView(model, "function/medical_fee/get_medical_fee.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/get-medical-fee-detail/{id}")
	public String getMedicalFeeDetail(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("title", "Chi tiết phiếu xét nghiệm");
			TestResult testResult = this.testResultService.getTestResultById(id);
			String[] lstTest = testResult.getLstTest().split(",");
			List<Test> lstTestComplete = new ArrayList<Test>();
			for(String item : lstTest) {
				Test test = this.testService.findByTestName(item.trim());
				lstTestComplete.add(test);
			}
			model.addAttribute("testResult", testResult);
			model.addAttribute("lstTestComplete", lstTestComplete);
			return createView(model, "function/medical_fee/get_medical_fee_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			return null;
		}
	}
	
	@GetMapping("/invoice")
	public String viewInvoicePage(Model model) {
		try {
			model.addAttribute("title", "Hóa đơn");
			InvoiceFindParams findParams = new InvoiceFindParams();
			List<Invoice> lstInvoice = this.invoiceService.findInvoice(PageRequest.of(0, 1000), findParams).getContent();
			model.addAttribute("lstInvoice", lstInvoice);
			return createView(model, "function/invoice/invoice_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/invoice-detail/{id}")
	public String viewInvoiceDetail(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("title", "Chi tiết Hóa đơn");
			Invoice invoice = this.invoiceService.getInvoiceById(id);
			String[] lstTest = invoice.getLstTest().split(",");
			List<Test> lstTestComplete = new ArrayList<Test>();
			for(String item : lstTest) {
				Test test = this.testService.findByTestName(item.trim());
				lstTestComplete.add(test);
			}
			model.addAttribute("invoice", invoice);
			model.addAttribute("lstTestComplete", lstTestComplete);
			return createView(model, "function/invoice/invoice_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			}
			
			return createView(model, "function/customer/customer_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			return null;
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
			return null;
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
			return null;
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
				redirAttrs.addFlashAttribute("error", "Cập nhật thất bại!");
	            return createView(model, "function/customer/customer_create.html");
	        }
	        
	        this.customerService.saveCustomer(customer);
	        redirAttrs.addFlashAttribute("success", "Cập nhật thành công!");
	        return "redirect:/customer-list";
		} catch (Exception e) {
			e.printStackTrace();
			redirAttrs.addFlashAttribute("error", "Cập nhật thất bại!");
			return null;
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
			return null;
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
			}
			
			AccountFindParams accountFindParams = new AccountFindParams();
			accountFindParams.setRole(2L); //Tim bac si
			accountFindParams.setIsActive(1L); //Tai khoan active
			List<Account> lstBacSi = this.accountService.findAccount(PageRequest.of(0, 1000), accountFindParams).getContent();
			if(lstBacSi != null) {
				model.addAttribute("lstBacSi", lstBacSi);
			}
			
			return createView(model, "function/boss/invoice_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/view-invoice-detail/{id}")
	public String viewBossInvoiceDetail(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("title", "Chi tiết Hóa đơn");
			Invoice invoice = this.invoiceService.getInvoiceById(id);
			String[] lstTest = invoice.getLstTest().split(",");
			List<Test> lstTestComplete = new ArrayList<Test>();
			for(String item : lstTest) {
				Test test = this.testService.findByTestName(item.trim());
				lstTestComplete.add(test);
			}
			model.addAttribute("invoice", invoice);
			model.addAttribute("lstTestComplete", lstTestComplete);
			return createView(model, "function/boss/invoice_detail.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
				
			}
			
			return createView(model, "function/boss/test/test_type_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			return null;
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
			return null;
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
			return null;
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
			return null;
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
			return null;
		}
	}
	
	@GetMapping("/test-list")
	public String viewTestPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model, @ModelAttribute("findParams") TestFindParams findParams) {
		try {
			model.addAttribute("title", "Danh sách xét nghiệm");
			Pageable pageAble = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("id")));
			Page<Test> testDate = this.testService.findTest(pageAble, findParams);
			
			if(testDate != null) {
				model.addAttribute("pageSize", testDate.getSize());
				model.addAttribute("pageIndex", testDate.getNumber());
				model.addAttribute("totalPages", testDate.getTotalPages());
				model.addAttribute("totalElements", testDate.getTotalElements());
				List<Test> lstTest = testDate.getContent();
				model.addAttribute("lstTest", lstTest);
				
			}
			
			return createView(model, "function/boss/test/test_list.html");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			return null;
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
			return null;
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
			return null;
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
			return null;
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
			return null;
		}
	}
	//END BOSS area
}
