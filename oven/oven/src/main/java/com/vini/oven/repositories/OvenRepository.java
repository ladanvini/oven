package com.vini.oven.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vini.oven.entities.Oven;

@Repository
public interface OvenRepository extends CrudRepository<Oven, Integer>{
	List<Oven> findAll();

}
