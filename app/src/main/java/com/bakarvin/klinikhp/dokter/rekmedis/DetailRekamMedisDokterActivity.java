package com.bakarvin.klinikhp.dokter.rekmedis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.admin.crud.pasien.EditPasienActivity;
import com.bakarvin.klinikhp.databinding.ActivityDetailRekamMedisDokterBinding;
import com.bakarvin.klinikhp.model.Pasien;
import com.bakarvin.klinikhp.model.RekamMedis;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailRekamMedisDokterActivity extends AppCompatActivity {

    ActivityDetailRekamMedisDokterBinding rekamMedisDokterBinding;
    DatabaseReference dbMedis;
    String getIdMedis;
    String getNamaDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rekamMedisDokterBinding = ActivityDetailRekamMedisDokterBinding.inflate(getLayoutInflater());
        setContentView(rekamMedisDokterBinding.getRoot());
        dbMedis = FirebaseDatabase.getInstance().getReference("RekamMedis");
        getIdMedis = getIntent().getStringExtra("idMedis");
        init(getIdMedis);
        rekamMedisDokterBinding.btnHapusRekamMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusRekamMedis();
            }
        });
        rekamMedisDokterBinding.btnEditRekamMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editRekamMedis();
            }
        });
    }



    private void init(String getIdMedis) {
        dbMedis.child(getIdMedis).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RekamMedis rekamMedis = snapshot.getValue(RekamMedis.class);
                rekamMedisDokterBinding.txtDetailIdRekamMedis.setText(rekamMedis.getId_Medis());
                rekamMedisDokterBinding.txtDetailIdDokterMedis.setText(rekamMedis.getId_dokter());
                rekamMedisDokterBinding.txtDetailIdPasienMedis.setText(rekamMedis.getId_pasien());
                rekamMedisDokterBinding.txtDetailNamaPasienMedis.setText(rekamMedis.getNama_pasien());
                rekamMedisDokterBinding.txtDetailAnasMedis.setText(rekamMedis.getAnastesa());
                rekamMedisDokterBinding.txtDetailDiagMedis.setText(rekamMedis.getDiagnosa());
                rekamMedisDokterBinding.txtDetailTerapiMedis.setText(rekamMedis.getTerapi());
                rekamMedisDokterBinding.txtDetailResepMedis.setText(rekamMedis.getResep());
                rekamMedisDokterBinding.txtDetailTanggalMedis.setText(rekamMedis.getTgl_medis());
                getNamaDokter = rekamMedis.getNama_dokter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void hapusRekamMedis(){
        dbMedis.child(getIdMedis).removeValue();
        Toast.makeText(this, "Hapus Data Rekam Medis dengan ID :" + getIdMedis, Toast.LENGTH_LONG).show();
        finish();
    }

    private void editRekamMedis(){
        Intent i = new Intent(this, EditRekamMedisActivity.class);
        i.putExtra("idMedis", rekamMedisDokterBinding.txtDetailIdRekamMedis.getText());
            i.putExtra("idDokter", rekamMedisDokterBinding.txtDetailIdDokterMedis.getText());
        i.putExtra("namaDokter", getNamaDokter);
            i.putExtra("idPasien", rekamMedisDokterBinding.txtDetailIdPasienMedis.getText());
        i.putExtra("namaPasien", rekamMedisDokterBinding.txtDetailNamaPasienMedis.getText());
            i.putExtra("anas", rekamMedisDokterBinding.txtDetailAnasMedis.getText());
        i.putExtra("diag", rekamMedisDokterBinding.txtDetailDiagMedis.getText());
            i.putExtra("terapi", rekamMedisDokterBinding.txtDetailTerapiMedis.getText());
        i.putExtra("resep", rekamMedisDokterBinding.txtDetailResepMedis.getText());
            i.putExtra("tglMedis", rekamMedisDokterBinding.txtDetailTanggalMedis.getText());
        startActivity(i);
    }
}