package com.bakarvin.klinikhp.admin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.admin.crud.dokter.DaftarDokterActivity;
import com.bakarvin.klinikhp.admin.crud.pasien.DaftarPasienActivity;
import com.bakarvin.klinikhp.databinding.FragmentDaftarStaffBinding;

public class DaftarStaffFragment extends Fragment {

    FragmentDaftarStaffBinding daftarStaffBinding;

    public DaftarStaffFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        daftarStaffBinding = FragmentDaftarStaffBinding.inflate(inflater, container, false);
        View v = daftarStaffBinding.getRoot();
        daftarStaffBinding.btnDaftarPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DaftarPasienActivity.class));
            }
        });
        daftarStaffBinding.btnDaftarDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DaftarDokterActivity.class));
            }
        });

        return v;
    }
}