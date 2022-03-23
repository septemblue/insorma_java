package com.septemblue.insorma.local;

import androidx.lifecycle.MutableLiveData;

import com.septemblue.insorma.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Database {
    // simple Account dummy data used in insorma app
    private static final Account admin = new Account("admin", "superuser", "admin");
    private static final ArrayList<Furniture> example = new ArrayList()
    {{
        add(new Furniture("SM42", R.drawable.sepatu, "Sepatu merah", 42000));
        add(new Furniture("CJ25", R.drawable.cleana_jeans, "Celana Jeans", 25000));
    }};

    public static MutableLiveData<HashMap<String, Account>> accounts = new MutableLiveData<>(new HashMap<String, Account>()
    {{ put("admin", admin);}});

    public static MutableLiveData<ArrayList<Furniture>> furnitures = new MutableLiveData<>(new ArrayList<>(example));
    public static MutableLiveData<Vector<History>> history = new MutableLiveData<>(new Vector<>());
}

