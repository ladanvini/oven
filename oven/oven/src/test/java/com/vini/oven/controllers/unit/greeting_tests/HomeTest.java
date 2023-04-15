package com.vini.oven.controllers.unit.greeting_tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.vini.oven.controllers.GreetingController;

@WebMvcTest(GreetingController.class)
public class HomeTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testShowsGreetings() {

	String expected = "Welcome to Oven Service!\r\n\r\n"
		+ "I should be running on your localhost:8080\n"
		+ "Go ahead and curl me with any of the following endpoints:\n"
		+ "/ovens";

	try {
	    mvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
