package com.test.rest_service;

import com.test.rest_service.model.Price;
import com.test.rest_service.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RestServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private PriceRepository priceRepository;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		priceRepository.deleteAll();
		//Inicializa la bbdd con datos de prueba
		priceRepository.save(new Price(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455, 0, 35.50, "EUR"));
		priceRepository.save(new Price(1, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455, 1, 25.45, "EUR"));
		priceRepository.save(new Price(1, LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455, 1, 30.50, "EUR"));
		priceRepository.save(new Price(1, LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 4, 35455, 1, 38.95, "EUR"));
	}

	@Test
	public void test1() throws Exception {
		mockMvc.perform(get("/api/price")
						.param("applicationDate", "2020-06-14T10:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	public void test2() throws Exception {
		mockMvc.perform(get("/api/price")
						.param("applicationDate", "2020-06-14T16:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(25.45));
	}

	@Test
	public void test3() throws Exception {
		mockMvc.perform(get("/api/price")
						.param("applicationDate", "2020-06-14T21:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	public void test4() throws Exception {
		mockMvc.perform(get("/api/price")
						.param("applicationDate", "2020-06-15T10:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(30.50));
	}

	@Test
	public void test5() throws Exception {
		mockMvc.perform(get("/api/price")
						.param("applicationDate", "2020-06-16T21:00:00")
						.param("productId", "35455")
						.param("brandId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(38.95));
	}
}