package com.example.airlineReservation.service;

import java.util.Map;

import javax.validation.ValidationException;

import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.util.CashbackDetailsOfPassengerOutput;
import com.example.airlineReservation.util.FieldValidationStatus;
import com.example.airlineReservation.util.TravelDetailsOutput;

public interface AirlineReservationService {

	public FieldValidationStatus addBookingDetails(ReservationDetails airlineReservation);
	public TravelDetailsOutput getTravelDetailsByParameters(String travelType, String bookingStatus, String source, String destination);
	public TravelDetailsOutput getTravelDetailsByDate(String startDate, String endDate, boolean isCacheable);
	public FieldValidationStatus updateBookingDetails(Long pnr, Map<String, Object> updates) throws ValidationException;
	public FieldValidationStatus cancelBookingDetails(Long pnr);
	public CashbackDetailsOfPassengerOutput getBookingDetailsByCashbackEligibilty(Integer age, String gender, String travelType);
}
