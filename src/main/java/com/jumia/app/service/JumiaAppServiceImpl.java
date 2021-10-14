package com.jumia.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jumia.app.model.Country;
import com.jumia.app.model.Customer;
import com.jumia.app.model.dto.CountryDto;
import com.jumia.app.model.dto.PhoneNumberListingDto;
import com.jumia.app.repository.CountryRepository;
import com.jumia.app.repository.CustomerRepository;

@Service
public class JumiaAppServiceImpl implements JumiaAppService {

	private static final String INVALID = "Invalid";

	private static final String VALID = "Valid";

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CountryRepository countryRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<PhoneNumberListingDto> findAllPhoneNumbers() {
		List<Customer> customers = customerRepository.findAll();
		List<PhoneNumberListingDto> phoneNumbers = new ArrayList<>();

		if (!CollectionUtils.isEmpty(customers)) {
			List<Country> countries = countryRepository.findAll();
			Map<String, Country> codeCountryMap = getCodeCountryMap(countries);

			for (Customer customer : customers) {
				String phoneNumber = customer.getPhoneNumber();

				if (phoneNumber != null) {
					addPhoneNumberListingDto(codeCountryMap, phoneNumber, phoneNumbers);
				}
			}

		}

		return phoneNumbers;
	}

	private void addPhoneNumberListingDto(Map<String, Country> codeCountryMap, String phoneNumber,
			List<PhoneNumberListingDto> phoneNumbers) {
		PhoneNumberListingDto phoneNumberListingDto = new PhoneNumberListingDto();
		String code = phoneNumber.substring(1, 4);
		Country country = codeCountryMap.get('+' + code);

		if (country != null) {
			phoneNumberListingDto.setCountry(modelMapper.map(country, CountryDto.class));
			phoneNumberListingDto.setNumber(phoneNumber);
			phoneNumberListingDto.setState(getState(phoneNumber, country.getValidationRegex()));

			phoneNumbers.add(phoneNumberListingDto);
		}
	}

	private String getState(String phoneNumber, String validationRegex) {
		return Pattern.matches(validationRegex, phoneNumber) ? VALID : INVALID;
	}

	private Map<String, Country> getCodeCountryMap(List<Country> countries) {
		Map<String, Country> codeCountryMap = new HashMap<>();

		for (Country country : countries) {
			codeCountryMap.put(country.getCode(), country);
		}
		return codeCountryMap;
	}

}
