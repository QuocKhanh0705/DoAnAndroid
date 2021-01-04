package com.huflit.goldtracker.ui.coin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.ui.base.BaseFragment;

import java.util.List;

public class CoinFragment extends BaseFragment implements CoinAdapter.OnCoinClickListener, CoinView {

    private RecyclerView rvCoin;
    private AppCompatTextView tvPercent;
    private AppCompatTextView tvPercentPicker;
    private CoinAdapter coinAdapter;
    private CoinPresenter presenter;

    private int defaultCoinPercentage = 0;
    private int currentCoinPercentage = defaultCoinPercentage;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_bitcoin;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCoin = view.findViewById(R.id.rvCoin);
        tvPercent = view.findViewById(R.id.tvPercent);
        tvPercentPicker = view.findViewById(R.id.tvPercentPicker);

        presenter = new CoinPresenter(this);
        loadCoin(Coin.getPercentageSymbols()[defaultCoinPercentage]);
        tvPercent.setText(getPercentageTitle());

        tvPercentPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Select type");
                builder.setItems(Coin.getPercentageTitles(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        currentCoinPercentage = position;
                        tvPercent.setText(getPercentageTitle());
                        loadCoin(Coin.getPercentageSymbols()[currentCoinPercentage]);
                    }
                });
                builder.show();
            }
        });
    }

    private void loadCoin(String percentageType) {
        presenter.getCoin(percentageType);
        mainActivity.showProgress();
    }

    private String getPercentageTitle(){
        return "Percent "+Coin.getPercentageSymbols()[currentCoinPercentage];
    }

    @Override
    public void onCoinClicked(Coin coin) {
        Toast.makeText(getContext(), coin.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadCoinSuccess(List<Coin> coins) {
        mainActivity.hideProgress();
        coinAdapter = null;
        coinAdapter = new CoinAdapter(coins, currentCoinPercentage);
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
