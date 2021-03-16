package com.bakarvin.klinikhp.admin.crud.dokter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.admin.crud.pasien.EditPasienActivity;
import com.bakarvin.klinikhp.databinding.ActivityDetailDokterBinding;
import com.bakarvin.klinikhp.model.Dokter;
import com.bakarvin.klinikhp.model.JadwalDokter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailDokterActivity extends AppCompatActivity {

    ActivityDetailDokterBinding detailDokterBinding;
    String getKtpDokter;
    String getIdJadwal;
    String passDokter;
    String idJadwal;
    String ketJadwal;
    DatabaseReference dbDokter;
    DatabaseReference dbJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailDokterBinding = ActivityDetailDokterBinding.inflate(getLayoutInflater());
        setContentView(detailDokterBinding.getRoot());
        dbDokter = FirebaseDatabase.getInstance().getReference("Dokter");
        dbJadwal = FirebaseDatabase.getInstance().getReference("Jadwal");
        getKtpDokter = getIntent().getStringExtra("no_ktp");
        getIdJadwal = getIntent().getStringExtra("id_jadwal");
        detailDokterBinding.btnHapusDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusDokter();
            }
        });
        detailDokterBinding.btnEditDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDokter();
            }
        });
        detailDokterBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initDataDokter(getKtpDokter);
        initDataJadwal(getIdJadwal);
    }

    void initDataJadwal(String getIdJadwal) {
        dbJadwal.child(getIdJadwal).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                JadwalDokter jadwalDokter = snapshot.getValue(JadwalDokter.class);
                idJadwal = jadwalDokter.getId_jadwal();
                detailDokterBinding.txtDetailJadwalDokter.setText(jadwalDokter.getHari_jadwal());
                ketJadwal = jadwalDokter.getKeterangan_jadwal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void initDataDokter(String getKtpDokter) {
        Toast.makeText(this, getKtpDokter, Toast.LENGTH_SHORT).show();
        dbDokter.child(getKtpDokter).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dokter dokter = snapshot.getValue(Dokter.class);
                detailDokterBinding.txtDetailKtpDokter.setText(dokter.getKtp_dokter());
                detailDokterBinding.txtDetailNamaDokter.setText(dokter.getNama_dokter());
                detailDokterBinding.txtDetailIdDokter.setText("1");
                detailDokterBinding.txtDetailAlamatDokter.setText(dokter.getAlamat_dokter());
                detailDokterBinding.txtDetailTelpDokter.setText(dokter.getTelp_dokter());
                detailDokterBinding.txtDetailPoliDokter.setText(dokter.getPoli_dokter());
                detailDokterBinding.txtDetailUnameDokter.setText(dokter.getUsername_dokter());
                detailDokterBinding.txtDetailNamaStaff.setText("-");
                passDokter = dokter.getPassword_dokter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//

    }

    private void editDokter() {
        Intent i = new Intent(this, EditDokterActivity.class);
        i.putExtra("no_ktp", detailDokterBinding.txtDetailKtpDokter.getText());
        i.putExtra("nama_dokter", detailDokterBinding.txtDetailNamaDokter.getText());
        i.putExtra("alamat_dokter", detailDokterBinding.txtDetailAlamatDokter.getText());
        i.putExtra("pass_dokter", passDokter.toString());
        i.putExtra("poli_dokter", detailDokterBinding.txtDetailPoliDokter.getText());
        i.putExtra("telp_dokter", detailDokterBinding.txtDetailTelpDokter.getText());
        i.putExtra("user_dokter", detailDokterBinding.txtDetailUnameDokter.getText());
        i.putExtra("hari_jadwal", detailDokterBinding.txtDetailJadwalDokter.getText());
        i.putExtra("id_jadwal", idJadwal.toString());
        i.putExtra("ket_jadwal", ketJadwal.toString());
        Toast.makeText(this, passDokter + idJadwal + ketJadwal, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    void hapusDokter() {
        dbDokter.child(getKtpDokter).removeValue();
        Toast.makeText(this, "Hapus Data Dokter no KTP :" + getKtpDokter, Toast.LENGTH_LONG).show();
        finish();
    }
}