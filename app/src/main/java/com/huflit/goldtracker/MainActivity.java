package com.huflit.goldtracker;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.huflit.goldtracker.data.model.Gold;
import com.huflit.goldtracker.data.model.GoldResponse;
import com.huflit.goldtracker.data.service.ApiService;
import com.huflit.goldtracker.data.service.RetrofitService;
import com.huflit.goldtracker.ui.GoldAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;
    private RecyclerView rvGold;
    private GoldAdapter goldAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvGold = findViewById(R.id.rvGold);
        mainPresenter = new MainPresenter(this);
        mainPresenter.getGold();
//
//        findViewById(R.id.btnClickMe).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mainPresenter.getGold();
//            }
//        });
    }

    @Override
    public void onLoadGoldSuccess(GoldResponse goldResponse) {
        List<Gold> golds = goldResponse.getGolds().get(0).getGolds();
        goldAdapter = new GoldAdapter(golds);
        rvGold.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        rvGold.setAdapter(goldAdapter);
    }

    @Override
    public void onLoadGoldFailed() {

    }

    public void updateTitle(@StringRes int titleResId) {
        if (titleResId != -1) {

        }
    }
}