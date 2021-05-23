package com.example.airlineReservation.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

import com.example.airlineReservation.model.PassengerDetails;

public class PassengerRepositoryImpl implements PassengerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public PassengerDetails addPassengerDetails(PassengerDetails passengerDetails) {
		entityManager.persist(passengerDetails);
		return passengerDetails;
	}

	@Override
	@Transactional
	public PassengerDetails updatePassengerDetails(PassengerDetails passengerDetails) {
		entityManager.merge(passengerDetails);
		return passengerDetails;
	}

	@Override
	public PassengerDetails getBookingDetailsById(Long passengerId) throws ValidationException {
		PassengerDetails result = new PassengerDetails();
		try {
			TypedQuery<PassengerDetails> query = entityManager.createNamedQuery("PassengerDetails.passengerDetailsById", PassengerDetails.class);
			query.setParameter("passengerId", passengerId);
			result = query.getSingleResult();
			
		}
		catch (NoResultException exp) {
			throw new ValidationException("Passenger detail is not availble for the entered id");
		}
		return result;
	}

}
