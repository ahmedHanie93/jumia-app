package com.jumia.app.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jumia.app.model.dto.PhoneNumberListingDto;
import com.jumia.app.service.JumiaAppService;

@RestController
public class JumiaAppRestController {

	@Autowired
	private JumiaAppService jumiaAppService;

	@GetMapping(value = "/phonenumbers")
	public List<PhoneNumberListingDto> findAllPhoneNumbers() {
		return jumiaAppService.findAllPhoneNumbers();
	}
}
