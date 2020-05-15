package ru.edu.hse.sdfomin.HousingAndCommunalServices.repository;

import org.springframework.data.repository.CrudRepository;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Address;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
