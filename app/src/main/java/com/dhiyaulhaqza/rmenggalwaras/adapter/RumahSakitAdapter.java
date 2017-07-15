package com.dhiyaulhaqza.rmenggalwaras.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhiyaulhaqza.rmenggalwaras.R;
import com.dhiyaulhaqza.rmenggalwaras.model.RumahSakit;

import java.util.List;

/**
 * Created by dhiyaulhaqza on 7/15/17.
 */

public class RumahSakitAdapter extends RecyclerView.Adapter<RumahSakitAdapter.ViewHolder> {

    private List<RumahSakit> rumahSakitList;

    public RumahSakitAdapter(List<RumahSakit> rumahSakitList) {
        this.rumahSakitList = rumahSakitList;
    }

    @Override
    public RumahSakitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RumahSakitAdapter.ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return rumahSakitList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDokter;
        private TextView tvPasien;
        private TextView tvKeterangan;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDokter = itemView.findViewById(R.id.tv_item_dokter);
            tvPasien = itemView.findViewById(R.id.tv_item_pasien);
            tvKeterangan = itemView.findViewById(R.id.tv_item_keterangan);
        }

        public void bindView(int position) {
            RumahSakit rs = rumahSakitList.get(position);
            if (rs != null) {
                tvPasien.setText(rs.getPasien());
                tvDokter.setText(rs.getDokter());
                tvKeterangan.setText(rs.getKeterangan());
            }
        }
    }
}
