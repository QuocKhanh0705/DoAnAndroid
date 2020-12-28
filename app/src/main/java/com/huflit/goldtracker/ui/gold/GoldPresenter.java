package com.huflit.goldtracker.ui.gold;

import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoldPresenter extends BasePresenter {

    private GoldView goldView;

    public GoldPresenter(GoldView goldView) {
        this.goldView = goldView;
    }

    public void getGold(String date) {
        goldApiService.getGold(date).enqueue(new Callback<TyGiaResponse>() {
            @Override
            public void onResponse(Call<TyGiaResponse> call, Response<TyGiaResponse> response) {
                TyGiaResponse data = response.body();
                if (data != null) {
                    goldView.onLoadGoldSuccess(data);
                } else {
                    goldView.onLoadGoldFailed();
                }
            }

            @Override
            public void onFailure(Call<TyGiaResponse> call, Throwable t) {
                goldView.onLoadGoldFailed();
            }
        });
    }
}
