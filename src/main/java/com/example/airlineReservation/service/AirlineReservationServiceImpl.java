package com.example.airlineReservation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.Status;
import com.example.airlineReservation.model.TravelDetails;
import com.example.airlineReservation.model.TravelDetailsOutput;
import com.example.airlineReservation.repository.AirlineReservationRepository;
import com.example.airlineReservation.repository.TravelRepository;

@Service
public class AirlineReservationServiceImpl implements AirlineReservationService {
	
	@Autowired
	private AirlineReservationRepository airlineReservationRepository;
	
	@Autowired
	private TravelRepository travelRepository;

	@Override
	public AirlineReservationOutput addBookingDetails(ReservationDetails airlineReservation) {
		airlineReservation.setBookedDateTime(LocalDateTime.now());
		ReservationDetails reservationDetailsAdded =  airlineReservationRepository.save(airlineReservation);
		return addedBookingDetails(reservationDetailsAdded);
	}

	@Override
	public TravelDetailsOutput getTravelDetailsByTravelType(String travelType) {
		List<ReservationDetails> reservationDetails = travelRepository.getTravelDetailsByTravelType(travelType);
		return getBookingDetails(reservationDetails);
	}

	@Override
	public TravelDetailsOutput getTravelDetailsByDate(String startDate, String endDate) {
		List<ReservationDetails> reservationDetails = travelRepository.getTravelDetailsByDate(startDate, endDate);
		return getBookingDetails(reservationDetails);
	}
	
	private static TravelDetailsOutput getBookingDetails(List<ReservationDetails> reservationDetails) {
		List<TravelDetails> travelDetails = new ArrayList<>();
		TravelDetailsOutput travelDetailsOutput = new TravelDetailsOutput();
		List<Status> statusList = new ArrayList<Status>();
		Status status = new Status();
		if(!reservationDetails.isEmpty()) {
			travelDetails = reservationDetails.stream()
					.map(reservationDetail -> new TravelDetails(reservationDetail.getPnr(), 
							reservationDetail.getPassengerName(), reservationDetail.getPassengerContactNumber(), reservationDetail.getBookedDateTime(),
							reservationDetail.getSource(), reservationDetail.getDestination(), reservationDetail.getAddress().getTravelType(),
							reservationDetail.getAddress().getAddressDetail().stream().filter(address -> address.getAddressType().equals("Source")).findFirst().get().getPincode(),
							reservationDetail.getAddress().getAddressDetail().stream().filter(address -> address.getAddressType().equals("Destination")).findFirst().get().getPincode()))
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
	
	private static AirlineReservationOutput addedBookingDetails(ReservationDetails reservationDetails) {
		AirlineReservationOutput airlineReservationOutput = new AirlineReservationOutput();
		Status status = new Status();
		List<Status> statuses = new ArrayList<Status>();
		if(!reservationDetails.getPnr().equals(null)) {
			status.setStatusLevel("Success");
			status.setMessage("Ticket booked successfully!!");
			statuses.add(status);
			airlineReservationOutput.setStatus(statuses);
			airlineReservationOutput.setReservationDetails(reservationDetails);
			return airlineReservationOutput;
		}
		status.setStatusLevel("Failure");
		status.setMessage("Booking Failed!!");
		statuses.add(status);
		airlineReservationOutput.setStatus(statuses);
		return airlineReservationOutput;
	}

}
