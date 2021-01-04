package com.huflit.goldtracker.ui.exchange;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Exchange;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BaseFragment;
import com.huflit.goldtracker.ui.gold.GoldFragment;
import com.huflit.goldtracker.utils.DateUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExchangeFragment extends BaseFragment implements ExchangeView, DatePickerDialog.OnDateSetListener {

    private RecyclerView rvExchange;
    private AppCompatTextView tvDate;
    private AppCompatButton btnDateChange;
    private ExchangeAdapter exchangeAdapter;

    private ExchangePresenter presenter;
    private Calendar exchangeCalendar;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private List<Exchange> exchanges = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_exchange;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        rvExchange = view.findViewById(R.id.rvExchange);
        btnDateChange = view.findViewById(R.id.btnDateChange);
        tvDate = view.findViewById(R.id.tvDate);

        exchangeAdapter = new ExchangeAdapter();
        rvExchange.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvExchange.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvExchange.setAdapter(exchangeAdapter);

        exchangeCalendar = mainActivity.getExchangeCalendar();
        tvDate.setText(DateUtils.getDateString(exchangeCalendar));
        loadRate();

        btnDateChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(ExchangeFragment.this,
                        exchangeCalendar.get(Calendar.YEAR), // Initial year selection
                        exchangeCalendar.get(Calendar.MONTH), // Initial month selection
                        exchangeCalendar.get(Calendar.DAY_OF_MONTH) // Initial day selection
                );
                dialog.show(getFragmentManager(), GoldFragment.class.getName());
            }
        });
    }

    private void loadRate() {
        mainActivity.showProgress();
        presenter = new ExchangePresenter(this);
        presenter.getExchange(DateUtils.getDateFormat(exchangeCalendar));
    }

    @Override
    public void onLoadExchangeSuccess(List<Exchange> exchanges) {
        mainActivity.hideProgress();
        this.exchanges = exchanges;
        exchangeAdapter.setExchanges(exchanges);
    }

    @Override
    public void onLoadExchangeFailed() {
        Toast.makeText(getContext(), getString(R.string.message_error), Toast.LENGTH_SHORT).show();
        mainActivity.hideProgress();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        tvDate.setText(DateUtils.getDateString(year, monthOfYear, dayOfMonth));
        presenter.getExchange(DateUtils.getDateFormat(year, monthOfYear, dayOfMonth));
        mainActivity.setExchangeCalendar(DateUtils.getCalendar(year, monthOfYear, dayOfMonth));
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
                    List<Exchange> filterList = filter(exchanges, newText);
                    exchangeAdapter.setExchanges(filterList);
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

    private List<Exchange> filter(List<Exchange> exchanges, String query) {
        query = query.toLowerCase();
        final List<Exchange> filterList = new ArrayList<>();
        for (Exchange exchange : exchanges) {
            String name = exchange.getName().toLowerCase();
            String code = exchange.getCode().toLowerCase();
            if (name.contains(query) || code.contains(query)) {
                filterList.add(exchange);
            }
        }
        return filterList;
    }
}
