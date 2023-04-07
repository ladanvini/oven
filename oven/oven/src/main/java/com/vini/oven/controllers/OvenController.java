package com.vini.oven.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vini.oven.services.OvenService;

@RestController
public class OvenController {
	private OvenService ovenService;
	
	public OvenController() {
		this.ovenService = new OvenService();
	}
	
	@GetMapping("/")
	public String home() {
		return "";
	}
}
