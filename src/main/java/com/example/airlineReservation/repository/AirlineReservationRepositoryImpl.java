package com.example.airlineReservation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.airlineReservation.model.ReservationDetails;

public class AirlineReservationRepositoryImpl implements TravelRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ReservationDetails> getTravelDetailsByTravelType(String travelType) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.ticketDetailsByTravelType", ReservationDetails.class);
		query.setParameter("travelType", travelType);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

	@Override
	public List<ReservationDetails> getTravelDetailsByDate(String startDate, String endDate) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.ticketDetailsByDate", ReservationDetails.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

}
