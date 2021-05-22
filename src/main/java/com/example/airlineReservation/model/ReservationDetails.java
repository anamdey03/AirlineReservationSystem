package com.example.airlineReservation.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "reservationDetails")
@NamedQueries({
		@NamedQuery(name = ReservationDetails.TICKET_DETAILS_TRAVEL_TYPE, query = ReservationDetails.TICKET_DETAILS_TRAVEL_TYPE_QUERY),
		@NamedQuery(name = ReservationDetails.TICKET_DETAILS_BY_DATE, query = ReservationDetails.TICKET_DETAILS_BY_DATE_QUERY)
})
public class ReservationDetails {

	public static final String TICKET_DETAILS_TRAVEL_TYPE = "ReservationDetails.ticketDetailsByTravelType";
	public static final String TICKET_DETAILS_TRAVEL_TYPE_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address " 
			+ "where a.travelType = :travelType";
	
	public static final String TICKET_DETAILS_BY_DATE = "ReservationDetails.ticketDetailsByDate";
	public static final String TICKET_DETAILS_BY_DATE_QUERY = "Select distinct rd from reservationDetails rd "
			+ "left join address a on rd.pnr = a.reservationDetails "
			+ "left join addressDetail ad on a.addressId = ad.address " 
			+ "where (trunc(rd.bookedDateTime) between :startDate and :endDate)";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pnr;

	@NotBlank(message = "Name should not be blank")
	private String passengerName;

	@NotNull(message = "Age may not be empty")
	private Integer passengerAge;

	@NotBlank(message = "Gender should not be blank")
	@Pattern(regexp = "^[M|F]{1}$", message ="Gender must be M or F")
	private String gender;
	
	@NotNull(message = "Contact Number may not be empty")
	private Long passengerContactNumber;

	@NotBlank(message = "Email Id should not be blank")
	@Email(message = "Email Id should be in correct format")
	private String emailId;

	@NotBlank(message = "Source City should not be blank")
	private String source;

	@NotBlank(message = "Destination City should not be blank")
	private String destination;

	private LocalDateTime bookedDateTime;

	@JsonManagedReference
	@OneToOne(mappedBy = "reservationDetails", cascade = CascadeType.ALL)
	private Address address;

	public ReservationDetails() {
	}

	public ReservationDetails(Long pnr, String passengerName, Integer passengerAge, String gender, Long passengerContactNumber,
			String emailId, String source, String destination, LocalDateTime bookedDateTime, Address address) {
		this.pnr = pnr;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.gender = gender;
		this.passengerContactNumber = passengerContactNumber;
		this.emailId = emailId;
		this.source = source;
		this.destination = destination;
		this.bookedDateTime = bookedDateTime;
		this.address = address;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(Integer passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPassengerContactNumber() {
		return passengerContactNumber;
	}

	public void setPassengerContactNumber(Long passengerContactNumber) {
		this.passengerContactNumber = passengerContactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public LocalDateTime getBookedDateTime() {
		return bookedDateTime;
	}

	public void setBookedDateTime(LocalDateTime bookedDateTime) {
		this.bookedDateTime = bookedDateTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
