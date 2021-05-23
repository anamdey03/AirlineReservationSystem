package com.example.airlineReservation.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.airlineReservation.model.PassengerDetails;
import com.example.airlineReservation.service.PassengerService;
import com.example.airlineReservation.util.FieldValidationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/airlineReservation/passenger")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	// Register a new Passenger
	@RequestMapping(path = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Registration Of Passengers", response = FieldValidationStatus.class, responseContainer = "String", httpMethod = "POST")
	public ResponseEntity<String> addPassengerDetails(@Valid @RequestBody PassengerDetails passengerDetails)
			throws JsonProcessingException {
		ObjectMapper customerMapper = new ObjectMapper();
		FieldValidationStatus passengerDetailsOutput = passengerService.addPassengerDetails(passengerDetails);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<>(customerMapper.writeValueAsString(passengerDetailsOutput), HttpStatus.OK);
	}
	
	// Update Passenger Details by Id
	@RequestMapping(path = "/update/{passengerId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update Booking Details", response = FieldValidationStatus.class, responseContainer = "String", httpMethod = "PATCH")
	public ResponseEntity<String> addBookingDetails(@PathVariable Long passengerId, @RequestBody Map<String, Object> updates) throws JsonProcessingException, ValidationException {
		ObjectMapper customerMapper = new ObjectMapper();
		FieldValidationStatus passengerDetailsUpdatedOutput = passengerService.updatePassengerDetails(passengerId, updates);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		customerMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		customerMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector())
				.registerModule(new JavaTimeModule()).setDateFormat(new StdDateFormat())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new ResponseEntity<String>(customerMapper.writeValueAsString(passengerDetailsUpdatedOutput), HttpStatus.OK);
	}
}
