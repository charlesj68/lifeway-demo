package com.example.lifewaydemo.dto;

/* **********
A simple POJO provided to separate the database entity objects from the API interface objects. This version
removes the id field for cases where it would introduce security issues, i.e., POST and PUT.
********** */

public class PersonCreateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public PersonCreateDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonCreateDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
