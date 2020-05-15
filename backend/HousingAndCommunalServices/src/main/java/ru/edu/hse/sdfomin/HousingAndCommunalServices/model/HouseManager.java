package ru.edu.hse.sdfomin.HousingAndCommunalServices.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class HouseManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private List<House> houses;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id")
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
