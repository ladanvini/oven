package com.vini.oven.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GreetingController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
	String greeting = "Welcome to Oven Service!\r\n\r\n";
	greeting += "I should be running on your localhost:8080\n";
	String curl_with_paths = "Go ahead and curl me with any of the following endpoints:\n";
	curl_with_paths += "/ovens";
	return greeting + curl_with_paths;
    }

}
