package com.vini.oven.controllers.unit.oven_controller_tests;

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
import com.vini.oven.services.OvenService;

@WebMvcTest(OvenController.class)
public class GetOvenByKey {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private OvenService oven_service;

    private final String path_url = "/ovens/";

    @Test
    public void testShowsCorrectOven() {
	String expected = "OVEN";
	try {
	    mvc.perform(get(this.path_url)).andDo(print()).andExpect(status().isOk())
		    .andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testShowsNotFoundWhenOvenDoesNotExist() {
	String expected = "404 Oven Not Found";

	try {
	    mvc.perform(get(this.path_url)).andDo(print()).andExpect(status().isNotFound())
		    .andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void testShowsBadRequestWhenInvalidKey() {
	String expected = "400 Invalid Oven Key";

	try {
	    mvc.perform(get(this.path_url)).andDo(print()).andExpect(status().isBadRequest())
		    .andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void testShowsUnavailableIfCantGetStateFromOtherMicroservice() {
	String expected = "503 Service is Currently Unavailable";

	try {
	    mvc.perform(get(this.path_url)).andDo(print()).andExpect(status().isServiceUnavailable())
		    .andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}
