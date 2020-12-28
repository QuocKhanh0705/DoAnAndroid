package com.huflit.goldtracker.data.service;

import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/json.php?&nganhang=VIETCOM&gold=1&rate=0")
    Call<TyGiaResponse> getGold(@Query("ngay") String date);

    @GET("/json.php?&nganhang=VIETCOM&gold=0&rate=1")
    Call<TyGiaResponse> getExchange(@Query("ngay") String date);

    @GET("/api/2/public/currency")
    Call<List<Coin>> getCoin();
}
