package com.swu.kalkulator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {

    private final ArrayList<Riwayat> listRiwayat;

    public RiwayatAdapter(ArrayList<Riwayat> listRiwayat) {
        this.listRiwayat = listRiwayat;
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_riwayat, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        Riwayat item = listRiwayat.get(position);
        holder.tvPerhitungan.setText(item.getPerhitungan());
        holder.tvHasil.setText(item.getHasil());
    }

    @Override
    public int getItemCount() {
        return listRiwayat.size();
    }

    static class RiwayatViewHolder extends RecyclerView.ViewHolder {
        TextView tvPerhitungan, tvHasil;

        RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPerhitungan = itemView.findViewById(R.id.tvItemPerhitungan);
            tvHasil = itemView.findViewById(R.id.tvItemHasil);
        }
    }
}