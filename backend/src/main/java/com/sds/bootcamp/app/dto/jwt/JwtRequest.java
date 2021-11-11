package com.sds.bootcamp.app.dto.jwt;

import java.io.Serializable;

public class JwtRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private boolean remember = false;
	
	public JwtRequest(){}
	
	public JwtRequest(String email, String password, boolean remember) {
		this.email = email;
		this.password = password;
		this.remember = remember;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
}
