package com.vini.ovenstate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vini.ovenstate.entities.Oven;
import com.vini.ovenstate.services.StateService;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Oven getOvenState(@PathVariable("id") int id, @RequestBody Oven oven) {

	return this.stateService.getCurrentOvenState(oven);
    }

}
