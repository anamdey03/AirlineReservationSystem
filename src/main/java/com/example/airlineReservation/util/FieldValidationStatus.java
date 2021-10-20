package com.example.airlineReservation.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"pnr", "passengerId"})
public class FieldValidationStatus {

	private List<Status> status;

	private Long pnr;
	
	private Long passengerId;

	public FieldValidationStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldValidationStatus(List<Status> status) {
		super();
		this.status = status;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

}
