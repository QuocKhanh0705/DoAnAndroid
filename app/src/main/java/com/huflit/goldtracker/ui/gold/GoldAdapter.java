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

    protected final List<Gold> golds;
    private OnGoldClickListener listener;

    public GoldAdapter(List<Gold> golds, OnGoldClickListener listener) {
        this.golds = golds;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GoldAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gold, parent, false);
        return new ViewHolder(view, listener);
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
        private final AppCompatTextView tvType;
        private final AppCompatTextView tvBuy;
        private final AppCompatTextView tvSell;
        private final CurrencyUtils currencyUtils = new CurrencyUtils();
        private Gold gold;

        public ViewHolder(@NonNull View itemView, OnGoldClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            tvBuy = itemView.findViewById(R.id.tvBuy);
            tvSell = itemView.findViewById(R.id.tvSell);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onGoldClicked(gold);
                }
            });
        }


        public void bind(Gold gold) {
            this.gold = gold;
            String name = gold.getCompany() + " " + gold.getBrand();
            tvName.setText(name);
            tvType.setText(gold.getType());
            tvBuy.setText(currencyUtils.format(gold.getBuy(), currencyUtils.CURRENCY_MIL_UNIT));
            tvSell.setText(currencyUtils.format(gold.getSell(), currencyUtils.CURRENCY_MIL_UNIT));
        }
    }

    public interface OnGoldClickListener {
        void onGoldClicked(Gold gold);
    }
}
