package ru.edu.hse.sdfomin.HousingAndCommunalServices.viewModel;

import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Address;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.Person;
import ru.edu.hse.sdfomin.HousingAndCommunalServices.model.ProposalStatus;

public class ProposalView {

    private Long id;

    private String text;

    private String answer;

    private Person person;

    private Address address;

    private ProposalStatus proposalStatus;

    private String statusClass;

    public ProposalView(Long id, String text, String answer, Person person, Address address, ProposalStatus proposalStatus, String statusClass) {
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.person = person;
        this.address = address;
        this.proposalStatus = proposalStatus;
        this.statusClass = statusClass;
    }

    public ProposalView() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }

    public void setProposalStatus(ProposalStatus proposalStatus) {
        this.proposalStatus = proposalStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStatusClass() {
        return statusClass;
    }

    public void setStatusClass(String statusClass) {
        this.statusClass = statusClass;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
