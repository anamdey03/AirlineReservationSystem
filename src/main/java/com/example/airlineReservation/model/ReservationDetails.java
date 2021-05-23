package com.example.airlineReservation.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "reservationDetails")
@NamedQueries({
		@NamedQuery(name = ReservationDetails.TICKET_DETAILS_BY_PARAMETERS, query = ReservationDetails.TICKET_DETAILS_BY_PARAMETERS_QUERY),
		@NamedQuery(name = ReservationDetails.TICKET_DETAILS_BY_DATE, query = ReservationDetails.TICKET_DETAILS_BY_DATE_QUERY),
		@NamedQuery(name = ReservationDetails.TICKET_DETAILS_BY_PNR, query = ReservationDetails.TICKET_DETAILS_BY_PNR_QUERY) })
public class ReservationDetails {

	public static final String TICKET_DETAILS_BY_PARAMETERS = "ReservationDetails.ticketDetailsByParameters";
	public static final String TICKET_DETAILS_BY_PARAMETERS_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join passengerDetails pd on rd.passengerDetails = pd.passengerId "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address " 
			+ "where (:travelType IS NULL OR a.travelType = :travelType) AND (:bookingStatus IS NULL OR rd.bookingStatus = :bookingStatus) "
			+ "AND (:source IS NULL OR rd.source = :source) AND (:destination IS NULL OR rd.destination = :destination)";
	
	public static final String TICKET_DETAILS_BY_DATE = "ReservationDetails.ticketDetailsByDate";
	public static final String TICKET_DETAILS_BY_DATE_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join passengerDetails pd on rd.passengerDetails = pd.passengerId "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address "
			+ "where (trunc(rd.bookingDateTime) between :startDate and :endDate)";

	public static final String TICKET_DETAILS_BY_PNR = "ReservationDetails.ticketDetailsByPnr";
	public static final String TICKET_DETAILS_BY_PNR_QUERY = "Select distinct rd from reservationDetails rd "
			+ "where rd.pnr = :pnr";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pnr;

	@NotBlank(message = "Source City should not be blank")
	private String source;

	@NotBlank(message = "Destination City should not be blank")
	private String destination;

	private LocalDateTime bookingDateTime;
	
	private String bookingStatus;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "passengerId", referencedColumnName = "passengerId")
	private PassengerDetails passengerDetails;

	@JsonManagedReference
	@OneToOne(mappedBy = "reservationDetails", cascade = CascadeType.ALL)
	private Address address;

	public ReservationDetails() {

	}

	public ReservationDetails(Long pnr, @NotBlank(message = "Source City should not be blank") String source,
			@NotBlank(message = "Destination City should not be blank") String destination,
			LocalDateTime bookingDateTime, String bookingStatus, PassengerDetails passengerDetails, Address address) {
		super();
		this.pnr = pnr;
		this.source = source;
		this.destination = destination;
		this.bookingDateTime = bookingDateTime;
		this.bookingStatus = bookingStatus;
		this.passengerDetails = passengerDetails;
		this.address = address;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getBookingDateTime() {
		return bookingDateTime;
	}

	public void setBookingDateTime(LocalDateTime bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}
	
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public PassengerDetails getPassengerDetails() {
		return passengerDetails;
	}

	public void setPassengerDetails(PassengerDetails passengerDetails) {
		this.passengerDetails = passengerDetails;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
