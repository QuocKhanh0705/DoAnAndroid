package com.huflit.goldtracker.ui.coin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.coin.CoinResponse;
import com.huflit.goldtracker.ui.base.BaseFragment;
import com.huflit.goldtracker.utils.DateUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

public class CoinFragment extends BaseFragment implements CoinAdapter.OnCoinClickListener, CoinView {

    private RecyclerView rvCoin;
    private AppCompatTextView tvDate;
    private CoinAdapter coinAdapter;
    private Calendar coinCalendar;
    private CoinPresenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_bitcoin;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCoin = view.findViewById(R.id.rvCoin);
        loadCoin();
    }

    private void loadCoin() {
        presenter = new CoinPresenter(this);
        presenter.getCoin("1h");
        mainActivity.showProgress();
    }

    @Override
    public void onCoinClicked(Coin coin) {
        Toast.makeText(getContext(), coin.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadCoinSuccess(List<Coin> coins) {
        mainActivity.hideProgress();
        coinAdapter = new CoinAdapter(coins);
        rvCoin.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCoin.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvCoin.setAdapter(coinAdapter);
    }

    @Override
    public void onLoadCoinFailed() {
        mainActivity.hideProgress();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainActivity.hideProgress();
    }
}
