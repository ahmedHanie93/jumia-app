package com.jumia.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jumia.app.model.Country;
import com.jumia.app.model.Customer;
import com.jumia.app.model.dto.PhoneNumberListingDto;
import com.jumia.app.repository.CountryRepository;
import com.jumia.app.repository.CustomerRepository;
import com.jumia.app.service.JumiaAppService;

@SpringBootTest
class JumiaAppServiceTests {

	private static final Country MOROCCO = new Country(0, "Morocco", "+212", "\\(212\\)\\  ?[5-9]\\d{8}$");
	private static final Customer VALID_CUSTOMER = new Customer(1, "Mohamed", "(212) 698054317");
	private static final Customer INVALID_CUSTOMER = new Customer(0, "Ahmed", "(212) 6007989253");
	private static final Customer NULL_NUMER_CUSTOMER = new Customer(0, "Ahmed", null);
	private static final Customer NON_EXISTENT_CODE_CUSTOMER = new Customer(1, "Mohamed", "(444) 698054317");

	@Autowired
	private JumiaAppService jumiaAppService;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private CountryRepository countryRepository;

	@Test
	public void findAllPhoneNumbersEmptyDBTest() {
		mockRepositoriesBehavior(new ArrayList<>(), new ArrayList<>());

		List<PhoneNumberListingDto> phoneNumbers = jumiaAppService.findAllPhoneNumbers();
		assertNotNull(phoneNumbers);
		assertEquals(0, phoneNumbers.size());
	}

	@Test
	public void findAllPhoneNumbersValidDataTest() {
		mockRepositoriesBehavior(List.of(INVALID_CUSTOMER, VALID_CUSTOMER), List.of(MOROCCO));

		List<PhoneNumberListingDto> phoneNumbers = jumiaAppService.findAllPhoneNumbers();
		assertEquals(2, phoneNumbers.size());
	}

	@Test
	public void findAllPhoneNumbersStateTest() {
		mockRepositoriesBehavior(List.of(INVALID_CUSTOMER, VALID_CUSTOMER), List.of(MOROCCO));

		List<PhoneNumberListingDto> phoneNumbers = jumiaAppService.findAllPhoneNumbers();
		assertNotNull(phoneNumbers);
		assertEquals("Invalid", phoneNumbers.get(0).getState());
		assertEquals("Valid", phoneNumbers.get(1).getState());
	}

	@Test
	public void findAllPhoneNumbersNullDBNumber() {
		mockRepositoriesBehavior(List.of(NULL_NUMER_CUSTOMER, VALID_CUSTOMER), List.of(MOROCCO));

		List<PhoneNumberListingDto> phoneNumbers = jumiaAppService.findAllPhoneNumbers();
		assertNotNull(phoneNumbers);
		assertEquals(1, phoneNumbers.size());
	}

	@Test
	public void findAllPhoneNumbersDBWrongCodeCountry() {
		mockRepositoriesBehavior(List.of(NULL_NUMER_CUSTOMER, NON_EXISTENT_CODE_CUSTOMER), List.of(MOROCCO));

		List<PhoneNumberListingDto> phoneNumbers = jumiaAppService.findAllPhoneNumbers();

		assertNotNull(phoneNumbers);
		assertEquals(0, phoneNumbers.size());
	}

	private void mockRepositoriesBehavior(List<Customer> customers, List<Country> countries) {
		when(customerRepository.findAll()).thenReturn(customers);
		when(countryRepository.findAll()).thenReturn(countries);
	}
}
