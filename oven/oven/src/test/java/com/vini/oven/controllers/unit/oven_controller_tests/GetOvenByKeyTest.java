package com.vini.oven.controllers.unit.oven_controller_tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.vini.oven.controllers.OvenController;
import com.vini.oven.entities.Oven;
import com.vini.oven.exceptions.MyCustomInternalExceptions;
import com.vini.oven.services.OvenService;

@WebMvcTest(OvenController.class)
public class GetOvenByKeyTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OvenService ovenService;

    private final String pathUrl = "/ovens/";

    @Test
    public void testShowsCorrectOven() throws Exception {
	String oven_key = "Ignatious";
	Oven test_oven = new Oven(oven_key, true, 10, 10, 0, 100);

	when(ovenService.getOvenByKey(oven_key)).thenReturn(test_oven);

	String expected = test_oven.toString();

	mockMvc.perform(get(pathUrl + oven_key)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));

    }

    @Test
    public void testShowsNotFoundWhenOvenDoesNotExist() throws Exception {
	String oven_key = "Ignatious";
	when(ovenService.getOvenByKey(oven_key)).thenThrow(new MyCustomInternalExceptions("msg", "oven.not_found"));

	String expected = "404 Oven Not Found\n";

	mockMvc.perform(get(pathUrl + oven_key)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

    @Test
    public void testShowsOvenNotFoundWhenOvenNull() throws Exception {
	String expected = "404 Oven Not Found\n";

	mockMvc.perform(get(pathUrl + "true")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

    @Test
    public void testShowsUnavailableIfCantGetStateFromOtherMicroservice() throws Exception {
	String oven_key = "Ignatious";

	when(ovenService.getOvenByKey(oven_key))
		.thenThrow(new MyCustomInternalExceptions("msg", "service.cannot_receive_message"));

	String expected = "503 Service is Currently Unavailable\n";

	mockMvc.perform(get(pathUrl + oven_key)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }
}
