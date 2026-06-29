package com.example.endemikdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.endemikdb.R;
import com.example.endemikdb.database.EndemikDatabase;
import com.example.endemikdb.database.FavoritDao;
import com.example.endemikdb.model.Endemik;
import com.example.endemikdb.model.Favorit;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ImageButton btnBack, btnFavorit;
    private ImageView imgDetail;
    private TextView tvTitle, tvNamaLatin, tvStatus, tvFamili, tvGenus, tvAsal, tvSebaran, tvDeskripsi;

    private Endemik currentEndemik;
    private EndemikDatabase db;
    private boolean isFavorit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = EndemikDatabase.getInstance(this);

        btnBack = findViewById(R.id.btnBack);
        btnFavorit = findViewById(R.id.btnFavorit);
        imgDetail = findViewById(R.id.imgDetail);
        tvTitle = findViewById(R.id.tvTitle);
        tvNamaLatin = findViewById(R.id.tvNamaLatin);
        tvStatus = findViewById(R.id.tvStatus);
        tvFamili = findViewById(R.id.tvFamili);
        tvGenus = findViewById(R.id.tvGenus);
        tvAsal = findViewById(R.id.tvAsal);
        tvSebaran = findViewById(R.id.tvSebaran);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);

        int id = getIntent().getIntExtra("id", -1);

        // Ambil data by id - kita perlu query baru
        // Atau pass seluruh data via intent (simpler)
        loadDetail(id);

        btnBack.setOnClickListener(v -> finish());

        btnFavorit.setOnClickListener(v -> toggleFavorit());
    }

    private void toggleFavorit() {
        if (currentEndemik == null) return;

        FavoritDao favoritDao = db.favoritDao();

        if (isFavorit) {
            // Hapus dari favorit
            favoritDao.deleteByEndemikId(currentEndemik.getId());
            isFavorit = false;
        } else {
            // Tambah ke favorit
            Favorit favorit = new Favorit(
                    currentEndemik.getId(),
                    currentEndemik.getNama(),
                    currentEndemik.getNamaLatin(),
                    currentEndemik.getTipe(),
                    currentEndemik.getFoto(),
                    currentEndemik.getStatus()
            );
            favoritDao.insert(favorit);
            isFavorit = true;
        }
        updateFavoritIcon();
    }

    private void loadDetail(int id) {
        db.endemikDao().getById(id).observe(this, endemik -> {
            if (endemik == null) return;
            currentEndemik = endemik;

            // Cek apakah sudah favorit dari tabel favorit
            new Thread(() -> {
                isFavorit = db.favoritDao().isFavorit(id) > 0;
                runOnUiThread(this::updateFavoritIcon);
            }).start();

            tvTitle.setText(currentEndemik.getNama());
            tvNamaLatin.setText(currentEndemik.getNamaLatin());
            tvStatus.setText(currentEndemik.getStatus());
            tvFamili.setText(currentEndemik.getFamili());
            tvGenus.setText(currentEndemik.getGenus());
            tvAsal.setText(currentEndemik.getAsal());
            tvSebaran.setText(currentEndemik.getSebaran());
            tvDeskripsi.setText(currentEndemik.getDeskripsi());

            Glide.with(this)
                    .load(currentEndemik.getFoto())
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_placeholder)
                    .centerCrop()
                    .into(imgDetail);
        });
    }

    private void updateFavoritIcon() {
        if (isFavorit) {
            btnFavorit.setImageResource(R.drawable.ic_favorite);
        } else {
            btnFavorit.setImageResource(R.drawable.ic_favorite_border);
        }
    }
}