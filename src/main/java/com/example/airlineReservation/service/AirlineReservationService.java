package com.example.airlineReservation.service;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.TravelDetailsOutput;

public interface AirlineReservationService {

	public AirlineReservationOutput addBookingDetails(ReservationDetails airlineReservation);
	public TravelDetailsOutput getTravelDetailsByTravelType(String travelType);
	public TravelDetailsOutput getTravelDetailsByDate(String startDate, String endDate);

}
