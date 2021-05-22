package com.example.airlineReservation.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlineReservation.model.AirlineReservationOutput;
import com.example.airlineReservation.model.FieldValidationStatus;
import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.model.Status;
import com.example.airlineReservation.model.TravelDetailsOutput;
import com.example.airlineReservation.service.AirlineReservationService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@RequestMapping("api/v1/airlineReservation")
public class AirlineReservationController {

	@Autowired
	private AirlineReservationService airlineReservationService;

	// Add Booking Details for a Passenger
	@PostMapping(path = "/{booking}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addBookingDetails(@Valid @RequestBody ReservationDetails reservationDetails)
			throws JsonProcessingException {
		ObjectMapper customerMapper = new ObjectMapper();
		AirlineReservationOutput airlineReservationOutput = airlineReservationService
				.addBookingDetails(reservationDetails);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<>(customerMapper.writeValueAsString(airlineReservationOutput), HttpStatus.OK);
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

	// Get Booking Details of Passengers by Travel Type
	@GetMapping(path = "/booked/{travelType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTravelDetailsByTravelType(@PathVariable String travelType)
			throws JsonProcessingException {
		ObjectMapper customerMapper = new ObjectMapper();
		TravelDetailsOutput travelDetails = airlineReservationService.getTravelDetailsByTravelType(travelType);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<String>(customerMapper.writeValueAsString(travelDetails), HttpStatus.OK);
	}

	// Get Booking Details of Passengers between Start Date and End Date
	@GetMapping(path = "/booked/{startDate}/and/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
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

}
