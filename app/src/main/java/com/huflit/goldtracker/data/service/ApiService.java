package com.huflit.goldtracker.data.service;

import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //json.php?column=3&cols=2&title=0&chart=1&gold=1&rate=1&expand=2&color=aa1111&nganhang=VIETCOM&fontsize=80
    @GET("/json.php")
    Call<TyGiaResponse> getGold();

    @GET("/api/2/public/currency")
    Call<List<Coin>> getCoin();
}
