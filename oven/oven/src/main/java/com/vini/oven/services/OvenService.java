package com.vini.oven.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vini.oven.entities.Oven;
import com.vini.oven.repositories.OvenRepository;

@Service
public class OvenService {
	@Autowired
	private OvenRepository ovenRepo;

	public List<Oven> show_all_ovens() {
		return ovenRepo.findAll();

	}

	public void save_dummy_oven() {
		ovenRepo.save(new Oven());
		ovenRepo.save(new Oven(true, 10, 10, 0, 100));
	}
}
