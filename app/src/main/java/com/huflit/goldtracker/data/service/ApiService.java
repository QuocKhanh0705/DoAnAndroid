package com.huflit.goldtracker.data.service;

import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.coin.CoinResponse;
import com.huflit.goldtracker.data.model.gold.GoldResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/json.php?ran=0&rate=0&gold=1&bank=VIETCOM&date=now")
    Call<GoldResponse> getGold();

    @GET("/api/2/public/currency")
    Call<List<Coin>> getCoin();
}
