package com.huflit.goldtracker.ui.base;

import com.huflit.goldtracker.data.service.ApiService;
import com.huflit.goldtracker.data.service.ApiType;
import com.huflit.goldtracker.data.service.RetrofitService;

public class BasePresenter {

    private RetrofitService retrofitService;
    protected ApiService goldApiService;
    protected ApiService coinApiService;

    public BasePresenter() {
        retrofitService = new RetrofitService();
        goldApiService = retrofitService.build(ApiType.GOLD).create(ApiService.class);
        coinApiService = retrofitService.build(ApiType.BITCOIN).create(ApiService.class);
    }
}
