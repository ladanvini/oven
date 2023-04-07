package com.vini.oven.controllers;

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

import com.vini.oven.services.OvenService;

@WebMvcTest(OvenController.class)
public class OvenControllerUnitTest {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private OvenService ovenService;

    @Test
    public void testWithMockedService() throws Exception {
		
		String expected = "Hello world";
		when(ovenService.show_my_oven_status()).thenReturn("YO HELLO");

    	this.mvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }

}
