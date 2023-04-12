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
		String greeting = "Welcome to Oven Service!\r\n\r\n";
		greeting += "I should be running on your localhost:8080\n";
		String curl_with_paths = "Go ahead and curl me with any of the following endpoints:\n";
		curl_with_paths += "/all";
		return greeting + curl_with_paths;
	}

	@GetMapping("/all")
	public String all_ovens() {
		List<Oven> all_ovens = this.ovenService.show_all_ovens();

		String stingified_ovens = "\n";
		for (int i = 0; i < all_ovens.size(); i++) {
			stingified_ovens += all_ovens.get(i).toString();
		}
		return stingified_ovens;
	}

}
