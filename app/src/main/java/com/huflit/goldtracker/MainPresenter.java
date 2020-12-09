package com.huflit.goldtracker;

import com.huflit.goldtracker.data.model.GoldResponse;
import com.huflit.goldtracker.ui.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenter {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void getGold() {
        apiService.getGold().enqueue(new Callback<GoldResponse>() {
            @Override
            public void onResponse(Call<GoldResponse> call, Response<GoldResponse> response) {
                GoldResponse data = response.body();
                if (data != null) {
                    mainView.onLoadGoldSuccess(data);
                } else {
                    mainView.onLoadGoldFailed();
                }
            }

            @Override
            public void onFailure(Call<GoldResponse> call, Throwable throwable) {
                mainView.onLoadGoldFailed();
            }
        });
    }
}
