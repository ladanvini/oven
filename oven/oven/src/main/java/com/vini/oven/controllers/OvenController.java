package com.vini.oven.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String allOvens() {
	List<String> all_ovens = ovenService.showAllOvensStr();
	if (all_ovens == null || all_ovens.size() == 0) {
	    return "\nOops! I have no ovens!\n";
	}
	String title = "Here are all of my ovens!";
	String stingified_ovens = "\n";
	for (int i = 0; i < all_ovens.size(); i++) {
	    stingified_ovens += (all_ovens.get(i) + "\n");
	}
	return title + stingified_ovens;
    }

}
