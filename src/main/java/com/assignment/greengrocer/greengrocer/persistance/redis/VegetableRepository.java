package com.assignment.greengrocer.greengrocer.persistance.redis;

import org.springframework.data.repository.CrudRepository;

public interface VegetableRepository extends CrudRepository<Vegetable, String> {

}
