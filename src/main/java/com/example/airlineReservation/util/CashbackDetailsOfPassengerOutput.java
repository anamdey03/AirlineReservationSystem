package com.example.airlineReservation.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

public class CashbackDetailsOfPassengerOutput {

	private List<Status> status;
	
	@JsonFilter("CashbackFilter")
	private List<CashbackDetailsOfPassenger> cashbackDetailsOfPassengers;

	public CashbackDetailsOfPassengerOutput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CashbackDetailsOfPassengerOutput(List<Status> status,
			List<CashbackDetailsOfPassenger> cashbackDetailsOfPassengers) {
		super();
		this.status = status;
		this.cashbackDetailsOfPassengers = cashbackDetailsOfPassengers;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public List<CashbackDetailsOfPassenger> getCashbackDetailsOfPassengers() {
		return cashbackDetailsOfPassengers;
	}

	public void setCashbackDetailsOfPassengers(List<CashbackDetailsOfPassenger> cashbackDetailsOfPassengers) {
		this.cashbackDetailsOfPassengers = cashbackDetailsOfPassengers;
	}

}
