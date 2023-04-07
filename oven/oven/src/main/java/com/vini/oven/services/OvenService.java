package com.vini.oven.services;

import com.vini.oven.entities.Oven;

public class OvenService {
	private Oven oven;
	public OvenService() {
		// Temporarily start with an OFF button
		this.oven = new Oven();
	}
}
