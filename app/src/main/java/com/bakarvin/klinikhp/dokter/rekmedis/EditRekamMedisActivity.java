package com.bakarvin.klinikhp.dokter.rekmedis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bakarvin.klinikhp.databinding.ActivityEditRekamMedisBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditRekamMedisActivity extends AppCompatActivity {

    ActivityEditRekamMedisBinding editRekamMedisBinding;
    DatabaseReference dbMedis;
    DatabaseReference dbEditMedis;
    String getIdMedis;
    String getIdDokter;
    String getNamaDokter;
    String getIdPasien;
    String getNamaPasien;
    String getAnas;
    String getDiag;
    String getTerapi;
    String getResep;
    String getTgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editRekamMedisBinding = ActivityEditRekamMedisBinding.inflate(getLayoutInflater());
        setContentView(editRekamMedisBinding.getRoot());
        dbMedis = FirebaseDatabase.getInstance().getReference("RekamMedis");
        getIntentData();
        editRekamMedisBinding.btnEditMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMedis();
            }
        });
    }

    private void getIntentData() {
        getIdMedis = getIntent().getStringExtra("idMedis");
        getIdDokter= getIntent().getStringExtra("idDokter");
        getNamaDokter= getIntent().getStringExtra("namaDokter");
        getIdPasien= getIntent().getStringExtra("idPasien");
        getNamaPasien= getIntent().getStringExtra("namaPasien");
        getAnas= getIntent().getStringExtra("anas");
        getDiag= getIntent().getStringExtra("diag");
        getTerapi= getIntent().getStringExtra("terapi");
        getResep= getIntent().getStringExtra("resep");
        getTgl= getIntent().getStringExtra("tglMedis");
        init();
    }

    private void init() {
        editRekamMedisBinding.txtEditIdDokterMedis.setText(getIdDokter);
        editRekamMedisBinding.txtEditIdPasienMedis.setText(getIdPasien);
        editRekamMedisBinding.txtEditAnasMedis.setText(getAnas);
        editRekamMedisBinding.txtEditDiagMedis.setText(getDiag);
        editRekamMedisBinding.txtEditTerapiMedis.setText(getTerapi);
        editRekamMedisBinding.txtEditResepMedis.setText(getResep);
    }

    private void editMedis() {
        String anastesa = editRekamMedisBinding.txtEditAnasMedis.getText().toString();
        String diagnosa = editRekamMedisBinding.txtEditDiagMedis.getText().toString();
        String id_Medis = getIdMedis;
        String id_dokter = editRekamMedisBinding.txtEditIdDokterMedis.getText().toString();
        String id_pasien = editRekamMedisBinding.txtEditIdPasienMedis.getText().toString();
        String nama_dokter = getNamaDokter;
        String nama_pasien = getNamaPasien;
        String resep = editRekamMedisBinding.txtEditResepMedis.getText().toString();
        String terapi = editRekamMedisBinding.txtEditTerapiMedis.getText().toString();
        String tgl_medis = getTgl;

        dbEditMedis = dbMedis.child(getIdMedis);
        HashMap<String,String> medisMap = new HashMap<>();
        medisMap.put("anastesa", anastesa);
        medisMap.put("diagnosa", diagnosa);
        medisMap.put("id_Medis", id_Medis);
        medisMap.put("id_dokter", id_dokter);
        medisMap.put("id_pasien", id_pasien);
        medisMap.put("nama_dokter", nama_dokter);
        medisMap.put("nama_pasien", nama_pasien);
        medisMap.put("resep", resep);
        medisMap.put("terapi", terapi);
        medisMap.put("tgl_medis", tgl_medis);
        dbEditMedis.setValue(medisMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("Masuk", "Data berhasil diubah = " + id_Medis);
                finish();
            }
        });
    }
}