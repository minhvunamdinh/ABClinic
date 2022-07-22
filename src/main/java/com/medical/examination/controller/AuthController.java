package com.medical.examination.controller;

import com.medical.examination.entity.Account;
import com.medical.examination.repository.AccountRepository;
import com.medical.examination.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@GetMapping("/test-string")
	public String test() {
		return "Auth controller OK!";
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		AccountDetail accountDetails = (AccountDetail) authentication.getPrincipal();
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = (HttpServletRequest) attr.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("CURRENT_USER", accountDetails);
		return (ResponseEntity<?>) ResponseEntity.ok(new JwtResponse(accountDetails.getId(), "Bearer", jwt,
				accountDetails.getUsername(), accountDetails.getEmail(), accountDetails.getRole()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		System.out.println("RUN");
		if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		Account account = new Account(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getIsActive(), signUpRequest.getFullname(), signUpRequest.getAddress(),
				signUpRequest.getDob(), signUpRequest.getGender(), signUpRequest.getRole(),
				signUpRequest.getIsWorking(), signUpRequest.getStatus(), signUpRequest.getEmail());

		accountRepository.save(account);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
