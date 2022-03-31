package com.septemblue.insorma.local;

import java.util.HashMap;

public class Users {

    public String emailAddress;
    public String username;
    public String password;
    public String phoneNumber;

    //required data
    public Users(String emailAddress, String username, String password) {
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
    }

    public static Users getAccount(HashMap<String, Users> accounts, String emailAddress) {
        Users users = null;
        for (Users x : accounts.values()) {
            if (x.emailAddress.equals(emailAddress)) {
                users = x;
                break;
            }
        }
        return users;
    }

    public static Boolean any(HashMap<String, Users> accounts, String username) {
        boolean exist = false;
        for (Users x :
                accounts.values()) {
            if (x.username.equals(username)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

}
