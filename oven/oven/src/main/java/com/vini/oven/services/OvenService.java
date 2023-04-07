package com.vini.oven.services;

import org.springframework.stereotype.Service;

import com.vini.oven.entities.Oven;

@Service
public class OvenService {
	private Oven oven;
	public OvenService() {
		// Temporarily start with an OFF button
		this.oven = new Oven();
	}
	
	public String show_my_oven_status() {		
		return this.oven.toString();
	}
}
