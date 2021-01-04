package com.huflit.goldtracker.ui.rate;

import com.huflit.goldtracker.data.model.gold.TyGiaResponse;

public interface RateView {

    void onLoadExchangeSuccess(TyGiaResponse tyGiaResponse);

    void onLoadExchangeFailed();
}
