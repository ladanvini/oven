package com.vini.oven.controllers.unit.oven_controller_tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.vini.oven.controllers.OvenController;
import com.vini.oven.services.OvenService;

@WebMvcTest(OvenController.class)
public class AllOvensTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OvenService ovenService;

    private final String pathUrl = "/ovens";

    @Test
    public void testShowsSingleOven() {

	String dummy_oven_str = "Hello World!";
	List<String> given_ovens = new ArrayList<String>();
	given_ovens.add(dummy_oven_str);
	when(ovenService.showAllOvensStr()).thenReturn(given_ovens);

	String expected = "Here are all of my ovens!\nHello World!\n";
	try {
	    mockMvc.perform(get(pathUrl)).andDo(print()).andExpect(status().isOk())
		    .andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testShowsMultipleOvens() {

	List<String> given_ovens = new ArrayList<String>();
	given_ovens.add("But Luke, ");
	given_ovens.add("I am your");
	given_ovens.add("FATHER!!");
	when(ovenService.showAllOvensStr()).thenReturn(given_ovens);

	String expected = "Here are all of my ovens!\nBut Luke, \nI am your\nFATHER!!\n";
	try {
	    mockMvc.perform(get(pathUrl)).andDo(print()).andExpect(status().isOk())
		    .andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testShowsOopsIfNoOvens() {

	List<String> given_ovens = new ArrayList<String>();
	when(ovenService.showAllOvensStr()).thenReturn(given_ovens);

	String expected = "\nOops! I have no ovens!\n";
	try {
	    mockMvc.perform(get(pathUrl)).andDo(print()).andExpect(status().isOk())
		    .andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testShowsOopsIfOvensNull() {

	when(ovenService.showAllOvensStr()).thenReturn(null);

	String expected = "\nOops! I have no ovens!\n";
	try {
	    mockMvc.perform(get(pathUrl)).andDo(print()).andExpect(status().isOk())
		    .andExpect(content().string(expected));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
