package com.huflit.goldtracker.ui.rate;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Exchange;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RateFragment extends BaseFragment implements RateView {

    private AppCompatEditText edtAmount;
    private AppCompatTextView tvResult;
    private AppCompatSpinner spnFrom;
    private AppCompatSpinner spnTo;
    private RatePresenter presenter;

    private List<Exchange> exchanges = new ArrayList<>();
    private Exchange fromExchange;
    private Exchange toExchange;
    private double currentAmount;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_rate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtAmount = view.findViewById(R.id.edtAmount);
        tvResult = view.findViewById(R.id.tvResult);
        spnFrom = view.findViewById(R.id.spnFrom);
        spnTo = view.findViewById(R.id.spnTo);

        presenter = new RatePresenter(this);
        presenter.getExchange("");
        mainActivity.showProgress();

        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double amount = 0.0;
                if (!editable.toString().isEmpty()) {
                    amount = Double.parseDouble(editable.toString());
                }
                currentAmount = amount;
                calculateCurrency(currentAmount);
            }
        });

        spnFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                fromExchange = exchanges.get(position);
                calculateCurrency(currentAmount);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                toExchange = exchanges.get(position);
                calculateCurrency(currentAmount);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void calculateCurrency(double amount) {
        double delta = fromExchange.getSell() / toExchange.getSell();
        double result = delta * amount;
        tvResult.setText(String.format("%s %s", result, toExchange.getCode()));
    }

    @Override
    public void onLoadExchangeSuccess(TyGiaResponse tyGiaResponse) {
        mainActivity.hideProgress();
        exchanges = tyGiaResponse.getExchanges().get(0).getExchanges();

        Exchange vnd = new Exchange("VND", "VND", "1.00", "1.00", "1.00");
        exchanges.add(vnd);
        Collections.sort(exchanges, new Comparator<Exchange>() {
            @Override
            public int compare(Exchange o1, Exchange o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });

        CurrencyAdapter adapter = new CurrencyAdapter(getContext(), exchanges);
        spnFrom.setAdapter(adapter);
        spnTo.setAdapter(adapter);
        fromExchange = exchanges.get(0);
        toExchange = exchanges.get(0);
    }

    @Override
    public void onLoadExchangeFailed() {

    }
}
