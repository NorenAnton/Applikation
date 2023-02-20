package com.miun.applikation;

public class User {

    private String firstName, lastName;
    private int personId;

    public User(String firstName, String lastName, int personId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personId = personId;
    }

    public String getName() {
        return firstName + " " + lastName;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
