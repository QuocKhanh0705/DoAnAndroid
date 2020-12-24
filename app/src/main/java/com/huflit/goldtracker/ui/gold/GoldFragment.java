package com.huflit.goldtracker.ui.gold;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BaseFragment;

import java.util.List;

public class GoldFragment extends BaseFragment implements GoldAdapter.OnGoldClickListener {

    private RecyclerView rvGold;
    private GoldAdapter goldAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_gold;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvGold = view.findViewById(R.id.rvGold);
        loadGold();
    }

    private void loadGold() {
        List<Gold> golds = mainActivity.getGoldList().get(0).getGolds();
        goldAdapter = new GoldAdapter(golds,this);
        rvGold.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvGold.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvGold.setAdapter(goldAdapter);
    }

    @Override
    public void onGoldClicked(Gold gold) {
        Toast.makeText(getContext(),gold.toString(),Toast.LENGTH_LONG).show();
    }
}
