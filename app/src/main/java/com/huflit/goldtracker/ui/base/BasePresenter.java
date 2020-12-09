package com.huflit.goldtracker.ui.base;

import com.huflit.goldtracker.data.service.ApiService;
import com.huflit.goldtracker.data.service.RetrofitService;

public class BasePresenter {

    private RetrofitService retrofitService;
    protected ApiService apiService;

    public BasePresenter() {
        retrofitService = new RetrofitService();
        apiService = retrofitService.build().create(ApiService.class);
    }
}
