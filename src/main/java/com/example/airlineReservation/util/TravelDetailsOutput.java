package com.example.airlineReservation.util;

import java.util.List;

public class TravelDetailsOutput {

	private List<Status> status;
	private List<TravelDetails> travelDetails;

	public TravelDetailsOutput() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelDetailsOutput(List<Status> status, List<TravelDetails> travelDetails) {
		super();
		this.status = status;
		this.travelDetails = travelDetails;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public List<TravelDetails> getTravelDetails() {
		return travelDetails;
	}

	public void setTravelDetails(List<TravelDetails> travelDetails) {
		this.travelDetails = travelDetails;
	}

}
