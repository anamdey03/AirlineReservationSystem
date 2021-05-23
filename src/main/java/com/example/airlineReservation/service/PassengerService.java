package com.example.airlineReservation.service;

import java.util.Map;

import javax.validation.ValidationException;

import com.example.airlineReservation.model.PassengerDetails;
import com.example.airlineReservation.util.FieldValidationStatus;

public interface PassengerService {

	public FieldValidationStatus addPassengerDetails(PassengerDetails passengerDetails);
	public FieldValidationStatus updatePassengerDetails(Long passengerId, Map<String, Object> updates) throws ValidationException;
}
