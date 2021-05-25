package com.example.airlineReservation.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.repository.AirlineReservationRepository;
import com.example.airlineReservation.util.BookingStatus;
import com.example.airlineReservation.util.CashbackDetailsOfPassenger;
import com.example.airlineReservation.util.CashbackDetailsOfPassengerOutput;
import com.example.airlineReservation.util.FieldValidationStatus;
import com.example.airlineReservation.util.PerformStatus;
import com.example.airlineReservation.util.Status;
import com.example.airlineReservation.util.TravelDetails;
import com.example.airlineReservation.util.TravelDetailsOutput;

public class AirlineReservationServiceImpl implements AirlineReservationService {

	@Autowired
	private AirlineReservationRepository airlineReservationRepository;

	@Override
	public FieldValidationStatus addBookingDetails(ReservationDetails airlineReservation) {
		airlineReservation.setBookingDateTime(LocalDateTime.now());
		airlineReservation.setBookingStatus(BookingStatus.BOOKED.name());
		ReservationDetails reservationDetailsAdded = airlineReservationRepository
				.saveBookingDetails(airlineReservation);
		return addedBookingDetails(reservationDetailsAdded);
	}

	@Override
	public TravelDetailsOutput getTravelDetailsByParameters(String travelType, String bookingStatus, String source, String destination) {
		List<ReservationDetails> reservationDetails = airlineReservationRepository
				.getTravelDetailsByParameters(travelType, bookingStatus, source, destination);
		return getBookingDetails(reservationDetails);
	}

	@Override
	public TravelDetailsOutput getTravelDetailsByDate(String startDate, String endDate) {
		List<ReservationDetails> reservationDetails = airlineReservationRepository.getTravelDetailsByDate(startDate,
				endDate);
		return getBookingDetails(reservationDetails);
	}

	@Override
	public FieldValidationStatus updateBookingDetails(Long pnr, Map<String, Object> updates)
			throws ValidationException {
		ReservationDetails reservationDetailsUpdated = new ReservationDetails();
		ReservationDetails reservationDetails = airlineReservationRepository.getBookingDetailsByPnr(pnr);
		updates.forEach((key, value) -> {
			// use reflection to get field key on object and set it to value value
			// Change ReservationDetails.class to whatever your object is: Object.class
			Field field = ReflectionUtils.findField(ReservationDetails.class, key); // find field in the object class
			field.setAccessible(true);
			ReflectionUtils.setField(field, reservationDetails, value); // set given field for defined object to value V
		});
		reservationDetailsUpdated = airlineReservationRepository.updateBookingDetails(reservationDetails);
		return updatedBookingDetails(reservationDetailsUpdated);
	}

	@Override
	public FieldValidationStatus cancelBookingDetails(Long pnr) {
		ReservationDetails reservationDetails = airlineReservationRepository.getBookingDetailsByPnr(pnr);
		if (reservationDetails.getBookingStatus().equals(BookingStatus.BOOKED.name())) {
			reservationDetails.setBookingStatus(BookingStatus.CANCELLED.name());
			reservationDetails = airlineReservationRepository.updateBookingDetails(reservationDetails);
			return cancelBookingDetails(reservationDetails);
		}
		return cancelBookingDetailsFailed(reservationDetails);
	}

	private static TravelDetailsOutput getBookingDetails(List<ReservationDetails> reservationDetails) {
		List<TravelDetails> travelDetails = new ArrayList<>();
		TravelDetailsOutput travelDetailsOutput = new TravelDetailsOutput();
		List<Status> statusList = new ArrayList<Status>();
		Status status = new Status();
		if (!reservationDetails.isEmpty()) {
			travelDetails = reservationDetails.stream()
					.map(reservationDetail -> new TravelDetails(reservationDetail.getPnr(),
							reservationDetail.getPassengerDetails().getPassengerName(),
							reservationDetail.getPassengerDetails().getPassengerContactNumber(),
							reservationDetail.getBookingDateTime(), reservationDetail.getSource(),
							reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType(),
							reservationDetail.getAddress().getAddressDetail().stream()
									.filter(address -> address.getAddressType().equals("Source")).findFirst().get()
									.getPincode(),
							reservationDetail.getAddress().getAddressDetail().stream()
									.filter(address -> address.getAddressType().equals("Destination")).findFirst().get()
									.getPincode()))
					.collect(Collectors.toList());
			status.setStatusLevel("success");
			status.setMessage("Booking Details Fetched Successfully");
			statusList.add(status);
			travelDetailsOutput.setStatus(statusList);
			travelDetailsOutput.setTravelDetails(travelDetails);
			return travelDetailsOutput;
		}
		status.setStatusLevel("success");
		status.setMessage("Booking details not available");
		statusList.add(status);
		travelDetailsOutput.setStatus(statusList);
		return travelDetailsOutput;
	}

