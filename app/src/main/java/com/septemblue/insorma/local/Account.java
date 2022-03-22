package com.septemblue.insorma.local;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static Account getAccount(HashMap<String, Account> accounts, String emailAddress) {
        Account account = null;
        for (Account x : accounts.values()) {
            if (x.emailAddress.equals(emailAddress)) {
                account = x;
                break;
            }
        }
        return account;
    }

    public static Boolean any(HashMap<String, Account> accounts, String username) {
        boolean exist = false;
        for (Account x :
                accounts.values()) {
            if (x.username.equals(username)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

}
