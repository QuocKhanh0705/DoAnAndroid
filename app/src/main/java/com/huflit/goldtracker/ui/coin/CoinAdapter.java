package com.huflit.goldtracker.ui.coin;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.gold.Exchange;
import com.huflit.goldtracker.utils.CurrencyUtils;

import java.util.ArrayList;
import java.util.List;


public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {

    private List<Coin> coins = new ArrayList<>();
    private int percentageType;

    public CoinAdapter(int percentageType) {
        this.percentageType = percentageType;
    }

    @NonNull
    @Override
    public CoinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.ViewHolder holder, int position) {
        holder.bind(coins.get(position), percentageType);
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
        private final AppCompatTextView tvPercent;
        private Coin coin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.tvRank);
            imgLogo = itemView.findViewById(R.id.imgLogo);
            tvName = itemView.findViewById(R.id.tvName);
            tvSymbol = itemView.findViewById(R.id.tvSymbol);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPercent = itemView.findViewById(R.id.tvPercent);
        }

        public void bind(Coin coin, int percentageType) {
            this.coin = coin;
            tvRank.setText(String.valueOf(coin.getMarketCapRank()));
            tvName.setText(coin.getName().toUpperCase());
            tvSymbol.setText(coin.getSymbol().toUpperCase());
            Glide.with(itemView).load(coin.getImage()).into(imgLogo);

            tvPrice.setText(CurrencyUtils.formatFullDigit(coin.getCurrentPrice()));

            String percent = CurrencyUtils.percentFormat(getPercentage(percentageType));
            tvPercent.setText(percent);
            if (getPercentage(percentageType) >= 0) {
                tvPercent.setTextColor(Color.GREEN);
            } else {
                tvPercent.setTextColor(Color.RED);
            }
        }

        private double getPercentage(int percentageType) {
            double percent;
            switch (Coin.getPercentageSymbols()[percentageType]) {
                case "1h":
                    percent = coin.getPriceChangePercentage1hInCurrency();
                    break;
                case "24h":
                    percent = coin.getPriceChangePercentage24hInCurrency();
                    break;
                case "7d":
                    percent = coin.getPriceChangePercentage7dInCurrency();
                    break;
                case "30d":
                    percent = coin.getPriceChangePercentage30dInCurrency();
                    break;
                case "1y":
                    percent = coin.getPriceChangePercentage1yInCurrency();
                    break;
                default:
                    percent = 0.0;
                    break;
            }
            return percent;
        }
    }

    public interface OnCoinClickListener {
        void onCoinClicked(Coin coin);
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
        notifyDataSetChanged();
    }
}
