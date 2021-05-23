package com.example.airlineReservation.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "passengerDetails")
@NamedQueries({
	@NamedQuery(name = PassengerDetails.PASSENGER_DETAILS_BY_ID, query = PassengerDetails.PASSENGER_DETAILS_BY_ID_QUERY)
})
public class PassengerDetails {
	
	public static final String PASSENGER_DETAILS_BY_ID = "PassengerDetails.passengerDetailsById";
	public static final String PASSENGER_DETAILS_BY_ID_QUERY = "Select distinct pd from passengerDetails pd "
			+ "where pd.passengerId = :passengerId";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerId;

	@NotBlank(message = "Name should not be blank")
	private String passengerName;

	@NotNull(message = "Age may not be empty")
	private Integer passengerAge;

	@NotBlank(message = "Gender should not be blank")
	@Pattern(regexp = "^[M|F]{1}$", message = "Gender must be M or F")
	private String gender;

	@NotNull(message = "Contact Number may not be empty")
	private Long passengerContactNumber;

	@NotBlank(message = "Email Id should not be blank")
	@Email(message = "Email Id should be in correct format")
	private String emailId;

	@JsonManagedReference
	@OneToMany(mappedBy = "passengerDetails")
	private List<ReservationDetails> reservationDetails;

	public PassengerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PassengerDetails(Long passengerId, @NotBlank(message = "Name should not be blank") String passengerName,
			@NotNull(message = "Age may not be empty") Integer passengerAge,
			@NotBlank(message = "Gender should not be blank") @Pattern(regexp = "^[M|F]{1}$", message = "Gender must be M or F") String gender,
			@NotNull(message = "Contact Number may not be empty") Long passengerContactNumber,
			@NotBlank(message = "Email Id should not be blank") @Email(message = "Email Id should be in correct format") String emailId,
			List<ReservationDetails> reservationDetails) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.gender = gender;
		this.passengerContactNumber = passengerContactNumber;
		this.emailId = emailId;
		this.reservationDetails = reservationDetails;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Integer getPassengerAge() {
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

	public List<ReservationDetails> getReservationDetails() {
		return reservationDetails;
	}

	public void setReservationDetails(List<ReservationDetails> reservationDetails) {
		this.reservationDetails = reservationDetails;
	}

}
