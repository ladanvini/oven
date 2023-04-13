package com.vini.oven.services.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.vini.oven.entities.Oven;
import com.vini.oven.repositories.OvenRepository;
import com.vini.oven.services.OvenService;

@SpringBootTest()
@ActiveProfiles("test")
public class ShowAllOvensStrTest {
	@Autowired
	private OvenService oven_service;
	@Autowired
	private OvenRepository oven_repository;

	@BeforeEach
	public void setUp() {
		oven_repository.deleteAll();
	}

	@Test
	public void testShowSingleEmptyOvenInCorrectStrFormat() {
		oven_repository.save(new Oven());

		List<String> actual = oven_service.showAllOvensStr();

		String light_str = "Lights: ";
		light_str += "OFF";
		String upper_str = "Upper Element Temp: " + Integer.toString(0);
		String lower_str = "Lower Element Temp:" + Integer.toString(0);
		String grill_str = "Grill Temp: " + Integer.toString(0);
		String fan_str = "Fan Speed: " + Integer.toString(0);

		List<String> expected = new ArrayList<String>();
		expected.add("\n" + light_str + "\n" + upper_str + "\n" + lower_str + "\n" + grill_str + "\n" + fan_str + "\n");

		assertEquals(expected, actual);

	}

	@Test
	public void testReturnEmptyWhenNoOvensInRepo() {

		List<String> actual = oven_service.showAllOvensStr();
		List<String> expected = new ArrayList<String>();

		assertEquals(expected, actual);
	}

	@Test
	public void testReturnCorrectListOfOvensWhenMultiple() {
		Oven test_oven_0 = new Oven(true, 0, 10, 0, 100);
		Oven test_oven_1 = new Oven(false, 32, 0, 1, 0);
		Oven test_oven_2 = new Oven();
		List<Oven> ovens_to_save = new ArrayList<Oven>();
		ovens_to_save.add(test_oven_0);
		ovens_to_save.add(test_oven_1);
		ovens_to_save.add(test_oven_2);
		oven_repository.saveAll(ovens_to_save);

		List<String> expected = new ArrayList<String>();
		expected.add(test_oven_0.toString());
		expected.add(test_oven_1.toString());
		expected.add(test_oven_2.toString());

		List<String> actual = oven_service.showAllOvensStr();
		assertEquals(expected, actual);
	}

	@AfterEach
	public void tearDown() {
		oven_repository.deleteAll();
	}

}
