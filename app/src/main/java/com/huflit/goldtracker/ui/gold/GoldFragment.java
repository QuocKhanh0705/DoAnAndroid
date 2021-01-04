package com.huflit.goldtracker.ui.gold;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huflit.goldtracker.R;
import com.huflit.goldtracker.data.model.gold.Gold;
import com.huflit.goldtracker.data.model.gold.TyGiaResponse;
import com.huflit.goldtracker.ui.base.BaseFragment;
import com.huflit.goldtracker.utils.DateUtils;
import com.huflit.goldtracker.utils.StringUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GoldFragment extends BaseFragment
        implements GoldAdapter.OnGoldClickListener, DatePickerDialog.OnDateSetListener, GoldView {

    private RecyclerView rvGold;
    private AppCompatTextView tvDate;
    private GoldAdapter goldAdapter;
    private GoldPresenter presenter;
    private Calendar goldCalendar;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private List<Gold> golds = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_gold;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        rvGold = view.findViewById(R.id.rvGold);
        tvDate = view.findViewById(R.id.tvDate);

        goldAdapter = new GoldAdapter(this);
        rvGold.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvGold.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvGold.setAdapter(goldAdapter);

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
    public void onLoadGoldSuccess(List<Gold> golds) {
        mainActivity.hideProgress();
        this.golds = golds;
        goldAdapter.setGolds(golds);
    }

    @Override
    public void onLoadGoldFailed() {
        Toast.makeText(getContext(), getString(R.string.message_error), Toast.LENGTH_SHORT).show();
        mainActivity.hideProgress();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainActivity.hideProgress();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) mainActivity.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    List<Gold> filterList = filter(golds, newText);
                    goldAdapter.setGolds(filterList);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private List<Gold> filter(List<Gold> golds, String query) {
        query = query.toLowerCase();
        final List<Gold> filterList = new ArrayList<>();
        for (Gold gold : golds) {
            String name = StringUtils.removeAccent(gold.getBrand().toLowerCase());
            String company = StringUtils.removeAccent(gold.getCompany().toLowerCase());
            if (name.contains(query) || company.contains(query)) {
                filterList.add(gold);
            }
        }
        return filterList;
    }
}
