package ru.hse.edu.sdfomin.housingandcommunalservices.model;

public class Proposal {

    private Long id;

    private String text;

    private String answer;

    private Person person;

    private ProposalStatus proposalStatus;

    private Address address;

    public Proposal() {
    }

    public Proposal(String text, String answer, Person person, Address address) {
        this.text = text;
        this.answer = answer;
        this.person = person;
        this.address = address;
        proposalStatus = ProposalStatus.PENDING;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }

    public void setProposalStatus(ProposalStatus proposalStatus) {
        this.proposalStatus = proposalStatus;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
