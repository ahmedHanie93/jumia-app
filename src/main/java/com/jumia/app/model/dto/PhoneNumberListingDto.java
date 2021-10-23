package com.jumia.app.model.dto;

public class PhoneNumberListingDto {

	private CountryDto country;

	private String number;

	private String state;

	public PhoneNumberListingDto() {
	}

	public PhoneNumberListingDto(CountryDto country, String number, String state) {
		super();
		this.country = country;
		this.number = number;
		this.state = state;
	}

	public CountryDto getCountry() {
		return country;
	}

	public void setCountry(CountryDto country) {
		this.country = country;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
