package com.example.airlineReservation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

import com.example.airlineReservation.model.ReservationDetails;

public class AirlineReservationRepositoryImpl implements AirlineReservationRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public ReservationDetails saveBookingDetails(ReservationDetails reservationDetails) {
		entityManager.persist(reservationDetails);
		return reservationDetails;
	}

	@Override
	public List<ReservationDetails> getTravelDetailsByTravelType(String travelType) {
		TypedQuery<ReservationDetails> query = entityManager
				.createNamedQuery("ReservationDetails.ticketDetailsByTravelType", ReservationDetails.class);
		query.setParameter("travelType", travelType);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

	@Override
	public List<ReservationDetails> getTravelDetailsByDate(String startDate, String endDate) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.ticketDetailsByDate",
				ReservationDetails.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<ReservationDetails> result = query.getResultList();
		return result;
	}
	
	@Override
	public List<ReservationDetails> getTravelDetailsByBookingStatus(String bookingStatus) {
		TypedQuery<ReservationDetails> query = entityManager
				.createNamedQuery("ReservationDetails.ticketDetailsByBookingStatus", ReservationDetails.class);
		query.setParameter("bookingStatus", bookingStatus.toUpperCase());
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public ReservationDetails updateBookingDetails(ReservationDetails reservationDetails) {
		entityManager.merge(reservationDetails);
		return reservationDetails;
	}

	@Override
	public ReservationDetails getBookingDetailsByPnr(Long pnr) throws ValidationException {
		ReservationDetails result = new ReservationDetails();
		try {
			TypedQuery<ReservationDetails> query = entityManager
					.createNamedQuery("ReservationDetails.ticketDetailsByPnr", ReservationDetails.class);
			query.setParameter("pnr", pnr);
			result = query.getSingleResult();

		} catch (NoResultException exp) {
			throw new ValidationException("Booking Detail is not availble for the entered PNR");
		}
		return result;
	}

}
