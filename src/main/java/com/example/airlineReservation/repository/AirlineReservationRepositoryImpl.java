package com.example.airlineReservation.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

import com.example.airlineReservation.model.ReservationDetails;
import com.example.airlineReservation.util.BookingStatus;

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
	public List<ReservationDetails> getTravelDetailsByParameters(String travelType, String bookingStatus, String source, String destination) {
		TypedQuery<ReservationDetails> query = entityManager
				.createNamedQuery("ReservationDetails.ticketDetailsByParameters", ReservationDetails.class);
		query.setParameter("travelType", travelType);
		query.setParameter("bookingStatus", bookingStatus != null ? bookingStatus.toUpperCase() : null);
		query.setParameter("source", source);
		query.setParameter("destination", destination);
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

	@Override
	public List<ReservationDetails> getBookingDetailsByCashbackEligibilty(Integer age, String gender,
			String travelType) {
		TypedQuery<ReservationDetails> query = entityManager.createNamedQuery("ReservationDetails.reservationDetailsByCashbackEligibility", ReservationDetails.class);
		query.setParameter("gender", gender);
		query.setParameter("age", age);
		query.setParameter("travelType", travelType);
		query.setParameter("bookingStatus", BookingStatus.BOOKED.name());
		List<ReservationDetails> result = query.getResultList();
		return result;
	}

}
