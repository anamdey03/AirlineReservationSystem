package com.example.airlineReservation.repository;

import javax.validation.ValidationException;

import com.example.airlineReservation.model.PassengerDetails;

public interface PassengerRepository {

	public PassengerDetails addPassengerDetails(PassengerDetails passengerDetails);
	public PassengerDetails updatePassengerDetails(PassengerDetails passengerDetails);
	public PassengerDetails getBookingDetailsById(Long passengerId) throws ValidationException;
}
