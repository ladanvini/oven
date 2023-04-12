package com.vini.oven.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vini.oven.entities.Oven;
import com.vini.oven.repositories.OvenRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class OvenServiceUnitTest {

	@Autowired
    private OvenService ovenService;
	
	@MockBean
	private OvenRepository ovenRepo;

	@Test
	public void testSimpleServiceUnitTest() throws Exception {
		String light_str = "Lights: ";		
		light_str += "ON";
		String upper_str = "Upper Element Temp: " + Integer.toString(0);
		String lower_str = "Lower Element Temp:" + Integer.toString(0);
		String grill_str = "Grill Temp: " + Integer.toString(0);
		String fan_str = "Fan Speed: " + Integer.toString(0); 
		
		String expected = ("\n" + light_str + "\n" + upper_str  + "\n" + lower_str  + "\n" + grill_str  + "\n" + fan_str + "\n");

		when(ovenRepo.get_oven_state()).thenReturn(new Oven(true, 0,0,0,0));
		
		String actual = ovenService.show_my_oven_status();
		assertEquals(actual, expected);

	}

}
