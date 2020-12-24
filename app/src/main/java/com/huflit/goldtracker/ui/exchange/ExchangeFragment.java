package com.huflit.goldtracker.ui.exchange;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Exchange;
import com.huflit.goldtracker.ui.base.BaseFragment;

import java.util.List;

public class ExchangeFragment extends BaseFragment {

    private RecyclerView rvExchange;
    private ExchangeAdapter exchangeAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_exchange;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvExchange = view.findViewById(R.id.rvExchange);
        loadRate();
    }

    private void loadRate() {
        List<Exchange> exchanges = mainActivity.getRateList().get(0).getExchanges();
        exchangeAdapter = new ExchangeAdapter(exchanges);
        rvExchange.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvExchange.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvExchange.setAdapter(exchangeAdapter);
    }
}
