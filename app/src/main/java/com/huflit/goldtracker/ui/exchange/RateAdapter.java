package com.huflit.goldtracker.ui.exchange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.data.model.gold.Rate;
import com.huflit.goldtracker.utils.CurrencyUtils;

import java.util.List;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {

    private final List<Rate> rates;

    public RateAdapter(List<Rate> rates) {
        this.rates = rates;
    }

    @NonNull
    @Override
    public RateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RateAdapter.ViewHolder holder, int position) {
        holder.bind(rates.get(position));
    }

    @Override
    public int getItemCount() {
        return rates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView tvName;
        private final AppCompatTextView tvBuy;
        private final AppCompatTextView tvTransfer;
        private final AppCompatTextView tvSell;
        private final CurrencyUtils currencyUtils = new CurrencyUtils();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvBuy = itemView.findViewById(R.id.tvBuy);
            tvTransfer = itemView.findViewById(R.id.tvTransfer);
            tvSell = itemView.findViewById(R.id.tvSell);
        }

        public void bind(Rate rate) {
            tvName.setText(rate.getName());
            tvBuy.setText(rate.getBuy());
            tvTransfer.setText(rate.getTransfer());
            tvSell.setText(rate.getSell());
        }
    }
}
