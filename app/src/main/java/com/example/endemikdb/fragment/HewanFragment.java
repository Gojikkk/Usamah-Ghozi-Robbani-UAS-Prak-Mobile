package com.example.endemikdb.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endemikdb.R;
import com.example.endemikdb.activity.DetailActivity;
import com.example.endemikdb.adapter.EndemikAdapter;
import com.example.endemikdb.database.EndemikDatabase;
import com.example.endemikdb.model.Endemik;

import java.util.ArrayList;
import java.util.List;

public class HewanFragment extends Fragment {

    private RecyclerView recyclerView;
    private EndemikAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hewan, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new EndemikAdapter(getContext(), new ArrayList<>(), endemik -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("id", endemik.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        observeData();

        return view;
    }

    private void observeData() {
        SharedPreferences prefs = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        String region = prefs.getString("region", "Semua");

        EndemikDatabase.getInstance(getContext())
                .endemikDao()
                .getHewanByRegion(region)
                .observe(getViewLifecycleOwner(), list -> {
                    if (list != null) {
                        adapter.updateData(list);
                    }
                });
    }
}
