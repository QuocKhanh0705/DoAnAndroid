package com.huflit.goldtracker.ui.exchange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Exchange;
import com.huflit.goldtracker.utils.CurrencyUtils;

import java.util.List;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHolder> {

    private final List<Exchange> exchanges;

    public ExchangeAdapter(List<Exchange> exchanges) {
        this.exchanges = exchanges;
    }

    @NonNull
    @Override
    public ExchangeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exchange, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExchangeAdapter.ViewHolder holder, int position) {
        holder.bind(exchanges.get(position));
    }

    @Override
    public int getItemCount() {
        return exchanges.size();
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

        public void bind(Exchange exchange) {
            tvName.setText(exchange.getName());

            if (exchange.getBuy() == 0.0) {
                tvBuy.setVisibility(View.INVISIBLE);
            } else {
                tvBuy.setVisibility(View.VISIBLE);
                tvBuy.setText(currencyUtils.format(exchange.getBuy()));
            }

            if (exchange.getTransfer() == 0.0) {
                tvTransfer.setVisibility(View.INVISIBLE);
            } else {
                tvTransfer.setVisibility(View.VISIBLE);
                tvTransfer.setText(currencyUtils.format(exchange.getTransfer()));
            }


            if (exchange.getSell() == 0.0) {
                tvSell.setVisibility(View.INVISIBLE);
            } else {
                tvSell.setVisibility(View.VISIBLE);
                tvSell.setText(currencyUtils.format(exchange.getSell()));
            }
        }
    }
}
