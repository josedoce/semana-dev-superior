package com.sds.bootcamp.app.dto.jwt;

import java.util.Date;

public class TokenInfo {
	private Long id;
	private String email;
	private Date expiration;
	private Date issued;
	
	public TokenInfo() {}
	
	public TokenInfo(Long id, String email, Date expiration, Date issued) {
		this.id = id;
		this.email = email;
		this.expiration = expiration;
		this.issued = issued;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public Date getIssued() {
		return issued;
	}
	public void setIssued(Date issued) {
		this.issued = issued;
	}
}
