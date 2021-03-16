package com.bakarvin.klinikhp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.klinikhp.databinding.ItemRecyclerBinding;
import com.bakarvin.klinikhp.model.Pasien;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.PasienHolder>{

    private ArrayList<Pasien> pasienList;
    private Context context;
    private onAction action;
    public PasienAdapter(ArrayList<Pasien> pasienList, Context context){
        this.pasienList = pasienList;
        this.context = context;
    }
    @NonNull
    @Override
    public PasienAdapter.PasienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRecyclerBinding itemRecyclerBinding = ItemRecyclerBinding.inflate(layoutInflater, parent, false);
        return new PasienHolder(itemRecyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PasienAdapter.PasienHolder holder, int position) {
        Pasien pasien = pasienList.get(position);
        holder.itemRecyclerBinding.txtItemNama.setText(pasien.getNama_pasien());
        holder.itemRecyclerBinding.txtItemJadKel.setText(pasien.getJenkel());
        holder.itemRecyclerBinding.txtItemPoliUmur.setText(pasien.getUmur());
        holder.itemRecyclerBinding.cardItemRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onActionClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pasienList.size();
    }

    public class PasienHolder extends RecyclerView.ViewHolder {
        private ItemRecyclerBinding itemRecyclerBinding;
        public PasienHolder(@NonNull ItemRecyclerBinding itemRecyclerBinding) {
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
