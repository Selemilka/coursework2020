package ru.edu.hse.sdfomin.HousingAndCommunalServices.repository;

import org.springframework.data.repository.CrudRepository;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Person;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Proposal;

public interface ProposalRepo extends CrudRepository<Proposal, Long> {

    Iterable<Proposal> findByPerson(Person person);

    Iterable<Proposal> findByPersonOrderByIdDesc(Person person);

    Iterable<Proposal> findAllByOrderByIdDesc();
}
