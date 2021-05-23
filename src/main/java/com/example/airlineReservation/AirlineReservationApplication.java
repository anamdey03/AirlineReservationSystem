package com.example.airlineReservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.airlineReservation.repository.AirlineReservationRepository;
import com.example.airlineReservation.repository.AirlineReservationRepositoryImpl;
import com.example.airlineReservation.repository.PassengerRepository;
import com.example.airlineReservation.repository.PassengerRepositoryImpl;
import com.example.airlineReservation.service.AirlineReservationService;
import com.example.airlineReservation.service.AirlineReservationServiceImpl;
import com.example.airlineReservation.service.PassengerService;
import com.example.airlineReservation.service.PassengerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AirlineReservationApplication {
	
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
	
	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationApplication.class, args);
	}

}
