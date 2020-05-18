package ru.edu.hse.sdfomin.HousingAndCommunalServices.model;


public enum UserRole/* implements GrantedAuthority */{
    USER;

 //   @Override
    public String getAuthority() {
        return name();
    }
}
