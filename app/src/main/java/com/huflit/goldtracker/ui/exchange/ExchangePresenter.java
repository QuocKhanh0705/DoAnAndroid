package com.huflit.goldtracker.ui.exchange;

import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangePresenter extends BasePresenter {

    private ExchangeView exchangeView;

    public ExchangePresenter(ExchangeView exchangeView) {
        this.exchangeView = exchangeView;
    }

    public void getExchange(String date) {
        goldApiService.getExchange(date).enqueue(new Callback<TyGiaResponse>() {
            @Override
            public void onResponse(Call<TyGiaResponse> call, Response<TyGiaResponse> response) {
                TyGiaResponse data = response.body();
                if (data != null) {
                    exchangeView.onLoadExchangeSuccess(data);
                } else {
                    exchangeView.onLoadExchangeFailed();
                }
            }

            @Override
            public void onFailure(Call<TyGiaResponse> call, Throwable t) {
                exchangeView.onLoadExchangeFailed();
            }
        });
    }
}
