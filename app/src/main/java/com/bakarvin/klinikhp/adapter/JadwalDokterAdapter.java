package com.bakarvin.klinikhp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.klinikhp.databinding.ItemRecyclerBinding;
import com.bakarvin.klinikhp.model.JadwalDokter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JadwalDokterAdapter extends RecyclerView.Adapter<JadwalDokterAdapter.JadwalHolder> {

    private final ArrayList<JadwalDokter> jadwalList;
    private final Context context;
    private onAction action;
    public JadwalDokterAdapter(ArrayList<JadwalDokter> jadwalList, Context context){
        this.jadwalList = jadwalList;
        this.context = context;
    }
    @NonNull
    @Override
    public JadwalDokterAdapter.JadwalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRecyclerBinding itemRecyclerBinding = ItemRecyclerBinding.inflate(layoutInflater, parent, false);
        return new JadwalHolder(itemRecyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalDokterAdapter.JadwalHolder holder, int position) {
        JadwalDokter jadwal = jadwalList.get(position);
        holder.itemRecyclerBinding.txtItemNama.setText(jadwal.getNama_dokter());
        holder.itemRecyclerBinding.txtItemJadKel.setText(jadwal.getHari_jadwal());
        holder.itemRecyclerBinding.txtItemPoliUmur.setText(jadwal.getPoli_dokter());
        holder.itemRecyclerBinding.cardItemRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onActionClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    public class JadwalHolder extends RecyclerView.ViewHolder {
        private final ItemRecyclerBinding itemRecyclerBinding;
        public JadwalHolder(@NonNull ItemRecyclerBinding itemRecyclerBinding) {
            super(itemRecyclerBinding.getRoot());
            this.itemRecyclerBinding = itemRecyclerBinding;
        }
    }
    public interface onAction{
        void onActionClick(View view, int position);
    }
    public void ActionClick(onAction onAction){
        action = onAction;
    }
}