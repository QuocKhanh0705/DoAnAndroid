package com.huflit.goldtracker.ui.gold;

import com.huflit.goldtracker.data.model.gold.GoldResponse;
import com.huflit.goldtracker.ui.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoldPresenter extends BasePresenter {

    private GoldView goldView;

    public GoldPresenter(GoldView goldView) {
        this.goldView = goldView;
    }

    public void getGold() {
        goldApiService.getGold().enqueue(new Callback<GoldResponse>() {
            @Override
            public void onResponse(Call<GoldResponse> call, Response<GoldResponse> response) {
                GoldResponse data = response.body();
                if (data != null) {
                    goldView.onLoadGoldSuccess(data);
                } else {
                    goldView.onLoadGoldFailed();
                }
            }

            @Override
            public void onFailure(Call<GoldResponse> call, Throwable throwable) {
                goldView.onLoadGoldFailed();
            }
        });
    }
}
