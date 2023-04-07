package com.vini.oven.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.vini.oven.services.OvenService;


@SpringBootTest
@AutoConfigureMockMvc
public class OvenControllerTests {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private OvenService ovenService;
    
    @Test
    public void itegrationTest() throws Exception {
		String light_str = "Lights: ";		
		light_str += "OFF";
		String upper_str = "Upper Element Temp: " + Integer.toString(0);
		String lower_str = "Lower Element Temp:" + Integer.toString(0);
		String grill_str = "Grill Temp: " + Integer.toString(0);
		String fan_str = "Fan Speed: " + Integer.toString(0); 
		
		String expected = ("\n" + light_str + "\n" + upper_str  + "\n" + lower_str  + "\n" + grill_str  + "\n" + fan_str + "\n");

    	this.mvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(expected));
    }


}
