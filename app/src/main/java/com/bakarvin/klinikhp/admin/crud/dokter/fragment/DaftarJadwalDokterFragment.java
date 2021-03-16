package com.bakarvin.klinikhp.admin.crud.dokter.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.admin.MainMenuStaffActivity;
import com.bakarvin.klinikhp.databinding.FragmentDaftarJadwalDokterBinding;
import com.bakarvin.klinikhp.databinding.FragmentDaftarStaffBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class DaftarJadwalDokterFragment extends Fragment {

    FragmentDaftarJadwalDokterBinding daftarJadwalDokterBinding;
    DatabaseReference dbDokter;
    DatabaseReference dbJadwal;
    String ktp;
    String nama;
    String alamat;
    String poli;
    String telp;
    String uname;
    String pass;
    String hari;
    String keterangan;

    public DaftarJadwalDokterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        daftarJadwalDokterBinding = FragmentDaftarJadwalDokterBinding.inflate(inflater, container, false);
        View v = daftarJadwalDokterBinding.getRoot();
        daftarJadwalDokterBinding.btnDaftarDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftarDokter();
            }
        });
        return v;
    }

    private void getDataDokter() {
        ktp = getArguments().getString("no_ktp");
        nama = getArguments().getString("nama");
        alamat = getArguments().getString("alamat");
        poli = getArguments().getString("poli");
        telp = getArguments().getString("telp");
        uname = getArguments().getString("uname");
        pass = getArguments().getString("pass");
    }
    private void daftarDokter() {
        getDataDokter();
        dbDokter = FirebaseDatabase.getInstance().getReference("Dokter").child(ktp);
        HashMap<String, String> dokterMap = new HashMap<>();
        dokterMap.put("ktp_dokter", ktp);
        dokterMap.put("nama_dokter", nama);
        dokterMap.put("alamat_dokter", alamat);
        dokterMap.put("poli_dokter", poli);
        dokterMap.put("telp_dokter", telp);
        dokterMap.put("username_dokter", uname);
        dokterMap.put("password_dokter", pass);
        dbDokter.setValue(dokterMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Berhasil Regist Dokter", Toast.LENGTH_SHORT).show();
                    daftarJadwal();
                } else {
                    Toast.makeText(getContext(), "Gagal Regist Dokter", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void daftarJadwal() {
        hari = daftarJadwalDokterBinding.txtJadwalHariDokter.getText().toString();
        keterangan = daftarJadwalDokterBinding.txtJadwalKetDokter.getText().toString();
        dbJadwal = FirebaseDatabase.getInstance().getReference("Jadwal").child("1");
        HashMap<String, String> jadwalMap = new HashMap<>();
        jadwalMap.put("id_jadwal", "1");
        jadwalMap.put("ktp_dokter", ktp);
        jadwalMap.put("hari_jadwal", hari);
        jadwalMap.put("keterangan_jadwal", keterangan);
        jadwalMap.put("nama_dokter", nama);
        jadwalMap.put("poli_dokter", poli);
        dbJadwal.setValue(jadwalMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Berhasil Regist Jadwal", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainMenuStaffActivity.class));
                } else {
                    Toast.makeText(getContext(), "Gagal Regist Jadwal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}