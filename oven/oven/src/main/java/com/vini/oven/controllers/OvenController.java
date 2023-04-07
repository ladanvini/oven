package com.vini.oven.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vini.oven.services.OvenService;

@RestController
public class OvenController {
	private final OvenService ovenService;
	
	public OvenController(OvenService overService) {
		this.ovenService = overService;
	}
	
	@GetMapping("/")
	public String home() {
		return this.ovenService.show_my_oven_status();
	}
}
