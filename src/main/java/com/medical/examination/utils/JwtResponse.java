package com.medical.examination.utils;

public class JwtResponse {
	private Long id;
	private String type;
	private String token;
	private String username;
	private String email;
	private Long role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getRole() {
		return role;
	}
	public void setRole(Long role) {
		this.role = role;
	}
	public JwtResponse(Long id, String type, String token, String username, String email, Long role) {
		super();
		this.id = id;
		this.type = type;
		this.token = token;
		this.username = username;
		this.email = email;
		this.role = role;
	}
	
}
