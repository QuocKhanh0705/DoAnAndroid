package com.huflit.goldtracker.ui.coin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.coin.Coin;
import com.huflit.goldtracker.data.model.gold.Exchange;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.ui.exchange.ExchangeAdapter;
import com.huflit.goldtracker.utils.CurrencyUtils;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder>  {
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

        private final AppCompatTextView tvName;
        private final AppCompatTextView tvId;
        private final AppCompatTextView tvCurrentPrice;
        private final AppCompatTextView tvPriceChangePercentage;
        private Coin coin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCurrentPrice = itemView.findViewById(R.id.tvPriceCurrent);
            tvId = itemView.findViewById(R.id.tvType);
            tvPriceChangePercentage = itemView.findViewById(R.id.tvPriceChangePercentage);
        }

        public void bind(Coin coin) {
            this.coin = coin;
            String name = coin.getFullName() + " " + coin.getId();
            tvName.setText(name);
            tvId.setText(coin.getId());
            tvCurrentPrice.setText(coin.getCurrent_Price());
            tvPriceChangePercentage.setText(coin.getChange_Price_24h_Percentage());
        }
    }
    public interface OnCoinClickListener {
        void onCoinClicked(Coin coin);
    }
}
