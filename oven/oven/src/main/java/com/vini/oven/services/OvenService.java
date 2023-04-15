package com.vini.oven.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
	// TODO get oven state from other microservice
	// TODO save oven state to db
	// TODO return new oven
	return ovensByKey.get(0);
    }

    public Oven saveNewOven(Oven newOven) throws MyCustomInternalExceptions {
	try {
	    Oven savedOven = oven_repository.save(newOven);
	    if (savedOven == null)
		throw new MyCustomInternalExceptions("Could not save oven", "service.database_error");
	    return savedOven;
	} catch (Exception err) {
	    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	    System.out.println(err.getMessage());
	    System.out.println(err.toString());
	    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	    if (err instanceof DataIntegrityViolationException)
		throw new MyCustomInternalExceptions(newOven.getKey() + " already exists", "data.unique_not_respected");
	    else
		throw new MyCustomInternalExceptions(err.getLocalizedMessage(), "service.database_error");
	}
    }
}
