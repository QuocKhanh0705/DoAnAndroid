package com.huflit.goldtracker.ui.rate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Exchange;

import java.util.List;

public class CurrencyAdapter extends BaseAdapter {

    private List<Exchange> currencies;
    private LayoutInflater inflter;

    public CurrencyAdapter(Context context, List<Exchange> currencies) {
        this.currencies = currencies;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return currencies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_currency,null);
        AppCompatTextView tvCurrency = view.findViewById(R.id.tvCurrency);
        tvCurrency.setText(currencies.get(position).getName());
        return view;
    }
}
