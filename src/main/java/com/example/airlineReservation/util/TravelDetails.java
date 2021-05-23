package com.example.airlineReservation.util;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TravelDetails {

	private Long pnr;
	private String passengerName;
	private Long passengerContactNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime bookingDate;
	private String source;
	private String destination;
	private String travelType;
	private String sourcePinCode;
	private String destinationPinCode;

	public TravelDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelDetails(Long pnr, String passengerName, Long passengerContactNumber, LocalDateTime bookingDate,
			String source, String destination, String travelType, String sourcePinCode, String destinationPinCode) {
		super();
		this.pnr = pnr;
		this.passengerName = passengerName;
		this.passengerContactNumber = passengerContactNumber;
		this.bookingDate = bookingDate;
		this.source = source;
		this.destination = destination;
		this.travelType = travelType;
		this.sourcePinCode = sourcePinCode;
		this.destinationPinCode = destinationPinCode;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Long getPassengerContactNumber() {
		return passengerContactNumber;
	}

	public void setPassengerContactNumber(Long passengerContactNumber) {
		this.passengerContactNumber = passengerContactNumber;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getSourcePinCode() {
		return sourcePinCode;
	}

	public void setSourcePinCode(String sourcePinCode) {
		this.sourcePinCode = sourcePinCode;
	}

	public String getDestinationPinCode() {
		return destinationPinCode;
	}

	public void setDestinationPinCode(String destinationPinCode) {
		this.destinationPinCode = destinationPinCode;
	}

}
