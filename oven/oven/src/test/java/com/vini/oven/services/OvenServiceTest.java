package com.vini.oven.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class OvenServiceTest {
	@Autowired
    private OvenService ovenService;

	@Test
	public void testIntegrationEmptyOven() {
		String actual = ovenService.show_my_oven_status();
		String light_str = "Lights: ";		
		light_str += "OFF";
		String upper_str = "Upper Element Temp: " + Integer.toString(0);
		String lower_str = "Lower Element Temp:" + Integer.toString(0);
		String grill_str = "Grill Temp: " + Integer.toString(0);
		String fan_str = "Fan Speed: " + Integer.toString(0); 
		
		String expected = ("\n" + light_str + "\n" + upper_str  + "\n" + lower_str  + "\n" + grill_str  + "\n" + fan_str + "\n");
		
		assertEquals(actual,expected);
	}

}
