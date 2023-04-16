package com.vini.oven.controllers.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.vini.oven.entities.Oven;
import com.vini.oven.repositories.OvenRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SaveNewOvenTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OvenRepository ovenRepository;

    @BeforeEach
    public void setUp() {
	ovenRepository.deleteAll();
    }

    private final String pathUrl = "/ovens/new";

    @AfterEach
    public void tearDown() {
	ovenRepository.deleteAll();
    }

    @Test
    public void testSaveNewOvenByKeyOnly() throws Exception {
	Oven expectedOven = new Oven("Severus");
	String expectedResp = expectedOven.toString();

	mockMvc.perform(post(pathUrl).contentType(MediaType.APPLICATION_JSON).content(expectedOven.toJSON()))
		.andExpect(status().isOk()).andExpect(content().string(expectedResp));

	List<Oven> actualOvens = ovenRepository.findAll();
	Assertions.assertEquals(1, actualOvens.size());

	Oven actualOven = actualOvens.get(0);
	Assertions.assertEquals(expectedOven, actualOven);
    }

    @Test
    public void testSaveNewOvenMultipleProperties() throws Exception {
	Oven expectedOven = new Oven("Prince", true, 12, 3, 456, 7);
	String expectedResp = expectedOven.toString();

	mockMvc.perform(post(pathUrl).contentType(MediaType.APPLICATION_JSON).content(expectedOven.toJSON()))
		.andExpect(status().isOk()).andExpect(content().string(expectedResp));

	List<Oven> actualOvens = ovenRepository.findAll();
	Assertions.assertEquals(1, actualOvens.size());

	Oven actualOven = actualOvens.get(0);
	Assertions.assertEquals(expectedOven, actualOven);
    }

    @Test
    public void testDoesNotSaveNewOvenWhenAlreadyExists() throws Exception {
	Oven existingOven = new Oven("Prince");
	ovenRepository.save(existingOven);

	Oven reqOven = new Oven("Prince", true, 12, 3, 456, 7);
	String expectedResp = "Prince already exists\n";

	mockMvc.perform(post(pathUrl).contentType(MediaType.APPLICATION_JSON).content(reqOven.toJSON()))
		.andExpect(status().isOk()).andExpect(content().string(expectedResp));

	List<Oven> actualOvens = ovenRepository.findAll();
	Assertions.assertEquals(1, actualOvens.size());

	Oven actualOven = actualOvens.get(0);
	Assertions.assertEquals(existingOven, actualOven);
    }

}
