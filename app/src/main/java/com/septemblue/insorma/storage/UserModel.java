package com.septemblue.insorma.storage;

import androidx.annotation.NonNull;

public class UserModel {

    public int UserID;
    public String emailAddress;
    public String username;
    public String password;
    public String phoneNumber;


    // constructor
    public UserModel(String emailAddress, String username, String password, String phoneNumber) {
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public UserModel() {
    }

    // toString
    @NonNull
    @Override
    public String toString() {
        return "UserModel{" +
                "emailAddress='" + emailAddress + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    // getter and setter
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
