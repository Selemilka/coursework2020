package ru.hse.edu.sdfomin.housingandcommunalservices.model;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {

    private Long id;

    private String familyName;

    private String givenName;

    private String email;

    private String mobilePhone;

    private String googleId;

    private List<House> houses;

    private List<Proposal> proposals;

    public Person() {
    }

    public Person(String familyName, String givenName, String email, String mobilePhone, String googleId, List<House> houses, List<Proposal> proposals) {
        this.familyName = familyName;
        this.givenName = givenName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.googleId = googleId;
        this.houses = houses;
        this.proposals = proposals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
