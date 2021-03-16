package com.bakarvin.klinikhp.dokter.rekmedis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.adapter.PasienAdapter;
import com.bakarvin.klinikhp.databinding.ActivityDaftarRekamMedisBinding;
import com.bakarvin.klinikhp.model.Dokter;
import com.bakarvin.klinikhp.model.Pasien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DaftarRekamMedisActivity extends AppCompatActivity {

    ActivityDaftarRekamMedisBinding daftarRekamMedisBinding;
    DatabaseReference dbDokter;
    DatabaseReference dbPasien;
    DatabaseReference dbMedis;
    String ktpDokter;
    String ktpPasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daftarRekamMedisBinding = ActivityDaftarRekamMedisBinding.inflate(getLayoutInflater());
        setContentView(daftarRekamMedisBinding.getRoot());
        dbDokter = FirebaseDatabase.getInstance().getReference("Dokter");
        dbPasien = FirebaseDatabase.getInstance().getReference("Pasien");

        daftarRekamMedisBinding.btnDaftarMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIDDokter();

            }
        });

    }

    private void getDataPasien() {
        Query q = FirebaseDatabase.getInstance().getReference("Pasien")
                .orderByChild("no_ktp").equalTo(daftarRekamMedisBinding
                        .txtDaftarIdPasienMedis.getText().toString());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    Pasien pasien = ds.getValue(Pasien.class);
                    ktpPasien = pasien.getNama_pasien();
                }
                daftarMedis();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void daftarMedis() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String anastesa = daftarRekamMedisBinding.txtDaftarAnasMedis.getText().toString();
        String diagnosa = daftarRekamMedisBinding.txtDaftarDiagMedis.getText().toString();
        String terapi = daftarRekamMedisBinding.txtDaftarTerapiMedis.getText().toString();
        String resep = daftarRekamMedisBinding.txtDaftarResepMedis.getText().toString();
        String idDokter = daftarRekamMedisBinding.txtDaftarIdDokterMedis.getText().toString();
        String idPasien = daftarRekamMedisBinding.txtDaftarIdPasienMedis.getText().toString();
        String idMedis  = "00001";
        String formatDate = df.format(today);
        dbMedis = FirebaseDatabase.getInstance().getReference("RekamMedis").child(idMedis);
        HashMap<String, String> medisMap = new HashMap<>();
        medisMap.put("id_Medis", idMedis);
        medisMap.put("id_dokter", idDokter);
        medisMap.put("nama_dokter", ktpDokter);
        medisMap.put("id_pasien", idPasien);
        medisMap.put("nama_pasien", ktpPasien);
        medisMap.put("anastesa", anastesa);
        medisMap.put("diagnosa", diagnosa);
        medisMap.put("terapi", terapi);
        medisMap.put("resep", resep);
        medisMap.put("tgl_medis", formatDate);
        Toast.makeText(getApplicationContext(), medisMap.toString(), Toast.LENGTH_SHORT).show();
        dbMedis.setValue(medisMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Log.d("Masuk", "Id Medis = " + idMedis);
            }
        });



    }

    void getIDDokter(){
//        dbPasien.orderByChild("no_ktp").equalTo("0001");
            Query q = FirebaseDatabase.getInstance().getReference("Dokter")
                    .orderByChild("ktp_dokter").equalTo(daftarRekamMedisBinding
                            .txtDaftarIdDokterMedis.getText().toString());
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()){
                        Dokter dokter = ds.getValue(Dokter.class);
                        ktpDokter = dokter.getNama_dokter();
                    }
                    getDataPasien();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }
}
