package com.vini.oven.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vini.oven.entities.Oven;
import com.vini.oven.exceptions.MyCustomInternalExceptions;
import com.vini.oven.repositories.OvenRepository;

@Service
public class OvenService {
    @Autowired
    private OvenRepository oven_repository;

    public List<String> showAllOvensStr() {
	List<Oven> all_ovens = oven_repository.findAllByOrderByIdAsc();
	List<String> all_ovens_str = new ArrayList<String>();
	for (int i = 0; i < all_ovens.size(); i++) {
	    all_ovens_str.add(all_ovens.get(i).toString());
	}
	return all_ovens_str;

    }

    public Oven getOvenByKey(String key) throws MyCustomInternalExceptions {
	List<Oven> ovensByKey = this.oven_repository.findByKey(key);
	if (ovensByKey.size() == 0)
	    throw new MyCustomInternalExceptions("No ovens found with key " + key, "oven.not_found");
	if (ovensByKey.size() > 1)
	    throw new MyCustomInternalExceptions("Oops! Something is wrong with DB!" + key,
		    "data.unique_not_respected");
	// get oven state from other micro service
	Oven oven = ovensByKey.get(0);
	Oven updatedOvenState = getOvenWithUpdatedModeFromOvenStateSvc(oven);
	// save oven state to db
	oven.setAllProperties(updatedOvenState.getLight(), updatedOvenState.getUpper_element(),
		updatedOvenState.getLower_element(), updatedOvenState.getGrill_temp(), updatedOvenState.getFan_speed());
	oven_repository.save(oven);
	// return oven with new properties based on its state
	return oven;
    }

    public Oven saveNewOven(Oven newOven) throws MyCustomInternalExceptions {
	try {
	    Oven savedOven = oven_repository.save(newOven);
	    if (savedOven == null)
		throw new MyCustomInternalExceptions("Could not save oven", "service.database_error");
	    return savedOven;
	} catch (Exception err) {
	    if (err instanceof DataIntegrityViolationException)
		throw new MyCustomInternalExceptions(newOven.getKey() + " already exists", "data.unique_not_respected");
	    else
		throw new MyCustomInternalExceptions(err.getLocalizedMessage(), "service.database_error");
	}
    }

    private Oven getOvenWithUpdatedModeFromOvenStateSvc(Oven oven) throws MyCustomInternalExceptions {
	oven.toJSON();
	RestTemplate restTemplate = new RestTemplate();
	URI uri;
	try {
	    uri = new URI("http://localhost:8088/states/" + oven.getId());
	    ResponseEntity<Oven> result = restTemplate.postForEntity(uri, oven, Oven.class);
	    return result.getBody();
	} catch (URISyntaxException e) {
	    e.printStackTrace();
	    throw new MyCustomInternalExceptions("", "");
	}

    }
}
