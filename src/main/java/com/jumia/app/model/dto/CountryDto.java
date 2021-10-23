package com.jumia.app.model.dto;

public class CountryDto {

	private String name;

	private String code;

	public CountryDto() {
	}

	public CountryDto(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
