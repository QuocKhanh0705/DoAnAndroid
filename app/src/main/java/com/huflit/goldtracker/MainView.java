package com.huflit.goldtracker;

import com.huflit.goldtracker.data.model.GoldResponse;

public interface MainView {

    void onLoadGoldSuccess(GoldResponse goldResponse);

    void onLoadGoldFailed();
}
