package com.example.endemikdb.database;

import android.content.Context;
import android.util.Log;

import com.example.endemikdb.model.Endemik;
import com.example.endemikdb.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSeeder {

    public static void seedData(Context context) {
        EndemikDatabase db = EndemikDatabase.getInstance(context);
        EndemikDao dao = db.endemikDao();

        if (dao.getCount() > 0) return; // sudah ada data

        RetrofitClient.getApiService().getEndemikList().enqueue(new Callback<List<Endemik>>() {
            @Override
            public void onResponse(Call<List<Endemik>> call, Response<List<Endemik>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> {
                        dao.insertAll(response.body());
                        Log.d("DataSeeder", "Data successfully seeded from API");
                    }).start();
                } else {
                    Log.e("DataSeeder", "Failed to fetch data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Endemik>> call, Throwable t) {
                Log.e("DataSeeder", "Error fetching data", t);
            }
        });
    }
}
