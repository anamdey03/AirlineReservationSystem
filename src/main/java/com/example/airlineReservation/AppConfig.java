package com.example.airlineReservation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.airlineReservation.repository.AirlineReservationRepository;
import com.example.airlineReservation.repository.AirlineReservationRepositoryImpl;
import com.example.airlineReservation.repository.PassengerRepository;
import com.example.airlineReservation.repository.PassengerRepositoryImpl;
import com.example.airlineReservation.service.AirlineReservationService;
import com.example.airlineReservation.service.AirlineReservationServiceImpl;
import com.example.airlineReservation.service.PassengerService;
import com.example.airlineReservation.service.PassengerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public AirlineReservationService airlineReservationService() {
		return new AirlineReservationServiceImpl();
	}
	
	@Bean PassengerService passengerService() {
		return new PassengerServiceImpl();
	}
	
	@Bean
	public AirlineReservationRepository airlineReservationRepository() {
		return new AirlineReservationRepositoryImpl(); 
	}
	
	@Bean
	public PassengerRepository passengerRepository() {
		return new PassengerRepositoryImpl();
	}
}
