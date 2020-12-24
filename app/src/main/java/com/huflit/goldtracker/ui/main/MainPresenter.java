package com.huflit.goldtracker.ui.main;

import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenter {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void getData() {
        goldApiService.getGold().enqueue(new Callback<TyGiaResponse>() {
            @Override
            public void onResponse(Call<TyGiaResponse> call, Response<TyGiaResponse> response) {
                TyGiaResponse data = response.body();
                if (data != null) {
                    mainView.onLoadDataSuccess(data);
                } else {
                    mainView.onLoadDataFailed();
                }
            }

            @Override
            public void onFailure(Call<TyGiaResponse> call, Throwable throwable) {
                mainView.onLoadDataFailed();
            }
        });
    }

    public void getCoin(){
        coinApiService.getCoin().enqueue(new Callback<List<Coin>>() {
            @Override
            public void onResponse(Call<List<Coin>> call, Response<List<Coin>> response) {
                mainView.onLoadCoinSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Coin>> call, Throwable t) {
                mainView.onLoadCoinFailed();
            }
        });
    }
}
