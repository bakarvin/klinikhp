package com.bakarvin.klinikhp.admin.crud.dokter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.databinding.ActivityEditDokterBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditDokterActivity extends AppCompatActivity {

    ActivityEditDokterBinding editDokterBinding;
    String getKtp;
    String getNama;
    String getAlamat;
    String getPass;
    String getPoli;
    String getTelp;
    String getUser;
    String getHari;
    String getIdJadwal;
    String getKetJadwal;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editDokterBinding = ActivityEditDokterBinding.inflate(getLayoutInflater());
        setContentView(editDokterBinding.getRoot());
        dbRef = FirebaseDatabase.getInstance().getReference("Dokter");
        editDokterBinding.txtEditKtpDokter.setEnabled(false);
        editDokterBinding.txtEditNamaDokter.setEnabled(false);
        editDokterBinding.txtEditPoliDokter.setEnabled(false);
        getData();
        editDokterBinding.btnEdtDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDokter();
            }
        });
        editDokterBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData() {
        getKtp = getIntent().getStringExtra("no_ktp");
        getNama = getIntent().getStringExtra("nama_dokter");
        getAlamat = getIntent().getStringExtra("alamat_dokter");
        getPass = getIntent().getStringExtra("pass_dokter");
        getPoli = getIntent().getStringExtra("poli_dokter");
        getTelp = getIntent().getStringExtra("telp_dokter");
        getUser = getIntent().getStringExtra("user_dokter");
        getHari = getIntent().getStringExtra("hari_jadwal");
        getIdJadwal = getIntent().getStringExtra("id_jadwal");
        getKetJadwal = getIntent().getStringExtra("ket_jadwal");
        init();
    }
    private void init() {
        editDokterBinding.txtEditKtpDokter.setText(getKtp);
        editDokterBinding.txtEditNamaDokter.setText(getNama);
        editDokterBinding.txtEditAlamatDokter.setText(getAlamat);
        editDokterBinding.txtEditPassDokter.setText(getPass);
        editDokterBinding.txtEditPoliDokter.setText(getPoli);
        editDokterBinding.txtEditTelpDokter.setText(getTelp);
        editDokterBinding.txtEditUnameDokter.setText(getUser);
    }
    private void editDokter() {
        String no_ktp = editDokterBinding.txtEditKtpDokter.getText().toString();
        String nama_dokter = editDokterBinding.txtEditNamaDokter.getText().toString();
        String alamat_dokter = editDokterBinding.txtEditAlamatDokter.getText().toString();
        String poli = editDokterBinding.txtEditPoliDokter.getText().toString();
        String no_hp = editDokterBinding.txtEditTelpDokter.getText().toString();
        String uname = editDokterBinding.txtEditUnameDokter.getText().toString();
        String pass =  editDokterBinding.txtEditPassDokter.getText().toString();

        if (no_ktp.isEmpty()||nama_dokter.isEmpty()||alamat_dokter.isEmpty()||poli.isEmpty()||
                no_hp.isEmpty()||uname.isEmpty()||pass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Ada yang Kosong", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference dbDokter = dbRef.child(getKtp);
            HashMap<String, String> dokterMap = new HashMap<>();
            dokterMap.put("alamat_dokter", alamat_dokter);
            dokterMap.put("ktp_dokter", no_ktp);
            dokterMap.put("nama_dokter", nama_dokter);
            dokterMap.put("password_dokter", pass);
            dokterMap.put("poli_dokter", poli);
            dokterMap.put("telp_dokter", no_hp);
            dokterMap.put("username_dokter", uname);
            dbDokter.setValue(dokterMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Log.d("Masuk", "Data berhasil diubah No. KTP = " + no_ktp);
                    finish();
                }
            });
        }
    }
}