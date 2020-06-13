package com.spring.demo.daos;

import com.spring.demo.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {}
