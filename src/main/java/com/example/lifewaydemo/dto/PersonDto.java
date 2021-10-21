package com.example.lifewaydemo.dto;

/* **********
A simple POJO provided to separate the database entity objects from the API interface objects. This version
implements the id element for instances where this is needed, primarily GETs. A separate DTO is also provided
which removes the id field for cases where that would be problematic, i.e., POST and PUT.
********** */

public class PersonDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
