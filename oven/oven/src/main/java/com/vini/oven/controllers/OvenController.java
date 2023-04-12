package com.vini.oven.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vini.oven.entities.Oven;
import com.vini.oven.services.OvenService;

@RestController
public class OvenController {
	@Autowired
	private OvenService ovenService;

	@GetMapping("/")
	public String home() {
		ovenService.save_dummy_oven();
		List<Oven> all_ovens = this.ovenService.show_all_ovens();
		
		String stingified_ovens = "\n";
		for (int i=0; i < all_ovens.size(); i++) {
			stingified_ovens += all_ovens.get(i).toString();
		}
		return stingified_ovens;
	}
}
