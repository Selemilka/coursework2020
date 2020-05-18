package ru.edu.hse.sdfomin.HousingAndCommunalServices.repository;

import org.springframework.data.repository.CrudRepository;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Person;

public interface PersonRepo extends CrudRepository<Person, Long> {
    Person findByGoogleId(String googleId);
}
