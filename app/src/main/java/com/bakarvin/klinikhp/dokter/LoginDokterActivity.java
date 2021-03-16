package com.bakarvin.klinikhp.dokter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.Preferences;
import com.bakarvin.klinikhp.databinding.ActivityLoginDokterBinding;
import com.bakarvin.klinikhp.model.Dokter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginDokterActivity extends AppCompatActivity {

    ActivityLoginDokterBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginDokterBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });
        loginBinding.btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void validasiLogin() {
        String userAwal = loginBinding.txtUname.getText().toString();
        String passAwal = loginBinding.txtPass.getText().toString();
        if (userAwal == null && passAwal == null){
            Toast.makeText(this, "Pastikan Anda Sudah Mengisi Username dan Password", Toast.LENGTH_SHORT).show();
        } else {
            initLogin();
        }
    }

    void initLogin(){
        String userAwal = loginBinding.txtUname.getText().toString();
        String passAwal = loginBinding.txtPass.getText().toString();
        FirebaseDatabase.getInstance().getReference("Dokter").child(userAwal)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Dokter dokter = snapshot.getValue(Dokter.class);
                        if (dokter == null) {
                            Toast.makeText(getApplicationContext(), "User Name Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                        } else {
                            if (dokter.getKtp_dokter().equals(userAwal) && dokter.getPassword_dokter().equals(passAwal)){
                                Toast.makeText(getApplicationContext(), "Login Berhasil" + snapshot.getKey(), Toast.LENGTH_SHORT).show();
                                String idHasil = dokter.getKtp_dokter();
                                String unameHasil = dokter.getUsername_dokter();
                                setPref(idHasil, unameHasil);
                                startActivity(new Intent(getApplicationContext(), MainMenuDokterActivity.class));
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
        Preferences.setLoginKode(getBaseContext(), "1");
        Preferences.setUserLogin(getBaseContext(), idHasil);
        Preferences.setLoginUname(getBaseContext(), unameHasil);
        Toast.makeText(this, idHasil + "" + unameHasil , Toast.LENGTH_SHORT).show();
    }

}