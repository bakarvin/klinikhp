package com.bakarvin.klinikhp.dokter.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.databinding.FragmentDaftarStaffBinding;
import com.bakarvin.klinikhp.databinding.FragmentHomeDokterBinding;
import com.bakarvin.klinikhp.dokter.rekmedis.DaftarRekamMedisActivity;
import com.bakarvin.klinikhp.dokter.rekmedis.DetailRekamMedisDokterActivity;
import com.bakarvin.klinikhp.dokter.rekmedis.ListRekamMedisActivity;

public class HomeDokterFragment extends Fragment {

    FragmentHomeDokterBinding homeDokterBinding;

    public HomeDokterFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeDokterBinding = FragmentHomeDokterBinding.inflate(inflater, container, false);
        View v = homeDokterBinding.getRoot();
        homeDokterBinding.btnRekamMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ListRekamMedisActivity.class));
            }
        });
        return v;
    }
}