package com.bakarvin.klinikhp.admin.crud.pasien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.MainActivity;
import com.bakarvin.klinikhp.admin.MainMenuStaffActivity;
import com.bakarvin.klinikhp.databinding.ActivityDetailPasienBinding;
import com.bakarvin.klinikhp.model.Pasien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailPasienActivity extends AppCompatActivity {

    ActivityDetailPasienBinding detailPasienBinding;
    String getNoKtp;
    DatabaseReference dbDataPasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailPasienBinding = ActivityDetailPasienBinding.inflate(getLayoutInflater());
        setContentView(detailPasienBinding.getRoot());
        dbDataPasien = FirebaseDatabase.getInstance().getReference("Pasien");
        getNoKtp = getIntent().getStringExtra("no_ktp");
        init(getNoKtp);
        detailPasienBinding.btnHapusPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusPasien();
            }
        });
        detailPasienBinding.btnEditPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPasien();
            }
        });
        detailPasienBinding.btnBackDetailPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailPasienActivity.this, MainMenuStaffActivity.class));
            }
        });
    }
    void init(String getNoKtp) {
        Toast.makeText(this, getNoKtp, Toast.LENGTH_SHORT).show();
        dbDataPasien.child(getNoKtp).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Pasien pasien = snapshot.getValue(Pasien.class);
                detailPasienBinding.txtDetailNamaPasien.setText(pasien.getNama_pasien());
                detailPasienBinding.txtDetailIdPasien.setText("Belum ada ID");
                detailPasienBinding.txtDetailKtpPasien.setText(pasien.getNo_ktp());
                detailPasienBinding.txtDetailAlamatPasien.setText(pasien.getAlamat_pasien());
                detailPasienBinding.txtDetailJenkelPasien.setText(pasien.getJenkel());
                detailPasienBinding.txtDetailUmurPasien.setText(pasien.getUmur());
                detailPasienBinding.txtDetailStatusPasien.setText(pasien.getStatus());
                detailPasienBinding.txtDetailTelpPasien.setText(pasien.getNo_hp());
                detailPasienBinding.txtDetailIbuPasien.setText(pasien.getNama_ibu());
                detailPasienBinding.txtDetailPasanganPasien.setText(pasien.getNama_pasangan());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void editPasien() {
        Intent i = new Intent(this, EditPasienActivity.class);
        i.putExtra("no_ktp", detailPasienBinding.txtDetailKtpPasien.getText());
        i.putExtra("nama_pasien", detailPasienBinding.txtDetailNamaPasien.getText());
        i.putExtra("alamat_pasien", detailPasienBinding.txtDetailAlamatPasien.getText());
        i.putExtra("jenkel_pasien", detailPasienBinding.txtDetailJenkelPasien.getText());
        i.putExtra("umur_pasien", detailPasienBinding.txtDetailUmurPasien.getText());
        i.putExtra("status_pasien", detailPasienBinding.txtDetailStatusPasien.getText());
        i.putExtra("telp_pasien", detailPasienBinding.txtDetailTelpPasien.getText());
        i.putExtra("ibu_pasien", detailPasienBinding.txtDetailIbuPasien.getText());
        i.putExtra("pasangan_pasien", detailPasienBinding.txtDetailPasanganPasien.getText());
        startActivity(i);
    }

    private void hapusPasien() {
        dbDataPasien.child(getNoKtp).removeValue();
        Toast.makeText(this, "Hapus Data Pasien no KTP :" + getNoKtp, Toast.LENGTH_LONG).show();
        finish();
    }


}