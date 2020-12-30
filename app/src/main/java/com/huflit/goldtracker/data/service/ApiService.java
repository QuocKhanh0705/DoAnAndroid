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

    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&sparkline=false")
    Call<List<Coin>> getCoin(@Query("price_change_percentage") String priceChangePercentage);

    // https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=bitcoin%2Cethereum%2Cripple%2Cstellar%2Ctether%2Cchainlink%2Clitecoin%2Cbitcoin-cash%2Ccardano%2Cusd-coin&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=1h
    // https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&sparkline=false&price_change_percentage=7d
    //vsCurrency = usd
    //ids = ids=bitcoin%2Cethereum%2Cripple%2Cstellar%2Ctether%2Cchainlink%2Clitecoin%2Cbitcoin-cash%2Ccardano%2Cusd-coin
}
