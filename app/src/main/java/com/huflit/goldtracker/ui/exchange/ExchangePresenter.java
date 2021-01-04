package com.huflit.goldtracker.ui.exchange;

import com.huflit.goldtracker.data.model.gold.BaseExchange;
import com.huflit.goldtracker.data.model.gold.Exchange;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BasePresenter;

import java.util.List;

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
                if (response.body() != null) {
                    List<BaseExchange> exchanges = response.body().getExchanges();
                    if (exchanges.isEmpty()) {
                        exchangeView.onLoadExchangeFailed();
                    } else {
                        exchangeView.onLoadExchangeSuccess(exchanges.get(0).getExchanges());
                    }
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
