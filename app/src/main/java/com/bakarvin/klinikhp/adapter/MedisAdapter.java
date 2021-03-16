package com.bakarvin.klinikhp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bakarvin.klinikhp.databinding.ItemRekamMedisBinding;
import com.bakarvin.klinikhp.model.RekamMedis;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedisAdapter extends RecyclerView.Adapter<MedisAdapter.MedisHolder>{

    private final ArrayList<RekamMedis> medisList;
    private onAction action;

    public MedisAdapter(ArrayList<RekamMedis> medisList) {
        this.medisList = medisList;
    }

    @NonNull
    @Override
    public MedisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRekamMedisBinding itemRekamMedisBinding = ItemRekamMedisBinding.inflate(layoutInflater,parent, false);
        return new MedisHolder(itemRekamMedisBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MedisHolder holder, int position) {
        RekamMedis rekamMedis = medisList.get(position);
        holder.itemRekamMedisBinding.txtTgl.setText(rekamMedis.getTgl_medis());
        holder.itemRekamMedisBinding.txtNamaDokter.setText(rekamMedis.getNama_dokter());
        holder.itemRekamMedisBinding.txtNamaPasien.setText(rekamMedis.getNama_pasien());
        holder.itemRekamMedisBinding.cardItemMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onActionClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medisList.size();
    }

    public class MedisHolder extends RecyclerView.ViewHolder {
        private final ItemRekamMedisBinding itemRekamMedisBinding;
        public MedisHolder(@NonNull ItemRekamMedisBinding itemRekamMedisBinding) {
            super(itemRekamMedisBinding.getRoot());
            this.itemRekamMedisBinding = itemRekamMedisBinding;
        }
    }
    public interface onAction{
        void onActionClick(View v, int position);
    }
    public void ActionClick(onAction onAction){
        action = onAction;
    }
}