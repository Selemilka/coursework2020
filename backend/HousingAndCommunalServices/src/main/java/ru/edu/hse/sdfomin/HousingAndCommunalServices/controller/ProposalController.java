package ru.edu.hse.sdfomin.HousingAndCommunalServices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Proposal;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.ProposalStatus;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.repository.ProposalRepo;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.viewModel.ProposalView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/proposals")
public class ProposalController {

    private final ProposalRepo proposalRepo;

    public ProposalController(ProposalRepo proposalRepo) {
        this.proposalRepo = proposalRepo;
    }

    @GetMapping
    public String proposalsList(Map<String, Object> model) {
        GetProposalViewList(model);
        return "proposals";
    }

    @GetMapping(path = "{id}")
    public String getProposal(@PathVariable("id") Long id,
                              Map<String, Object> model) {
        Optional<Proposal> proposal = proposalRepo.findById(id);
        if (proposal.isEmpty()) {
            GetProposalViewList(model);
            return "proposals";
        }
        model.put("proposal", proposal.get());
        return "proposalanswer";
    }

    @PostMapping("answer")
    public String proposalAnswer(@RequestParam Long proposalId,
                                 @RequestParam String newStatus,
                                 @RequestParam String answer,
                                 Map<String, Object> model) {
        Optional<Proposal> proposal = proposalRepo.findById(proposalId);
        if (proposal.isPresent()) {
            proposal.get().setAnswer(answer);
            switch (newStatus) {
                case "1":
                    proposal.get().setProposalStatus(ProposalStatus.PENDING);
                    break;
                case "2":
                    proposal.get().setProposalStatus(ProposalStatus.ACCEPTED);
                    break;
                case "3":
                    proposal.get().setProposalStatus(ProposalStatus.REJECTED);
                    break;
            }
            proposalRepo.save(proposal.get());
        }
        GetProposalViewList(model);
        return "redirect:/proposals";
    }

    @PostMapping("status")
    public String changeStatus(@RequestParam Long proposalId,
                               @RequestParam String newStatus,
                               Map<String, Object> model) {
        Optional<Proposal> proposal = proposalRepo.findById(proposalId);
        if (proposal.isPresent()) {
            Proposal v = proposal.get();
            switch (newStatus) {
                case "1":
                    v.setProposalStatus(ProposalStatus.PENDING);
                    break;
                case "2":
                    v.setProposalStatus(ProposalStatus.ACCEPTED);
                    break;
                case "3":
                    v.setProposalStatus(ProposalStatus.REJECTED);
                    break;
            }
            proposalRepo.save(v);
        }
        GetProposalViewList(model);
        return "redirect:/proposals";
    }

    private void GetProposalViewList(Map<String, Object> model) {
        ArrayList<ProposalView> proposalViews = new ArrayList<>();
        Iterable<Proposal> proposals = proposalRepo.findAllByOrderByIdDesc();
        for (Proposal v : proposals) {
            ProposalView x = new ProposalView(v.getId(), v.getText(), v.getAnswer(), v.getPerson(), v.getAddress(), v.getProposalStatus(), "");
            switch (v.getProposalStatus()) {
                case PENDING:
                    x.setStatusClass("table-warning");
                    break;
                case ACCEPTED:
                    x.setStatusClass("table-success");
                    break;
                case REJECTED:
                    x.setStatusClass("table-danger");
                    break;
            }
            proposalViews.add(x);
        }
        model.put("proposalViews", proposalViews);
    }
}
