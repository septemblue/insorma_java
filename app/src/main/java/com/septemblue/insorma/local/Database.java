package com.septemblue.insorma.local;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class Database {
    // simple Account dummy data used in insorma app
    private static final Account admin = new Account("admin", "superuser", "admin");
//    private static final ArrayList<Furniture> example = new ArrayList<>(Furniture());

    static MutableLiveData<ArrayList<Account>> accounts = new MutableLiveData<>(new ArrayList<>((Collection<? extends Account>) admin));
    static MutableLiveData<ArrayList<Furniture>> furnitures = new MutableLiveData<>(new ArrayList<>());
    static MutableLiveData<Vector<History>> history = new MutableLiveData<>(new Vector<>());
}
