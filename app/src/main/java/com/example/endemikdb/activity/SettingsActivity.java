package com.example.endemikdb.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.endemikdb.R;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat switchDarkMode;
    private Spinner spinnerRegion;
    private ImageButton btnBack;
    private SharedPreferences prefs;

    private final List<String> regionList = Arrays.asList(
            "Semua", "Jawa", "Sumatra", "Kalimantan", "Sulawesi",
            "Papua", "Maluku", "Nusa Tenggara", "Bali"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences("settings", MODE_PRIVATE);

        btnBack = findViewById(R.id.btnBack);
        switchDarkMode = findViewById(R.id.switchDarkMode);
        spinnerRegion = findViewById(R.id.spinnerRegion);

        // Setup Spinner Region
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, regionList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setAdapter(adapter);

        // Load saved settings
        boolean isDark = prefs.getBoolean("dark_mode", false);
        switchDarkMode.setChecked(isDark);

        String savedRegion = prefs.getString("region", "Semua");
        int regionIndex = regionList.indexOf(savedRegion);
        if (regionIndex >= 0) spinnerRegion.setSelection(regionIndex);

        // Dark Mode Switch
        switchDarkMode.setOnCheckedChangeListener((btn, isChecked) -> {
            prefs.edit().putBoolean("dark_mode", isChecked).apply();
            AppCompatDelegate.setDefaultNightMode(
                    isChecked
                            ? AppCompatDelegate.MODE_NIGHT_YES
                            : AppCompatDelegate.MODE_NIGHT_NO
            );
        });

        // Save Region saat berubah
        spinnerRegion.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                prefs.edit().putString("region", regionList.get(position)).apply();
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        btnBack.setOnClickListener(v -> finish());
    }
}