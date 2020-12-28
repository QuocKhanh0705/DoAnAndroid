package com.huflit.goldtracker.ui.exchange;

import com.huflit.goldtracker.data.model.gold.TyGiaResponse;

public interface ExchangeView {

    void onLoadExchangeSuccess(TyGiaResponse tyGiaResponse);

    void onLoadExchangeFailed();
}
