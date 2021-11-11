package com.sds.bootcamp.app.dto.jwt;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final String jwttoken;
	private OffsetDateTime datetime;
	
	public JwtResponse(String jwttoken) {
		this.datetime = OffsetDateTime.now();
		this.jwttoken = jwttoken;
	}
	
	public String getJwttoken() {
		return jwttoken;
	}
	
	public String getDate() {
		return datetime.toString();
	}
}
