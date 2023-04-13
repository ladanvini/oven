package com.vini.oven.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vini.oven.entities.Oven;
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

    public void save_dummy_oven() {
	oven_repository.save(new Oven());
	oven_repository.save(new Oven(true, 10, 10, 0, 100));
    }
}
