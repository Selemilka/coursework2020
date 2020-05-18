package ru.edu.hse.sdfomin.HousingAndCommunalServices.api;

import org.springframework.web.bind.annotation.*;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Person;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Proposal;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.PersonRepo;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.ProposalRepo;

@RestController
@RequestMapping("api/proposal")
public class ProposalRestController {

    private final ProposalRepo proposalRepo;

    private final PersonRepo personRepo;

    public ProposalRestController(ProposalRepo proposalRepo, PersonRepo personRepo) {
        this.proposalRepo = proposalRepo;
        this.personRepo = personRepo;
    }

    @GetMapping(path = "{id}")
    public Iterable<Proposal> getProposalsByGoogleId(@PathVariable("id") String id) {
        Person person = personRepo.findByGoogleId(id);
        Iterable<Proposal> proposals = proposalRepo.findByPerson(person);
        return proposalRepo.findByPerson(person);
    }

    @PostMapping
    public void addProposal(@RequestBody Proposal proposal) {
        proposalRepo.save(proposal);
    }
}
