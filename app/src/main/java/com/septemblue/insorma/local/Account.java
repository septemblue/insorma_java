package com.septemblue.insorma.local;

public class Account {

    String emailAddress;
    String username;
    String password;
    String phoneNumber;

    //required data
    public Account(String emailAddress, String username, String password) {
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
    }


}
