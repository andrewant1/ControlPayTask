package com.controlpay.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile {

    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("email")
    private String email;
    @JsonProperty("age")
    private int age;
    @JsonProperty("profilePictue")
    private String profilePicture;
    @JsonProperty("userRole")
    private String userRole;

    public UserProfile(){
        super();
    }

    public UserProfile(String firstname, String lastname, String email, int age, String profilePicture, String userRole) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.profilePicture = profilePicture;
        this.userRole = userRole;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }



}
