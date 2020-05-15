package ru.edu.hse.sdfomin.HousingAndCommunalServices.model;

import javax.persistence.*;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_manager_id")
    private HouseManager houseManager;

    private ProposalStatus proposalStatus;

    public Proposal() {
    }

    public Proposal(String text, String tag, User user, House house, HouseManager houseManager) {
        this.text = text;
        this.tag = tag;
        this.user = user;
        this.house = house;
        this.houseManager = houseManager;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public HouseManager getHouseManager() {
        return houseManager;
    }

    public void setHouseManager(HouseManager houseManager) {
        this.houseManager = houseManager;
    }

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }

    public void setProposalStatus(ProposalStatus proposalStatus) {
        this.proposalStatus = proposalStatus;
    }
}
