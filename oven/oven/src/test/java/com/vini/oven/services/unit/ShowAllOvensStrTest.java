package com.vini.oven.services.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vini.oven.entities.Oven;
import com.vini.oven.repositories.OvenRepository;
import com.vini.oven.services.OvenService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ShowAllOvensStrTest {
	@Autowired
	private OvenService oven_service;

	@MockBean
	private OvenRepository oven_repository;

	@Test
	public void testReturnsOneCustomOven() throws Exception {
		Oven test_oven = new Oven(true, 0, 10, 0, 100);
		List<Oven> repo_oven_list = new ArrayList<Oven>();
		repo_oven_list.add(test_oven);
		List<String> expected = new ArrayList<String>();
		expected.add(test_oven.toString());

		when(oven_repository.findAll()).thenReturn(repo_oven_list);

		List<String> actual = oven_service.showAllOvensStr();
		assertEquals(expected, actual);
	}

	@Test
	public void testReturnsEmptyListWhenNoOvens() throws Exception {
		List<String> expected = new ArrayList<String>();

		when(oven_repository.findAll()).thenReturn(new ArrayList<Oven>());

		List<String> actual = oven_service.showAllOvensStr();
		assertEquals(expected, actual);
	}

	@Test
	public void testReturnsMultipleOvens() throws Exception {
		Oven test_oven_0 = new Oven(true, 0, 10, 0, 100);
		Oven test_oven_1 = new Oven(false, 32, 0, 1, 0);
		Oven test_oven_2 = new Oven();
		List<Oven> repo_oven_list = new ArrayList<Oven>();
		repo_oven_list.add(test_oven_0);
		repo_oven_list.add(test_oven_1);
		repo_oven_list.add(test_oven_2);

		List<String> expected = new ArrayList<String>();
		expected.add(test_oven_0.toString());
		expected.add(test_oven_1.toString());
		expected.add(test_oven_2.toString());

		when(oven_repository.findAll()).thenReturn(repo_oven_list);

		List<String> actual = oven_service.showAllOvensStr();
		assertEquals(expected, actual);
	}

}
