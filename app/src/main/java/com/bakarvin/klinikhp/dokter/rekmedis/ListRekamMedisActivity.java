package com.bakarvin.klinikhp.dokter.rekmedis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.adapter.JadwalDokterAdapter;
import com.bakarvin.klinikhp.adapter.MedisAdapter;
import com.bakarvin.klinikhp.adapter.PasienAdapter;
import com.bakarvin.klinikhp.admin.crud.dokter.DetailDokterActivity;
import com.bakarvin.klinikhp.admin.crud.pasien.DetailPasienActivity;
import com.bakarvin.klinikhp.databinding.ActivityListRekamMedisBinding;
import com.bakarvin.klinikhp.model.JadwalDokter;
import com.bakarvin.klinikhp.model.Pasien;
import com.bakarvin.klinikhp.model.RekamMedis;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ListRekamMedisActivity extends AppCompatActivity {

    ActivityListRekamMedisBinding listRekamMedisBinding;
    ArrayList<RekamMedis> medisList;
    MedisAdapter medisAdapter;

    DatabaseReference dbRekam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listRekamMedisBinding = ActivityListRekamMedisBinding.inflate(getLayoutInflater());
        setContentView(listRekamMedisBinding.getRoot());
        dbRekam = FirebaseDatabase.getInstance().getReference("RekamMedis");
        medisList = new ArrayList<>();
        getDataMedis();
//        medisList = new ArrayList<>();
//        getDataMedis();
        listRekamMedisBinding.fabDaftarRekamMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DaftarRekamMedisActivity.class));
            }
        });
    }

    private void getDataMedis() {
        dbRekam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    RekamMedis rekamMedis = ds.getValue(RekamMedis.class);
                    medisList.add(rekamMedis);
                }
                medisAdapter = new MedisAdapter(medisList);
                listRekamMedisBinding.rvListMedis.setAdapter(medisAdapter);
                medisAdapter.ActionClick(new MedisAdapter.onAction() {
                    @Override
                    public void onActionClick(View v, int position) {
                        RekamMedis rekamMedis = medisList.get(position);
                        Intent i = new Intent(getApplicationContext(), DetailRekamMedisDokterActivity.class);
                        i.putExtra("idMedis",rekamMedis.getId_Medis());
                        startActivity(i);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "WTF DB Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}