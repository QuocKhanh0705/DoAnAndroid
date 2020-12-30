package com.huflit.goldtracker.ui.coin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.gold.Exchange;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.ui.exchange.ExchangeAdapter;
import com.huflit.goldtracker.utils.CurrencyUtils;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {
    private final List<Coin> coins;

    public CoinAdapter(List<Coin> coins) {
        this.coins = coins;
    }

    @NonNull
    @Override
    public CoinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.ViewHolder holder, int position) {
        holder.bind(coins.get(position));
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView tvRank;
        private final AppCompatImageView imgLogo;
        private final AppCompatTextView tvName;
        private final AppCompatTextView tvSymbol;
        private final AppCompatTextView tvPrice;
        private final AppCompatTextView tvPercent1h;
        private Coin coin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.tvRank);
            imgLogo = itemView.findViewById(R.id.imgLogo);
            tvName = itemView.findViewById(R.id.tvName);
            tvSymbol = itemView.findViewById(R.id.tvSymbol);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPercent1h = itemView.findViewById(R.id.tvPercent1h);
        }

        public void bind(Coin coin) {
            this.coin = coin;
            tvRank.setText(String.valueOf(coin.getMarketCapRank()));
            tvName.setText(coin.getName().toUpperCase());
            tvSymbol.setText(coin.getSymbol().toUpperCase());

            String percent = String.valueOf(coin.getPriceChangePercentage1hInCurrency()).substring(0,3) + "%";

            tvPrice.setText(String.valueOf(coin.getCurrentPrice()));
            tvPercent1h.setText(percent);
        }
    }

    public interface OnCoinClickListener {
        void onCoinClicked(Coin coin);
    }
}
