package com.example.endemikdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.endemikdb.R;
import com.example.endemikdb.model.Favorit;

import java.util.List;

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.ViewHolder> {

    private Context context;
    private List<Favorit> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Favorit favorit);
    }

    public FavoritAdapter(Context context, List<Favorit> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public void updateData(List<Favorit> newList) {
        if (this.list == null) {
            this.list = newList;
        } else {
            this.list.clear();
            this.list.addAll(newList);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_endemik, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Favorit favorit = list.get(position);
        holder.tvNama.setText(favorit.getNama());

        Glide.with(context)
                .load(favorit.getFoto())
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .centerCrop()
                .into(holder.imgEndemik);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(favorit));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEndemik;
        TextView tvNama;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEndemik = itemView.findViewById(R.id.imgEndemik);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }
}