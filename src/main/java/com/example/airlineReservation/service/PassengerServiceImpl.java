package com.example.airlineReservation.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import com.example.airlineReservation.model.PassengerDetails;
import com.example.airlineReservation.repository.PassengerRepository;
import com.example.airlineReservation.util.FieldValidationStatus;
import com.example.airlineReservation.util.PerformStatus;
import com.example.airlineReservation.util.Status;

public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	@Override
	public FieldValidationStatus addPassengerDetails(PassengerDetails passengerDetails) {
		PassengerDetails passengerDetailsAdded = passengerRepository.addPassengerDetails(passengerDetails);
		return addedPassengerDetails(passengerDetailsAdded);
	}

	private static FieldValidationStatus addedPassengerDetails(PassengerDetails passengerDetails) {
		FieldValidationStatus fieldValidationStatus = new FieldValidationStatus();
		List<Status> statuses = new ArrayList<Status>();
		if (!passengerDetails.getPassengerId().equals(null)) {
			statuses.add(PerformStatus.passengerCreatedSuccess());
			fieldValidationStatus.setStatus(statuses);
			fieldValidationStatus.setPassengerId(passengerDetails.getPassengerId());
			return fieldValidationStatus;
		}
		statuses.add(PerformStatus.passengerCreatedFailed());
		fieldValidationStatus.setStatus(statuses);
		return fieldValidationStatus;
	}

	@Override
	public FieldValidationStatus updatePassengerDetails(Long passengerId, Map<String, Object> updates)
			throws ValidationException {
		PassengerDetails passengerDetailsUpdated = new PassengerDetails();
		PassengerDetails passengerDetails = passengerRepository.getBookingDetailsById(passengerId);
		updates.forEach((key, value) -> {
			// use reflection to get field key on object and set it to value value
			// Change ReservationDetails.class to whatever your object is: Object.class
			Field field = ReflectionUtils.findField(PassengerDetails.class, key); // find field in the object class
			field.setAccessible(true);
			ReflectionUtils.setField(field, passengerDetails, value); // set given field for defined object to value V
		});
		passengerDetailsUpdated = passengerRepository.updatePassengerDetails(passengerDetails);
		return updatedPassengerDetails(passengerDetailsUpdated);
	}

	private static FieldValidationStatus updatedPassengerDetails(PassengerDetails passengerDetails) {
		FieldValidationStatus fieldValidationStatus = new FieldValidationStatus();
		List<Status> statuses = new ArrayList<Status>();
		if (!passengerDetails.getPassengerId().equals(null)) {
			statuses.add(PerformStatus.passengerUpdateSuccess());
			fieldValidationStatus.setStatus(statuses);
			return fieldValidationStatus;
		}
		statuses.add(PerformStatus.passengerUpdateFailed());
		fieldValidationStatus.setStatus(statuses);
		return fieldValidationStatus;
	}
}
