package com.example.airlineReservation.util;

public class PerformStatus {
	
	public static Status passengerCreatedSuccess() {
		Status status = new Status();
		status.setStatusLevel("Success");
		status.setMessage("Passenger registered successfully!!");
		return status;
	}
	
	public static Status passengerCreatedFailed() {
		Status status = new Status();
		status.setStatusLevel("Failure");
		status.setMessage("Passenger registration Failed!!");
		return status;
	}
	
	public static Status bookingCreatedSuccess() {
		Status status = new Status();
		status.setStatusLevel("Success");
		status.setMessage("Ticket booked successfully!!");
		return status;
	}
	
	public static Status bookingCreatedFailed() {
		Status status = new Status();
		status.setStatusLevel("Failure");
		status.setMessage("Booking Failed!!");
		return status;
	}
	
	public static Status bookingUpdateSuccess() {
		Status status = new Status();
		status.setStatusLevel("Success");
		status.setMessage("Booking details updated successfully!!");
		return status;
	}
	
	public static Status bookingUpdateFailed() {
		Status status = new Status();
		status.setStatusLevel("Failure");
		status.setMessage("Booking details updation Failed!!");
		return status;
	}
	
	public static Status passengerUpdateSuccess() {
		Status status = new Status();
		status.setStatusLevel("Success");
		status.setMessage("Passenger details updated successfully!!");
		return status;
	}
	
	public static Status passengerUpdateFailed() {
		Status status = new Status();
		status.setStatusLevel("Failure");
		status.setMessage("Passenger details updation Failed!!");
		return status;
	}
	
	public static Status bookingCancelSuccess() {
		Status status = new Status();
		status.setStatusLevel("Success");
		status.setMessage("Booking details cancelled successfully!!");
		return status;
	}
	
	public static Status bookingCancelFailed() {
		Status status = new Status();
		status.setStatusLevel("Failure");
		status.setMessage("Booking cancellation failed or the booking is already cancelled!!");
		return status;
	}
	
	public static Status bookingNotAvailable() {
		Status status = new Status();
		status.setStatusLevel("Failure");
		status.setMessage("Booking details not available for the given PNR!!");
		return status;
	}
}
