package ru.hse.edu.sdfomin.housingandcommunalservices.model;

import java.util.List;

public class HouseManager {

    private Long id;

    private List<House> houses;

    private List<Proposal> proposals;

    private String name;

    public HouseManager() {
    }

    public HouseManager(List<House> houses, List<Proposal> proposals, String name) {
        this.houses = houses;
        this.proposals = proposals;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
