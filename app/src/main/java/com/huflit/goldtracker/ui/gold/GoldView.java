package com.huflit.goldtracker.ui.gold;

import com.huflit.goldtracker.data.model.gold.TyGiaResponse;

public interface GoldView {

    void onLoadGoldSuccess(TyGiaResponse tyGiaResponse);

    void onLoadGoldFailed();
}
