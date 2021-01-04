package com.huflit.goldtracker.ui.coin;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class CoinFragment extends BaseFragment implements CoinAdapter.OnCoinClickListener, CoinView {

    private RecyclerView rvCoin;
    private AppCompatTextView tvPercent;
    private AppCompatTextView tvPercentPicker;
    private CoinAdapter coinAdapter;
    private CoinPresenter presenter;

    private int defaultCoinPercentage = 0;
    private int currentCoinPercentage = defaultCoinPercentage;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private List<Coin> coins = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_bitcoin;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        rvCoin = view.findViewById(R.id.rvCoin);
        tvPercent = view.findViewById(R.id.tvPercent);
        tvPercentPicker = view.findViewById(R.id.tvPercentPicker);

        coinAdapter = new CoinAdapter(currentCoinPercentage);
        rvCoin.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCoin.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvCoin.setAdapter(coinAdapter);

        presenter = new CoinPresenter(this);
        loadCoin(Coin.getPercentageSymbols()[defaultCoinPercentage]);
        tvPercent.setText(getPercentageTitle());

        tvPercentPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.coin_dialog_title);
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

    private String getPercentageTitle() {
        return String.format("%s %s", getString(R.string.coin_percentage), Coin.getPercentageSymbols()[currentCoinPercentage]);
    }

    @Override
    public void onCoinClicked(Coin coin) {
        Toast.makeText(getContext(), coin.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadCoinSuccess(List<Coin> coins) {
        mainActivity.hideProgress();
        this.coins = coins;
        coinAdapter = new CoinAdapter(currentCoinPercentage);
        rvCoin.setAdapter(coinAdapter);
        coinAdapter.setCoins(coins);
    }

    @Override
    public void onLoadCoinFailed() {
        Toast.makeText(getContext(), getString(R.string.message_error), Toast.LENGTH_SHORT).show();
        mainActivity.hideProgress();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainActivity.hideProgress();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) mainActivity.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    List<Coin> filterList = filter(coins, newText);
                    coinAdapter.setCoins(filterList);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private List<Coin> filter(List<Coin> coins, String query) {
        query = query.toLowerCase();
        final List<Coin> filterList = new ArrayList<>();
        for (Coin coin : coins) {
            String name = coin.getName().toLowerCase();
            String symbol = coin.getSymbol().toLowerCase();
            if (name.contains(query) || symbol.contains(query)) {
                filterList.add(coin);
            }
        }
        return filterList;
    }
}
