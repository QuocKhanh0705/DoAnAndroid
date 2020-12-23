package com.huflit.goldtracker.ui.exchange;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Rate;
import com.huflit.goldtracker.ui.base.BaseFragment;

import java.util.List;

public class ExchangeFragment extends BaseFragment {

    private RecyclerView rvRate;
    private RateAdapter rateAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_exchange;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRate = view.findViewById(R.id.rvRate);
        loadRate();
    }

    private void loadRate() {
        List<Rate> rates = mainActivity.getRateList().get(0).getRates();
        rateAdapter = new RateAdapter(rates);
        rvRate.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvRate.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvRate.setAdapter(rateAdapter);
    }
}
