package com.vini.oven.services.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.vini.oven.entities.Oven;
import com.vini.oven.exceptions.MyCustomInternalExceptions;
import com.vini.oven.repositories.OvenRepository;
import com.vini.oven.services.OvenService;
import com.vini.oven.services.OvenStateCaller;

@SpringBootTest()
@ActiveProfiles("test")
public class GetOvenByKeyTest {
    @Autowired
    private OvenService oven_service;

    @Autowired
    private OvenRepository oven_repository;

    @MockBean
    private OvenStateCaller ovenStateCaller;

    @BeforeEach
    public void setUp() {
	oven_repository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
	oven_repository.deleteAll();
    }

    @Test
    public void testReturnsCorrectOvenWhenOnlyOne() throws MyCustomInternalExceptions {
	Oven expected = new Oven("Severus");
	oven_repository.save(expected);
	when(ovenStateCaller.getOvenWithUpdatedModeFromOvenStateSvc(expected)).thenReturn(expected);
	Oven actual = this.oven_service.getOvenByKey("Severus");
	assertEquals(expected, actual);
    }

    @Test
    public void testReturnsCorrectOven() throws MyCustomInternalExceptions {
	Oven expected = new Oven("Severus");
	oven_repository.save(expected);
	oven_repository.save(new Oven());
	when(ovenStateCaller.getOvenWithUpdatedModeFromOvenStateSvc(expected)).thenReturn(expected);
	Oven actual = oven_service.getOvenByKey("Severus");
	assertEquals(expected, actual);
    }

    @Test
    public void testRaisesCorrectlyIfNoneFound() {
	oven_repository.save(new Oven());
	Exception exception = assertThrows(MyCustomInternalExceptions.class, () -> {
	    oven_service.getOvenByKey("Severus");
	});

	String expectedMessage = "No ovens found with key Severus";
	String actualMessage = exception.getMessage();
	String expectedErrCode = "oven.not_found";
	String actualErrCode = ((MyCustomInternalExceptions) exception).getErrorCode();

	assertTrue(actualMessage.contains(expectedMessage));
	assertTrue(actualErrCode.contentEquals(expectedErrCode));

    }

}
