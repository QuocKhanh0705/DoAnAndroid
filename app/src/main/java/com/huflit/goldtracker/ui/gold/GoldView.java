package com.huflit.goldtracker.ui.gold;

import com.huflit.goldtracker.data.model.gold.Gold;

import java.util.List;

public interface GoldView {

    void onLoadGoldSuccess(List<Gold> golds);

    void onLoadGoldFailed();
}
