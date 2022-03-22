package com.septemblue.insorma.local;

public class Account {

    public String emailAddress;
    public String username;
    public String password;
    public String phoneNumber;

    //required data
    public Account(String emailAddress, String username, String password) {
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
    }


}
