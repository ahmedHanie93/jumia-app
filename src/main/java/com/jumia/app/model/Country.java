package com.jumia.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country extends BasicEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "regex")
	private String validationRegex;

	public Country() {
	}

	public Country(Integer id, String name, String code, String validationRegex) {
		super(id);
		this.name = name;
		this.code = code;
		this.validationRegex = validationRegex;
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

	public String getValidationRegex() {
		return validationRegex;
	}

	public void setValidationRegex(String validationRegex) {
		this.validationRegex = validationRegex;
	}
}
