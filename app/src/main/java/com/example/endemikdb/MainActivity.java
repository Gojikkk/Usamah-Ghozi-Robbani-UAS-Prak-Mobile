package com.example.endemikdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.endemikdb.R;
import com.example.endemikdb.database.DataSeeder;
import com.example.endemikdb.fragment.HewanFragment;
import com.example.endemikdb.fragment.TumbuhanFragment;

public class MainActivity extends AppCompatActivity {

    private LinearLayout navHewan, navTumbuhan;
    private ImageButton btnSearch, btnFavorit, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Seed data
        DataSeeder.seedData(this);

        // Inisialisasi View
        navHewan = findViewById(R.id.navHewan);
        navTumbuhan = findViewById(R.id.navTumbuhan);
        btnSearch = findViewById(R.id.btnSearch);
        btnFavorit = findViewById(R.id.btnFavorit);
        btnSettings = findViewById(R.id.btnSettings);

        // Fragment default
        loadFragment(new HewanFragment());

        // Navigasi
        navHewan.setOnClickListener(v -> loadFragment(new HewanFragment()));
        navTumbuhan.setOnClickListener(v -> loadFragment(new TumbuhanFragment()));

        // Search
        btnSearch.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, PencarianActivity.class)));

        // Favorit
        btnFavorit.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, FavoritActivity.class)));

        // Settings
        btnSettings.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}