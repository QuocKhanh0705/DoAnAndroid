package com.huflit.goldtracker.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.huflit.goldtracker.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}