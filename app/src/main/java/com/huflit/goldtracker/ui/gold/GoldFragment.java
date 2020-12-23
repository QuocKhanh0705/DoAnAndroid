package com.huflit.goldtracker.ui.gold;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.data.model.gold.GoldResponse;
import com.huflit.goldtracker.ui.base.BaseFragment;

import java.util.List;

public class GoldFragment extends BaseFragment implements GoldView {

    private RecyclerView rvGold;
    private GoldAdapter goldAdapter;
    private GoldPresenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_gold;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvGold = view.findViewById(R.id.rvGold);
        presenter = new GoldPresenter(this);
        presenter.getGold();
    }

    @Override
    public void onLoadGoldSuccess(GoldResponse goldResponse) {
        List<Gold> golds = goldResponse.getGolds().get(0).getGolds();
        goldAdapter = new GoldAdapter(golds);
        rvGold.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvGold.setAdapter(goldAdapter);
    }

    @Override
    public void onLoadGoldFailed() {

    }
}
