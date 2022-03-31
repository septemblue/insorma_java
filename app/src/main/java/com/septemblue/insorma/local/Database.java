package com.septemblue.insorma.local;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.septemblue.insorma.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Database {
    // simple Account dummy data used in insorma app
    private static final Users admin = new Users("admin", "superuser", "admin");
    private static final ArrayList<Product> example = new ArrayList()
    {{
        add(new Product("SM42", R.drawable.sepatu, "Sepatu merah", 42000, 4, "sepatu lebron"));
        add(new Product("CJ25", R.drawable.cleana_jeans, "Celana Jeans", 25000, 3, "celana lembut"));
        add(new Product("BJ30", R.drawable.baju, "Baju Polos", 30000, 3, "baju rumah biasa"));
    }};

    public static MutableLiveData<HashMap<String, Users>> accounts = new MutableLiveData<>(new HashMap<String, Users>()
    {{ put("admin", admin);}});

    public static MutableLiveData<ArrayList<Product>> furnitures = new MutableLiveData<>(new ArrayList<>(example));
    private static MutableLiveData<Vector<Transaction>> transactionHistory = new MutableLiveData<>(new Vector<>());

    // has static id that will to show how many transactions already done
    private static int transId = 0;

    public static int getTransId() {
        return transId;
    }

    public static void setTransId(int transId) {
        Database.transId = transId;
    }

    public static LiveData<Vector<Transaction>> getTransactionHistory() {
        return transactionHistory;
    }

    public static LiveData<Vector<Transaction>> findUserHistory(Vector<Transaction> transactions, String username) {
        MutableLiveData<Vector<Transaction>> tempVector = new MutableLiveData<>(new Vector<>());
        for (Transaction x :
                transactions) {
            if (x.user.username.equals(username)) {
                tempVector.getValue().add(x);
                break;
            }
        }
        return tempVector;
    }
}

