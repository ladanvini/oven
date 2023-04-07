package com.vini.oven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/")
	public String hello_world() {
		return "Hello my big bad world!";
	}
}
