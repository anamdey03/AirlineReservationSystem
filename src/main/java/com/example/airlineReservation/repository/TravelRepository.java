package com.example.airlineReservation.repository;

import java.util.List;

import com.example.airlineReservation.model.ReservationDetails;

public interface TravelRepository {

	public List<ReservationDetails> getTravelDetailsByTravelType(String travelType);
	
	public List<ReservationDetails> getTravelDetailsByDate(String startDate, String endDate);
}
