package com.jumia.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jumia.app.model.dto.PhoneNumberListingDto;

@Service
public interface JumiaAppService {

	List<PhoneNumberListingDto> findAllPhoneNumbers();

}
