package ru.edu.hse.sdfomin.HousingAndCommunalServices.repository;

import org.springframework.data.repository.CrudRepository;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Proposal;

public interface ProposalRepo extends CrudRepository<Proposal, Long> {

    Iterable<Proposal> findByTag(String tag);
}
