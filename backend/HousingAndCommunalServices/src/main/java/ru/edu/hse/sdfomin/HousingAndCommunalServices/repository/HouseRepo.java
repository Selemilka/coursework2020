package ru.edu.hse.sdfomin.HousingAndCommunalServices.repository;

import org.springframework.data.repository.CrudRepository;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.House;

public interface HouseRepo extends CrudRepository<House, Long> {

}
