package com.sds.bootcamp.app.dto;

import java.io.Serializable;

import com.sds.bootcamp.app.model.Seller;

public class SellerDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	public SellerDTO() {}
	
	public SellerDTO(Seller entity) {
		this(entity.getId(), entity.getName());
	}
	
	public SellerDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
