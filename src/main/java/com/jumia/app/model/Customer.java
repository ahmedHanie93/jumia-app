package com.jumia.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends BasicEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phoneNumber;

	public Customer() {
		super();
	}

	public Customer(Integer id, String name, String phoneNumber) {
		super(id);
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
