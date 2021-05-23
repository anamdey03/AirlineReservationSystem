package com.example.airlineReservation.repository;

import java.util.List;

import javax.validation.ValidationException;

import com.example.airlineReservation.model.ReservationDetails;

public interface AirlineReservationRepository {
	
	public ReservationDetails saveBookingDetails(ReservationDetails reservationDetails);

	public List<ReservationDetails> getTravelDetailsByTravelType(String travelType);
	
	public List<ReservationDetails> getTravelDetailsByDate(String startDate, String endDate);
	
	public List<ReservationDetails> getTravelDetailsByBookingStatus(String bookingStatus);
	
	public ReservationDetails updateBookingDetails(ReservationDetails reservationDetails);
	
	public ReservationDetails getBookingDetailsByPnr(Long pnr) throws ValidationException;
}
