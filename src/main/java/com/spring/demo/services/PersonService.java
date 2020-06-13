package com.spring.demo.services;

import com.spring.demo.models.Person;;

import java.util.List;

public interface PersonService {
    public List<Person> getAllPersons();
    public Person getPersonById(int id);
    public void saveOrUpdate(Person person);
    public void delete(int id);
}
