package com.bakarvin.klinikhp.admin.crud.pasien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bakarvin.klinikhp.databinding.ActivityEditPasienBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditPasienActivity extends AppCompatActivity {

    ActivityEditPasienBinding editPasienBinding;
    DatabaseReference dbPasien;
    DatabaseReference dbEditPasien;
    String getKtp;
    String getNamaPasien;
    String getAlamat;
    String getJenkel;
    String getUmur;
    String getStatus;
    String getTelp;
    String getIbu;
    String getPasangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editPasienBinding = ActivityEditPasienBinding.inflate(getLayoutInflater());
        setContentView(editPasienBinding.getRoot());
        dbPasien = FirebaseDatabase.getInstance().getReference().child("Pasien");
        getIntentExtra();
        editPasienBinding.btnEdtPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditPasien();
            }
        });
        editPasienBinding.imgBackEditPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getIntentExtra() {
        getKtp = getIntent().getStringExtra("no_ktp");
        getNamaPasien = getIntent().getStringExtra("nama_pasien");
        getAlamat = getIntent().getStringExtra("alamat_pasien");
        getJenkel = getIntent().getStringExtra("jenkel_pasien");
        getUmur = getIntent().getStringExtra("umur_pasien");
        getStatus = getIntent().getStringExtra("status_pasien");
        getTelp = getIntent().getStringExtra("telp_pasien");
        getIbu = getIntent().getStringExtra("ibu_pasien");
        getPasangan = getIntent().getStringExtra("pasangan_pasien");
        init();
    }

    private void init() {
        editPasienBinding.txtEditKtpPasien.setText(getKtp);
        editPasienBinding.txtEditNamaPasien.setText(getNamaPasien);
        editPasienBinding.txtEditAlamatPasien.setText(getAlamat);
        if (getJenkel.equals("Laki - laki")){
            editPasienBinding.spinEditJenkelPasien.setSelection(0);
        } else {
            editPasienBinding.spinEditJenkelPasien.setSelection(1);
        }
        editPasienBinding.txtEditUmurPasien.setText(getUmur);
        if (getStatus.equals("Menikah")){
            editPasienBinding.spinEditStatusPasien.setSelection(0);
        }else{
            editPasienBinding.spinEditStatusPasien.setSelection(1);
        }
        editPasienBinding.txtEditTelpPasien.setText(getTelp);
        editPasienBinding.txtEditIbuPasien.setText(getIbu);
        editPasienBinding.txtEditPasanganPasien.setText(getPasangan);
    }

    private void EditPasien() {
            String no_ktp = editPasienBinding.txtEditKtpPasien.getText().toString();
            String nama_pasien = editPasienBinding.txtEditNamaPasien.getText().toString();
            String alamat_pasien = editPasienBinding.txtEditAlamatPasien.getText().toString();
            String jenkel = editPasienBinding.spinEditJenkelPasien.getSelectedItem().toString();
            String umur = editPasienBinding.txtEditUmurPasien.getText().toString();
            String status = editPasienBinding.spinEditStatusPasien.getSelectedItem().toString();
            String no_hp = editPasienBinding.txtEditTelpPasien.getText().toString();
            String nama_ibu = editPasienBinding.txtEditIbuPasien.getText().toString();
            String nama_pasangan =  editPasienBinding.txtEditPasanganPasien.getText().toString();

            if (no_ktp.isEmpty()||nama_pasien.isEmpty()||alamat_pasien.isEmpty()||jenkel.isEmpty()||umur.isEmpty()||
                    status.isEmpty()||no_hp.isEmpty()||nama_ibu.isEmpty()||nama_pasangan.isEmpty()){
                Toast.makeText(getApplicationContext(), "Ada yang Kosong", Toast.LENGTH_SHORT).show();
            } else {
                dbEditPasien = dbPasien.child(getKtp);
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
                dbEditPasien.setValue(pasienMap).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Log.d("Masuk", "Data berhasil diubah No. KTP = " + no_ktp);
                        finish();
                    }
                });
            }
    }
    
}