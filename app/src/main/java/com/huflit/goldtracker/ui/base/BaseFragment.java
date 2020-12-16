package com.huflit.goldtracker.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.huflit.goldtracker.MainActivity;

abstract public class BaseFragment extends Fragment {

    @StringRes
    protected int titleResId = -1;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected MainActivity mainActivity = (MainActivity) getActivity();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
//        mainActivity.updateTitle(titleResId);
    }
}
