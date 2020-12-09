package com.huflit.goldtracker.data.service;

import com.huflit.goldtracker.data.model.GoldResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/json.php?ran=0&rate=0&gold=1&bank=VIETCOM&date=now")
    Call<GoldResponse> getGold();
}
