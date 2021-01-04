package com.huflit.goldtracker.ui.gold;

import com.huflit.goldtracker.data.model.gold.BaseGold;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BasePresenter;

import java.util.List;

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
                if (response.body() != null) {
                    List<BaseGold> golds = response.body().getGolds();
                    if (golds.isEmpty()) {
                        goldView.onLoadGoldFailed();
                    } else {
                        goldView.onLoadGoldSuccess(golds.get(0).getGolds());
                    }
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
