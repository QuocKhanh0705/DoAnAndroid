package com.huflit.goldtracker.ui.gold;

import com.huflit.goldtracker.data.model.gold.GoldResponse;

public interface GoldView {

    void onLoadGoldSuccess(GoldResponse goldResponse);

    void onLoadGoldFailed();
}
