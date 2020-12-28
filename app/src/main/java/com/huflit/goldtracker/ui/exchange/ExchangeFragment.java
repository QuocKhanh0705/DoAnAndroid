package com.huflit.goldtracker.ui.exchange;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
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

import java.util.Calendar;
import java.util.List;

public class ExchangeFragment extends BaseFragment implements ExchangeView, DatePickerDialog.OnDateSetListener {

    private RecyclerView rvExchange;
    private AppCompatTextView tvDate;
    private ExchangeAdapter exchangeAdapter;

    private ExchangePresenter presenter;
    private Calendar exchangeCalendar;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_exchange;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvExchange = view.findViewById(R.id.rvExchange);
        tvDate = view.findViewById(R.id.tvDate);

        exchangeCalendar = mainActivity.getExchangeCalendar();
        tvDate.setText(DateUtils.getDateString(exchangeCalendar));
        loadRate();

        tvDate.setOnClickListener(new View.OnClickListener() {
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
        presenter = new ExchangePresenter(this);
        presenter.getExchange(DateUtils.getDateFormat(exchangeCalendar));
    }

    @Override
    public void onLoadExchangeSuccess(TyGiaResponse tyGiaResponse) {
        List<Exchange> exchanges = tyGiaResponse.getExchanges().get(0).getExchanges();
        exchangeAdapter = new ExchangeAdapter(exchanges);
        rvExchange.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvExchange.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvExchange.setAdapter(exchangeAdapter);
    }

    @Override
    public void onLoadExchangeFailed() {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        tvDate.setText(DateUtils.getDateString(year, monthOfYear, dayOfMonth));
        presenter.getExchange(DateUtils.getDateFormat(year, monthOfYear, dayOfMonth));
        mainActivity.setExchangeCalendar(DateUtils.getCalendar(year, monthOfYear, dayOfMonth));
    }
}
