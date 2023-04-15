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
public class GetOvenByKeyTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private OvenRepository oven_repository;

    @BeforeEach
    public void setUp() {
	oven_repository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
	oven_repository.deleteAll();
    }

    @Test
    public void testShowsCorrectOven() throws Exception {
	Oven expected_oven = new Oven("Hermoine", true, 0, 0, 0, 199);
	oven_repository.save(expected_oven);
	oven_repository.save(new Oven("Harry"));
	oven_repository.save(new Oven("Ron"));
	String expected = "\nOven Key: Hermoine"
		+ "\nLights: ON\nUpper Element Temp: 0\nLower Element Temp: 0"
		+ "\nGrill Temp: 0\nFan Speed: 199\n";

	this.mvc.perform(get("/ovens/Hermoine")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

    @Test
    public void testShowsOvenNotFoundWhenNoneWithKeyExist() throws Exception {
	Oven expected_oven = new Oven("Hermoine", true, 0, 0, 0, 199);
	oven_repository.save(expected_oven);
	oven_repository.save(new Oven("Harry"));
	oven_repository.save(new Oven("Ron"));
	String expected = "404 Oven Not Found";

	this.mvc.perform(get("/ovens/Neville")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

    @Test
    public void testShowsOvenNotFoundWhenNoOvensExist() throws Exception {
	String expected = "404 Oven Not Found";

	this.mvc.perform(get("/ovens/Hermoine")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

}
