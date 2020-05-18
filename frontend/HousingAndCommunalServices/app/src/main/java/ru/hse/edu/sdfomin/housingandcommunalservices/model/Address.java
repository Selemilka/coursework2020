package ru.hse.edu.sdfomin.housingandcommunalservices.model;

public class Address {

    private Long id;

    private String flatNumber;

    private String street;

    private String houseNumber;

    public Address() {
    }

    public Address(String flatNumber, String street, String houseNumber) {
        this.flatNumber = flatNumber;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
