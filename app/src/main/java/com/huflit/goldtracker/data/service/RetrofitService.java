package com.huflit.goldtracker.data.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private final OkHttpClient.Builder okHttpBuilder;

    public RetrofitService() {
        okHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(120L, TimeUnit.SECONDS)
                .readTimeout(120L, TimeUnit.SECONDS)
                .writeTimeout(120L, TimeUnit.SECONDS);
    }

    public Retrofit build(ApiType apiType) {
        return new Retrofit.Builder()
                .baseUrl(apiType.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpBuilder.build())
                .build();
    }
}
