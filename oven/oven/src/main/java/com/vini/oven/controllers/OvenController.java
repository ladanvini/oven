package com.vini.oven.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vini.oven.entities.Oven;
import com.vini.oven.exceptions.MyCustomInternalExceptions;
import com.vini.oven.services.OvenService;

@RestController
@RequestMapping("/ovens")
public class OvenController {
    @Autowired
    private OvenService ovenService;

    @RequestMapping(method = RequestMethod.GET)
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

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String getOvenByKey(@PathVariable("key") String key) {
	try {
	    Oven oven = this.ovenService.getOvenByKey(key);
	    if (oven == null)
		return "404 Oven Not Found\n";
	    return oven.toString();
	} catch (MyCustomInternalExceptions err) {
	    if (err.getErrorCode().contentEquals("oven.not_found"))
		return "404 Oven Not Found\n";
	    if (err.getErrorCode().contentEquals("data.unique_not_respected"))
		return "500 Server Error\n";
	    if (err.getErrorCode().contentEquals("service.cannot_receive_message"))
		return "503 Service is Currently Unavailable\n";
	    return "Oops! Something is not right here...\n" + err.getLocalizedMessage();
	}
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveNewOven(@RequestBody Oven newOven) {
	try {
	    Oven saved = ovenService.saveNewOven(newOven);
	    if (saved == null)
		return "Something very bad and unexpected happened!\n";
	    return saved.toString();
	} catch (MyCustomInternalExceptions err) {
	    return err.getMessage();
	}

    }

}
