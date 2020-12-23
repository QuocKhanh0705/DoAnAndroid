package com.huflit.goldtracker.ui.main;

import com.huflit.goldtracker.data.model.gold.TyGiaResponse;

public interface MainView {

    void onLoadDataSuccess(TyGiaResponse tyGiaResponse);

    void onLoadDataFailed();
}
