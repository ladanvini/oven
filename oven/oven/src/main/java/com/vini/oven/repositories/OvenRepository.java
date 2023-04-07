package com.vini.oven.repositories;

import org.springframework.stereotype.Repository;

import com.vini.oven.entities.Oven;

@Repository
public class OvenRepository {
	private Oven oven;
	public OvenRepository() {
		//TODO initiate oven from persistent storage
		oven = new Oven();
	}
	
	public Oven get_oven_state() {
		return this.oven;
	}
}
