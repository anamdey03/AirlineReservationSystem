package com.example.airlineReservation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.service.AirlineReservationService;
import com.example.airlineReservation.util.CashbackDetailsOfPassengerOutput;
import com.example.airlineReservation.util.FieldValidationStatus;
import com.example.airlineReservation.util.Status;
import com.example.airlineReservation.util.TravelDetailsOutput;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/airlineReservation")
public class AirlineReservationController {

	@Autowired
	private AirlineReservationService airlineReservationService;

	// Add Booking Details for a Passenger
	@RequestMapping(path = "/booking", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add Booking Details Of Passengers", response = FieldValidationStatus.class, responseContainer = "String", httpMethod = "POST")
	public ResponseEntity<String> addBookingDetails(@Valid @RequestBody ReservationDetails reservationDetails)
			throws JsonProcessingException {
		ObjectMapper customerMapper = new ObjectMapper();
		FieldValidationStatus airlineReservationOutput = airlineReservationService
				.addBookingDetails(reservationDetails);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<>(customerMapper.writeValueAsString(airlineReservationOutput), HttpStatus.OK);
	}

	// Updated Booking Details by PNR
	@RequestMapping(path = "/booked/update/{pnr}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Booking Details", response = FieldValidationStatus.class, responseContainer = "String", httpMethod = "PATCH")
	public ResponseEntity<String> addBookingDetails(@PathVariable Long pnr, @RequestBody Map<String, Object> updates)
			throws JsonProcessingException, ValidationException {
		ObjectMapper customerMapper = new ObjectMapper();
		FieldValidationStatus airlineReservationOutput = airlineReservationService.updateBookingDetails(pnr, updates);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<String>(customerMapper.writeValueAsString(airlineReservationOutput), HttpStatus.OK);
	}

	// Cancel Booking by PNR
	@RequestMapping(path = "booked/cancel/{pnr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Cancel Booking", response = FieldValidationStatus.class, responseContainer = "String", httpMethod = "GET")
	public ResponseEntity<String> cancelBooking(@PathVariable Long pnr)
			throws JsonProcessingException, ValidationException {
		ObjectMapper customerMapper = new ObjectMapper();
		FieldValidationStatus cancelBookingStatus = airlineReservationService.cancelBookingDetails(pnr);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<String>(customerMapper.writeValueAsString(cancelBookingStatus), HttpStatus.OK);
	}

	// Get Booking Details of Passengers by Parameters
	@RequestMapping(path = "/booking/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Fetch Booking Details Of Passengers By Parameters", response = TravelDetailsOutput.class, responseContainer = "String", httpMethod = "GET")
	public ResponseEntity<String> getTravelDetailsByParameters(@RequestParam(required = false) String travelType,
															   @RequestParam(required = false) String bookingStatus,
															   @RequestParam(required = false) String source,
															   @RequestParam(required = false) String destination)
			throws JsonProcessingException {
		ObjectMapper customerMapper = new ObjectMapper();
		TravelDetailsOutput travelDetails = airlineReservationService.getTravelDetailsByParameters(travelType, bookingStatus, source, destination);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<String>(customerMapper.writeValueAsString(travelDetails), HttpStatus.OK);
	}
	
	// Get Booking Details of Passengers between Start Date and End Date
	@RequestMapping(path = "/booking/{startDate}/and/{endDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Fetch Booking Details Of Passengers Present Between Start and End Date", response = TravelDetailsOutput.class, responseContainer = "String", httpMethod = "GET")
	public ResponseEntity<String> getTravelDetailsByDate(@PathVariable String startDate, @PathVariable String endDate)
			throws JsonProcessingException {
		ObjectMapper customerMapper = new ObjectMapper();
		TravelDetailsOutput travelDetails = airlineReservationService.getTravelDetailsByDate(startDate, endDate);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<String>(customerMapper.writeValueAsString(travelDetails), HttpStatus.OK);
	}
	
	// Get Reservation Details and Cashback Amount of Passengers with respect to their Age, Gender & Travel Type
	@RequestMapping(path = "cashback/age/{age}/gender/{gender}/travelType/{travelType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Fetching Cashback Details of Passengers with respect to their Age, Gender & Travel Type", response = CashbackDetailsOfPassengerOutput.class, responseContainer = "String", httpMethod = "GET")
	public ResponseEntity<String> getBookingDetailsByCashbackEligibilty(@PathVariable Integer age,
																		@PathVariable String gender,
																		@PathVariable String travelType) throws JsonProcessingException {
		ObjectMapper customerMapper = new ObjectMapper();
		CashbackDetailsOfPassengerOutput passengerDetails = airlineReservationService.getBookingDetailsByCashbackEligibilty(age, gender, travelType);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<String>(customerMapper.writeValueAsString(passengerDetails), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	FieldValidationStatus exceptionHandler(MethodArgumentNotValidException e) {
		FieldValidationStatus fieldValidationStatus = new FieldValidationStatus();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<Status> statuses = fieldErrors.stream().map(status -> new Status("failure", status.getDefaultMessage()))
				.collect(Collectors.toList());
		fieldValidationStatus.setStatus(statuses);
		return fieldValidationStatus;
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ValidationException.class)
	FieldValidationStatus exceptionHandler(ValidationException e) {
		List<Status> statuses = new ArrayList<Status>();
		statuses.add(new Status("failed", e.getMessage()));
		return new FieldValidationStatus(statuses);
	}

}
