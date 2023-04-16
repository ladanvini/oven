package com.vini.oven.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vini.oven.entities.Oven;
import com.vini.oven.exceptions.MyCustomInternalExceptions;

@Service
public class OvenStateCaller {
    @Autowired
    private RestTemplate restTemplate;

    public Oven getOvenWithUpdatedModeFromOvenStateSvc(Oven oven) throws MyCustomInternalExceptions {
	oven.toJSON();
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);

	HttpEntity<String> entity = new HttpEntity<String>(oven.toJSON(), headers);

	String uri = "http://statessvc:8088/states/" + oven.getId();
	ResponseEntity<Oven> result = restTemplate.postForEntity(uri, entity, Oven.class);
	if (result.getStatusCode() == HttpStatus.OK)
	    return result.getBody();
	throw new MyCustomInternalExceptions(result.getStatusCode().toString(), "service.cannot_receive_message");

    }

}
