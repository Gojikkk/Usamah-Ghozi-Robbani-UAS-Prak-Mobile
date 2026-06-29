package com.example.endemikdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endemikdb.R;
import com.example.endemikdb.adapter.FavoritAdapter;
import com.example.endemikdb.database.EndemikDatabase;
import com.example.endemikdb.model.Favorit;

import java.util.List;

public class FavoritActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritAdapter adapter;
    private ImageButton btnBack, btnFavorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        btnBack = findViewById(R.id.btnBack);
        btnFavorit = findViewById(R.id.btnFavorit);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        loadData();

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadData() {
        EndemikDatabase.getInstance(this).favoritDao().getAll().observe(this, list -> {
            if (list != null) {
                if (adapter == null) {
                    adapter = new FavoritAdapter(this, list, favorit -> {
                        Intent intent = new Intent(this, DetailActivity.class);
                        intent.putExtra("id", favorit.getEndemikId());
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.updateData(list);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}