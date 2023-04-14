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
	oven_repository.save(new Oven("Dumbledore"));

	List<String> actual = oven_service.showAllOvensStr();

	String expected_oven_str = "\nOven Key: Dumbledore\n"
		+ "Lights: OFF\n"
		+ "Upper Element Temp: 0\n"
		+ "Lower Element Temp: 0\n"
		+ "Grill Temp: 0\n"
		+ "Fan Speed: 0\n";

	List<String> expected = new ArrayList<String>();
	expected.add(expected_oven_str);

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
	Oven test_oven_0 = new Oven("Fineas", true, 0, 10, 0, 100);
	Oven test_oven_1 = new Oven("Regulus", false, 32, 0, 1, 0);
	Oven test_oven_2 = new Oven("Dumbledore");
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
