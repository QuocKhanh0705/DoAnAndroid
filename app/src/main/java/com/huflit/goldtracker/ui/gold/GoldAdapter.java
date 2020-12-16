package com.huflit.goldtracker.ui.gold;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.utils.CurrencyUtils;

import java.util.List;

public class GoldAdapter extends RecyclerView.Adapter<GoldAdapter.ViewHolder> {

    private final List<Gold> golds;

    public GoldAdapter(List<Gold> golds) {
        this.golds = golds;
    }

    @NonNull
    @Override
    public GoldAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gold, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoldAdapter.ViewHolder holder, int position) {
        holder.bind(golds.get(position));
    }

    @Override
    public int getItemCount() {
        return golds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatTextView tvName;
        private final AppCompatTextView tvBuy;
        private final AppCompatTextView tvSell;
        private final CurrencyUtils currencyUtils = new CurrencyUtils();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvBuy = itemView.findViewById(R.id.tvBuy);
            tvSell = itemView.findViewById(R.id.tvSell);
        }

        public void bind(Gold gold) {
            String name = gold.getCompany() + " " + gold.getBrand();
            tvName.setText(name);
            tvBuy.setText(gold.getBuy());
            tvSell.setText(gold.getSell());
        }
    }
}
