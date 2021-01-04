package com.huflit.goldtracker.ui.rate;

import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BasePresenter;
import com.huflit.goldtracker.ui.exchange.ExchangeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatePresenter extends BasePresenter {

    private RateView rateView;

    public RatePresenter(RateView rateView) {
        this.rateView = rateView;
    }

    public void getExchange(String date) {
        goldApiService.getExchange(date).enqueue(new Callback<TyGiaResponse>() {
            @Override
            public void onResponse(Call<TyGiaResponse> call, Response<TyGiaResponse> response) {
                TyGiaResponse data = response.body();
                if (data != null) {
                    rateView.onLoadExchangeSuccess(data);
                } else {
                    rateView.onLoadExchangeFailed();
                }
            }

            @Override
            public void onFailure(Call<TyGiaResponse> call, Throwable t) {
                rateView.onLoadExchangeFailed();
            }
        });
    }
}
