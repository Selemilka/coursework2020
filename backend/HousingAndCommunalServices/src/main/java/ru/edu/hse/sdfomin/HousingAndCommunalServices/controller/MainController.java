package ru.edu.hse.sdfomin.HousingAndCommunalServices.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.House;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Proposal;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.User;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.HouseRepo;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.ProposalRepo;

import java.util.Map;

@Controller
public class MainController {

    private final ProposalRepo proposalRepo;

    private final HouseRepo houseRepo;

    public MainController(ProposalRepo proposalRepo, HouseRepo houseRepo) {
        this.proposalRepo = proposalRepo;
        this.houseRepo = houseRepo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Proposal> proposals = proposalRepo.findAll();
        Iterable<House> houses = houseRepo.findAll();
        model.put("proposals", proposals);
        model.put("houses", houses);
        return "main";
    }

    @PostMapping("add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam House house,
            Map<String, Object> model) {
        Proposal proposal = new Proposal(text, tag, user, house, house.getHouseManager());
        proposalRepo.save(proposal);

        Iterable<Proposal> proposals = proposalRepo.findAll();
        model.put("proposals", proposals);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Proposal> proposals = filter == null || filter.isBlank() ?
                proposalRepo.findAll() : proposalRepo.findByTag(filter);
        model.put("proposals", proposals);
        return "main";
    }
}
