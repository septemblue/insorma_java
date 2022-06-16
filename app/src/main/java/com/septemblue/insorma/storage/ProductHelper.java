package com.septemblue.insorma.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


public class ProductHelper {
    private final DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public static ArrayList<ProductModel> products = new ArrayList<>();

    public ProductHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void insertProduct(ProductModel product) {

        db = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("productName", product.productName);
            contentValues.put("productPrice", product.productPrice);
            contentValues.put("productImage", product.productImage);
            contentValues.put("productDescription", product.productDescription);
            Log.i("produk", product.toString());

            db.insert("PRODUCT_TABLE", null, contentValues);
            updateProductList();
    }

    public void updateProductList() {

        String getAllAccountsQuery = "SELECT * FROM PRODUCT_TABLE";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(getAllAccountsQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int productID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                String productName = cursor.getString(cursor.getColumnIndexOrThrow("productName"));
                String productPrice = cursor.getString(cursor.getColumnIndexOrThrow("productPrice"));
                String productImage = cursor.getString(cursor.getColumnIndexOrThrow("productImage"));
                String productDescription = cursor.getString(cursor.getColumnIndexOrThrow("productDescription"));

                ProductModel product = new ProductModel(productName, productPrice, productImage, productDescription);
                product.productID = productID;
                products.add(product);

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }

        cursor.close();
        db.close();
    }

}
