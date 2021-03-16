package com.bakarvin.klinikhp.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.Preferences;
import com.bakarvin.klinikhp.databinding.ActivityLoginStaffBinding;
import com.bakarvin.klinikhp.dokter.MainMenuDokterActivity;
import com.bakarvin.klinikhp.model.Dokter;
import com.bakarvin.klinikhp.model.Staff;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginStaffActivity extends AppCompatActivity {

    ActivityLoginStaffBinding loginStaffBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginStaffBinding = ActivityLoginStaffBinding.inflate(getLayoutInflater());
        setContentView(loginStaffBinding.getRoot());
        loginStaffBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });
        loginStaffBinding.btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void validasiLogin() {
        String userAwal = loginStaffBinding.txtUname.getText().toString();
        String passAwal = loginStaffBinding.txtPass.getText().toString();
        if (userAwal.equals("") && passAwal.equals("")){
            Toast.makeText(this, "Pastikan Anda Sudah Mengisi Username dan Password", Toast.LENGTH_SHORT).show();
        } else {
            initLogin();
        }
    }

    private void initLogin() {
        String userAwal = loginStaffBinding.txtUname.getText().toString();
        String passAwal = loginStaffBinding.txtPass.getText().toString();
        FirebaseDatabase.getInstance().getReference("Staff").child(userAwal)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Staff staff = snapshot.getValue(Staff.class);
                        if (staff == null) {
                            Toast.makeText(getApplicationContext(), "User Name Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                        } else {
                            if (staff.getId_staff().equals(userAwal) && staff.getPass().equals(passAwal)){
                                Toast.makeText(getApplicationContext(), "Login Berhasil" + snapshot.getKey(), Toast.LENGTH_SHORT).show();
                                String idHasil = staff.getId_staff();
                                String unameHasil = staff.getUserName();
                                setPref(idHasil, unameHasil);
                                startActivity(new Intent(getApplicationContext(), MainMenuStaffActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "ID null", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setPref(String idHasil, String unameHasil) {
        Preferences.setLoginStatus(getBaseContext(), true);
        Preferences.setLoginKode(getBaseContext(), "2");
        Preferences.setUserLogin(getBaseContext(), idHasil);
        Preferences.setLoginUname(getBaseContext(), unameHasil);
        Toast.makeText(this, idHasil + "" + unameHasil , Toast.LENGTH_SHORT).show();
    }
}