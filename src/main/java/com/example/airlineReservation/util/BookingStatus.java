package com.example.airlineReservation.util;

public enum BookingStatus {

	BOOKED("B"), CANCELLED("C");

	private final String shortCode;

	private BookingStatus(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getShortCode() {
		return shortCode;
	}

}
