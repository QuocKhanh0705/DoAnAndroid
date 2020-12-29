package com.huflit.goldtracker.ui.gold;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BaseFragment;
import com.huflit.goldtracker.utils.DateUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

public class GoldFragment extends BaseFragment
        implements GoldAdapter.OnGoldClickListener, DatePickerDialog.OnDateSetListener, GoldView {

    private RecyclerView rvGold;
    private AppCompatTextView tvDate;
    private GoldAdapter goldAdapter;
    private GoldPresenter presenter;
    private Calendar goldCalendar;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_gold;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvGold = view.findViewById(R.id.rvGold);
        tvDate = view.findViewById(R.id.tvDate);

        loadCalendar();
        loadGold();

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(GoldFragment.this,
                        goldCalendar.get(Calendar.YEAR), // Initial year selection
                        goldCalendar.get(Calendar.MONTH), // Initial month selection
                        goldCalendar.get(Calendar.DAY_OF_MONTH) // Initial day selection
                );
                dialog.show(getFragmentManager(), GoldFragment.class.getName());
            }
        });
    }

    private void loadCalendar() {
        goldCalendar = mainActivity.getGoldCalendar();
        tvDate.setText(DateUtils.getDateString(goldCalendar));
    }

    private void loadGold() {
        presenter = new GoldPresenter(this);
        presenter.getGold(DateUtils.getDateFormat(goldCalendar));
        mainActivity.showProgress();
    }

    @Override
    public void onGoldClicked(Gold gold) {
        Toast.makeText(getContext(), gold.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        tvDate.setText(DateUtils.getDateString(year, monthOfYear, dayOfMonth));
        presenter.getGold(DateUtils.getDateFormat(year, monthOfYear, dayOfMonth));
        mainActivity.setGoldCalendar(DateUtils.getCalendar(year, monthOfYear, dayOfMonth));
    }

    @Override
    public void onLoadGoldSuccess(TyGiaResponse tyGiaResponse) {
        mainActivity.hideProgress();
        List<Gold> golds = tyGiaResponse.getGolds().get(0).getGolds();
        goldAdapter = new GoldAdapter(golds, this);
        rvGold.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvGold.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvGold.setAdapter(goldAdapter);
    }

    @Override
    public void onLoadGoldFailed() {
        mainActivity.hideProgress();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainActivity.hideProgress();
    }
}
