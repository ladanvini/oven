package com.vini.oven.controllers.integration;

import static org.mockito.Mockito.when;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.vini.oven.entities.Oven;
import com.vini.oven.exceptions.MyCustomInternalExceptions;
import com.vini.oven.repositories.OvenRepository;
import com.vini.oven.services.OvenStateCaller;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetOvenByKeyTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OvenRepository ovenRepository;

    @MockBean
    private OvenStateCaller ovenStateCaller;

    @BeforeEach
    public void setUp() {
	ovenRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
	ovenRepository.deleteAll();
    }

    @Test
    public void testShowsCorrectOvenWhenNoChange() throws Exception {
	Oven expected_oven = new Oven("Hermoine", true, 0, 0, 0, 199);
	ovenRepository.save(expected_oven);
	ovenRepository.save(new Oven("Harry"));
	ovenRepository.save(new Oven("Ron"));

	when(ovenStateCaller.getOvenWithUpdatedModeFromOvenStateSvc(expected_oven)).thenReturn(expected_oven);

	String expected = "\nOven Key: Hermoine"
		+ "\nLights: ON\nUpper Element Temp: 0\nLower Element Temp: 0"
		+ "\nGrill Temp: 0\nFan Speed: 199\n";

	mockMvc.perform(get("/ovens/Hermoine")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

    @Test
    public void testShowsCorrectOvenWhenStateChanged() throws Exception {
	Oven test_oven = new Oven("Hermoine", true, 0, 0, 0, 199);
	ovenRepository.save(test_oven);
	ovenRepository.save(new Oven("Harry"));
	ovenRepository.save(new Oven("Ron"));
	Oven expected_oven = new Oven("Hermoine", true, 100, 10, 0, 0);
	when(ovenStateCaller.getOvenWithUpdatedModeFromOvenStateSvc(test_oven)).thenReturn(expected_oven);

	String expected = "\nOven Key: Hermoine"
		+ "\nLights: ON\nUpper Element Temp: 100\nLower Element Temp: 10"
		+ "\nGrill Temp: 0\nFan Speed: 0\n";

	mockMvc.perform(get("/ovens/Hermoine")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

    @Test
    public void testShowsOvenNotFoundWhenNoneWithKeyExist() throws Exception {
	Oven expected_oven = new Oven("Hermoine", true, 0, 0, 0, 199);
	ovenRepository.save(expected_oven);
	ovenRepository.save(new Oven("Harry"));
	ovenRepository.save(new Oven("Ron"));

	when(ovenStateCaller.getOvenWithUpdatedModeFromOvenStateSvc(expected_oven)).thenReturn(expected_oven);

	String expected = "404 Oven Not Found\n";

	mockMvc.perform(get("/ovens/Neville")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

    @Test
    public void testShowsOvenNotFoundWhenNoOvensExist() throws Exception {
	String expected = "404 Oven Not Found\n";

	mockMvc.perform(get("/ovens/Hermoine")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

    @Test
    public void testShowsServiceUnavailableWhenCallerThrows() throws Exception {
	Oven expected_oven = new Oven("Luna", true, 0, 0, 0, 199);
	ovenRepository.save(expected_oven);

	when(ovenStateCaller.getOvenWithUpdatedModeFromOvenStateSvc(expected_oven))
		.thenThrow(new MyCustomInternalExceptions("400 Bad Request", "service.cannot_receive_message"));

	String expected = "503 Service is Currently Unavailable\n";

	mockMvc.perform(get("/ovens/Luna")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

}
