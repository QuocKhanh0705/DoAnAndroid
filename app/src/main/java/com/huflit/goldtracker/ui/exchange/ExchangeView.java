package com.huflit.goldtracker.ui.exchange;

import com.huflit.goldtracker.data.model.gold.Exchange;

import java.util.List;

public interface ExchangeView {

    void onLoadExchangeSuccess(List<Exchange> exchanges);

    void onLoadExchangeFailed();
}
