package com.paypal.library.entities;

import javax.persistence.Embeddable;

/**
 * @author Sandip
 */
@Embeddable
public class Address {

	private String building;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	public Address() {
		
	}
	
	public Address(String building, String landmark, String city, String state, String country, String pincode) {
		this.building = building;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public String getBuilding() {
		return building;
	}

	public String getLandmark() {
		return landmark;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	
}
