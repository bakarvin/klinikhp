package com.bakarvin.klinikhp.admin.crud.dokter.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.databinding.FragmentDaftarDataDokterBinding;
import com.bakarvin.klinikhp.databinding.FragmentDaftarJadwalDokterBinding;
import com.bakarvin.klinikhp.databinding.FragmentDaftarStaffBinding;

import java.util.ArrayList;
import java.util.List;


public class DaftarDataDokterFragment extends Fragment {

    FragmentDaftarDataDokterBinding daftarDataDokterBinding;

    public DaftarDataDokterFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        daftarDataDokterBinding = FragmentDaftarDataDokterBinding.inflate(inflater, container, false);
        View v = daftarDataDokterBinding.getRoot();
        daftarDataDokterBinding.btnNextDaftarDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataDokter();
            }
        });
        return v;
    }
    void sendDataDokter(){
        Bundle args = new Bundle();
        args.putString("no_ktp",daftarDataDokterBinding.txtDaftarKTPDokter.getText().toString());
        args.putString("nama",daftarDataDokterBinding.txtDaftarNamaDokter.getText().toString());
        args.putString("alamat",daftarDataDokterBinding.txtDaftarAlamatDokter.getText().toString());
        args.putString("poli",daftarDataDokterBinding.txtDaftarPoliDokter.getText().toString());
        args.putString("telp",daftarDataDokterBinding.txtDaftarTelpDokter.getText().toString());
        args.putString("uname",daftarDataDokterBinding.txtDaftarUnameDokter.getText().toString());
        args.putString("pass",daftarDataDokterBinding.txtDaftarPassDokter.getText().toString());
        Fragment nextFrag = new DaftarJadwalDokterFragment();
        nextFrag.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragDaftarDokter, nextFrag)
                .addToBackStack(null)
                .commit();
    }
}