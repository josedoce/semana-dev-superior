package com.sds.bootcamp.app.errors.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problema {
	private Integer status;
	private OffsetDateTime datetime;
	private String title;
	private List<ErrorList> errors;
	
	public Problema() {
		
	}
	
	public Problema(Integer status, OffsetDateTime datetime, String title, List<ErrorList> errors) {
		this.status = status;
		this.datetime = datetime;
		this.title = title;
		this.errors = errors;
	}
	
	public static class ErrorList {
		private String name;
		private String message;
		
		public ErrorList() {}
		
		public ErrorList(String name, String message) {
			this.name = name;
			this.message = message;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return this.message;
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(OffsetDateTime datetime) {
		this.datetime = datetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ErrorList> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorList> errors) {
		this.errors = errors;
	}
	
}
