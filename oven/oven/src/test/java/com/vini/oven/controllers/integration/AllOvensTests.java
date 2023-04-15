package com.vini.oven.controllers.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.vini.oven.entities.Oven;
import com.vini.oven.repositories.OvenRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AllOvensTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private OvenRepository oven_repository;

    @BeforeEach
    public void setUp() {
	oven_repository.deleteAll();
    }

    @Test
    public void testShowsSingleOvenCorrectStrFormat() throws Exception {
	oven_repository.save(new Oven("Snape"));
	String expected = "Here are all of my ovens!\n"
		+ "\nOven Key: Snape"
		+ "\nLights: OFF\nUpper Element Temp: 0\nLower Element Temp: 0"
		+ "\nGrill Temp: 0\nFan Speed: 0\n\n";

	this.mvc.perform(get("/ovens")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(expected));
    }

    @Test
    public void testShowsMultipleOvensCorrectStrFormat() throws Exception {
	oven_repository.save(new Oven("Grindelwald"));
	oven_repository.save(new Oven("Antioch", true, 0, 10, 10, 100));
	oven_repository.save(new Oven("Cadmus", false, 10, 234, 5, 6));
	oven_repository.save(new Oven("Ignotus"));
	String expected = "Here are all of my ovens!\n"
		+ "\nOven Key: Grindelwald"
		+ "\nLights: OFF\nUpper Element Temp: 0\nLower Element Temp: 0"
		+ "\nGrill Temp: 0\nFan Speed: 0\n\n";
	expected += "\nOven Key: Antioch"
		+ "\nLights: ON\nUpper Element Temp: 0\nLower Element Temp: 10"
		+ "\nGrill Temp: 10\nFan Speed: 100\n\n";
	expected += "\nOven Key: Cadmus"
		+ "\nLights: OFF\nUpper Element Temp: 10\nLower Element Temp: 234"
		+ "\nGrill Temp: 5\nFan Speed: 6\n\n";
	expected += "\nOven Key: Ignotus"
		+ "\nLights: OFF\nUpper Element Temp: 0\nLower Element Temp: 0"
		+ "\nGrill Temp: 0\nFan Speed: 0\n\n";

	this.mvc.perform(get("/ovens")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(expected));
    }

    @Test
    public void testShowsOopsIfNoOvens() throws Exception {
	String expected = "\nOops! I have no ovens!\n";
	mvc.perform(get("/ovens")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(expected));

    }

    @AfterEach
    public void tearDown() {
	oven_repository.deleteAll();
    }

}
