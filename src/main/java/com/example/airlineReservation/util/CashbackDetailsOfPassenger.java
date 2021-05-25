package com.example.airlineReservation.util;

public class CashbackDetailsOfPassenger {

	private Long passengerId;
	private String passengerName;
	private String emailId;
	private Long pnr;
	private Double ticketPrice;
	private Double cashbackAmount;

	public CashbackDetailsOfPassenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CashbackDetailsOfPassenger(Long passengerId, String passengerName, String emailId, Long pnr,
			Double ticketPrice, Double cashbackAmount) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.emailId = emailId;
		this.pnr = pnr;
		this.ticketPrice = ticketPrice;
		this.cashbackAmount = cashbackAmount;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Double getCashbackAmount() {
		return cashbackAmount;
	}

	public void setCashbackAmount(Double cashbackAmount) {
		this.cashbackAmount = cashbackAmount;
	}

}
