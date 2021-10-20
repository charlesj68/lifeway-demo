package com.example.lifewaydemo.model;

import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public Person(String firstName, String lastName, String email, String phone) {
        EmailValidator emailValidator = EmailValidator.getInstance();

        this.firstName = firstName.trim();
        if (this.firstName.equals("")) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.lastName = lastName.trim();
        if (this.lastName.equals("")) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.email = email.trim();
        if (!emailValidator.isValid(this.email)) {
            throw new IllegalArgumentException("Provided email is invalid");
        }
        // TODO: Develop a phone number validator
        this.phone = phone.trim();
    }

    public Person() {

    }

    public long getId() {
        return id;
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

    public void setPhone(String newPhone) {
        this.phone = newPhone.trim();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}