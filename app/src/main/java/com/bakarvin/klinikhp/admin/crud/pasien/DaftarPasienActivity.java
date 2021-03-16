package com.bakarvin.klinikhp.admin.crud.pasien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.databinding.ActivityDaftarPasienBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DaftarPasienActivity extends AppCompatActivity {

    DatabaseReference DBref;
    ActivityDaftarPasienBinding daftarPasienBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daftarPasienBinding = ActivityDaftarPasienBinding.inflate(getLayoutInflater());
        setContentView(daftarPasienBinding.getRoot());
        daftarPasienBinding.btnDaftarinPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDaftar();
            }
        });
    }
    void initDaftar(){
        String no_ktp = daftarPasienBinding.txtDaftarNoKtp.getText().toString();
        String nama_pasien = daftarPasienBinding.txtDaftarNamaPasien.getText().toString();
        String alamat_pasien = daftarPasienBinding.txtDaftarAlamatPasien.getText().toString();
        String jenkel = daftarPasienBinding.spinJenkelPasien.getSelectedItem().toString();
        String umur = daftarPasienBinding.txtDaftarUmurPasien.getText().toString();
        String status = daftarPasienBinding.spinStatusPasien.getSelectedItem().toString();
        String no_hp = daftarPasienBinding.txtDaftarTelpPasien.getText().toString();
        String nama_ibu = daftarPasienBinding.txtNamaIbuPasien.getText().toString();
        String nama_pasangan = daftarPasienBinding.txtDaftarPasanganPasien.getText().toString();

        if (no_ktp.isEmpty()||nama_pasien.isEmpty()||alamat_pasien.isEmpty()||jenkel.isEmpty()||umur.isEmpty()||
        status.isEmpty()||no_hp.isEmpty()||nama_ibu.isEmpty()||nama_pasangan.isEmpty()){
            Toast.makeText(getApplicationContext(), "Ada yang Kosong", Toast.LENGTH_SHORT).show();
        } else {
            DBref = FirebaseDatabase.getInstance().getReference("Pasien").child(no_ktp);
            HashMap<String, String> pasienMap = new HashMap<>();
            pasienMap.put("no_ktp", no_ktp);
            pasienMap.put("nama_pasien", nama_pasien);
            pasienMap.put("alamat_pasien", alamat_pasien);
            pasienMap.put("jenkel", jenkel);
            pasienMap.put("umur", umur);
            pasienMap.put("status", status);
            pasienMap.put("no_hp", no_hp);
            pasienMap.put("nama_ibu", nama_ibu);
            if (status.equals("Belum Menikah")){
                pasienMap.put("nama_pasangan","-");
            } else {
                pasienMap.put("nama_pasangan", nama_pasangan);
            }
            DBref.setValue(pasienMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Log.d("Masuk", "No. KTP = " + no_ktp);
                }
            });
        }
    }
}