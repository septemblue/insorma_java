package com.septemblue.insorma.main.api;

import com.septemblue.insorma.main.dataclass.Furnitures;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FurnituresAPI {

    @GET("/v1/5f379081-2473-4494-9cc3-9e808772dc54")
    Call<Furnitures> listFurnitures();
}
