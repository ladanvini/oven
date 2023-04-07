package com.vini.oven.services;

import org.springframework.stereotype.Service;

import com.vini.oven.repositories.OvenRepository;

@Service
public class OvenService {
	private final OvenRepository ovenRepo;
	public OvenService(OvenRepository ovenRepo) {
		this.ovenRepo = ovenRepo;
	}
	
	public String show_my_oven_status() {		
		return ovenRepo.get_oven_state().toString();
	}
}
