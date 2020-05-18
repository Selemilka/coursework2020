package ru.hse.edu.sdfomin.housingandcommunalservices.model;

import java.util.List;

public class House {

    private Long id;

    private Address address;

    private List<Person> persons;

    private HouseManager houseManager;

    public House() {
    }

    public House(Address address, List<Person> persons, HouseManager houseManager) {
        this.address = address;
        this.persons = persons;
        this.houseManager = houseManager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> users) {
        this.persons = users;
    }

    public HouseManager getHouseManager() {
        return houseManager;
    }

    public void setHouseManager(HouseManager houseManager) {
        this.houseManager = houseManager;
    }
}
