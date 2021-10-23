package com.jumia.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jumia.app.model.dto.CountryDto;
import com.jumia.app.model.dto.PhoneNumberListingDto;
import com.jumia.app.service.JumiaAppService;

@RunWith(SpringRunner.class)
@WebMvcTest
class JumiaAppServiceRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JumiaAppService jumiaAppService;

	@Test
	public void testController() throws Exception {
		when(jumiaAppService.findAllPhoneNumbers()).thenReturn(
				List.of(new PhoneNumberListingDto(new CountryDto("Morocco", "+212"), "(212) 6007989253", "Invalid")));

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/phonenumbers")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"country\":{\"name\":\"Morocco\",\"code\":\"+212\"},\"number\":\"(212) 6007989253\",\"state\":\"Invalid\"}]";

		assertEquals(expected, result.getResponse().getContentAsString());
	}

}
