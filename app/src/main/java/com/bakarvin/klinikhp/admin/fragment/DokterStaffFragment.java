package com.bakarvin.klinikhp.admin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.klinikhp.adapter.JadwalDokterAdapter;
import com.bakarvin.klinikhp.admin.crud.dokter.DetailDokterActivity;
import com.bakarvin.klinikhp.databinding.FragmentDokterStaffBinding;
import com.bakarvin.klinikhp.model.JadwalDokter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DokterStaffFragment extends Fragment {

    FragmentDokterStaffBinding dokterStaffBinding;
    ArrayList<JadwalDokter> jadwalList;
    JadwalDokterAdapter jadwalDokterAdapter;
    DatabaseReference dbJadwal;

    public DokterStaffFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dokterStaffBinding = FragmentDokterStaffBinding.inflate(inflater, container, false);
        View v = dokterStaffBinding.getRoot();
        dbJadwal = FirebaseDatabase.getInstance().getReference("Jadwal");
        jadwalList = new ArrayList<>();
        getDataDokter();
        return  v;
    }

    private void getDataDokter() {
        dbJadwal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    JadwalDokter jadwalDokter = snapshot.getValue(JadwalDokter.class);
                    jadwalList.add(jadwalDokter);
                }
                jadwalDokterAdapter = new JadwalDokterAdapter(jadwalList, getContext());
                dokterStaffBinding.rvListDokter.setAdapter(jadwalDokterAdapter);

                jadwalDokterAdapter.ActionClick(new JadwalDokterAdapter.onAction() {
                    @Override
                    public void onActionClick(View view, int position) {
                        JadwalDokter jadwalDokter = jadwalList.get(position);
                        Intent i = new Intent(getContext(), DetailDokterActivity.class);
                        i.putExtra("no_ktp", jadwalDokter.getKtp_dokter());
                        i.putExtra("id_jadwal", jadwalDokter.getId_jadwal());
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}