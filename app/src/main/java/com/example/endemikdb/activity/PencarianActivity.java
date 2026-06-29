package com.example.endemikdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endemikdb.R;
import com.example.endemikdb.adapter.EndemikAdapter;
import com.example.endemikdb.database.EndemikDatabase;
import com.example.endemikdb.model.Endemik;

import java.util.ArrayList;
import java.util.List;

public class PencarianActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EndemikAdapter adapter;
    private EditText etSearch;
    private ImageButton btnBack, btnFavorit, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        btnBack = findViewById(R.id.btnBack);
        btnFavorit = findViewById(R.id.btnFavorit);
        btnClear = findViewById(R.id.btnClear);
        etSearch = findViewById(R.id.etSearch);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Default tampilkan semua
        loadData("");

        btnBack.setOnClickListener(v -> finish());

        btnFavorit.setOnClickListener(v -> {
            startActivity(new Intent(this, FavoritActivity.class));
        });

        btnClear.setOnClickListener(v -> {
            etSearch.setText("");
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                btnClear.setVisibility(query.isEmpty() ? View.GONE : View.VISIBLE);
                loadData(query);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadData(String query) {
        List<Endemik> list;
        if(query.isEmpty()){
            list = EndemikDatabase.getInstance(this)
                    .endemikDao()
                    .getAll();
        }else{
            list = EndemikDatabase.getInstance(this)
                    .endemikDao()
                    .searchSync(query);
        }

        if (adapter == null) {
            adapter = new EndemikAdapter(this, list, endemik -> {
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id", endemik.getId());
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateData(list);
        }
    }
}