package com.medical.examination.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.medical.examination.entity.*;
import com.medical.examination.findparams.InvoiceFindParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.medical.examination.findparams.AccountFindParams;
import com.medical.examination.findparams.ClinicWorkingFindParams;
import com.medical.examination.findparams.CustomerFindParams;
import com.medical.examination.request.MedicalExaminationRequest;
import com.medical.examination.service.AccountService;
import com.medical.examination.service.ClinicWorkingService;
import com.medical.examination.service.CustomerService;
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
	TestTypeService typeService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	TestResultService testResultService;
	
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
	public String addMedicalExaminationAddPage(MedicalExaminationRequest medicalExaminationRequest, Model model) {
		model.addAttribute("title", "Tạo khám bệnh");
		Customer customer = new Customer();
		Customer newCustomerCreated = new Customer();
		ClinicWorking clinicWorking = new ClinicWorking();
		//Check benh nhan ton` tai hay chua neu co thi lay tu db ra, khong thi tao moi
		if(medicalExaminationRequest.getCustomerId() != null) {
			Long checkCustomerExist = this.customerService.getCountCustomerById(medicalExaminationRequest.getCustomerId());
			if(checkCustomerExist != null && checkCustomerExist > 0) {
				customer = this.customerService.getCustomerById(medicalExaminationRequest.getCustomerId());
				clinicWorking.setCustomer(customer);
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
		System.out.println("OK");
		return "redirect:/medical-examination";
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
	
	@GetMapping("/medical-examination-working/{clinicWorkingId}")
	public String viewMedicalExaminationWorkingPage(@PathVariable("clinicWorkingId") Long clinicWorkingId, Model model) {
		model.addAttribute("title", "Khám bệnh");
		ClinicWorking clinicWorking = this.clinicWorkingService.getClinicWorkingById(clinicWorkingId);
		
		List<Test> lstTest = this.testService.findTest(PageRequest.of(0, 100), null).getContent();
		model.addAttribute("lstTest", lstTest);
		if(clinicWorking != null) {
			model.addAttribute("clinicWorking", clinicWorking);
		}
		return createView(model, "function/medical_examination/medical_examination_working.html");
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
}