	private static FieldValidationStatus addedBookingDetails(ReservationDetails reservationDetails) {
		FieldValidationStatus fieldValidationStatus = new FieldValidationStatus();
		List<Status> statuses = new ArrayList<Status>();
		if (!reservationDetails.getPnr().equals(null)) {
			statuses.add(PerformStatus.bookingCreatedSuccess());
			fieldValidationStatus.setStatus(statuses);
			return fieldValidationStatus;
		}
		statuses.add(PerformStatus.bookingCreatedFailed());
		fieldValidationStatus.setStatus(statuses);
		return fieldValidationStatus;
	}

	private static FieldValidationStatus updatedBookingDetails(ReservationDetails reservationDetails) {
		FieldValidationStatus fieldValidationStatus = new FieldValidationStatus();
		List<Status> statuses = new ArrayList<Status>();
		if (!reservationDetails.getPnr().equals(null)) {
			statuses.add(PerformStatus.bookingUpdateSuccess());
			fieldValidationStatus.setStatus(statuses);
			return fieldValidationStatus;
		}
		statuses.add(PerformStatus.bookingUpdateFailed());
		fieldValidationStatus.setStatus(statuses);
		return fieldValidationStatus;
	}

	private static FieldValidationStatus cancelBookingDetails(ReservationDetails reservationDetails) {
		FieldValidationStatus fieldValidationStatus = new FieldValidationStatus();
		List<Status> statuses = new ArrayList<Status>();
		if (reservationDetails.getBookingStatus().equals(BookingStatus.CANCELLED.name())) {
			statuses.add(PerformStatus.bookingCancelSuccess());
			fieldValidationStatus.setStatus(statuses);
			return fieldValidationStatus;
		}
		statuses.add(PerformStatus.bookingCancelFailed());
		fieldValidationStatus.setStatus(statuses);
		return fieldValidationStatus;
	}

	private static FieldValidationStatus cancelBookingDetailsFailed(ReservationDetails reservationDetails) {
		FieldValidationStatus fieldValidationStatus = new FieldValidationStatus();
		List<Status> statuses = new ArrayList<Status>();
		statuses.add(PerformStatus.bookingCancelFailed());
		fieldValidationStatus.setStatus(statuses);
		return fieldValidationStatus;
	}

	@Override
	public CashbackDetailsOfPassengerOutput getBookingDetailsByCashbackEligibilty(Integer age, String gender,
			String travelType) {
		List<ReservationDetails> passengerDetails = airlineReservationRepository.getBookingDetailsByCashbackEligibilty(age, gender, travelType);
		return getCashbackDetailsOfPassengers(passengerDetails);
	}
	
	private static CashbackDetailsOfPassengerOutput getCashbackDetailsOfPassengers(List<ReservationDetails> reservationDetails) {
		List<CashbackDetailsOfPassenger> cashbackDetailsOfPassengers = new ArrayList<>();
		CashbackDetailsOfPassengerOutput cashbackDetailsOfPassengerOutput = new CashbackDetailsOfPassengerOutput();
		List<Status> statusList = new ArrayList<>();
		Status  status = new Status();
		if(!reservationDetails.isEmpty()) {
			cashbackDetailsOfPassengers = reservationDetails.stream()
					.map(reservationDetail -> new CashbackDetailsOfPassenger(reservationDetail.getPassengerDetails().getPassengerId(), 
							reservationDetail.getPassengerDetails().getPassengerName(), reservationDetail.getPassengerDetails().getEmailId(), 
							reservationDetail.getPnr(), reservationDetail.getTicketPrice(), cashbackAmount(reservationDetail.getTicketPrice())))
					.collect(Collectors.toList());
			status.setStatusLevel("success");
			status.setMessage("Cashback Details of Passengers Fetched Successfully");
			statusList.add(status);
			cashbackDetailsOfPassengerOutput.setStatus(statusList);
			cashbackDetailsOfPassengerOutput.setCashbackDetailsOfPassengers(cashbackDetailsOfPassengers);
			return cashbackDetailsOfPassengerOutput;
		}
		status.setStatusLevel("success");
		status.setMessage("Cashback Details not available!!");
		statusList.add(status);
		cashbackDetailsOfPassengerOutput.setStatus(statusList);
		return cashbackDetailsOfPassengerOutput;
	}
	
	private static Double cashbackAmount(Double price) {
		return (20 * price) / 100;
	}

}
